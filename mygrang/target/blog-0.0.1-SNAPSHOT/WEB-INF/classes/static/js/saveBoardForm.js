/* att_zone : 이미지들이 들어갈 위치 id, btn : file tag id */
(imageView = function imageView(att_zone, btn) {
    var attZone = document.getElementById(att_zone);
    var btnAtt = document.getElementById(btn);
    var sel_files = [];

    // 이미지와 체크 박스를 감싸고 있는 div 속성
    var div_style = "display:inline-block;position:relative;" + "width:150px;height:120px;margin:5px;z-index:1";
    // 미리보기 이미지 속성
    var img_style = "width:100%;height:100%;z-index:none";
    // 이미지안에 표시되는 체크박스의 속성
    var chk_style =
        "width:30px;height:30px;position:absolute;font-size:24px;" +
        "right:0px;bottom:0px;z-index:999;";

    btnAtt.onchange = function (e) {
        var files = e.target.files;
        var fileArr = Array.prototype.slice.call(files);
        for (f of fileArr) {
            imageLoader(f);
        }
    };

    // 탐색기에서 드래그앤 드롭 사용
    attZone.addEventListener(
        "dragenter",
        function (e) {
            e.preventDefault();
            e.stopPropagation();
        },
        false
    );

    attZone.addEventListener(
        "dragover",
        function (e) {
            e.preventDefault();
            e.stopPropagation();
        },
        false
    );

    attZone.addEventListener(
        "drop",
        function (e) {
            var files = {};
            e.preventDefault();
            e.stopPropagation();
            var dt = e.dataTransfer;
            files = dt.files;
            for (f of files) {
                imageLoader(f);
            }
        },
        false
    );

    /*첨부된 이미리즐을 배열에 넣고 미리보기 */
    imageLoader = function (file) {
        sel_files.push(file);
        var reader = new FileReader();
        reader.onload = function (ee) {
            let img = document.createElement("img");
            img.setAttribute("style", img_style);
            img.src = ee.target.result;
            attZone.appendChild(makeDiv(img, file));
        };

        reader.readAsDataURL(file);
    };

    /*첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환 */
    makeDiv = function (img, file) {
        var div = document.createElement("div");
        div.setAttribute("style", div_style);

        var btn = document.createElement("input");
        btn.setAttribute("type", "button");
        btn.setAttribute("value", "x");
        btn.setAttribute("delFile", file.name);
        btn.setAttribute("style", chk_style);
        btn.onclick = function (ev) {
            var ele = ev.srcElement;
            var delFile = ele.getAttribute("delFile");
            for (var i = 0; i < sel_files.length; i++) {
                if (delFile == sel_files[i].name) {
                    sel_files.splice(i, 1);
                }
            }

            dt = new DataTransfer();
            for (f in sel_files) {
                var file = sel_files[f];
                dt.items.add(file);
            }
            btnAtt.files = dt.files;
            var p = ele.parentNode;
            attZone.removeChild(p);
        };
        div.appendChild(img);
        div.appendChild(btn);
        return div;
    };
})("att_zone", "btnAtt");

/*이미지 업로드*/
function upload(event){
    event.preventDefault();
    let formData = new FormData();
    let contentFiles = [];//첨부파일 배열
    let fileinput = $('.files')[0].files//p19,p20
    console.log($('.files').length)
    console.log($('#att_zone').length)
    console.log(fileinput.length)//2
    console.log(contentFiles.length)
    if(!$('.files')[0].files[0]){
        alert("사진을 등록해 주세요")
        return false;
    }
    if($('.files')[0].files.length>5){
        alert("사진은 5개까지 업로드 가능합니다.");
        return false;
    }
    if($('#title').val().trim()==='' || $('#title').val()===null){
        alert("제목을 입력해주세요")
        return false;
    }

    formData.append("title",$('#title').val());

    for(let i=0; i<fileinput.length; i++){
        formData.append("file",$('.files')[0].files[i])
    }


    formData.append("content",$('#content').val());
    for(let key of formData.keys()){
        console.log('${key}: ${formData.get(key)}');
    }
    for (let value of formData.values()) {
        console.log(value);
    }


    $.ajax({
        type:"POST",
        url:"/api/board",
        data: formData,
        contentType: false,
        processData: false,
        enctype: "multipart/form-data",
        dataType:"text"
    }).done(res=>{
        alert(res);
        location.href='/'
    }).fail(error=>{
        alert("게시글 등록에 실패하였습니다.")
    })
}

function update(){
    //event.preventDefault();
    let id=$("#id").val()
    let userid=$("#userid").val()

    let data={
        title:$("#title").val(),
        content:$("#content").val()
    };
    $.ajax({
        type:"PUT",
        url:"/api/board/"+id,
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        //dataType:"test"
    }).done(function(resp){
        alert("수정이 완료되었습니다.");
        location.href="/userPage/"+userid;
    }).fail(function(error){
        alert(JSON.stringify(error))
    })
    return false;
}

function deleteById(e){
    onload
    var id=$("#id").val();
    let userid=$("#userid").val();
    console.log(id,userid)
    $.ajax({
        type:"DELETE",
        url:"/api/board/"+id,
        /*dataType:"json"*/
    }).done(function(resp){
        alert("삭제가 완료되었습니다.");
        location.href="/userPage/"+userid;
    }).fail(function(error){
        alert(JSON.stringify(error))
    })
    return false;
}