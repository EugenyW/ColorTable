<%-- 
    View Colors Table
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Color`s Table</title>
    </head>
    <body>
        <h1>Color`s table</h1>
        <h2>
            <span>OUT TEXT:  </span><span id="outtxt">    </span>
        </h2>
        <table id="blueRect"  border="1"  width="400px" >
            <%--
                Отображение таблицы цветов
            --%>
            <%
                // Получение JSON 
                JSONArray json_color = (JSONArray) request.getAttribute("jsoncolor_atr");

                // Отрисовка таблицы
                if (json_color != null && json_color.length() != 0) {

                    for (int i = 0; i < json_color.length(); i++) {
                        JSONObject jo = json_color.getJSONObject(i);
                        out.println("<tr>");
                        out.println("<td>" + jo.getString("color_number") + "</td><td>" + jo.optString("name") + "</td>");
                        out.println("</tr>");
                    }

                } else {
                    out.println("<p>Нет данных из БД</p>");
                }
            %>
        </table>
        <!-----------
        Кнопки навигации
        
        --------------->

            <ul >
                <c:if test="${currentPage != 1}">
                    <li><a class="page-link"
                                             href="color?showRecordOnPage=${showRecordOnPage}&page=${currentPage-1}">предыдущая страница</a>
                    </li>
                </c:if>

                <c:if test="${currentPage lt noOfPages}">
                    <li><a class="page-link"
                                             href="color?showRecordOnPage=${showRecordOnPage}&page=${currentPage+1}">следующая страница</a>
                    </li>
                </c:if>


            </ul>
  

        <script>
            // Функция отображения строки из таблицы с подсвечиванием фона
            function setColor(e) {

                with (event.target || event.srcElement) {
                    var row = parentNode.rowIndex;
                    var column = cellIndex + 1;
                }

                e = event || window.event;

                if (e.srcElement.cellIndex !== 1)// реакция только на ячейку таблицы с именем
                    return;
                // Формирование строки с выбранными данными из таблицы
                var txt = e.currentTarget.rows [row].cells [0].innerHTML;
                var colorname = e.currentTarget.rows [row].cells [1].innerHTML.replace(/ /g, '');

                txt = txt + "," + colorname;

                var ot = document.getElementById("outtxt");
                ot.style.backgroundColor = colorname;
                ot.innerText = txt;
            }

            // Регистрация слушателей событий
            var blueRect = document.getElementById("blueRect");
            blueRect.addEventListener("mouseover", setColor); // наведение курсора
            blueRect.addEventListener("onclick", setColor);   // клик левой кнопки мышки

        </script>
    </body>
</html>
