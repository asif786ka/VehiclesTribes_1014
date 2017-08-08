package shirts.com.roomormshirts.data.remote;

import shirts.com.roomormshirts.data.remote.model.CartShirtListResponse;
import retrofit2.Call;
import retrofit2.http.GET;


public interface CartShirtDBService {

    // need to update with actual cart service
    @GET("itunes-music/top-songs/50/explicit/json")
    Call<CartShirtListResponse> loadCartShirts();

}
