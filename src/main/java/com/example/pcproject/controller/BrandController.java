package com.example.pcproject.controller;

import com.example.pcproject.Service.BrandService;
import com.example.pcproject.Service.ModelService;
import com.example.pcproject.models.DTO.AddBrandAndModelDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;

    public BrandController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping("/brandsAdd")
    public ModelAndView addBrands() {
        return new ModelAndView("brandModelAdd");
    }

    @PostMapping("/brandsAdd")
    public ModelAndView addBrands(@Valid AddBrandAndModelDTO addBrandAndModelDTO, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean brandIsAdded = brandService.addBrand(addBrandAndModelDTO);
            boolean modelIsAdded = modelService.addModel(addBrandAndModelDTO);

            if (!brandIsAdded && !modelIsAdded) {
                return new ModelAndView("brandModelAdd");
            }
        }

        return new ModelAndView("redirect:/admin/admin-panel");
    }

    @ModelAttribute
    AddBrandAndModelDTO addBrandAndModelDTO() {
        return new AddBrandAndModelDTO();
    }
}
