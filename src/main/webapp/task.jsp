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
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Quản lý công việc</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <link rel="stylesheet" href="./css/custom.css">
    
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
                            <a href="javascript:void(0)" class="open-close hidden-xs waves-effect waves-light">
                                <i class="fa fa-bars"></i>
                            </a>
                        </li>
                    </ul>
                    <ul class="nav navbar-top-links navbar-right pull-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> 
                                <img src="plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle">
                                <b class="hidden-xs">Cybersoft</b> 
                            </a>
                            <ul class="dropdown-menu dropdown-user animated flipInY">
                                <li>
                                    <a href="profile">
                                        <i class="ti-user"></i> Thông tin cá nhân</a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="ti-wallet"></i> Thống kê công việc</a>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li>
                                    <a href="logout">
                                        <i class="fa fa-power-off"></i> Đăng xuất</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
        </nav>
        <!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                <ul class="nav" id="side-menu">
                    <li style="padding: 10px 0 0;">
                        <a href="dashboard" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <li>
                        <a href="user" class="waves-effect"><i class="fa fa-user fa-fw" aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                    </li>
                    <li>
                        <a href="role" class="waves-effect"><i class="fa fa-modx fa-fw" aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                    </li>
                    <li>
                        <a href="jobs" class="waves-effect"><i class="fa fa-table fa-fw" aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                    </li>
                    <li>
                        <a href="tasks" class="waves-effect active"><i class="fa fa-tasks fa-fw" aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                    </li>
                    <li>
                        <a href="blank.html" class="waves-effect"><i class="fa fa-columns fa-fw" aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                    </li>
                    <li>
                        <a href="404.html" class="waves-effect"><i class="fa fa-info-circle fa-fw" aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
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
                        <h4 class="page-title">Danh sách công việc</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                        <a href="task-add" class="btn btn-sm btn-success">Thêm mới</a>
                    </div>
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box"> 
                            <div class="table-responsive">
                                <table id="myTable" class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên công việc</th>
                                            <th>Dự án</th>
                                            <th>Người thực hiện</th>
                                            <th>Ngày bắt đầu</th>
                                            <th>Ngày kết thúc</th>
                                            <th>Trạng thái</th>
                                            <th>Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="task" items="${listTasks}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${task.name}</td>
                                                <td>${task.jobName}</td>
                                                <td>${task.userName}</td>
                                                <td>${task.startDate}</td>
                                                <td>${task.endDate}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${task.statusId == 1}">
                                                            <span class="label label-danger">${task.statusName}</span>
                                                        </c:when>
                                                        <c:when test="${task.statusId == 2}">
                                                            <span class="label label-warning">${task.statusName}</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="label label-success">${task.statusName}</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a href="task-edit?id=${task.id}" class="btn btn-sm btn-primary">
                                                        <i class="fa fa-pencil"></i>
                                                    </a>
                                                    <button onclick="confirmDelete(${task.id}, '${task.name}')" class="btn btn-sm btn-danger">
                                                        <i class="fa fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2017 &copy; Pixel Admin brought to you by wrappixel.com </footer>
        </div>
        <!-- /#page-wrapper -->
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
    <!-- Custom Theme JavaScript -->
    <script src="js/custom.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#myTable').DataTable();
            
            // Hiển thị thông báo
            const urlParams = new URLSearchParams(window.location.search);
            const msg = urlParams.get('msg');
            
            if (msg === 'addSuccess') {
                Swal.fire({
                    icon: 'success',
                    title: 'Thành công!',
                    text: 'Thêm công việc thành công!',
                    timer: 2000,
                    showConfirmButton: false
                });
            } else if (msg === 'addError') {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi!',
                    text: 'Thêm công việc thất bại!',
                    confirmButtonText: 'OK'
                });
            } else if (msg === 'updateSuccess') {
                Swal.fire({
                    icon: 'success',
                    title: 'Thành công!',
                    text: 'Cập nhật công việc thành công!',
                    timer: 2000,
                    showConfirmButton: false
                });
            } else if (msg === 'updateError') {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi!',
                    text: 'Cập nhật công việc thất bại!',
                    confirmButtonText: 'OK'
                });
            } else if (msg === 'deleteSuccess') {
                Swal.fire({
                    icon: 'success',
                    title: 'Thành công!',
                    text: 'Xóa công việc thành công!',
                    timer: 2000,
                    showConfirmButton: false
                });
            } else if (msg === 'deleteError') {
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi!',
                    text: 'Xóa công việc thất bại!',
                    confirmButtonText: 'OK'
                });
            }
        });
        
        // Hàm xác nhận xóa
        function confirmDelete(id, taskName) {
            Swal.fire({
                title: 'Bạn có chắc chắn?',
                text: "Bạn muốn xóa công việc '" + taskName + "'?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Xóa',
                cancelButtonText: 'Hủy'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = 'task-delete?id=' + id;
                }
            });
        }
    </script>
</body>

</html>
