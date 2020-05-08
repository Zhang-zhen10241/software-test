<%@ page language="java" pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>双优英语-学而思网校网校，自主建课页面欢迎您！</title>
		<meta name="description" content="这是一个 index 页面">
		<meta name="keywords" content="index">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link rel="icon" type="image/png" href="assets/i/timg.jpg">
		<link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
		<meta name="apple-mobile-web-app-title" content="Amaze UI" />
		<script src="assets/js/echarts.min.js"></script>
		<link rel="stylesheet" href="assets/css/amazeui.min.css" />
		<link rel="stylesheet" href="assets/css/amazeui.datatables.min.css" />
		<link rel="stylesheet" href="assets/css/app.css">
		<script src="assets/js/jquery.min.js"></script>
	</head>

	<body data-type="widgets">
		<script src="assets/js/theme.js"></script>
		<div class="am-g tpl-g">
			<!-- 头部 -->
			<header>
				<!-- logo -->
				<div class="am-fl tpl-header-logo" style="width: 240px; height: 100px; position: fixed;">
					<a href="javascript:;"><img src="assets/img/timg.gif" alt="" style="width: 220px; height: 80px;"></a>
				</div>
				<!-- 右侧内容 -->
				<div class="tpl-header-fluid">
					<!-- 侧边切换 -->
					<div class="am-fl tpl-header-switch-button am-icon-list">
						<span> </span>
					</div>
					
					<div class="am-fr tpl-header-navbar">
						<ul>
							<!-- 欢迎语 -->
							<li class="am-text-sm tpl-header-navbar-welcome">
								<a href="javascript:;">学而思网校, <span>在线学习更高效</span>
								</a>
							</li>

							<!-- 退出 -->
							<!--<li class="am-text-sm">
								<a href="javascript:;"> <span class="am-icon-sign-out"></span> 退出
								</a>
							</li>-->
						</ul>
					</div>
				</div>

			</header>
			
			<div class="left-sidebar" id="sidebar">
				
				<ul class="sidebar-nav">
					
					<li class="sidebar-nav-link">
						<a href="CreateCourse.html" class="active">
							<i class="am-icon-wpforms sidebar-nav-link-logo"></i> 创建一节普通/同大纲课程
						</a>
					</li>
					<li class="sidebar-nav-link">
						<a href="updating.html">
							<i class="am-icon-wpforms sidebar-nav-link-logo"></i> 持续更新中，敬请期待
						</a>
					</li>
					<li class="sub-menu dcjq-parent-li" style="position:fixed;width:240px;bottom: 0px;border: 20px solid #3A4144;">
						<!--<a href="javascript:;" class="active dcjq-parent">
							<i class="fa fa-cogs"></i>-->
						<span style="color: #888888;">友情链接</span>
						
						<ul class="sub" style="display: block;">
							<li>
								<a href="http://qa.xesv5.com/" target="_blank">学而思网校QA</a>
							</li>
							<li>
								<a href="http://mail.100tal.com/" target="_blank">好未来邮箱</a>
							</li>
							<li>
								<a href="http://qa.xesv5.com/cdutil/index" target="_blank">测试用例上传系统</a>
							</li>
							<li>
								<a href="http://10.17.81.55:8080/org/index.do" target="_blank">Mock平台</a>
							</li>
							<li>
								<a href="http://wiki.xesv5.com" target="_blank">学而思网校WIKI</a>
							</li>
							<li>
								<a href="http://admin.xesv5.com/" target="_blank">学而思网校业务后台</a>
							</li>
							<li>
								<a href="http://qa.xesv5.com/pm/pList" target="_blank">测试问题反馈</a>
							</li>
						</ul>
					</li>
					

				</ul>
			</div>

			<!-- 内容区域 -->
			<div class="tpl-content-wrapper">

				<div class="row-content am-cf">

					<div class="row">

						<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
							<div class="widget am-cf">
								
								<div class="widget-body am-fr">

									<form class="am-form tpl-form-line-form">
									
										<!-- <div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">创建测试环境课程，点击去创建线上环境课程
												<span class="tpl-form-line-small-title"></span>
										    </label>
											<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="createOnlineCourse()">去创建线上课程</button>
											</div>
										</div>
										</div> -->
										
										<!-- <div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">创建线上环境课程，点击去创建测试环境课程
												<span class="tpl-form-line-small-title"></span>
										    </label>
											<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="createTestCourse()">去创建测试课程</button>
											</div>
										</div>
										</div> -->
										
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">建课人员Cookie
											<span class="tpl-form-line-small-title">cookie</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="cookie" placeholder="建课人员的cookie" value=""> <small>建课人员的cookie，请输入建课人员的cookie，asid=***/wx_sid=***</small>
											</div>
										</div>									
										
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">审核人员Cookie
											<span class="tpl-form-line-small-title">reviewCookie</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="reviewCookie" placeholder="审核人员的cookie" value=""> <small>审核人员的cookie，请输入审核人员的cookie，asid=***/wx_sid=***</small>
											</div>
										</div>
										
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">课程前缀标识
											<span class="tpl-form-line-small-title">namePrefix</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="namePrefix" placeholder="[test]" value="[test]"> <small>课程名称前缀标识，请输入课程名称前缀标识</small>
											</div>
										</div>							

										<div class="am-form-group">
											<label for="user-phone" class="am-u-sm-3 am-form-label">年级
											<span class="tpl-form-line-small-title">grade</span>
										</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{searchBox: 1}" style="display: none;" id="grade">
													<option value="24">小班</option>
													<option value="25">中班</option>
													<option value="1">大班</option>
													<option value="2" selected = "selected">一年级</option>
													<option value="3">二年级</option>
													<option value="4">三年级</option>
													<option value="5">四年级</option>
													<option value="6">五年级</option>
													<option value="7">六年级</option>
													<option value="8">初一</option>
													<option value="9">初二</option>
													<option value="10">初三</option>
													<option value="11">高一</option>
													<option value="12">高二</option>
													<option value="13">高三</option>
												</select> <br /> <small>请选择所建课程对应的年级</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone" class="am-u-sm-3 am-form-label">学科
											<span class="tpl-form-line-small-title">subject</span>
										</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{searchBox: 1}" style="display: none;" id="subject">
													<option value="1" selected="selected">语文</option>
													<option value="2">数学</option>
													<option value="3">英语</option>
													<option value="26">编程</option>
													<option value="4">物理</option>
													<option value="5">化学</option>																																						
													<option value="6">生物</option>
													<option value="9">地理</option>
													<option value="7">政治</option>
													<option value="8">历史</option>
													<option value="21">科学</option>
													<option value="20">趣味</option>
													<option value="25">生化</option>													
													<option value="10">魔方</option>
													<option value="11">音乐</option>
													<option value="15">天文</option>
													<option value="23">动物</option>
													<option value="27">财商</option>
														<!-- <option value="历史">历史</option>
												<option value="文化">文化</option>
												<option value="人工智能">人工智能</option>
												<option value="儿童安全">儿童安全</option>
												<option value="美学">美学</option>
												<option value="电影">电影</option>
												<option value="戏剧">戏剧</option>
												<option value="建筑">建筑</option>
												<option value="植物">植物</option>
												<option value="人体">人体</option>
												<option value="逻辑">逻辑</option>
												<option value="心理">心理</option>
												<option value="法学">法学</option>
												<option value="军事">军事</option>
												<option value="航空航天">航空航天</option>
												<option value="围棋">围棋</option>
												<option value="故宫">故宫</option> -->
												</select> <br /> <small>请选择你所建课程的学科</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone" class="am-u-sm-3 am-form-label">学期
											<span class="tpl-form-line-small-title">term</span>
										</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{searchBox: 1}" style="display: none;" id="term">
													<option value="1">春季班</option>
													<option value="2">暑假班</option>
													<option value="3" selected="selected">秋季班</option>
													<option value="4">寒假班</option>
													<option value="5">春上</option>
													<option value="6">春下</option>
													<option value="7">秋上</option>
													<option value="8">秋下</option>
												</select> <br /> <small>请选择你所建课程的学期对应的value（春季班/暑假班/秋季班/寒假班） 数字所对应的学期：1-春季班 2-暑假班 3-秋季班 4-寒假班</small>
											</div>
										</div>																			

										<div class="am-form-group">
											<label for="user-phone" class="am-u-sm-3 am-form-label">课程品类
											<span class="tpl-form-line-small-title">category</span>
										</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{searchBox: 1}" style="display: none;" id="category">
													<option value="8" selected="selected">直播小班</option>
													<option value="9">直播大班</option>
													<option value="231">大班次卡</option>
													<option value="232">1V1次卡</option>
													<option value="1">录播</option>
												</select> <br /> <small>请选择你所建课程的品类对应的value（直播小班/直播大班）
												数字所对应的品类：8-直播小班 9-直播大班</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">场次数
											<span class="tpl-form-line-small-title">catalog_num</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="catalog_num" placeholder="50" value="50"> <small>请输入你所建课程的场次</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">讲义ID
											<span class="tpl-form-line-small-title">materialId</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="materialId" placeholder="" value=""> <small>请输入你所建课程所需要的讲义的ID</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">主讲老师ID
											<span class="tpl-form-line-small-title">teacherId</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="teacherId" placeholder="" value=""> <small>请输入你所建课程的主讲老师的ID</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">辅导老师ID
											<span class="tpl-form-line-small-title">counselorTeacherId</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="counselorTeacherId" placeholder="2377" value="2377"> <small>请输入你所建课程的辅导老师的ID</small>
											</div>
										</div>
																						
										<div class="am-form-group">
											<label for="user-phone" class="am-u-sm-3 am-form-label">循环周期
											<span class="tpl-form-line-small-title">cycleStr</span>
										</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{searchBox: 1}" style="display: none;" id="cycleStr">
													<option value="1">1天</option>
													<option value="2" selected="selected">2天</option>
													<option value="3">3天</option>
													<option value="4">4天</option>
													<option value="5">5天</option>
													<option value="6">6天</option>
													<option value="7" >7天</option>
												</select> <br /> <small>请选择你所建课程所需时间模板的循环周期</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">班级限额
											<span class="tpl-form-line-small-title">courseLimit</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="courseLimit" placeholder="10" value="10"> <small>请输入班级限额</small>
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">课程限额
											<span class="tpl-form-line-small-title">classLimit</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="classLimit" placeholder="2" value="2"> <small>请输入课程限额</small>
											</div>
										</div>
										
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">已存在的大纲ID
											<span class="tpl-form-line-small-title">existedOutlineId</span>
										</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="existedOutlineId" placeholder="" value=""> <small>已存在的大纲ID，非创建同大纲课程的伙伴请忽略！！！</small>
											</div>
										</div>
										
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="createCourse()">开始构建</button>
												<div id="data" style="color: red;" ></div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="application/javascript">
			function createCourse() {
				//打点
				$.ajax({
					type:"GET",
					async:false,
					url:"http://192.168.32.18:8085/projectdot/add?projectName=CreateCourseWeb&userName=anonymous",		
				});
				var course = {
					cookie: $("#cookie").val().trim(),
					reviewCookie: $("#reviewCookie").val().trim(),
					namePrefix: $("#namePrefix").val().trim(),
					grade: $("#grade").val().trim(),
					subject: $("#subject").val().trim(),
					term: $("#term").val().trim(),
					category: $("#category").val().trim(),
					catalog_num: $("#catalog_num").val().trim(),
					materialId: $("#materialId").val().trim(),
					teacherId: $("#teacherId").val().trim(),
					counselorTeacherId: $("#counselorTeacherId").val().trim(),
					cycleStr: $("#cycleStr").val().trim(),
					courseLimit: $("#courseLimit").val().trim(),
					classLimit: $("#classLimit").val().trim(),
					existedOutlineId: $("#existedOutlineId").val().trim()				
				};
				console.log(course);
				var result=' ';
				 $.ajax({
					type: "post",
					url: "/CreateCourse/CreateCourseAPI",
					dataType: 'json',
					//必需设定，后台@RequestBody会根据它做数据反序列化
					contentType: "application/json;charset=UTF-8",
					//必需把JSON数据以字符串的格式提交
					data: JSON.stringify(course),
					error:function(res){
						alert("创建失败");
					},
					success: function(res) {
						$("#data").html(res);
						if(res.match('建课成功')!=null){
							var url = res.split('买课链接为');
							window.open(url[1]);
						}	
					}
				}); 				 
			}
			/* function createOnlineCourse(){
				window.open("https://www.xueersi.com");
			} */
			/* function createTestCourse(){
				window.open("https://www.xueersi.com");
			} */
		</script>
		<script src="assets/js/amazeui.min.js"></script>
		<script src="assets/js/amazeui.datatables.min.js"></script>
		<script src="assets/js/dataTables.responsive.min.js"></script>
		<script src="assets/js/app.js"></script>
		
		<footer>
			<div class="admin-content-footer">
				© 2018 XES_QA
				<a href="http://www.xueersi.com" target="_blank">www.xueersi.com</a> powered by 学习研发中心测试组 created by 张洁
			</div>
		</footer>
	</body>

</html>

