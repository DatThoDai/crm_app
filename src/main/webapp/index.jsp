<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
    <title>CRM Dashboard</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- toast CSS -->
    <link href="plugins/bower_components/toast-master/css/jquery.toast.css" rel="stylesheet">
    <!-- morris CSS -->
    <link href="plugins/bower_components/morrisjs/morris.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <link rel="stylesheet" href="./css/custom.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header"> 
                <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse">
                    <i class="fa fa-bars"></i>
                </a>
                <div class="top-left-part">
                    <a class="logo" href="dashboard">
                        <b>
                            <img src="plugins/images/pixeladmin-logo.png" alt="home" />
                        </b>
                        <span class="hidden-xs">
                            <img src="plugins/images/pixeladmin-text.png" alt="home" />
                        </span>
                    </a>
                </div>
                <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                    <li>
                        <form role="search" class="app-search hidden-xs">
                            <input type="text" placeholder="Search..." class="form-control"> 
                            <a href="">
                                <i class="fa fa-search"></i>
                            </a>
                        </form>
                    </li>
                </ul>
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
            <!-- /.navbar-header -->
            <!-- /.navbar-top-links -->
            <!-- /.navbar-static-side -->
        </nav>
        <!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                <ul class="nav" id="side-menu">
                    <li style="padding: 10px 0 0;">
                        <a href="dashboard" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <li>
                        <a href="user" class="waves-effect"><i class="fa fa-user fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                    </li>
                    <li>
                        <a href="role" class="waves-effect"><i class="fa fa-modx fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                    </li>
                    <li>
                        <a href="jobs" class="waves-effect"><i class="fa fa-table fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                    </li>
                    <li>
                        <a href="tasks" class="waves-effect"><i class="fa fa-tasks fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                    </li>
                    <li>
                        <a href="blank.html" class="waves-effect"><i class="fa fa-columns fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                    </li>
                    <li>
                        <a href="404.html" class="waves-effect"><i class="fa fa-info-circle fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Dashboard</h4>
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- row -->
            <div class="row">
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-md-6 col-sm-6 col-xs-6"> <i data-icon="E"
                                    class="linea-icon linea-basic"></i>
                                <h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <h3 class="counter text-right m-t-15 text-danger">${pendingTasks}</h3>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="${pendingPercent}"
                                        aria-valuemin="0" aria-valuemax="100" style="width: ${pendingPercent}%"> <span
                                            class="sr-only">${pendingPercent}% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-md-6 col-sm-6 col-xs-6"> <i class="linea-icon linea-basic"
                                    data-icon="&#xe01b;"></i>
                                <h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <h3 class="counter text-right m-t-15 text-megna">${inProgressTasks}</h3>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-megna" role="progressbar" aria-valuenow="${inProgressPercent}"
                                        aria-valuemin="0" aria-valuemax="100" style="width: ${inProgressPercent}%"> <span
                                            class="sr-only">${inProgressPercent}% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-md-6 col-sm-6 col-xs-6"> <i class="linea-icon linea-basic"
                                    data-icon="&#xe00b;"></i>
                                <h5 class="text-muted vb">ĐÃ HOÀN THÀNH</h5>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <h3 class="counter text-right m-t-15 text-primary">${completedTasks}</h3>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="${completedPercent}"
                                        aria-valuemin="0" aria-valuemax="100" style="width: ${completedPercent}%"> <span
                                            class="sr-only">${completedPercent}% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
            </div>
            <!--row -->
            <div class="row">
                <!-- 1. Dự Án Cần Ưu Tiên -->
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title"><i class="fa fa-exclamation-triangle text-danger m-r-5"></i> Dự Án Cần Ưu Tiên</h3>
                        <p class="text-muted">Gần deadline và còn nhiều task chưa xong</p>
                        <div class="table-responsive" style="max-height: 370px; overflow-y: auto;">
                            <c:forEach var="project" items="${urgentProjects}">
                                <div class="m-b-20 p-10" style="border-left: 3px solid 
                                    ${project.daysRemaining <= 3 ? '#f62d51' : project.daysRemaining <= 7 ? '#ffa500' : '#03a9f3'};">
                                    <div class="row">
                                        <div class="col-xs-8">
                                            <h5 class="m-b-0"><strong>${project.name}</strong></h5>
                                            <small class="text-muted">
                                                <i class="fa fa-tasks"></i> ${project.incompleteTasks}/${project.totalTasks} chưa xong
                                            </small>
                                        </div>
                                        <div class="col-xs-4 text-right">
                                            <h4 class="m-t-0 ${project.daysRemaining <= 3 ? 'text-danger' : project.daysRemaining <= 7 ? 'text-warning' : 'text-info'}">
                                                ${project.daysRemaining}
                                            </h4>
                                            <small class="text-muted">ngày</small>
                                        </div>
                                    </div>
                                    <div class="progress m-t-10" style="height: 5px;">
                                        <div class="progress-bar ${project.daysRemaining <= 3 ? 'progress-bar-danger' : project.daysRemaining <= 7 ? 'progress-bar-warning' : 'progress-bar-info'}" 
                                             style="width: ${((project.totalTasks - project.incompleteTasks) * 100) / project.totalTasks}%"></div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${empty urgentProjects}">
                                <div class="text-center text-muted p-20">
                                    <i class="fa fa-check-circle fa-3x text-success"></i>
                                    <p class="m-t-10">Tất cả dự án đang trong tầm kiểm soát!</p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                
                <!-- 2. Task Sắp Hết Hạn -->
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title"><i class="fa fa-clock-o text-warning m-r-5"></i> Task Sắp Hết Hạn</h3>
                        <p class="text-muted">Deadline trong 7 ngày tới</p>
                        <div class="table-responsive" style="max-height: 370px; overflow-y: auto;">
                            <c:forEach var="task" items="${upcomingTasks}">
                                <div class="m-b-15 p-b-10" style="border-bottom: 1px solid #e4e7ea;">
                                    <div class="row">
                                        <div class="col-xs-9">
                                            <h5 class="m-b-5 m-t-0"><strong>${task.name}</strong></h5>
                                            <small class="text-muted">
                                                <i class="fa fa-user"></i> ${task.userName}
                                            </small><br/>
                                            <small class="text-muted">
                                                <i class="fa fa-folder"></i> ${task.jobName}
                                            </small>
                                        </div>
                                        <div class="col-xs-3 text-right">
                                            <span class="label ${task.daysRemaining == 0 ? 'label-danger' : task.daysRemaining <= 2 ? 'label-warning' : 'label-info'}">
                                                <c:choose>
                                                    <c:when test="${task.daysRemaining == 0}">Hôm nay</c:when>
                                                    <c:when test="${task.daysRemaining == 1}">Ngày mai</c:when>
                                                    <c:otherwise>${task.daysRemaining} ngày</c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${empty upcomingTasks}">
                                <div class="text-center text-muted p-20">
                                    <i class="fa fa-calendar-check-o fa-3x text-success"></i>
                                    <p class="m-t-10">Không có task nào sắp deadline!</p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                
                <!-- 3. Nhân Viên Quá Tải -->
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title"><i class="fa fa-user-times text-megna m-r-5"></i> Nhân Viên Quá Tải</h3>
                        <p class="text-muted">Cần hỗ trợ hoặc phân bổ lại công việc</p>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Nhân Viên</th>
                                        <th class="text-center">Đang Làm</th>
                                        <th class="text-center">Chờ</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${overloadedUsers}">
                                        <tr>
                                            <td>
                                                <strong>${user.fullname}</strong><br/>
                                                <small class="text-muted">${user.email}</small>
                                            </td>
                                            <td class="text-center">
                                                <span class="label ${user.inProgressTasks >= 5 ? 'label-danger' : user.inProgressTasks >= 3 ? 'label-warning' : 'label-info'}">
                                                    ${user.inProgressTasks}
                                                </span>
                                            </td>
                                            <td class="text-center">
                                                <span class="label label-default">${user.pendingTasks}</span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty overloadedUsers}">
                                        <tr>
                                            <td colspan="3" class="text-center text-muted">
                                                <i class="fa fa-smile-o"></i> Tất cả nhân viên đều ổn!
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!--Counter js -->
    <script src="plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
    <script src="plugins/bower_components/counterup/jquery.counterup.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="js/custom.min.js"></script>
    <script src="plugins/bower_components/toast-master/js/jquery.toast.js"></script>
    <script>
        // Counter animation
        $(document).ready(function() {
            $(".counter").counterUp({
                delay: 100,
                time: 1200
            });
        });
    </script>
</body>

</html>
