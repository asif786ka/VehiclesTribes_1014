package vehicles.com.roomormcars.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import vehicles.com.roomormcars.data.local.dao.VehiclesDao;
import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;


@Database(entities = {VehiclesEntity.class}, version = 2)
public abstract class VehiclesDatabase extends RoomDatabase{

    public abstract VehiclesDao vehiclesDao();
}
