package shirts.com.roomormshirts.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import shirts.com.roomormshirts.data.ShirtsRepository;
import shirts.com.roomormshirts.data.Resource;
import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;


public class ShirtDetailViewModel extends ViewModel{
    private final LiveData<Resource<ShirtsEntity>> shirtDetail = new MutableLiveData<>();
    private final ShirtsRepository ShirtsRepository;

    @Inject
    public ShirtDetailViewModel(ShirtsRepository ShirtsRepository) {
        this.ShirtsRepository = ShirtsRepository;
    }

    public LiveData<ShirtsEntity> getShirt(int id){
        return ShirtsRepository.getShirt(id);
    }
}
