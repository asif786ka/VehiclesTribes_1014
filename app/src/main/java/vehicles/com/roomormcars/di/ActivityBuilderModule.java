package shirts.com.roomormshirts.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import shirts.com.roomormshirts.ui.main.MainActivity;
import shirts.com.roomormshirts.ui.maps.MapsActivity;


@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MapsActivity mapsActivity();
}
