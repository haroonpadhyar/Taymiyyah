<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<div class="container">
  <!--PAGINATION-->
  <div>
    <ul class="pagination">
      <li><a href="" id="prv" class="previous">&laquo;&nbsp;<fmt:message key="previous" bundle="${msg}"/></a></li>
      <li class="disabled"><span><div id="currentPage"></div></span></li>
      <li class="disabled"><span><fmt:message key="of" bundle="${msg}"/></span></li>
      <li class="disabled"><span><div id="totalPages"></div></span></li>
      <li><a href="" id="nxt" class="next"><fmt:message key="next" bundle="${msg}"/>&nbsp;&raquo;</a></li>
    </ul>
    &nbsp;
    <ul class="pagination">
      <li class="disabled"><span><fmt:message key="totalHits" bundle="${msg}"/></span></li>
      <li class="disabled"><span><div id="totalHits" style="font-weight: bold;"></div></span></li>
    </ul>
  </div>

</div>
