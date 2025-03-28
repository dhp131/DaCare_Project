package com.prm392.dacare.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.prm392.dacare.R;
import com.prm392.dacare.adapter.ImageSliderAdapter;
import com.prm392.dacare.adapter.ProductPaginationAdapter;
import com.prm392.dacare.databinding.FragmentHomeBinding;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductPaginationAdapter adapter;

    private ProgressBar progressBar;
    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;


    private ViewPager2 viewPager;
    private ImageSliderAdapter sliderAdapter;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private int currentPage = 0;
    private final List<Integer> imageList = Arrays.asList(
            R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4
    );


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);
        progressBar = root.findViewById(R.id.loadingIndicator);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new ProductPaginationAdapter();

        recyclerView.setAdapter(adapter);
        Log.i("TAG", "onCreateView: Home fragment init");
        viewModel.getProductLiveData().observe(getViewLifecycleOwner(), products -> {
            if (!products.isEmpty()){
                progressBar.setVisibility(View.GONE);
                adapter.submitList(products);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }

        });

        viewPager = root.findViewById(R.id.viewPagerSlider);
        sliderAdapter = new ImageSliderAdapter(imageList);
        viewPager.setAdapter(sliderAdapter);
        runnable = () -> {
            if (currentPage == imageList.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(runnable, 3000);
        };
        handler.postDelayed(runnable, 3000);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
        binding = null;
    }
}