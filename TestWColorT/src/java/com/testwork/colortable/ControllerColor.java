package com.testwork.colortable;

/*
 Модуль обработки запросов 
 */
import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;
import org.json.JSONArray;

public class ControllerColor extends HttpServlet {
final int recordour = 3;
    public void doGet(HttpServletRequest req,
            HttpServletResponse res)
            throws ServletException, IOException {

        String page = req.getParameter("page"); // Номер страницы для вывода
        if (page == null || page.isEmpty()) {
            page = "1";// По умолчанию выбирается страница 1
        }
        // Получение JSON с данными
        JSONArray us = ModelColor.getInstance().getColorJSON(Integer.parseInt(page), recordour);

        
        // Расчет оставшихся страниц
        int nOfPages = ModelColor.getInstance().getNumberOfRows() /recordour;

        if (nOfPages % recordour > 0) {
            nOfPages++;
        }

        // Передача параметров
        req.setAttribute("jsoncolor_atr", us);
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", page);
        
        // Отображение страницы
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/color.jsp");
        requestDispatcher.forward(req, res);
    }
}
