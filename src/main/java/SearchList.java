

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
    public ArrayList<Integer> search(Word[] sentence){
        ArrayList<Integer> Words=new ArrayList<Integer>();
        for (int i = 0; i <sentence.length-nodes.size()+1 ; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                SearchNode node = nodes.get(j);
                if (!node.match(sentence[i+j],node.offset==null?null:sentence[i+j+node.offset])){
                    break;
                }
                if (j==nodes.size()-1){
                    Words.add(i);
                }
            }
        }
        return Words;
    }
}
