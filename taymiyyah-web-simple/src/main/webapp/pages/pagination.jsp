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

<div class="container">
  <!--PAGINATION-->
  <div>
    <ul class="pagination">
      <%--<li><a href="" id="prv" class="previous">&laquo;&nbsp;<fmt:message key="previous" bundle="${msg}"/></a></li>--%>
      <li><a href="" id="prv" class="previous">&laquo;&nbsp;<fmt:message key="previous"/></a></li>
      <li class="disabled"><span><div id="currentPage"></div></span></li>
      <%--<li class="disabled"><span><fmt:message key="of" bundle="${msg}"/></span></li>--%>
      <li class="disabled"><span><fmt:message key="of" /></span></li>
      <li class="disabled"><span><div id="totalPages"></div></span></li>
      <%--<li><a href="" id="nxt" class="next"><fmt:message key="next" bundle="${msg}"/>&nbsp;&raquo;</a></li>--%>
      <li><a href="" id="nxt" class="next"><fmt:message key="next"/>&nbsp;&raquo;</a></li>
    </ul>
    &nbsp;
    <ul class="pagination">
      <%--<li class="disabled"><span><fmt:message key="totalHits" bundle="${msg}"/></span></li>--%>
      <li class="disabled"><span><fmt:message key="totalHits" /></span></li>
      <li class="disabled"><span><div id="totalHits" style="font-weight: bold;"></div></span></li>
    </ul>
  </div>

</div>
</fmt:bundle>