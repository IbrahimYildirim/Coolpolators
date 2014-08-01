package com.example.coolpolators;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import com.example.iycustominterpolators.R;

public class MainActivity extends Activity {

	private boolean overshootIsBig = false;
	private boolean jelloIsBig = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	
	public void onOvershootClick(final View v){
		
        ValueAnimator vaX = (overshootIsBig) ? ValueAnimator.ofInt(400,150) : ValueAnimator.ofInt(150,400);
        vaX.setDuration(1000);
        vaX.setInterpolator(new OvershootInterpolator());
        vaX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().height = value.intValue();
                v.requestLayout();
            }
        });
        vaX.start();
        
        ValueAnimator vaY = (overshootIsBig) ? ValueAnimator.ofInt(500,250) : ValueAnimator.ofInt(250,500);
        vaY.setDuration(1000);
        vaY.setInterpolator(new OvershootInterpolator());
        vaY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().width = value.intValue();
                v.requestLayout();
            }
        });
        vaY.start();
        
        overshootIsBig = !overshootIsBig;
	}
	
	public void onJelloClick(final View v){
		
        ValueAnimator vaX = (jelloIsBig) ? ValueAnimator.ofInt(400,150) : ValueAnimator.ofInt(150,400);
        vaX.setDuration(1000);
        vaX.setInterpolator(new JellyBounceInterpolator());
        vaX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().height = value.intValue();
                v.requestLayout();
            }
        });
        vaX.start();
        
        ValueAnimator vaY = (jelloIsBig) ? ValueAnimator.ofInt(500,250) : ValueAnimator.ofInt(250,500);
        vaY.setDuration(1000);
        vaY.setInterpolator(new JellyBounceInterpolator());
        vaY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().width = value.intValue();
                v.requestLayout();
            }
        });
        vaY.start();
        
        jelloIsBig = !jelloIsBig;
	}
	
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public class JellyBounceInterpolator implements Interpolator {

		@Override
		public float getInterpolation(float ratio) {
			if (ratio == 0.0f || ratio == 1.0f)
				return ratio;
			else {
				float p = 0.6f;
				float two_pi = (float) (Math.PI * 2.7f);
				return (float) Math.pow(2.0f, -10.0f * ratio) * (float) Math.sin((ratio - (p/5.0f)) * two_pi/p) + 1.0f;
			}
		}
	}

}
