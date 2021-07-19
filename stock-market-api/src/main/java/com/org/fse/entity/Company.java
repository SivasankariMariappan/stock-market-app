package com.org.fse.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;


@Document(collection="company")
public class Company {

    @Id
    @ApiModelProperty(notes = "The Company Code")
    private String companyCode;
    @ApiModelProperty(notes = "The Company Name")
    private String companyName;
    @ApiModelProperty(notes = "The Company CEO")
    private String companyCeo;
    @ApiModelProperty(notes = "Turn over of a company")
    private BigDecimal turnOver;
    @ApiModelProperty(notes = "Official webiste of a company")    
    private String companyLink;
    @ApiModelProperty(notes = "Stock Exchange")
    private String stockExchange;
    @ApiModelProperty(notes = "Stock Price List of a company")
    private List<StockDetails> stockDetailsList;

    public Company(String companyCode, String companyName, String companyCeo, BigDecimal turnOver, String companyLink, String stockExchange) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyCeo = companyCeo;
        this.turnOver = turnOver;
        this.companyLink = companyLink;
        this.stockExchange = stockExchange;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCeo() {
        return companyCeo;
    }

    public void setCompanyCeo(String companyCeo) {
        this.companyCeo = companyCeo;
    }

    public BigDecimal getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(BigDecimal turnOver) {
        this.turnOver = turnOver;
    }

    public String getCompanyLink() {
        return companyLink;
    }

    public void setCompanyLink(String companyLink) {
        this.companyLink = companyLink;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public List<StockDetails> getStockDetailsList() {
        return stockDetailsList;
    }

    public void setStockDetailsList(List<StockDetails> stockDetailsList) {
        this.stockDetailsList = stockDetailsList;
    }
}
