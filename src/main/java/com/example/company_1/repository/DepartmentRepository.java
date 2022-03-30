package com.example.company_1.repository;

import com.example.company_1.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    boolean existsByNameAndCompanyId(String name, Integer company_id);

    boolean existsByNameAndCompanyIdAndIdNot(String name, Integer company_id, Integer id);

    Page<Department> findDepartmentsByCompanyId(Integer company_id, Pageable pageable);
}
