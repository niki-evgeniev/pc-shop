package com.example.pcproject.models.DTO;

import com.example.pcproject.models.eunums.ComputerType;
import com.example.pcproject.models.eunums.TypeToUse;

import java.math.BigDecimal;

public record ProductAllDTO(
        String id,
        String brand,
        String model,
        int year,
        BigDecimal price,
        String imageUrl,
        ComputerType computer,
        TypeToUse typeToUse
) {
    public String summary() {
        return brand + " " + model + " " + year;
    }
}

