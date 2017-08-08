package shirts.com.roomormshirts.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;

@Dao
public interface ShirtsDao {

    @Query("SELECT * FROM shirts")
    LiveData<List<ShirtsEntity>> loadShirts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveShirts(List<ShirtsEntity> shirtsEntities);

    @Query("SELECT * FROM shirts WHERE id=:id")
    LiveData<ShirtsEntity> getShirt(int id);

}
