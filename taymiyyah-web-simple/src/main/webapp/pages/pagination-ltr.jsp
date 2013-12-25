<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<div class="container">

  <div class="row well" style="margin-right: 0px;margin-left: 0px;">
    <div class="col-md-2" title="Previous">
      <a id="prv" href=""><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;<fmt:message key="previous" bundle="${msg}"/></a></div>
    <div id="currentPage" class="col-md-1"></div>
    <div class="col-md-1"><fmt:message key="of" bundle="${msg}"/></div>
    <div id="totalPages" class="col-md-1"></div>
    <div class="col-md-2" style="text-align: center" title="Next">
      <a id="nxt" href=""><fmt:message key="next" bundle="${msg}"/>&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></a></div>
    <div class="col-md-2"></div>
    <div class="col-md-2" dir="rtl"><fmt:message key="totalHits" bundle="${msg}"/></div>
    <div id="totalHits" class="col-md-1" dir="rtl" style="font-weight: bold;"></div>
  </div>

</div>
