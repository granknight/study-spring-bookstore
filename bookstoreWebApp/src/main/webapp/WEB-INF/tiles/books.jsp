<%--
  Created by IntelliJ IDEA.
  User: ykyoon
  Date: 3/10/14
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2>jstl test code 입니다.</h2>
<div class="example-form">
  <h3>c:forEach example code</h3>
  <table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
      <th>Title</th>
      <th>status</th>
      <th>Description</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="book" items="${books}">
      <tr>
        <td>${book.name}</td>
        <c:set var="status" value="일반" />
        <c:choose>
          <c:when test="${book.status eq 'CANRENT'}">
            <c:set var="status" value="일반" />
          </c:when>
          <c:when test="${book.status eq 'RENTNOW'}">
            <c:set var="status" value="대여중" />
          </c:when>
          <c:otherwise>
            <c:set var="status" value="분실중" />
          </c:otherwise>
        </c:choose>
        <td>${status }</td>
        <td>${book.comment}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>