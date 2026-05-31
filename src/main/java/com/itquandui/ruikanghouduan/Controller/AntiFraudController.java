package com.itquandui.ruikanghouduan.Controller;

import com.itquandui.ruikanghouduan.Service.KnowledgeBaseService;
import com.itquandui.ruikanghouduan.Service.ReportService;
import com.itquandui.ruikanghouduan.Service.RiskJudgeService;
import com.itquandui.ruikanghouduan.Service.ScenarioService;
import com.itquandui.ruikanghouduan.model.KnowledgeEntry;
import com.itquandui.ruikanghouduan.model.ReportItem;
import com.itquandui.ruikanghouduan.model.ReportRequest;
import com.itquandui.ruikanghouduan.model.RiskJudgeRequest;
import com.itquandui.ruikanghouduan.model.RiskJudgeResult;
import com.itquandui.ruikanghouduan.model.Scenario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anti-fraud")
public class AntiFraudController {
    private final ScenarioService scenarioService;
    private final RiskJudgeService riskJudgeService;
    private final ReportService reportService;
    private final KnowledgeBaseService knowledgeBaseService;

    @Autowired
    public AntiFraudController(
            ScenarioService scenarioService,
            RiskJudgeService riskJudgeService,
            ReportService reportService,
            KnowledgeBaseService knowledgeBaseService
    ) {
        this.scenarioService = scenarioService;
        this.riskJudgeService = riskJudgeService;
        this.reportService = reportService;
        this.knowledgeBaseService = knowledgeBaseService;
    }

    @GetMapping("/scenarios")
    public List<Scenario> scenarios() {
        return scenarioService.getScenarios();
    }

    @PostMapping("/judge")
    public RiskJudgeResult judge(@RequestBody RiskJudgeRequest request) {
        return riskJudgeService.judge(request);
    }

    @PostMapping("/reports")
    public ReportItem submitReport(@RequestBody ReportRequest request) {
        return reportService.createReport(request);
    }

    @GetMapping("/reports")
    public List<ReportItem> listReports() {
        return reportService.listReports();
    }

    @GetMapping("/knowledge")
    public List<KnowledgeEntry> knowledge(@RequestParam(name = "q", required = false) String keyword) {
        return knowledgeBaseService.search(keyword);
    }
}
