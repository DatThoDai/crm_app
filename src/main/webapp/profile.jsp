<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile - CRM System</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
</head>

<body>
    <div class="preloader" style="display: none;">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header">
                <div class="top-left-part">
                    <a class="logo" href="dashboard">
                        <b><img src="plugins/images/pixeladmin-logo.png" alt="home" /></b>
                        <span class="hidden-xs"><img src="plugins/images/pixeladmin-text.png" alt="home" /></span>
                    </a>
                </div>
                <ul class="nav navbar-top-links navbar-right pull-right">
                    <li>
                        <div class="dropdown">
                            <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#"> 
                                <img src="plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle" />
                                <b class="hidden-xs">Cybersoft</b> 
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="profile">Thông tin cá nhân</a></li>
                                <li><a href="#">Thống kê công việc</a></li>
                                <li class="divider"></li>
                                <li><a href="logout">Đăng xuất</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        
        <!-- Left navbar -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li><a href="dashboard"><i class="fa fa-clock-o fa-fw"></i><span class="hide-menu">Dashboard</span></a></li>
                    <li><a href="user"><i class="fa fa-user fa-fw"></i><span class="hide-menu">Thành viên</span></a></li>
                    <li><a href="role"><i class="fa fa-modx fa-fw"></i><span class="hide-menu">Quyền</span></a></li>
                    <li><a href="jobs"><i class="fa fa-table fa-fw"></i><span class="hide-menu">Dự án</span></a></li>
                    <li><a href="tasks"><i class="fa fa-table fa-fw"></i><span class="hide-menu">Công việc</span></a></li>
                </ul>
            </div>
        </div>
        
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Chi tiết thành viên</h4>
                    </div>
                </div>
                
                <!-- User Info Row -->
                <div class="row">
                    <div class="col-md-4 col-xs-12">
                        <div class="white-box">
                            <div class="user-bg">
                                <img width="100%" alt="user" src="plugins/images/large/img1.jpg" style="height: 150px; object-fit: cover;">
                                <div class="overlay-box" style="position: absolute; top: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.3); display: flex; align-items: center; justify-content: center; flex-direction: column;">
                                    <div class="user-content text-center">
                                        <img src="plugins/images/users/genu.jpg" class="img-circle" alt="img" style="width: 80px; height: 80px;">
                                        <h4 class="text-white" style="margin: 10px 0 5px 0;">${user != null && user.fullName != null ? user.fullName : 'Người dùng'}</h4>
                                        <h5 class="text-white" style="margin: 0;">${user != null && user.email != null ? user.email : 'email@example.com'}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Statistics -->
                    <div class="col-md-8 col-xs-12">
                        <div class="row">
                            <!-- Pending Tasks -->
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box text-center">
                                    <h3 class="text-danger">${pendingTasks}</h3>
                                    <h5 class="text-muted">CHƯA BẮT ĐẦU</h5>
                                    <div class="progress" style="height: 5px;">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" style="width: ${pendingPercent != null ? pendingPercent : 0}%"></div>
                                    </div>
                                </div>
                            </div>
                            
                            <!-- In Progress Tasks -->
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box text-center">
                                    <h3 class="text-warning">${inProgressTasks}</h3>
                                    <h5 class="text-muted">ĐANG THỰC HIỆN</h5>
                                    <div class="progress" style="height: 5px;">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" style="width: ${inProgressPercent != null ? inProgressPercent : 0}%"></div>
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Completed Tasks -->
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box text-center">
                                    <h3 class="text-success">${completedTasks}</h3>
                                    <h5 class="text-muted">HOÀN THÀNH</h5>
                                    <div class="progress" style="height: 5px;">
                                        <div class="progress-bar progress-bar-success" role="progressbar" style="width: ${completedPercent != null ? completedPercent : 0}%"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Tasks List -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <h4>DANH SÁCH CÔNG VIỆC</h4>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên Công Việc</th>
                                            <th>Dự Án</th>
                                            <th>Ngày Bắt Đầu</th>
                                            <th>Ngày Kết Thúc</th>
                                            <th>Trạng Thái</th>
                                            <th>Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:choose>
                                            <c:when test="${not empty userTasks}">
                                                <c:forEach var="task" items="${userTasks}" varStatus="loop">
                                                    <tr>
                                                        <td>${loop.index + 1}</td>
                                                        <td>${task.name != null ? task.name : 'N/A'}</td>
                                                        <td>${task.jobName != null ? task.jobName : 'N/A'}</td>
                                                        <td>${task.startDate != null ? task.startDate : 'N/A'}</td>
                                                        <td>${task.endDate != null ? task.endDate : 'N/A'}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${task.statusId == 1}">
                                                                    <span class="label label-danger">${task.statusName != null ? task.statusName : 'Chưa bắt đầu'}</span>
                                                                </c:when>
                                                                <c:when test="${task.statusId == 2}">
                                                                    <span class="label label-warning">${task.statusName != null ? task.statusName : 'Đang thực hiện'}</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="label label-success">${task.statusName != null ? task.statusName : 'Hoàn thành'}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <a href="task-edit?id=${task.id}" class="btn btn-sm btn-primary">Cập nhật</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <tr>
                                                    <td colspan="7" class="text-center">Chưa có công việc nào</td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <footer class="footer text-center">2018 &copy; myclass.com</footer>
        </div>
    </div>
    
    <!-- jQuery -->
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Wave Effects -->
    <script src="js/waves.js"></script>
    
    <script>
        $(document).ready(function() {
            console.log('Profile page loaded successfully');
        });
    </script>
</body>
</html>
