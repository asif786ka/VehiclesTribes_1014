package vehicles.com.roomormcars.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import vehicles.com.roomormcars.data.VehiclesRepository;
import vehicles.com.roomormcars.data.Resource;
import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;


public class VehiclesListViewModel extends ViewModel {
    private final LiveData<Resource<List<VehiclesEntity>>> vehicles;

    @Inject
    public VehiclesListViewModel(VehiclesRepository VehiclesRepository) {
        vehicles = VehiclesRepository.loadVehicles();
    }

    public LiveData<Resource<List<VehiclesEntity>>> getVehicles() {
        return vehicles;
    }
}
