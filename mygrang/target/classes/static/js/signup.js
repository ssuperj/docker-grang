let index = {
	init: function() {
		$(".ordinarysignup").on("click", () => {
			this.test();
		});
	},
	test : function() {
		var p1 = document.getElementById("password1").value;
		var p2 = document.getElementById("password2").value;
		var username = document.querySelector(".username").value;
		var email = document.querySelector(".email").value;
		if( p1 != p2 ) {
			alert("비밀번호가 일치하지 않습니다");
			console.log(username);
			console.log(email);
			return false;
		}
		else if(p1===null || p1.trim()===''){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		else if(username===null || username.trim()===''){
			alert("아이디를 입력해주세요");
			return false;
		}
		else if(email===null || email.trim()===''){
			alert("이메일을 입력해주세요");
			return false;
		}
		else{
			this.save();
		};
	},
	save: function() {
		let data = {
			username : $(".username").val(),
			email : $(".email").val(),
			password : $("#password1").val()
		};
		$.ajax({
			type : "POST",
			url : "/auth/api/user",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			datatype : "json"
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.")
			location.replace("/")
		}).fail(function(error) {
			alert("존재하는 아이디입니다.");
		})
	}
}
index.init();