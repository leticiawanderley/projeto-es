package br.edu.ufcg.maonamassa.models;

import br.edu.ufcg.maonamassa.utils.Storable;

public class Step extends Storable<Step> {
	
	
	private Long id;
	
	private String description;
	
	private double time;
	
	private Annex annex;
	
	public Step(String description, double tempo) {
		this.description = description;
		this.time = tempo;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public boolean isTimed() {
		return time > 0;
	}

	public Annex getAnnex() {
		return annex;
	}

	public void setAnnex(Annex annex) {
		this.annex = annex;
	}

	public Long getId() {
		return id;
	}	

	@Override
	public String toString() {
		return "Step [id=" + id + ", description=" + description + ", time="
				+ time + ", annex=" + annex + "]";
	}

}
