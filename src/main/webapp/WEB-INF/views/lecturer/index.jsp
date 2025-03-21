<!doctype html>

<html lang="en" class="h-100">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/leaf.svg">
    <title>lecturer - UIS</title>
    <link rel="stylesheet" type="text/css" href="assets/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/main2.css">
    <link href="assets/css/main.css" rel="stylesheet">
</head>

<body class="overlay-scrollbar">

    <!-- start navbar -->
    <jsp:include page="layout/navbar.jsp" />
    <!-- end navbar -->


    <div class="sidebar">
        <ul class="sidebar-nav">
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-tachometer-alt"></i>
                    </div> <span> Dashboard </span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link active">
                    <div>
                        <i class="fab fa-accusoft"></i>
                    </div> <span>App Home</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-tasks"></i>
                    </div> <span>My Tasks</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-spinner"></i>
                    </div> <span>WorkFlow</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-check-circle"></i>
                    </div> <span>Checklist</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-bug"></i>
                    </div> <span>Bugs</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-chart-line"></i>
                    </div> <span>Analytics</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-book-open"></i>
                    </div> <span>Information</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fas fa-adjust"></i>
                    </div> <span>Adjust</span>
                </a></li>
            <li class="sidebar-nav-item"><a href="index.html" class="sidebar-nav-link">
                    <div>
                        <i class="fab fa-algolia"></i>
                    </div> <span>Timeline</span>
                </a></li>
        </ul>
    </div>


    <!-- 
    <div class="wrapper">

        <div class="row">
            <div class="widget-tiles">
                <div class="row text-center">

                    <div class="col-3 col-m-6 col-sm-6">
                        <a target="_blank" href="https://github.com/ajkr195/Avni">
                            <div class="widget">
                                <div class="icon">
                                    <i class="fas fa-tasks"></i>
                                </div>
                                <div class="description">
                                    <h3>Widget 1</h3>
                                    <p>Some descriptive text. Try to zoom in-out the screen to
                                        see responsiveness.</p>
                                </div>
                            </div>
                        </a>
                    </div>


                    <div class="col-3 col-m-6 col-sm-6">
                        <a target="_blank" href="https://github.com/ajkr195/Avni">
                            <div class="widget">
                                <div class="icon">
                                    <i class="fas fa-chart-line"></i>
                                </div>
                                <div class="description">
                                    <h3>Widget 2</h3>
                                    <p>Some descriptive text. Try to zoom in-out the screen to
                                        see responsiveness.</p>
                                </div>
                            </div>
                        </a>
                    </div>

                    <div class="col-3 col-m-6 col-sm-6">
                        <a target="_blank" href="https://github.com/ajkr195/Avni">
                            <div class="widget">
                                <div class="icon">
                                    <i class="fas fa-gift"></i>
                                </div>
                                <div class="description">
                                    <h3>Widget 3</h3>
                                    <p>Some descriptive text. Try to zoom in-out the screen to
                                        see responsiveness.</p>
                                </div>
                            </div>
                        </a>
                    </div>

                    <div class="col-3 col-m-6 col-sm-6">
                        <a target="_blank" href="https://github.com/ajkr195/Avni">
                            <div class="widget">
                                <div class="icon">
                                    <i class="fas fa-book-open"></i>
                                </div>
                                <div class="description">
                                    <h3>Widget 4</h3>
                                    <p>Some descriptive text. Try to zoom in-out the screen to
                                        see responsiveness.</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>



        <div class="row">
            <div class="col-12 col-m-12 col-sm-12">
                <div class="card">
                    <div class="card-header">
                        <h3>Table</h3>
                        <i class="fas fa-ellipsis-h"></i>
                    </div>
                    <div class="card-content">
                        <table class="table no-margin">
                            <thead class="success">
                                <tr>
                                    <th>Name</th>
                                    <th>Sales</th>
                                    <th>Actual</th>
                                    <th>Some</th>
                                    <th>Thing</th>
                                    <th>Variance</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><i class="fas fa-user text-primary"></i> Some Name</td>
                                    <td>
                                        <div class="d-flex">
                                            <span class="pr-2 d-flex align-items-center">85%</span>
                                        </div>
                                    </td>
                                    <td>32,435</td>
                                    <td>53,462</td>
                                    <td>67,453</td>
                                    <td>
                                        <div class="progress-wrapper">
                                            <p>
                                                More than 6 hours <span class="float-right">20%</span>
                                            </p>
                                            <div class="progress">
                                                <div class="bg-danger" style="width: 20%"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><i class="fas fa-user text-success"></i> Cecelia
                                        Bradley</td>
                                    <td>
                                        <div class="d-flex">
                                            <span class="pr-2 d-flex align-items-center">55%</span>
                                        </div>
                                    </td>
                                    <td>4,36780</td>
                                    <td>32,435</td>
                                    <td>32,435</td>
                                    <td>
                                        <div class="progress-wrapper">
                                            <p>
                                                More than 3 hours <span class="float-right">40%</span>
                                            </p>
                                            <div class="progress">
                                                <div class="bg-warning" style="width: 40%"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><i class="fas fa-user text-warning"></i> Leah Sherman</td>
                                    <td>
                                        <div class="d-flex">
                                            <span class="pr-2 d-flex align-items-center">23%</span>
                                        </div>
                                    </td>
                                    <td>2,800</td>
                                    <td>32,435</td>
                                    <td>2,300</td>
                                    <td>
                                        <div class="progress-wrapper">
                                            <p>
                                                1 - 3 hours <span class="float-right">60%</span>
                                            </p>
                                            <div class="progress">
                                                <div class="bg-primary" style="width: 60%"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><i class="fas fa-user text-success"></i> Ina Curry</td>
                                    <td>
                                        <div class="d-flex">
                                            <span class="pr-2 d-flex align-items-center">44%</span>
                                        </div>
                                    </td>
                                    <td>4,36,780</td>
                                    <td>32,435</td>
                                    <td>53462</td>
                                    <td>
                                        <div class="progress-wrapper">
                                            <p>
                                                Less than 1 hour <span class="float-right">50%</span>
                                            </p>
                                            <div class="progress">
                                                <div class="bg-success" style="width: 50%"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><i class="fas fa-user text-success"></i> Lida
                                        Fitzgerald</td>
                                    <td>
                                        <div class="d-flex">
                                            <span class="pr-2 d-flex align-items-center">65%</span>
                                        </div>
                                    </td>
                                    <td>67,453</td>
                                    <td>4,36,780</td>
                                    <td>32,435</td>
                                    <td>
                                        <div class="progress-wrapper">
                                            <p>
                                                More than 6 hours <span class="float-right">20%</span>
                                            </p>
                                            <div class="progress">
                                                <div class="bg-danger" style="width: 20%"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><i class="fas fa-user text-danger"></i> Stella Johnson</td>
                                    <td>
                                        <div class="d-flex">
                                            <span class="pr-2 d-flex align-items-center">49%</span>
                                        </div>
                                    </td>
                                    <td>43,662</td>
                                    <td>67,453</td>
                                    <td>4,36780</td>
                                    <td>
                                        <div class="progress-wrapper">
                                            <p>
                                                1 - 3 hours <span class="float-right">60%</span>
                                            </p>
                                            <div class="progress">
                                                <div class="bg-primary" style="width: 60%"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><i class="fas fa-user text-success"></i> Maria Ortiz</td>
                                    <td>
                                        <div class="d-flex">
                                            <span class="pr-2 d-flex align-items-center">65%</span>
                                        </div>
                                    </td>
                                    <td>4,36780</td>
                                    <td>32,435</td>
                                    <td>53,462</td>
                                    <td>
                                        <div class="progress-wrapper">
                                            <p>
                                                1 - 3 hours <span class="float-right">60%</span>
                                            </p>
                                            <div class="progress">
                                                <div class="bg-primary" style="width: 60%"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div> -->


</body>
<script src="assets/js/script2.js"></script>
<script src="assets/js/script.js"></script>

</html>