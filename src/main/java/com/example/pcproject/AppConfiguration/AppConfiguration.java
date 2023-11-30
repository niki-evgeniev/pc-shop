package com.example.pcproject.AppConfiguration;

import com.example.pcproject.models.bindingModels.ProductBindingModel;
import com.example.pcproject.models.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<ProductBindingModel, Product>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        return modelMapper;
    }

}
