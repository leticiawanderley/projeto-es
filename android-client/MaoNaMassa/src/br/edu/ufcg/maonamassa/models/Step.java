package br.edu.ufcg.maonamassa.models;

public class Step extends Storable<Step>{
	
	private Long id;
	
	private String description;
	
	private double time;
	
	private Annex annex;
	
	public Step(String description, double time) {
		this.description = description;
		this.time = time;
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

	public void setTime(double time) {
		this.time = time;
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
}