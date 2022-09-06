//package com.example.springsecuritydatabase.controller;
//
//import com.example.springsecuritydatabase.entity.Product;
//import com.example.springsecuritydatabase.serviceimpl.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/")
//public class ProductController {
//
//    private final ProductService productService;
//
//    @RequestMapping("/")
//    public String getAllProduct(Model model){
//        List<Product> productList = productService.getAllProducts();
//        model.addAttribute("allProducts",productList);
//        return "product_page";
//    }
//
//    @RequestMapping("/new")
//    public String showPage(Model model){
//        model.addAttribute("product", new Product());
//        return "new_product";
//    }
//
//    @RequestMapping(value = "/save",method = RequestMethod.POST)
//    public String saveProduct(@ModelAttribute("product") Product product){
//        productService.saveProduct(product);
//        return "redirect:/";
//    }
//
//    @RequestMapping("/edit/{id}")
//    public ModelAndView editProduct(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("edit_product");
//        Product product = productService.getProductById(id);
//        modelAndView.addObject(product);
//        return modelAndView;
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable(name = "id")Long id){
//        productService.deleteProduct(id);
//        return "redirect:/";
//    }
//}
