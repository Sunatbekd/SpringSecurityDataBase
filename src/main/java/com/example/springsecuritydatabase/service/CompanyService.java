package com.example.springsecuritydatabase.service;


import com.example.springsecuritydatabase.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyService {
    List<Company> getAllCompany();
    void addCompany(Company company);
    Company getCompanyById(Long id);
    void updateCompany(Long id,Company company);
    void deleteCompany(Long id);
}
