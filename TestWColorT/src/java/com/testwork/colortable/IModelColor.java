/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testwork.colortable;

import java.util.ArrayList;
import org.json.JSONArray;

public interface IModelColor {
    ArrayList<CColor> getColorFromBD(int curPage, int pCount);

    JSONArray getJSON();

    Integer getNumberOfRows();

}
