package com.br.marcob.contato;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationDAO implements Serializable {
    private String LIST_USER_PASS_SQL = "select pass from `authorization`.login where USER_NAME=?";

    private Connection conexao;

    public AuthorizationDAO(Connection conexao) { this.conexao = conexao; }

    public String getPass(String userName) {
        Authorization auth = new Authorization();
        auth.setUser(userName);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexao.prepareStatement(LIST_USER_PASS_SQL);
            ps.setString(1, auth.getUser());
            rs = ps.executeQuery();

            if(rs != null && rs.next()){
                auth.setPass(rs.getString(1));
            }

            return auth.getPass();
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
