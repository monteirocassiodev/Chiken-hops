package com.bsb;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {
	private static final String TAG = " AndroidLauncher";
	protected AdView adView;
	private AudioAttributes.Builder builder;
	private RelativeLayout.LayoutParams params;
	private adView bannerAd;

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout layout = new RelativeLayout(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Bsb(), config);
		View gameView = initializeForView(new MyGDXGame(),config);

		// definição do layout

		layout =new RelativeLayout(this);
		layout.addView(gameView), ViewGroup.LayoutParams.MATCH_PARENT;
				ViewGroup.LayoutParams.MATCH_PARENT;

		params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);

		bannerAd = new adView(this);
		adView.setAdUnitId("ca-app-pub-6399906585875445/8553676315");
		adView.setAdSize(AdSize.SMART_BANNER);

		layout.addView(bannerAd, params);
		SetContentView(layout);

		AdRequest ad = new AdRequest.Builder().build();
		adView.loadAd(ad);


	}

	private void SetContentView(RelativeLayout relativeLayout) {

	}

}







