package com.org.fse.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;


@Document(collection="company")
public class Company {

    @Id
    private String companyCode;
    private String companyName;
    private String companyCeo;
    private BigDecimal turnOver;
    private String companyLink;
    private String stockExchange;
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
