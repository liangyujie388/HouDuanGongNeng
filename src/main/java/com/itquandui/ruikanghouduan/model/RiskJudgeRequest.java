package com.itquandui.ruikanghouduan.model;

public class RiskJudgeRequest {
    private String url;
    private String text;

    public RiskJudgeRequest() {
    }

    public RiskJudgeRequest(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
