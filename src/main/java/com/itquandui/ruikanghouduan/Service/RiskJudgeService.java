package com.itquandui.ruikanghouduan.Service;

import com.itquandui.ruikanghouduan.model.RiskJudgeRequest;
import com.itquandui.ruikanghouduan.model.RiskJudgeResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class RiskJudgeService {
    private static final Pattern POLICE_PATTERN = Pattern.compile("安全账户|转入.*账户|涉案|洗钱|保密案件|公检法");
    private static final Pattern TASK_PATTERN = Pattern.compile("刷单|返利|垫付|解冻|保证金|任务单");
    private static final Pattern URGENT_PATTERN = Pattern.compile("验证码|不要告诉别人|10分钟|立即|否则影响征信|冻结");
    private static final Pattern SCREEN_PATTERN = Pattern.compile("屏幕共享|远程协助|会议软件");
    private static final Pattern LINK_PATTERN = Pattern.compile("(http|https)://\\S+", Pattern.CASE_INSENSITIVE);
    private static final Pattern SHORT_LINK_PATTERN = Pattern.compile("t\\.cn|bit\\.ly|tinyurl|dwz|short", Pattern.CASE_INSENSITIVE);
    private static final Pattern SENSITIVE_WORD_PATTERN = Pattern.compile("verify|login|secure|bank|pay", Pattern.CASE_INSENSITIVE);

    private final List<RiskRule> rules = List.of(
            new RiskRule(35, "疑似“冒充公检法/安全账户”话术", (u, t) -> POLICE_PATTERN.matcher(t).find()),
            new RiskRule(25, "疑似“刷单返利/垫付解冻”话术", (u, t) -> TASK_PATTERN.matcher(t).find()),
            new RiskRule(20, "存在“紧迫威胁/验证码”诱导特征", (u, t) -> URGENT_PATTERN.matcher(t).find()),
            new RiskRule(20, "存在“屏幕共享/远程控制”风险", (u, t) -> SCREEN_PATTERN.matcher(t).find()),
            new RiskRule(15, "包含外部链接，需核验来源与域名", (u, t) -> LINK_PATTERN.matcher(t).find() || LINK_PATTERN.matcher(u).find()),
            new RiskRule(15, "疑似短链，可能隐藏真实跳转", (u, t) -> SHORT_LINK_PATTERN.matcher(u).find()),
            new RiskRule(10, "URL 含敏感诱导词（verify/login/bank/pay）", (u, t) -> SENSITIVE_WORD_PATTERN.matcher(u).find())
    );

    public RiskJudgeResult judge(RiskJudgeRequest request) {
        String url = request == null || request.getUrl() == null ? "" : request.getUrl().trim();
        String text = request == null || request.getText() == null ? "" : request.getText().trim();

        int total = 0;
        List<String> hits = new ArrayList<>();

        for (RiskRule rule : rules) {
            if (rule.matcher.test(url, text)) {
                total += rule.score;
                hits.add(rule.message);
            }
        }

        RiskLevel level = RiskLevel.fromScore(total);
        if (hits.isEmpty()) {
            hits.add("未命中明显规则（建议仍进行官方渠道核验）");
        }

        return new RiskJudgeResult(level.label, total, hits, buildAdvice(level));
    }

    private String buildAdvice(RiskLevel level) {
        String base = "建议：不要点击链接/不要转账/不要透露验证码；通过官方 App 或官方客服电话自行核验；保存证据（截图、链接、账号）并及时举报。";
        return switch (level) {
            case HIGH -> "风险较高。" + base + "\n如已输入验证码/转账/安装软件：立即联系银行/平台止付，修改密码，关闭免密，必要时报警。";
            case MID -> "存在明显可疑点。" + base + "\n建议把完整对话与链接发给 AI 进一步分析诈骗特征与应对步骤。";
            default -> "当前未发现强特征，但仍建议谨慎核验来源。" + base;
        };
    }

    private record RiskRule(int score, String message, BiPredicate<String, String> matcher) {
    }

    private enum RiskLevel {
        HIGH("HIGH"),
        MID("MID"),
        LOW("LOW");

        private final String label;

        RiskLevel(String label) {
            this.label = label;
        }

        private static RiskLevel fromScore(int score) {
            if (score >= 55) {
                return HIGH;
            }
            if (score >= 30) {
                return MID;
            }
            return LOW;
        }
    }
}
