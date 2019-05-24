<%--
  Created by IntelliJ IDEA.
  User: yuki.komatsu
  Date: 2019-05-23
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>Welcome shopping!</title>
</head>
<body>
<jsp:include page="/menu.jsp"/><br>
<h3>The current cart.</h3>

<c:if test="${empty cart.items}">
  now the cart is empty.
</c:if>

<c:if test="${not empty cart.items}">
  <table border="1">
    <tr><td>Title</td>><td>Price</td><tb>Quantity</tb><tb>Total</tb><tb>Delete</tb></tr>

    <c:forEach items="${cart.items}" var="item">
    <tr>
      <td align="center">${item.value.title}</td>
      <td align="center">${item.value.price}円</td>
      <td align="center">${item.value.quantity}</td>
      <td align="center">${item.value.price * item.value.quantity}円</td>
    <td>
    <form action="/mbshop/CartController?mode=3&id=${item.value.id}" method="post">
      <input type="hidden" name="name" value="${item.value.code}">
      <input type="submit" value="delete">
    </form>i
    </td>
    </tr>
    </c:forEach>
    <tr><td align="right" colspan="5"></td></tr>
  </table>

  <form action="/mbshop/OrdersController?mode=1" method="post">
    <input type="submit" value="Order">
  </form>
</c:if>
</body>
</html>
