package shirts.com.roomormshirts.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



public class ShirtsPagerAdapter extends FragmentStatePagerAdapter{

    private static final String[] titles = new String[]{"ShirtsCategory1", "ShirtsCategory2", "ShirtsCategory3"};

    public ShirtsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return ShirtsListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
