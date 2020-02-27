package br.com.softplan.api.domain;

public enum Nacionalidade {
	BRASIL("Brasil"), 
	EUA("EUA"),
	FRANCA("França");
	

	private String label;

	private Nacionalidade(String label) {
	        this.label = label;
	    }

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
