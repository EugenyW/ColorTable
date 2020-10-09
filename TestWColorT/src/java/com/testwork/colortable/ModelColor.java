package com.testwork.colortable;

/*
 Функциональны модуль работы с данными базы данных
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelColor implements IModelColor {

    final static String JSON_COLORNUMBER = "color_number";
    final static String JSON_COLORNAME = "name";

    private static ModelColor instance = new ModelColor();

    private List<CColor> model;

    public static ModelColor getInstance() {
        return instance;
    }

    private ModelColor() {
        model = new ArrayList<>();
    }

    public void add(CColor color) {
        model.add(color);
    }

    public List<CColor> listColor() {

        return model;
    }

    /*
     Функция  получения JSON Array  
     */
    @Override
    public JSONArray getJSON() {
        JSONArray list = new JSONArray();

        for (CColor color : model) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put(JSON_COLORNUMBER, color.GetId());
            jsonObject.put(JSON_COLORNAME, color.GetName());
            list.put(jsonObject);

        }
        return list;
    }

    /*
     Функция получения данных с цветами из БД
     */
    public JSONArray getColorJSON(int curPage, int pCount) {
        model.clear();
        model = getColorFromBD(curPage, pCount);
        return getJSON();
    }

    /*
    получение коллекции цветов из Базы данных
     */
    @Override
    public ArrayList<CColor> getColorFromBD(int curPage, int pCount) {
        ArrayList<CColor> ar = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int pStartId = curPage * pCount - pCount;
        // Соединение с базой через пул соединений
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            String selectSQL = "SELECT * FROM TSVETA WHERE id > ? LIMIT ?";
            statement = con.prepareStatement(selectSQL);

            statement.setInt(1, pStartId);
            statement.setInt(2, pCount);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ar.add(new CColor(
                        resultSet.getString(2),
                        resultSet.getString(3)));

            }
        } catch (Exception ex) {
            Logger.getLogger(ModelColor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelColor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ar;
    }

    @Override
    public Integer getNumberOfRows() {

        ResultSet resultSet = null;
        int Count = 0;
        // Соединение с базой через пул соединений
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            String selectSQL = "SELECT COUNT(id) AS COUNT_COLOR FROM TSVETA";
            Statement statement = con.createStatement();

            resultSet = statement.executeQuery(selectSQL);
            resultSet.next();
            Count = resultSet.getInt("COUNT_COLOR");

        } catch (Exception ex) {
            Logger.getLogger(ModelColor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelColor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return Count;

    }

}
