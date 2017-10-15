package vehicles.com.roomormcars.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class VehiclesPagerAdapter extends FragmentStatePagerAdapter {


    /*
      If more categories are required for vehicles.Right now only one category.
    */
    private static final String[] titles = new String[]{"VehiclesCategory"};

    public VehiclesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return VehiclesListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
