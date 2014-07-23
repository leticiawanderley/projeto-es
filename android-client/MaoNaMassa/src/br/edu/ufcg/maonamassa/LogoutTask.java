package br.edu.ufcg.maonamassa;



import br.edu.ufcg.maonamassa.models.User;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;
import br.edu.ufcg.maonamassa.utils.Routes;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


class LogoutTask extends AsyncTask<String, Void, String> {
	
	private SessionManager session;
	private Context context;
	private ProgressDialog dialog;
	
	public LogoutTask(SessionManager session, Context context) {
		this.session = session;
		this.context = context;
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
    	//dialog.cancel();
    	//Intent intent = new Intent(context, MainActivity.class);
 	    //context.startActivity(intent);
    }
    

    
    
    
	
}

