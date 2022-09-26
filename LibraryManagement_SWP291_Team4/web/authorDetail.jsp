<!DOCTYPE html>
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
                                <img class="w-100 h-100" src="img/authors/KitRocha.PNG" alt="Image">
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
                        <h3>Kit Rocha</h3>
                        <div class="d-flex mb-3">
                            <div class="text-primary mr-2">
                                <small class="fas fa-star"></small>
                                <small class="fas fa-star"></small>
                                <small class="fas fa-star"></small>
                                <small class="fas fa-star-half-alt"></small>
                                <small class="far fa-star"></small>
                            </div>
                            <small class="pt-1">(99 Reviews)</small>
                        </div>
                        <!--<h3 class="font-weight-semi-bold mb-4">$150.00</h3>-->
                        <p class="mb-4">Official Bio

                            Kit Rocha is the pseudonym for co-writing team Donna Herren and Bree Bridges. After penning dozens of paranormal novels, novellas and stories as Moira Rogers, they branched out into gritty, sexy dystopian romance.

                            The Beyond series has appeared on the New York Times and USA Today bestseller lists, has been nominated for best erotic romance in the RT Reviewer?s Choice award five times, and won in 2013 and 2015. Their most recent adventure is partnering with Tor to launch their critically acclaimed Mercenary Librarians series.
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
            </div>
            <div class="row px-xl-5">
                <div class="col">
                    <div class="bg-light p-30">
                        <div class="nav nav-tabs mb-4">
                            <a class="nav-item nav-link text-dark active" data-toggle="tab" href="#tab-pane-1">Information</a>
                            <!--<a class="nav-item nav-link text-dark" data-toggle="tab" href="#tab-pane-2">Information</a>-->
                            <!--<a class="nav-item nav-link text-dark" data-toggle="tab" href="#tab-pane-3">Reviews (1)</a>-->
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="tab-pane-1">
                                <h4 class="mb-3">Contact Information</h4>
                                <p>
                                    You can contact Bree & Donna at kit@kitrocha.com
                                    Bree & Donna are represented by Sarah Younger at NYLA. For rights inquiries about their books, contact rights@nyliterary.com
                                </p>
                                <h4 class="mb-3">Unofficial Bio</h4>
                                <p>
                                    Bree & Donna met while writing X-Men fanfic in 2000 and became BFFs 4Evah. When Bree?s fallback job as a salesperson for non-denominational angel paraphernalia could no longer support her California rent, Donna invited her to move to Alabama and live with her. After meeting their husbands in a table top RPG at the local comic book store, they served as each others? Maids of Honor in 2004. In 2007 they decided that they should return to That Writing Thing they?d always loved, but this time writing original characters?together. The rest is history.
                                </p>
                                <p>
                                    They currently live in the backwoods in Alabama, three miles apart, and spend their non-writing time caring for their various cats, dogs, and chickens. (Oh, yeah, and Donna?s kids.) You can follow their Kit Rocha twitter account or keep up with their individual adventures at @mostlybree and @totallydonna.
                                </p>
                                <p>
                                    In their free time, they make jewelry and crafts and sell them on etsy.
                                </p>
                            </div>
                            <div class="tab-pane fade" id="tab-pane-2">
                                <h4 class="mb-3">Additional Information</h4>
                                <p>Eos no lorem eirmod diam diam, eos elitr et gubergren diam sea. Consetetur vero aliquyam invidunt duo dolores et duo sit. Vero diam ea vero et dolore rebum, dolor rebum eirmod consetetur invidunt sed sed et, lorem duo et eos elitr, sadipscing kasd ipsum rebum diam. Dolore diam stet rebum sed tempor kasd eirmod. Takimata kasd ipsum accusam sadipscing, eos dolores sit no ut diam consetetur duo justo est, sit sanctus diam tempor aliquyam eirmod nonumy rebum dolor accusam, ipsum kasd eos consetetur at sit rebum, diam kasd invidunt tempor lorem, ipsum lorem elitr sanctus eirmod takimata dolor ea invidunt.</p>
                                <div class="row">
                                    <div class="col-md-6">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item px-0">
                                                Sit erat duo lorem duo ea consetetur, et eirmod takimata.
                                            </li>
                                            <li class="list-group-item px-0">
                                                Amet kasd gubergren sit sanctus et lorem eos sadipscing at.
                                            </li>
                                            <li class="list-group-item px-0">
                                                Duo amet accusam eirmod nonumy stet et et stet eirmod.
                                            </li>
                                            <li class="list-group-item px-0">
                                                Takimata ea clita labore amet ipsum erat justo voluptua. Nonumy.
                                            </li>
                                        </ul> 
                                    </div>
                                    <div class="col-md-6">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item px-0">
                                                Sit erat duo lorem duo ea consetetur, et eirmod takimata.
                                            </li>
                                            <li class="list-group-item px-0">
                                                Amet kasd gubergren sit sanctus et lorem eos sadipscing at.
                                            </li>
                                            <li class="list-group-item px-0">
                                                Duo amet accusam eirmod nonumy stet et et stet eirmod.
                                            </li>
                                            <li class="list-group-item px-0">
                                                Takimata ea clita labore amet ipsum erat justo voluptua. Nonumy.
                                            </li>
                                        </ul> 
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="tab-pane-3">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h4 class="mb-4">1 review for "Dance with the Devil"</h4>
                                        <div class="media mb-4">
                                            <img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                                            <div class="media-body">
                                                <h6>John Doe<small> - <i>01 Jan 2045</i></small></h6>
                                                <div class="text-primary mb-2">
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star-half-alt"></i>
                                                    <i class="far fa-star"></i>
                                                </div>
                                                <p>Diam amet duo labore stet elitr ea clita ipsum, tempor labore accusam ipsum et no at. Kasd diam tempor rebum magna dolores sed sed eirmod ipsum.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <h4 class="mb-4">Leave a review</h4>
                                        <small>Your email address will not be published. Required fields are marked *</small>
                                        <div class="d-flex my-3">
                                            <p class="mb-0 mr-2">Your Rating * :</p>
                                            <div class="text-primary">
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                                <i class="far fa-star"></i>
                                            </div>
                                        </div>
                                        <form>
                                            <div class="form-group">
                                                <label for="message">Your Review *</label>
                                                <textarea id="message" cols="30" rows="5" class="form-control"></textarea>
                                            </div>
                                            <!--                                            <div class="form-group">
                                                                                            <label for="name">Your Name *</label>
                                                                                            <input type="text" class="form-control" id="name">
                                                                                        </div>
                                                                                        <div class="form-group">
                                                                                            <label for="email">Your Email *</label>
                                                                                            <input type="email" class="form-control" id="email">
                                                                                        </div>-->
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