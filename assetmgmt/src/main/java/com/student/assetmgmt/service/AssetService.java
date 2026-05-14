package com.student.assetmgmt.service;

import com.student.assetmgmt.entity.Asset;
import com.student.assetmgmt.entity.AssetStatusLog;
import com.student.assetmgmt.repository.AssetRepository;
import com.student.assetmgmt.repository.AssetStatusLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetStatusLogRepository logRepository;

    // ---- Feature 1: Add New Asset ----
    public Asset addAsset(Asset asset) {
        asset.setStatus("Available");   // New asset is always Available first
        Asset saved = assetRepository.save(asset);

        // Write to audit log
        AssetStatusLog log = new AssetStatusLog(
                saved,
                "Asset Added",
                "Admin",
                "New asset registered: " + saved.getAssetName()
        );
        logRepository.save(log);

        return saved;
    }

    // ---- Feature 2: View All Assets ----
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    // Get one asset by ID
    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    // ---- Feature 3: Assign Asset to Employee ----
    public Asset assignAsset(Long assetId, String employeeName) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + assetId));

        asset.setAssignedTo(employeeName);
        asset.setStatus("Assigned");
        Asset saved = assetRepository.save(asset);

        // Write to audit log
        AssetStatusLog log = new AssetStatusLog(
                saved,
                "Asset Assigned",
                "Admin",
                "Assigned to employee: " + employeeName
        );
        logRepository.save(log);

        return saved;
    }

    // Unassign an asset (make it Available again)
    public Asset unassignAsset(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + assetId));

        String prevEmployee = asset.getAssignedTo();
        asset.setAssignedTo(null);
        asset.setStatus("Available");
        Asset saved = assetRepository.save(asset);

        // Write to audit log
        AssetStatusLog log = new AssetStatusLog(
                saved,
                "Asset Unassigned",
                "Admin",
                "Returned from employee: " + prevEmployee
        );
        logRepository.save(log);

        return saved;
    }

    // ---- Feature 6: Warranty Expiry Check ----
    // Returns assets whose warranty expires within the next 30 days (or already expired)
    public List<Asset> getExpiredOrExpiringSoonAssets() {
        LocalDate thirtyDaysFromNow = LocalDate.now().plusDays(30);
        return assetRepository.findByWarrantyExpiryDateBefore(thirtyDaysFromNow);
    }

    // Save asset (used for updates)
    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    // Delete asset
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}