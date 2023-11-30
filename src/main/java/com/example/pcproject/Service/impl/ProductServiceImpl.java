package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.ModelRepository;
import com.example.pcproject.Repository.ProductRepository;
import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Service.ProductService;
import com.example.pcproject.models.bindingModels.ProductAllBindingModel;
import com.example.pcproject.models.bindingModels.ProductBindingModel;
import com.example.pcproject.models.bindingModels.ProductDetailsDTO;
import com.example.pcproject.models.entity.Model;
import com.example.pcproject.models.entity.Product;
import com.example.pcproject.models.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository,
                              ProductRepository productRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean addProduct(ProductBindingModel productBindingModel, UserDetails userDetails) {

        Product newProduct = modelMapper.map(productBindingModel, Product.class);
        Model model = modelRepository.findById(productBindingModel.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found"));
        newProduct.setModel(model);
        newProduct.setCreated(LocalDateTime.now());
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow( ()-> new IllegalArgumentException("User not found"));
        newProduct.setSeller(user);

        productRepository.save(newProduct);
        return true;
    }

    @Override
    public Page<ProductAllBindingModel> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductServiceImpl::mapAsSummary);
    }

    @Override
    public Optional<ProductDetailsDTO> getDetails(Long id) {
        return productRepository.findById(id).map(ProductServiceImpl::mapAsDetails);
    }

    private static ProductDetailsDTO mapAsDetails(Product product) {

        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        productDetailsDTO.setId(product.getId());
        productDetailsDTO.setBrand(product.getModel().getBrand().getName());
        productDetailsDTO.setModel(product.getModel().getName());
        productDetailsDTO.setYear(product.getYear());
        productDetailsDTO.setPrice(product.getPrice());
        productDetailsDTO.setImageUrl(product.getImageUrl());
        productDetailsDTO.setPhoneNumber(product.getPhoneNumber());
        productDetailsDTO.setDescription(product.getDescription());
        productDetailsDTO.setComputerType(product.getComputerType());
        productDetailsDTO.setTypeToUse(product.getTypeToUse());
        productDetailsDTO.setSeller(product.getSeller().getUsername());

        return productDetailsDTO;
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

