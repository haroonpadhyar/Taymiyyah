<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<div class="container">

  <div class="row well" style="margin-right: 0px;margin-left: 0px;">
    <div id="totalHits" class="col-md-1" style="font-weight: bold;"></div>
    <div class="col-md-2"><fmt:message key="totalHits" bundle="${msg}"/></div>
    <div class="col-md-2"></div>
    <div class="col-md-2" style="text-align: center" title="Next">
      <a id="nxt" href=""><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;<fmt:message key="next" bundle="${msg}"/></a></div>
    <div id="totalPages" class="col-md-1" dir="rtl"></div>
    <div class="col-md-1" dir="rtl"><fmt:message key="of" bundle="${msg}"/></div>
    <div id="currentPage" class="col-md-1" dir="rtl"></div>
    <div class="col-md-2"  style="text-align: right" title="Previous">
      <a id="prv" href=""><fmt:message key="previous" bundle="${msg}"/>&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></a >
    </div>
  </div>

</div>
