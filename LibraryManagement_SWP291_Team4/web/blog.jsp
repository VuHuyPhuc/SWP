<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Library - Online Library Website</title>
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
        <!-- Header Start -->
        <header>
            <%@include file="headerFooter/header.jsp" %>
        </header>
        <!-- Header End -->
        <!-- Breadcrumb Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-12">
                    <nav class="breadcrumb bg-light mb-30">
                        <a class="breadcrumb-item text-dark" href="home.jsp">Home</a>
                        <a class="breadcrumb-item text-dark" href="blog">Blog</a>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->


        <!-- Cart Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-lg-8 table-responsive mb-5">
                    <table class="table table-light table-borderless table-hover text-center mb-0">
                        <thead class="thead-dark">
                            <tr>
                                
                                <th>Image</th>
                                <th>Title</th>
                                <th>Date</th>
                                <th>Detail</th>
                                <th>Author</th>

                            </tr>


                        </thead>
                        <tbody class="align-middle">
                            
                            <c:forEach items="${requestScope.list}" var="c">
                                <tr>
                                    
                                    
                                    <td class="align-middle"><img src="${c.image}" alt="" style="width: 50px;"></td>
                                    <td class="align-middle">
                                        <div class="input-group quantity mx-auto" style="width: 200px;">
                                            ${c.title}
                                        </div>
                                    </td>
                                    <td class="align-middle">26/05/2022</td>
                                    <td><a href="blogDetail?id=${c.id}">See more</a></td>
                                    <td class="align-middle">${c.author}</td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                            <!--                                <div class="col-12">
                                                        <nar aria-lable="...">
                                                            <ul class="pagination pagination-lg">
                            <c:forEach begin="1" end="${numberPage}" var="i">
                                <li class="page-item"><a class="page-link" href="blog?index=${i}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </nar>
            </div>-->
                        </tbody>
                    </table>
                </div>
                <div class="col-12">
                    <nav>

                        <ul class="pagination justify-content-center">
                            <li class="page-item ${requestScope.index == 1?'disabled':''}"><a class="page-link" href="blog?index=${index-1}">Previous</span></a></li>
                                <c:forEach var="i" begin="1" end="${requestScope.numpage}">
                                <li class="page-item ${requestScope.index == i?'active':''}"><a class="page-link" href="blog?index=${i}">${i}</a></li>
                                </c:forEach>
                            <li class="page-item ${requestScope.index == numpage?'disabled':''}"><a class="page-link" href="blog?index=${index+1}">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Cart End -->


        <!-- Footer Start -->
        <footer>
            <%@include file="headerFooter/footer.jsp" %>
        </footer>
        <!-- Footer End -->



        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


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