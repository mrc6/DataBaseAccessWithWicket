package com.br.marcob.contato;

public enum EstadoCivil {
    SOLTEIRO("Solteiro"), CASADO("Casado"), DIVORCIADO("Divorciado"), VIUVO("Viúvo");

    private String label;

    private EstadoCivil(String label){ this.label = label;}

    public String getLabel(){ return label; }
}
