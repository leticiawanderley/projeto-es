package br.edu.ufcg.maonamassa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TelaBoasVindasActivity extends Activity implements Runnable {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Handler h = new Handler();
		h.postDelayed(this, 3000);//aqui � definido o delay do handler em milisegundos
	}

	@Override
	public void run(){
		startActivity(new Intent(this, MainActivity.class));//aqui � iniciada nossa 2 activity
		finish();//aqui � chamado o m�todo finish pra finalizar a activity atual no caso SplashScreen
	}
}