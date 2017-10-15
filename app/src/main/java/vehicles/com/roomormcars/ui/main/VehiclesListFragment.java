package vehicles.com.roomormcars.ui.main;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import vehicles.com.roomormcars.data.local.CarParcelable;
import vehicles.com.roomormcars.data.local.entity.VehiclesEntity;
import vehicles.com.roomormcars.databinding.FragmentVehiclesListBinding;
import vehicles.com.roomormcars.ui.maps.MapsActivity;


public class VehiclesListFragment extends LifecycleFragment implements VehiclesListCallback {

    @Inject
    VehiclesListViewModel VehiclesListViewModel;


    FragmentVehiclesListBinding binding;

    ArrayList<CarParcelable> carParcelableList = new ArrayList<CarParcelable>();

    public static VehiclesListFragment newInstance() {
        Bundle args = new Bundle();
        VehiclesListFragment fragment = new VehiclesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVehiclesListBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(new VehiclesListAdapter(this));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        VehiclesListViewModel
                .getVehicles()
                .observe(this, listResource -> binding.setResource(listResource));
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onVehicleClicked(VehiclesEntity vehiclesEntity, View sharedView) {

        carParcelableList = getCarsList();

        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putParcelableArrayListExtra("carList",carParcelableList );
        intent.putExtra("selectedCar", vehiclesEntity.getId());
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }

    public ArrayList<CarParcelable> getCarsList(){


        Gson gson;

        for(VehiclesEntity car : VehiclesListViewModel.getVehicles().getValue().data ){
            gson = new Gson();
            Type type = new TypeToken<List<Double>>(){}.getType();
            List<Double> carsList = gson.fromJson(car.getCoordinatesList(), type);
            carParcelableList.add(new CarParcelable(carsList.get(0),
                    carsList.get(1), car.getTitle(), car.getOverview()));
        }

        return carParcelableList;
    }

}
