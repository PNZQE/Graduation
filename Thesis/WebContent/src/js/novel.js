//获取所有的novel
function allNovel(){
	var url="../NovelServlet";
	
	$.post(
		url,
		function(data) {  
			var arr = JSON.parse(data);
			var hotArr = [];
			var newArr = [];
			for(var i=0;i<arr.length;i++){
				if(arr[i].sign){
					var signArr = arr[i].sign.split(" ");
					if(signArr.indexOf("hot") != -1){
						hotArr.push(arr[i]);
					}
					if(signArr.indexOf("new") != -1){
						newArr.push(arr[i]);
					}
				}
			}
			showHot(hotArr);
			showNew(newArr);
			typeClassify(arr);
		}
    ); 
}
allNovel();

//首页热门推荐小说展示
function showHot(arr){
	var hotBlock = document.querySelector(".hotBlock");
	for(var i=0;i<5;i++){
		var href = "chapter.html?novelId=" + arr[i].id;
		var str = `<div class="hotBook">
		    			<a href=${href} target="_blank">
		    				<div class="hotBookImg">
				    			<img src=${arr[i].src} />
				    		</div>
				    		<div class="hotBookIntro">
				    			${arr[i].name}
				    		</div>
		    			</a>
			    	</div>`;
		hotBlock.innerHTML += str;
	}
}

//首页新书推荐小说展示
function showNew(arr){
	var newBlock = document.querySelector(".newBlock");
	for(var i=1;i<5;i++){
		var href = "chapter.html?novelId=" + arr[i].id;
		var str = `<div class="newBook">
		    			<a href=${href} target="_blank">
		    				<div class="newBookImg">
				    			<img src=${arr[i].src} />
				    		</div>
				    		<div class="newBookIntro">
				    			<p>书名：${arr[i].name}</p>
				    			<p>作者：${arr[i].author}</p>
				    			<p class="intro">简介：${arr[i].intro}</p>
				    		</div>
		    			</a>
		    		</div>`;
		newBlock.innerHTML += str;
	}
	var newBook = newBlock.querySelectorAll(".newBook");
	
	//控制简介内容超出100个字显示省略号
	for(var j=0;j<newBook.length;j++){
		var newBook_p = newBook[j].querySelector(".intro");
		if(newBook_p.innerText.length > 100){
			var str = newBook_p.innerText.substr(0,100)+"……";
			newBook_p.innerText = str;
		}
	}
}

//分类推荐
function typeClassify(arr){
	var wuxia = [], xuanhuan = [], modern = [], ancient = [];
	var classify_block = document.querySelector(".classify_block");
	var blocks = classify_block.querySelectorAll(".block");
	
	for(var i=0;i<arr.length;i++){
		console.log(arr[i].type);
		if(arr[i].type == "武侠仙侠"){
			var classify_block_list = blocks[0].querySelector(".classify_block_list");
			if(wuxia.length < 3){
				wuxia.push(arr[i]);
			}
			if(wuxia.length == 3){
				var str = showType(wuxia);
				classify_block_list.innerHTML = str;
			}
		}
		else if(arr[i].type == "玄幻"){
			var classify_block_list = blocks[1].querySelector(".classify_block_list");
			if(xuanhuan.length < 3){
				xuanhuan.push(arr[i]);
			}
			if(xuanhuan.length == 3){
				var str = showType(xuanhuan);
				classify_block_list.innerHTML = str;
			}
		}
		else if(arr[i].type == "现代言情"){
			var classify_block_list = blocks[2].querySelector(".classify_block_list");
			if(modern.length < 3){
				modern.push(arr[i]);
			}
			if(modern.length == 3){
				var str = showType(modern);
				classify_block_list.innerHTML = str;
			}
		}
		else if(arr[i].type == "古代言情"){
			var classify_block_list = blocks[3].querySelector(".classify_block_list");
			if(ancient.length < 3){
				ancient.push(arr[i]);
			}
			if(ancient.length == 3){
				var str = showType(ancient);
				classify_block_list.innerHTML = str;
			}
		}
	}
	
	//控制简介内容超出50个字显示省略号
	var classify_block_book_intro = classify_block.querySelectorAll(".classify_block_book_intro");
	for(var j=0;j<classify_block_book_intro.length;j++){
		var classify_p = classify_block_book_intro[j].querySelector("p");
		if(classify_p.innerText.length > 50){
			var str = classify_p.innerText.substr(0,50)+"……";
			classify_p.innerText = str;
		}
	}
}

//获取分类推荐小说结构
function showType(arr){
	var href = "chapter.html?novelId=";
	var str = `<ul>
					<li><a href=${href+arr[0].id} target="_blank">
						<div class="classify_block_book_list">
							<p>${arr[0].name}</p>
							<p>${arr[0].author}</p>
						</div>
						<div class="classify_block_book_intro">
							<div class="classify_block_book_intro_img">
								<img src=${arr[0].src} />
							</div>
							<p>${arr[0].intro}</p>
						</div>
					</a></li>
					<li><a href="${href+arr[1].id}" target="_blank">
						<div class="classify_block_book_list">
							<p>${arr[1].name}</p>
							<p>${arr[1].author}</p>
						</div>
					</a></li>
					<li><a href="${href+arr[2].id}" target="_blank">
						<div class="classify_block_book_list">
							<p>${arr[2].name}</p>
							<p>${arr[2].author}</p>
						</div>
					</a></li>
				</ul>`;
	return str;
}