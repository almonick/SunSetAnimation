package beatbox.android.bignerdranch.com.sunsetanimation;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;

/**
 * Created by Bender on 28/09/2015.
 */
public class SunsetFragment extends Fragment {

	private View mScene, mSun, mSky;
	private int mBlueSkyColor, mSunsetSkyColor, mNightSkyColor;

	public static SunsetFragment newInstance(){
		return new SunsetFragment();
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mScene = inflater.inflate(R.layout.fragment_sunset, container, false);
		mSky = mScene.findViewById(R.id.sky);
		mSun = mScene.findViewById(R.id.sun);
		Resources res = getResources();
		mBlueSkyColor = res.getColor(R.color.blue_sky);
		mNightSkyColor = res.getColor(R.color.night_sky);
		mSunsetSkyColor = res.getColor(R.color.sunset_sky);
		mScene.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startAnimation();
			}
		});
		return mScene;
	}

	private void startAnimation(){
		int sunYstart = mSun.getTop();
		int sunYend = mSky.getHeight();
		Log.d("", "### sunYstart  " + sunYstart + " " + sunYend);
		ObjectAnimator hightAnimation = ObjectAnimator.ofFloat(mSun, "y", sunYstart, sunYend).setDuration(3000l);
		hightAnimation.setInterpolator(new AccelerateInterpolator());
		ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofInt(mSky, "backgroundColor", mBlueSkyColor, mSunsetSkyColor).setDuration(3000l);
		sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());

		ObjectAnimator nightSkyAnimator = ObjectAnimator.ofInt(mSky, "backgroundColor", mSunsetSkyColor, mNightSkyColor).setDuration(1500l);
		nightSkyAnimator.setEvaluator(new ArgbEvaluator());

		AnimatorSet set = new AnimatorSet();
		set.play(hightAnimation).with(sunsetSkyAnimator)
				.before(nightSkyAnimator);
		set.start();
	}
}
