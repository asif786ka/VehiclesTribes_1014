package vehicles.com.roomormcars.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vehicles.com.roomormcars.data.local.dao.ShirtsDao;
import vehicles.com.roomormcars.data.local.entity.ShirtsEntity;
import vehicles.com.roomormcars.data.remote.ShirtsDBService;
import vehicles.com.roomormcars.data.remote.model.ApiResponse;
import retrofit2.Call;


public class ShirtsRepository {

    private final ShirtsDao ShirtsDao;
    private final ShirtsDBService ShirtsDBService;

    @Inject
    public ShirtsRepository(ShirtsDao ShirtsDao, ShirtsDBService ShirtsDBService) {
        this.ShirtsDao = ShirtsDao;
        this.ShirtsDBService = ShirtsDBService;
    }

    public LiveData<Resource<List<ShirtsEntity>>> loadShirts() {
        return new NetworkBoundResource<List<ShirtsEntity>, ApiResponse>() {

            @NonNull
            @Override
            protected void saveCallResult(@NonNull ApiResponse item) {


                List<ShirtsEntity> songEntities = new ArrayList<ShirtsEntity>();

                //List<ApiResponse.PlaceMarks> songEntities = new ArrayList<ApiResponse.PlaceMarks>();

                ShirtsEntity ShirtsEntity;
                Gson gson;
                if(item!=null) {
                    for (int i = 0; i < item.getPlaceMarksList().size(); i++) {
                        ApiResponse.PlaceMarks shirt = item.getPlaceMarksList().get(i);
                        ShirtsEntity = new ShirtsEntity();
                        ShirtsEntity.setPosterPath(shirt.getAddress());
                        ShirtsEntity.setTitle(shirt.getCarName());
                        ShirtsEntity.setBackdropPath(shirt.getEngineType());
                        ShirtsEntity.setId(i);
                        ShirtsEntity.setOverview(shirt.getAddress());
                        // create a new Gson instance
                        gson = new Gson();
                        // convert your list to json
                        String jsonCartList = gson.toJson(shirt.getCoordiatesList());
                        // print your generated json
                        System.out.println("jsonCartList: " + jsonCartList);
                        ShirtsEntity.setCoordinatesList(jsonCartList);
                        songEntities.add(ShirtsEntity);
                    }


                    ShirtsDao.saveShirts(songEntities);
                }
            }

            @NonNull
            @Override
            protected LiveData<List<ShirtsEntity>> loadFromDb() {
                return ShirtsDao.loadShirts();
            }

            @NonNull
            @Override
            protected Call<ApiResponse> createCall() {
                return ShirtsDBService.loadShirts();
            }
        }.getAsLiveData();
    }

    public LiveData<ShirtsEntity> getShirt(int id){
        return ShirtsDao.getShirt(id);
    }
}
