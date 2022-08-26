<%--
  Created by IntelliJ IDEA.
  User: seongmin
  Date: 2022/08/26
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<jsp:useBean id="dao" class="com.example.publicwifiinfo.dao.LookupHistoryDAO"/>
<html>
<head>
    <title></title>
</head>
<body>
    <%
        Long id = Long.valueOf(request.getParameter("id"));
        dao.deleteLookupData(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/history.jsp");
        dispatcher.forward(request,response);
    %>
</body>
</html>
