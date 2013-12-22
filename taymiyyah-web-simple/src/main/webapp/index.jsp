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
        $('#nxt,#prv,#orgSrch,#trnsSrch').live('click', function() {
          $.ajax({ // ajax call starts
            url: 'searchthetext',
            type: "POST",
            data: 'ajax=yes&term=' + $('#term').val()
                +'&termHidden='+$('#termHidden').val()
                +'&locale='+$('#locale').val()
                +'&src='+$(this ).attr("id")
                +'&currentPage='+$('#currentPageHidden').val()
                +'&totalPages='+$('#totalPagesHidden').val()
                +'&original='+$('#originalHidden').val(),
//            dataType: 'json',
            success: function(data)
            {
              var resp = JSON.parse(data);
              var quranList = resp.quranList;
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
              $('#currentPage').html(resp.currentPage);
              $('#totalPages').html(resp.totalPages);
              $('#totalHits').html(resp.totalHits);
              $('#currentPageHidden').val(resp.currentPage);
              $('#totalPagesHidden').val(resp.totalPages);
              $('#originalHidden').val(resp.original);
              $('#termHidden').val(resp.term);
              $('#qtableDiv' ).show();
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

   <form>
     <label><fmt:message key="searchTerm" bundle="${msg}"/>:</label>
     <input id="term" name="term" type="text" value="محمد"/>
     <br>
     <button id="orgSrch" type="submit" name="original" value="1" ><fmt:message key="searchOriginal" bundle="${msg}"/></button>
     <br>
     <button id="trnsSrch" type="submit" name="original" value="0" ><fmt:message key="searchTrns" bundle="${msg}"/></button>

     <%--Hidden values--%>
     <input id="locale" type="hidden" name="locale" value=<c:out value="${requestScope.localeLang}" default="en" /> />
     <input id="currentPageHidden" type="hidden" value="0" />
     <input id="totalPagesHidden" type="hidden" value="0" />
     <input id="originalHidden" type="hidden" value="0" />
     <input id="termHidden" type="hidden" value="" />
     <%--Hidden values--%>

   </form>
  <br>

  <%--data grid--%>
  <div id="qtableDiv" style="display: none">
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
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </tbody>
    </table>
    <%--data grid end--%>

    <%--pagination controls--%>
    <br>
    <a href="" id="prv" ><fmt:message key="previous" bundle="${msg}"/></a>&nbsp;&nbsp;
    <div id="currentPage"></div>&nbsp;
    <fmt:message key="of" bundle="${msg}"/>&nbsp;
    <div id="totalPages"></div> &nbsp;&nbsp;
    <a href="" id="nxt"  ><fmt:message key="next" bundle="${msg}"/></a>&nbsp;  &nbsp;&nbsp;&nbsp;
    <fmt:message key="totalHits" bundle="${msg}"/> &nbsp;
    <div id="totalHits"><div> &nbsp;&nbsp;
    <%--pagination controls end--%>
  </div>

  <c:catch var="ex">
    <c:if test="${ex ne null}">
      <c:out value="Some error occure."/>
      <c:out value="${ex.message}"/>
    </c:if>
  </c:catch>

  </body>
</html>
