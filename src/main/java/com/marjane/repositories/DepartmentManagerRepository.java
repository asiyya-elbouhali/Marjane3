package com.marjane.repositories;

import com.marjane.models.DepartmentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, Long> {
 }
