package com.br.marcob;

import com.br.marcob.contato.Contato;
import com.br.marcob.contato.ContatoDAO;
import org.apache.wicket.markup.html.basic.Label;

import java.sql.Connection;

public class Editar extends Criar {
    public Editar(Contato contato){
        super(contato);
        replace(new Label("titulo", "Edição de Contato"));
    }

    @Override
    protected void salvar(Contato contatoSubmetido) {
        Connection conexao = ((WicketApplication) getApplication()).getConexao();
        ContatoDAO dao = new ContatoDAO(conexao);
        dao.atualizar(contatoSubmetido);
    }
}
