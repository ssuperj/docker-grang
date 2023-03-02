const content = document.querySelectorAll(".add_reply");
const btn = document.querySelectorAll(".r_btn-save");


const c_content = document.querySelectorAll('.c_content');
const r_c_content = document.querySelectorAll('.r_c_content');
const more_btn = document.querySelectorAll('.r_c_more');
const cancle_btn = document.querySelectorAll('.r_c_more_cancle');

const edit = document.querySelectorAll(".reply_edit");//수정버튼
const cancle_editbtn = document.querySelectorAll(".reply_cancle");
const editBtn = document.querySelectorAll(".reply_edit_button");//게시버튼
const edit_zone = document.querySelectorAll(".edit_zone");
const deleteBtn = document.querySelectorAll(".reply_delete");

btn.forEach((target)=>{
	target.addEventListener('click',(e)=>{
		e.preventDefault();
		this.save(e);
	});
});
//더보기 부분
for(let i=0; i<c_content.length; i++){
	if(c_content[i].value.length<=35){
		more_btn[i].style.display = "none";
	}
}

more_btn.forEach((target)=>{
	target.addEventListener('click',(e)=>{
		e.preventDefault();
		c = (e.target.parentElement.children[0]);
		c.style.height = "100px";
		c.style.webkitLineClamp= 12;
		c.style.overflow="auto";
		x = (e.target.parentElement.children[2]);
		x.style.display="block";
		m = (e.target.parentElement.children[1]);
		m.style.display="none";
	})
})

cancle_btn.forEach((target)=>{
	target.addEventListener('click',(e)=>{
		e.preventDefault();
		c = (e.target.parentElement.children[0]);
		c.style.overflow="hidden";
		c.style.height = "50px";
		c.style.webkitLineClamp= 2;

		x = (e.target.parentElement.children[2]);
		x.style.display="none";
		m = (e.target.parentElement.children[1]);
		m.style.display="block";
	})
})

edit.forEach((target)=>{
	target.addEventListener('click',(e)=>{
		e.preventDefault();
		editzone = (e.target.parentElement.parentElement.children[1].children[0].children[3]);
		cancle = (e.target.parentElement.parentElement.children[2].children[2]);
		dn = (e.target.parentElement.parentElement.children[2].children[1]);
		editzone.style.display="block";
		cancle.style.display="block";
		dn.style.display="none";

	});
});

cancle_editbtn.forEach((target)=>{
	target.addEventListener('click',(e)=>{
		e.preventDefault();
		editzone = (e.target.parentElement.parentElement.children[1].children[0].children[3]);
		cancle = (e.target.parentElement.parentElement.children[2].children[2]);
		dn = (e.target.parentElement.parentElement.children[2].children[1]);
		editzone.style.display="none";
		cancle.style.display="none";
		dn.style.display="block";

	})
})

editBtn.forEach((target)=>{
	target.addEventListener('click',(e)=>{
		e.preventDefault();
		this.editReply(e);
	});
});

deleteBtn.forEach((target)=>{
	target.addEventListener('click',(e)=>{
		e.preventDefault();
		this.deleteById(e);
	});
});




function save(event){
	const content = event.target.parentElement.children[2].value
	const boardId= event.target.parentElement.children[0].value
	const userId= event.target.parentElement.children[1].value
	if(content.trim() === '' || content===null){
		alert("내용을 입력해주세요")
		return false;
	}
	let data = {
		content,
		boardId,
		userId
	}

	$.ajax({
		type: "POST",
		url: "/api/reply",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp){
		alert("댓글 작성이 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert("실패");
	});
}

function editReply(event) {
	const count = (event.target.parentElement.children[1]).value;//input
	const id =(event.target.parentElement.children[0]).value;
	if(count.trim()==='' || count===null){
		alert("내용을 입력해주세요")
		return false;
	}
	let data = {
		content : count
	}
	/*alert(data.content)*/
	$.ajax({
		type: "PUT",
		url: "/api/reply/"+id,
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(resp){
		alert("댓글 수정이 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
}

function deleteById(event) {
	const parent = event.target.parentElement.parentElement;
	const reid =event.target.parentElement.children[0].value;

	$.ajax({
		type: "DELETE",
		url: "/api/reply/"+reid,
		dataType: "json"
	}).done(function(resp){
		alert("댓글 삭제가 완료되었습니다.");
		parent.remove();
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	});
}