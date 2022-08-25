<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dao" class="com.example.publicwifiinfo.dao.WifiInfoDAO"/>

<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script>
        let latitude = 0
        let longitude = 0
        function cal_location() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (pos) {
                    latitude = pos.coords.latitude;
                    longitude = pos.coords.longitude;

                    document.getElementById('latitude').value = latitude;
                    document.getElementById('longitude').value = longitude;
                });
            } else {
                alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
            }
        }
    </script>
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
    <h1>"와이파이 정보 구하기"</h1>
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">OPen API 와이파이 정보 가져오기</a>
    <br>
    <form method="get" action="${pageContext.request.contextPath}/index.jsp">
        LAT:<input type="text" name="lat" id="latitude" value=0.0>
        LNT:<input type="text" name="lnt" id="longitude" value=0.0>
        <input type="button" value="내 위치 가져오기" onclick="cal_location()">
        <input type="submit" value="근처 WIFI 정보 보기">
    </form>
    <table id="customers">
        <tr>
            <th>거리(KM)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <c:if test="${param.lat == null || param.lnt == null}">
            <tr>
                <td colspan="17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
            </tr>
        </c:if>
        <c:if test="${param.lat != null && param.lnt != null}">
            <c:set var="list" value="<%=dao.wifiList()%>"/>
            <c:forEach var="wifi" items="${list}">
                <tr>
                    <td>${wifi.distance}</td>
                    <td>${wifi.XSWifiManageNo}</td>
                    <td>${wifi.XSWifiWRDOFC}</td>
                    <td>${wifi.XSWifiMainNM}</td>
                    <td>${wifi.XSWifiADRES1}</td>
                    <td>${wifi.XSWifiADRES2}</td>
                    <td>${wifi.XSWifiInstallFloor}</td>
                    <td>${wifi.XSWifiInstallType}</td>
                    <td>${wifi.XSWifiInstallMBY}</td>
                    <td>${wifi.XSWifiServiceSE}</td>
                    <td>${wifi.XSWifiCMCWR}</td>
                    <td>${wifi.XSWifiCNSTCYear}</td>
                    <td>${wifi.XSWifiInoutDoor}</td>
                    <td>${wifi.XSWifiREMARS3}</td>
                    <td>${wifi.lat}</td>
                    <td>${wifi.lnt}</td>
                    <td>${wifi.workDatetime}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>
</html>