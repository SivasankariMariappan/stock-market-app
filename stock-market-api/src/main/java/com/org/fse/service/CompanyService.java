package com.org.fse.service;

import com.org.fse.entity.Company;
import com.org.fse.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> getCompanyByCode(String companyCode) {
       return companyRepository.findById(companyCode);
    }

   // TODO:: get latest stock price alone
    public List<Company> getAllCompanies() {
        return companyRepository.findAllByLatestStockDate();
    }

    public String deleteCompany(String companyCode) {
        companyRepository.deleteById(companyCode);
        return "CompanyDetails have been deleted successfully";
    }

    public List filterStockDetails(String companyCode, Date startDate, Date endDate) {
       return companyRepository.aggregate(companyCode,startDate,endDate);
    }
}
