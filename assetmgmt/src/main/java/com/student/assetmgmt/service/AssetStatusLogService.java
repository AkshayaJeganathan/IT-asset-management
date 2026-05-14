package com.student.assetmgmt.service;

import com.student.assetmgmt.entity.AssetStatusLog;
import com.student.assetmgmt.repository.AssetStatusLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetStatusLogService {

    @Autowired
    private AssetStatusLogRepository logRepository;

    // ---- Feature 5: View Audit Trail ----

    // Get all log entries (most recent first)
    public List<AssetStatusLog> getAllLogs() {
        return logRepository.findAllByOrderByActionTimeDesc();
    }

    // Get logs for a specific asset
    public List<AssetStatusLog> getLogsByAsset(Long assetId) {
        return logRepository.findByAssetIdOrderByActionTimeDesc(assetId);
    }
}