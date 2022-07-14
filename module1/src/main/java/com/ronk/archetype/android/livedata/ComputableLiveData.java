/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ronk.archetype.android.livedata;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * based on the (currently) internal class {@link androidx.lifecycle.ComputableLiveData}
 */
public abstract class ComputableLiveData<T> {
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final Executor mExecutor;
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final MutableLiveData<T> mLiveData;
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final AtomicBoolean mInvalid = new AtomicBoolean(true);
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final AtomicBoolean mComputing = new AtomicBoolean(false);

    private final Handler mHandler;

    /**
     * Creates a computable live data that computes values on the specified executor.
     *
     * @param executor Executor that is used to compute new LiveData values.
     */
    public ComputableLiveData(@NonNull Executor executor) {
        mExecutor = executor;
        mLiveData = new MutableLiveData<>() {
            @Override
            protected void onActive() {
                ComputableLiveData.this.onActive();
            }

            @Override
            protected void onInactive() {
                ComputableLiveData.this.onInactive();
            }
        };
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Returns the LiveData managed by this class.
     *
     * @return A LiveData that is controlled by ComputableLiveData.
     */
    @SuppressWarnings("WeakerAccess")
    @NonNull
    public LiveData<T> getLiveData() {
        return mLiveData;
    }

    @VisibleForTesting
    final Runnable mRefreshRunnable = new Runnable() {
        @WorkerThread
        @Override
        public void run() {
            boolean computed;
            do {
                computed = false;
                // compute can happen only in 1 thread but no reason to lock others.
                if (mComputing.compareAndSet(false, true)) {
                    // as long as it is invalid, keep computing.
                    try {
                        T value = null;
                        while (mInvalid.compareAndSet(true, false)) {
                            computed = true;
                            value = compute();
                        }
                        if (computed) {
                            mLiveData.postValue(value);
                        }
                    } finally {
                        // release compute lock
                        mComputing.set(false);
                    }
                }
                // check invalid after releasing compute lock to avoid the following scenario.
                // Thread A runs compute()
                // Thread A checks invalid, it is false
                // Main thread sets invalid to true
                // Thread B runs, fails to acquire compute lock and skips
                // Thread A releases compute lock
                // We've left invalid in set state. The check below recovers.
            } while (computed && mInvalid.get());
        }
    };

    // invalidation check always happens on the main thread
    @VisibleForTesting
    final Runnable mInvalidationRunnable = new Runnable() {
        @MainThread
        @Override
        public void run() {
            boolean isActive = mLiveData.hasActiveObservers();
            if (mInvalid.compareAndSet(false, true)) {
                if (isActive) {
                    mExecutor.execute(mRefreshRunnable);
                }
            }
        }
    };

    /**
     * Invalidates the LiveData.
     * <p>
     * When there are active observers, this will trigger a call to {@link #compute()}.
     */
    public void invalidate() {
        mHandler.post(mInvalidationRunnable);
    }

    // TODO https://issuetracker.google.com/issues/112197238
    @SuppressWarnings({"WeakerAccess", "UnknownNullness"})
    @WorkerThread
    protected abstract T compute();


    @CallSuper
    protected void onActive() {
        mExecutor.execute(mRefreshRunnable);
    }

    protected void onInactive() {

    }
}
