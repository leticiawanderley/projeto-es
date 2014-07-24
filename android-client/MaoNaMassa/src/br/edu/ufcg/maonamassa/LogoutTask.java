package br.edu.ufcg.maonamassa;



import br.edu.ufcg.maonamassa.models.User;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;
import br.edu.ufcg.maonamassa.utils.Routes;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;


class LogoutTask extends AsyncTask<String, Void, String> {
	
	private SessionManager session;
	private Context context;
	private ProgressDialog dialog;
	private WebView wb;
	
	public LogoutTask(SessionManager session, WebView webview, Context context) {
		this.session = session;
		this.context = context;
		this.wb = webview;
	}
	
	@Override
	protected void onPreExecute() {
		 //dialog = ProgressDialog.show(context, "",  "Carregando gostosuras..", true);
	}
	
    @Override
    protected String doInBackground(String... urls) {
		return HttpURLCon.GET(Routes.SERVER_URL + "/" + Routes.LOGOUT_URL 
				+ "?id=" + session.getUserDetails().getId() +"&token="+ session.getUserDetails().getAccessToken());
    }
    
 
    @Override
    protected void onPostExecute(String result) {
    	session.logoutUser();
    	wb.clearHistory();
    	wb.clearCache(true);
    	wb.clearSslPreferences();
    	CookieManager cookieManager = CookieManager.getInstance();
    	cookieManager.removeAllCookie();
    	//dialog.cancel();
    	//Intent intent = new Intent(context, MainActivity.class);
 	    //context.startActivity(intent);
    }
    

    
    
    
	
}

