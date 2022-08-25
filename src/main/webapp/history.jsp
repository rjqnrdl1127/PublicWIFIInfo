<%--
  Created by IntelliJ IDEA.
  User: seongmin
  Date: 2022/08/19
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dao" class="com.example.publicwifiinfo.dao.LookupHistoryDAO"/>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <a href="index.jsp">홈</a> |
    <a href="">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">OPen API 와이파이 정보 가져오기</a>
    <table id="customers">
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        <c:set var="lookupList" value="<%=dao.LookUplist()%>"/>
        <c:forEach var="lookup" items="${lookupList}">
            <tr>
                <td>${lookup.id}</td>
                <td>${lookup.xCoordinate}</td>
                <td>${lookup.yCoordinate}</td>
                <td>${lookup.inquiryDate}</td>
                <td align="center">
                    <input type="button" value="삭제"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
