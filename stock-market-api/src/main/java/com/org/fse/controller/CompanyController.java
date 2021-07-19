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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1.0/market")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value="StockMarket", description="Stock Market Application to see stocks of different companies.")
@RestController
public class CompanyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "View a company by its code",response = Company.class)
    @GetMapping(path="/company/info/{companyCode}")
    public Company getCompanyByCode(@PathVariable String companyCode){
        logger.info("Get company details for"+ companyCode);
        Optional<Company> optionalCompany=  companyService.getCompanyByCode(companyCode);
        if(optionalCompany.isPresent())
            return optionalCompany.get();
        throw new CompanyNotFoundException("Company "+ companyCode +" - Not Found");
    }

    @ApiOperation(value = "View a list of available companies",response = Iterable.class)
    @GetMapping(path="/company/getall")
    public List<Company> getAllCompanies(){
       return companyService.getAllCompanies();
    }

    @ApiOperation(value = "Add a company", response=Company.class)
    @PostMapping(path="/company/register")
    public Company registerCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @ApiOperation(value = "Delete a company")
    @DeleteMapping(path="/company/delete/{companyCode}")
    public String deleteCompany(@PathVariable String companyCode){
        logger.info("Delete details of a company with code"+ companyCode);
        return companyService.deleteCompany(companyCode);
    }

    @ApiOperation(value = "Add a stock price to the company", response=Company.class)
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

    @ApiOperation(value = "View the stock details of a company between specified time", response=Iterable.class)
    @GetMapping(path="/stock/get/{companyCode}/{startDate}/{endDate}")
    public List<StockSearchResponse> filterStockDetails(@PathVariable String companyCode, @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date endDate){
        logger.info("Filter details of a company with code " + companyCode + "StartDate" + startDate + "endDate" +endDate );
       return companyService.filterStockDetails(companyCode,startDate,endDate);
    }



}
