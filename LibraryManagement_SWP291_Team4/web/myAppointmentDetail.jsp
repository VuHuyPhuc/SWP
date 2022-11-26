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

        <header>
            <%@include file="headerFooter/header.jsp" %>
        </header>
        <!-- Breadcrumb Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-12">
                    <nav class="breadcrumb bg-light mb-30">
                        <a class="breadcrumb-item text-dark" href="home.jsp">Home</a>
                        <a class="breadcrumb-item text-dark" href="myAppointment">My Appointment</a>
                        <span class="breadcrumb-item active">My Appointment Detail</span>
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
                                <img class="w-100 h-100" src="${appointmentDetail.book.image}" alt="Image">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-7 h-auto mb-30">
                    <div class="h-100 bg-light p-30">
                        <h3>${appointmentDetail.book.title}</h3>
                        <div class="d-flex mb-3">
                            <div class="text-primary mr-2">
                                <c:forEach var = "i" begin = "1" end = "4">
                                    <small class="fas fa-star"></small>
                                </c:forEach>
                                <!--                                <small class="fas fa-star"></small>  full star  
                                                                <small class="fas fa-star-half-alt"></small>  haft star  
                                                                <small class="far fa-star"></small>  blank star  -->
                            </div>
                            <small class="pt-1">(99 Reviews)</small>
                        </div>
                        <p class="mb-4">${appointmentDetail.book.introduction}</p>
                        <p>Publisher: ${appointmentDetail.book.publisher}</p>
                        <p>Publication Year: ${appointmentDetail.book.publication_year}</p>
                        <p>Quantity: ${appointmentDetail.book.quantity}</p>

                        <strong class="text-dark mr-3">Date to get book: ${appointmentDetail.date}</strong>
                    </div>
                </div>
            </div>
            <div class="row px-xl-5">
                <div class="col">
                    <div class="bg-light p-30">
                        <div class="nav nav-tabs mb-4">
                            <a class="nav-item nav-link text-dark active" data-toggle="tab" href="#tab-pane-1">Description</a>
                            <!--<a class="nav-item nav-link text-dark" data-toggle="tab" href="#tab-pane-2">Information</a>-->
                            <a class="nav-item nav-link text-dark" data-toggle="tab" href="#tab-pane-3">Reviews (${listBookReview.size()})</a>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="tab-pane-1">
                                <h4 class="mb-3">Book Description</h4>
                                <p>${appointmentDetail.book.description}</p>
                            </div>
                            <div class="tab-pane fade" id="tab-pane-3">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h4 class="mb-4">${listBookReview.size()} review for "Dance with the Devil"</h4>
                                        <div class="media mb-4">
                                            <!--<img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">-->
                                            <c:if test="${listBookReview.size() > 0}">
                                                <c:forEach items="${listBookReview}" var="r">
                                                    <div class="media-body">
                                                        <h6>${r.user.fullname}<small> - <i>${r.date}</i></small></h6>
    <!--                                                    <div class="text-primary mb-2">
                                                            <i class="fas fa-star"></i>
                                                            <i class="fas fa-star"></i>
                                                            <i class="fas fa-star"></i>
                                                            <i class="fas fa-star-half-alt"></i>
                                                            <i class="far fa-star"></i>
                                                        </div>-->
                                                        <!--<p>Diam amet duo labore stet elitr ea clita ipsum, tempor labore accusam ipsum et no at. Kasd diam tempor rebum magna dolores sed sed eirmod ipsum.</p>-->
                                                        <p>${r.comment}</p>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <h4 class="mb-4">Leave a review</h4>
                                        <!--<small>Your email address will not be published. Required fields are marked *</small>-->
                                        <div class="d-flex my-3">
<!--                                            <p class="mb-0 mr-2">Your Rating * :</p>
                                            <div class="text-primary">
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                            </div>-->
                                        </div>
                                        <form action="bookReview?action=insert&bookId=${appointmentDetail.book.id}&userId=${appointmentDetail.user_id}" method="POST">
                                            <div class="form-group">
                                                <label for="message">Your Review *</label>
                                                <textarea name="comment" id="message" cols="30" rows="5" class="form-control" required=""></textarea>
                                                <input type="hidden" name="appointmentId" value="${appointmentDetail.appointment_id}">
                                            </div>
                                            <div class="form-group mb-0">
                                                <input type="submit" value="Leave Your Review" class="btn btn-primary px-3">
                                            </div>
                                        </form>
                                    </div>
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