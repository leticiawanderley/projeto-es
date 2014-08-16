package br.edu.ufcg.maonamassa;

import java.lang.reflect.Modifier;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddIngredientDialog extends DialogFragment {
	public interface IngredientDialogListener {
		void onDialogPositiveClick(DialogFragment dialog, final String ingrediente);

		void onDialogNegativeClick(DialogFragment dialog);
	}

	// Use this instance of the interface to deliver action events
	IngredientDialogListener mListener;
	// Override the Fragment.onAttach() method to instantiate the
	// NoticeDialogListener
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			mListener = (IngredientDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement NoticeDialogListener");
		}
	}

	@Override
	public AlertDialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final View myView = inflater.inflate(R.layout.dialog_add_ingredient, null);
	    final EditText desc = (EditText) myView.findViewById(R.id.new_ingrediente_description);
	    final Spinner medida = (Spinner) myView.findViewById(R.id.nova_medida);
	    final EditText unit = (EditText) myView.findViewById(R.id.quantidade);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
	            R.array.medidas_spinner, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    medida.setAdapter(adapter);
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(myView)
				// Add action buttons
				.setPositiveButton(R.string.adicionar,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								mListener.onDialogPositiveClick(AddIngredientDialog.this, 
										makeIngredient(unit, medida, desc));
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mListener.onDialogNegativeClick(AddIngredientDialog.this);
							}
						});
		return builder.create();
	}
	
	private String makeIngredient(EditText unt, Spinner medida, EditText desc){
		return unt.getText().toString() + " " + medida.getSelectedItem().toString() + " de "+ desc.getText().toString();
	}
}
