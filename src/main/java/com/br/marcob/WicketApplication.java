package com.br.marcob;

import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.br.marcob.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private Connection conexao;
	private Connection authConnection;
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Inicio.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		conexao = criaConexao("agenda");
		authConnection = criaConexao("authorization");

		// needed for the styling used by the quickstart
		getCspSettings().blocking()
			.add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
			.add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
			.add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");

		// add your configuration here
	}

	private Connection criaConexao(String dataBase){
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:1521/"+dataBase+"?useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root",
					"#M169870m"
			);
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Connection getConexao(){ return conexao; }

	public Connection getAuthConnection(){ return authConnection; }
}
