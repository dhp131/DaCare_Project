package com.prm392.dacare.ui.home;

import android.os.Bundle;
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

import com.prm392.dacare.R;
import com.prm392.dacare.adapter.ProductPaginationAdapter;
import com.prm392.dacare.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductPaginationAdapter adapter;

    private ProgressBar progressBar;
    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

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
        adapter.notifyDataSetChanged();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}