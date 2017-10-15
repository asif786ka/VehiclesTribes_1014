package shirts.com.roomormshirts.databinding;

import android.databinding.BindingAdapter;
import android.widget.TextView;


public final class TextBindingAdapter {

    @BindingAdapter(value = "text")
    public static void setText(TextView view, String text) {
        if (text!= null && !text.equals(""))
            view.setText(text);
    }

}
