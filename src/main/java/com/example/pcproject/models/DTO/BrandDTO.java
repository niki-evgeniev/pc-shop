package com.example.pcproject.models.DTO;

import java.util.ArrayList;
import java.util.List;

public class BrandDTO {

    private String name;
    private List<ModelDTO> models = new ArrayList<>();

    public BrandDTO() {
    }

    public BrandDTO(String name, List<ModelDTO> collect) {
        this.name = name;
        this.models = collect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public void setModels(List<ModelDTO> models) {
        this.models = models;
    }
}

