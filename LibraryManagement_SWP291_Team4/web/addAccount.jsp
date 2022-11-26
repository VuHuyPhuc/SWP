<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>User Profile</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <jsp:include page="headerFooter/header.jsp" />
        </header>

        <div class="container-fluid pt-5 m-auto">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">User Profile</span></h2>

            <form method="post" action="${pageContext.request.contextPath}/add-account">
                <div class="inputs">
                    <c:if test="${mess != null}">
                        <h3 class="notification" style="color: red;"> ${mess}&nbsp;</h3>
                    </c:if>
                    <input name="fullname" type="text"  placeholder="Full name">
                    <br>
                    <br>
                    <input name="username" type="text"  placeholder="Username">
                    <br>
                    <br>
                    <input name="password" type="password" placeholder="Password">
                    <br>
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

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>