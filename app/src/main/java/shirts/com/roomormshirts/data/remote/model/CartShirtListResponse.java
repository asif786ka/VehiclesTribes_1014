package shirts.com.roomormshirts.data.remote.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CartShirtListResponse {

    @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
