<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>登录</title>

    <!-- Bootstrap -->
    <link href="${path }/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${path }/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${path }/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="${path }/vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${path }/build/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
              <h1>智能燃气管网监控系统</h1>
              
            <form action="login" method="post">
              <div>
                <input name="username" type="text" class="form-control" placeholder="用户名" required="true" />
              </div>
              <div>
                <input name="password" type="password" class="form-control" placeholder="密码" required="true" />
              </div>
              
              <div><span><font color="red">${error }</font></span></div>
              
              <div>
                <button type="submit" class="btn btn-default submit">登录</button>
              </div>
            </form>
              
              <div class="clearfix"></div>

              <div class="separator">

                <div class="clearfix"></div>
                <br/>

                <div>
                  <h1><i class="fa fa-paw"></i>河海大学</h1>
                  <p>©2017 河海大学版权所有</p>
                </div>
              </div>
          </section>
        </div>

      </div>
    </div>
  </body>
</html>