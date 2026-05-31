package com.itquandui.ruikanghouduan.Service;

import com.itquandui.ruikanghouduan.Repository.ReportRepository;
import com.itquandui.ruikanghouduan.model.ReportItem;
import com.itquandui.ruikanghouduan.model.ReportRequest;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final AtomicLong sequence = new AtomicLong(1);

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ReportItem createReport(ReportRequest request) {
        if (request == null || request.getEvidence() == null || request.getEvidence().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "请填写证据内容（链接/账号/群号/聊天记录等）");
        }
        String type = request.getType();
        if (type == null || type.trim().isEmpty()) {
            type = "unknown";
        }
        ReportItem item = new ReportItem(
                sequence.getAndIncrement(),
                type.trim(),
                request.getEvidence().trim(),
                Instant.now()
        );
        return reportRepository.save(item);
    }

    public List<ReportItem> listReports() {
        return reportRepository.findAll();
    }
}
