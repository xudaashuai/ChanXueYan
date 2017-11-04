import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Word {

    @SerializedName("arg")
    public List<Object> mArg;
    @SerializedName("cont")
    public String mCont;
    @SerializedName("id")
    public Long mId;
    @SerializedName("ne")
    public String mNe;
    @SerializedName("parent")
    public Long mParent;
    @SerializedName("pos")
    public String mPos;
    @SerializedName("relate")
    public String mRelate;
    @SerializedName("sem")
    public List<Sem> mSem;
    @SerializedName("semparent")
    public Long mSemparent;
    @SerializedName("semrelate")
    public String mSemrelate;

    public List<Object> getArg() {
        return mArg;
    }

    public void setArg(List<Object> arg) {
        mArg = arg;
    }

    public String getCont() {
        return mCont;
    }

    public void setCont(String cont) {
        mCont = cont;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getNe() {
        return mNe;
    }

    public void setNe(String ne) {
        mNe = ne;
    }

    public Long getParent() {
        return mParent;
    }

    public void setParent(Long parent) {
        mParent = parent;
    }

    public String getPos() {
        return mPos;
    }

    public void setPos(String pos) {
        mPos = pos;
    }

    public String getRelate() {
        return mRelate;
    }

    public void setRelate(String relate) {
        mRelate = relate;
    }

    public List<Sem> getSem() {
        return mSem;
    }

    public void setSem(List<Sem> sem) {
        mSem = sem;
    }

    public Long getSemparent() {
        return mSemparent;
    }

    public void setSemparent(Long semparent) {
        mSemparent = semparent;
    }

    public String getSemrelate() {
        return mSemrelate;
    }

    public void setSemrelate(String semrelate) {
        mSemrelate = semrelate;
    }

}
