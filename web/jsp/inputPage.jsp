<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Input Page</title>
    <style>
        <%@include file="/content/css/style.css"%>
    </style>
</head>
<body>
    <div class="wrapper">
        <form action="input" name="inputForm" method="get">
            <input class="textview">
        </form>
            <table>
                <tr>
                    <td><input class="button" type="button" value="C"></td>
                    <td><input class="button" type="button" value="<"></td>
                    <td><input class="button" type="button" value="/"></td>
                    <td><input class="button" type="button" value="x"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="7"></td>
                    <td><input class="button" type="button" value="8"></td>
                    <td><input class="button" type="button" value="9"></td>
                    <td><input class="button" type="button" value="-"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="4"></td>
                    <td><input class="button" type="button" value="5"></td>
                    <td><input class="button" type="button" value="6"></td>
                    <td><input class="button" type="button" value="+"></td>
                </tr>
                <tr>
                    <td><input class="button" type="button" value="1"></td>
                    <td><input class="button" type="button" value="2"></td>
                    <td><input class="button" type="button" value="3"></td>
                    <td><input class="button" type="button" value="="></td>
                </tr>
            </table>
    </div>
</body>
</html>
