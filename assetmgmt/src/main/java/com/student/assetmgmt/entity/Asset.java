package com.student.assetmgmt.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String assetName;

    @Column(nullable = false)
    private String assetType;   // e.g. Laptop, Monitor, Printer

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String status;      // Available, Assigned, Under Maintenance, Retired

    private String assignedTo;  // Employee name (null if not assigned)

    private LocalDate purchaseDate;

    private LocalDate warrantyExpiryDate;

    // ---- Constructors ----

    public Asset() {}

    public Asset(String assetName, String assetType, String serialNumber,
                 String status, String assignedTo,
                 LocalDate purchaseDate, LocalDate warrantyExpiryDate) {
        this.assetName = assetName;
        this.assetType = assetType;
        this.serialNumber = serialNumber;
        this.status = status;
        this.assignedTo = assignedTo;
        this.purchaseDate = purchaseDate;
        this.warrantyExpiryDate = warrantyExpiryDate;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getAssetType() { return assetType; }
    public void setAssetType(String assetType) { this.assetType = assetType; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public LocalDate getWarrantyExpiryDate() { return warrantyExpiryDate; }
    public void setWarrantyExpiryDate(LocalDate warrantyExpiryDate) {
        this.warrantyExpiryDate = warrantyExpiryDate;
    }

    // Helper: is warranty expired?
    public boolean isWarrantyExpired() {
        if (warrantyExpiryDate == null) return false;
        return warrantyExpiryDate.isBefore(LocalDate.now());
    }

    // Helper: days until warranty expires (negative = already expired)
    public long daysUntilWarrantyExpiry() {
        if (warrantyExpiryDate == null) return Long.MAX_VALUE;
        return LocalDate.now().until(warrantyExpiryDate).getDays()
               + LocalDate.now().until(warrantyExpiryDate).getMonths() * 30L
               + LocalDate.now().until(warrantyExpiryDate).getYears() * 365L;
    }
}
