package br.edu.ufcg.maonamassa;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class AddIngredientDialog extends DialogFragment{

	@Override
	public AlertDialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(R.layout.dialog_add_ingredient, null))
		// Add action buttons
		.setPositiveButton(R.string.adicionar, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// sign in the user ...
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				AddIngredientDialog.this.getDialog().cancel();
			}
		});      	
		return builder.create();
	}
}

