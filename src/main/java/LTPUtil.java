

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.bson.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LTPUtil {
    private String apiKey = "z1L4K6O9L74CBdFDLxsywSxSjLmwTfSxhdYqTgjc";
    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;

    public LTPUtil() {
        try {
            mongoClient = new MongoClient("localhost", 27017);
            mongoDatabase = mongoClient.getDatabase("meituan");
            collection = mongoDatabase.getCollection("comments");

            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    void parseSentence(String text) {
        try {
            OutputStream f = null;
            f = new FileOutputStream("1.txt");
            f.write(getLTPResultByNetwork(text, "conll").getBytes());
            f.flush();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getLTPResultByNetwork(String text, String type) {
        String url = "http://api.ltp-cloud.com/analysis/?api_key=%s&pattern=all&text=%s&format=" + type;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(url, apiKey, text))
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String content = response.body().string();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    JSONArray parse(String text) {
        JSONArray json = JSON.parseArray(getLTPResultByNetwork(text,"json"));
        return json.getJSONArray(0);
    }

    private Word[] convert(JSONArray array) {
        Word[] words = new Word[array.size()];
        for (int i = 0; i < array.size(); i++) {
            words[i] = JSON.parseObject(JSON.toJSONString(array.get(i)), Word.class);
        }
        for (int i = 0; i < array.size(); i++) {
            // words[i].setHEAD(words[words[i].parent]);
        }
        return words;
    }

    public void testRulesWithNetwork(List<SearchList> list) throws IOException {
        String pathname = "comment.txt";
        File filename = new File(pathname);
        InputStreamReader reader = null;

        reader = new InputStreamReader(
                new FileInputStream(filename));

        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        int lineno = 0;
        int okno = 0;

        System.out.println("一共有 10000 组测试用例 开始测试");
        while (line != null) {
            lineno += 1;
            if (lineno % 10 == 0)
                System.out.println("测试了" + lineno + "组测试用例，" + okno + "组测试用例匹配该规则");
            line = br.readLine(); // 一次读入一行数据
            if (line == null) {
                break;
            }
            JSONArray sentences = parse(line);
            if (sentences == null) {
                continue;
            }
            okno = search(list, lineno, okno, sentences);
        }
        System.out.println("一共有" + lineno + " 组测试用例，" + okno + "组测试用例匹配该规则");
    }

    public int search(List<SearchList> list, int lineno, int okno, JSONArray sentences) {
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
        return okno;
    }

    public void testRulesWithDatabase(List<SearchList> list) throws IOException {

        int lineno = 0;
        int okno = 0;
        String line;
        FindIterable<Document> findIterable = collection.find(new BasicDBObject("text", new BasicDBObject("$ne", null)));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        System.out.println("一共有 10000 组测试用例 开始测试");
        while (mongoCursor.hasNext()) {
            lineno += 1;
            if (lineno % 10000 == 0)
                System.out.println("测试了" + lineno + "组测试用例，" + okno + "组测试用例匹配该规则");
            line = (String) mongoCursor.next().get("ltp_result");// 一次读入一行数据
            if (line == null) {
                break;
            }
            JSONArray sentences = null;
            try {
                sentences = JSONArray.parseArray(line).getJSONArray(0);
            } catch (Exception e) {
                continue;
            }
            if (sentences == null) {
                continue;
            }
            okno = search(list, lineno, okno, sentences);
        }
        System.out.println("一共有 " + lineno + " 组测试用例，" + okno + "组测试用例匹配该规则");
    }
}
