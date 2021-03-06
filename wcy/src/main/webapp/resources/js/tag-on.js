
	
	
	//input값
	var oldVal = null;
	var currentVal = null;
	
	//검색된 태그 결과 값
	var nameListOn = Array();
	var noListOn = Array();
	
	//태그 테이블에 있는 지 없는 지 (0 false, 1 true)
	var sw = 0;
	
	//등록된 태그 담는 
	var sendTagListOn = Array();
	
	$("#text-on").on("propertychange change keyup paste input", function() {
		var cv = $(this).val();
		console.log(cv);
		
		//#로 시작하는지 검사
		if(!cv.startsWith( '#' )){
			cv = "#" + $(this).val();
		}
		
		//좌우공백제거
		currentVal = cv.replace(/ /gi, "");
		
		if (currentVal == oldVal) {
			return;
		}
		
		// input이 null값이면
		if(!currentVal){
			$("#resultListOn").empty();
			return; //break;
		}
		
		oldVal = currentVal;
		
		//start Ajax 검색
		$.ajax({
					type : 'POST',
					url : "/tag/checkTagDetail",
					headers : {"Content-Type" : "application/json"},
					data : oldVal,
					error : function(err) {
						console.log("ajax checkTagDetail 실행중 오류가 발생하였습니다.");
					},
					success : function(data) {
						
						//초기화
						$("#resultListOn").empty();
						sw = 0;
						
						//일치하는 결과가 없다면
						if(data.noListOn[0] === "no result"){
							console.log(data.noListOn[0]);						}
						else{
							//있다면
							sw = 1;
						}
							
							//배열에 넣기 (삭제 시 undifined로 출력됨)
							nameListOn = data.nameList;
							noListOn = data.noList;
							
							var checkWord = $("#text-on").val(); //검색어 입력값
							
							if (checkWord.length > 0 && data.nameList.length > 0) {
								for (i = 0; i < data.nameList.length; i++) {
									$("nameListOn").add(data.nameList[i]);
									$("noListOn").add(data.noList[i]);
									
									$("#resultListOn").append("<li 'class='resultListOn' value='"
	                                        + data.nameList[i]
	                                        + "' data-input='"
	                                        + data.nameList[i]
	                                        + "'>"
											+ "<a href='javascript:void(0);' value='"
											+ data.nameList[i]
											+ "'"
											+ "onclick='appendBtnOn("
											//+ i
											+ ")' id='"
											+ data.nameList[i]
											+ "'>"
											+ data.nameList[i]
											+ "</a>"
											+ "</li>");
								}

							}
						
						

					}
					
				});//end Ajax
				
	});//end #text-keyup-function
	
	//input text 창에 enter
	$("#text").keyup(function(e){if(e.keyCode == 13)  appendBtnOn();});
	
	//엔터키 입력 이벤트
