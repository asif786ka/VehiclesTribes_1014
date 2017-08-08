package shirts.com.roomormshirts.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import shirts.com.roomormshirts.data.local.dao.ShirtsDao;
import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;
import shirts.com.roomormshirts.data.remote.ShirtsDBService;
import shirts.com.roomormshirts.data.remote.model.Shirt;
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
        return new NetworkBoundResource<List<ShirtsEntity>, List<Shirt>>() {

            @NonNull
            @Override
            protected void saveCallResult(@NonNull List<Shirt> item) {


                List<ShirtsEntity> songEntities = new ArrayList<ShirtsEntity>();

                ShirtsEntity ShirtsEntity;
                if(item!=null) {
                    for (int i = 0; i < item.size(); i++) {
                        Shirt shirt = item.get(i);
                        ShirtsEntity = new ShirtsEntity();
                        ShirtsEntity.setPosterPath(shirt.getPicture());
                        ShirtsEntity.setTitle(shirt.getName());
                        ShirtsEntity.setBackdropPath(shirt.getPicture());
                        ShirtsEntity.setId(shirt.getId());
                        ShirtsEntity.setOverview(shirt.getColour());
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
            protected Call<List<Shirt>> createCall() {
                return ShirtsDBService.loadShirts();
            }
        }.getAsLiveData();
    }

    public LiveData<ShirtsEntity> getShirt(int id){
        return ShirtsDao.getShirt(id);
    }
}
