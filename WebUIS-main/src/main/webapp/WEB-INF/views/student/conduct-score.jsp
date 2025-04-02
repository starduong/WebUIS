<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

                <style>
                    .semester-list {
                        background-color: #fff;
                        border-radius: 5px;
                        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
                    }

                    .semester-item {
                        padding: 15px;
                        border-bottom: 1px solid #f0f0f0;
                        transition: all 0.2s;
                    }

                    .semester-item:hover {
                        background-color: #f8f9fa;
                    }

                    .semester-item.active {
                        background-color: #fff0f0;
                        border-left: 3px solid #dc3545;
                    }

                    .conduct-score-card {
                        border-radius: 5px;
                        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
                    }

                    .conduct-score-header {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        padding: 15px 20px;
                        border-bottom: 1px solid #f0f0f0;
                    }

                    .conduct-score-title {
                        font-size: 1.25rem;
                        font-weight: 600;
                        margin: 0;
                    }

                    .score-table th,
                    .score-table td {
                        padding: 12px 15px;
                        vertical-align: middle;
                    }

                    .score-input {
                        width: 60px;
                        text-align: center;
                        border: 1px solid #dee2e6;
                        border-radius: 4px;
                        padding: 5px;
                    }

                    .score-range {
                        color: #6c757d;
                        font-size: 0.9rem;
                    }

                    .btn-submit {
                        background-color: #28a745;
                        color: white;
                        border: none;
                        padding: 8px 16px;
                        border-radius: 4px;
                    }

                    .btn-appeal {
                        background-color: #dc3545;
                        color: white;
                        border: none;
                        padding: 8px 16px;
                        border-radius: 4px;
                    }

                    .floating-action-btn {
                        position: fixed;
                        bottom: 30px;
                        right: 30px;
                        width: 50px;
                        height: 50px;
                        border-radius: 50%;
                        background-color: #dc3545;
                        color: white;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                        z-index: 1000;
                    }
                </style>

            </head>

            <body class="d-flex flex-column h-100">
                <div id="page">

                    <div class="wrapper">

                        <!-- start sidebar -->
                        <jsp:include page="layout/sidebar.jsp" />
                        <!-- end sidebar -->

                        <div id="bodywrapper" class="container-fluid showhidetoggle">

                            <!-- start navbar -->
                            <jsp:include page="layout/navbar.jsp" />
                            <!-- end navbar -->


                            <div class="content">
                                <div class="container-fluid">
                                    <div class="row mt-2">
                                        <div class="col-md-12">
                                            <ol class="breadcrumb d-flex justify-content-end mb-0">
                                                <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                                                <li class="breadcrumb-item active">Điểm rèn luyện</li>
                                            </ol>
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <!-- Semester List -->
                                        <div class="card">
                                            <div class="content" id="tableContent">
                                                <div class="head">
                                                    <h5 class="card-header">Tổng hợp điểm rèn luyện các học kỳ</h5>
                                                </div>
                                                <div class="canvas-wrapper">
                                                    <table class="table no-margin" id="finTable">
                                                        <thead class="success">
                                                            <tr>
                                                                <th>Học kỳ</th>
                                                                <th>Tổng điểm</th>
                                                                <th>Xếp loại</th>
                                                                <th>Trạng thái</th>
                                                                <th>Hành động</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>Jacob Jensen</td>
                                                                <td>85%</td>
                                                                <td>32,435</td>
                                                                <td><span class="badge bg-success">Đã hoàn thành</span>
                                                                </td>
                                                                <td>
                                                                    <a href="/student/conduct-score-detail">
                                                                        <span class="badge bg-primary rounded-pill">Xem
                                                                            chi tiết</span>
                                                                    </a>
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td>Cecelia Bradley</td>
                                                                <td>55%</td>
                                                                <td>4,36780</td>
                                                                <td>765728</td>
                                                                <td>
                                                                    <a href="/student/conduct-score-detail">
                                                                        <span class="badge bg-primary rounded-pill">Xem
                                                                            chi tiết</span>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Leah Sherman</td>
                                                                <td>23%</td>
                                                                <td>2300</td>
                                                                <td>22437</td>
                                                                <td>
                                                                    <a href="/student/conduct-score-detail">
                                                                        <span class="badge bg-primary rounded-pill">Xem
                                                                            chi tiết</span>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Ina Curry</td>
                                                                <td>44%</td>
                                                                <td>53462</td>
                                                                <td>1,75938</td>
                                                                <td>
                                                                    <a href="/student/conduct-score-detail">
                                                                        <span class="badge bg-primary rounded-pill">Xem
                                                                            chi tiết</span>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Lida Fitzgerald</td>
                                                                <td>65%</td>
                                                                <td>67453</td>
                                                                <td>765377</td>
                                                                <td>
                                                                    <a href="/student/conduct-score-detail">
                                                                        <span class="badge bg-primary rounded-pill">Xem
                                                                            chi tiết</span>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Stella Johnson</td>
                                                                <td>49%</td>
                                                                <td>43662</td>
                                                                <td>96535</td>
                                                                <td>
                                                                    <a href="/student/conduct-score-detail">
                                                                        <span class="badge bg-primary rounded-pill">Xem
                                                                            chi tiết</span>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Maria Ortiz</td>
                                                                <td>65%</td>
                                                                <td>76555</td>
                                                                <td>258546</td>
                                                                <td>
                                                                    <a href="/student/conduct-score-detail">
                                                                        <span class="badge bg-primary rounded-pill">Xem
                                                                            chi tiết</span>
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
                </div>

                </div>

                </div>
                </div>

                <!-- start footer -->
                <jsp:include page="layout/footer.jsp" />
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