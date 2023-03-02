$(".ordinaryLogin").on("click", () => {
    this.test();
});

function test(){
    const id = $(".login_username").val()
    const pwd = $(".login_password").val()

    if(id.trim()==='' || id===null){
        alert("아이디를 입력해주세요.")
        return false;
    }
    if(pwd.trim()==='' || pwd===null){
        alert("비밀번호를 입력해주세요.")
        return false;
    }
}