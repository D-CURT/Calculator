<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Input Page</title>
    <style>
        <%@include file='/content/css/style.css'%>
    </style>
</head>
<body>
    <div class="wrapper">
        <form action="input" name="inputForm" method="get">
            <table border="1px">
                <tr>
                    <td><input type="submit" value="7"></td>
                    <td><input type="submit" value="8"></td>
                    <td><input type="submit" value="9"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="4"></td>
                    <td><input type="submit" value="5"></td>
                    <td><input type="submit" value="6"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="1"></td>
                    <td><input type="submit" value="2"></td>
                    <td><input type="submit" value="3"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
