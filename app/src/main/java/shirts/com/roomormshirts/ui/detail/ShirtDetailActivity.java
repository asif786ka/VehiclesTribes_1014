package shirts.com.roomormshirts.ui.detail;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import shirts.com.roomormshirts.R;
import shirts.com.roomormshirts.databinding.ActivityShirtDetailBinding;


public class ShirtDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private static final String KEY_SHIRT_ID = "key_shirt_id";

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    ActivityShirtDetailBinding binding;

    @Inject
    ShirtDetailViewModel shirtDetailViewModel;

    public static Intent newIntent(Context context, int shirtId) {
        Intent intent = new Intent(context, ShirtDetailActivity.class);
        intent.putExtra(KEY_SHIRT_ID, shirtId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shirt_detail);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int shirtId = getIntent().getIntExtra(KEY_SHIRT_ID, 0);
        shirtDetailViewModel.getShirt(shirtId)
                .observe(this, shirtEntity -> binding.setShirt(shirtEntity));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

}
