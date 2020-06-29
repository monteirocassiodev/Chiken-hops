package com.bsb;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.bsb.Bsb;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

public class AndroidLauncher extends AndroidApplication {
	private static final String TAG = " AndroidLauncher";
	protected AdView adView;
	private AudioAttributes.Builder builder;

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout layout = new RelativeLayout(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Bsb(), config);
		View gameView = initializeForView(new AdMobDemo(),config);
		Class<Layout> layoutClass = Layout.class;

		adView = new AdView(this);

		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad Loaded...");
			}
		});
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-6399906585875445~1062199101");
		AdRequest.Builder Builder = new AdRequest.Builder();
		builder.addTestDevice("79b448dff4281063f89e9425df2a3530");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		layout.addView(adView, adParams);
		adView.loadAd(Builder.build());

		setContentView(layout);



	}
	}







