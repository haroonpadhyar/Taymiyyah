<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<html>
  <head>
    <title>مكتشف</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:if test="${sessionScope.locale eq null or sessionScope.locale.language.equals('en')}">
      <link id="cssLink" href="css/ltr/bootstrap.css" rel="stylesheet" media="screen">
    </c:if>
    <c:if test="${sessionScope.locale ne null and !sessionScope.locale.language.equals('en')}">
      <link id="cssLink" href="css/rtl/bootstrap.css" rel="stylesheet" media="screen">
    </c:if>
    <%--<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>--%>
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
    <script src="javascript/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="javascript/ajax.js" type="text/javascript"></script>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <!--CUSTOM CSS-->
    <style>
      body {
        padding-top: 100px;
      }
    </style>
  </head>

  <body>

    <jsp:include page="pages/header.jsp" />

    <div class="container">
      <p class="h1"><fmt:message key="about" bundle="${msg}"/></p>
      <p class="lead"><fmt:message key="aboutParagraph" bundle="${msg}"/></p>
    </div>

  </body>
</html>

