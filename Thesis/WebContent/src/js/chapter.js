function allNovel(){
	var url="../NovelServlet";
	
	$.post(
		url,
		function(data) {  
			var arr = JSON.parse(data);
			console.log(arr);
			var search = window.location.search.split("?")[1];
			var novelId = search.split("=")[1];
			for(var i=0;i<arr.length;i++){
				if(arr[i].id == novelId){
					showArtical(arr[i]);
					break;
				}
			}
		}
    ); 
}
allNovel();

function showArtical(obj){
	var chapter = document.querySelector(".chapter");
	var str = `<div class="chapter_left">
					<div class="chapter_left_img">
						<img src=${obj.src} />
					</div>
				</div>
				<div class="chapter_right">
					<div class="title">
						<span>${obj.name}</span>
						<span>作者：${obj.author}</span>
						<span>类别：${obj.type}</span>
					</div>
					<div class="intro">
						<p>简介：${obj.intro}</p>
					</div>
					<div class="button">
						<span>开始阅读</span>
						<span>收藏</span>
					</div>
					<div class="new_chapter">
						<div class="new_title">
							<span class="logo">新</span>
							<p>第10章 宁死不从</p>
						</div>
						<div class="new_content">
							<p>一世丹尊、苏凌，偶获上古至宝天魔珠，却惨遭奸人围攻陨落。五百年后，苏凌重生在一个苏家的废物少爷身上，从此踏上一条逆天之路。修炼丹武之道，何人敢称至尊，唯我苏凌！</p>
						</div>
					</div>
				</div>`;
	chapter.innerHTML = str;
}