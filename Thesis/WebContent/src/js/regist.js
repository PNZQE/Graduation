/**
 * 提交用户注册信息给后台，连接数据库
 */

$('#registSend').click(function(){  
    var name=document.getElementById('regist_name').value;  
    var psd1=document.getElementById('regist_password1').value;  
    var psd2=document.getElementById('regist_password2').value; 
    var phone=document.getElementById('regist_phone').value;  
    var code=document.getElementById('regist_code').value; 
    var registForm = document.querySelector("#registForm").elements;
    var msg = "";
    
    //判断表格信息是否填写正确和完整
    var isPost = registCheck(registForm);
    
    if(isPost){
    	var user={"name":name,"psd":psd1,"phone":phone};  
        var url="../AddUserServlet";
          
        $.post(
    		url, 
    		{"data":JSON.stringify(user)},
    		function(response) {  
    			if(response == "false"){
    				alert("该手机号已被注册");
    				document.getElementById('regist_phone').focus();
    			}else{
    				var webRegist = document.querySelector(".webRegist");
        			var regist = document.querySelector(".regist");
        			//将登录注册表单隐藏
        			regist.style.display = "none";
        			//页面上显示用户名字
        			webRegist.innerHTML = user.name;
    			}
    		}
        ); 
    }
});  

/**
 * 提交用户登录信息给后台，连接数据库
 */
$("#loginSend").click(function(){ 
    var phone=document.getElementById('login_phone').value; 
    var psd=document.getElementById('login_password').value; 
    var loginForm = document.querySelector("#loginForm").elements;
    
    //判断表格信息是否填写正确和完整
    var isPost = loginCheck(loginForm);
    if(isPost){
    	var user={"phone":phone,"psd":psd};  
        var url="../LoginServlet";
          
        $.post(
    		url, 
    		{"data":JSON.stringify(user)},
    		function(data) {  
    			var json = JSON.parse(data);
    			console.log(json);
    			if(json.psd != psd){
    				alert("密码不正确");
    				document.getElementById('login_password').focus();
    			}else{
    				var webRegist = document.querySelector(".webRegist");
        			var regist = document.querySelector(".regist");
        			//将登录注册表单隐藏
        			regist.style.display = "none";
        			//页面上显示用户名字
        			webRegist.innerHTML = json.name;
    			}
    		}
        ); 
    }
});

//检查注册表格填写信息是否正确和完整
function registCheck(form){
	var isTrue = true;
	//检查输入信息是否有空
	for (var i=0;i<form.length;i++) {
		if(form[i].value == ""){
			switch(form[i].name){
				case "user_name":
					msg = "用户名";
					break;
				case "user_password1":
					msg = "密码";
					break;
				case "user_password2":
					msg = "确认密码";
					break;
				case "user_phone":
					msg = "手机号";
					break;
				case "user_code":
					msg = "验证码";
					break;
			}
        	alert(msg + "不能为空");
			isTrue = false;
        	form[i].focus();
        	break;
       }  
	}
	
    //检查用户两次输入密码是否一致
    var psd1 = form[1].value;
    var psd2 = form[2].value;
	if(psd1 && psd2 && psd1!=psd2){
		alert("两次输入密码不一致");
		isTrue = false;
		form[2].focus();
	}
	
	//检查用户输入的手机号格式是否正确
	var reg1 = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
	var phone = form[3].value;
	if(phone && !reg1.test(phone)){
		alert("输入的手机号码格式不正确");
		isTrue = false;
		form[3].focus();
	}
	
	//检查用户输入的验证码格式是否正确，验证码为4位
	var reg2 = /^([0-9]|[a-z]|[A-Z]){4}$/;
	var code = form[4].value;
	if(code && !reg2.test(code)){
		alert("输入的验证码格式不正确");
		isTrue = false;
		form[4].focus();
	}
	
	return isTrue;
}

//检查登录表格填写信息是否正确和完整
function loginCheck(form){
	var isTrue = true;
	//检查输入信息是否有空
	for (var i=0;i<form.length;i++) {
		if(form[i].value == ""){
			switch(form[i].name){
			case "user_phone":
				msg = "手机号";
				break;
				case "user_password":
					msg = "密码";
					break;
			}
        	alert(msg + "不能为空");
			isTrue = false;
        	form[i].focus();
        	break;
       }  
	}
	
	//检查用户输入的手机号格式是否正确
	var reg1 = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
	var phone = form[0].value;
	if(phone && !reg1.test(phone)){
		alert("输入的手机号码格式不正确");
		isTrue = false;
		form[0].focus();
	}
	
	return isTrue;
}