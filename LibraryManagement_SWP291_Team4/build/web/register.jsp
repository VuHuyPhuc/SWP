<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link href="css/login.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"><link rel="stylesheet" href="./style.css">
        <title>Register new account</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
    </head>

    <body>

          <div class="box-form" style="height:80%; position: absolute;left: 50%;top: 50%;transform: translate(-50%, -50%);">
            <div class="left" style="width: 70%; background-image: url('https://media.istockphoto.com/vectors/online-library-media-and-books-vector-id1125526872?k=20&m=1125526872&s=170667a&w=0&h=2F7c0KbQF13HIww3-SXBrGx6Xqo5jNy19zFPJ_Bg0oM=')">
            </div>

            <div class="right" style="width: 30%;">
                <p>I had an account? <a href="${pageContext.request.contextPath}/login">Go to login page</a></p>
                <form method="post" action="${pageContext.request.contextPath}/register">
                    <div class="inputs">
                        <c:if test="${mess == 'success'}">
                            <h3 class="notification" style="color: green;"> Register new account successfully</h3>
                        </c:if>
                        <c:if test="${mess != 'success'}">
                            <h3 class="notification" style="color: red;"> ${mess}&nbsp;</h3>
                        </c:if>
                        <input name="fullname" type="text"  placeholder="Full name">
                        <br>
                        <input name="username" type="text"  placeholder="Username">
                        <br>
                        <input name="password" type="password" placeholder="Password">
                        <br>
                        <input name="email" type="text" placeholder="Email">
                        <br>
                        <br>
                        <span style="color:#8b8683;">Role&nbsp;</span>
                        <select name="role" style="border-radius: 5px; color: #8b8683; height:30px;">
                            <option value="admin">Admin</option>
                            <option value="user">User</option>
                            <option value="liabrian">Liabrian</option>
                            <option value="marketing">Marketing</option>
                        </select>
                        <input type="submit" value="Register" style="border-radius: 10px; background-color: #ebe534;">
                    </div>
                </form>
            </div>
        </div>

    </body>

</html>