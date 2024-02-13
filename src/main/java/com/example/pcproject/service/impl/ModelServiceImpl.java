package com.example.pcproject.service.impl;

import com.example.pcproject.repository.BrandRepository;
import com.example.pcproject.repository.ModelRepository;
import com.example.pcproject.service.ModelService;
import com.example.pcproject.models.DTO.brand.AddBrandAndModelDTO;
import com.example.pcproject.models.entity.Brand;
import com.example.pcproject.models.entity.Model;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper,
                            BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean addModel(AddBrandAndModelDTO addBrandAndModelDTO) {
        Model nameModel = modelRepository.findByName(addBrandAndModelDTO.getName());
        Brand byName = brandRepository.findByName(addBrandAndModelDTO.getBrand());

        if (nameModel == null) {
            Model newModel = modelMapper.map(addBrandAndModelDTO, Model.class);
            newModel.setCreated(LocalDateTime.now());
            newModel.setBrand(byName);
            modelRepository.save(newModel);
            return true;
        }
        return false;
    }
}
