package com.example.pcproject.controller;

import com.example.pcproject.service.ProductService;
import com.example.pcproject.models.DTO.product.ProductAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.pcproject.common.Constants.*;

@Controller
@RequestMapping("product")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ModelAndView allProduct(
            @PageableDefault(size = 5, sort = "price")
            Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("productAll");
        Page<ProductAllDTO> allProduct = productService.getAllProduct(pageable);
        modelAndView.addObject("productAll", allProduct);
        modelAndView.addObject("viewName", ALL_PRODUCTS);
        return modelAndView;
    }

    @GetMapping("/laptop")
    public ModelAndView allLaptop(
            @PageableDefault(size = 5, sort = "price")
            Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("productAll");
        Page<ProductAllDTO> allLaptop = productService.getAllLaptop(pageable);
        modelAndView.addObject("productAll", allLaptop);
        modelAndView.addObject("viewName", ALL_LAPTOPS);
        return modelAndView;
    }
    @GetMapping("/computer")
    public ModelAndView allComputer(
            @PageableDefault(size = 5, sort = "price")
            Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("productAll");
        Page<ProductAllDTO> allComputer = productService.getAllComputer(pageable);
        modelAndView.addObject("productAll", allComputer);
        modelAndView.addObject("viewName", ALL_COMPUTER);
        return modelAndView;
    }
}
