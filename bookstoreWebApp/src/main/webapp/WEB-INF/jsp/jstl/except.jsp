<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%--
  Created by IntelliJ IDEA.
  User: ykyoon
  Date: 3/10/14
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title></title>
</head>
<body>
  <ul>
    <li>input value1 : ${value1}</li>
    <li>input value2 : ${value2}</li>
  </ul>
  result = value1 / value2
  <br/>
  <c:catch var="err">
    result =
    <%
      int value1 = (Integer) request.getAttribute("value1");
      int value2 = (Integer) request.getAttribute("value2");

      int result = value1 / value2;
      out.println(String.format("%d", result));
    %>
  </c:catch>
  <c:out value="${err}"/>
</body>
</html>
