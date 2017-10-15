package vehicles.com.roomormcars.ui.main;

import android.arch.lifecycle.LifecycleFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import vehicles.com.roomormcars.data.local.entity.CartShirtEntity;
import vehicles.com.roomormcars.data.local.entity.ShirtsEntity;
import vehicles.com.roomormcars.databinding.FragmentShirtsListBinding;
import vehicles.com.roomormcars.ui.maps.MapsActivity;


public class ShirtsListFragment extends LifecycleFragment implements ShirtsListCallback {

    @Inject
    ShirtsListViewModel ShirtsListViewModel;


    FragmentShirtsListBinding binding;

    CartShirtEntity cartShirtEntity;


    public static ShirtsListFragment newInstance() {
        Bundle args = new Bundle();
        ShirtsListFragment fragment = new ShirtsListFragment();
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
        binding = FragmentShirtsListBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerView.setAdapter(new ShirtsListAdapter(this));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ShirtsListViewModel
                .getShirts()
                .observe(this, listResource -> binding.setResource(listResource));
    }

    @Override
    public void onShirtClicked(ShirtsEntity ShirtsEntity, View sharedView) {

        ArrayList<CarParcelable> carParcelableList = new ArrayList<CarParcelable>();

        Gson gson;

        for(ShirtsEntity car : ShirtsListViewModel.getShirts().getValue().data ){
            gson = new Gson();
            Type type = new TypeToken<List<Double>>(){}.getType();
            List<Double> carsList = gson.fromJson(car.getCoordinatesList(), type);
            carParcelableList.add(new CarParcelable(carsList.get(0),
                    carsList.get(1), car.getTitle(), car.getOverview()));
        }
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putParcelableArrayListExtra("carList",carParcelableList );
        intent.putExtra("selectedCar", ShirtsEntity.getId());
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }

    @Override
    public void onAddShirtClicked(ShirtsEntity ShirtsEntity, View sharedView) {

    }
}
