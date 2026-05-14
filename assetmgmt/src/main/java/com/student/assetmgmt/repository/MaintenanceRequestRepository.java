package com.student.assetmgmt.repository;

import com.student.assetmgmt.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {

    // Find all maintenance requests for a specific asset
    List<MaintenanceRequest> findByAssetId(Long assetId);

    // Find by status
    List<MaintenanceRequest> findByRequestStatus(String requestStatus);
}