package com.prm392.dacare.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.prm392.dacare.model.Product;
import com.prm392.dacare.repository.pagingsource.ProductDataFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;



public class HomeViewModel extends ViewModel {

    private Executor executor;
    private LiveData<PagedList<Product>> productLiveData;

    public HomeViewModel() {
        init();
    }


    private void init() {
        executor = Executors.newFixedThreadPool(5);

        ProductDataFactory feedDataFactory = new ProductDataFactory();


        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        productLiveData = (new LivePagedListBuilder<>(feedDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }


    public LiveData<PagedList<Product>> getProductLiveData() {
        return productLiveData;
    }
}