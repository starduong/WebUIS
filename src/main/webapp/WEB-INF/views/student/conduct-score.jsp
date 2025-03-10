<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!doctype html>

    <html lang="en" class="h-100">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="shortcut icon" type="image/x-icon" href="../assets/img/leaf.svg">
        <title>UIS</title>
        <link href="../assets/css/bootstrap.css" rel="stylesheet">
        <link href="../assets/css/main.css" rel="stylesheet">
    </head>

    <body class="d-flex flex-column h-100">
        <div id="page">

            <div class="wrapper">

                <!-- start sidebar -->
                <jsp:include page="../layout/sidebar.jsp" />
                <!-- end sidebar -->

                <div id="bodywrapper" class="container-fluid showhidetoggle">

                    <!-- start navbar -->
                    <jsp:include page="../layout/navbar.jsp" />
                    <!-- end navbar -->


                    <div class="content">
                        <div class="container-fluid">
                            <div class="row mt-2">
                                <div class="col-md-6 float-start">
                                    <h4 class="m-0 text-dark text-muted">Conduct score</h4>
                                </div>
                                <div class="col-md-6">
                                    <ol class="breadcrumb float-end">
                                        <li class="breadcrumb-item"><a href="#"> Home</a></li>
                                        <li class="breadcrumb-item active">conduct score</li>
                                    </ol>
                                </div>
                            </div>


                            <div class="card">
                                <div class="content">
                                    <div class="canvas-wrapper">

                                        <div class="row g-3 mb-4">
                                            <div class="col-md-5 col-lg-4 order-md-last">
                                                <h4 class="d-flex justify-content-between align-items-center mb-3">
                                                    <span class="text-muted">Your cart</span> <span
                                                        class="badge bg-success rounded-pill">3</span>
                                                </h4>
                                                <ul class="list-group mb-3">
                                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                                        <div>
                                                            <h6 class="my-0">Product name</h6>
                                                            <small class="text-muted">Brief description</small>
                                                        </div> <span class="text-muted">$12</span>
                                                    </li>
                                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                                        <div>
                                                            <h6 class="my-0">Second product</h6>
                                                            <small class="text-muted">Brief description</small>
                                                        </div> <span class="text-muted">$8</span>
                                                    </li>
                                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                                        <div>
                                                            <h6 class="my-0">Third item</h6>
                                                            <small class="text-muted">Brief description</small>
                                                        </div> <span class="text-muted">$5</span>
                                                    </li>
                                                    <li class="list-group-item d-flex justify-content-between bg-light">
                                                        <div class="text-success">
                                                            <h6 class="my-0">Promo code</h6>
                                                            <small>EXAMPLECODE</small>
                                                        </div> <span class="text-success">−$5</span>
                                                    </li>
                                                    <li class="list-group-item d-flex justify-content-between">
                                                        <span>Total (USD)</span> <strong>$20</strong>
                                                    </li>
                                                </ul>

                                                <form class="card p-2">
                                                    <!--<div class="input-group"> -->
                                                    <input type="text" class="form-control form-control-sm"
                                                        placeholder="Promo code">
                                                    <button type="submit" class="btn btn-sm btn-danger">Redeem</button>
                                                    <!--</div> -->
                                                </form>
                                            </div>
                                            <div class="col-md-7 col-lg-8">
                                                <form class="needs-validation" novalidate>
                                                    <h4 class="mb-3 text-muted">Billing address</h4>
                                                    <div class="row g-3 mb-3">
                                                        <div class="col-sm-6">
                                                            <div class="form-floating">
                                                                <input type="email" class="form-control"
                                                                    id="floatingInput" placeholder="name@example.com"
                                                                    required> <label for="floatingInput">Email
                                                                    address</label>
                                                                <div class="invalid-feedback">Valid email is
                                                                    required.</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <div class="form-floating">
                                                                <input type="text" class="form-control"
                                                                    id="floatingPassword" placeholder="Username"> <label
                                                                    for="floatingPassword">Username</label>

                                                            </div>
                                                        </div>
                                                    </div>


                                                    <div class="row g-3">
                                                        <div class="col-sm-6">
                                                            <label for="firstName" class="form-label">First
                                                                name</label> <input type="text" class="form-control"
                                                                id="firstName" placeholder="" value="" required>
                                                            <div class="invalid-feedback">Valid first name is
                                                                required.</div>
                                                        </div>

                                                        <div class="col-sm-6">
                                                            <label for="lastName" class="form-label">Last name</label>
                                                            <input type="text" class="form-control" id="lastName"
                                                                placeholder="" value="" required>
                                                            <div class="invalid-feedback">Valid last name is
                                                                required.</div>
                                                        </div>
                                                        <div class="col-12">
                                                            <label for="username" class="form-label">Username</label>
                                                            <div class="input-group">
                                                                <span class="input-group-text">@</span> <input
                                                                    type="text" class="form-control" id="username"
                                                                    placeholder="Username" required>
                                                                <div class="invalid-feedback">Your username is
                                                                    required.</div>
                                                            </div>
                                                        </div>

                                                        <div class="col-12">
                                                            <label for="email" class="form-label">Email <span
                                                                    class="text-muted">(Optional)</span></label> <input
                                                                type="email" class="form-control" id="email"
                                                                placeholder="you@example.com">
                                                            <div class="invalid-feedback">Please enter a valid
                                                                email address for shipping updates.</div>
                                                        </div>

                                                        <div class="col-12">
                                                            <label for="address" class="form-label">Address</label>
                                                            <input type="text" class="form-control" id="address"
                                                                placeholder="1234 Main St" required>
                                                            <div class="invalid-feedback">Please enter your
                                                                shipping address.</div>
                                                        </div>

                                                        <div class="col-12">
                                                            <label for="address2" class="form-label">Address 2
                                                                <span class="text-muted">(Optional)</span>
                                                            </label> <input type="text" class="form-control"
                                                                id="address2" placeholder="Apartment or suite">
                                                        </div>

                                                        <div class="col-md-5">
                                                            <label for="country" class="form-label">Country</label>
                                                            <select class="form-select" id="country" required>
                                                                <option value="">Choose...</option>
                                                                <option>United States</option>
                                                            </select>
                                                            <div class="invalid-feedback">Please select a valid
                                                                country.</div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <label for="state" class="form-label">State</label> <select
                                                                class="form-select" id="state" required>
                                                                <option value="">Choose...</option>
                                                                <option>California</option>
                                                            </select>
                                                            <div class="invalid-feedback">Please provide a valid
                                                                state.</div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <label for="zip" class="form-label">Zip</label> <input
                                                                type="text" class="form-control" id="zip" placeholder=""
                                                                required>
                                                            <div class="invalid-feedback">Zip code required.</div>
                                                        </div>
                                                    </div>

                                                    <hr class="my-4">

                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input"
                                                            id="same-address">
                                                        <label class="form-check-label" for="same-address">Shipping
                                                            address
                                                            is the same as
                                                            my billing address</label>
                                                    </div>

                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" id="save-info">
                                                        <label class="form-check-label" for="save-info">Save this
                                                            information for next
                                                            time</label>
                                                    </div>

                                                    <hr class="my-4">

                                                    <h4 class="mb-3">Payment</h4>

                                                    <div class="my-3">
                                                        <div class="form-check">
                                                            <input id="credit" name="paymentMethod" type="radio"
                                                                class="form-check-input" checked required> <label
                                                                class="form-check-label" for="credit">Credit
                                                                card</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input id="debit" name="paymentMethod" type="radio"
                                                                class="form-check-input" required> <label
                                                                class="form-check-label" for="debit">Debit card</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input id="paypal" name="paymentMethod" type="radio"
                                                                class="form-check-input" required> <label
                                                                class="form-check-label" for="paypal">PayPal</label>
                                                        </div>
                                                    </div>

                                                    <div class="row gy-3 mb-4">
                                                        <div class="col-md-6">
                                                            <label for="cc-name" class="form-label">Name on
                                                                card</label> <input type="text" class="form-control"
                                                                id="cc-name" placeholder="" required> <small
                                                                class="text-muted">Full name as displayed on
                                                                card</small>
                                                            <div class="invalid-feedback">Name on card is
                                                                required</div>
                                                        </div>

                                                        <div class="col-md-6">
                                                            <label for="cc-number" class="form-label">Credit
                                                                card number</label> <input type="text"
                                                                class="form-control" id="cc-number" placeholder=""
                                                                required>
                                                            <div class="invalid-feedback">Credit card number is
                                                                required</div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <label for="cc-expiration"
                                                                class="form-label">Expiration</label>
                                                            <input type="text" class="form-control" id="cc-expiration"
                                                                placeholder="" required>
                                                            <div class="invalid-feedback">Expiration date
                                                                required</div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <label for="cc-cvv" class="form-label">CVV</label> <input
                                                                type="text" class="form-control" id="cc-cvv"
                                                                placeholder="" required>
                                                            <div class="invalid-feedback">Security code required</div>
                                                        </div>
                                                    </div>



                                                    <div class="row gy-3 mb-4">
                                                        <div class="col-md-6">
                                                            <div class="input-group mb-3">
                                                                <div class="input-group-text">
                                                                    <input class="form-check-input mt-0" type="checkbox"
                                                                        value="" required>
                                                                    <div class="invalid-feedback">This is required</div>
                                                                </div>
                                                                <input type="text" class="form-control" required>
                                                                <div class="invalid-feedback">This is required</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="input-group mb-3">
                                                                <div class="input-group-text">
                                                                    <input class="form-check-input mt-0" type="checkbox"
                                                                        value="" required>
                                                                    <div class="invalid-feedback">This is required</div>
                                                                </div>
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                    </div>


                                                    <div class="row gy-3 mb-4">
                                                        <div class="col-md-6">
                                                            <div class="input-group  mb-4">
                                                                <div class="input-group-text">
                                                                    <input class="form-check-input mt-0"
                                                                        name="radioInInput" type="radio" value=""
                                                                        required>
                                                                    <div class="invalid-feedback">Select an option</div>
                                                                </div>
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>

                                                        <div class="col-md-6">
                                                            <div class="input-group  mb-4">
                                                                <div class="input-group-text">
                                                                    <input class="form-check-input mt-0"
                                                                        name="radioInInput" type="radio" value=""
                                                                        required>
                                                                    <div class="invalid-feedback">Select an option</div>
                                                                </div>
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <hr class="my-4">

                                                    <button class="w-100 btn btn-sm btn-primary btn-lg" type="submit">
                                                        <i data-feather="check-circle" class="data-feather mr-1"></i>
                                                        Continue to checkout
                                                    </button>
                                                </form>
                                            </div>
                                        </div>

                                    </div>

                                    <!-- 							Canvas Wrapper End -->

                                </div>
                            </div>



                        </div>

                    </div>

                </div>

            </div>
        </div>

        <!-- start footer -->
        <jsp:include page="../layout/footer.jsp" />
        <!-- end footer -->

        <div id="loading" class="spinner-border text-primary align-middle" role="status"></div>

        <button class="btn btn-sm btn-primary rounded-circle" onclick="scrollToTopFunction()" id="scrollToTop"
            title="Scroll to top">
            <i data-feather="arrow-up-circle"></i>
        </button>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.js"></script>
        <script src="../assets/js/bootstrap.bundle.min.js"></script>
        <script src="../assets/js/script.js"></script>
        <script type="text/javascript">
            document.addEventListener("DOMContentLoaded", function (event) {
                feather.replace();
            });
        </script>
        <script type="text/javascript">
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms).forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
            })()
        </script>
    </body>

    </html>