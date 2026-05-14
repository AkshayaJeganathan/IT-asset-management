package com.student.assetmgmt.controller;

import com.student.assetmgmt.entity.Asset;
import com.student.assetmgmt.entity.MaintenanceRequest;
import com.student.assetmgmt.service.AssetService;
import com.student.assetmgmt.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private AssetService assetService;

    // View all maintenance requests
    @GetMapping
    public String viewAllRequests(Model model) {
        List<MaintenanceRequest> requests = maintenanceService.getAllRequests();
        model.addAttribute("requests", requests);
        model.addAttribute("pageTitle", "Maintenance Requests");
        return "maintenance/list";
    }

    // ---- Feature 4: Show Raise Maintenance Request Form ----
    @GetMapping("/raise/{assetId}")
    public String showRaiseForm(@PathVariable Long assetId, Model model) {
        Asset asset = assetService.getAssetById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        model.addAttribute("asset", asset);
        model.addAttribute("pageTitle", "Raise Maintenance Request");
        return "maintenance/raise";
    }

    // ---- Feature 4: Submit Maintenance Request Form ----
    @PostMapping("/raise/{assetId}")
    public String raiseRequest(@PathVariable Long assetId,
                                @RequestParam String raisedBy,
                                @RequestParam String issueDescription) {
        maintenanceService.raiseRequest(assetId, raisedBy, issueDescription);
        return "redirect:/maintenance";
    }

    // Update request status (e.g. mark as Resolved)
    @PostMapping("/update-status/{id}")
    public String updateStatus(@PathVariable Long id,
                                @RequestParam String newStatus) {
        maintenanceService.updateStatus(id, newStatus);
        return "redirect:/maintenance";
    }
}