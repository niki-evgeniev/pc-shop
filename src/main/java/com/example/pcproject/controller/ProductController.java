package com.example.pcproject.controller;

import com.example.pcproject.Service.BrandService;
import com.example.pcproject.Service.ProductService;
import com.example.pcproject.models.bindingModels.ProductBindingModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final BrandService brandService;
    private final ProductService productService;

    public ProductController(BrandService brandService, ProductService productService) {
        this.brandService = brandService;
        this.productService = productService;
    }

    @GetMapping("/add")
    public ModelAndView add() {

        ModelAndView modelAndView = new ModelAndView("productAdd");
        modelAndView.addObject("brands", brandService.getAllBrand());


        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid ProductBindingModel productBindingModel, BindingResult bindingResult){

        if (!bindingResult.hasErrors()){
            boolean productIsAdded = productService.addProduct(productBindingModel);
            if (productIsAdded){
            return new ModelAndView("redirect:/");
            }
        }
        return new ModelAndView("productAdd");
    }

    @ModelAttribute
    ProductBindingModel productBindingModel(){
        return new ProductBindingModel();
    }

}
