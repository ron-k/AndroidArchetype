package com.ronk.ex20220714.flickr_gallery.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ronk.ex20220714.flickr_gallery.databinding.FragmentFlickrGalleryBinding;

public class FlickrGalleryFragment extends Fragment {

    private static final int SPAN_COUNT = 3;
    private FragmentFlickrGalleryBinding layout;

    public static Fragment newInstance() {
        return new FlickrGalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = FragmentFlickrGalleryBinding.inflate(inflater);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), SPAN_COUNT);
        this.layout.galleryItems.setLayoutManager(layoutManager);

        GalleryViewModel viewModel = FlickrGalleryScope.getViewModel(this);
        this.layout.galleryItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                    if (lastVisibleItemPosition > totalItemCount - 2*SPAN_COUNT) {
                        viewModel.needMore();

                    }
                }
            }
        });
        GalleryItemsAdapter adapter = new GalleryItemsAdapter(viewModel::handleItemClicked, FlickrGalleryScope.getImageLoader(requireContext())) {
            @Override
            protected int getItemViewportSize() {
                return FlickrGalleryFragment.this.layout.galleryItems.getMeasuredWidth() / SPAN_COUNT;
            }
        };
        this.layout.galleryItems.setAdapter(adapter);

        viewModel.getGalleryItems().observe(getViewLifecycleOwner(), adapter::updateItems);
        viewModel.getNavigation().observe(getViewLifecycleOwner(), directive -> {
            if (directive != null) {
                directive.accept(requireContext());
            }
        });

        return this.layout.getRoot();
    }

    @Override
    public void onDestroyView() {
        layout = null;
        super.onDestroyView();
    }


}
