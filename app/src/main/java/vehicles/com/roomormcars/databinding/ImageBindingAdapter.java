package vehicles.com.roomormcars.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vehicles.com.roomormcars.R;



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
