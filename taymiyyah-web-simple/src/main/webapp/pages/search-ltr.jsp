<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.messages" var="msg"/>

<div class="container">

<br>
<br>
<br>
<a href="searchthetext?locale=ar">العربیة</a>&nbsp;
<a href="searchthetext?locale=ur">اردو</a>&nbsp;
<a href="searchthetext?locale=en">English</a>&nbsp;

  <div class="row">
    <%-- Full text search form --%>
    <div class="col-md-6">
      <form class="well form-search">
        <div class="row">
          <div class="col-md-2">
            <label style="padding: 5px;"><fmt:message key="searchTerm" bundle="${msg}"/>:</label>
          </div>
          <div class="col-md-10">
            <input id="term" type="text" class="input-medium search-query"
                   style="width: 100%;padding: 5px;" value="محمد" />
          </div>
        </div><%-- end row 1--%>
        <br>
        <div class="row">
          <div class="col-md-2">

          </div>
          <div class="col-md-5">
            <button id="orgSrch" type="submit" class="btn btn-primary"
                    style="width: 100%" ><fmt:message key="searchOriginal" bundle="${msg}"/></button>
          </div>
          <div class="col-md-5">
            <button id="trnsSrch" type="submit" class="btn btn-primary"
                    style="width: 100%" ><fmt:message key="searchTrns" bundle="${msg}"/></button>
          </div>
        </div> <%-- end row 2--%>

        <%--Hidden values--%>
        <input id="locale" type="hidden" name="locale" value=<c:out value="${sessionScope.localeLang}" default="en" /> />
        <input id="currentPageHidden" type="hidden" value="0" />
        <input id="totalPagesHidden" type="hidden" value="0" />
        <input id="originalHidden" type="hidden" value="0" />
        <input id="termHidden" type="hidden" value="" />
        <%--Hidden values--%>

      </form>
    </div>

    <%-- id search form --%>
    <div class="col-md-6">
      <form class="well form-search">
        <div class="row">
          <div class="col-md-3">
            <input id="radio" type="radio" name="radio" value="idSrch">&nbsp;
            <label><fmt:message key="ayahNo" bundle="${msg}"/></label>
          </div>
          <div class="col-md-7">
            <select style="width: 100%;padding: 7px;">
              <option value="volvo">Volvo</option>
              <option value="saab">Saab</option>
              <option value="mercedes">Mercedes</option>
              <option value="audi">Audi</option>
            </select>
          </div>
          <div class="col-md-2">

          </div>
        </div> <%-- end row 1--%>
        <br>
        <div class="row">
          <div class="col-md-3">
            <input id="radio" type="radio" name="radio" value="srSrch">&nbsp;
            <label><fmt:message key="serialNo" bundle="${msg}"/></label>
          </div>
          <div class="col-md-2">
            <input id="ayaNo" type="text" class="input-medium search-query"
                   style="width: 100%;padding: 5px;" value="" />
          </div>
          <div class="col-md-5">
            <button id="srch" type="submit" class="btn btn-primary"
                    style="width: 100%" ><fmt:message key="search" bundle="${msg}"/></button>
          </div>
          <div class="col-md-2">

          </div>
        </div><%-- end row 2--%>

      </form>
    </div><%--end col 6--%>
  </div> <%-- end row--%>

</div>
