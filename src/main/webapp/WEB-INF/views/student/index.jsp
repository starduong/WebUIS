<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!doctype html>

    <html lang="en" class="h-100">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/leaf.svg">
        <title>UIS</title>
        <link href="assets/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/main.css" rel="stylesheet">
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
                                    <ol class="breadcrumb float-end">
                                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                                        <li class="breadcrumb-item active">Tổng quan</li>
                                    </ol>
                                </div>
                            </div>


                            <div class="row">

                                <div class="col-sm-6 col-md-6 col-lg-3">
                                    <div class="card card-rounded">
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="icon-big text-center">
                                                        <i class="olive data-feather-big" stroke-width="3"
                                                            data-feather="bell"></i>
                                                    </div>
                                                </div>
                                                <div class="col-sm-8">
                                                    <div class="detail">
                                                        <p class="detail-subtitle">Thông báo</p>
                                                        <span class="number">0</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="footer">
                                                <hr />
                                                <div class="d-flex justify-content-between box-font-small">
                                                    <div class="col-md-6 stats">
                                                        <i data-feather="calendar"></i> tuần này
                                                    </div>
                                                    <div class="col-md-6">
                                                        <a class="text-primary float-end" href="#"><i class="blue"
                                                                data-feather="chevrons-right"></i>Xem
                                                            chi tiết</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-md-6 col-lg-3">
                                    <div class="card card-rounded">
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="icon-big text-center">
                                                        <i class="teal data-feather-big" stroke-width="3"
                                                            data-feather="calendar"></i>
                                                    </div>
                                                </div>
                                                <div class="col-sm-8">
                                                    <div class="detail">
                                                        <p class="detail-subtitle">Lịch học</p>
                                                        <span class="number">week 1</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="footer">
                                                <hr />
                                                <div class="d-flex justify-content-between box-font-small">
                                                    <div class="col-md-6 stats">
                                                        <i data-feather="calendar"></i> Tuần này
                                                    </div>
                                                    <div class="col-md-6">
                                                        <a class="text-primary float-end" href="#"><i class="blue"
                                                                data-feather="chevrons-right"></i>Xem chi tiết</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-md-6 col-lg-3">
                                    <div class="card card-rounded">
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <div class="icon-big text-center">
                                                        <i class="orange data-feather-big" stroke-width="3"
                                                            data-feather="clock"></i>
                                                    </div>
                                                </div>
                                                <div class="col-sm-8">
                                                    <div class="detail">
                                                        <p class="detail-subtitle">Lịch thi</p>
                                                        <span class="number">0</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="footer">
                                                <hr />
                                                <div class="d-flex justify-content-between box-font-small">
                                                    <div class="col-md-6 stats">
                                                        <i data-feather="calendar"></i> Tuần này
                                                    </div>
                                                    <div class="col-md-6">
                                                        <a class="text-primary float-end" href="#"><i class="blue"
                                                                data-feather="chevrons-right"></i>Xem chi tiết</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-md-6 col-lg-3">
                                    <div class="card card-rounded text-center">
                                        <div class="content">
                                            <img src="assets/img/ptithcm-logo.jpg" alt="Avni - The Earth"
                                                class="img-fluid rounded-circle mb-2" width="135" height="135" />
                                        </div>
                                    </div>
                                </div>

                            </div>


                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="card">
                                                <div class="content">
                                                    <div class="head">
                                                        <h5 class="mb-0">Điểm học tập</h5>
                                                        <p class="text-muted">Visitor data</p>
                                                    </div>
                                                    <div class="canvas-wrapper">
                                                        <canvas class="chart" id="trafficflow"></canvas>
                                                    </div>
                                                    <div class="ui hidden divider"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="card">
                                                <div class="content">
                                                    <div class="head">
                                                        <h5 class="mb-0">Điểm rèn luyện</h5>
                                                        <p class="text-muted">Users per month</p>
                                                    </div>
                                                    <div class="canvas-wrapper">
                                                        <canvas class="chart" id="sales"></canvas>
                                                    </div>
                                                    <div class="ui hidden divider"></div>
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
        </div>


        <div id="loading" class="spinner-border text-primary align-middle" role="status"></div>
        <button class="btn btn-sm btn-primary rounded-circle" onclick="scrollToTopFunction()" id="scrollToTop"
            title="Scroll to top">
            <i data-feather="arrow-up-circle"></i>
        </button>

        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/Chart.min.js"></script>
        <script src="assets/js/script.js"></script>

        <script type="text/javascript">
            document.addEventListener("DOMContentLoaded", function (event) {
                feather.replace();
            });
        </script>

        <script type="text/javascript">
            var trafficchart = document.getElementById("trafficflow");
            var saleschart = document.getElementById("sales");

            var myChart1 = new Chart(trafficchart, {
                type: 'line',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
                        'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                    datasets: [{
                        backgroundColor: "rgba(48, 164, 255, 0.5)",
                        borderColor: "rgba(48, 164, 255, 0.8)",
                        data: ['1135', '1135', '1140', '1168', '1150', '1145',
                            '1155', '1155', '1150', '1160', '1185', '1190'],
                        label: '',
                        fill: true
                    }]
                },
                options: {
                    responsive: true,
                    title: {
                        display: false,
                        text: 'Chart'
                    },
                    legend: {
                        position: 'top',
                        display: false,
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Months'
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Number of Visitors'
                            }
                        }]
                    }
                }
            });

            var myChart2 = new Chart(saleschart, {
                type: 'bar',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
                        'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                    datasets: [{
                        label: 'Income',
                        backgroundColor: "rgba(76, 175, 80, 0.5)",
                        borderColor: "#6da252",
                        borderWidth: 1,
                        data: ["280", "300", "400", "600", "450", "400", "500",
                            "550", "450", "650", "950", "1000"],
                    }]
                },
                options: {
                    responsive: true,
                    title: {
                        display: false,
                        text: 'Chart'
                    },
                    legend: {
                        position: 'top',
                        display: false,
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Months'
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Number of Users'
                            }
                        }]
                    }
                }
            });
        </script>

        <script src="assets/js/jspdf.min.js"></script>

        <script>
            function onClick() {
                var pdfExport = new jsPDF('p', 'pt', 'a4');
                var htmlTableContent = document.getElementById("tableContent");
                pdfExport.fromHTML(htmlTableContent);
                pdfExport.save('tableData.pdf');
            };

            var element = document.getElementById("exportToPDF1");
            element.addEventListener("click", onClick);
        </script>

        <script>
            function showTableData() {
                var oTable = document.getElementById('finTable');
                var rowLength = oTable.rows.length;
                for (i = 0; i < rowLength; i++) {
                    var oCells = oTable.rows.item(i).cells;
                    var cellLength = oCells.length;
                    for (var j = 0; j < cellLength; j++) {
                        var cellVal = oCells.item(j).innerHTML;
                        //alert(cellVal);
                    }
                }
            }
        </script>

        <script type="text/javascript">
            document.getElementById('finTable').addEventListener('click',
                function (item) {
                    var row = item.path[1];
                    var row_value = "";
                    for (var j = 0; j < row.cells.length; j++) {
                        row_value += row.cells[j].innerHTML;
                        row_value += " | ";
                    }

                    //alert(row_value);
                    var pdfExport = new jsPDF('p', 'pt', 'a4');
                    pdfExport.fromHTML(row_value);
                    pdfExport.save(row_value.split('|')[0].trim() + '.pdf');

                    if (row.classList.contains('highlight'))
                        row.classList.remove('highlight');
                    else
                        row.classList.add('highlight');
                });
        </script>
    </body>

    </html>