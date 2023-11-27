package com.example.pcproject.models.bindingModels;

import java.util.List;

public record BrandDTO(
        String name,
        List<ModelDTO> models) {
}

