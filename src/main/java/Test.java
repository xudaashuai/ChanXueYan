import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        HanlpUtil hanlpUtil = new HanlpUtil();
        hanlpUtil.parseSentence("这个火锅好吃。");
        // 然后 用 dependencyView 打开 1.txt 就可以看见效果了
    }
}