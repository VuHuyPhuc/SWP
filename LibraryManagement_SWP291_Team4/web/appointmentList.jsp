<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DataTable - Mazer Admin Dashboard</title>

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.css">

        <link rel="stylesheet" href="css/style.css">

        <link rel="stylesheet" href="css/perfect-scrollbar.css">
        <link rel="stylesheet" href="css/bootstrap-icons.css">
        <link rel="stylesheet" href="css/app.css">
        <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
    </head>

    <style>
        .dialog {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: 10;
            display: flex;
            align-items: center;
            justify-content: center;
            visibility: hidden;
        }
        .dialog:target {
            visibility: visible;
        }
        .overlay {
            background-color: rgba(0,0,0,0.5); /* Black background with opacity */
        }
        .overlay-close {
            position: absolute;
            width: 100%;
            height: 100%;
            cursor: default;
        }
        .dialog-body {
            width: 60%;
            position: relative;
            padding: 48px;
            background-color: white;
        }
        .dialog-btn-close {
            position: absolute;
            top: 2px;
            right: 6px;
            text-decoration: none;
            color: red;
            font-size: 30px;
        }
        .dialog-btn {
            position: absolute;
            width: 6%;
            left: 47%;
            bottom: 15px;
            background-color: gray;
            border: none;
            color: black;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
        }
    </style>

    <body>
        <div id="app">
            <div id="sidebar" class="active">
                <div class="sidebar-wrapper active">
                    <div class="sidebar-header">
                        <div class="d-flex justify-content-between">
                            <div class="logo">
                                <a href="index.html"><img src="img/logo/logo.png" alt="Logo" srcset=""></a>
                            </div>
                            <div class="toggler">
                                <a href="dashboard" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar-menu">
                        <ul class="menu">
                            <li class="sidebar-title">Menu</li>

                            <li class="sidebar-item">
                                <a href="dashboard" class='sidebar-link'>
                                    <i class="bi bi-grid-fill"></i>
                                    <span>Dashboard</span>
                                </a>
                            </li>
                            <li class="sidebar-item active">
                                <a href="appointmentList" class='sidebar-link'>
                                    <i class="bi bi-grid-fill"></i>
                                    <span>My Appointment</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="reservationList" class='sidebar-link'>
                                    <i class="bi bi-grid-fill"></i>
                                    <span>My Reservation</span>
                                </a>
                            </li>

                            <li class="sidebar-item">
                                <a href="bookmanagement" class='sidebar-link'>
                                    <i class="bi bi-book"></i>
                                    <span>Book Management</span>
                                </a>
                            </li>

                            
                        </ul>
                    </div>
                    <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
                </div>
            </div>
            <div id="main">
                <header class="mb-3">
                    <a href="#" class="burger-btn d-block d-xl-none">
                        <i class="bi bi-justify fs-3"></i>
                    </a>
                </header>

                <div class="page-heading">
                    <div class="page-title">
                        <div class="row">
                            <div class="col-12 col-md-6 order-md-1 order-last">
                                <h3>Appointment List</h3>
                            </div>
                            <div class="col-12 col-md-6 order-md-2 order-first">
                                <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                    <form action="appointmentList?action=search" method="POST">
                                        Category:<select name="categoryId">
                                            <option value="all" selected>All</option>
                                            <c:forEach items="${categorylist}" var="c">
                                                <option value="${c.id}">${c.name}</option>
                                            </c:forEach>
                                        </select>
                                        Status:<select name="status">
                                            <option value="all" selected>All</option>
                                            <option value="pending">pending</option>
                                            <option value="rejected">rejected</option>
                                            <option value="complete">complete</option>
                                        </select>
                                        Date after:<input type="date" name="dateFrom" required="" value="2000-01-01"> 
                                        before: <input type="date" name="dateTo" required="" value="2050-01-01">
                                        <input style="width: 80%;" name="userFullname" placeholder="userFullname">
                                        <input style="width: 80%;" name="bookTitle" placeholder="bookTitle">
                                        <input type="submit" value="search">
                                    </form>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <section class="section">
                        <div class="card">
                            <div class="card-header">
                                <!--<h1>Appointment List</h1>-->
                                <a style="position: initial;
                                   background-color: white;" class="dialog-btn" href="#add-appointment">Add new &CirclePlus;</a>
                            </div>
                            <div class="card-body">
                                <!-- table for appointment list start -->
                                <table class="table table-striped" id="table1">
                                    <thead>
                                        <tr>
                                            <th>NO.</th>
                                            <th>Book Image</th>
                                            <th>Book Title</th>
                                            <th>Member</th>
                                            <th>Date to get book</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="count" value="${1}"></c:set>
                                        <c:forEach items="${listAppointment}" var="a">
                                            <tr>
                                                <td>${count}</td>
                                                <td><img src="${a.book.image}" alt="" style="width: 50px;"></td>
                                                <td>${a.book.title}</td>
                                                <td>${a.user_fullname}</td>
                                                <td>${a.date}</td>
                                                <td>
                                                    <span style="color: yellow;" class="${a.status == 'pending' ? "badge bg-success" : "badge bg-warning"}">${a.status}</span>
                                                </td>
                                                <td>
                                                    <a class="dialog-edit-btn" href="#edit-appointment${count-1}" class='sidebar-link'>
                                                        <i class="bi bi-pencil"></i>
                                                    </a>
                                                    &nbsp;
                                                    <a href="appointmentList?action=delete&appointmentId=${a.appointment_id}" class='sidebar-link'>
                                                        <i class="bi bi-trash"></i>
                                                    </a>
                                                    &nbsp;
                                                    <a class="dialog-view-btn" href="#view-appointment${count-1}" class='sidebar-link'>
                                                        <i class="bi bi-eye"></i>                                                    
                                                    </a>
                                                </td>
                                            </tr>
                                            <c:set var="count" value="${count+1}"></c:set>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- table for appointment list end -->
                                <!-- modal for add new appointment start -->
                                <div class="dialog overlay" id="add-appointment">
                                    <a class="overlay-close" href="#"></a>
                                    <div class="dialog-body row">
                                        <h1>Add new Appointment</h1>
                                        <form action="appointmentList?action=add" method="POST">
                                            <table border="0">
                                                <tbody>
                                                    <tr>
                                                        <td style="visibility: hidden;">some more space</td>
                                                        <td>
                                                            <div class="form-group">
                                                                <img src="">
                                                            </div>
                                                        </td>
                                                        <td style="visibility: hidden;">some more space</td>
                                                        <td>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Book Id:</label>
                                                                <input type="number" class="form-control" name="bookId" min="1" required="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Title:</label>
                                                                <input disabled="" type="text" class="form-control" name="bookTitle">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Member id:</label>
                                                                <input type="number" class="form-control" name="userId" min="1" required="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Date:</label>
                                                                <input type="date" class="form-control" name="dateToGet" required="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Status:</label>
                                                                <select class="form-control" name="status">
                                                                    <option value="pending">pending</option> <!-- ${condition ? 'some text when true' : 'some text when false'} -->
                                                                    <option value="rejected">rejected</option>
                                                                    <option value="completed">completed</option>
                                                                </select>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <input type="submit" value="Add">
                                            <a class="dialog-btn-close" href="#">&times;</a>
                                        </form>
                                    </div>
                                </div>
                                <!-- modal for add new appointment end -->
                                <!-- modal for edit appointment start -->
                                <c:set var="indexOfAppointment" value="${0}"></c:set>
                                <c:forEach items="${listAppointment}" var="a">
                                    <div class="dialog overlay" id="edit-appointment${indexOfAppointment}">
                                        <c:set var="indexOfAppointment" value="${indexOfAppointment+1}"></c:set>
                                            <a class="overlay-close" href="#"></a>
                                            <div class="dialog-body row">
                                                <h1>Edit Appointment</h1>
                                                <form action="appointmentList?action=edit" method="POST">
                                                    <table border="0">
                                                        <tbody>
                                                            <tr>
                                                                <td style="visibility: hidden;">some more space</td>
                                                                <td>
                                                                    <div class="form-group">
                                                                        <img src="${a.book.image}">
                                                                </div>
                                                            </td>
                                                            <td style="visibility: hidden;">some more space</td>
                                                            <td>
                                                                <input type="hidden" class="form-control" name="appointmentId" value="${a.appointment_id}">
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Book Id:</label>
                                                                    <input type="number" class="form-control" name="bookId" value="${a.book.id}" min="1" required="">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Title:</label>
                                                                    <input disabled="" type="text" class="form-control" name="bookTitle" value="${a.book.title}">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Member id:</label>
                                                                    <input type="number" class="form-control" name="userId" value="${a.user_id}" min="1" required="">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Date:</label>
                                                                    <input type="date" class="form-control" name="dateToGet" value="${a.date}" required="">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Status:</label>
                                                                    <select class="form-control" name="status">
                                                                        <option value="pending" ${a.status == 'pending' ? "selected" : ""}>pending</option> <!-- ${condition ? 'some text when true' : 'some text when false'} -->
                                                                        <option value="rejected" ${a.status == 'rejected' ? "selected" : ""}>rejected</option>
                                                                        <option value="completed" ${a.status == 'completed' ? "selected" : ""}>completed</option>
                                                                    </select>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <input type="submit" value="Save">
                                                <a class="dialog-btn-close" href="#">&times;</a>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- modal for edit appointment end -->
                                <!-- modal for view appointment start -->
                                <c:set var="indexOfAppointment" value="${0}"></c:set>
                                <c:forEach items="${listAppointment}" var="a">
                                    <div class="dialog overlay" id="view-appointment${indexOfAppointment}">
                                        <c:set var="indexOfAppointment" value="${indexOfAppointment+1}"></c:set>
                                            <a class="overlay-close" href="#"></a>
                                            <div class="dialog-body row">
                                                <h1>View Appointment Detail</h1>
                                                <table border="0">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td style="visibility: hidden;">some more space</td>
                                                            <td>
                                                                <div class="form-group">
                                                                    <img src="${a.book.image}">
                                                            </div>
                                                        </td>
                                                        <td style="visibility: hidden;">some more space</td>
                                                        <td>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Book Id:</label>
                                                                <input type="number" class="form-control" name="bookId" value="${a.book.id}" disabled="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Title:</label>
                                                                <input type="text" class="form-control" name="bookTitle" value="${a.book.title}" disabled="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Member id:</label>
                                                                <input type="number" class="form-control" name="userId" value="${a.user_id}" disabled="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Date:</label>
                                                                <input type="date" class="form-control" name="dateToGet" value="${a.date}" disabled="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-form-label">Status:</label>
                                                                <select class="form-control" name="status" disabled="">
                                                                    <option value="pending" ${a.status == 'pending' ? "selected" : ""}>pending</option> <!-- ${condition ? 'some text when true' : 'some text when false'} -->
                                                                    <option value="rejected" ${a.status == 'rejected' ? "selected" : ""}>rejected</option>
                                                                    <option value="completed" ${a.status == 'completed' ? "selected" : ""}>completed</option>
                                                                </select>
                                                            </div>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <a class="dialog-btn" href="#">OK</a>
                                            <a class="dialog-btn-close" href="#">&times;</a>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- modal for view appointment end -->
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <script src="js/perfect-scrollbar.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/mainManager.js"></script>
    </body>

</html>