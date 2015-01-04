package com.aaaliua.application;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Typeface;

import com.aaaliua.function.FileRequestTransformer;
import com.dazhongcun.application.BaseApplication;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class AppApplication extends BaseApplication {

	private static HashMap<String, Typeface> sTypefaces = new HashMap<String, Typeface>();
	private static Picasso sPicasso, sPicassoDetailsView;
	private static Downloader sDownloader;
	private static ThreadPoolExecutor sThreadPoolExecutor;
	private static LruCache sLruCache;
	private static FileRequestTransformer mFileRequestTransformer;
	
	private static File sBaseAppFolder, sMovieThumbFolder, sMovieBackdropFolder, sTvShowThumbFolder, sTvShowBackdropFolder, sTvShowEpisodeFolder, sTvShowSeasonFolder, sAvailableOfflineFolder, sCacheFolder;
	
	public static Typeface getOrCreateTypeface(Context context, String key) {
		if (!sTypefaces.containsKey(key))
			sTypefaces.put(key, Typeface.createFromAsset(context.getAssets(), key));
		return sTypefaces.get(key);
	}
	
	public static Picasso getPicasso(Context context) {
		if (sPicasso == null)
			sPicasso = new Picasso.Builder(context).build()/*.downloader(getDownloader(context)).executor(getThreadPoolExecutor()).memoryCache(getLruCache(context)).requestTransformer(getFileRequestTransformer()).build()*/;
		return sPicasso;
	}
	public static Downloader getDownloader(Context context) {
		if (sDownloader == null)
			sDownloader = new OkHttpDownloader(context);
		return sDownloader;
	}
	private static ThreadPoolExecutor getThreadPoolExecutor() {
		if (sThreadPoolExecutor == null)
			sThreadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<Runnable>(), new PicassoThreadFactory());
		return sThreadPoolExecutor;
	}
	
	static class PicassoThreadFactory implements ThreadFactory {
		public Thread newThread(Runnable r) {
			return new PicassoThread(r);
		}
	}
	
	private static class PicassoThread extends Thread {
		public PicassoThread(Runnable r) {
			super(r);
		}

		@Override public void run() {
			android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
			super.run();
		}
	}
	
	public static LruCache getLruCache(Context context) {
		if (sLruCache == null)
			sLruCache = new LruCache(calculateMemoryCacheSize(context));
		return sLruCache;
	}
	
	public static int calculateMemoryCacheSize(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
		int memoryClass = am.getLargeMemoryClass();

		// Target 20% of the available heap.
		return 1024 * 1024 * memoryClass / 5;
	}
	
	private static FileRequestTransformer getFileRequestTransformer() {
		if (mFileRequestTransformer == null)
			mFileRequestTransformer = new FileRequestTransformer();
		return mFileRequestTransformer;
	}
	
	
	public static File getMovieBackdropFolder(Context c) {
		if (sMovieBackdropFolder == null) {
			sMovieBackdropFolder = getSubAppFolder(c, "movie-backdrops");
			sMovieBackdropFolder.mkdirs();
		}
		return sMovieBackdropFolder;
	}
	
	public static File getTvShowBackdropFolder(Context c) {
		if (sTvShowBackdropFolder == null) {
			sTvShowBackdropFolder = getSubAppFolder(c, "tvshows-backdrops");
			sTvShowBackdropFolder.mkdirs();
		}
		return sTvShowBackdropFolder;
	}
	
	
	
	private static File getSubAppFolder(Context c, String foldername) {
		return new File(getAppFolder(c), foldername);
	}
	
	public static File getAppFolder(Context c) {
		if (sBaseAppFolder == null) {
			sBaseAppFolder = c.getExternalFilesDir(null);
        }
		return sBaseAppFolder;
	}
	
	
	
	public static File getCacheFolder(Context c) {
		if (sCacheFolder == null) {
			sCacheFolder = getSubAppFolder(c, "app_cache");
			sCacheFolder.mkdirs();
		}
		return sCacheFolder;
	}
	
	
	@Override
	public void initDB() {

	}

	@Override
	public void initEnv() {

	}

	@Override
	public void initImageLoad() {

	}

}
