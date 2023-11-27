package com.marjane.repositories;

import com.marjane.models.GeneralAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralAdminRepository extends JpaRepository<GeneralAdmin, Long> {
 }
