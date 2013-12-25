<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
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
                var title = "("+quran.surahId+")"+quran.surahName +" "+quran.ayahId+". "+quran.juzName;
                str += "<div title=\""+title+"\" class=\"row well\" style=\"margin-right: 0px;margin-left: 0px;\">"
                    +"<p style=\"font-size: xx-large\" dir=\"rtl\">"
                    +quran.ayahText
                    +" . ("+quran.surahName+" "+quran.ayahId+")"
                    +"</p>";

                if(resp.lang == "en"){
                  str +="<p style=\"font-size: large\" >"
                      +quran.ayahTranslationText
                      +"</p>";
                } else{
                  if(resp.lang != "ar"){
                    str +="<p style=\"font-size: large\" dir=\"rtl\">"
                        +quran.ayahTranslationText
                        +"</p>" ;
                  }
                }

                str += "</div>";
              }
              $('#qtableDiv').html(str);
              $('#currentPage').html(resp.currentPage);
              $('#totalPages').html(resp.totalPages);
              $('#totalHits').html(resp.totalHits);
              $('#currentPageHidden').val(resp.currentPage);
              $('#totalPagesHidden').val(resp.totalPages);
              $('#originalHidden').val(resp.original);
              $('#termHidden').val(resp.term);
              $('#resultDiv' ).show();
            }
          });
          return false; // keeps the page from not refreshing
        });
      });
    </script>
  </head>
  
  <body>

<%--ltr--%>
  <c:if test="${sessionScope.locale eq null or sessionScope.locale.language.equals('en')}">
    <jsp:include page="pages/header-ltr.jsp" />
    <jsp:include page="pages/search-ltr.jsp" />
    <div id="resultDiv" style="display: none">
      <div id="qtableDiv" class="container"></div>
      <jsp:include page="pages/pagination-ltr.jsp" />
    </div>
  </c:if>
<%--ent ltr--%>

<%--rtl--%>
<c:if test="${sessionScope.locale ne null and !sessionScope.locale.language.equals('en')}">
  <jsp:include page="pages/header-rtl.jsp" />
  <jsp:include page="pages/search-rtl.jsp" />
  <div id="resultDiv" style="display: none">
    <div id="qtableDiv" class="container"></div>
    <jsp:include page="pages/pagination-rtl.jsp" />
  </div>
</c:if>
<%--ent rtl--%>

  <div class="container">

      <c:catch var="ex">
        <c:if test="${ex ne null}">
          <c:out value="Some error occure."/>
          <c:out value="${ex.message}"/>
        </c:if>
      </c:catch>
    </div>
  <%--End Container--%>


  </body>
</html>
