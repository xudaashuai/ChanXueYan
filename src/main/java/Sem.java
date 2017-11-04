import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Sem {

    @SerializedName("id")
    private Long mId;
    @SerializedName("parent")
    private Long mParent;
    @SerializedName("relate")
    private String mRelate;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getParent() {
        return mParent;
    }

    public void setParent(Long parent) {
        mParent = parent;
    }

    public String getRelate() {
        return mRelate;
    }

    public void setRelate(String relate) {
        mRelate = relate;
    }

}
