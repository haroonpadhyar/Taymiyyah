<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <title>مكتشف</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>

  <body>

  <%--ltr--%>
  <c:if test="${sessionScope.locale eq null or sessionScope.locale.language.equals('en')}">
    <jsp:include page="pages/header-ltr.jsp" />
  </c:if>
  <%--ent ltr--%>

  <%--rtl--%>
  <c:if test="${sessionScope.locale ne null and !sessionScope.locale.language.equals('en')}">
    <jsp:include page="pages/header-rtl.jsp" />
  </c:if>
  <%--ent rtl--%>

  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <c:if test="${sessionScope.locale eq null or sessionScope.locale.language.equals('en')}">
  <div class="container" dir="ltr">
  </c:if>

  <c:if test="${sessionScope.locale ne null and !sessionScope.locale.language.equals('en')}">
    <div class="container" dir="rtl">
  </c:if>

    <p class="h1"><fmt:message key="contactUs" bundle="${msg}"/></p>
    <p class="lead"><fmt:message key="contactParagraph" bundle="${msg}"/></p>
  </div>

  </body>
</html>

