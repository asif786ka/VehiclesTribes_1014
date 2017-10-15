package vehicles.com.roomormcars.ui.main;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import vehicles.com.roomormcars.R;
import vehicles.com.roomormcars.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    ActivityMainBinding binding;

    private int containerId;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (savedInstanceState == null) {
            this.containerId = R.id.container;
            this.fragmentManager = getSupportFragmentManager();
            navigateToVehicles(containerId,fragmentManager);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    public void navigateToVehicles(int containerID,FragmentManager fragmentManager) {
        VehiclesListFragment fragment = VehiclesListFragment.newInstance();
        String tag = "vechiclesFragment";
        fragmentManager.beginTransaction()
                .replace(containerID, fragment, tag)
                .commitAllowingStateLoss();
    }


}