<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-6-12
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户登陆</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="customerId" /></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="pwd" /></td></tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录" />
            </td>
        </tr>
    </table>
</form>

</body>
</html>
