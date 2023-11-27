package com.marjane.repositories;

import com.marjane.models.CenterAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterAdminRepository extends JpaRepository<CenterAdmin, Long> {
 }

