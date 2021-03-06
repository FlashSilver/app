package com.uci.notshare;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App extends Application {

	@Override
	public void onCreate() {

		printHashkey();
		// TODO Auto-generated method stub
		super.onCreate();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(480, 800) // max width, max height锟斤拷锟斤拷锟斤拷锟斤拷锟矫匡拷锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟襟长匡拷
				.threadPoolSize(6)// 锟竭程筹拷锟节硷拷锟截碉拷锟斤拷锟斤拷
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/锟斤拷锟斤拷锟酵拷锟斤拷约锟斤拷锟斤拷诖婊猴拷锟绞碉拷锟�
				.memoryCacheSize(2 * 1024 * 1024).discCacheSize(50 * 1024 * 1024).discCacheFileNameGenerator(new Md5FileNameGenerator())// 锟斤拷锟斤拷锟斤拷锟绞憋拷锟斤拷URI锟斤拷锟斤拷锟組D5 锟斤拷锟斤拷
				.tasksProcessingOrder(QueueProcessingType.LIFO).discCacheFileCount(100) // 锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟�
				//.discCache((DiskCache) new UnlimitedDiscCache(cacheDir))// 锟皆讹拷锟藉缓锟斤拷路锟斤拷
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()).imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)锟斤拷时时锟斤拷
				.writeDebugLogs() // Remove for release app
				.build();// 锟斤拷始锟斤拷锟斤拷 
		// Initialize ImageLoader with configuration.

		ImageLoader.getInstance().init(config);// 全锟街筹拷始锟斤拷锟斤拷锟斤拷锟斤拷
	}

	public void printHashkey(){
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.uci.notshare",
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}

	}
}
