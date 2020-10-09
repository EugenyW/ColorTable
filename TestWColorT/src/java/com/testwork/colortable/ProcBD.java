/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testwork.colortable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Вызов хранимой процедуры
 */
public class ProcBD {

    public static String proc_get_info(String param)
            throws SQLException {
        Connection con = null;
        CallableStatement proc = null;
        String out = "";
        try {
            con = ConnectionPool.getInstance().getConnection();
            proc = con.prepareCall("{ call get_info(?) }");
            proc.setString(1, param);
            proc.registerOutParameter(1, Types.VARCHAR);
            proc.execute();
            out = (String) proc.getObject(1);
        } finally {
            try {
                proc.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelColor.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.close();
        }
        return out;
    }
}
