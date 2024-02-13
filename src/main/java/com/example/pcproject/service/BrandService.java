package com.example.pcproject.service;

import com.example.pcproject.models.DTO.brand.AddBrandAndModelDTO;
import com.example.pcproject.models.DTO.brand.BrandDTO;

import java.util.List;

public interface BrandService {

    List<BrandDTO> getAllBrand();

    boolean addBrand(AddBrandAndModelDTO addBrandAndModelDTO);
}
