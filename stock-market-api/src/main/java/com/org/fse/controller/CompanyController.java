package com.org.fse.controller;


import com.org.fse.dto.StockSearchResponse;
import com.org.fse.entity.Company;
import com.org.fse.entity.StockDetails;
import com.org.fse.exception.CompanyNotFoundException;
import com.org.fse.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1.0/market")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CompanyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CompanyService companyService;


    @GetMapping(path="/company/info/{companyCode}")
    public Company getCompanyByCode(@PathVariable String companyCode){
        logger.info("Get company details for"+ companyCode);
        Optional<Company> optionalCompany=  companyService.getCompanyByCode(companyCode);
        if(optionalCompany.isPresent())
            return optionalCompany.get();
        throw new CompanyNotFoundException("Company "+ companyCode +" - Not Found");
    }

    @GetMapping(path="/company/getall")
    public List<Company> getAllCompanies(){
       return companyService.getAllCompanies();
    }

    @PostMapping(path="/company/register")
    public Company registerCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @DeleteMapping(path="/company/delete/{companyCode}")
    public String deleteCompany(@PathVariable String companyCode){
        logger.info("Delete details of a company with code"+ companyCode);
        return companyService.deleteCompany(companyCode);
    }


    @PostMapping(path="/stock/add/{companyCode}")
    public Company addStock(@PathVariable String companyCode, @RequestBody StockDetails s){
        Optional<Company> optionalCompany=  companyService.getCompanyByCode(companyCode);
        if(!optionalCompany.isPresent())
            throw new CompanyNotFoundException("Company "+ companyCode +" - Not Found");

       logger.info("Stock details to be added for a company with code "+ companyCode);
       StockDetails stock= new StockDetails(s.getStockPrice(), new Date());
       Company companyToBeUpdated= optionalCompany.get();
       List<StockDetails> stockDetails= companyToBeUpdated.getStockDetailsList();

       if(null == stockDetails || stockDetails.size()<= 0)
           stockDetails =  new ArrayList<StockDetails>();

       stockDetails.add(stock);
       companyToBeUpdated.setStockDetailsList(stockDetails);

       return companyService.addCompany(companyToBeUpdated);
    }


    @GetMapping(path="/stock/get/{companyCode}/{startDate}/{endDate}")
    public List<StockSearchResponse> filterStockDetails(@PathVariable String companyCode, @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date endDate){
        logger.info("Filter details of a company with code " + companyCode + "StartDate" + startDate + "endDate" +endDate );
       return companyService.filterStockDetails(companyCode,startDate,endDate);
    }



}
