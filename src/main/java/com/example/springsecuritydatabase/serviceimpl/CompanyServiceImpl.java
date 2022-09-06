package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.Company;
import com.example.springsecuritydatabase.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements com.example.springsecuritydatabase.service.CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompany(){
       return companyRepository.findAll();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findCompanyById(id);
    }

    @Override
    public void updateCompany(Long id, Company company) {
        Company company1 = companyRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("not found"));
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        companyRepository.save(company1);
    }

    // public String updatePersonById(Long id, Person person) {
//        Person updatePerson = repository.findById(id).orElseThrow(
//                () -> new RuntimeException("not found"));
//        updatePerson.setName(person.getName());
//        updatePerson.setAge(person.getAge());
//        updatePerson.setEmail(person.getEmail());
//        updatePerson.setPhoneNumber(person.getPhoneNumber());
//        repository.save(updatePerson);
//        return "person updated";
//    }


    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }
}
