package com.app.appbelajarkomik.ads;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.utils.Constant;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ManagerAds {
    String TAG ="ManagerAdsTAG";
    Context context;
    OnInterReady onInterReady;
    private void setOnInterReady(OnInterReady onInterReady){
        this.onInterReady = onInterReady;
    }

    public ManagerAds(Context context) {
        this.context = context; 
        init();
    }

    private void init() {
        AppLovinSdk.getInstance( context ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( context, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );
    }

    public void setBanner(RelativeLayout relativeLayout){MaxAdView adView = new MaxAdView( Constant.getBannerId(context), context );
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {
                Log.d(TAG, "onAdExpanded: ");

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {
                Log.d(TAG, "onAdCollapsed: ");

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                Log.d(TAG, "onAdLoaded: ");

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {
                Log.d(TAG, "onAdDisplayed: ");

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                Log.d(TAG, "onAdHidden: ");

            }

            @Override
            public void onAdClicked(MaxAd ad) {
                Log.d(TAG, "onAdClicked: ");

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                Log.d(TAG, "onAdLoadFailed: "+error.getMessage());

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                Log.d(TAG, "onAdDisplayFailed: "+error.getMessage());

            }
        });

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int heightPx = context.getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );
        relativeLayout.addView(adView);

        adView.loadAd();
    }

    private int retryAttempt;
    public void setInterstitial(){
        AppLovinSdk.getInstance( context ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( context, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {

                MaxInterstitialAd interstitialAd = new MaxInterstitialAd( Constant.getInterId(context),
                        (Activity) context);
                MaxAdListener maxAdListener = new MaxAdListener() {
                    @Override
                    public void onAdLoaded(final MaxAd maxAd)
                    {
                        retryAttempt = 0;
                        if (interstitialAd.isReady())   {
                            interstitialAd.showAd();
                        }

                    }
                    @Override
                    public void onAdLoadFailed(final String adUnitId, final MaxError error)
                    {
                        retryAttempt++;
                        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                interstitialAd.loadAd();
                            }
                        }, delayMillis );
                    }
                    @Override
                    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error)
                    {
                        interstitialAd.loadAd();
                    }
                    @Override
                    public void onAdDisplayed(final MaxAd maxAd) {}
                    @Override
                    public void onAdClicked(final MaxAd maxAd) {}
                    @Override
                    public void onAdHidden(final MaxAd maxAd)
                    {
                        // interstitialAd.loadAd();
                    }
                };
                interstitialAd.setListener( maxAdListener );
                interstitialAd.loadAd();

            }
        } );

    }

    private MaxAd nativeAd;

    public void setNativeAds(FrameLayout frameLayout){
        MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader( Constant.getNativeId(context), context );
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd );
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                nativeAdView.setLayoutParams( new FrameLayout.LayoutParams( 450  , 250   ) );

                frameLayout.removeAllViews();
                frameLayout.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
            }
        } );

        nativeAdLoader.loadAd(); 
    }


    public interface OnInterReady{
        void onShow();
    }


}