<%--
  Created by IntelliJ IDEA.
  User: ykyoon
  Date: 3/10/14
  Time: 10:45 AM
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
<div class="example-form">
  <fieldset>
    <legend>FMT examples - Date</legend>
    <ul>
      <li><fmt:formatDate value="${date}" type="DATE" pattern="yyyy/MM/dd"/></li>
      <li><fmt:formatDate value="${date}" type="DATE" pattern="yyyy년 M월 dd일"/></li>
      <li><fmt:formatDate value="${date}" type="DATE" pattern="yyyy-MM-dd"/></li>
    </ul>
  </fieldset>
  <br/>
  <fieldset>
    <legend>FMT example - Number</legend>
    <ul>
      <li>orginal : ${number}</li>
      <li><fmt:formatNumber value="${number}" groupingUsed="true" currencySymbol=","/></li>
      <li><fmt:formatNumber value="${number}" minFractionDigits="5"/></li>
      <li><fmt:formatNumber value="${number}" type="CURRENCY"/></li>
      <li><fmt:formatNumber value="234.3" pattern="△#,##0.00; ▼#,##0.00"/></li>
      <li><fmt:formatNumber value="-1234.56" pattern="△#,##0.00; ▼#,##0.00"/></li>
      <li><fmt:formatNumber value="0.99" type="percent"/></li>
    </ul>
  </fieldset>
</div>
</body>
</html>
