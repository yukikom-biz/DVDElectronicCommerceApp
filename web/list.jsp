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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="template/nav.jsp"/>
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
<p>タイトル: ${item.getTitle()}</p>
<p>価値: ${item.getPrice()}</p>
<%--    <p>${items.get(i).getText()}</p>--%>
<p>プレイアー: ${item.getPlayers()}</p>
<form action="CartController?mode=1" method="post">
    数量:<input type="hidden" name="id" value="${item.getId()}">
    <select name="quantity"><option value="1">1</option><option value="2">2</option><option value="3">3</option></select>
    <input type="submit" value="カートに追加">
</form>
<br><br>

<% }} %>


</body>
</html>