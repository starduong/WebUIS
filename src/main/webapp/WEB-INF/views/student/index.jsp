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
                                    <h4 class="m-0 text-dark text-muted">Overview</h4>
                                </div>
                                <div class="col-md-6">
                                    <ol class="breadcrumb float-end">
                                        <li class="breadcrumb-item"><a href="#"> Home</a></li>
                                        <li class="breadcrumb-item active">Overview</li>
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
                                                        <h5 class="mb-0">Web Traffic</h5>
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
                                                        <h5 class="mb-0">Number of Users</h5>
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
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="content">
                                            <div class="head">
                                                <h5 class="mb-0">Top Visitors by Country</h5>
                                                <p class="text-muted">Fiscal user data</p>
                                            </div>
                                            <div class="canvas-wrapper">
                                                <table class="table no-margin">
                                                    <thead class="success">
                                                        <tr>
                                                            <th>Country</th>
                                                            <th class="text-right">Unique Visitors</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><i class="text-primary" data-feather="flag"></i>
                                                                United States</td>
                                                            <td class="text-right">27,340</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-danger" data-feather="flag"></i>
                                                                India</td>
                                                            <td class="text-right">21,280</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-primary" data-feather="flag"></i>
                                                                Japan</td>
                                                            <td class="text-right">18,210</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-success" data-feather="flag"></i>
                                                                United Kingdom</td>
                                                            <td class="text-right">15,176</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-warning" data-feather="flag"></i>
                                                                India</td>
                                                            <td class="text-right">14,276</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-warning" data-feather="flag"></i>
                                                                Germany</td>
                                                            <td class="text-right">13,176</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-success" data-feather="flag"></i>
                                                                India</td>
                                                            <td class="text-right">12,176</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-primary" data-feather="flag"></i>
                                                                United States</td>
                                                            <td class="text-right">11,886</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-success" data-feather="flag"></i>
                                                                India</td>
                                                            <td class="text-right">11,509</td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="text-info" data-feather="flag"></i>
                                                                India</td>
                                                            <td class="text-right">1,700</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="content">
                                            <div class="head">
                                                <h5 class="mb-0">Most Visited Pages</h5>
                                                <p class="text-muted">Fiscal visitor data</p>
                                            </div>
                                            <div class="canvas-wrapper">
                                                <table class="table no-margin table-striped">
                                                    <thead class="success">
                                                        <tr>
                                                            <th>Page Name</th>
                                                            <th class="text-right">Visitors</th>
                                                            <th>Target</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">8,340</td>
                                                            <td>
                                                                <div class="progress" style="height: 20px;">
                                                                    <div class="progress-bar" role="progressbar"
                                                                        style="width: 25%;" aria-valuenow="25"
                                                                        aria-valuemin="0" aria-valuemax="100">25%
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">7,280</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-success"
                                                                        role="progressbar" style="width: 100%;"
                                                                        aria-valuenow="50" aria-valuemin="0"
                                                                        aria-valuemax="100"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">6,210</td>
                                                            <td>
                                                                <div class="progress" style="height: 20px;">
                                                                    <div class="progress-bar bg-danger"
                                                                        role="progressbar" style="width: 25%;"
                                                                        aria-valuenow="25" aria-valuemin="0"
                                                                        aria-valuemax="100">25%</div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">5,176</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-info" role="progressbar"
                                                                        style="width: 80%" aria-valuenow="50"
                                                                        aria-valuemin="0" aria-valuemax="100"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">4,276</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-warning"
                                                                        role="progressbar" style="width: 90%"
                                                                        aria-valuenow="50" aria-valuemin="0"
                                                                        aria-valuemax="100"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">3,176</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-danger"
                                                                        role="progressbar" style="width: 100%"
                                                                        aria-valuenow="50" aria-valuemin="0"
                                                                        aria-valuemax="100"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">2,176</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-success"
                                                                        role="progressbar" style="width: 100%"
                                                                        aria-valuenow="50" aria-valuemin="0"
                                                                        aria-valuemax="100"></div>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">1,886</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-success"
                                                                        role="progressbar" style="width: 100%"
                                                                        aria-valuenow="50" aria-valuemin="0"
                                                                        aria-valuemax="100"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">1,509</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-warning"
                                                                        role="progressbar" style="width: 50%"
                                                                        aria-valuenow="50" aria-valuemin="0"
                                                                        aria-valuemax="100"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><a href="index.html" class="text-info"><i
                                                                        data-feather="link"
                                                                        class="data-feather blue"></i>index.html
                                                                </a></td>
                                                            <td class="text-right">1,100</td>
                                                            <td>
                                                                <div class="progress" style="height: 10px;">
                                                                    <div class="progress-bar bg-success"
                                                                        role="progressbar" style="width: 100%"
                                                                        aria-valuenow="50" aria-valuemin="0"
                                                                        aria-valuemax="100"></div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row mt-2 mb-2">
                                <div class="widget-tiles">
                                    <div class="row text-center">

                                        <div class="col-sm-6 col-md-6 col-lg-3">
                                            <a target="_blank" href="https://github.com/ajkr195/Avni">
                                                <div class="widget">
                                                    <div class="icon">
                                                        <i class="data-feather-big-white" data-feather="filter"></i>
                                                    </div>
                                                    <div class="description">
                                                        <h3>Widget 1</h3>
                                                        <p>Some descriptive text. Try to zoom in-out the screen
                                                            to see responsiveness.</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>


                                        <div class="col-sm-6 col-md-6 col-lg-3">
                                            <a target="_blank" href="https://github.com/ajkr195/Avni">
                                                <div class="widget">
                                                    <div class="icon">
                                                        <i class="data-feather-big-white" data-feather="activity"></i>
                                                    </div>
                                                    <div class="description">
                                                        <h3>Widget 2</h3>
                                                        <p>Some descriptive text. Try to zoom in-out the screen
                                                            to see responsiveness.</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>

                                        <div class="col-sm-6 col-md-6 col-lg-3">
                                            <a target="_blank" href="https://github.com/ajkr195/Avni">
                                                <div class="widget">
                                                    <div class="icon">
                                                        <i class="data-feather-big-white" data-feather="airplay"></i>
                                                    </div>
                                                    <div class="description">
                                                        <h3>Widget 3</h3>
                                                        <p>Some descriptive text. Try to zoom in-out the screen
                                                            to see responsiveness.</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>

                                        <div class="col-sm-6 col-md-6 col-lg-3">
                                            <a target="_blank" href="https://github.com/ajkr195/Avni">
                                                <div class="widget">
                                                    <div class="icon">
                                                        <i class="data-feather-big-white" data-feather="archive"></i>
                                                    </div>
                                                    <div class="description">
                                                        <h3>Widget 4</h3>
                                                        <p>Some descriptive text. Try to zoom in-out the screen
                                                            to see responsiveness.</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card">
                                <div class="content" id="tableContent">
                                    <div class="head">
                                        <h5 class="mb-0">Financial review</h5>
                                        <p class="text-muted">
                                            Of the data<br />
                                            <button class="btn btn-sm btn-primary" id="exportToPDF1">
                                                <i data-feather="download"></i>Export All Rows To PDF
                                            </button>
                                            <br />
                                            <!--<button class="btn btn-sm btn-primary"> -->
                                            <!--onClick="showTableData();"> -->
                                            <!--	<i data-feather="download"></i>Show Table Data -->
                                            <!--</button> -->
                                            OR...Click individual rows to export them to PDF individually.
                                        </p>
                                    </div>
                                    <div class="canvas-wrapper">
                                        <table class="table no-margin" id="finTable">
                                            <thead class="success">
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Sale-Rate</th>
                                                    <th>Actual</th>
                                                    <th>Variance</th>
                                                    <!--<td></td> -->
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Jacob Jensen</td>
                                                    <td>85%</td>
                                                    <td>32,435</td>
                                                    <td>40,234</td>
                                                    <!--<td><div class="progress" style="height: 10px;"> -->
                                                    <!--		<div class="progress-bar bg-danger" role="progressbar" -->
                                                    <!--			style="width: 25%" aria-valuenow="50" aria-valuemin="0" -->
                                                    <!--			aria-valuemax="100"></div> -->
                                                    <!--	</div></td> -->
                                                </tr>
                                                <tr>
                                                    <td>Cecelia Bradley</td>
                                                    <td>55%</td>
                                                    <td>4,36780</td>
                                                    <td>765728</td>
                                                    <!--<td><div class="progress" style="height: 10px;"> -->
                                                    <!--		<div class="progress-bar bg-success" role="progressbar" -->
                                                    <!--			style="width: 25%" aria-valuenow="50" aria-valuemin="0" -->
                                                    <!--			aria-valuemax="100"></div> -->
                                                    <!--	</div></td> -->
                                                </tr>
                                                <tr>
                                                    <td>Leah Sherman</td>
                                                    <td>23%</td>
                                                    <td>2300</td>
                                                    <td>22437</td>
                                                    <!--<td><div class="progress" style="height: 10px;"> -->
                                                    <!--		<div class="progress-bar bg-danger" role="progressbar" -->
                                                    <!--			style="width: 85%" aria-valuenow="50" aria-valuemin="0" -->
                                                    <!--			aria-valuemax="100"></div> -->
                                                    <!--	</div></td> -->
                                                </tr>
                                                <tr>
                                                    <td>Ina Curry</td>
                                                    <td>44%</td>
                                                    <td>53462</td>
                                                    <td>1,75938</td>
                                                    <!--<td><div class="progress" style="height: 10px;"> -->
                                                    <!--		<div class="progress-bar bg-warning" role="progressbar" -->
                                                    <!--			style="width: 35%" aria-valuenow="50" aria-valuemin="0" -->
                                                    <!--			aria-valuemax="100"></div> -->
                                                    <!--	</div></td> -->
                                                </tr>
                                                <tr>
                                                    <td>Lida Fitzgerald</td>
                                                    <td>65%</td>
                                                    <td>67453</td>
                                                    <td>765377</td>
                                                    <!--<td><div class="progress" style="height: 10px;"> -->
                                                    <!--		<div class="progress-bar bg-success" role="progressbar" -->
                                                    <!--			style="width: 50%" aria-valuenow="50" aria-valuemin="0" -->
                                                    <!--			aria-valuemax="100"></div> -->
                                                    <!--	</div></td> -->
                                                </tr>
                                                <tr>
                                                    <td>Stella Johnson</td>
                                                    <td>49%</td>
                                                    <td>43662</td>
                                                    <td>96535</td>
                                                    <!--<td><div class="progress" style="height: 10px;"> -->
                                                    <!--		<div class="progress-bar bg-danger" role="progressbar" -->
                                                    <!--			style="width: 70%" aria-valuenow="50" aria-valuemin="0" -->
                                                    <!--			aria-valuemax="100"></div> -->
                                                    <!--	</div></td> -->
                                                </tr>
                                                <tr>
                                                    <td>Maria Ortiz</td>
                                                    <td>65%</td>
                                                    <td>76555</td>
                                                    <td>258546</td>
                                                    <!--<td><div class="progress" style="height: 10px;"> -->
                                                    <!--		<div class="progress-bar bg-primary" role="progressbar" -->
                                                    <!--			style="width: 45%" aria-valuenow="50" aria-valuemin="0" -->
                                                    <!--			aria-valuemax="100"></div> -->
                                                    <!--	</div></td> -->
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-6 col-md-6 col-lg-3">
                                    <div class="card card-rounded">
                                        <div class="content">
                                            <div class="row">
                                                <div class="dfd text-center">
                                                    <i class="blue data-feather-big" stroke-width="3"
                                                        data-feather="thumbs-up"></i>
                                                    <h4 class="mb-0">+21,900</h4>
                                                    <p class="text-muted">Social Likes</p>
                                                </div>
                                            </div>
                                            <div class="footer">
                                                <hr />
                                                <div class="d-flex justify-content-between box-font-small">
                                                    <div class="col-md-6 stats">
                                                        <i data-feather="calendar"></i> This Week
                                                    </div>
                                                    <div class="col-md-6">
                                                        <a class="text-primary float-end" href="#"><i class="blue"
                                                                data-feather="chevrons-right"></i>See Details</a>
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
                                                <div class="dfd text-center">
                                                    <i class="grey data-feather-big" stroke-width="3"
                                                        data-feather="share-2"></i>
                                                    <h4 class="mb-0">+22,566</h4>
                                                    <p class="text-muted">Followers</p>
                                                </div>
                                            </div>
                                            <div class="footer">
                                                <hr />
                                                <div class="d-flex justify-content-between box-font-small">
                                                    <div class="col-md-6 stats">
                                                        <i data-feather="calendar"></i> This Week
                                                    </div>
                                                    <div class="col-md-6">
                                                        <a class="text-primary float-end" href="#"><i class="blue"
                                                                data-feather="chevrons-right"></i>See Details</a>
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
                                                <div class="dfd text-center">
                                                    <i class="orange data-feather-big" stroke-width="3"
                                                        data-feather="mail"></i>
                                                    <h4 class="mb-0">+15,566</h4>
                                                    <p class="text-muted">Subscribers</p>
                                                </div>
                                            </div>
                                            <div class="footer">
                                                <hr />
                                                <div class="d-flex justify-content-between box-font-small">
                                                    <div class="col-md-6 stats">
                                                        <i data-feather="calendar"></i> This Week
                                                    </div>
                                                    <div class="col-md-6">
                                                        <a class="text-primary float-end" href="#"><i class="blue"
                                                                data-feather="chevrons-right"></i>See Details</a>
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
                                                <div class="dfd text-center">
                                                    <i class="olive data-feather-big" stroke-width="3"
                                                        data-feather="dollar-sign"></i>
                                                    <h4 class="mb-0">+98,601</h4>
                                                    <p class="text-muted">Sales</p>
                                                </div>
                                            </div>
                                            <div class="footer">
                                                <hr />
                                                <div class="d-flex justify-content-between box-font-small">
                                                    <div class="col-md-6 stats">
                                                        <i data-feather="calendar"></i> This Week
                                                    </div>
                                                    <div class="col-md-6">
                                                        <a class="text-primary float-end" href="#"><i class="blue"
                                                                data-feather="chevrons-right"></i>See Details</a>
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
        <jsp:include page="../layout/footer.jsp" />
        <!-- end footer -->

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