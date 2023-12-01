package com.example.pcproject.controller;

import com.example.pcproject.Service.BrandService;
import com.example.pcproject.Service.ProductService;
import com.example.pcproject.Service.exception.ObjectNotFoundException;
import com.example.pcproject.models.bindingModels.ProductBindingModel;
import com.example.pcproject.models.bindingModels.ProductDetailsDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView add(@Valid ProductBindingModel productBindingModel, BindingResult bindingResult
            , @AuthenticationPrincipal UserDetails user) {

        if (!bindingResult.hasErrors()) {
            boolean productIsAdded = productService.addProduct(productBindingModel, user);
            if (productIsAdded) {
                return new ModelAndView("redirect:/");
            }
        }

        return new ModelAndView("productAdd");
    }

    @ModelAttribute
    ProductBindingModel productBindingModel() {
        return new ProductBindingModel();
    }


    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable Long id) {

        ProductDetailsDTO productDetails = productService.getDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer details not found"));
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("productDetails", productDetails);

        return modelAndView;
    }

    @DeleteMapping("{id}")
    public ModelAndView delete(@PathVariable Long id) {

        productService.soldProduct(id);

        return new ModelAndView("redirect:/product/all");
    }
}
