package com.itquandui.ruikanghouduan.model;

import java.time.Instant;

public class ReportItem {
    private long id;
    private String type;
    private String evidence;
    private Instant time;

    public ReportItem() {
    }

    public ReportItem(long id, String type, String evidence, Instant time) {
        this.id = id;
        this.type = type;
        this.evidence = evidence;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
