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
    <script src="javascript/jquery-1.7.2.js" type="text/javascript"></script>
    <title>مكتشف</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <script type="text/javascript">
      $(document).ready(function(){
        $('#nxt,#prv').live('click', function() { // This event fires when a button is clicked
          var button = $(this).val();
          $.ajax({ // ajax call starts
            url: 'searchthetext',
            type: "POST",
            data: 'ajax=yes&term=' + $('#termHidden').val()
              +'&locale='+$('#locale').val()
              +'&pagination='+$(this ).attr("id")
              +'&currentPage='+$('#currentPageHidden').val()
              +'&totalPages='+$('#totalPagesHidden').val()
              +'&original='+$('#originalHidden').val(),
//            dataType: 'json',
            success: function(data)
            {
              var returnedData = JSON.parse(data);
              var quranList = returnedData.quranList;
              var str ="";
              for(var i =0;i < quranList.length;i++)
              {
                var quran = quranList[i];
                str +="<tr>"
                  +"<td>"+quran.surahId+"</td>"
                  +"<td>"+quran.surahName+"</td>"
                  +"<td>"+quran.ayahId+"</td>"
                  +"<td>"+quran.ayahText
                  +"<br>"
                  +quran.ayahTranslationText
                  +"</td>"
                  +"<td>"+quran.juzName+"</td>"
                  +"</tr>";

              }
              $('#qtable tbody').html(str);
              $('#currentPage').html(returnedData.pageNo);
              $('#currentPageHidden').val(returnedData.pageNo);
            }
          });
          return false; // keeps the page from not refreshing
        });
      });
    </script>
  </head>
  
  <body>
  <h1><span style="color: green">مكتشف</span> </h1>
    <br>
    <a href="searchthetext?locale=ar">العربیة</a>&nbsp;
    <a href="searchthetext?locale=ur">اردو</a>&nbsp;
    <a href="searchthetext?locale=en">English</a>&nbsp;

   <form action="searchthetext" method="post">
     <label><fmt:message key="searchTerm" bundle="${msg}"/>:</label>
     <input name="term" type="text" value="محمد"/>
     <br>

     <input type="hidden" name="locale"  id="locale" value=<c:out value="${requestScope.localeLang}" default="en" /> />
     <button type="submit" name="original" value="1" ><fmt:message key="searchOriginal" bundle="${msg}"/></button>
     <br>
     <button type="submit" name="original" value="0" ><fmt:message key="searchTrns" bundle="${msg}"/></button>

   </form>
  <br>
  <c:if test="${requestScope.quranList ne null}">
    <table id="qtable" dir="RTL" border="1" width="100%">
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
    <a href="" id="prv" ><fmt:message key="previous" bundle="${msg}"/></a>&nbsp;&nbsp;
    <div id="currentPage"><c:out value="${requestScope.currentPage}"/></div>&nbsp;
    <fmt:message key="of" bundle="${msg}"/>&nbsp;
    <c:out value="${requestScope.totalPage}"/> &nbsp;&nbsp;
    <a href="" id="nxt"  ><fmt:message key="next" bundle="${msg}"/></a>&nbsp;  &nbsp;&nbsp;&nbsp;
    <fmt:message key="totalHits" bundle="${msg}"/>&nbsp;
    <c:out value="${requestScope.totalHits}"/> &nbsp;&nbsp;
    <input type="hidden" name="currentPageHidden"
           id="currentPageHidden" value=<c:out value="${requestScope.currentPage}" default="0" /> />
    <input type="hidden" name="totalPagesHidden"
           id="totalPagesHidden" value=<c:out value="${requestScope.totalPage}" default="0" /> />
    <input type="hidden" name="originalHidden"
           id="originalHidden" value=<c:out value="${requestScope.original}" default="0" /> />
    <input type="hidden" name="termHidden"
           id="termHidden" value=<c:out value="${requestScope.term}" default="_" /> />
  </c:if>

  <c:catch var="ex">
    <c:if test="${ex ne null}">
      <c:out value="Some error occure."/>
      <c:out value="${ex.message}"/>
    </c:if>
  </c:catch>

  </body>
</html>
