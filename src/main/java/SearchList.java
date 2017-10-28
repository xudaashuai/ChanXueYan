import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

import java.util.ArrayList;

public class SearchList {
    ArrayList<SearchNode> nodes=new ArrayList<SearchNode>();
    public SearchList(){}
    public void addNode(SearchNode node){
        nodes.add(node);
    }
    public void addRelate(int index,int offset,String relate){
        nodes.get(index).addConnect(offset,relate,nodes.get(index+offset));
    }
    public ArrayList<Integer> search(CoNLLSentence sentence){
        ArrayList<Integer> coNLLWords=new ArrayList<Integer>();
        for (int i = 0; i <sentence.getWordArray().length-nodes.size()+1 ; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                SearchNode node = nodes.get(j);
                if (!node.match(sentence.getWordArray()[i+j],node.offset==null?null:sentence.getWordArray()[i+j+node.offset])){
                    break;
                }
                if (j==nodes.size()-1){
                    coNLLWords.add(i);
                }
            }
        }
        return coNLLWords;
    }
}
