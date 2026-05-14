package com.student.assetmgmt.repository;

import com.student.assetmgmt.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    // Find all assets by status (e.g. "Available", "Assigned")
    List<Asset> findByStatus(String status);

    // Find assets whose warranty expires before a given date
    List<Asset> findByWarrantyExpiryDateBefore(LocalDate date);

    // Find assets assigned to a specific employee
    List<Asset> findByAssignedTo(String assignedTo);
}