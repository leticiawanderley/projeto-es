package br.edu.ufcg.maonamassa;

import br.edu.ufcg.maonamassa.models.Step;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddStepDialog extends DialogFragment{

	public interface StepDialogListener {
		void onDialogPositiveClick(DialogFragment dialog, final Step step);
		void onDialogNegativeClick(DialogFragment dialog);
	}

	StepDialogListener mListener2;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			mListener2 = (StepDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement StepDialogListener");
		}
	}

	@Override
	public AlertDialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final View myView = inflater.inflate(R.layout.dialog_add_step, null);
     	final EditText desc = (EditText) myView.findViewById(R.id.new_step_description);
	    final EditText time = (EditText) myView.findViewById(R.id.new_step_time);
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(myView)
				// Add action buttons
				.setPositiveButton(R.string.adicionar,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
									mListener2.onDialogPositiveClick(AddStepDialog.this, makeStep(desc, time));			
							}

							
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mListener2.onDialogNegativeClick(AddStepDialog.this);
							}
						});
		return builder.create();
	}
	
	private Step makeStep(EditText desc, EditText time) {
		String descricao = desc.getText().toString();
		if(time.getText().toString().length() > 0){
			Double tempo = Double.valueOf(time.getText().toString());
			if(descricao.length() > 0 && tempo >0){
				return new Step(descricao, tempo);
			}
		}
		if(descricao.length()>0){
			return new Step(descricao, 0);
		}
			
		  
		
		
		return null;
	}
}

