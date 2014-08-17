package br.edu.ufcg.maonamassa;

public class StepItem {
	private String descricao;
	private int tempo;
	public StepItem(String descricao, int tempo) {
		this.descricao = descricao;
		this.tempo = tempo;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
