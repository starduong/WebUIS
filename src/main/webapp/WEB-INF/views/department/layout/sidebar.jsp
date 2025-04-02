<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!doctype html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" type="image/x-icon" href="../assets/img/logo-ptit-1.svg">
    <title>UIS</title>
    <link href="../../../../resources/assets/css/bootstrap.css" rel="stylesheet">
    <link href="../../../../resources/assets/css/main.css" rel="stylesheet">
</head>
<body>
    <!-- start sidebar -->
    <nav id="sidebar" class="active">

        <div class="sidebar-header text-center">
            <img src="../assets/img/ptithcm-logo.jpg"  class="app-logo" style="min-width: 40px !important;">
            <h4 class="sidebar-title theme-item"></h4>
        </div>

        <ul class="list-unstyled components text-secondary">
            <li><a href="/student/conduct-score"><i class="data-feather theme-item" data-feather="user-check"></i> <span
                        class="theme-item">Điểm rèn luyện</span></a></li>
        </ul>

        <ul class="list-unstyled components text-secondary">
            <li><a href="/student/conduct-score"><i class="data-feather theme-item" data-feather="award"></i> <span
                        class="theme-item">Kết quả học tập</span></a></li>
        </ul>

        <ul class="list-unstyled components text-secondary">
            <li><a href="/student/conduct-score"><i class="data-feather theme-item" data-feather="file-text"></i> <span
                        class="theme-item">Mẫu đơn</span></a></li>
        </ul>

        <div class="text-center mt-5 mb-5">
            <a href="/student" class="theme-btn" onClick="removeCookieSidebar()">Reset</a>
        </div>

    </nav>
    <!-- end sidebar -->
</body>