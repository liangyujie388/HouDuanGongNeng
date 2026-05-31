package com.itquandui.ruikanghouduan.Service;

import com.itquandui.ruikanghouduan.model.KnowledgeEntry;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeBaseService {
    private final List<KnowledgeEntry> entries = List.of(
            new KnowledgeEntry("刷单返利有什么典型特征？", "先小额返利获取信任，再诱导大额垫付；常见借口：解冻、保证金、刷流水。"),
            new KnowledgeEntry("什么是“安全账户”？", "公检法不会要求转入所谓安全账户；这是典型诈骗话术。"),
            new KnowledgeEntry("验证码可以给对方吗？", "任何验证码都不要透露；验证码=账户操作授权。"),
            new KnowledgeEntry("收到可疑链接怎么办？", "不点击；通过官方 App/官网手动输入地址核验；保存证据并举报。")
    );

    public List<KnowledgeEntry> search(String keyword) {
        String query = keyword == null ? "" : keyword.trim();
        if (query.isEmpty()) {
            return entries;
        }
        return entries.stream()
                .filter(entry -> (entry.getQuestion() + entry.getAnswer()).contains(query))
                .collect(Collectors.toList());
    }
}
