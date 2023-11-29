package com.example.pcproject.Service;

import com.example.pcproject.models.bindingModels.ProductAllBindingModel;
import com.example.pcproject.models.bindingModels.ProductBindingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    boolean addProduct(ProductBindingModel productBindingModel);

    Page<ProductAllBindingModel> getAllProduct(Pageable pageable);
}
