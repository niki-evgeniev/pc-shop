package com.example.pcproject.Service.impl;

import com.example.pcproject.Repository.BrandRepository;
import com.example.pcproject.Repository.ModelRepository;
import com.example.pcproject.Service.BrandService;
import com.example.pcproject.models.DTO.AddBrandAndModelDTO;
import com.example.pcproject.models.DTO.BrandDTO;
import com.example.pcproject.models.DTO.ModelDTO;
import com.example.pcproject.models.entity.Brand;
import com.example.pcproject.models.entity.Model;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandDTO> getAllBrand() {
        return brandRepository.getAllBrands().stream()
                .map(brand -> new BrandDTO(
                        brand.getName(),
                        brand.getModels().stream()
                                .map(model -> new ModelDTO(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(ModelDTO::name))
                                .collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(BrandDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addBrand(AddBrandAndModelDTO addBrandAndModelDTO) {
        LocalDateTime timeNow = LocalDateTime.now();

        Brand nameBrand = brandRepository.findByName(addBrandAndModelDTO.getBrand());
        if (nameBrand == null) {
            Brand newBrand = modelMapper.map(addBrandAndModelDTO, Brand.class);
            newBrand.setCreated(timeNow);
            newBrand.setName(addBrandAndModelDTO.getBrand());
            brandRepository.save(newBrand);
            return true;
        }
        return false;
    }
}
