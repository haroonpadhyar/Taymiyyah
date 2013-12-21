<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<fmt:setLocale value="${requestScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
    <title>مكتشف</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <h1><span style="color: green">مكتشف</span> </h1>
    <br>
    <a href="searchservlet?locale=ar">العربیة</a>&nbsp;
    <a href="searchservlet?locale=ur">اردو</a>&nbsp;
    <a href="searchservlet?locale=en">English</a>&nbsp;

   <form action="searchservlet" method="post">
     <label><fmt:message key="searchTerm" bundle="${msg}"/>:</label>
     <input name="term" type="text" value="محمد"/>
     <br>

     <button type="submit" name="original" value="1" ><fmt:message key="searchOriginal" bundle="${msg}"/></button>
     <br>
     <button type="submit" name="original" value="0" ><fmt:message key="searchTrns" bundle="${msg}"/></button>

    <br>
    <c:if test="${requestScope.quranList ne null}">
      <table dir="RTL" border="1" width="100%">
        <thead>
          <tr>
            <th><fmt:message key="surahNo" bundle="${msg}"/></th>
            <th><fmt:message key="surahName" bundle="${msg}"/></th>
            <th><fmt:message key="ayahNo" bundle="${msg}"/></th>
            <th><fmt:message key="ayah" bundle="${msg}"/></th>
            <th><fmt:message key="juzNo" bundle="${msg}"/></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="quran" items="${requestScope.quranList}" varStatus="var">
            <tr>
              <td><c:out value="${quran.surahId}"/></td>
              <td><c:out value="${quran.surahName}"/></td>
              <td><c:out value="${quran.ayahId}"/></td>
              <td>
                <c:out escapeXml="false" value="${quran.ayahText}"/>
                <br>
                <c:out escapeXml="false" value="${quran.ayahTranslationText}"/>
              </td>
              <td><c:out value="${quran.juzName}"/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <br>
      <button type="submit" name="original" value="prv" ><fmt:message key="previous" bundle="${msg}"/></button>&nbsp;&nbsp;
      <c:out value="${requestScope.currentPage}"/>&nbsp;
      <fmt:message key="of" bundle="${msg}"/>&nbsp;
      <c:out value="${requestScope.totalPage}"/> &nbsp;&nbsp;
      <button type="submit" name="original" value="nxt" ><fmt:message key="next" bundle="${msg}"/></button>&nbsp;  &nbsp;&nbsp;&nbsp;
      <fmt:message key="totalHits" bundle="${msg}"/>&nbsp;
      <c:out value="${requestScope.totalHits}"/> &nbsp;&nbsp;
      <input type="hidden" name="currentPageHidden" value=<c:out value="${requestScope.currentPage}" default="0" /> />
      <input type="hidden" name="totalPagesHidden" value=<c:out value="${requestScope.totalPage}" default="0" /> />
      <input type="hidden" name="originalHidden" value=<c:out value="${requestScope.original}" default="0" /> />
      <input type="hidden" name="termHidden" value=<c:out value="${requestScope.term}" default="_" /> />
    </c:if>

     <input type="hidden" name="locale" value=<c:out value="${requestScope.localeLang}" default="en" /> />
   </form>

  <c:catch var="ex">
    <c:if test="${ex ne null}">
      <c:out value="Some error occure."/>
      <c:out value="${ex.message}"/>
    </c:if>
  </c:catch>

  </body>
</html>
