package shirts.com.roomormshirts.ui.main;

import android.view.View;

import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;


public interface ShirtsListCallback {
    void onShirtClicked(ShirtsEntity ShirtsEntity, View sharedView);

    void onAddShirtClicked(ShirtsEntity ShirtsEntity, View sharedView);

}
