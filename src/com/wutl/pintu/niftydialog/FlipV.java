package com.wutl.pintu.niftydialog;
import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

public class FlipV extends BaseEffects{

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationX", -90, 0).setDuration(mDuration)

        );
    }
}