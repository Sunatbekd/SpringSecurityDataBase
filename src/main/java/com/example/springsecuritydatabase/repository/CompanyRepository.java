package com.example.springsecuritydatabase.repository;

import com.example.springsecuritydatabase.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findCompanyById(Long id);


}
