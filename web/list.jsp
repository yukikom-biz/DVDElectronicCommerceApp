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
%>
<%--    We'll see what the output is here--%>
    <p>タイトル: ${items.get(i).getTitle()}</p>
    <p>価値: ${items.get(i).getPrice()}</p>
<%--    <p>${items.get(i).getText()}</p>--%>
    <p>プレイアー: ${items.get(i).getPlayers()}</p>
    <br><br>

 <% }} %>


</body>
</html>
