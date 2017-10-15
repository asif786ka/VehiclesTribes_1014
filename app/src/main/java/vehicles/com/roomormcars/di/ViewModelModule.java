package shirts.com.roomormshirts.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import shirts.com.roomormshirts.ui.main.ShirtsListViewModel;
import shirts.com.roomormshirts.viewmodel.ShirtViewModelFactory;



@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShirtsListViewModel.class)
    abstract ViewModel bindsShirtsListViewModel(ShirtsListViewModel ShirtsListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ShirtViewModelFactory ShirtViewModelFactory);
}
