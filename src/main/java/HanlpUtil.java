

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hankcs.hanlp.HanLP;
import okhttp3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class HanlpUtil {
    String apiKey = "z1L4K6O9L74CBdFDLxsywSxSjLmwTfSxhdYqTgjc";

    public void parseSentence(String text) {
        String url = "http://api.ltp-cloud.com/analysis/?api_key=%s&pattern=all&text=%s&format=conll";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(url, apiKey, text))
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String content = response.body().string();
            System.out.println(content);
            OutputStream f = null;
            f = new FileOutputStream("1.txt");
            f.write(content.getBytes());
            f.flush();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray parse(String text) {
        String url = "http://api.ltp-cloud.com/analysis/?api_key=%s&pattern=all&text=%s&format=json";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(url, apiKey, text))
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String content = response.body().string();
            // System.out.println(content);
            JSONArray json = JSON.parseArray(content);
            return json.getJSONArray(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Word[] convert(JSONArray array) {
        Word[] words = new Word[array.size()];
        for (int i = 0; i < array.size(); i++) {
            words[i] = convert(array.get(i));
        }
        for (int i = 0; i < array.size(); i++) {
            // words[i].setHEAD(words[words[i].parent]);
        }
        return words;
    }

    Word convert(Object object) {
        Word word = JSON.parseObject(JSON.toJSONString(object), Word.class);
        return word;
    }

    public void parseFile(SearchList rule) throws IOException {
        ArrayList<SearchList> rules = new ArrayList<SearchList>();
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
        int okno = 0;

        System.out.println("一共有 10000 组测试用例 开始测试");
        while (line != null) {
            lineno += 1;
            if (lineno % 100 == 0)
                System.out.println("测试了" + lineno + "组测试用例，" + okno + "组测试用例匹配该规则");
            line = br.readLine(); // 一次读入一行数据
            if (line == null) {
                break;
            }
            JSONArray sentences = parse(line);
            for (SearchList rule : list) {
                for (int i = 0; i < sentences.size(); i++) {
                    Word[] sentence = convert(sentences.getJSONArray(i));
                    ArrayList<Integer> words = rule.search(sentence);
                    if (words.size() != 0) {
                        okno += 1;
                        for (int index : words) {
                            System.out.print(lineno);
                            for (int j = 0; j < rule.nodes.size(); j++) {
                                System.out.print(" " + sentence[index + j].mCont + " ");
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
        System.out.println("一共有 10000 组测试用例，" + okno + "组测试用例匹配该规则");
    }

}
