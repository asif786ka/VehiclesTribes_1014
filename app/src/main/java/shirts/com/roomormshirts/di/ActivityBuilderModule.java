package shirts.com.roomormshirts.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import shirts.com.roomormshirts.ui.cart.CartActivity;
import shirts.com.roomormshirts.ui.detail.ShirtDetailActivity;
import shirts.com.roomormshirts.ui.main.MainActivity;


@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract CartActivity cartActivity();

    @ContributesAndroidInjector
    abstract ShirtDetailActivity shirtDetailActivity();
}
