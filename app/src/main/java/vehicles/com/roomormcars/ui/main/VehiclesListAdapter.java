package vehicles.com.roomormcars.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;
import vehicles.com.roomormcars.databinding.ItemVehicleListBinding;
import vehicles.com.roomormcars.ui.BaseAdapter;



public class VehiclesListAdapter extends BaseAdapter<VehiclesListAdapter.VehicleViewHolder, VehiclesEntity> {

    private List<VehiclesEntity> vehiclesEntities;

    private final VehiclesListCallback VehiclesListCallback;

    public VehiclesListAdapter(@NonNull VehiclesListCallback VehiclesListCallback) {
        vehiclesEntities = new ArrayList<>();
        this.VehiclesListCallback = VehiclesListCallback;
    }

    @Override
    public void setData(List<VehiclesEntity> shirtEntities) {
        this.vehiclesEntities = shirtEntities;
        notifyDataSetChanged();
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return VehicleViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, VehiclesListCallback);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder viewHolder, int i) {
        viewHolder.onBind(vehiclesEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return vehiclesEntities.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {

        public static VehicleViewHolder create(LayoutInflater inflater, ViewGroup parent, VehiclesListCallback callback) {
            ItemVehicleListBinding itemVehicleListBinding = ItemVehicleListBinding.inflate(inflater, parent, false);
            return new VehicleViewHolder(itemVehicleListBinding, callback);
        }

        ItemVehicleListBinding binding;

        public VehicleViewHolder(ItemVehicleListBinding binding, VehiclesListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v ->
                    callback.onVehicleClicked(binding.getVehicle(), binding.getRoot()));;
        }

        public void onBind(VehiclesEntity VehiclesEntity) {
            binding.setVehicle(VehiclesEntity);
            binding.executePendingBindings();
        }
    }
}
