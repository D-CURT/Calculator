<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Input Page</title>
    <script type="text/javascript" src="content/js/functions.js"></script>
    <style>
        <%@include file="/content/css/style.css"%>
    </style>
</head>
<body>
    <div class="wrapper">
        <form action="input" name="inputForm" method="get">
                <input class="resultArea" name="resultArea" type="text" value="">
        </form>
            <table>
                <tr>
                    <td><input class="button" type="button" value="C" ></td>
                    <td><input class="button" type="button" value="<"></td>
                    <td><input class="button" type="button" value="/" onclick="insertNumber('/')"></td>
                    <td><input class="button" type="button" value="x" onclick="insertNumber('*')"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="7" onclick="insertNumber(7)"></td>
                    <td><input class="button" type="button" value="8" onclick="insertNumber(8)"></td>
                    <td><input class="button" type="button" value="9" onclick="insertNumber(9)"></td>
                    <td><input class="button" type="button" value="-" onclick="insertNumber('-')"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="4" onclick="insertNumber(4)"></td>
                    <td><input class="button" type="button" value="5" onclick="insertNumber(5)"></td>
                    <td><input class="button" type="button" value="6" onclick="insertNumber(6)"></td>
                    <td><input class="button" type="button" value="+" onclick="insertNumber('+')"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="1" onclick="insertNumber(1)"></td>
                    <td><input class="button" type="button" value="2" onclick="insertNumber(2)"></td>
                    <td><input class="button" type="button" value="3" onclick="insertNumber(3)"></td>
                    <td><input class="button" type="button" value="=" onclick="equal()"></td>
                </tr>
            </table>
    </div>
</body>
</html>
