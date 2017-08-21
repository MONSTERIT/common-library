package net.wepla.library_common;

import android.content.Context;
import android.graphics.Color;

import com.balysv.materialripple.MaterialRippleLayout;

/**
 * Created by bek on 21/08/2017.
 */

public class CustomTexview extends android.support.v7.widget.AppCompatTextView {

    public CustomTexview(Context context) {
        super(context);
        MaterialRippleLayout.on(this)  // pass view in on() method
                .rippleColor(Color.parseColor("#FF000000")) // set color of ripple
                .rippleAlpha(0.2f) // set alpha
                .rippleHover(true) // enable ripple on hover view
                .create();
    }
}
