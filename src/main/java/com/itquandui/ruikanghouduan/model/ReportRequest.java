package com.itquandui.ruikanghouduan.model;

public class ReportRequest {
    private String type;
    private String evidence;

    public ReportRequest() {
    }

    public ReportRequest(String type, String evidence) {
        this.type = type;
        this.evidence = evidence;
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
}
