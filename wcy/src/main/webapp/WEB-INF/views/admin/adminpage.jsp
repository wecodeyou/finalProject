<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- 파비콘 적용 -->
<link rel="shortcut icon" href="<c:url value='/img/favicon/wcy-favicon.ico'/>">
<link rel="stylesheet" href="<c:url value='/css/commons.css'/>">

<!-- Custom fonts for this template-->
<!-- <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css"> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/css/sb-admin-2.min.css" rel="stylesheet">

<link href="/css/dataTables.bootstrap4.min.css" rel="stylesheet" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

<title>WE CODE YOU | 관리자 페이지</title>
<script src="https://code.jquery.com/jquery-3.5.0.min.js" integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>
<style>

    p{line-height: 150% !important;}
    
    .center-header .center-sub-nav {width: 100%; background: #f4f4f4; border-radius: 5px; display: table; table-layout: fixed; margin-top: 20px; margin-bottom: 50px; overflow: hidden;}
    .center-header .center-sub-nav li{display: table-cell;}
    .center-header .center-sub-nav li a{display: block; height: 60px; box-sizing: border-box; font-size: 18px; color: #888; padding: 0 0 0 20px; line-height: 60px; border-bottom: 4px solid #ddd;}
    .center-sub-nav li a.active{color: #25283D; border-color: #25283D;}

	#adminUser {display:none;}

</style>

</head>
<body>

<jsp:include page="../include/header-sub.jsp" />

<main class="wcy-main-content">
		<div class="wcy-contents">
			<div class="page-width">
				<div class="center-header">
					<ul class="center-sub-nav">
						<li><a class="active" href="javascript:adminChart();">관리자 대시보드</a></li>
						<li><a href="javascript:adminUser();">회원 관리</a></li>
						<%-- <li><a href="<c:url value='/admin/etc'/>">기타 관리</a></li> --%>
					</ul>
				</div>
			</div>
			<!-- Begin Page Content -->
			<div class="container-fluid" id="adminChart">

				<!-- Page Heading -->
				<div
					class="d-sm-flex align-items-center justify-content-between mb-4">
					<h1 class="h3 mb-0 text-gray-800">한 눈에 보기</h1>
					<a href="#"
						class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
						class="fas fa-download fa-sm text-white-50"></i> 월간 리포트 등록</a>
					
				</div>

				<!-- Content Row -->
				<div class="row">

					<!-- Earnings (Monthly) Card Example -->
					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card border-left-primary shadow h-100 py-2">
							<div class="card-body">
								<div class="row no-gutters align-items-center">
									<div class="col mr-2">
										<div class="text-s font-weight-bold text-primary text-uppercase mb-1">
											${thisMonthly.purchaseM}월 수입</div>
										<div class="h5 mb-0 font-weight-bold text-gray-800">
											<fmt:formatNumber type="currency" value="${thisMonthly.earningMonthly}" />
										</div>
									</div>
									<div class="col-auto">
										<i class="fas fa-calendar fa-2x text-gray-300"></i>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Earnings (Monthly) Card Example -->
					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card border-left-success shadow h-100 py-2">
							<div class="card-body">
								<div class="row no-gutters align-items-center">
									<div class="col mr-2">
										<div class="text-s font-weight-bold text-success text-uppercase mb-1">
											${thisAnnual.purchaseY}년 수입</div>
										<div class="h5 mb-0 font-weight-bold text-gray-800">
											<fmt:formatNumber type="currency" value="${thisAnnual.earningAnnual}" />
										</div>
									</div>
									<div class="col-auto">
										<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Earnings (Monthly) Card Example -->
					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card border-left-danger shadow h-100 py-2">
							<div class="card-body">
								<div class="row no-gutters align-items-center">
									<div class="col mr-2">
										<div class="text-s font-weight-bold text-danger text-uppercase mb-1">
										이달의 환불 <fmt:formatNumber value="${thisMonthly.refundCount/thisMonthly.purchaseCount}" type="percent" /> </div>
										<div class="row no-gutters align-items-center">
											<div class="col-auto">
												<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">총 ${thisMonthly.refundCount}개
												</div>
											</div>
											<div class="col">
												<div class="progress progress-sm mr-2">
													<div class="progress-bar bg-danger" role="progressbar"
														style="width: ${thisMonthly.refundCount/thisMonthly.purchaseCount*100}%" aria-valuenow="50" aria-valuemin="0"
														aria-valuemax="100"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-auto">
										<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Pending Requests Card Example -->
					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card border-left-warning shadow h-100 py-2">
							<div class="card-body">
								<div class="row no-gutters align-items-center">
									<div class="col mr-2">
										<div
											class="text-s font-weight-bold text-warning text-uppercase mb-1">건의사항</div>
										<div class="h5 mb-0 font-weight-bold text-gray-800">18건</div>
									</div>
									<div class="col-auto">
										<i class="fas fa-comments fa-2x text-gray-300"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Content Row -->

				<div class="row">

					<!-- Area Chart -->
					<div class="col-xl-8 col-lg-7">
						<div class="card shadow mb-4">
							<!-- Card Header - Dropdown -->
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">수익 그래프</h6>
								<div class="dropdown no-arrow">
									<a class="dropdown-toggle" href="#" role="button"
										id="dropdownMenuLink" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <i
										class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
										aria-labelledby="dropdownMenuLink">
										<div class="dropdown-header">Dropdown Header:</div>
										<a class="dropdown-item" href="#">Action</a> <a
											class="dropdown-item" href="#">Another action</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#">ShxncvdSsdfgh else here</a>
									</div>
								</div>
							</div>
							<!-- Card Body -->
							<div class="card-body">
								<div class="chart-area">
									<%-- <canvas id="myAreaChart"></canvas> --%>
									<canvas id="myChart" width="800" height="450"></canvas>
								</div>
							</div>
						</div>
					</div>

					<!-- Pie Chart -->
					<div class="col-xl-4 col-lg-5">
						<div class="card shadow mb-4">
							<!-- Card Header - Dropdown -->
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">사이트 유입 경로</h6>
								<div class="dropdown no-arrow">
									<a class="dropdown-toggle" href="#" role="button"
										id="dropdownMenuLink" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <i
										class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
										aria-labelledby="dropdownMenuLink">
										<div class="dropdown-header">Dropdown Header:</div>
										<a class="dropdown-item" href="#">Action</a> <a
											class="dropdown-item" href="#">Another action</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
							</div>
							<!-- Card Body -->
							<div class="card-body">
								<div class="chart-pie pt-4 pb-2">
									<canvas id="myPieChart"></canvas>
								</div>
								<div class="mt-4 text-center small">
									<span class="mr-2" >
										<i class="fas fa-circle text-primary"></i> 
										<span id="spanId1"></span>
									</span> 
									<span class="mr-2"> 
										<i class="fas fa-circle text-success"></i> 
										<span id="spanId2"></span>
									</span> 
									<span class="mr-2"> 
										<i class="fas fa-circle text-info"></i>
										<span id="spanId3"></span>
									</span>
									<span class="mr-2"> 
										<i class="fas fa-circle text-secondary"></i>
										<span id="spanId4"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Content Row -->
				<div class="row">

					<!-- Content Column -->
					<div class="col-lg-6 mb-4">
						<!-- lang -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">선호하는 언어 Top 5</h6>
							</div>
							<div class="card-body">
								<div class="chart-bar pt-4 pb-2">
									<canvas id="myBarChart"></canvas>
								</div>
							</div>
						</div>
					</div>

					<div class="col-lg-6 mb-4">
						<!-- Project Card Example -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">사이트 이용 목적</h6>
							</div>
							<div class="card-body"><br>
								<c:forEach items="${reportList}" var="rL" varStatus="status">
									<c:if test="${status.index eq 0}">
										<h4 class=" font-weight-bold">
											${rL.interestIndex}<br><br>
											<span class="float-right">
												<fmt:formatNumber value="${rL.cnt / total}" type="percent" /></span>
										</h4>
										<div class="progress mb-4">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: ${rL.cnt / total * 100}%" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100"></div>
										</div>
										<br>
									</c:if>
									<c:if test="${status.index eq 1}">
											<h4 class=" font-weight-bold">
											${rL.interestIndex}<br><br>
											<span class="float-right">
												<fmt:formatNumber value="${rL.cnt / total}" type="percent" /></span>
										</h4>
										<div class="progress mb-4">
											<div class="progress-bar" role="progressbar"
												style="width: ${rL.cnt / total * 100}%" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100"></div>
										</div>
										<br>
									</c:if>
									<c:if test="${status.index eq 2}">
										<h4 class=" font-weight-bold">
											${rL.interestIndex}<br><br>
											<span class="float-right">
												<fmt:formatNumber value="${rL.cnt / total}" type="percent" /></span>
										</h4>
										<div class="progress mb-4">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: ${rL.cnt / total * 100}%" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100"></div>
										</div>
										<br>
									</c:if>
									<c:if test="${status.index eq 3}">
											<h4 class=" font-weight-bold">
											${rL.interestIndex}<br><br>
											<span class="float-right">
												<fmt:formatNumber value="${rL.cnt / total}" type="percent" /></span>
										</h4>
										<div class="progress mb-4">
											<div class="progress-bar" role="progressbar"
												style="width: ${rL.cnt / total * 100}%" aria-valuenow="20" aria-valuemin="0"
												aria-valuemax="100"></div>
										</div>
									</c:if>
								</c:forEach>
								<br>
							</div>
						</div>
						
						<!-- Approach -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">Development
									Approach</h6>
							</div>
							<div class="card-body">
								<p>SB Admin 2 makes extensive use of Bootstrap 4 utility
									classes in order to reduce CSS bloat and poor page performance.
									Custom CSS classes are used to create custom components and
									custom utility classes.</p>
								<p class="mb-0">Before working with this theme, you should
									become familiar with the Bootstrap framework, especially the
									utility classes.</p>
							</div>
						</div>
						
						

					</div>
				</div>

			</div>
			<!-- /.container-fluid -->
			
			<div class="container-fluid" id="adminUser">
			
				<div class="card mb-4">
                            <div class="card-header"><i class="fas fa-table mr-1"></i>WE CODE YOU | 회원 목록</div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>UserType</th>
                                                <th>Email</th>
                                                <th>Name</th>
                                                <th>Tel</th>
                                                <th>Created_at</th>
                                                <th>Login_at</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="m" items="${members}">
                                        		<tr>
                                        			<td>
                                        			<div style="text-align:center">
                                        				<c:if test="${m.userType == 0}">
                                        					일반회원
                                        				</c:if>
                                        				<c:if test="${m.userType == 1}">
                                        					강사
                                        				</c:if>
                                        				<c:if test="${m.userType == 2}">
                                        					*관리자
                                        				</c:if>
                                        				<button type="button" style="font-size:11px; width:50px"class="btn btn-info" value="${m.userNo}" id="authChangeU">변경</button>
                                        			</div>
                                   
                                        			</td>
                                        			<td>${m.userEmail}</td>
                                        			<td>${m.userName}</td>
                                        			<td>${m.userTel}</td>
                                        			<td>${m.userCreatedAt}</td>
                                        			<td>${m.userLoginAt}</td>
                                        		</tr>
                                        	</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
			</div>
			<!-- /.container-fluid -->

		</div>
	</main>

<%@ include file="../admin/user-change-modal.jsp" %>  
 
<script src="<c:url value = "/js/jquery-3.0.0.min.js"/>"></script>
<script src="<c:url value = "/js/main.js"/>"></script>

<!-- 여기서부터 부트스트랩 -->
<!-- Bootstrap core JavaScript-->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="/js/demo/chart-area-demo.js"></script>
<script src="/js/demo/chart-pie-demo.js"></script>
<script src="/js/demo/chart-bar-demo.js"></script>

<!-- 데이터 테이블 -->
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>


<!-- 여기까지 -->  

<script type="text/javascript">

//authChangeU authChangeL
  $("#authChangeU").click(function() {
	  
  });


var sBtn = $(".center-sub-nav > li");    //  ul > li 이를 sBtn으로 칭한다. (클릭이벤트는 li에 적용 된다.)
$(function(){
	  sBtn.find("a").click(function(){   // sBtn에 속해 있는  a 찾아 클릭 하면.
	  sBtn.find("a").removeClass("active");     // sBtn 속에 (active) 클래스를 삭제 한다.
	  $(this).addClass("active"); // 클릭한 a에 (active)클래스를 넣는다.
	});
});


	function adminChart(){
		$('#adminChart').show();
 		$('#adminUser').hide();
	}
	function adminUser(){
		$('#adminChart').hide();
		$('#adminUser').show();
	
	}
	
</script>

</body>
<jsp:include page="../include/footer.jsp" /> 
</html>