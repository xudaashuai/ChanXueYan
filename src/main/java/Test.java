import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        HanlpUtil hanlpUtil = new HanlpUtil();
        hanlpUtil.parseSentence("这里写你想要解析的句子");
        // 然后 用 dependencyView 打开 1.txt 就可以看见效果了
    }
}