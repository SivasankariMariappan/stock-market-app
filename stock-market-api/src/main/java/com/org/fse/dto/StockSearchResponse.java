package com.org.fse.dto;

import com.org.fse.entity.StockDetails;

import java.util.List;

public class StockSearchResponse {

    private List<StockDetails> stockDetails;
    private  float minPrice;
    private  float maxPrice;
    private  float avgPrice;

    public List<StockDetails> getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(List<StockDetails> stockDetails) {
        this.stockDetails = stockDetails;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(float avgPrice) {
        this.avgPrice = avgPrice;
    }
}
