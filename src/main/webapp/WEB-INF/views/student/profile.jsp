<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!doctype html>

    <html lang="en" class="h-100">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="shortcut icon" type="image/x-icon" href="/assets/img/leaf.svg">
        <title>UIS</title>
        <link href="/assets/css/bootstrap.css" rel="stylesheet">
        <link href="/assets/css/main.css" rel="stylesheet">
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
                                <div class="col-md-6 float-start">
                                    <h4 class="m-0 text-dark text-muted">Profile</h4>
                                </div>
                                <div class="col-md-6">
                                    <ol class="breadcrumb float-end">
                                        <li class="breadcrumb-item"><a href="#"> Home</a></li>
                                        <li class="breadcrumb-item active">Profile</li>
                                    </ol>
                                </div>
                            </div>

                            <!-- Main Content -->
                            <div class="row mt-3">
                                <!-- Profile Information -->
                                <div class="col-md-4">
                                    <div class="card card-rounded mb-3">
                                        <div class="card-body text-center">
                                            <img src="/assets/img/student-boy.png" alt="Avatar"
                                                class="img-fluid rounded-circle mb-3" width="120" height="120" />
                                            <h5 class="card-title mb-0"> ${student.lastName} ${student.firstName}</h5>
                                            <div class="text-muted mb-2"><span class="badge bg-success">Sinh viên</span>
                                            </div>
                                        </div>
                                        <hr class="my-0" />
                                        <div class="card-body">
                                            <h5 class="h6 card-title">Thông tin Khóa học</h5>
                                            <ul class="list-unstyled">
                                                <li class="mb-2"><strong>MSSV:</strong> ${student.studentId}</li>
                                                <li class="mb-2"><strong>Lớp:</strong> ${student.classEntity.classId}
                                                </li>
                                                <li class="mb-2"><strong>Ngành:</strong> ${student.major.majorName}</li>
                                                <li class="mb-2"><strong>Khoa:</strong>
                                                    ${student.classEntity.faculty.facultyName}</li>
                                                <li class="mb-2"><strong>Bậc đào tạo:</strong>
                                                    ${student.classEntity.trainingSystem}
                                                </li>
                                                <li class="mb-2"><strong>Niên khóa:</strong>
                                                    ${student.classEntity.academicYear}</li>

                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <!-- Academic Information -->
                                <div class="col-md-8">
                                    <div class="card card-rounded mb-3">
                                        <div class="card-header bg-white">
                                            <h5 class="card-title mb-0">Thông tin học tập</h5>
                                        </div>
                                        <div class="card-body">
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <h6 class="card-title">Điểm trung bình tích lũy</h6>
                                                            <h2 class="text-center">3.65</h2>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <h6 class="card-title">Điểm rèn luyện</h6>
                                                            <h2 class="text-center">85</h2>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="card mb-3">
                                                <div class="card-header bg-light">
                                                    <h6 class="card-title mb-0">Tình trạng học tập</h6>
                                                </div>
                                                <div class="card-body">
                                                    <table class="table table-bordered">
                                                        <tbody>
                                                            <tr>
                                                                <td><strong>Số tín chỉ đã đăng ký:</strong></td>
                                                                <td>120</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Số tín chỉ đã hoàn thành:</strong></td>
                                                                <td>100</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Số tín chỉ còn thiếu:</strong></td>
                                                                <td>20</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Trạng thái:</strong></td>
                                                                <td><span class="badge bg-success">Đang học</span></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                            <div class="card">
                                                <div class="card-header bg-light">
                                                    <h6 class="card-title mb-0">Hoạt động gần đây</h6>
                                                </div>
                                                <div class="card-body">
                                                    <ul class="list-group list-group-flush">
                                                        <li
                                                            class="list-group-item d-flex justify-content-between align-items-center">
                                                            Đăng ký học phần HK2 (2023-2024)
                                                            <span
                                                                class="badge bg-primary rounded-pill">15/01/2024</span>
                                                        </li>
                                                        <li
                                                            class="list-group-item d-flex justify-content-between align-items-center">
                                                            Nộp đơn xin phúc khảo
                                                            <span
                                                                class="badge bg-primary rounded-pill">10/01/2024</span>
                                                        </li>
                                                        <li
                                                            class="list-group-item d-flex justify-content-between align-items-center">
                                                            Đánh giá rèn luyện HK1 (2023-2024)
                                                            <span
                                                                class="badge bg-primary rounded-pill">05/01/2024</span>
                                                        </li>
                                                    </ul>
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

        <script src="/assets/js/feather.min.js"></script>
        <script src="/assets/js/bootstrap.bundle.min.js"></script>
        <script src="/assets/js/script.js"></script>
        <script type="text/javascript">
            document.addEventListener("DOMContentLoaded", function (event) {
                feather.replace();
            });
        </script>
    </body>

    </html>