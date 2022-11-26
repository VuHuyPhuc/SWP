
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link href=""https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet"/>
        <link href=""https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"/>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>
        <div class="box-form " style="height:80%; position: absolute;left: 50%;top: 50%;transform: translate(-50%, -50%);">
            <div class="left" style="width: 70%; background-image: url('https://media.istockphoto.com/vectors/online-library-media-and-books-vector-id1125526872?k=20&m=1125526872&s=170667a&w=0&h=2F7c0KbQF13HIww3-SXBrGx6Xqo5jNy19zFPJ_Bg0oM=')">
            </div>

            <div class="right" style="width: 30%;">
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Create new account</a></p>
                <p class="notification" style="color: red;"> ${error}&nbsp;</p>
                <form method="post" action="${pageContext.request.contextPath}/login">
                    <div class="inputs">
                        <input name="username"  placeholder="user name">
                        <br>
                        <input name="password" type="password" placeholder="password">
                    </div>
                    <br>
                    <input type="submit" value="Login" style="border-radius: 10px; background-color: #ebe534;">
                </form>
            </div>
        </div>

    </body>

</html>