<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>MultiShop - Online Shop Website Template</title>
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
                        <a class="breadcrumb-item text-dark" href="#">Authors</a>
                        <span class="breadcrumb-item active">Author Detail</span>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->


        <!-- Shop Detail Start -->
        <div class="container-fluid pb-5">
           
            <div class="row px-xl-5">
                <div class="col-lg-5 mb-30">
                    <div id="product-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner bg-light">
                            <div class="carousel-item active">
                                <img class="w-100 h-100" src="${author.image}">
                            </div>
                            <!--                            <div class="carousel-item">
                                                            <img class="w-100 h-100" src="img/product-2.jpg" alt="Image">
                                                        </div>
                                                        <div class="carousel-item">
                                                            <img class="w-100 h-100" src="img/product-3.jpg" alt="Image">
                                                        </div>
                                                        <div class="carousel-item">
                                                            <img class="w-100 h-100" src="img/product-4.jpg" alt="Image">
                                                        </div>-->
                        </div>
                        <!--                        <a class="carousel-control-prev" href="#product-carousel" data-slide="prev">
                                                    <i class="fa fa-2x fa-angle-left text-dark"></i>
                                                </a>
                                                <a class="carousel-control-next" href="#product-carousel" data-slide="next">
                                                    <i class="fa fa-2x fa-angle-right text-dark"></i>
                                                </a>-->
                    </div>
                </div>

                <div class="col-lg-7 h-auto mb-30">
                    <div class="h-100 bg-light p-30">
                        <h3>${author.fullname}</h3>
                        <div class="d-flex mb-3">
                            <div class="text-primary mr-2">
                                <small class="fas fa-star"></small>
                                <small class="fas fa-star"></small>
                                <small class="fas fa-star"></small>
                                <small class="fas fa-star-half-alt"></small>
                                <small class="far fa-star"></small>
                            </div>
                        </div>
                        <!--<h3 class="font-weight-semi-bold mb-4">$150.00</h3>-->
                        <p class="mb-4">${author.breif_information}
                        </p>

                        <!--                        <div class="d-flex align-items-center mb-4 pt-2">
                                                    <strong class="text-dark mr-3">Quantity:</strong>
                                                    <div class="input-group quantity mr-3" style="width: 130px;">
                                                        <input type="text" class="form-control bg-secondary border-0 text-center" value="1">
                                                    </div>
                                                    <button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Add To
                                                        Cart</button>
                                                    <button class="btn btn-primary px-3"><i class="far fa-heart mr-1"></i> Add To
                                                        Reservations List</button>
                                                </div>-->
                    </div>
                </div>
            <div class="row px-xl-5">
                <div class="col">
                    <div class="bg-light p-30">
                        <div class="nav nav-tabs mb-4">
                            <a class="nav-item nav-link text-dark active" data-toggle="tab" href="#tab-pane-1">Information</a>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="tab-pane-1">
                                <p>
                                    ${author.information}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
       
        <!-- Shop Detail End -->

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