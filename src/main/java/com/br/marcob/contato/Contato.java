package com.br.marcob.contato;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Contato extends PageParameters {
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private EstadoCivil estadoCivil;

    public long getId(){ return id; }
    public void setId(long id){ this.id = id; }

    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome = nome; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    public String getTelefone(){ return telefone; }
    public void setTelefone(String telefone){ this.telefone = telefone; }

    public EstadoCivil getEstadoCivil(){ return estadoCivil; }
    public void setEstadoCivil(EstadoCivil estadoCivil){ this.estadoCivil = estadoCivil; }

    @Override
    public String toString(){
        return "{Contato: nome='"+nome+"' email='"+email+"' telefone='"+telefone+"' estadoCivil='"+estadoCivil.name()+"'}";
    }
}
