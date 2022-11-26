<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>User Profile</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        
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
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Change Password</span></h2>
            <form method="post" action="${pageContext.request.contextPath}/changepassword">
            <div class="row ml-5">
                <div>
                    <c:if test="${mess == 'success'}">
                        <p class="notification" style="color: green;">Password changed successfully</p>
                    </c:if>
                    <c:if test="${mess != 'success'}">
                        <p class="notification" style="color: red;">${mess}</p>
                    </c:if>    
                </div>
            </div>
            <br>
            <div class="row ml-5">
                <div>
                    <input type="password" name="oldPassword" placeholder="Old password" style="border-radius: 5px;"/>
                </div>
            </div>
            <br>
            <div class="row ml-5">
               <div>
                    <input type="password" name="newPassword" placeholder="New password" style="border-radius: 5px;"/>
               </div>
            </div>
            <br>
            <div class="row ml-5">
               <div>
                    <input type="password" name="reNewPassword" placeholder="Re-enter new password" style="border-radius: 5px;"/>
                </div>
            </div>
            <br>
            <div class="row ml-5">
               <div>
                    <input type="submit" value="Change password" style="border-radius: 5px; background-color: #3d464d; color:white;"/>
                </div>
            </div>
            </form>
        </div>

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