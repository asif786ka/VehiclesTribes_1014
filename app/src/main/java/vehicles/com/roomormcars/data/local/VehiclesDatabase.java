package vehicles.com.roomormcars.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import vehicles.com.roomormcars.data.local.dao.CartShirtDao;
import vehicles.com.roomormcars.data.local.dao.ShirtsDao;
import vehicles.com.roomormcars.data.local.entity.CartShirtEntity;
import vehicles.com.roomormcars.data.local.entity.ShirtsEntity;


@Database(entities = {ShirtsEntity.class, CartShirtEntity.class}, version = 2)
public abstract class ShirtsDatabase extends RoomDatabase{

    public abstract ShirtsDao shirtsDao();

    public abstract CartShirtDao cartShirtsDao();
}
