package vehicles.com.roomormcars.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vehicles.com.roomormcars.data.local.dao.VehiclesDao;
import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;
import vehicles.com.roomormcars.data.remote.VehiclesDBService;
import vehicles.com.roomormcars.data.remote.model.VehicleApiResponse;
import retrofit2.Call;


public class VehiclesRepository {

    private final VehiclesDao VehiclesDao;
    private final VehiclesDBService VehiclesDBService;

    @Inject
    public VehiclesRepository(VehiclesDao VehiclesDao, VehiclesDBService VehiclesDBService) {
        this.VehiclesDao = VehiclesDao;
        this.VehiclesDBService = VehiclesDBService;
    }

    public LiveData<Resource<List<VehiclesEntity>>> loadVehicles() {
        return new NetworkBoundResource<List<VehiclesEntity>, VehicleApiResponse>() {

            @NonNull
            @Override
            protected void saveCallResult(@NonNull VehicleApiResponse item) {


                List<VehiclesEntity> vehicleEntities = new ArrayList<VehiclesEntity>();


                VehiclesEntity VehiclesEntity;
                Gson gson;
                if(item!=null) {
                    for (int i = 0; i < item.getPlaceMarksList().size(); i++) {
                        VehicleApiResponse.PlaceMarks vehicle = item.getPlaceMarksList().get(i);
                        VehiclesEntity = new VehiclesEntity();
                        VehiclesEntity.setPosterPath(vehicle.getAddress());
                        VehiclesEntity.setTitle(vehicle.getCarName());
                        VehiclesEntity.setBackdropPath(vehicle.getEngineType());
                        VehiclesEntity.setId(i);
                        VehiclesEntity.setOverview(vehicle.getAddress());
                        gson = new Gson();
                        String jsonCartList = gson.toJson(vehicle.getCoordiatesList());
                        VehiclesEntity.setCoordinatesList(jsonCartList);
                        vehicleEntities.add(VehiclesEntity);
                    }


                    VehiclesDao.saveVehicles(vehicleEntities);
                }
            }

            @NonNull
            @Override
            protected LiveData<List<VehiclesEntity>> loadFromDb() {
                return VehiclesDao.loadVehicles();
            }

            @NonNull
            @Override
            protected Call<VehicleApiResponse> createCall() {
                return VehiclesDBService.loadVehicles();
            }
        }.getAsLiveData();
    }

    public LiveData<VehiclesEntity> getVehicle(int id){
        return VehiclesDao.getVehicle(id);
    }
}
