package com.student.assetmgmt.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(nullable = false)
    private String raisedBy;        // Person who raised the request

    @Column(nullable = false)
    private String issueDescription;

    @Column(nullable = false)
    private String requestStatus;   // Pending, In Progress, Resolved

    private LocalDate requestDate;

    // ---- Constructors ----

    public MaintenanceRequest() {}

    public MaintenanceRequest(Asset asset, String raisedBy,
                               String issueDescription, String requestStatus,
                               LocalDate requestDate) {
        this.asset = asset;
        this.raisedBy = raisedBy;
        this.issueDescription = issueDescription;
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

    public String getRaisedBy() { return raisedBy; }
    public void setRaisedBy(String raisedBy) { this.raisedBy = raisedBy; }

    public String getIssueDescription() { return issueDescription; }
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getRequestStatus() { return requestStatus; }
    public void setRequestStatus(String requestStatus) { this.requestStatus = requestStatus; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }
}