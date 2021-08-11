<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含base标签，css样式，jQuery文件--%>
	<%@include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function(){
			//绑定submit按钮
			$("#sub_btn").click(function(){
				//验证用户名
				var usernameText = $("#username").val();
				var prop = /^\w{5,12}$/;
				if(!prop.test(usernameText)){
					$(".errorMsg").text("用户名格式错误");
					return false;
				}
				//验证密码
				var passwordText = $("#password").val();
				var prop = /^\w{5,12}$/;
				if(!prop.test(passwordText)){
					$(".errorMsg").text("密码格式错误");
					return false;
				}
				//确认密码
				var repwdText = $("#repwd").val();
				if(repwdText!=passwordText){
					$(".errorMsg").text("密码不一致");
					return false;
				}
				//电子邮件
				var emailText = $("#email").val();
				var prop = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(!prop.test(emailText)){
					$(".errorMsg").text("邮箱格式错误");
					return false;
				}
				//验证码
				var codeText = $("#code").val();
				//codeText = $.trim(codeText);
				//alert(codeText);
				if(codeText==null||codeText==""){
					$(".errorMsg").text("验证码错误");
					return false;
				}
			});
			//绑定验证码切换的点击事件
			$("#code_img").click(function(){
				this.src = "${baseUrl}kaptcha.jpg?d="+new Date();
			});
			//检测用户名是否可用
			$("#username").blur(function(){
				var value = this.value;
				$.getJSON("${pageScope.baseUrl}userServlet","action=existUserName&name="+value,function(data){
					if(data.isExist){
						//若存在该用户
						$(".errorMsg").text("该用户存在！");
					}else{
						$(".errorMsg").text("用户名可用");
					}
				});
			});
		})
	</script>
	<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${requestScope.get("msg")}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="name" id="username"
										   value="${requestScope.get("username")}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.get("email")}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>
									<img alt="" src="kaptcha.jpg" id="code_img" style="width: 100px; height: 32px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>