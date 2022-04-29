package com.br.marcob;

import com.br.marcob.contato.AuthorizationDAO;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import org.apache.wicket.markup.html.form.*;

import java.sql.Connection;


public class Login extends WebPage {
    public Login(){
        final TextField<String> campoNomeUsuario = new TextField<String>("nomeUsuario", new Model<String>());
        final PasswordTextField campoPassword =new PasswordTextField("password", new Model<String>());

        final Label mensagemErroLogin = new Label("mensagemErroLogin", Model.of("Erro ao realizar login"));
        mensagemErroLogin.setOutputMarkupId(true).setVisible(false);

        Form<String> formularioLogin = new Form<>("formularioLogin"){
            @Override
            public final void onSubmit(){
                String nomeUsuario = campoNomeUsuario.getModelObject();
                String senha = campoPassword.getModelObject();

                Connection conexao = ((WicketApplication) getApplication()).getAuthConnection();
                AuthorizationDAO dao = new AuthorizationDAO(conexao);
                String receivedPass = dao.getPass(nomeUsuario);

                if(receivedPass == null){
                    mensagemErroLogin.setVisible(true);
                } else if(receivedPass.equals(senha)){
                    getSession().setAttribute("userName", nomeUsuario);
                    setResponsePage(Inicio.class);
                } else {
                    mensagemErroLogin.setVisible(true);
                }
            }
        };
        add(mensagemErroLogin, formularioLogin);
        formularioLogin.add(campoNomeUsuario, campoPassword);
    }
}
