package com.example.pcproject.controller;


import com.example.pcproject.Service.ProductService;
import com.example.pcproject.models.DTO.product.ProductAllDTO;
import com.example.pcproject.models.DTO.SearchModelDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.pcproject.Common.Constants.ALL_PRODUCTS;

@Controller
public class SearchController {

    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/search")
    public ModelAndView search(@Valid SearchModelDTO searchModelDTO, BindingResult bindingResult,
                               @PageableDefault(size = 5, sort = "price") Pageable pageable) {
        if (!bindingResult.hasErrors()) {

            ModelAndView modelAndView = new ModelAndView("productAll");
            Page<ProductAllDTO> searchingModels = productService.searchModel(pageable, searchModelDTO.getModel());
            modelAndView.addObject("productAll", searchingModels);
            modelAndView.addObject("viewName", ALL_PRODUCTS);
            return modelAndView;
        }
        return new ModelAndView("index");
    }

    @ModelAttribute
    SearchModelDTO searchModelDTO() {
        return new SearchModelDTO();
    }
}
