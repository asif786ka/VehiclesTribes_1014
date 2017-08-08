package shirts.com.roomormshirts.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;
import shirts.com.roomormshirts.databinding.ItemShirtListBinding;
import shirts.com.roomormshirts.ui.BaseAdapter;



public class ShirtsListAdapter extends BaseAdapter<ShirtsListAdapter.ShirtViewHolder, ShirtsEntity> {

    private List<ShirtsEntity> shirtsEntities;

    private final ShirtsListCallback ShirtsListCallback;

    public ShirtsListAdapter(@NonNull ShirtsListCallback ShirtsListCallback) {
        shirtsEntities = new ArrayList<>();
        this.ShirtsListCallback = ShirtsListCallback;
    }

    @Override
    public void setData(List<ShirtsEntity> shirtEntities) {
        this.shirtsEntities = shirtEntities;
        notifyDataSetChanged();
    }

    @Override
    public ShirtViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return ShirtViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, ShirtsListCallback);
    }

    @Override
    public void onBindViewHolder(ShirtViewHolder viewHolder, int i) {
        viewHolder.onBind(shirtsEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return shirtsEntities.size();
    }

    static class ShirtViewHolder extends RecyclerView.ViewHolder {

        public static ShirtViewHolder create(LayoutInflater inflater, ViewGroup parent, ShirtsListCallback callback) {
            ItemShirtListBinding itemShirtListBinding = ItemShirtListBinding.inflate(inflater, parent, false);
            return new ShirtViewHolder(itemShirtListBinding, callback);
        }

        ItemShirtListBinding binding;

        public ShirtViewHolder(ItemShirtListBinding binding, ShirtsListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.imageAdd.setOnClickListener(v ->
                    callback.onAddShirtClicked(binding.getShirt(), binding.imageAdd));
            binding.imageViewCover.setOnClickListener(v ->
                    callback.onShirtClicked(binding.getShirt(), binding.imageViewCover));
            binding.shirtColor.setVisibility(View.VISIBLE);
        }

        public void onBind(ShirtsEntity ShirtsEntity) {
            binding.setShirt(ShirtsEntity);
            binding.executePendingBindings();
        }
    }
}
