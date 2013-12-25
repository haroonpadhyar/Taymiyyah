<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="row ">
      <div class="col-md-5"></div>
      <div class="col-md-2" style="text-align: center">
        <span style="color: #ffffff" class="h1">مكتشف</span>
      </div>
      <div class="col-md-5">
        <div class="col-md-4"></div>
        <div class="col-md-6">
          <div class="navbar-header collapse navbar-collapse">
            <ul class="nav navbar-nav">
              <li><a href="contactus.jsp"><fmt:message key="contactUs" bundle="${msg}"/></a></li>
              <li ><a href="about.jsp"><fmt:message key="about" bundle="${msg}"/></a></li>
            </ul>
          </div>
        </div>
        <div class="col-md-2">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp" style="color: #ffffff" ><fmt:message key="home" bundle="${msg}"/></a>
          </div>
        </div>

      </div>

    </div>
  </div>  <!-- container -->

</div>
