<%@ page import="java.util.List" %>
<%@ page import="model.ItemBean" %>

<%--
  Created by IntelliJ IDEA.
  User: yuki.komatsu
  Date: 2019-05-23
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ItemList</title>
</head>
<body>
<h2>MB Shop</h2>
<form action="ItemsController" method="get">
    <label for="keywordSearch">Search</label>
    <input type="text" name="keyword" id="keywordSearch">
    <input type="submit" value="Search">
</form>
<%--todo item box--%>

<%
    List items = (List) request.getAttribute("items");
    if (items != null){
        int n = items.size();
        for (int i = 0; i < n; i++) {
            if (items.get(i) instanceof ItemBean) {
                request.setAttribute("item",items.get(i));
            }
%>
<%--    We'll see what the output is here--%>
<p>タイトル: <a href="ItemsController?mode=1&keyword=${item.getTitle()}">${item.getTitle()}</a></p>
    <p>価値: ${item.getPrice()}</p>
<%--    <p>${item.getText()}</p>--%>
    <br><br>

 <% }} %>


</body>
</html>
