import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        LTPUtil LTPUtil = new LTPUtil();
        LTPUtil.parseSentence("这个火锅好吃。");
        // 然后 用 dependencyView 打开 1.txt 就可以看见效果了
    }
}