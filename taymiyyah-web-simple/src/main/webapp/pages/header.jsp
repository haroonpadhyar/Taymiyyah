<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<%--<fmt:setLocale value="${sessionScope.locale}" />--%>
<%
  String localeLang = (String)session.getAttribute("localeLang");
  if(localeLang != null && localeLang.equals("en")) {
%>
<fmt:setLocale value="en" />
<%
}else if(localeLang != null && localeLang.equals("ar")) {
%>
<fmt:setLocale value="ar" />
<%
}else if(localeLang != null && localeLang.equals("ur")) {
%>
<fmt:setLocale value="ur" />
<%
}else {
%>
<fmt:setLocale value="en" />
<%
  }
%>
<%--<fmt:setBundle basename="i18n.messages" var="msg"/>--%>
<fmt:bundle basename="i18n.messages">

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand h1" href="index.jsp" style="color: #ffffff;font-weight: bolder " >مكتشف</a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <%--<li class="active"><a href="index.jsp"><fmt:message key="home" bundle="${msg}"/></a></li>--%>
        <li class="active"><a href="index.jsp"><fmt:message key="home"/></a></li>
        <%--<li ><a href="about.jsp"><fmt:message key="about" bundle="${msg}"/></a></li>--%>
        <li ><a href="about.jsp"><fmt:message key="about"/></a></li>
        <%--<li><a href="contactus.jsp"><fmt:message key="contactUs" bundle="${msg}"/></a></li>--%>
        <li><a href="contactus.jsp"><fmt:message key="contactUs"/></a></li>
      </ul>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>
</fmt:bundle>