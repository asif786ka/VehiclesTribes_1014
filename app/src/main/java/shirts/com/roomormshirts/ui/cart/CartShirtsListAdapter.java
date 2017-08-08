package shirts.com.roomormshirts.ui.cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;
import shirts.com.roomormshirts.databinding.ItemCartShirtListBinding;
import shirts.com.roomormshirts.ui.BaseAdapter;


public class CartShirtsListAdapter extends BaseAdapter<CartShirtsListAdapter.ShirtViewHolder, CartShirtEntity> {

    private List<CartShirtEntity> shirtEntities;

    private final ITunesCartShirtsListCallback ShirtsListCallback;

    private final ITunesCartBadgeCountCallback ITunesCartBadgeCountCallback;


    public CartShirtsListAdapter(@NonNull ITunesCartShirtsListCallback ShirtsListCallback, ITunesCartBadgeCountCallback ITunesCartBadgeCountCallback) {
        shirtEntities = new ArrayList<>();
        this.ShirtsListCallback = ShirtsListCallback;
        this.ITunesCartBadgeCountCallback = ITunesCartBadgeCountCallback;
    }

    @Override
    public void setData(List<CartShirtEntity> shirtEntities) {
        this.shirtEntities = shirtEntities;
        notifyDataSetChanged();
        ITunesCartBadgeCountCallback.onBadgeCount(shirtEntities.size());
    }

    @Override
    public ShirtViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return ShirtViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, ShirtsListCallback);
    }

    @Override
    public void onBindViewHolder(ShirtViewHolder viewHolder, int i) {
        viewHolder.onBind(shirtEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return shirtEntities.size();
    }

    static class ShirtViewHolder extends RecyclerView.ViewHolder {

        public static ShirtViewHolder create(LayoutInflater inflater, ViewGroup parent, ITunesCartShirtsListCallback callback) {
            ItemCartShirtListBinding itemSongListBinding = ItemCartShirtListBinding.inflate(inflater, parent, false);
            return new ShirtViewHolder(itemSongListBinding, callback);
        }

        ItemCartShirtListBinding   binding;

        public ShirtViewHolder(ItemCartShirtListBinding   binding, ITunesCartShirtsListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.imageDelete.setOnClickListener(v ->
                    callback.onShirtImageClicked(binding.getShirt(), binding.imageDelete));
        }

        public void onBind(CartShirtEntity ITunesSongEntity) {
            binding.setShirt(ITunesSongEntity);
            binding.executePendingBindings();
        }
    }

    public void refreshAdapter() {

        notifyDataSetChanged();
        ITunesCartBadgeCountCallback.onBadgeCount(shirtEntities.size());

    }
}
