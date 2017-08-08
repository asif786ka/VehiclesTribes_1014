package shirts.com.roomormshirts.data.remote;

import java.util.List;

import shirts.com.roomormshirts.data.remote.model.Shirt;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ShirtsDBService {

    @GET("shirts")
    Call<List<Shirt>> loadShirts();

}
