package com.kull.android;



import java.util.concurrent.ExecutorService;

import com.kull.android.app.KULLApplication;
import com.kull.android.image.ImageCache;

import android.content.Context;

public class Utils {
	 private Utils() {
	    }

	    /**
	     * Return the current {@link GDApplication}
	     * 
	     * @param context The calling context
	     * @return The {@link GDApplication} the given context is linked to.
	     */
	    public static KULLApplication getGDApplication(Context context) {
	        return (KULLApplication) context.getApplicationContext();
	    }

	    /**
	     * Return the {@link GDApplication} image cache
	     * 
	     * @param context The calling context
	     * @return The image cache of the current {@link GDApplication}
	     */
	    public static ImageCache getImageCache(Context context) {
	        return getGDApplication(context).getImageCache();
	    }

	    /**
	     * Return the {@link GDApplication} executors pool.
	     * 
	     * @param context The calling context
	     * @return The executors pool of the current {@link GDApplication}
	     */
	    public static ExecutorService getExecutor(Context context) {
	        return getGDApplication(context).getExecutor();
	    }
}
