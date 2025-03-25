package com.prm392.dacare.repository.pagingsource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.prm392.dacare.model.Product;

public class ProductDataFactory extends DataSource.Factory<Integer, Product> {

    private MutableLiveData<ProductPagingSource> mutableLiveData;
    private ProductPagingSource productPagingSource;

    public ProductDataFactory(){
        this.mutableLiveData = new MutableLiveData<ProductPagingSource>();
    }

    @NonNull
    @Override
    public DataSource create() {
        productPagingSource = new ProductPagingSource();
        mutableLiveData.postValue(productPagingSource);
        return productPagingSource;
    }
    public MutableLiveData<ProductPagingSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
