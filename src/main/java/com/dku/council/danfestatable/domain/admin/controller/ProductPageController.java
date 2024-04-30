package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.admin.dto.request.RequestCreateProductDto;
import com.dku.council.danfestatable.domain.admin.service.ProductPageService;
import com.dku.council.danfestatable.domain.product.model.dto.list.SummarizedProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/manage/product")
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductPageService service;

    @GetMapping
    public String product(Model model, RequestCreateProductDto dto) {
        List<SummarizedProductDto> all = service.getProducts();
        model.addAttribute("products", all);
        model.addAttribute("object", dto);
        return "page/product/product";
    }

    @PostMapping("/create")
    public String createProduct(HttpServletRequest request, RequestCreateProductDto dto) {
        service.createProduct(dto);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/{productId}/delete")
    public String deleteProduct(HttpServletRequest request, @PathVariable Long productId) {
        service.deleteProduct(productId);
        return "redirect:" + request.getHeader("Referer");
    }
}
