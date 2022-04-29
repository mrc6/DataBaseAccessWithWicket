package com.br.marcob.contato;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Authorization extends PageParameters {
    private long id;
    private String user;
    private String pass;

    public long getId(){ return id; }
    public void setId(long id){ this.id = id; }

    public String getUser(){ return user; }
    public void setUser(String user){ this.user = user; }

    public String getPass(){ return pass; }
    public void setPass(String pass){ this.pass = pass; }

    @Override
    public String toString(){
        return "{Authorization: user='"+user+"' pass='"+pass+"'}";
    }
}
