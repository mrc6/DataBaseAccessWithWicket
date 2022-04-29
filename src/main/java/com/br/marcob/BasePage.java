package com.br.marcob;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class BasePage extends WebPage {
    public BasePage(){
        String userName = (String) getSession().getAttribute("userName");
        if(userName == null){
            setResponsePage(Login.class);
            return;
        }

        add(new Link<Void>("sair") {
            public void onClick(){
                getSession().invalidate();
                setResponsePage(Inicio.class);
            }
        });
    }
}
