package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.Company;
import com.example.springsecuritydatabase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String getAllCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompany());
        return "/company/companies";
    }

    @GetMapping("/addCompany")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/addCompany";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/updateCompany/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        model.addAttribute("company",companyService.getCompanyById(id));
        return "company/updateCompany";
    }

    @PostMapping ("/saveUpdate/{id}")
    public String saveUpdateCompany(@PathVariable Long id,@ModelAttribute("company") Company company) {
        companyService.updateCompany(id, company);
        return "redirect:/companies";
    }

//    @RequestMapping("/updateCompany/{id}")
//    public ModelAndView updateCompany(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("/company/updateCompany");
//        Company company = companyService.getCompanyById(id);
//        modelAndView.addObject(company);
//        return modelAndView;
//    }



    @RequestMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }
}
