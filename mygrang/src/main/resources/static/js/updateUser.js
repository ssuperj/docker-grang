
function readURL(input) {
	if (input.files && input.files[0]) {
		console.dir(input)
		var reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById('preview').src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	} else {
		document.getElementById('preview').src = "";
	}
}



let index = {
	init: function() {
		$("#updateUser").on("click", () => {
			this.test();
		});
	},
	test : function() {
		var p1 = document.getElementById("pwd").value;
		var p2 = document.getElementById("pwdc").value;
		var email = document.getElementById("uEmail").value;
		if( p1 != p2 ) {
			alert("비밀번호가 일치하지 않습니다");
			return false;
		}
		else if(p1.trim()==='' || p1===null){
			alert("비밀번호를 입력하세요");
			return false;
		}
		else if(email.trim()==='' || email===null){
			alert("이메일을 입력하세요");
			return false;
		}
		else{
			this.update();
		};
	},
	update: function() {
		let formData = new FormData();
		console.log($('#change_img')[0].files)
		if($('#change_img')[0].files.length !== 0){
			formData.append("file",$('#change_img')[0].files[0]);
		}
		formData.append("username",$('#uId').val());
		formData.append("email",$('#uEmail').val());
		formData.append("password",$('#pwd').val());
		for (let value of formData.values()) {
			console.log(value);
		}
		$.ajax({
			type : "post",
			url : "/auth/api/userUpdate",
			data: formData,
			contentType: false,
			processData: false,
			enctype: "multipart/form-data",
			dataType:"text"
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다.")
			location.href="/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	}
}
index.init();