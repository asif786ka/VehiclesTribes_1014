package shirts.com.roomormshirts.ui.cart;

import android.view.View;

import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;


public interface ITunesCartShirtsListCallback {
    void onShirtImageClicked(CartShirtEntity ITunesSongEntity, View sharedView);
}
