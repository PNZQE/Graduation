//头部、导航栏、尾部的js

//header的logo大小改变
window.onscroll=function(){
	//logo大小改变
	if(document.documentElement&&document.documentElement.scrollTop){
        scrollTop=document.documentElement.scrollTop;
    }else if(document.body){
        scrollTop=document.body.scrollTop;
    }
    if(scrollTop>50){
    	document.getElementsByClassName('header_img')[0].style.width='80px';
    }else{
    	document.getElementsByClassName('header_img')[0].style.width='130px';
    }
    
    //回到顶部按钮显示与隐藏
    var btn = document.querySelector(".top");
	var curScroll = window.pageYOffset;
	if(curScroll>=100){
		btn.style.display = "block";
	}
	else{
		btn.style.display = "none";
	}
}	

//回到顶部
function func(){
	var btn = document.querySelector(".top");
	btn.addEventListener("click",function(){
		toTop();
	})
}
function toTop(){
	var curScroll = window.pageYOffset;
	window.scrollTo(0,curScroll-50);
	var timer = window.requestAnimationFrame(toTop);
	if(curScroll<=0){
		cancelAnimationFrame(timer); //清除帧动画
	}
}
func();

//登录注册页面及功能
var web = document.querySelector(".web"); //首页
var webRegist = document.querySelector(".webRegist"); //首页登录按钮
var regist = document.querySelector(".regist"); //登录注册页面
var loginForm = document.querySelector(".login_form"); //登录表单
var registForm = document.querySelector(".regist_form"); //注册表单
var registBtn = document.querySelector(".regist_btn"); //登录页面的注册按钮
var logintClose = loginForm.querySelector(".close"); //关闭按钮
var registClose = registForm.querySelector(".close"); //关闭按钮
//首页、登录页、注册页之间的显示和隐藏
webRegist.addEventListener("click",function(){
	regist.style.display = "block";
	document.body.style.overflow="hidden";
	document.body.style.height="100%";
})
registBtn.addEventListener("click",function(){
	loginForm.style.display = "none";
	registForm.style.display = "block";
})
logintClose.addEventListener("click",function(){
	regist.style.display = "none";
	document.body.style.overflow="visible";
	document.body.style.height="auto";
})
registClose.addEventListener("click",function(){
	regist.style.display = "none";
	document.body.style.overflow="visible";
	document.body.style.height="auto";
})