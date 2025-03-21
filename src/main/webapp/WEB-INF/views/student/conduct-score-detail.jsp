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
                                        <div class="col-md-12">
                                            <div class="page-content">
                                                <div class="container-fluid">
                                                    <div class="row mb-3">
                                                        <div class="col-12">
                                                            <div
                                                                class="d-flex justify-content-between align-items-center">
                                                                <h4 class="mb-0">PHIẾU ĐIỂM RÈN LUYỆN</h4>
                                                                <div class="d-flex align-items-center">
                                                                    <div class="form-group me-2">
                                                                        <select id="semesterSelect"
                                                                            class="form-select form-select-sm"
                                                                            style="width: 200px;">
                                                                            <option value="" selected disabled>--- Chọn
                                                                                học kỳ ---</option>
                                                                            <c:forEach var="semester"
                                                                                items="${semesters}">
                                                                                <option value="${semester.semesterId}">
                                                                                    Học kỳ ${semester.order}
                                                                                    (${semester.academicYear})
                                                                                </option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <button class="btn btn-success btn-sm">Đã
                                                                        gửi</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <div class="table-responsive">
                                                                    <table class="table table-bordered">
                                                                        <p class="head">
                                                                            <button class="btn btn-sm btn-primary"
                                                                                id="exportToPDF1">
                                                                                <i data-feather="download"></i>Export To
                                                                                PDF
                                                                            </button>
                                                                        </p>
                                                                </div>
                                                                <thead>
                                                                    <tr>
                                                                        <th>NỘI DUNG</th>
                                                                        <th class="text-center">Điểm tối đa</th>
                                                                        <th class="text-center">Sinh viên tự
                                                                            đánh
                                                                            giá</th>
                                                                        <th class="text-center">Ban cán sự chấm
                                                                            điểm
                                                                        </th>
                                                                        <th class="text-center">CVHT chấm điểm
                                                                        </th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>Tiêu chí 1. Đánh giá về ý thức tham
                                                                            gia
                                                                            học tập</td>
                                                                        <td class="text-center">20</td>
                                                                        <td class="text-center"></td>
                                                                        <td class="text-center"></td>
                                                                        <td class="text-center"></td>
                                                                    </tr>

                                                                    <tr>
                                                                        <td>Ý thức và thái độ trong học tập:
                                                                        </td>
                                                                        <td class="text-center"><span
                                                                                class="score-range">[0 -
                                                                                3]</span>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="0" placeholder="0-10" min="0"
                                                                                    max="10" step="1">
                                                                            </div>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="3" disabled>
                                                                            </div>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="3" disabled>
                                                                            </div>
                                                                        </td>

                                                                    </tr>

                                                                    <tr>
                                                                        <td>Kết quả học tập trong kỳ học</td>
                                                                        <td class="text-center"><span
                                                                                class="score-range">[0 -
                                                                                10]</span></td>
                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="0" placeholder="0-10" min="0"
                                                                                    max="10" step="1">
                                                                            </div>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="3" disabled>
                                                                            </div>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="3" disabled>
                                                                            </div>
                                                                        </td>
                                                                    </tr>

                                                                    <tr>
                                                                        <td>Ý thức chấp hành tốt nội quy về các
                                                                            kỳ
                                                                            thi</td>
                                                                        <td class="text-center"><span
                                                                                class="score-range">[4 -
                                                                                4]</span>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="0" placeholder="0-10" min="0"
                                                                                    max="10" step="1">
                                                                            </div>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="3" disabled>
                                                                            </div>
                                                                        </td>

                                                                        <td class="text-center">
                                                                            <div
                                                                                class="d-flex justify-content-center align-items-center">
                                                                                <input type="number"
                                                                                    class="form-control form-control-sm text-center score-input"
                                                                                    value="3" disabled>
                                                                            </div>
                                                                        </td>
                                                                    </tr>


                                                                    <!-- Có thể thêm các tiêu chí khác nếu cần -->
                                                                </tbody>
                                                                </table>
                                                            </div>

                                                            <div class="mt-3 text-end">
                                                                <button class="btn btn-primary">Lưu đánh
                                                                    giá</button>
                                                                <button class="btn btn-success">Gửi đánh
                                                                    giá</button>
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