/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ss;


import ss.exception.StatementNotNullException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author amit
 */
public class Ss{

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    private Ss() {
    }

    public static Ss getInstance() {
        return new Ss();
    }
    //Used For Connect to DB

    public Connection getConnection(String url, String user, String pass) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }

    //Use for getting Statement Object
    public Statement getStatment(Connection con) throws SQLException {
        stmt = con.createStatement();
        return stmt;
    }
    //For Reterival

    public ResultSet getData(Statement st, String sql) throws SQLException, StatementNotNullException {
        if (st != null) {
            rs = st.executeQuery(sql);

        } else {
            throw new StatementNotNullException();
        }

        return rs;

    }
    //For Insert/Delete/Update

    public void setData(Statement st, String sql) throws SQLException, StatementNotNullException {
        if (st != null) {
            st.executeUpdate(sql);
        } else {
            throw new StatementNotNullException();
        }
    }
    public void close() throws SQLException
    {
        stmt.close();
        con.close();
    }
}

