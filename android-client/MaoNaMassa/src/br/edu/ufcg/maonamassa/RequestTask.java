package br.edu.ufcg.maonamassa;

import java.net.URI;

import android.os.AsyncTask;
import android.widget.Toast;
import br.edu.ufcg.maonamassa.utils.HttpURLCon;

public class RequestTask extends AsyncTask<String, Void, String> {

	Toast ok;
	
	public RequestTask(Toast ok) {
		this.ok = ok;
	}

	@Override
	protected void onPreExecute() {
		
	}

	@Override
	protected String doInBackground(String... urls) {
		return HttpURLCon.GET1(urls[0]);
	}

	@Override
	protected void onPostExecute(String result) {
		ok.show();
	}
}
