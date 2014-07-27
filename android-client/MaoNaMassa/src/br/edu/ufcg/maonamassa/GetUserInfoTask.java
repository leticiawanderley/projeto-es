package br.edu.ufcg.maonamassa;

import android.os.AsyncTask;
import br.edu.ufcg.maonamassa.models.User;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;
import br.edu.ufcg.maonamassa.utils.Routes;

class GetUserInfoTask extends AsyncTask<String, Void, String> {

	private SessionManager session;
	private final String ID;
	private final String TOKEN;

	public GetUserInfoTask(SessionManager session, String ID, String TOKEN) {
		this.session = session;
		this.ID = ID;
		this.TOKEN = TOKEN;
	}

	@Override
	protected String doInBackground(String... urls) {
		return HttpURLCon.GET(Routes.SERVER_URL + "/me?id=" + ID + "&token="
				+ TOKEN);
	}

	@Override
	protected void onPostExecute(String result) {
		User user = new User(null, null, null, null);
		// Log.d("MEU", result);
		user = user.desjsonify(result);
		session.createLoginSession(user.getId(), user.getName(),
				user.getEmail(), user.getPhoto(), TOKEN);
	}

}
