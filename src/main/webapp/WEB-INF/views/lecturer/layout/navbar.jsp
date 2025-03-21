<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!-- start navbar -->
    <div class="navbar">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link"> <i class="fas fa-bars" onclick="collapseSidebar()"></i>
                </a></li>
            <li class="nav-item"><img src="assets/img/leaf.svg" alt="logo" class="logo logo-light"> <img
                    src="assets/img/leaf.svg" alt="logo" class="logo logo-dark"></li>
        </ul>
        <h2>
            <a href="#" class="text-success no-decor">PTIT</a>
        </h2>

        <div class="collapse navbar-collapse mx-1" id="navbarsExample04">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <div class="nav-dropdown">
                        <a class="nav-item nav-link active text-secondary py-0" aria-current="page" href="/student"><i
                                class="data-feather theme-item" data-feather="home"></i>
                            <span class="theme-item">Home </span></a>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="nav-dropdown">
                        <a class="nav-item nav-link active text-secondary py-0" aria-current="page"
                            href="/student/profile/N22DCVT076"><i class="data-feather theme-item"
                                data-feather="user"></i>
                            <span class="theme-item">Profile </span></a>
                    </div>
                </li>
            </ul>

        </div>

        <ul class="navbar-nav nav-right">
            <li class="nav-item mode"><a class="nav-link" href="#" onclick="switchTheme()"> <i
                        class="fas fa-moon dark-icon"></i> <i class="fas fa-sun light-icon"></i>
                </a></li>
            <li class="nav-item dropdown"><a class="nav-link"> <i class="fas fa-bell dropdown-toggle"
                        data-toggle="notification-menu"></i>
                    <span class="navbar-badge">15</span>
                </a>
                <ul id="notification-menu" class="dropdown-menu notification-menu">
                    <div class="dropdown-menu-header">
                        <span> Notifications </span>
                    </div>
                    <div class="dropdown-menu-content overlay-scrollbar scrollbar-hover">
                        <li class="dropdown-menu-item"><a href="#" class="dropdown-menu-link">
                                <div>
                                    <i class="fas fa-gift"></i>
                                </div> <span> Lorem ipsum dolor sit amet, consectetuer
                                    adipiscing elit. <br> <span> 15/07/2020 </span>
                                </span>
                            </a></li>
                        <li class="dropdown-menu-item"><a href="#" class="dropdown-menu-link">
                                <div>
                                    <i class="fas fa-tasks"></i>
                                </div> <span> Lorem ipsum dolor sit amet, consectetuer
                                    adipiscing elit. <br> <span> 15/07/2020 </span>
                                </span>
                            </a></li>
                        <li class="dropdown-menu-item"><a href="#" class="dropdown-menu-link">
                                <div>
                                    <i class="fas fa-gift"></i>
                                </div> <span> Lorem ipsum dolor sit amet, consectetuer
                                    adipiscing elit. <br> <span> 15/07/2020 </span>
                                </span>
                            </a></li>
                    </div>
                    <div class="dropdown-menu-footer">
                        <span> View all notifications </span>
                    </div>
                </ul>
            </li>
            <li class="nav-item avt-wrapper">
                <div class="avt dropdown">
                    <img src="assets/img/user.svg" alt="User image" class="dropdown-toggle" data-toggle="user-menu">
                    <ul id="user-menu" class="dropdown-menu">
                        <li class="dropdown-menu-item"><a class="dropdown-menu-link">
                                <div>
                                    <i class="fas fa-user-tie"></i>
                                </div> <span>Profile</span>
                            </a></li>
                        <li class="dropdown-menu-item"><a href="#" class="dropdown-menu-link">
                                <div>
                                    <i class="fas fa-cog"></i>
                                </div> <span>Settings</span>
                            </a></li>
                        <li class="dropdown-menu-item"><a href="#" class="dropdown-menu-link">
                                <div>
                                    <i class="fas fa-sign-out-alt"></i>
                                </div> <span>Logout</span>
                            </a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>

    <!-- end navbar -->