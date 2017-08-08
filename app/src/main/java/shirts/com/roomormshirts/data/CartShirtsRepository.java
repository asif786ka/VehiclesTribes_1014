package shirts.com.roomormshirts.data;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import shirts.com.roomormshirts.data.local.dao.CartShirtDao;
import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;
import shirts.com.roomormshirts.data.remote.CartShirtDBService;
import shirts.com.roomormshirts.data.remote.model.CartShirtListResponse;
import retrofit2.Call;


public class CartShirtsRepository {

    private final CartShirtDao CartShirtDao;
    private final CartShirtDBService ITunesDBCartService;

    @Inject
    public CartShirtsRepository(CartShirtDao CartShirtDao, CartShirtDBService ITunesDBCartService) {
        this.CartShirtDao = CartShirtDao;
        this.ITunesDBCartService = ITunesDBCartService;
    }

    public LiveData<Resource<List<CartShirtEntity>>> loadCartShirts() {
        return new NetworkBoundResource<List<CartShirtEntity>, CartShirtListResponse>() {

            @Override
            protected void saveCallResult(@NonNull CartShirtListResponse item) {


                /*List<CartShirtEntity> cartSongEntities = new ArrayList<CartShirtEntity>();

                List<Result> ituneList = item.getFeed().getResults();


                CartShirtEntity CartShirtEntity;
                for (int i = 0; i < ituneList.size(); i++) {
                    Result ituneSong = ituneList.get(i);
                    CartShirtEntity = new CartShirtEntity();
                    CartShirtEntity.setPosterPath(ituneSong.getArtworkUrl100().toString());
                    CartShirtEntity.setTitle(ituneSong.getName());
                    CartShirtEntity.setBackdropPath(ituneSong.getArtworkUrl100().toString());
                    CartShirtEntity.setId(Integer.parseInt(ituneSong.getId()));
                    CartShirtEntity.setOverview(ituneSong.getArtistName());
                    cartSongEntities.add(CartShirtEntity);
                }


                CartShirtDao.saveCartShirt(cartSongEntities);*/
            }

            @NonNull
            @Override
            protected LiveData<List<CartShirtEntity>> loadFromDb() {
                return CartShirtDao.loadCartShirts();
            }

            @NonNull
            @Override
            protected Call<CartShirtListResponse> createCall() {
                return ITunesDBCartService.loadCartShirts();

            }
        }.getAsLiveData();
    }



    public LiveData<CartShirtEntity> getCartShirt(int id){
        return CartShirtDao.getCartShirt(id);
    }

    public void addCartShirt(final CartShirtEntity cartShirt){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                CartShirtDao.insert(cartShirt);
                return null;
            }
        }.execute();
    }

    public void deleteCartShirt(CartShirtEntity cartShirt){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                CartShirtDao.delete(cartShirt);
                return null;
            }
        }.execute();
    }
}
