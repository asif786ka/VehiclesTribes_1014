package shirts.com.roomormshirts.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import shirts.com.roomormshirts.ui.main.ShirtsListFragment;


@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ShirtsListFragment contributeShirtListFragment();
}
