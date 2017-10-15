package vehicles.com.roomormcars.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import vehicles.com.roomormcars.ui.main.MainActivity;
import vehicles.com.roomormcars.ui.maps.MapsActivity;


@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MapsActivity mapsActivity();
}
