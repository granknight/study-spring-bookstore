<%--
  Created by IntelliJ IDEA.
  User: ykyoon
  Date: 3/10/14
  Time: 10:48 AM
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
  main page
  <div>
    아래에 import된 target page가 표시됩니다.
    주의할점은 Page가 호출되듯이 Controller code가 호출되지 않는다는것입니다.
    <br/>
    <c:import url="importTarget.jsp"/>
  </div>
</body>
</html>
