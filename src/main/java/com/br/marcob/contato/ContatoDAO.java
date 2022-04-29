package com.br.marcob.contato;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements Serializable {
    public static final String INSERT_SQL = "INSERT INTO CONTATO (NOME, EMAIL, TELEFONE, ESTADO_CIVIL) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE CONTATO SET NOME=?, EMAIL=?, TELEFONE=?, ESTADO_CIVIL=? WHERE ID_CONTATO=?";
    public static final String LIST_SQL = "SELECT ID_CONTATO, NOME, EMAIL, TELEFONE, ESTADO_CIVIL FROM CONTATO WHERE NOME LIKE ?";

    private Connection conexao;

    public ContatoDAO(Connection conexao) { this.conexao = conexao; }

    public void inserir(Contato contato){
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement(INSERT_SQL);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getTelefone());
            ps.setString(4, contato.getEstadoCivil().name());
            ps.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public void atualizar(Contato contato) {
        PreparedStatement p = null;
        try {
            p = conexao.prepareStatement(UPDATE_SQL);
            p.setString(1, contato.getNome());
            p.setString(2, contato.getEmail());
            p.setString(3, contato.getTelefone());
            p.setString(4, contato.getEstadoCivil().name());
            p.setLong(5, contato.getId());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public List<Contato> listarPorNome(String nome) {
        List<Contato> contatos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexao.prepareStatement(LIST_SQL);
            ps.setString(1, nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong(1));
                contato.setNome(rs.getString(2));
                contato.setEmail(rs.getString(3));
                contato.setTelefone(rs.getString(4));
                contato.setEstadoCivil(EstadoCivil.valueOf(rs.getString(5)));
                contatos.add(contato);
            }
            return contatos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
        }

    }
}
