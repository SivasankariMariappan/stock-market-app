package com.org.fse.repository;

import com.org.fse.dto.StockSearchResponse;
import com.org.fse.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.Date;
import java.util.List;

public class StockSearchImpl implements  StockSearch {

    Logger logger =  LoggerFactory.getLogger(StockSearchImpl.class);


    private final MongoTemplate mongoTemplate;

    @Autowired
    public StockSearchImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<StockSearchResponse> aggregate(String companyCode, Date fromDate, Date toDate) {
        logger.info("Search impl >>" + companyCode + "StartDate" + fromDate + "endDate" +fromDate );
        MatchOperation companyMatchOperation = matchCompanyCode(companyCode);
        UnwindOperation unwindOperation =unWindStockDetailsList();
        MatchOperation stockMatchOperation = matchStockDate(fromDate, toDate);
        GroupOperation groupOperation = getGroupOperation();
        ProjectionOperation projectionOperation = getProjectOperation();

        List<StockSearchResponse> response= mongoTemplate.aggregate(newAggregation(
                companyMatchOperation,
                unwindOperation,
                stockMatchOperation,
                groupOperation,
                projectionOperation
        ), Company.class, StockSearchResponse.class).getMappedResults();

        logger.info("response from interface >>" + response);
        return response;
    }

    private UnwindOperation unWindStockDetailsList(){
        return unwind("stockDetailsList");
    }

    private MatchOperation matchCompanyCode (String companyCode){
        return match(where("companyCode").is(companyCode));
    }

    private MatchOperation matchStockDate(Date fromDate, Date toDate) {
        Criteria priceCriteria =where("stockDetailsList.stockDate").gte(fromDate).andOperator(where("stockDetailsList.stockDate").lte(toDate));
        return match(priceCriteria);
    }

    private GroupOperation getGroupOperation() {
        return group()
                .push("stockDetailsList").as("stockDetails")
                .avg("stockDetailsList.stockPrice").as("avgPrice")
                .min("stockDetailsList.stockPrice").as("minPrice")
                .max("stockDetailsList.stockPrice").as("maxPrice");
    }

    private ProjectionOperation getProjectOperation() {
        return project( "stockDetails", "avgPrice","minPrice", "maxPrice" )
                .and("companyCode").previousOperation();
    }

    private SortOperation sortByStockDate(){
        return sort(Sort.by(Direction.DESC, "stockDetailsList.stockDate"));
    }

    private GroupOperation groupOperationForFindAll(){
        return group("companyCode")
                .first("companyName").as("companyName")
                .first("companyCeo").as("companyCeo")
                .first("turnOver").as("turnOver")
                .first("companyLink").as("companyLink")
                .first("stockExchange").as("stockExchange")
                .push("stockDetailsList").as("stockDetailsList");
    }

    private ProjectionOperation projectionOperationForFindAll(){
        return project("companyCode", "companyName", "companyCeo", "turnOver", "companyLink", "stockExchange").and("stockDetailsList").slice(1).as("stockDetailsList");
    }

    @Override
    public List<Company> findAllByLatestStockDate() {


        UnwindOperation unwindOperation =unWindStockDetailsList();
        SortOperation sortOperation = sortByStockDate();
        GroupOperation groupOperation =groupOperationForFindAll();
        ProjectionOperation projectionOperation = projectionOperationForFindAll();
        List<Company> response= mongoTemplate.aggregate(newAggregation(
                unwindOperation,
                sortOperation,
                groupOperation,
                projectionOperation
        ), Company.class, Company.class).getMappedResults();

        logger.info("response from interface >>" + response);
        return response;
    }

}
