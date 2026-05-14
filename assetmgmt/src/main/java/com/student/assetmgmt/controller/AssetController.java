package com.student.assetmgmt.controller;

import com.student.assetmgmt.entity.Asset;
import com.student.assetmgmt.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    // ---- Feature 2: View All Assets ----
    @GetMapping
    public String viewAllAssets(Model model) {
        List<Asset> assets = assetService.getAllAssets();
        model.addAttribute("assets", assets);
        model.addAttribute("pageTitle", "All Assets");
        return "assets/list";
    }

    // ---- Feature 1: Show Add Asset Form ----
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("asset", new Asset());
        model.addAttribute("pageTitle", "Add New Asset");
        return "assets/add";
    }

    // ---- Feature 1: Submit Add Asset Form ----
    @PostMapping("/add")
    public String addAsset(@ModelAttribute Asset asset) {
        assetService.addAsset(asset);
        return "redirect:/assets";
    }

    // ---- Feature 3: Show Assign Form ----
    @GetMapping("/assign/{id}")
    public String showAssignForm(@PathVariable Long id, Model model) {
        Asset asset = assetService.getAssetById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        model.addAttribute("asset", asset);
        model.addAttribute("pageTitle", "Assign Asset");
        return "assets/assign";
    }

    // ---- Feature 3: Submit Assign Form ----
    @PostMapping("/assign/{id}")
    public String assignAsset(@PathVariable Long id,
                               @RequestParam String employeeName) {
        assetService.assignAsset(id, employeeName);
        return "redirect:/assets";
    }

    // Unassign asset
    @GetMapping("/unassign/{id}")
    public String unassignAsset(@PathVariable Long id) {
        assetService.unassignAsset(id);
        return "redirect:/assets";
    }

    // ---- Feature 6: Warranty Expiry Check ----
    @GetMapping("/warranty")
    public String warrantyCheck(Model model) {
        List<Asset> expiringAssets = assetService.getExpiredOrExpiringSoonAssets();
        model.addAttribute("assets", expiringAssets);
        model.addAttribute("pageTitle", "Warranty Expiry Check");
        return "assets/warranty";
    }

    // View single asset detail
    @GetMapping("/view/{id}")
    public String viewAsset(@PathVariable Long id, Model model) {
        Asset asset = assetService.getAssetById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        model.addAttribute("asset", asset);
        model.addAttribute("pageTitle", "Asset Details");
        return "assets/view";
    }
}