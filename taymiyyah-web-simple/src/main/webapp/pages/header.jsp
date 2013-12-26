<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand h1" href="index.jsp" style="color: #ffffff" >مكتشف</a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.jsp"><fmt:message key="home" bundle="${msg}"/></a></li>
        <li ><a href="about.jsp"><fmt:message key="about" bundle="${msg}"/></a></li>
        <li><a href="contactus.jsp"><fmt:message key="contactUs" bundle="${msg}"/></a></li>
      </ul>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>
