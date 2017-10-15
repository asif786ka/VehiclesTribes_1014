package vehicles.com.roomormcars.data.remote;

import vehicles.com.roomormcars.data.remote.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ShirtsDBService {

    /*@GET("shirts")
    Call<List<Shirt>> loadShirts();*/

    @GET("/car2go/vehicles.json")
    Call<ApiResponse> loadShirts();


}
