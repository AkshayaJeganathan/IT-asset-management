package com.student.assetmgmt.service;

import com.student.assetmgmt.entity.Asset;
import com.student.assetmgmt.entity.AssetStatusLog;
import com.student.assetmgmt.entity.MaintenanceRequest;
import com.student.assetmgmt.repository.AssetRepository;
import com.student.assetmgmt.repository.AssetStatusLogRepository;
import com.student.assetmgmt.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRequestRepository maintenanceRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetStatusLogRepository logRepository;

    // ---- Feature 4: Raise Maintenance Request ----
    public MaintenanceRequest raiseRequest(Long assetId, String raisedBy, String issueDescription) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + assetId));

        // Create the maintenance request
        MaintenanceRequest request = new MaintenanceRequest(
                asset,
                raisedBy,
                issueDescription,
                "Pending",          // Default status
                LocalDate.now()
        );
        MaintenanceRequest saved = maintenanceRepository.save(request);

        // Update asset status to "Under Maintenance"
        asset.setStatus("Under Maintenance");
        assetRepository.save(asset);

        // Write to audit log
        AssetStatusLog log = new AssetStatusLog(
                asset,
                "Maintenance Request Raised",
                raisedBy,
                "Issue: " + issueDescription
        );
        logRepository.save(log);

        return saved;
    }

    // Get all maintenance requests
    public List<MaintenanceRequest> getAllRequests() {
        return maintenanceRepository.findAll();
    }

    // Get maintenance requests for one asset
    public List<MaintenanceRequest> getRequestsByAsset(Long assetId) {
        return maintenanceRepository.findByAssetId(assetId);
    }

    // Update request status (e.g. mark as Resolved)
    public MaintenanceRequest updateStatus(Long requestId, String newStatus) {
        MaintenanceRequest request = maintenanceRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with ID: " + requestId));

        request.setRequestStatus(newStatus);
        MaintenanceRequest saved = maintenanceRepository.save(request);

        // If resolved, set asset back to Available
        if ("Resolved".equals(newStatus)) {
            Asset asset = request.getAsset();
            asset.setStatus("Available");
            assetRepository.save(asset);

            // Write to audit log
            AssetStatusLog log = new AssetStatusLog(
                    asset,
                    "Maintenance Resolved",
                    "Admin",
                    "Request #" + requestId + " marked as Resolved"
            );
            logRepository.save(log);
        }

        return saved;
    }
}