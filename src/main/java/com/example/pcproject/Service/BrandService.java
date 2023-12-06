package com.example.pcproject.Service;

import com.example.pcproject.models.DTO.AddBrandAndModelDTO;
import com.example.pcproject.models.DTO.BrandDTO;

import java.util.List;

public interface BrandService {

    List<BrandDTO> getAllBrand();

    boolean addBrand(AddBrandAndModelDTO addBrandAndModelDTO);
}
