package com.student.assetmgmt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_status_logs")
public class AssetStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(nullable = false)
    private String action;          // e.g. "Asset Added", "Assigned to John", "Maintenance Raised"

    @Column(nullable = false)
    private String performedBy;     // Who did this action

    @Column(nullable = false)
    private LocalDateTime actionTime;

    private String remarks;         // Optional extra notes

    // ---- Constructors ----

    public AssetStatusLog() {}

    public AssetStatusLog(Asset asset, String action,
                           String performedBy, String remarks) {
        this.asset = asset;
        this.action = action;
        this.performedBy = performedBy;
        this.actionTime = LocalDateTime.now();
        this.remarks = remarks;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }

    public LocalDateTime getActionTime() { return actionTime; }
    public void setActionTime(LocalDateTime actionTime) { this.actionTime = actionTime; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}