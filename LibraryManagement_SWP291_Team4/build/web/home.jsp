<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
            <jsp:include page="headerFooter/header.jsp" />
        </header>

        <!-- Categories Start -->
        <div class="container-fluid pt-5">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Categories</span></h2>
            <div class="row px-xl-5 pb-3">

                <c:if test="${requestScope.categoryDisplayers != null}">
                    <c:forEach items='${requestScope.categoryDisplayers}' var='c'>
                        <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                            <a class="text-decoration-none" href="${pageContext.request.contextPath}/book?cid=${c.id}">
                                <div class="cat-item d-flex align-items-center mb-4">
                                    <div class="overflow-hidden" style="width: 100px; height: 100px;">
                                        <img class="img-fluid" src="${c.image}" alt="">
                                    </div>
                                    <div class="flex-fill pl-3">
                                        <h6>${c.name}</h6>
                                        <small class="text-body">${c.bookTotal} books</small>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </c:if>

            </div>
        </div>
        <!-- Categories End -->


        <!-- Products Start -->
        <div class="container-fluid pt-5 pb-3">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Featured Products</span></h2>
            <div class="row px-xl-5">
                <c:if test="${requestScope.booksWithAuthors != null}">
                    <c:forEach items='${requestScope.booksWithAuthors}' var='b'>
                        <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                            <div class="product-item bg-light mb-4">
                                <div class="product-img position-relative overflow-hidden">
                                    <a href="${pageContext.request.contextPath}/bookDetail?id=${b.book.id}"><img class="img-fluid w-100" src="${b.book.image}" alt=""></a>

                                </div>
                                <div class="text-center py-4">
                                    <a class="h6 text-decoration-none text-truncate" href="${pageContext.request.contextPath}/bookDetail?id=${b.book.id}">${b.book.title}</a>
                                    <div class="d-flex align-items-center justify-content-center mt-2">
                                        <h5>By ${b.author.fullname}</h5>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-center mt-2">
                                        <c:forEach begin="1"  end="${b.book.rate}" var="i">         
                                            <small class="fa fa-star text-primary mr-1"></small>
                                        </c:forEach>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-center mt-2">
                                        <small>${b.book.quantity} left</small>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <!-- Products End -->

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