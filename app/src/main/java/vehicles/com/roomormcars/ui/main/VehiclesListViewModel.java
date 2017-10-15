package vehicles.com.roomormcars.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import vehicles.com.roomormcars.data.ShirtsRepository;
import vehicles.com.roomormcars.data.Resource;
import vehicles.com.roomormcars.data.local.entity.ShirtsEntity;


public class ShirtsListViewModel extends ViewModel {
    private final LiveData<Resource<List<ShirtsEntity>>> shirts;

    @Inject
    public ShirtsListViewModel(ShirtsRepository ShirtsRepository) {
        shirts = ShirtsRepository.loadShirts();
    }

    public LiveData<Resource<List<ShirtsEntity>>> getShirts() {
        return shirts;
    }
}
