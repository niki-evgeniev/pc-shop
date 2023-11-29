package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.ModelRepository;
import com.example.pcproject.Repository.ProductRepository;
import com.example.pcproject.Service.ProductService;
import com.example.pcproject.models.bindingModels.ProductAllBindingModel;
import com.example.pcproject.models.bindingModels.ProductBindingModel;
import com.example.pcproject.models.entity.Model;
import com.example.pcproject.models.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.productRepository = productRepository;
    }

    @Override
    public boolean addProduct(ProductBindingModel productBindingModel) {

        Product newProduct = modelMapper.map(productBindingModel, Product.class);
        Model model = modelRepository.findById(productBindingModel.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found"));
        newProduct.setModel(model);
        newProduct.setCreated(LocalDateTime.now());

        productRepository.save(newProduct);
        return true;
    }

    @Override
    public Page<ProductAllBindingModel> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductServiceImpl::mapAsSummary);
    }

    private static ProductAllBindingModel mapAsSummary(Product product) {
        return new ProductAllBindingModel(
                product.getId().toString(),
                product.getModel().getBrand().getName(),
                product.getModel().getName(),
                product.getYear(),
                product.getPrice(),
                product.getImageUrl(),
                product.getComputerType(),
                product.getTypeToUse());
    }
}
