<%@ page import="com.example.publicwifiinfo.ApiExplorer" %>
<%@ page import="com.google.gson.JsonParser" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="com.google.gson.JsonObject" %>
<%--
  Created by IntelliJ IDEA.
  User: seongmin
  Date: 2022/08/19
  Time: 9:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String json = ApiExplorer.ApiJson(); // json data
    ApiExplorer.saveApiData(); // 데이터 저장

    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse(json);
    JsonObject tbl = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
    int total = tbl.getAsJsonObject().get("list_total_count").getAsInt(); // 총 데이터 수 파싱
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <h1><%= total%>개의 WIFI 정보를 정상적으로 저장했습니다.</h1>
    <a href="index.jsp">홈으로 가기</a>
</body>
</html>
