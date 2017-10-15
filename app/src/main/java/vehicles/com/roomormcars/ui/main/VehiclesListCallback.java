package vehicles.com.roomormcars.ui.main;

import android.view.View;

import vehicles.com.roomormcars.data.local.entity.ShirtsEntity;


public interface ShirtsListCallback {
    void onShirtClicked(ShirtsEntity ShirtsEntity, View sharedView);

    void onAddShirtClicked(ShirtsEntity ShirtsEntity, View sharedView);

}
