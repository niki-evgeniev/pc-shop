package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.ModelRepository;
import com.example.pcproject.Repository.ProductRepository;
import com.example.pcproject.Repository.UserRepository;
import com.example.pcproject.Service.ProductService;
import com.example.pcproject.Service.aop.ExecutionTime;
import com.example.pcproject.models.bindingModels.ProductAllDTO;
import com.example.pcproject.models.bindingModels.ProductDTO;
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
    public boolean addProduct(ProductDTO productDTO, UserDetails userDetails) {

        Product newProduct = modelMapper.map(productDTO, Product.class);
        Model model = modelRepository.findById(productDTO.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found"));
        newProduct.setModel(model);
        newProduct.setCreated(LocalDateTime.now());
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        newProduct.setSeller(user);
        productRepository.save(newProduct);

        return true;
    }

    @ExecutionTime(
            time = 2000L
    )
    @Override
    public Page<ProductAllDTO> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductServiceImpl::mapAsSummary);
    }


    @ExecutionTime(
            time = 1000L
    )
    @Override
    public Optional<ProductDetailsDTO> getDetails(Long id) {
        return productRepository.findById(id).map(ProductServiceImpl::mapAsDetails);
    }

    @Override
    public void cleanExpiredProduct() {
//        List<Product> allProduct = productRepository.findAll();
//
//        for (Product product : allProduct) {
//            LocalDateTime created = product.getCreated();
//            LocalDateTime afterDays = created.plusDays(30);
//            if (LocalDateTime.now() > afterDays){
//
//            }
        //TODO FINISH
//        }
    }

    @Override
    public void soldProduct(Long id) {
        productRepository.deleteById(id);
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

    private static ProductAllDTO mapAsSummary(Product product) {
        return new ProductAllDTO(
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