/*	$("#text").keydown(function (key) {
		 
        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
        	appendBtnOn();
        }
 
    });
	*/
	
	
	
	
	function appendBtnOn(){
		if(currentFocus === undefined){
			currentFocus = 0;
		}
		console.log("클릭 : " + nameListOn[currentFocus]);
		if(sw == 0){ // no result
			insertTagOn();
			console.log('insert fucntion!');
			
		}else{
			if(currentFocus === undefined){
				currentFocus = 0;
			}
			clickLinkOn(currentFocus);
			console.log('clickLinkOn fucntion!');
		}
	}
	
	

	function clickLinkOn(obj) {
		var name = nameListOn[obj];
		var no = noListOn[obj];
		
		//중복검사
		if(document.getElementById(no)){
			return;
		}
		generateBtn(name, no);
		console.log("방금 들어간 인덱스: " + obj);
	}
	
	// 새로운 태그 insert
	function insertTagOn(){
		
		var tag = currentVal;
		
		//insert into tag table
		$.ajax({
			type : 'POST',
			url : "/tag/insertTagOn",
			headers : {"Content-Type" : "application/json"},
			data : tag,
			error : function(err) {
				console.log("ajax insertTagOn 실행중 오류가 발생하였습니다.");
			},
			success : function(data) {
				if(data === "fail") {
					console.log("태그 등록 실패!");
				} else{
					console.log("태그 등록 성공! " + data + "번 태그");
					
				    //태그 버튼 생성
				    generateBtn(tag, data);
				    return;
				}
			}
			
		});//end Ajax
		
	}
	
	
	//버튼 객체 동적 생성
	function generateBtn(name, no){
		
		var btn = document.createElement( 'button' );
		var text = name + " x"; //x -> &times; 로 넣을 방법 생각!
      	var btnText = document.createTextNode( text );
      	btn.appendChild( btnText );
      	btn.setAttribute("class", "btnTag");
      	btn.id = no; //id = tagId 값 (현재 문자열)
      	btn.value = name; //value = tagName
      	$("#selectedTagListOn").append(btn); 
      	
      
      	//리스트에 추가 <- 넘버[i]
      	sendTagListOn.push(no);
      	
      	
      	for (var i = 0; i < sendTagListOn.length; i++) {
			console.log("등록된 태그 버튼들: " + sendTagListOn[i] + " ");
		}
      	
      	//태그 버튼 누르면 삭제
      	btn.addEventListener ("click", function() {
      		//리스트에서 삭제 <- 넘버[i]
      		sendTagListOn.splice(sendTagListOn.indexOf(no),1);
      		
      		for (var i = 0; i < sendTagListOn.length; i++) {
				console.log("삭제 후 " + sendTagListOn[i] + " ");
			}
      		
      		//버튼 삭제
      		document.getElementById(no).remove();
      		
      		console.log("삭제완료!");
      		
      	});
      	
      	//추천 리스트 비우기
    	$("#resultListOn").empty();
      	//input창 비우기
    	document.getElementById('text-on').value = ''
    	//커서 focus
    	document.getElementById('text-on').focus();
    	console.log("더 이상 태그 추가 금지~~~ return");
    	return;
	}
	
	
	//currentFocus = 현재 인덱스
	var currentFocus;
	
	$(document).on("keyup", function(e){
		//37 left 39 right 38 up 40 down
		if(e.keyCode === 37 || e.keyCode === 39 || e.keyCode === 38 || e.keyCode === 40) {
			e.preventDefault(); //cancle event
			var children = $("#resultListOn").children();
			if(currentFocus === undefined) {
				currentFocus = 0;
			} else {
				currentFocus += e.keyCode === 37 ? -1 : 1;
				currentFocus < 0 && (currentFocus = children.length - 1);
				currentFocus >= children.length && (currentFocus = 0);
			}
			children.removeClass("--focus");
			children.eq(currentFocus).addClass("--focus");
			
			
			
			//문제점: currentFocus가 정의되지 않았을 때..? event작동 안함...
			console.log("currentFocus: " + currentFocus);
			//$("#text").keyup(function(e){if(e.keyCode == 13)  appendBtnOn();});
		}
	}) 
	
	//enter keyCode => append btn
	
	
	//input enter 시 이벤트
	//$("#text").keyup(function(e){if(e.keyCode == 13)  함수호출 });
	
	//insert into ptag
	function insertPtag() {
		 
		//insert into ptag table  sendTagList(tagNo list) 보내기
		$.ajax({
			type : 'POST',
			url : "/ptag/insertPtag",
			headers : {"Content-Type" : "application/json"},
			data : JSON.stringify(sendTagListOn),
			error : function(err) {
				console.log("ajax insertPtag 실행중 오류가 발생하였습니다.");
			},
			success : function(data) {
				if(data === "success") {
					console.log("ptag 테이블에 등록 성공! ");
					//페이지 이동
					//self.location="/wecodeyou/WEB-INF/views/interest/interest-result.jsp";
				} else if(data === "fail") {
					alert("상품에 태그를 등록하지 못 했어요!");
				}
				
				
			}
			
		});//end Ajax
		
	}
	
	//tag btn 클릭 시 검색
	$('.search_tag').click(function() {
		var id_check = $(this).attr("id");
		//alert("clicked: " + id_check);
		location.href = '/tag/searchProductByTag/'+ id_check;
	});
	
	
