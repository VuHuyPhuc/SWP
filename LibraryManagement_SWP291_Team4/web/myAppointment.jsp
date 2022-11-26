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
                        <a class="breadcrumb-item text-dark" href="myAppointment">My Appointment</a>
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
                        <form id='f1' action="myAppointment" method="POST">
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input  type="checkbox" class="custom-control-input" checked id="book-all" name="category" value="allBook">
                                <label class="custom-control-label" for="book-all">All Books</label>
                            </div>
                            
                            <c:set var="num" value="${1}"></c:set>
                            <c:forEach items="${requestScope.categorylist}" var="c">         

                                <c:set var="id" value="book-"></c:set>
                                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                        <input  type="checkbox" class="custom-control-input" id="${id}${num}" name="category" value="${c.name}">
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
                        <div class="col-12 pb-1">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <div class="ml-2">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Sorting</button>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="myAppointment?sort=lastest">Latest</a>
                                            <a class="dropdown-item" href="myAppointment?sort=newest">Newest</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                       
                    <table class="table table-light table-borderless table-hover text-left mb-0">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>NO.</th>
                                        <th>Image</th>
                                        <th>Book</th>
                                        <th>Date to get book</th>
                                        <th>Detail</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody class="align-middle">
                                <c:set var="count" value="${1}"></c:set>
                                    <c:forEach items="${lsAppointment}" var="a">
                                        <tr><td>${count}</td>
                                            <td><img src="${a.book.image}" alt="" style="width: 50px;"></td>
                                            <td style="text-align: left;" class="align-middle">${a.book.title}</td>
                                            <td class="align-middle">
                                                <div class="input-group quantity" style="width: 200px;">
                                                    <input type="date" disabled="" class="form-control form-control-sm bg-secondary border-0 text-center" value="${a.date}">
                                                </div>
                                            </td>
                                            <td><a href="myAppoinmentDetail?appointmentId=${a.appointment_id}">See More</a></td>
                                            <td class="align-middle">
                                                <a href="myAppointment?action=delete&appointmentId=${a.appointment_id}">
                                                    <button class="btn btn-sm btn-danger">
                                                        <i class="fa fa-times"></i>
                                                    </button>
                                                </a>
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count+1}"></c:set>
                                    </c:forEach>
                                </tbody>
                            </table>
                        <div class="col-12">
                            <nav>
<!--                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled"><a class="page-link" href="#">Previous</span></a></li>
                                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                                </ul>-->
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