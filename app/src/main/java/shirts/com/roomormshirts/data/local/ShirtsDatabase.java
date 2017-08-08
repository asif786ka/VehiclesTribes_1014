package shirts.com.roomormshirts.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import shirts.com.roomormshirts.data.local.dao.CartShirtDao;
import shirts.com.roomormshirts.data.local.dao.ShirtsDao;
import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;
import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;


@Database(entities = {ShirtsEntity.class, CartShirtEntity.class}, version = 2)
public abstract class ShirtsDatabase extends RoomDatabase{

    public abstract ShirtsDao shirtsDao();

    public abstract CartShirtDao cartShirtsDao();
}
