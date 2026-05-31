package com.itquandui.ruikanghouduan.Repository;

import com.itquandui.ruikanghouduan.model.ReportItem;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepository {
    private final ConcurrentLinkedDeque<ReportItem> reports = new ConcurrentLinkedDeque<>();

    public ReportItem save(ReportItem item) {
        reports.addFirst(item);
        return item;
    }

    public List<ReportItem> findAll() {
        return new ArrayList<>(reports);
    }
}
