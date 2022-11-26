<%-- 
    Document   : bookmanagement
    Created on : Oct 30, 2022, 11:53:36 PM
    Author     : s
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="dao.impl.CategoryDAOImpl"%>
<%@page import="entity.Category"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Management</title>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/style_table.css">
        <script src="js/bootstrap4.bundle.min.js"></script>        
        <link rel="stylesheet" href="css/perfect-scrollbar.css">
        <link rel="stylesheet" href="css/bootstrap-icons.css">
        <link rel="stylesheet" href="css/app.css">
        <link rel="shortcut icon" href="assets/images/favicon.svg" type="image/x-icon">
    </head>

    <body>
        <div id="app">
            <div id="sidebar" class="active">
                <div class="sidebar-wrapper active">
                    <div class="sidebar-header">
                        <div class="d-flex justify-content-between">
                            <div class="logo">
                                <a href="dashboard"><img src="img/logo/logo.png" alt="Logo" srcset=""></a>
                            </div>
                            <div class="toggler">
                                <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
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
                            <li class="sidebar-item">
                                <a href="appointmentList" class='sidebar-link'>
                                    <i class="bi bi-grid-fill"></i>
                                    <span>My Appointment</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class='sidebar-link'>
                                    <i class="bi bi-grid-fill"></i>
                                    <span>My Reservation</span>
                                </a>
                            </li>
                            <li class="sidebar-item active">
                                <a href="table-datatable.html" class='sidebar-link'>
                                    <i class="bi bi-book"></i>
                                    <span>Book Management</span>
                                </a>

                            </li>

                            


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
                                <h3>Book Management</h3>
                                <p class="text-subtitle text-muted">For user to check they list</p>
                            </div>
                            <div class="col-12 col-md-6 order-md-2 order-first">
                                <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="dashboard">Dashboard</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Book Management</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <section class="section">
                        <div class="card">
                            <div class="card-header"> 
                                <button data-toggle="modal" data-target="#modalAdd">Add a new book</button>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped" id="table1">
                                    <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Category</th>
                                            <th>Publisher</th>
                                            <th>Date</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${requestScope.booklist}" var="c">
                                            <c:set value="${c.category_id}" var="i"></c:set>
                                                <tr>

                                                    <td>${c.title}</td>
                                                <td>
                                                    ${c.category_id.name}
                                                </td>
                                                <td>${c.publisher}</td>
                                                <td>${c.publication_year}</td>
                                                <td>
                                                    <span class="badge bg-${c.status=='true'?'success':'danger'}">${c.status=='true'?'Active':'Inactive'}</span>
                                                </td>
                                                <td>
                                                    <!--Modal trigger-->
                                                    <a data-toggle="modal" data-target="#modalEdit" class='sidebar-link clicking' data-book_id="${c.id}" data-title="${c.title}" data-category="${c.category_id.id}" data-publisher="${c.publisher}" data-quantity="${c.quantity}" data-date="${c.publication_year}" data-image="${c.image}" data-rate="${c.rate}" data-status="${c.status=='true'?'1':'0'}" data-introduction="${c.introduction}" data-description="${c.description}">
                                                        <i class="bi bi-pencil"></i>
                                                    </a>
                                                    &nbsp;
                                                    <a data-toggle="modal" data-target="#modalView" class='sidebar-link clickingView' data-book_id="${c.id}" data-title="${c.title}" data-category="${c.category_id.name}" data-publisher="${c.publisher}" data-quantity="${c.quantity}" data-date="${c.publication_year}" data-image="${c.image}" data-rate="${c.rate}" data-status="${c.status=='true'?'Active':'Inactive'}" data-introduction="${c.introduction}" data-description="${c.description}">

                                                        <i class="bi bi-eye"></i>                                                    
                                                    </a>
                                                    <!-- End Modal trigger -->
                                                </td>
                                            </tr>

                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </section>
                </div>


            </div>
            <div class="modal hide fade" id="modalEdit">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <form action="bookmanagement" method="POST" enctype="multipart/form-data">
                            <input type="hidden" id="bookid" name="id">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Edit Book</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="title" class="col-form-label">Title:</label>
                                    <input type="text" class="form-control" id="title" name="title">
                                </div>
                                <div class="form-group">
                                    <label for="category" class="col-form-label">Category:</label>
                                    <select class="form-control" id="category" name="category">
                                        <c:forEach items="${requestScope.categorylist}" var="i">
                                            <option value="${i.id}">${i.name}</option>
                                        </c:forEach>

                                    </select>                                        
                                </div>
                                <div class="form-group">
                                    <label for="publisher" class="col-form-label">Publisher:</label>
                                    <input type="text" class="form-control" id="publisher" name="publisher">
                                </div>
                                <div class="form-group">
                                    <label for="date" class="col-form-label">Publication Date:</label>
                                    <input type="date" class="form-control" id="date" name="date">
                                </div>
                                <div class="form-group">
                                    <label for="quantity" class="col-form-label">Quantity:</label>
                                    <input type="number" class="form-control" id="quantity" name="quantity">
                                </div>
                                <div class="form-group">
                                    <label for="image" class="col-form-label">Image:</label>                                   
                                    <img class="form-control" id="image" name="image" style="height: fit-content"/>
                                    <input type="file" name="newimage">
                                </div>
                                <div class="form-group ">
                                    <label for="rate" class="col-form-label">Rate:</label>
                                    <input type="number" class="form-control" id="rate" name="rate">
                                </div>
                                <div class="form-group">
                                    <label for="status" class="col-form-label">Status:</label>
                                    <select class="form-control" id="status" name="status">
                                        <option value="1">Active</option>
                                        <option value="0">Inactive</option>
                                    </select>

                                </div>
                                <div class="form-group">
                                    <label for="introduction" class="col-form-label">Introduction:</label>
                                    <input type="text" class="form-control" id="introduction" name="introduction">
                                </div>
                                <div class="form-group">
                                    <label for="description" class="col-form-label">Description:</label>
                                    <input type="text" class="form-control" id="description" name="description">
                                </div>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <input type="submit" value="Save" class="btn btn-primary"/>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal hide fade" id="modalView">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">


                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Book Information</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="title" class="col-form-label">Title:</label>
                                <input type="text" class="form-control" id="titles" disabled>
                            </div>
                            <div class="form-group">
                                <label for="category" class="col-form-label">Category:</label>
                                <input type="text" class="form-control" id="categorys" disabled>
                            </div>
                            <div class="form-group">
                                <label for="publisher" class="col-form-label">Publisher:</label>
                                <input type="text" class="form-control" id="publishers" name="publisher" disabled>
                            </div>
                            <div class="form-group">
                                <label for="date" class="col-form-label">Publication Date:</label>
                                <input type="date" class="form-control" id="dates" name="date" disabled>
                            </div>
                            <div class="form-group">
                                <label for="quantity" class="col-form-label">Quantity:</label>
                                <input type="number" class="form-control" id="quantitys" name="quantity" disabled>
                            </div>
                            <div class="form-group">
                                <label for="image" class="col-form-label">Image:</label>                                   
                                <img class="form-control" id="images" name="image" style="height: fit-content"/>

                            </div>
                            <div class="form-group ">
                                <label for="rate" class="col-form-label">Rate:</label>
                                <input type="number" class="form-control" id="rates" name="rate" disabled>
                            </div>
                            <div class="form-group">
                                <label for="status" class="col-form-label">Status:</label>
                                <input type="text" class="form-control" id="statuss" name="status" disabled>
                            </div>
                            <div class="form-group">
                                <label for="introduction" class="col-form-label">Introduction:</label>
                                <input type="text" class="form-control" id="introductions" name="introduction" disabled>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-form-label">Description:</label>
                                <input type="text" class="form-control" id="descriptions" name="description" disabled>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </div>
            
            <div class="modal hide fade" id="modalAdd">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">


                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Add a new book</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <form action="bookmanagement" method="POST" enctype="multipart/form-data">

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="title" class="col-form-label">Title:</label>
                                <input type="text" class="form-control" name="title">
                            </div>
                            <div class="form-group">
                                <label for="category" class="col-form-label">Category:</label>
                                <select class="form-control" id="category" name="category">
                                        <c:forEach items="${requestScope.categorylist}" var="i">
                                            <option value="${i.id}">${i.name}</option>
                                        </c:forEach>
                                </select>  
                            </div>
                            <div class="form-group">
                                <label for="publisher" class="col-form-label">Publisher:</label>
                                <input type="text" class="form-control" name="publisher" >
                            </div>
                            <div class="form-group">
                                <label for="date" class="col-form-label">Publication Date:</label>
                                <input type="date" class="form-control" name="date" >
                            </div>
                            <div class="form-group">
                                <label for="quantity" class="col-form-label">Quantity:</label>
                                <input type="number" class="form-control" name="quantity">
                            </div>
                            <div class="form-group">
                                <label for="image" class="col-form-label">Image:</label>                                   
                                <input type="file" name="newimage">

                            </div>
                            <div class="form-group ">
                                <label for="rate" class="col-form-label">Rate:</label>
                                <input type="number" class="form-control" name="rate" >
                            </div>
                            <div class="form-group">
                                <label for="status" class="col-form-label">Status:</label>
                                <select class="form-control" id="status" name="status">
                                        <option value="1">Active</option>
                                        <option value="0">Inactive</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="introduction" class="col-form-label">Introduction:</label>
                                <input type="text" class="form-control"  name="introduction">
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-form-label">Description:</label>
                                <input type="text" class="form-control" name="description" >
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <input type="submit" value="Add" class="btn btn-primary"/>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

        <script src="js/perfect-scrollbar.min.js"></script>


        <script src="js/simple-datatables.js"></script>
        <script>
            $(document).ready(function () {
                $(".clicking").click(function () { // Click to only happen on announce links
                    $("#bookid").val($(this).data('book_id'));
                    $("#title").val($(this).data('title'));
                    $("#category").val($(this).data('category'));
                    $("#publisher").val($(this).data('publisher'));
                    $("#date").val($(this).data('date'));
                    $("#quantity").val($(this).data('quantity'));
                    $("#image").attr("src", $(this).data('image'));
                    $("#rate").val($(this).data('rate'));
                    $("#status").val($(this).data('status'));
                    $("#introduction").val($(this).data('introduction'));
                    $("#description").val($(this).data('description'));
                    $('#modalEdit').modal('show');
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $(".clickingView").click(function () { // Click to only happen on announce links
                    $("#titles").val($(this).data('title'));
                    $("#categorys").val($(this).data('category'));
                    $("#publishers").val($(this).data('publisher'));
                    $("#dates").val($(this).data('date'));
                    $("#quantitys").val($(this).data('quantity'));
                    $("#images").attr("src", $(this).data('image'));
                    $("#rates").val($(this).data('rate'));
                    $("#statuss").val($(this).data('status'));
                    $("#introductions").val($(this).data('introduction'));
                    $("#descriptions").val($(this).data('description'));
                    $('#modalView').modal('show');
                });
            });
        </script>
        <script>
            // Simple Datatable
            let table1 = document.querySelector('#table1');
            let dataTable = new simpleDatatables.DataTable(table1);
        </script>

        <script src="js/mainManager.js"></script>
    </body>
</html>
