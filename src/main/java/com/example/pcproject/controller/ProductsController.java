package com.example.pcproject.controller;

import com.example.pcproject.Service.ProductService;
import com.example.pcproject.models.bindingModels.ProductAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("product")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public ModelAndView allProduct (
            @PageableDefault(size = 5 , sort = "price")
            Pageable pageable){

        ModelAndView modelAndView = new ModelAndView("productAll");
        Page<ProductAllDTO> allProduct = productService.getAllProduct(pageable);
        modelAndView.addObject("productAll", allProduct);

        return modelAndView;
    }
}
