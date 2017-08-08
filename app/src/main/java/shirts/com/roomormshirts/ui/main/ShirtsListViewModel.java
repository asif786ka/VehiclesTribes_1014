package shirts.com.roomormshirts.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import shirts.com.roomormshirts.data.ShirtsRepository;
import shirts.com.roomormshirts.data.Resource;
import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;


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
