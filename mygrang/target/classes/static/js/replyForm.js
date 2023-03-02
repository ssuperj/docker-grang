let heart = document.querySelectorAll(".fa-heart");
heart.forEach((target) => target.addEventListener("click", clickHeart));
function clickHeart(event) {
	  let targetClass = event.target.classList;
	  let cnt = event.target.parentElement.children[1].value;
	  let bId = event.target.parentElement.children[2].value;
	  let uName = event.target.parentElement.children[3].value;
	  
	  
	  targetClass.toggle("fa-heart--red");
	  if (targetClass.contains("fa-heart--red")) {
	    targetClass.remove("fa-regular");
	    targetClass.add("fa-solid");
	    $.ajax({
				type: "POST",
				url: "/api/board/likes/"+bId,
				dataType: "text"
			}).done(res=>{
				console.log(res);
				event.target.parentElement.children[1].value++;			
			}).fail(error=>{
				console.log("보내기 오류", error);
			});
	  } else {
	    targetClass.remove("fa-solid");
	    targetClass.add("fa-regular");

	    $.ajax({
				type: "delete",
				url: "/api/board/likes/"+bId,
				dataType: "text"
			}).done(res=>{
				console.log(res);
				event.target.parentElement.children[1].value--;
			}).fail(error=>{
				console.log("삭제 오류", error);
			});
	  }
	  console.log(event.target.parentElement.children[1].value)
}

var swiper = new Swiper(".wrap", {
  pagination: {
    el: ".swiper-pagination",
    type: "fraction",
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  slidesPerView: "auto",
  spaceBetween: 100,
  loop: true,
  centeredSlides: true,
  speed: 1000,
  // direction: "horizontal",
  //   세로 vertical 기본값은 가로 horizontal
  effect: "coverflow",
  coverflowEffect: {
    rotate: 5, //회전값
    stretch: -100, //+면 당김 -면 벌림
    depth: 400, //원근감 깊이
    modifier: 1, //부여한 효과 배수로 늘림 1배 2배...
    slideShadows: false, //그림자
  },
});
let boardId = document.querySelectorAll(".boardId");

for(item of boardId){
	loadLikes(item.value, item);
}

function loadLikes(id, target) {
	fetch(`/auth/index/likes/${id}`)
	.then(resp=>resp.json())
	.then(resp=>{
		target.parentElement.children[1].value = resp[0];
		if(resp[1]===1){
			let targetClass = target.parentElement.children[0].classList;
			console.log(targetClass)
			targetClass.toggle("fa-heart--red");
	   		targetClass.remove("fa-regular");
	    	targetClass.add("fa-solid");
		}
	})
	.catch(err=>console.log(err))
}