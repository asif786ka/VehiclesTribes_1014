package vehicles.com.roomormcars.util;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import vehicles.com.roomormcars.data.NetworkBoundResource;
import vehicles.com.roomormcars.data.Resource;
import vehicles.com.roomormcars.data.local.dao.VehiclesDao;
import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;
import vehicles.com.roomormcars.data.remote.model.VehicleApiResponse;

public class MockModelsUtil {

    public static List<VehiclesEntity> createMockVehicles() {
        List<VehiclesEntity> vehicleEntities = new ArrayList<VehiclesEntity>();

        VehiclesEntity vehiclesEntity1 = new VehiclesEntity();
        vehiclesEntity1.setId(1);
        vehiclesEntity1.setTitle("HH-GO8522");
        vehiclesEntity1.setOverview("LesserstraÃŸe 170, 22049 Hamburg");
        VehiclesEntity vehiclesEntity2 = new VehiclesEntity();
        vehiclesEntity2.setId(2);
        vehiclesEntity2.setTitle("HH-GO8480");
        vehiclesEntity2.setOverview("Grosse ReichenstraÃŸe 7, 20457 Hamburg");

        vehicleEntities.add(vehiclesEntity1);
        vehicleEntities.add(vehiclesEntity2);

        return vehicleEntities;
    }




}
