package com.br.marcob;

import com.br.marcob.contato.Contato;
import com.br.marcob.contato.ContatoDAO;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import java.util.List;

public class Pesquisar extends BasePage {

    public Pesquisar(){
        Form<String> formularioPesquisa = new Form<>("formularioPesquisa");
        add(formularioPesquisa);

        final TextField<String> pesquisaNome = new TextField<>("pesquisaNome", new Model<String>());
        formularioPesquisa.add(pesquisaNome);

        final WebMarkupContainer containerResultados = new WebMarkupContainer("divResultados");
        containerResultados.setVisible(false);
        containerResultados.setOutputMarkupPlaceholderTag(true);
        add(containerResultados);

        final PropertyListView<Contato> listaResultados = new PropertyListView<Contato>("contatos", new ListModel<Contato>()) {
            @Override
            protected void populateItem(ListItem<Contato> listItem) {
                listItem.add(new Label("nome"));
                listItem.add(new Label("email"));
                listItem.add(new Label("telefone"));
                listItem.add(new Label("estadoCivil"));
                listItem.add(new Link<Void>("linkEditar") {
                    public void onClick(){
                        setResponsePage(new Editar(listItem.getModelObject())); // not work
                    }
                });
            }
        };
        containerResultados.add(listaResultados);

        AjaxButton botaoPesquisar = new AjaxButton("botaoPesquisar", formularioPesquisa) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                String nomeAPesquisar = pesquisaNome.getConvertedInput();
                List<Contato> contatos = dao().listarPorNome(nomeAPesquisar);
                listaResultados.setDefaultModelObject(contatos);
                containerResultados.setVisible(true);
                target.add(containerResultados);
            }
        };
        formularioPesquisa.add(botaoPesquisar);
    }

    private ContatoDAO dao(){
        return new ContatoDAO(((WicketApplication) getApplication()).getConexao());
    }
}
