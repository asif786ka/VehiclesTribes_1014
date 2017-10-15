package vehicles.com.roomormcars.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import vehicles.com.roomormcars.ui.main.VehiclesListFragment;


@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract VehiclesListFragment contributeVehiclesListFragment();
}
