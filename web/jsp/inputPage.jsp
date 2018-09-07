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
            <table>
                <tr><td><input id="exp" name="expression" type="text" value=""></td></tr>
                <tr><td><input class="resultArea" name="result" type="text" value="${result}"></td></tr>
            </table>
        </form>
            <table>
                <tr>
                    <td><input class="button" id="clean" type="button" value="C" onclick="clean()"></td>
                    <td><input class="button" id="back" type="button" value="<" onclick="backSpace()"></td>
                    <td><input class="button" type="button" value="%" onclick="insert('%')"></td>
                    <td><input class="button" type="button" value="^" onclick="insert('^')"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="7" onclick="insert(7)"></td>
                    <td><input class="button" type="button" value="8" onclick="insert(8)"></td>
                    <td><input class="button" type="button" value="9" onclick="insert(9)"></td>
                    <td><input class="button" type="button" value="x" onclick="insert('*')"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="4" onclick="insert(4)"></td>
                    <td><input class="button" type="button" value="5" onclick="insert(5)"></td>
                    <td><input class="button" type="button" value="6" onclick="insert(6)"></td>
                    <td><input class="button" type="button" value="/" onclick="insert('/')"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="1" onclick="insert(1)"></td>
                    <td><input class="button" type="button" value="2" onclick="insert(2)"></td>
                    <td><input class="button" type="button" value="3" onclick="insert(3)"></td>
                    <td><input class="button" type="button" value="-" onclick="insert('-')"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="0" onclick="insert(0)"></td>
                    <td><input class="button" type="button" value="." onclick="insert('.')"></td>
                    <td><input class="button" type="button" value="=" onclick="submit()"></td>
                    <td><input class="button" type="button" value="+" onclick="insert('+')"></td>
                </tr>
            </table>
    </div>
</body>
</html>
