package com.itquandui.ruikanghouduan.Service;

import com.itquandui.ruikanghouduan.model.Scenario;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {
    private final List<Scenario> scenarios = List.of(
            new Scenario(
                    "task-rebate",
                    "刷单返利",
                    "小额返利诱导大额垫付，借口“解冻/保证金”。",
                    "【剧情】你在群里看到“轻松兼职日赚300”，对方先让你做小任务返你20元，随后要求你垫付更大金额才能“解冻返利”。\n" +
                            "对方话术：“这是系统流程，不做就会影响信誉分。”\n" +
                            "目标：识别“先小利诱导大额投入/解冻费/保证金”等风险点。"
            ),
            new Scenario(
                    "game-trade",
                    "游戏交易",
                    "低价皮肤/账号，诱导跳转外链或仿冒担保平台。",
                    "【剧情】你在二手平台看到低价皮肤，对方让你加QQ并发来“担保交易链接”，要求你在外站付款。\n" +
                            "对方话术：“平台手续费太高，走链接更安全。”\n" +
                            "目标：识别“跳转外部链接/诱导私下交易/仿冒担保平台”等风险点。"
            ),
            new Scenario(
                    "police-impersonation",
                    "冒充公检法",
                    "恐吓涉案，要求保密、屏幕共享、转入安全账户。",
                    "【剧情】电话自称“公安/检察院”，称你涉嫌洗钱，要求你下载会议软件并屏幕共享，随后让你把钱转入“安全账户”。\n" +
                            "对方话术：“这是保密案件，不能告诉家人。”\n" +
                            "目标：识别“安全账户/恐吓威胁/要求保密/屏幕共享”等风险点。"
            )
    );

    public List<Scenario> getScenarios() {
        return scenarios;
    }
}
