package com.example.pcproject.models.bindingModels;

import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.models.eunums.TypeToUse;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductBindingModel {

    @NotNull
    private ComputerType computerType;

    @Positive
    @NotNull
    private Long modelId;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private TypeToUse typeToUse;

    @NotNull
    private String imageUrl;

    @NotNull
    @Min(value = 2020 , message = "Year must be bigger then 2020")
    private Integer year;

    @NotEmpty
    @Size(min = 5, max = 512)
    private String description;

    public ProductBindingModel() {
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TypeToUse getTypeToUse() {
        return typeToUse;
    }

    public void setTypeToUse(TypeToUse typeToUse) {
        this.typeToUse = typeToUse;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
