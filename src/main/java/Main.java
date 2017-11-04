import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // 封装的 hanlp 工具类
        LTPUtil LTPUtil = new LTPUtil();

        // 使用 SearchList 构建规则
        // SearchNode 需要一个参数 为 该词的词性 可以使用正则
        // 这个 rule 就是 火锅 超级 好吃的 句法 rule
        ArrayList<SearchList> rules = new ArrayList<SearchList>();
        SearchList rule = new SearchList();
        SearchNode node1 = new SearchNode("n.?");
        SearchNode node2 = new SearchNode("b");
        SearchNode node3 = new SearchNode("a");
        rule.addNode(node1);
        rule.addNode(node2);
        rule.addNode(node3);
        // 添加关联规则 index 表示第几个 节点 offset 表示父节点 偏移 ，relate 表示和父节点的关系 可以使用正则
        rule.addRelate(0, 2, "SBV");
        rule.addRelate(1, 1, "ADV");
        rules.add(rule);
        // 构建 rule 完成

        // 这个 rule2 是 火锅 好吃 的 rule
        SearchList rule2 = new SearchList();
        rule2.addNode(new SearchNode("n.?"));
        rule2.addNode(new SearchNode("a"));
        // 添加关联规则 index 表示第几个 节点 offset 表示父节点 偏移 ，relate 表示和父节点的关系 可以使用正则
        rule2.addRelate(0, 1, "SBV");
        // 使用 文件测试 规则
        // 会有控制台输出
        rules.add(rule2);
        LTPUtil.testRulesWithDatabase(rules);
    }
}

