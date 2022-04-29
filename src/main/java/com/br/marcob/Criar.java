package com.br.marcob;

import com.br.marcob.contato.Contato;
import com.br.marcob.contato.ContatoDAO;
import com.br.marcob.contato.EstadoCivil;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.sql.Connection;
import java.util.Arrays;

public class Criar extends BasePage {
    public Criar(){this(new Contato());}

    public Criar(Contato contato) {
        add(new Label("titulo", "Criação de Contato"));
        CompoundPropertyModel<Contato> modelContato = new CompoundPropertyModel<>(contato);

        // Criando um formulário
        Form<Contato> form = new Form<>("formularioContato", modelContato){
            @Override
            public void onSubmit(){
                Contato contatoSubmetido = getModelObject();
                salvar(contatoSubmetido);
                onSettingsChanged();
            }
        };
        add(form);
        TextField<String> inputNome = new TextField<>("nome");
        TextField<String> inputEmail = new TextField<>("email");
        TextField<String> inputTelefone = new TextField<>("telefone");
        DropDownChoice<EstadoCivil> comboBoxEstadoCivil = new DropDownChoice<>(
                "estadoCivil",
                Arrays.asList(EstadoCivil.values()),
                new IChoiceRenderer<EstadoCivil>() {
                    @Override
                    public Object getDisplayValue(EstadoCivil estadoCivil) {
                        return estadoCivil.getLabel();
                    }
                    @Override
                    public String getIdValue(EstadoCivil estadoCivil, int id){
                        return estadoCivil.name();
                    }
                });
        form.add(inputNome, inputEmail, inputTelefone, comboBoxEstadoCivil);

        // Validadores dos campos
        inputNome.setLabel(Model.of("Nome do Contato")).setRequired(true).add(StringValidator.maximumLength(100));
        inputEmail.setLabel(Model.of("Email do Contato")).setRequired(true).add(EmailAddressValidator.getInstance());

        // Feedback da mensagem de erro, caso aconteça
        add(new FeedbackPanel("feedbackMessage", new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR)));
    }

    protected void salvar(Contato contatoSubmetido) {
        Connection conexao = ((WicketApplication) getApplication()).getConexao();
        ContatoDAO dao = new ContatoDAO(conexao);
        dao.inserir(contatoSubmetido);
    }

    protected void onSettingsChanged(){
        setResponsePage(Inicio.class);
    }
}
