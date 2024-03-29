package com.example.pcproject.models.DTO.brand;

import com.example.pcproject.models.eunums.ComputerType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddBrandAndModelDTO {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20, message = "The symbol min is 2 max is 20")
    private String brand;

    //model
    @NotNull(message = "Please select type")
    private ComputerType computerType;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 20, message = "The symbol min is 2 max is 20")
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 4, message = "The symbol min is 3 max is 4")
    @Min(value = 2015, message = "Year must be bigger then 2015")
    private String startYear;

    @NotNull
    @NotBlank(message = "Enter URL for model")
    private String imageUrl;

    public AddBrandAndModelDTO() {
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
