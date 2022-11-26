<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Loan list</title>
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
            <jsp:include page="headerFooter/header.jsp" />
        </header>
        <!-- Header End -->

        <!-- Cart Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-lg-8 table-responsive mb-5">
                    <table class="table table-light table-borderless table-hover text-center mb-0">
                        <thead class="thead-dark">
                            <tr>
                                <th>Book image</th>
                                <th>Book title</th>
                                <th>Actual return date</th>
                                <th>Borrowing date</th>
                                <th>Return date</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody class="align-middle">
                            <c:if test="${requestScope.loans != null}">
                                <c:forEach items="${requestScope.loans}" var="l">
                                    <tr>
                                        <td class="align-middle"><img src="${l.book.image}" alt="" style="width: 50px;"></td>
                                        <td class="align-middle">
                                            <div class="input-group quantity mx-auto" style="width: 200px;">
                                                ${l.book.title}
                                            </div>
                                        </td>
                                        <td class="align-middle"><fmt:formatDate value="${l.dateActual}" pattern="dd/MM/yyyy" /></td>
                                        <td class="align-middle"><fmt:formatDate value="${l.dateFrom}" pattern="dd/MM/yyyy" /></td>
                                        <td class="align-middle"><fmt:formatDate value="${l.dateTo}" pattern="dd/MM/yyyy" /></td>
                                        <td class="align-middle">${l.status}</td>
                                        <td class="align-middle"><a href="${pageContext.request.contextPath}/loan?id=${l.id}">See detail</a></td>

                                    </tr>
                                </c:forEach>
                            </c:if>

                        </tbody>

                    </table>
                    <div>
                        <nav>
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${requestScope.index == 1?'disabled':''}"><a class="page-link" href="overdue-list?index=${requestScope.index-1}">Previous</a></li>
                                    <c:forEach var="i" begin="1" end="${requestScope.maxIndex}">
                                    <li class="page-item ${requestScope.index == i?'active':''}"><a class="page-link" href="overdue-list?index=${i}">${i}</a></li>
                                    </c:forEach>
                                <li class="page-item ${requestScope.index == maxIndex?'disabled':''}"><a class="page-link" href="overdue-list?index=${requestScope.index+1}">Next</a></li>
                            </ul>
                        </nav>
                    </div>
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