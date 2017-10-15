package vehicles.com.roomormcars.ui.main;

import android.view.View;

import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;


public interface VehiclesListCallback {
    void onVehicleClicked(VehiclesEntity vehiclesEntity, View sharedView);
}
