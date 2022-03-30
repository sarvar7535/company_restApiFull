package com.example.company_1.repository;

import com.example.company_1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByCorpName(String corpName);

    boolean existsByCorpNameAndIdNot(String corpName, Integer id);


}
