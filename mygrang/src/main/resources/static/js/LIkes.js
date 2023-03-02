function toggleLike(boardId) {
	let likeIcon = $(`#LikeIcon-${boardId}`);
	
	if (likeIcon.hasClass("far")) { // 좋아요
		
		$.ajax({
			type: "post",
			url: `/api/board/${boardId}/likes`,
			dataType: "text"
		}).done(res=>{
			console.log(res);
			let likeCountStr = $(`#LikeCount-${boardId}`).text();
			let likeCount = Number(likeCountStr) + 1;
			$(`#LikeCount-${imageId}`).text(likeCount);
			
			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(error=>{
			console.log("오류", error);
		});
		
		

	} else { // 좋아요취소
		
		$.ajax({
			type: "delete",
			url: `/api/board/${boardId}/likes`,
			dataType: "text"
		}).done(res=>{
			console.log(res);
			let likeCountStr = $(`#LikeCount-${boardId}`).text();
			let likeCount = Number(likeCountStr) - 1;
			$(`#LikeCount-${imageId}`).text(likeCount);
			
			likeIcon.removeClass("fas");
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
		}).fail(error=>{
			console.log("오류", error);
		});
		

	}
}