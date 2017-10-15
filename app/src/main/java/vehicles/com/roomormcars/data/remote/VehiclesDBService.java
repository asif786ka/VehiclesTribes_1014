package vehicles.com.roomormcars.data.remote;

import vehicles.com.roomormcars.data.remote.model.VehicleApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;


public interface VehiclesDBService {

    @GET("coding-challenge/locations.json")
    Call<VehicleApiResponse> loadVehicles();


}
