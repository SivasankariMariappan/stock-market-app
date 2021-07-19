package com.org.fse.entity;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection="stock")
public class StockDetails {

    @ApiModelProperty(notes = "Stock Price of a company")
    private float stockPrice;

    @ApiModelProperty(notes = "Stock added date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date stockDate;

    public StockDetails(float stockPrice, Date stockDate) {
        this.stockPrice = stockPrice;
        this.stockDate = stockDate;
    }

    public float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }
}
