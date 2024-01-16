package com.example.pcproject.models.DTO;

import jakarta.validation.constraints.NotNull;

public class SearchModelDTO {

    @NotNull
    private String model;

    public SearchModelDTO() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
