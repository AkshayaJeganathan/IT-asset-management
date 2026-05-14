package com.student.assetmgmt.controller;

import com.student.assetmgmt.entity.AssetStatusLog;
import com.student.assetmgmt.service.AssetStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/logs")
public class AssetStatusLogController {

    @Autowired
    private AssetStatusLogService logService;

    // ---- Feature 5: View Full Audit Trail ----
    @GetMapping
    public String viewAllLogs(Model model) {
        List<AssetStatusLog> logs = logService.getAllLogs();
        model.addAttribute("logs", logs);
        model.addAttribute("pageTitle", "Asset Status Audit Trail");
        return "logs/list";
    }

    // View logs for a specific asset
    @GetMapping("/asset/{assetId}")
    public String viewLogsForAsset(@PathVariable Long assetId, Model model) {
        List<AssetStatusLog> logs = logService.getLogsByAsset(assetId);
        model.addAttribute("logs", logs);
        model.addAttribute("assetId", assetId);
        model.addAttribute("pageTitle", "Logs for Asset #" + assetId);
        return "logs/list";
    }
}