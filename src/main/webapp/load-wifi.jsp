<%--
  Created by IntelliJ IDEA.
  User: seongmin
  Date: 2022/08/24
  Time: 11:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dao" class="com.example.publicwifiinfo.dao.WifiInfoDAO"/>
<c:set var="totCount" value="${dao.saveApiData()}"/>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <div align="center">
        <h1>${totCount}개의 WIFI 정보가 정상적으로 저장되었습니다.</h1>
        <a href="index.jsp" >홈으로 가기</a>
    </div>

</body>
</html>
