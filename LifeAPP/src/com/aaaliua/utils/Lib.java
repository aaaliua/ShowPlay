package com.aaaliua.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.aaaliua.application.AppApplication;
import com.aaaliua.lifeapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.TypedValue;


@SuppressLint("NewApi")
public class Lib {

	private static String[] MEDIA_APPS = new String[]{"com.imdb.mobile", "com.google.android.youtube", "com.ted.android", "com.google.android.videos", "se.mtg.freetv.tv3_dk", "tv.twitch.android.viewer",
		"com.netflix.mediaclient", "com.gotv.crackle.handset", "net.flixster.android", "com.google.tv.alf", "com.viki.android", "com.mobitv.client.mobitv", "com.hulu.plus.jp", "com.hulu.plus",
		"com.mobitv.client.tv", "air.com.vudu.air.DownloaderTablet", "com.hbo.android.app", "com.HBO", "bbc.iplayer.android", "air.uk.co.bbc.android.mediaplayer", "com.rhythmnewmedia.tvdotcom",
		"com.cnettv.app", "com.xfinity.playnow"};
	
	
	
	public static int getActionBarHeight(Context c) {
		int actionBarHeight = 0;
		TypedValue tv = new TypedValue();
		if (c.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, c.getResources().getDisplayMetrics());
		else
            actionBarHeight = 0; // No ActionBar style (pre-Honeycomb or ActionBar not in theme)

		return actionBarHeight;
	}
	
	
	public static int convertDpToPixels(Context c, int dp) {
		Resources r = c.getResources();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
	}
	
	public static boolean isTablet(Context c) {
		return (c.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
	
	public static boolean usesNavigationControl(Context context) {
		Configuration configuration = context.getResources().getConfiguration();
		if (configuration.navigation == Configuration.NAVIGATION_NONAV) {
			return false;
		} else if (configuration.touchscreen == Configuration.TOUCHSCREEN_FINGER) {
			return false;
		} else if (configuration.navigation == Configuration.NAVIGATION_DPAD) {
			return true;
		} else if (configuration.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH) {
			return true;
		} else if (configuration.touchscreen == Configuration.TOUCHSCREEN_UNDEFINED) {
			return true;
		} else if (configuration.navigationHidden == Configuration.NAVIGATIONHIDDEN_YES) {
			return true;
		} else if (configuration.uiMode == Configuration.UI_MODE_TYPE_TELEVISION) {
			return true;
		}
		return false;
	}
	
	
	public static boolean isMediaApp(ApplicationInfo ai) {
		for (int i = 0; i < MEDIA_APPS.length; i++)
			if (MEDIA_APPS[i].equals(ai.packageName))
				return true;
		return false;
	}
	
	
	public static String getRandomBackdropPath(Context c) {
		ArrayList<File> files = new ArrayList<File>();

		File[] f = AppApplication.getMovieBackdropFolder(c).listFiles();
		if (f != null)
            Collections.addAll(files, f);

		f = AppApplication.getTvShowBackdropFolder(c).listFiles();
		if (f != null)
            Collections.addAll(files, f);

		if (files.size() > 0) {
			Random rndm = new Random();
			return files.get(rndm.nextInt(files.size())).getAbsolutePath();
		}

		return "";
	}
	
	public static boolean hasLollipop() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}
	public static boolean hasJellyBeanMR2() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
	}
	@SuppressWarnings("deprecation")
	public static long getFreeMemory() {
		StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
		if (hasJellyBeanMR2())
			return stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
		else
			return stat.getAvailableBlocks() * stat.getBlockSize();
	}
}
