package com.org.fse.repository;

import com.org.fse.dto.StockSearchResponse;
import com.org.fse.entity.Company;

import java.util.Date;
import java.util.List;

public interface StockSearch {

   List<StockSearchResponse> aggregate(String companyCode, Date fromDate, Date toDate);
   List<Company> findAllByLatestStockDate();
}
