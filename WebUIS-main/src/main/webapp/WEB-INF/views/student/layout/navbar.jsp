<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!-- start navbar -->
    <nav class="navbar navbar-expand-md navbar-white bg-white py-0" aria-label="navbarexample" id="navbar">
        <div class="container-fluid">
            <button type="button" id="sidebarCollapse" class="btn btn-light py-0">
                <i data-feather="menu"></i> <span></span>
            </button>
            <img src="/assets/img/leaf.svg" alt="logo" class="app-logo theme-item mx-2 navbrandarea1">
            <h4 class="sidebar-title theme-item mt-2 navbrandarea2">PTIT</h4>
            <button class="navbar-toggler py-0" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"><i data-feather="menu"></i></span>
            </button>

            <div class="collapse navbar-collapse mx-1" id="navbarsExample04">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <div class="nav-dropdown">
                            <a class="nav-item nav-link active text-secondary py-0" aria-current="page"
                                href="/student"><i class="data-feather theme-item" data-feather="home"></i>
                                <span class="theme-item">Trang chủ</span></a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="nav-dropdown">
                            <a class="nav-item nav-link active text-secondary py-0" aria-current="page"
                                href="/student/profile/N22DCVT076"><i class="data-feather theme-item"
                                    data-feather="user"></i>
                                <span class="theme-item">Thông tin cá nhân</span></a>
                        </div>
                    </li>

                </ul>

                <div class="usermenu">
                    <div class="nav-dropdown py-0">
                        <a href="#" class="nav-item nav-link dropdown-toggle text-secondary py-0" id="navbarDropdown3"
                            role="button" data-bs-toggle="dropdown" aria-expanded="false"> <img
                                class="theme-item user-avatar" src="/assets/img/ptithcm-logo.jpg" alt="User image">
                            <span class="theme-item">My
                                Name</span><i class="theme-item" data-feather="chevron-down"></i></a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown3">

                            <li><a href="#" class="dropdown-item mt-2"><i class="data-feather" data-feather="user"></i>
                                    Profile</a></li>

                            <li><a href="#" class="dropdown-item mt-2"><i class="data-feather" data-feather="mail"></i>
                                    Messages</a></li>

                            <li><a href="#" class="dropdown-item mt-2" data-bs-toggle="modal"
                                    data-bs-target="#settingsModal"><i class="data-feather" data-feather="settings"></i>
                                    Settings</a></li>

                            <li><a href="#" class="dropdown-item mt-2"><i class="data-feather"
                                        data-feather="log-out"></i>
                                    Logout</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </nav>
    <!-- end navbar -->

    <!-- start setting navbar-->
    <div class="settings">
        <div class="modal fade" id="settingsModal" aria-labelledby="settingsModalTitle" aria-hidden="true"
            tabindex="-1">
            <div class="modal-dialog modal-dialog-settings">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title" id="exampleModalLabel">Settings</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <section id="logincontent" class="shiftdown">

                            <div class="row g-3 mb-3 mt-3">

                                <div class="col-md-6">
                                    <h6 class="text-muted">Select Color</h6>
                                    <span onclick="changeColor('0')" class="btn btn-sm btn-primary rounded-circle"><span
                                            class="me-2"></span></span> <span onclick="changeColor('1')"
                                        class="btn btn-sm btn-success rounded-circle"><span class="me-2"></span></span>
                                    <span onclick="changeColor('2')" class="btn btn-sm btn-danger rounded-circle"><span
                                            class="me-2"></span></span> <span onclick="changeColor('3')"
                                        class="btn btn-sm btn-warning rounded-circle"><span class="me-2"></span></span>
                                    <span onclick="changeColor('4')"
                                        class="btn btn-sm btn-secondary rounded-circle"><span
                                            class="me-2"></span></span>
                                    <div class="d-flex justify-content-between">
                                        <button onclick="removeColorCookie()">Reset to
                                            Default</button>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <h6 class="text-muted">Preferences</h6>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                                        <label class="form-check-label" for="flexSwitchCheckDefault">Switch
                                            option 1</label>
                                    </div>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked"
                                            checked>
                                        <label class="form-check-label" for="flexSwitchCheckChecked">Switch
                                            option 2</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row g-3 mb-3 mt-3">
                                <div class="col-md-6">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck1"
                                            checked>
                                        <label class="form-check-label" for="defaultCheck1">
                                            Checkbox 1 </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck2">
                                        <label class="form-check-label" for="defaultCheck2">
                                            Checkbox 2</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <h6 class="text-muted">View Size</h6>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault"
                                            id="radioCompactView"> <label class="form-check-label"
                                            for="radioCompactView">
                                            Compact</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault"
                                            id="radioFullView"> <label class="form-check-label" for="radioFullView">
                                            Full-screen </label>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <button onclick="removeViewSizeCookie()">Reset to
                                            Default</button>
                                    </div>

                                </div>
                            </div>
                            <hr />
                            <button class="btn btn-sm btn-danger" data-bs-dismiss="modal" type="button">
                                <i data-feather="check-circle" class="mr-1"></i> Ok
                            </button>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end setting navbar-->