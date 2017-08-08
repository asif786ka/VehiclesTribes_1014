package shirts.com.roomormshirts.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;

@Dao
public interface CartShirtDao {

    @Query("SELECT * FROM cartshirts")
    LiveData<List<CartShirtEntity>> loadCartShirts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCartShirt(List<CartShirtEntity> cartShirtsEntities);

    @Query("SELECT * FROM cartshirts WHERE id=:id")
    LiveData<CartShirtEntity> getCartShirt(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartShirtEntity cartShirt);

    @Delete
    void delete(CartShirtEntity cartShirt);

}
