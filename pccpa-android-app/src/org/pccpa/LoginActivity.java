package org.pccpa;

import java.util.Date;
import java.util.List;

import org.pccpa.api.Client;
import org.pccpa.api.Contact;
import org.pccpa.api.Client.Result;
import org.pccpa.api.LoginLog;
import org.pccpa.remind.RemindListActivity;

import com.kull.android.NetworkHelper;
import com.kull.android.SQLiteOrmHelper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.JsResult;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
			"foo@example.com:hello", "bar@example.com:world" };

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		
		SQLiteOrmHelper sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(this);
		NetworkHelper.enableNetwrokOnMainThread();
		try {
			List<LoginLog> loginlogs=sqLiteOrmHelper.select(LoginLog.class, new String[]{"*"}, "", new String[]{},"","","last_login_mis desc",0,1);
		    if(loginlogs.size()>0){
		    	LoginLog loginlog=loginlogs.get(0);
		        Long diff=new Date().getTime()-loginlog.getLast_login_mis();
		        if(diff<1000*60*60*24*7){
		        	Client.CURR_CLIENT=new Client(loginlog.getEid());
		      	   Client.CURR_CLIENT.setContact(sqLiteOrmHelper.load(Contact.class, loginlog.getEid()));
		      	 ContactActivity.CONTACT_ALL=sqLiteOrmHelper.select(Contact.class,
						   new String[]{"*"}
				           ,"areaid = ?"
				           ,new String[]{Client.CURR_CLIENT.getContact().getAreaID()}
						   );
			Intent intent=new Intent(this,ContactActivity.class);
		    startActivity(intent);
			
		        }
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setContentView(R.layout.activity_login);

		
		
		// Set up the login form.
		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.activity_login_etx_loginid);
		mEmailView.setText("lhh");

		mPasswordView = (EditText) findViewById(R.id.activity_login_etx_pwd);
		mPasswordView.setText("0000");
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							//attemptLogin();
							//doLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
						//doLogin();
					}
				});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	
	private boolean doLogin() throws Exception{
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();
		
			Result re=Client.doLogin(mEmail, mPassword,this);
			//Toast.makeText(this, re.getCode()+":"+re.getMsg(), 3000).show();
			if(re.getCode()==0){
				//Toast.makeText(this,"登录成功，系统初始化中，请稍候...", 2000).show();
				 SQLiteOrmHelper sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(this);
				 //sqLiteOrmHelper.createTable(Contact.class);
				   ContactActivity.CONTACT_ALL=sqLiteOrmHelper.select(Contact.class,
						   new String[]{"*"}
				           ,"areaid = ?"
				           ,new String[]{Client.CURR_CLIENT.getContact().getAreaID()}
						   );
			
			}
		    
		
		return true;
		
	}
	
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			//mPasswordView.setError(getString(R.string.error_invalid_password));
			//focusView = mPasswordView;
			//cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			//mEmailView.setError(getString(R.string.error_invalid_email));
			//focusView = mEmailView;
			//cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Result> {
		@Override
		protected Result doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.
			
			Result re=Result.create();
			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return re;
			}

			
            
			// TODO: register the new account here.
			try {
				mEmail = mEmailView.getText().toString();
				mPassword = mPasswordView.getText().toString();
				
				 re=Client.doLogin(mEmail, mPassword,getApplicationContext());
					//Toast.makeText(this, re.getCode()+":"+re.getMsg(), 3000).show();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				re.setCode(1);
				re.setMsg(e.getLocalizedMessage());
			}
			return re;
		}

		@Override
		protected void onPostExecute(final Result success) {
			mAuthTask = null;
			showProgress(false);

			if (success.getCode()==0) {
				finish();
				Toast.makeText(getApplicationContext(), "登录验证成功，缓存通信录数据中...", 3000).show();
				SQLiteOrmHelper sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(getApplicationContext());
				 //sqLiteOrmHelper.createTable(Contact.class);
				   try {
					ContactActivity.CONTACT_ALL=sqLiteOrmHelper.select(Contact.class,
							   new String[]{"*"}
					           ,"areaid = ?"
					           ,new String[]{Client.CURR_CLIENT.getContact().getAreaID()}
							   );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent=new Intent(getApplicationContext(),ContactActivity.class);
			    startActivity(intent);
			} else {
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
				Toast.makeText(getApplicationContext(), "登录失败:"+success.getMsg(), 6000).show();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
