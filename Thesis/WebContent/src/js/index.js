//三图轮播图和滚动轮播图渲染
function renderSwiper(){
	var swiper = document.querySelector(".swiper");
	var loop = swiper.querySelector(".threeLoop");
	var loopUl = loop.querySelector(".loop_ul");
	var device = document.querySelector(".device");
	var swiper_wrapper = device.querySelector(".swiper-wrapper");
	var url="../SwiperServlet";
	
	$.post(
		url, 
		function(data) {  
			//三图轮播图渲染
			var arr = JSON.parse(data);
			for(var i=0;i<arr.length;i++){
				if(arr[i].type == "threeLoop"){
					var li = `<li>
									<div class="loopName">
										<span>${arr[i].title}</span>
									</div>
									<div class="loopImg">
										<img src=${arr[i].url} />
									</div>
								</li>`;
					loopUl.innerHTML += li;
				}
				
				//滚动轮播图渲染
				if(arr[i].type == "swiper"){
					var li = `<div class="swiper-slide">
									<a href=${arr[i].href}>
										<img src=${arr[i].url} />
									</a>
								</div>`;
					swiper_wrapper.innerHTML += li;
				}
			}
			
			//首页轮番图初始化
	        var mySwiper = new Swiper('.swiper-container',{
	            pagination: '.pagination',
	            autoplay:4000,
	            loop: true,
	            grabCursor: true,
	            paginationClickable: true,
	            autoplayDisableOnInteraction: false,
	            observer :true, //修改swiper自己或子元素时，自动初始化swiper
		    	observeParents :true//修改swiper的父元素时，自动初始化swiper
	        });
	        $('.arrow-left').on('click', function(e) {
				e.preventDefault()
				mySwiper.swipePrev()
			})
			$('.arrow-right').on('click', function(e) {
				e.preventDefault()
				mySwiper.swipeNext()
			})
			
			//渲染完成后制作三图轮播效果
			threeLoop();
		}
	);
	
}
renderSwiper(); 

//轮播图中的三图轮播
function threeLoop(){
	var loop = document.querySelector(".threeLoop");
	var loopUl = loop.querySelector(".loop_ul");
	var loopLi = loopUl.querySelectorAll("li");
	var loopWidth = loop.offsetWidth;

	var len = loopLi.length;
	loopUl.style.width = 150 * len + "px";

	//一开始的效果
	var loopIndex = Math.floor(len / 2);
	loopUl.style.transform = "translateX(-" + (150 * (loopIndex - 1)) + "px)";
	loopLi[loopIndex].style.transform = "scale(1.1)";
	loopLi[loopIndex].style.zIndex = "2";
	//点击
	loopUl.addEventListener("click", function(e) {
		move(e.target);
	})

	function move(obj) {
		var child = obj.parentNode.parentNode;
		var i = 0; //获取元素li在ul的位置
		while((child = child.previousElementSibling) != null) {
			i++;
		}
		if(loopIndex == len - 1 && i == len - 3) {
			loopUl.style.transform = "translateX(-" + (150 * (i - 1)) + "px)";
			transShowImg(i);
			loopIndex = i;
		}
		if(loopIndex == 0 && i == 2) {
			loopUl.style.transform = "translateX(-" + (150 * (i - 1)) + "px)";
			transShowImg(i);
			loopIndex = i;
		}
		if(i == len - 1) {
			transShowImg(i);
			loopIndex = i;
		} else {
			if(i == loopIndex - 1) {
				loopUl.style.transform = "translateX(-" + (150 * (i - 1)) + "px)";
				transShowImg(i);
				loopIndex--;
			}
			if(i == loopIndex + 1) {
				loopUl.style.transform = "translateX(-" + (150 * (i - 1)) + "px)";
				transShowImg(i);
				loopIndex++;
			}
		}
	}

	function transShowImg(num) {
		for(var j = 0; j < len; j++) {
			loopLi[j].style.transform = "scale(1)";
			loopLi[j].style.zIndex = "0";
		}
		loopLi[num].style.transform = "scale(1.1)";
		loopLi[num].style.zIndex = "2";
	}
}