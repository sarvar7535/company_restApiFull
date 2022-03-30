package com.example.company_1.repository;

import com.example.company_1.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkerRepository extends JpaRepository<Worker,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    Page<Worker> findWorkersByDepartment_CompanyId(Integer department_company_id, Pageable pageable);


}
