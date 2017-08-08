package shirts.com.roomormshirts.ui.main;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import shirts.com.roomormshirts.R;
import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;
import shirts.com.roomormshirts.data.local.entity.ShirtsEntity;
import shirts.com.roomormshirts.databinding.FragmentShirtsListBinding;
import shirts.com.roomormshirts.ui.cart.ShirtsCartListViewModel;
import shirts.com.roomormshirts.ui.detail.ShirtDetailActivity;


public class ShirtsListFragment extends LifecycleFragment implements ShirtsListCallback {

    @Inject
    ShirtsListViewModel ShirtsListViewModel;

    @Inject
    ShirtsCartListViewModel shirtsCartListViewModel;

    FragmentShirtsListBinding binding;

    CartShirtEntity cartShirtEntity;


    public static ShirtsListFragment newInstance() {
        Bundle args = new Bundle();
        ShirtsListFragment fragment = new ShirtsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShirtsListBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerView.setAdapter(new ShirtsListAdapter(this));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ShirtsListViewModel
                .getShirts()
                .observe(this, listResource -> binding.setResource(listResource));
    }

    @Override
    public void onShirtClicked(ShirtsEntity ShirtsEntity, View sharedView) {

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), sharedView, getString(R.string.shared_image));
        startActivity(ShirtDetailActivity.newIntent(getActivity(), ShirtsEntity.getId()), options.toBundle());
    }

    @Override
    public void onAddShirtClicked(ShirtsEntity ShirtsEntity, View sharedView) {
        cartShirtEntity = new CartShirtEntity();
        cartShirtEntity.setId(ShirtsEntity.getId());
        cartShirtEntity.setPosterPath(ShirtsEntity.getPosterPath());
        cartShirtEntity.setTitle(ShirtsEntity.getTitle());
        shirtsCartListViewModel.addCartShirt(cartShirtEntity);

        Toast.makeText(getActivity(),"Shirt added to Cart",Toast.LENGTH_LONG).show();
    }
}
