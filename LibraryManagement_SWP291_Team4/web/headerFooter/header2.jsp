
<div class="container-fluid">
    <div class="row bg-secondary py-1 px-xl-5">
        <div class="col-lg-6 d-none d-lg-block">
            <div class="d-inline-flex align-items-center h-100">
<!--                <a class="text-body mr-3" href="">About</a>
                <a class="text-body mr-3" href="">Contact</a>-->
            </div>
        </div>
        <div class="col-lg-6 text-center text-lg-right">
            <div class="d-inline-flex align-items-center">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">My Account</button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <button class="dropdown-item" type="button" onclick="window.location.href='userprofile'">User Profile</button>
                        <button class="dropdown-item" type="button" onclick="window.location.href='changepassword'">Change password</button>
                        <button class="dropdown-item" type="button" onclick="window.location.href='logout'">Logout</button>
                    </div>
                </div>
            </div>
            <div class="d-inline-flex align-items-center d-block d-lg-none">
                <a href="" class="btn px-0 ml-2">
                    <i class="fas fa-heart text-dark"></i>
                    <span class="badge text-dark border border-dark rounded-circle" style="padding-bottom: 2px;">0</span>
                </a>
                <a href="" class="btn px-0 ml-2">
                    <i class="fas fa-shopping-cart text-dark"></i>
                    <span class="badge text-dark border border-dark rounded-circle" style="padding-bottom: 2px;">0</span>
                </a>
            </div>
        </div>
    </div>
    <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
        <div class="col-lg-4">
            <a href="" class="text-decoration-none">
                <span class="h1 text-uppercase text-primary bg-dark px-2">Library</span>
                <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Management</span>
            </a>
        </div>
        <div class="col-lg-4 col-6 text-left">
            <form action="">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for books">
                    <div class="input-group-append">
                        <span class="input-group-text bg-transparent text-primary">
                            <i class="fa fa-search"></i>
                        </span>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-4 col-6 text-right">
            <p class="m-0">Library Service</p>
            <h5 class="m-0">+012 345 6789</h5>
        </div>
    </div>
</div>
<!-- Topbar End -->

<!-- Navbar Start -->
<div class="container-fluid bg-dark mb-30">
    <div class="row px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a class="btn d-flex align-items-center justify-content-between bg-primary w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; padding: 0 30px;">
                <h6 class="text-dark m-0"><i class="fa fa-bars mr-2"></i>Categories</h6>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 999;">
                <div class="navbar-nav w-100">
                    
                    <a href="" class="nav-item nav-link">Action</a>
                    <a href="" class="nav-item nav-link">Comedy</a>
                    <a href="" class="nav-item nav-link">Adventure</a>
                    <a href="" class="nav-item nav-link">Crime</a>
                    <a href="" class="nav-item nav-link">Sci-Fiction</a>
                    <a href="" class="nav-item nav-link">Mystery</a>
                    <a href="" class="nav-item nav-link">Religion</a>
                    <a href="" class="nav-item nav-link">Documentary</a>
                    <a href="" class="nav-item nav-link">Romance</a>
                    <a href="" class="nav-item nav-link">Fantasy</a>
                    <a href="" class="nav-item nav-link">War</a>
                    <a href="" class="nav-item nav-link">Horror</a>
                    <a href="" class="nav-item nav-link">Thriller</a>
                    <a href="" class="nav-item nav-link">Drama</a>
                </div>
            </nav>
        </div>
        <div class="col-lg-9">
            <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                <a href="" class="text-decoration-none d-block d-lg-none">
                    <span class="h1 text-uppercase text-dark bg-light px-2">Library</span>
                    <span class="h1 text-uppercase text-light bg-primary px-2 ml-n1">Management</span>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="home.jsp" class="nav-item nav-link">Home</a>
                        <a href="book" class="nav-item nav-link">Books</a>
                        <!--<a href="detail.html" class="nav-item nav-link">Books Detail</a>-->
                        <a href="authors.jsp" class="nav-item nav-link">Authors</a>
                        <a href="myAppointment" class="nav-item nav-link">My Appointment</a>
                        <a href="myReservation" class="nav-item nav-link">My Reservation</a>
                        <a href="books.jsp" class="nav-item nav-link">Loan</a>
                        <a href="blog" class="nav-item nav-link">Blog</a>

                        
                        
<!--                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages <i class="fa fa-angle-down mt-1"></i></a>
                            <div class="dropdown-menu bg-primary rounded-0 border-0 m-0">
                                <a href="cart.html" class="dropdown-item">Shopping Cart</a>
                                <a href="checkout.html" class="dropdown-item">Checkout</a>
                            </div>
                        </div>
                        <a href="contact.html" class="nav-item nav-link">Contact</a>-->
                    </div>
                    <div class="navbar-nav ml-auto py-0 d-none d-lg-block">
                        <a href="" class="btn px-0">
                            <i class="fas fa-heart text-primary"></i>
                            <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">0</span>
                        </a>
                        <a href="" class="btn px-0 ml-3">
                            <i class="fas fa-shopping-cart text-primary"></i>
                            <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">0</span>
                        </a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<!-- Navbar End -->
