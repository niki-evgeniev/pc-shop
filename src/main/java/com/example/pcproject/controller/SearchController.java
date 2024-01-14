package com.example.pcproject.controller;


import com.example.pcproject.Service.ProductService;
import com.example.pcproject.models.DTO.ProductAllDTO;
import com.example.pcproject.models.DTO.SearchDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/search")
    public ModelAndView search(@Valid SearchDTO searchDTO, BindingResult bindingResult,
    @PageableDefault(size = 5, sort = "price") Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("productAll");
        Page<ProductAllDTO> searchingModels = productService.searchModel(pageable, searchDTO.getModel());
        modelAndView.addObject("productAll", searchingModels);
        modelAndView.addObject("viewName", "ALL");

        return modelAndView;
    }

    @ModelAttribute
    SearchDTO searchDTO() {
        return new SearchDTO();
    }
}
