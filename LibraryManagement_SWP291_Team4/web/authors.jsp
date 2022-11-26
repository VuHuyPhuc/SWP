<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Library - Online Library Management</title>
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
            <%@include file="headerFooter/header.jsp" %>
        </header>

        <!-- Breadcrumb Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-12">
                    <nav class="breadcrumb bg-light mb-30">
                        <a class="breadcrumb-item text-dark" href="#">Home</a>
                        <a class="breadcrumb-item text-dark" href="author">Authors</a>
                        <span class="breadcrumb-item active">Authors List</span>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->


        <!-- Shop Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">



                <!-- Shop Product Start -->
                <div class="col-lg-9 col-md-8">
                    <div class="row pb-3">
                        <div class="col-12 pb-1">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <div class="ml-2">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Sorting</button>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="#">Latest</a>
                                            <a class="dropdown-item" href="#">Popularity</a>
                                            <a class="dropdown-item" href="#">Best Rating</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:forEach var="c" items="${list}">
                            <div class="col-lg-4 col-md-6 col-sm-6 pb-1">
                                <div class="product-item bg-light mb-4">
                                    <div class="product-img position-relative overflow-hidden">
                                        <img class="img-fluid" style="height: 300px;width: 300px" src="${c.image}" alt="">
                                        <div class="product-action">
                                            <!--                                        <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-shopping-cart"></i></a>
                                                                                    <a class="btn btn-outline-dark btn-square" href=""><i class="far fa-heart"></i></a>-->
                                            <!--                                        <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-sync-alt"></i></a>
                                                                                    <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>-->
                                        </div>
                                    </div>

                                    <div class="text-center py-4">
                                        <a class="h5 text-decoration-none text-truncate" href="AuthorDetailController?author_id=${c.id}">By ${c.fullname}</a>
                                        <div class="d-flex align-items-center justify-content-center mt-2">
                                            <h5>Contact: </h5>
                                        </div>
                                        <!--                                        <div class="d-flex align-items-center justify-content-center mb-1">
                                                                                    <small class="fa fa-star text-primary mr-1"></small>
                                                                                    <small class="fa fa-star text-primary mr-1"></small>
                                                                                    <small class="fa fa-star text-primary mr-1"></small>
                                                                                    <small class="fa fa-star text-primary mr-1"></small>
                                                                                    <small class="fa fa-star text-primary mr-1"></small>
                                                                                    <small>(99)</small>
                                                                                </div>-->
                                    </div>
                                </div>
                            </div>
                        </c:forEach>


                        <div class="col-12">
                            <nar aria-lable="...">
                                <ul class="pagination pagination-lg">
                                    <c:forEach begin="1" end="${numberPage}" var="i">
                                        <li class="page-item"><a class="page-link" href="author?index=${i}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nar>
                        </div>
                    </div>
                </div>
                <!-- Shop Product End -->
            </div>
        </div>
        <!-- Shop End -->


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