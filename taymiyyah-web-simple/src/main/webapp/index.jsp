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
    <script src="javascript/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="javascript/ajax.js" type="text/javascript"></script>
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
    <jsp:include page="pages/search-ltr.jsp" />

    <div id="timeDiv" class="container" style="display: none">
      <small style="color: darkgray">
        <fmt:message key="almost" bundle="${msg}"/>
        <span id="totalHitsSmall"></span>
        <fmt:message key="results" bundle="${msg}"/>
        (<span id="time"></span>)&nbsp;<fmt:message key="seconds" bundle="${msg}"/>
      </small>
    </div>
    <div id="qtableDiv" class="container" style="display: none"></div>
    <div id="paginationDiv" style="display: none">
      <jsp:include page="pages/pagination-ltr.jsp" />
    </div>
  </c:if>
<%--ent ltr--%>

<%--rtl--%>
<c:if test="${sessionScope.locale ne null and !sessionScope.locale.language.equals('en')}">
  <jsp:include page="pages/header-rtl.jsp" />
  <jsp:include page="pages/search-rtl.jsp" />

  <div id="timeDiv" class="container" dir="rtl" style="display: none">
    <small style="color: darkgray">
      <fmt:message key="almost" bundle="${msg}"/>
      <span id="totalHitsSmall"></span>
      <fmt:message key="results" bundle="${msg}"/>
      (<span id="time"></span>)&nbsp;<fmt:message key="seconds" bundle="${msg}"/>
    </small>
  </div>
  <div id="qtableDiv" class="container" style="display: none"></div>
  <div id="paginationDiv" style="display: none">
    <jsp:include page="pages/pagination-rtl.jsp" />
  </div>
</c:if>
<%--ent rtl--%>

  <div class="container">

      <c:catch var="ex">
        <c:if test="${ex ne null}">
          <c:out value="Some error occure."/>
          <c:out value="${ex.message}"/>
        </c:if>
      </c:catch>
    </div>
  <%--End Container--%>


  </body>
</html>
