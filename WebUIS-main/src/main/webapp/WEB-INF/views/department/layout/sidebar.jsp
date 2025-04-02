<%@page contentType="text/html" pageEncoding="UTF-8" %>


    <!-- start sidebar -->
    <nav id="sidebar" class="active">

        <div class="sidebar-header text-center">
            <img src="../assets/img/leaf.svg" alt="logo" class="app-logo">
            <h4 class="sidebar-title theme-item">PTIT</h4>
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