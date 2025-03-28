package com.prm392.dacare.ui.home;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.prm392.dacare.model.Product;
import com.prm392.dacare.repository.pagingsource.ProductDataFactory;
import com.prm392.dacare.repository.pagingsource.ProductPagingSource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.os.Looper;

public class HomeViewModel extends ViewModel {
    private Executor executor;
    private LiveData<PagedList<Product>> productLiveData;
    private ProductDataFactory feedDataFactory;
    private MutableLiveData<ProductPagingSource> dataSourceLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> shouldRetry = new MutableLiveData<>(false);
    private Handler debounceHandler = new Handler(Looper.getMainLooper());

    public HomeViewModel() {
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);
        feedDataFactory = new ProductDataFactory();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();

        productLiveData = new LivePagedListBuilder<>(feedDataFactory, pagedListConfig)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Product>> getProductLiveData() {
        return productLiveData;
    }

    public void retryFetchWithDebounce() {
        debounceHandler.removeCallbacksAndMessages(null);
        debounceHandler.postDelayed(() -> {
            if (dataSourceLiveData.getValue() != null) {
                dataSourceLiveData.getValue().invalidate(); // Invalidate DataSource để reload
            }
        }, 1000);
    }
}