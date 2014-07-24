package br.edu.ufcg.maonamassa;

import java.io.FileOutputStream;

import br.edu.ufcg.maonamassa.utils.Routes;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends ActionBarActivity {

	
	SessionManager session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		 
		 session = new SessionManager(getApplicationContext());
		 
		 final WebView webview = (WebView) findViewById(R.id.webView1);
		 if(!session.isLoggedIn()) {
			 webview.setWebViewClient(new WebViewClient() {
			        
				 
				 @Override
				    public boolean shouldOverrideUrlLoading(WebView view, String url)
				    {
			            if(url.contains("authorized")){
			            	Log.v("meu", url);
			            	
			            	String[] peaces = url.split("\\?");
			            	String[] args = peaces[1].split("&");
			            	String token = args[1].split("=")[1];
			            	String id = args[2].split("=")[1];
			            	new GetUserInfoTask(session, id, token).execute();
			            	Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			        	    startActivity(intent);
			            }
				                 
				        return false;
				    }
	
				   
			    });
			 webview.getSettings().setJavaScriptEnabled(true);
			 webview.loadUrl(Routes.SERVER_URL + "/" + Routes.LOGIN_ROUTE);
		 } else {
			new LogoutTask(session, webview, getApplicationContext()).execute();
         	
		 }
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
