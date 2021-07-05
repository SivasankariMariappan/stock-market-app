package com.org.fse.repository;

import com.org.fse.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String>, StockSearch {

}
