<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>摄像头调用</title>
<meta charset="UTF-8">
<!--  
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript" src="resources/jquery-3.2.1.min.js"></script>
-->
<!-- jQuery -->
<script src="${path }/vendors/jquery/dist/jquery.min.js"></script><style>  
#video {  
    border: 1px solid #ccc;  
    display: block;  
    margin: 0 0 20px 0;
    float:left;
}

#canvas {
    margin-top: 20px;  
    border: 1px solid #ccc;  
    display: block;
}

</style> 

</head>
<body>
<div id="contentHolder">
	<div id = "mainvideo">
    <video id="video" width="320" height="320" autoplay></video>
    </div>
    <canvas style="display:block" id="canvas" width="320" height="320"></canvas>
    <button id="picture">拍照</button>
    <button id="sc">检测</button>
    <button id="zc">注册</button>
    <button id="rz">认证</button>
</div>
<script>
	
	//这里是做了兼容性的处理，navigator.getUserMedia是标准的API，navigator.webkitGetUserMedia是WebKit核心的API，
	//FireFox中已经用navigator.mediaDevices.getUserMedia代替了navigator.mozGetUserMedia
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mediaDevices.getUserMedia || navigator.mozGetUserMedia;
    if (navigator.getUserMedia) {
        navigator.getUserMedia({ audio: false, video: { width: 320, height: 320 } },
                function(stream) {
                    var video = document.getElementById("video");
                    video.src = window.URL.createObjectURL(stream);
                    video.onloadedmetadata = function(e) {
                        console.log("onloadedmetadata获取了");
                        video.play();
                    };
                },
                function(err) {
                    console.log("出错了:" + err.name);
                }
        );
    } else {
        console.log("不支持getUserMedia");
    }
    
    //创建一个canvas元，并获取它的2DContext,
    var context = document.getElementById("canvas").getContext("2d");

    //给拍照按钮添加按钮事件   $("#picture")  document.getElementById("picture")
    document.getElementById("picture").addEventListener("click", function () {
        context.drawImage(video, 0, 0, 320, 320);
        document.getElementById("mainvideo").style.display = "none";
    });
 
    //给检测添加按钮事件
    document.getElementById("sc").addEventListener("click", function () {
        //这里得到的就是Base64的编码方式，
        var imgData=document.getElementById("canvas").toDataURL("image/png");
        //请求图片根据接口要求，去除前22位的文件头，即去除data:image/jpeg;base64,
        var data1=imgData.substr(22);

        $.post("http://localhost:8080/GasMonitor/dete",
                {
             	 "image":data1
             	 },
         	function(data){
                  	//特别注意这里是如何对json数据进行处理的，分清楚json数据格式的层次
                  	var s1,s2,s3,s4;
                  	if(data.result_num!=0) {
                  		s1 = "你是一个";
                  	}else {
						s1 = "";
                    }

                  	if(data.result[0].gender=="male"){
                      	s2 = "男人，";
                    }else if(data.result[0].gender=="female"){
						s2 = "女人，";
                    }else {
						s2 = "";
                    }
                  	
                    if(data.result[0].glasses==0){
                        s4 = ",你没有戴眼镜";
                    }else if(data.result[0].glasses==1) {
						s4 = ",你戴着近视眼镜";
                    }else if(data.result[0].glasses==2) {
                    	s4 = ",你戴着墨镜";
                    }
                    else {
                    	s4 = "";
                        }
                    
                    if(data.result[0].expression==0) {
						s3 = "面无表情的";
                    }else if(data.result[0].expression==1) {
						s3 = "面带微笑的";
                    }else if(data.result[0].expression==2) {
						s3 = "猖狂大笑的";
                    }else {
						s3 = "";
                        }
                    alert(s1+s2+s3+s4);



/*
                    {
                        "result":[
                        	{"expression":0,
                            "beauty":48.416812896729,
                            "gender":"male","gender_probability":0.99925523996353,
                            "roll":-1.3796654939651,
                            "glasses_probability":0.99953198432922,
                            "face_probability":0.71843481063843,"yaw":-5.5738277435303,
                         	"glasses":1,"expression_probablity":0.99941623210907,
                         	"location":{"top":189,"left":100,"width":165,"height":154},
                         	"rotation_angle":0,"pitch":6.1355400085449,"age":27.296012878418}
                        ],
                        "log_id":3239516879,
                        "result_num":1
                    }
         */           
                    
                    
                    
             
             },
             "json");

        /* $.post("https://aip.baidubce.com/rest/2.0/face/v1/detect",
        {"Content-Type":"application/x-www-form-urlencoded",
     	 "access_token":"24.cc9943707ab381e684bbee99a21f8ff5.2592000.1500895855.282335-9798514",
     	 "image":data
     	 },
 	function(data){
     alert("人脸数："+data.result_num);
     },
     "jsonp");   */
        
       
    });


    //进行用户人脸的注册
    document.getElementById("zc").addEventListener("click", function () {
        //这里得到的就是Base64的编码方式，
        var imgData=document.getElementById("canvas").toDataURL("image/png");
        //请求图片根据接口要求，去除前22位的文件头，即去除data:image/jpeg;base64,
        var data1=imgData.substr(22);

        $.post("http://localhost:8080/GasMonitor/addface",
                {
             	 "image":data1
             	 },
         	function(data){
                  //进行判断，json中是否存在error_msg这个key
				if(data.hasOwnProperty("error_msg")) {
					alert("注册失败");
				} else {
					alert("注册成功");
				}
				            
             },
             "json");

    });


    //进行用户人脸的认证
    document.getElementById("rz").addEventListener("click", function () {
        //这里得到的就是Base64的编码方式，
        var imgData=document.getElementById("canvas").toDataURL("image/png");
        //请求图片根据接口要求，去除前22位的文件头，即去除data:image/jpeg;base64,
        var data1=imgData.substr(22);

        $.post("http://localhost:8080/GasMonitor/verifyUser",
                {
             	 "image":data1
             	 },
         	function(data){
                 //进行判断，
				if(data.result[0]>80) {
					alert("认证成功");
				} else {
					alert("认证失败");
				}            
             },
             "json");

    });

</script>
</body>
</html>