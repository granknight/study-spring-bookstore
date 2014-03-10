<%--
  Created by IntelliJ IDEA.
  User: ykyoon
  Date: 3/10/14
  Time: 9:59 AM
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
    <li>name : ${person.name}</li>
    <li>age : ${person.age}</li>
    <li>name : <c:out value="${person.name}" default="ABC"/></li>
    <li>age : <c:out value="${person.wrong}" default="empty name"/> </li>
  </ul>
</body>
</html>
