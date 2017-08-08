package shirts.com.roomormshirts.ui.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import shirts.com.roomormshirts.R;
import shirts.com.roomormshirts.databinding.ActivityMainBinding;
import shirts.com.roomormshirts.ui.cart.CartActivity;
import shirts.com.roomormshirts.ui.cart.ITunesCartBadgeCountCallback;
import shirts.com.roomormshirts.util.MenuCounterDrawable;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, Toolbar.OnMenuItemClickListener, ITunesCartBadgeCountCallback {


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    ActivityMainBinding binding;

    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.viewPager.setAdapter(new ShirtsPagerAdapter(getSupportFragmentManager()));
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(3);
        binding.toolbar.inflateMenu(R.menu.home_act_filtered);
        binding.toolbar.setOnMenuItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        // Add the filter & search buttons to the toolbar.

        binding.toolbar.inflateMenu(R.menu.home_act_filtered);
        binding.toolbar.setOnMenuItemClickListener(this);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_cart:
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    protected void showRedBadgeCounter(int count) {
        // Get the notifications MenuItem and LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.menu_cart);
        LayerDrawable icon = (LayerDrawable) item.getIcon();
        // Update LayerDrawable's MenuCounterDrawable
        setBadgeCount(this, icon, count/*DatabaseManager.getInstance().getProductCount()*/);
    }

    public void setBadgeCount(Context context, LayerDrawable icon, int count) {

        MenuCounterDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof MenuCounterDrawable) {
            badge = (MenuCounterDrawable) reuse;
        } else {
            badge = new MenuCounterDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }


    @Override
    public void onBadgeCount(int badgeCount) {
        if(badgeCount > 0) {
            showRedBadgeCounter(badgeCount);
        }
    }
}
