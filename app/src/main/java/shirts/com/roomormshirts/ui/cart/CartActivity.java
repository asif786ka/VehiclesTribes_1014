package shirts.com.roomormshirts.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import shirts.com.roomormshirts.R;
import shirts.com.roomormshirts.databinding.ActivityCartMainBinding;

public class CartActivity extends AppCompatActivity implements HasSupportFragmentInjector,ITunesCartBadgeCountCallback{

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    ActivityCartMainBinding binding;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CartActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart_main);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    @Override
    public void onBadgeCount(int badgeCount) {
        Toast.makeText(this,String.valueOf(badgeCount),Toast.LENGTH_LONG).show();
    }
}
