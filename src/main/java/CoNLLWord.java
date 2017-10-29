public class CoNLLWord {
    int ID;
    String POSTAG;
    CoNLLWord HEAD;
    String NAME, DEPREL;
    int parent;

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public CoNLLWord(int ID, String POSTAG, String NAME, int parent) {
        this.parent = parent;
        this.ID = ID;
        this.POSTAG = POSTAG;
        this.NAME = NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPOSTAG() {
        return POSTAG;
    }

    public void setPOSTAG(String POSTAG) {
        this.POSTAG = POSTAG;
    }

    public CoNLLWord getHEAD() {
        return HEAD;
    }

    public void setHEAD(CoNLLWord HEAD) {
        this.HEAD = HEAD;
    }

    public CoNLLWord(int ID, String POSTAG, CoNLLWord HEAD) {

        this.ID = ID;
        this.POSTAG = POSTAG;
        this.HEAD = HEAD;
    }
}
