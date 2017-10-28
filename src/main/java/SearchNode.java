import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchNode {
    Integer offset = null;
    Pattern relate;
    SearchNode parent;
    Pattern tag;

    public SearchNode(String cx) {
        this.tag = Pattern.compile('^' + cx + '$');
    }

    public void addConnect(Integer offset, String connection, SearchNode node) {
        this.offset = offset;
        this.relate = Pattern.compile('^' + connection + '$');
        this.parent = node;
    }

    public boolean match(CoNLLWord word,CoNLLWord parent) {
        Matcher matcher = tag.matcher(word.POSTAG);
        if ( !matcher.matches()){
            return false;
        }
        if(parent == null){
            return true;
        }
        if (parent.ID-word.ID!=offset){
            return false;
        }
        if(!relate.matcher(word.DEPREL).matches()){
            return false;
        }
        return true;
    }

}
