package shirts.com.roomormshirts.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import shirts.com.roomormshirts.R;



public final class ImageBindingAdapter {

    @BindingAdapter(value = "url")
    public static void loadImageUrl(ImageView view, String url) {
        if (url != null && !url.equals(""))
            Picasso.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .into(view);
    }

}
