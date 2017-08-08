package shirts.com.roomormshirts.ui.cart;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import shirts.com.roomormshirts.data.local.entity.CartShirtEntity;
import shirts.com.roomormshirts.databinding.FragmentCartShirtsListBinding;


public class CartShirtsListFragment extends LifecycleFragment implements ITunesCartShirtsListCallback,ITunesCartBadgeCountCallback {

    @Inject
    ShirtsCartListViewModel ShirtsCartListViewModel;

    FragmentCartShirtsListBinding binding;

    CartShirtsListAdapter mAdapter;


    public static CartShirtsListFragment newInstance() {
        Bundle args = new Bundle();
        CartShirtsListFragment fragment = new CartShirtsListFragment();
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
        binding = FragmentCartShirtsListBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter = new CartShirtsListAdapter(this,this);
        binding.recyclerView.setAdapter(mAdapter);
        binding.empty.setVisibility(mAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ShirtsCartListViewModel
                .getCartShirts()
                .observe(this, listResource -> binding.setResource(listResource));
    }



    @Override
    public void onShirtImageClicked(CartShirtEntity ITunesSongEntity, View sharedView) {
        ShirtsCartListViewModel.deleteCartShirt(ITunesSongEntity);
        mAdapter.refreshAdapter();

    }

    @Override
    public void onBadgeCount(int badgeCount) {

    }
}
