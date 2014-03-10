<%--
  Created by IntelliJ IDEA.
  User: ykyoon
  Date: 3/10/14
  Time: 10:25 AM
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
  <c:if test="${person.married}">
    결혼했습니다.
  </c:if>
  <br/>
  <c:choose>
    <c:when test="${person.hasChildren}">
      아이가 있습니다.
    </c:when>
    <c:otherwise>
      아이가 없습니다.
    </c:otherwise>
  </c:choose>
</body>
</html>
