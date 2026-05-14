package com.student.assetmgmt.repository;

import com.student.assetmgmt.entity.AssetStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetStatusLogRepository extends JpaRepository<AssetStatusLog, Long> {

    // Get all log entries for a specific asset, ordered by most recent first
    List<AssetStatusLog> findByAssetIdOrderByActionTimeDesc(Long assetId);

    // Get all logs (for a full audit trail view)
    List<AssetStatusLog> findAllByOrderByActionTimeDesc();
}