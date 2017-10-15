package vehicles.com.roomormcars.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import vehicles.com.roomormcars.ui.main.VehiclesListViewModel;
import vehicles.com.roomormcars.viewmodel.VehicleViewModelFactory;



@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VehiclesListViewModel.class)
    abstract ViewModel bindsVehiclesListViewModel(VehiclesListViewModel VehiclesListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(VehicleViewModelFactory VehicleViewModelFactory);
}
