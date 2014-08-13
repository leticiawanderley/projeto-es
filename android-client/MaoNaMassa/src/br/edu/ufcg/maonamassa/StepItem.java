package br.edu.ufcg.maonamassa;

public class StepItem {
	private String descricao;
	private String tempo;
	public StepItem(String descricao, String tempo) {
		this.descricao = descricao;
		this.tempo = tempo;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
