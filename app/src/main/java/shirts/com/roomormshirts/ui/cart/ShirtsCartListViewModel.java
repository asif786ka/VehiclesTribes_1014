package shirts.com.roomormshirts.ui.cart;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import shirts.com.roomormshirts.data.CartShirtsRepository;
import shirts.com.roomormshirts.data.Resource;
import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;


public class ShirtsCartListViewModel extends ViewModel {
    private final LiveData<Resource<List<CartShirtEntity>>> cartShirts;
    private final CartShirtsRepository CartShirtsRepository;


    @Inject
    public ShirtsCartListViewModel(CartShirtsRepository CartShirtsRepository) {
        this.CartShirtsRepository = CartShirtsRepository;
        cartShirts = CartShirtsRepository.loadCartShirts();
    }

    public LiveData<Resource<List<CartShirtEntity>>> getCartShirts() {
        return cartShirts;
    }

    public void addCartShirt(CartShirtEntity cartShirtEntity){
        CartShirtsRepository.addCartShirt(cartShirtEntity);
    }

    public void deleteCartShirt(CartShirtEntity cartShirtEntity){
        CartShirtsRepository.deleteCartShirt(cartShirtEntity);
    }
}
