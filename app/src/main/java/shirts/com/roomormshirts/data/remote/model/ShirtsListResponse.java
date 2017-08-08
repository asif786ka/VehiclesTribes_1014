package shirts.com.roomormshirts.data.remote.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ShirtsListResponse {

  /*  @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }*/


    public List<Shirt> getShirtList() {
        return shirtList;
    }

    public void setShirtList(List<Shirt> shirtList) {
        this.shirtList = shirtList;
    }

    @SerializedName("")
    @Expose
    private List<Shirt> shirtList;
}
