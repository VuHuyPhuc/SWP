<%-- 
    Document   : dashboard
    Created on : Oct 30, 2022, 4:13:20 PM
    Author     : s
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard - Mazer Admin Dashboard</title>

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.css">

        <link rel="stylesheet" href="iconly/bold.css">

        <link rel="stylesheet" href="css/perfect-scrollbar.css">
        <link rel="stylesheet" href="css/bootstrap-icons.css">
        <link rel="stylesheet" href="css/app.css">
    </head>
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
                                <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar-menu">
                        <ul class="menu">
                            <li class="sidebar-title">Menu</li>

                            <li class="sidebar-item active ">
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

                            <li class="sidebar-item">
                                <a href="bookmanagement" class='sidebar-link'>
                                    <i class="bi bi-book"></i>
                                    <span>Book Management</span>
                                </a>

                            </li>

                            


                    </div>
                    <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
                </div>
            </div>
            <div id="main" style="background-color: #f2f7ff;">
                <header class="mb-3">
                    <a href="#" class="burger-btn d-block d-xl-none">
                        <i class="bi bi-justify fs-3"></i>
                    </a>
                </header>

                <div class="page-heading">
                    <h3>Dashboard Statistics</h3>
                </div>
                <div class="page-content">
                    <section class="row">
                        <!--nhan-->
                        <div class="col-12 col-lg-9">
                            <div class="row">
                                <div class="col-6 col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body px-3 py-4-5">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="stats-icon purple">
                                                        <i class="iconly-boldShow"></i>
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <h6 class="text-muted font-semibold">Total Books</h6>
                                                    <h6 class="font-extrabold mb-0">${requestScope.countbook}</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6 col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body px-3 py-4-5">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="stats-icon blue">
                                                        <i class="iconly-boldProfile"></i>
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <h6 class="text-muted font-semibold">Total Users</h6>
                                                    <h6 class="font-extrabold mb-0">${requestScope.countuser}</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6 col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body px-3 py-4-5">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="stats-icon green">
                                                        <i class="iconly-boldAdd-User"></i>
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <h6 class="text-muted font-semibold">Borrowed Today</h6>
                                                    <h6 class="font-extrabold mb-0">${requestScope.countborrowing}</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6 col-lg-3 col-md-6">
                                    <div class="card">
                                        <div class="card-body px-3 py-4-5">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="stats-icon red">
                                                        <i class="iconly-boldBookmark"></i>
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <h6 class="text-muted font-semibold">Returned Today</h6>
                                                    <h6 class="font-extrabold mb-0">${requestScope.countreturn}</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4>Number of orders monthly</h4>
                                        </div>
                                        <div class="card-body">
                                            <div id="chart"></div>
                                            <script src="js/apexcharts.min.js"></script>
                                            <script>
                                                var codesJS = new Array();
                                                <% int[] codes=(int[])request.getAttribute("arraydashboard");
                                                if(codes!=null){
                                                    for(int i=0; i<codes.length; i++){ %>
                                                var code = <%= codes[i] %>;
                                                codesJS[<%= i %>] = code;
                                                <%}}%>
                                                var options = {
                                                    annotations: {
                                                        position: 'back'
                                                    },
                                                    dataLabels: {
                                                        enabled: false
                                                    },
                                                    chart: {
                                                        type: 'bar',
                                                        height: 300
                                                    },
                                                    fill: {
                                                        opacity: 1
                                                    },
                                                    plotOptions: {
                                                    },
                                                    series: [{
                                                            name: 'orders',
                                                            data: codesJS
                                                        }],
                                                    colors: '#435ebe',
                                                    xaxis: {
                                                        categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                                    },
                                                };

                                                var chart = new ApexCharts(document.querySelector("#chart"), options);
                                                chart.render();
                                            </script>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- het nhan-->
                    </section>
                </div>


            </div>
        </div>
        <script src="js/perfect-scrollbar.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/apexcharts.js"></script>

        <script src="js/dashboard.js"></script>

        <script src="js/mainManager.js"></script>
    </body>
</html>
