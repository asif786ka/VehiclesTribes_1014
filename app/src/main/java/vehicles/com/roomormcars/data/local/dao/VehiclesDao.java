package vehicles.com.roomormcars.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;

@Dao
public interface VehiclesDao {

    @Query("SELECT * FROM vehicles")
    LiveData<List<VehiclesEntity>> loadVehicles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveVehicles(List<VehiclesEntity> shirtsEntities);

    @Query("SELECT * FROM vehicles WHERE id=:id")
    LiveData<VehiclesEntity> getVehicle(int id);

}
