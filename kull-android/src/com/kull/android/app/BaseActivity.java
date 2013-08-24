package com.kull.android.app;





import com.kull.android.Const;
import com.kull.android.RHelper;

import com.kull.android.widget.ActionBar;
import com.kull.android.widget.ActionBar.OnActionBarListener;
import com.kull.android.widget.ActionBar.Type;
import com.kull.android.widget.ActionBarHost;
import com.kull.android.widget.ActionBarItem;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;


public class BaseActivity extends Activity implements ActionBarActivity {
	  private static final String LOG_TAG = BaseActivity.class.getSimpleName();

	    private boolean mDefaultConstructorUsed = false;

	    private Type mActionBarType;
	    private ActionBarHost mActionBarHost;

	    /**
	     * <p>
	     * Default constructor.
	     * </p>
	     * <p>
	     * <em><strong>Note</strong>: This constructor should never be used manually. 
	     * In order to instantiate an Activity you should let the Android system do 
	     * it for you by calling startActivity(Intent)</em>
	     * </p>
	     */
	    public BaseActivity() {
	        this(Type.Normal);
	        mDefaultConstructorUsed = true;
	    }

	    /**
	     * <p>
	     * Create a new Activity with an {@link ActionBar} of the given type.
	     * </p>
	     * <p>
	     * <em><strong>Note</strong>: This constructor should never be used manually. 
	     * In order to instantiate an Activity you should let the Android system do 
	     * it for you by calling startActivity(Intent)</em>
	     * </p>
	     * 
	     * @param actionBarType The {@link ActionBar.Type} for this Activity
	     */
	    public BaseActivity(ActionBar.Type actionBarType) {
	        super();
	        mActionBarType = actionBarType;
	    }

