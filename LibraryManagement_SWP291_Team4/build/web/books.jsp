<!DOCTYPE html>
<html lang="en">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <script type="text/javascript">
            function myFunction(obj) {

                var checkBox = document.getElementsByName("cid");
                if ((obj.id == 'book-all') && (checkBox[0].checked == true)) {
                    for (var i = 1; i < checkBox.length; i++) {
                        checkBox[i].checked = false;
                    }
                } else {
                    for (var i = 1; i < checkBox.length; i++) {
                        if (checkBox[i].checked == true)
                            checkBox[0].checked = false;
                    }
                }

                for (var i = 0; i < checkBox.length; i++) {
                    if (checkBox[i].checked == true) {
                        for (var z = 0; z < checkBox.length; z++) {
                            checkBox[z].checked = false;
                        }
                        checkBox[i].checked = true
                        break;
                    }
                }
            }
        </script>
    </head>

    <body>
        <header>
            <jsp:include page="headerFooter/header.jsp" />
        </header>


        <!-- Breadcrumb Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-12">
                    <nav class="breadcrumb bg-light mb-30">
                        <a class="breadcrumb-item text-dark" href="#">Home</a>
                        <a class="breadcrumb-item text-dark" href="#">Books</a>
                        <span class="breadcrumb-item active">Books List</span>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->


        <!-- Shop Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <!-- Shop Sidebar Start -->
                <div class="col-lg-3 col-md-4">
                    <!-- Price Start -->
                    <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Filter by Category</span></h5>
                    <div class="bg-light p-4 mb-30">
                        <form id='f1'action="book" method="POST">
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input  type="checkbox" class="custom-control-input" ${requestScope.cid==null?'checked':''} id="book-all">
                                <label class="custom-control-label" for="book-all">All Books</label>

                            </div>

                            <c:set var="num" value="${1}"></c:set>
                            <c:forEach items="${requestScope.categorylist}" var="c">         

                                <c:set var="id" value="book-"></c:set>
                                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                        <input  type="checkbox" class="custom-control-input" id="${id}${num}" name="cid" value="${c.id}" ${requestScope.cid==c.id?'checked':''}>
                                    <label class="custom-control-label" for="${id}${num}">${c.name}</label>

                                </div>
                                <c:set var ="num" value="${num+1}"></c:set>
                            </c:forEach>
                            <input type="submit" class="btn btn-block btn-primary font-weight-bold my-3 py-3" value="Filter">
                        </form>
                    </div> 
                    <!-- Price End -->

                </div>
                <!-- Shop Sidebar End -->


                <!-- Shop Product Start -->
                <div class="col-lg-9 col-md-8">
                    <div class="row pb-3">


                        <c:forEach items="${requestScope.booklist}" var="bookAuthor">
                            <div class="col-lg-4 col-md-6 col-sm-6 pb-1">
                                <div class="product-item bg-light mb-4">
                                    <div class="product-img position-relative overflow-hidden">
                                        <img class="img-fluid w-100" src="${bookAuthor.book.image}" alt="">
                                        <div class="product-action">
                                            <a class="btn btn-outline-dark btn-square" href="appointmentListCart?action=addToCart&bookId=${bookAuthor.book.id}"><i class="fa fa-shopping-cart"></i></a>
                                            <a class="btn btn-outline-dark btn-square" href="myReservation?action=addToReservationList&bookId=${bookAuthor.book.id}"><i class="far fa-heart"></i></a>
                                            <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-sync-alt"></i></a>
                                            <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>
                                        </div>
                                    </div>
                                    <div class="text-center py-4">
                                        <a class="h6 text-decoration-none text-truncate" href="bookDetail?id=${bookAuthor.book.id}">${bookAuthor.book.title}</a>
                                        <div class="d-flex align-items-center justify-content-center mt-2">
                                            <h5>${bookAuthor.author.fullname}</h5>
                                        </div>
                                        <div class="d-flex align-items-center justify-content-center mb-1">
                                            <c:forEach begin="1"  end="${bookAuthor.book.rate}" var="i">         
                                                <small class="fa fa-star text-primary mr-1"></small>
                                            </c:forEach>
                                        </div>
                                        <div class="d-flex align-items-center justify-content-center mt-2">
                                            <small>${bookAuthor.book.quantity} left</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="col-12">
                            <nav>

                                <ul class="pagination justify-content-center">
                                    <c:if test="${param.search != null}">
                                        <li class="page-item ${requestScope.index == 1?'disabled':''}"><a class="page-link" href="book?search=${param.search}&index=${index-1}">Previous</span></a></li>
                                        </c:if>
                                        <c:if test="${param.search == null}">
                                            <c:if test="${param.cid != null}">
                                            <li class="page-item ${requestScope.index == 1?'disabled':''}"><a class="page-link" href="book?cid=${param.cid}&index=${index-1}">Previous</span></a></li>
                                            </c:if>
                                            <c:if test="${param.cid == null}">
                                            <li class="page-item ${requestScope.index == 1?'disabled':''}"><a class="page-link" href="book?index=${index-1}">Previous</a></li>

                                        </c:if>


                                    </c:if>
                                    <c:forEach var="i" begin="1" end="${requestScope.page}">
                                        <c:if test="${param.search != null}">
                                            <li class="page-item ${requestScope.index == i?'active':''}"><a class="page-link" href="book?search=${param.search}&index=${i}">${i}</a></li>
                                            </c:if>
                                            <c:if test="${param.search == null}">
                                                <c:if test="${param.cid != null}">
                                                <li class="page-item ${requestScope.index == i?'active':''}"><a class="page-link" href="book?cid=${param.cid}&index=${i}">${i}</a></li>
                                                </c:if>
                                                <c:if test="${param.cid == null}">
                                                <li class="page-item ${requestScope.index == i?'active':''}"><a class="page-link" href="book?index=${i}">${i}</a></li>
                                                </c:if>
                                            </c:if>

                                    </c:forEach>
                                    <c:if test="${param.search != null}">
                                        <li class="page-item ${requestScope.index == page?'disabled':''}"><a class="page-link" href="book?search=${param.search}&index=${index+1}">Next</span></a></li>
                                        </c:if>
                                        <c:if test="${param.search == null}">
                                            <c:if test="${param.cid != null}">
                                            <li class="page-item ${requestScope.index == page?'disabled':''}"><a class="page-link" href="book?cid=${param.cid}&index=${index+1}">Next</span></a></li>
                                            </c:if>
                                            <c:if test="${param.cid == null}">
                                            <li class="page-item ${requestScope.index == page?'disabled':''}"><a class="page-link" href="book?index=${index+1}">Next</a></li>

                                        </c:if>


                                    </c:if>
                                </ul>
                            </nav>
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