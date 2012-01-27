<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>maktashaf</title>
</head>
<body>
<p>This is my message: </p>

<table>
  <tr>
    <th>Text</th>
  </tr>
  <c:forEach var="ayah" items="${ayahs}" varStatus="status">
  <tr>
    <td>${ayah.ayahText}</td>
  </tr>
  </c:forEach>

</body>
</html>