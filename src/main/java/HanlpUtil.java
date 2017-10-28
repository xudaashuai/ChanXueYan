

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import okhttp3.*;

import java.io.*;
import java.util.ArrayList;

public class HanlpUtil {
    public CoNLLSentence parseSentence(String text) {
        CoNLLSentence sentence = HanLP.parseDependency(text);
        OutputStream f = null;
        try {
            f = new FileOutputStream("1.txt");
            f.write(sentence.toString().getBytes());
            f.flush();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sentence;

    }

    public void parseFile(SearchList rule) throws IOException {
        ArrayList<SearchList> rules=new ArrayList<SearchList>();
        rules.add(rule);
        parseFileWithRules(rules);
    }
    public void parseFileWithRules(ArrayList<SearchList> list) throws IOException {
        String pathname = "comment.txt";
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = null; // 建立一个输入流对象reader

        reader = new InputStreamReader(
                new FileInputStream(filename));

        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
        line = br.readLine();
        int lineno = 0;
        int okno=0;

        System.out.println("一共有 10000 组测试用例 开始测试");
        while (line != null) {
            lineno += 1;
            if(lineno%100==0)
                System.out.println("测试了"+lineno +"组测试用例，"+okno+"组测试用例匹配该规则");
            line = br.readLine(); // 一次读入一行数据
            if (line==null){
                break;
            }
            CoNLLSentence sentence = parseSentence(line);
            for (SearchList rule:list) {
                ArrayList<Integer> words = rule.search(sentence);
                if (words.size()!=0) {
                    okno += 1;
                    for (int index : words) {
                        System.out.print(lineno);
                        for (int i = 0; i < rule.nodes.size(); i++) {
                            System.out.print(" "+sentence.getWordArray()[index+i].NAME+" ");
                        }
                        System.out.println();
                    }
                }
            }
        }
        System.out.println("一共有 10000 组测试用例，"+okno+"组测试用例匹配该规则");
    }

}