	    @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	        ensureLayout();
	        super.onRestoreInstanceState(savedInstanceState);
	    }

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        if (mDefaultConstructorUsed) {
	            // HACK cyril: This should have been done in the default
	            // constructor. Unfortunately, the getApplication() method returns
	            // null there. Hence, this has to be done here.
	            if (getClass().equals(getKULLApplication().getHomeActivityClass())) {
	                mActionBarType = Type.Dashboard;
	            }
	        }
	    }

	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        ensureLayout();
	    }

	    /**
	     * The current {@link ActionBar.Type} of the hosted {@link ActionBar}
	     * 
	     * @return The current {@link ActionBar.Type} of the hosted
	     *         {@link ActionBar}
	     */
	    public ActionBar.Type getActionBarType() {
	        return mActionBarType;
	    }

	    public int createLayout() {
	        switch (mActionBarType) {
	            case Dashboard:
	                return RHelper.layout.kull_content_dashboard;
	            case Empty:
	                return RHelper.layout.kull_content_empty;
	            case Normal:
	            default:
	                return RHelper.layout.kull_content_normal;
	        }
	    }

	    /**
	     * Call this method to ensure a layout has already been inflated and
	     * attached to the top-level View of this Activity.
	     */
	    protected void ensureLayout() {
	        if (!verifyLayout()) {
	            setContentView(createLayout());
	        }
	    }

	    /**
	     * Verify the given layout contains everything needed by this Activity. A
	     * BaseActivity, for instance, manages an {@link ActionBarHost}. As a result
	     * this method will return true of the current layout contains such a
	     * widget.
	     * 
	     * @return true if the current layout fits to the current Activity widgets
	     *         requirements
	     */
	    protected boolean verifyLayout() {
	        return mActionBarHost != null;
	    }

	    public KULLApplication getKULLApplication() {
	        return (KULLApplication) getApplication();
	    }

	    @Override
	    public void onContentChanged() {
	        super.onContentChanged();

	        onPreContentChanged();
	        onPostContentChanged();
	    }

	    public void onPreContentChanged() {
	        mActionBarHost = (ActionBarHost) findViewById(RHelper.id.kull_action_bar_host);
	        if (mActionBarHost == null) {
	            throw new RuntimeException("Your content must have an ActionBarHost whose id attribute is RHelper.id.kull_action_bar_host");
	        }
	        mActionBarHost.getActionBar().setOnActionBarListener(mActionBarListener);
	    }

	    public void onPostContentChanged() {

	        boolean titleSet = false;

	        final Intent intent = getIntent();
	        if (intent != null) {
	            String title = intent.getStringExtra(ActionBarActivity.ACTION_BAR_TITLE);
	            if (title != null) {
	                titleSet = true;
	                setTitle(title);
	            }
	        }

	        if (!titleSet) {
	            // No title has been set via the Intent. Let's look in the
	            // ActivityInfo
	            try {
	                final ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), 0);
	                if (activityInfo.labelRes != 0) {
	                    setTitle(activityInfo.labelRes);
	                }
	            } catch (NameNotFoundException e) {
	                // Do nothing
	            }
	        }

	        final int visibility = intent.getIntExtra(ActionBarActivity.ACTION_BAR_VISIBILITY, View.VISIBLE);
	        getActionBar_().setVisibility(visibility);
	    }

	    @Override
	    public void setTitle(CharSequence title) {
	        getActionBar_().setTitle(title);
	    }

	    @Override
	    public void setTitle(int titleId) {
	        setTitle(getString(titleId));
	    }

	    public ActionBar getActionBar_() {
	        ensureLayout();
	       return mActionBarHost.getActionBar();
	       //return null;
	    }

	    public ActionBarItem addActionBarItem(ActionBarItem item) {
	        return getActionBar_().addItem(item);
	    }

	    public ActionBarItem addActionBarItem(ActionBarItem item, int itemId) {
	        return getActionBar_().addItem(item, itemId);
	    }

	    public ActionBarItem addActionBarItem(ActionBarItem.Type actionBarItemType) {
	        return getActionBar_().addItem(actionBarItemType);
	    }

	    public ActionBarItem addActionBarItem(ActionBarItem.Type actionBarItemType, int itemId) {
	        return getActionBar_().addItem(actionBarItemType, itemId);
	    }

	    public FrameLayout getContentView() {
	        ensureLayout();
	        return mActionBarHost.getContentView();
	    }

	    /**
	     * <p>
	     * Set the activity content from a layout resource. The resource will be
	     * inflated, adding all top-level views to the activity.
	     * </p>
	     * <p>
	     * This method is an equivalent to setContentView(int) that automatically
	     * wraps the given layout in an {@link ActionBarHost} if needed..
	     * </p>
	     * 
	     * @param resID Resource ID to be inflated.
	     * @see #setActionBarContentView(View)
	     * @see #setActionBarContentView(View, LayoutParams)
	     */
	    public void setActionBarContentView(int resID) {
	        final FrameLayout contentView = getContentView();
	        contentView.removeAllViews();
	        LayoutInflater.from(this).inflate(resID, contentView);
	    }

	    /**
	     * <p>
	     * Set the activity content to an explicit view. This view is placed
	     * directly into the activity's view hierarchy. It can itself be a complex
	     * view hierarchy.
	     * </p>
	     * <p>
	     * This method is an equivalent to setContentView(View, LayoutParams) that
	     * automatically wraps the given layout in an {@link ActionBarHost} if
	     * needed.
	     * </p>
	     * 
	     * @param view The desired content to display.
	     * @param params Layout parameters for the view.
	     * @see #setActionBarContentView(View)
	     * @see #setActionBarContentView(int)
	     */
	    public void setActionBarContentView(View view, LayoutParams params) {
	        final FrameLayout contentView = getContentView();
	        contentView.removeAllViews();
	        contentView.addView(view, params);
	    }

	    /**
	     * <p>
	     * Set the activity content to an explicit view. This view is placed
	     * directly into the activity's view hierarchy. It can itself be a complex
	     * view hierarchy.
	     * </p>
	     * <p>
	     * This method is an equivalent to setContentView(View) that automatically
	     * wraps the given layout in an {@link ActionBarHost} if needed.
	     * </p>
	     * 
	     * @param view The desired content to display.
	     * @see #setActionBarContentView(int)
	     * @see #setActionBarContentView(View, LayoutParams)
	     */
	    public void setActionBarContentView(View view) {
	        final FrameLayout contentView = getContentView();
	        contentView.removeAllViews();
	        contentView.addView(view);
	    }

	    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
	        return false;
	    }

	    private OnActionBarListener mActionBarListener = new OnActionBarListener() {
	        public void onActionBarItemClicked(int position) {
	            if (position == OnActionBarListener.HOME_ITEM) {

	                final KULLApplication app = getKULLApplication();
	                switch (mActionBarType) {
	                    case Normal:
	                        final Class<?> klass = app.getHomeActivityClass();
	                        if (klass != null && !klass.equals(BaseActivity.this.getClass())) {
	                            if (Const.INFO_LOGS_ENABLED) {
	                                Log.i(LOG_TAG, "Going back to the home activity");
	                            }
	                            Intent homeIntent = new Intent(BaseActivity.this, klass);
	                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                            startActivity(homeIntent);
	                        }
	                        break;
	                    case Dashboard:
	                        final Intent appIntent = app.getMainApplicationIntent();
	                        if (appIntent != null) {
	                            if (Const.INFO_LOGS_ENABLED) {
	                                Log.i(LOG_TAG, "Launching the main application Intent");
	                            }
	                            startActivity(appIntent);
	                        }
	                        break;
	                }

	            } else {
	                if (!onHandleActionBarItemClick(getActionBar_().getItem(position), position)) {
	                    if (Const.WARNING_LOGS_ENABLED) {
	                        Log.w(LOG_TAG, "Click on item at position " + position + " dropped down to the floor");
	                    }
	                }
	            }
	        }
	    };
}
