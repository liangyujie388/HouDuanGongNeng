package com.itquandui.ruikanghouduan.model;

import java.util.List;

public class RiskJudgeResult {
    private String level;
    private int score;
    private List<String> signals;
    private String advice;

    public RiskJudgeResult() {
    }

    public RiskJudgeResult(String level, int score, List<String> signals, String advice) {
        this.level = level;
        this.score = score;
        this.signals = signals;
        this.advice = advice;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getSignals() {
        return signals;
    }

    public void setSignals(List<String> signals) {
        this.signals = signals;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
