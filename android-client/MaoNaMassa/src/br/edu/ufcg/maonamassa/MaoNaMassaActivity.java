package br.edu.ufcg.maonamassa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.Step;

@SuppressLint("NewApi")
public class MaoNaMassaActivity extends Activity implements
		FragmentManager.OnBackStackChangedListener {

	private Handler mHandler = new Handler();
	private boolean mShowingBack = false;
	private Recipe recipe;
	private Iterator<Step> steps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_mao_na_massa);

		Intent i = getIntent();
		recipe = new Recipe(0L, "NULL", null);
		recipe = recipe.desjsonify(i.getStringExtra("Recipe"));
		List<Step> lista = recipe.getSteps();
		Collections.reverse(lista);
		steps = lista.iterator();

		CardFragment fragment = new CardFragment();
		if (steps.hasNext())
			fragment.setStep(steps.next());

		if (savedInstanceState == null) {
			// If there is no saved instance state, add a fragment representing
			// the
			// front of the card to this activity. If there is saved instance
			// state,
			// this fragment will have already been added to the activity.
			getFragmentManager().beginTransaction()
					.add(R.id.container_maonamassa, fragment).commit();
		} else {
			mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
		}

		// Monitor back stack changes to ensure the action bar shows the
		// appropriate
		// button (either "photo" or "info").
		getFragmentManager().addOnBackStackChangedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		/*
		 * // Add either a "photo" or "finish" button to the action bar,
		 * depending on which page // is currently selected. MenuItem item =
		 * menu.add(Menu.NONE, R.id.action_flip, Menu.NONE, mShowingBack ?
		 * R.string.action_photo : R.string.action_info);
		 * item.setIcon(mShowingBack ? R.drawable.ic_action_search :
		 * R.drawable.ic_action_search);
		 * item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		 */
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_flip:
			flipCard();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void flipCard() {
		//if (mShowingBack) {
			//getFragmentManager().popBackStack();
	//		return;
		//}

		// Flip to the back.

		mShowingBack = true;

		CardFragment fragment = new CardFragment();
		if (steps.hasNext())
			fragment.setStep(steps.next());

		// Create and commit a new fragment transaction that adds the fragment
		// for the back of
		// the card, uses custom animations, and is part of the fragment
		// manager's back stack.

		getFragmentManager().beginTransaction()

		// Replace the default fragment animations with animator resources
		// representing
		// rotations when switching to the back of the card, as well as animator
		// resources representing rotations when flipping back to the front
		// (e.g. when
		// the system Back button is pressed).
				.setCustomAnimations(R.animator.card_flip_right_in,
						R.animator.card_flip_right_out,
						R.animator.card_flip_left_in,
						R.animator.card_flip_left_out)

				// Replace any fragments currently in the container view with a
				// fragment
				// representing the next page (indicated by the just-incremented
				// currentPage
				// variable).
				.replace(R.id.container_maonamassa, fragment)

				// Add this transaction to the back stack, allowing users to
				// press Back
				// to get to the front of the card.
				.addToBackStack(null)

				// Commit the transaction.
				.commit();

		// Defer an invalidation of the options menu (on modern devices, the
		// action bar). This
		// can't be done immediately because the transaction may not yet be
		// committed. Commits
		// are asynchronous in that they are posted to the main thread's message
		// loop.
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				invalidateOptionsMenu();
			}
		});
	}

	@Override
	public void onBackStackChanged() {
		mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

		// When the back stack changes, invalidate the options menu (action
		// bar).
		invalidateOptionsMenu();
	}

	/**
	 * A fragment representing the front of the card.
	 */
	@SuppressLint("ValidFragment")
	public class CardFragment extends Fragment {

		Step step;

		public void setStep(Step step) {
			this.step = step;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_card_front,
					container, false);
			TextView t = (TextView) rootView.findViewById(R.id.nome_etapa);
			Button b = (Button) rootView.findViewById(R.id.button_next_step);
			if (step != null)
				b.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						flipCard();
					}
				});
			else {
				b.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MaoNaMassaActivity.this, MainActivity.class);
						startActivity(intent);	
					}
				});
			}
			String title = step != null ? step.getDescription() : "";
			String titleButton = step != null ? "Próxima" : "Terminar!";
			t.setText(title);
			b.setText(titleButton);
			return rootView;
		}
	}

}