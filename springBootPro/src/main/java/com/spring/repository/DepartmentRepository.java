package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
