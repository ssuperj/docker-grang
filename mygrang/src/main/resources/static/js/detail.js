const sendUserId = document.querySelector("#sendUser").value;
const recvUserId = document.querySelector("#recvUser").value;
const currentHostname = window.location.hostname;
//유저 정보 가져오기
async function initPage() {
    let sendUser = (await findUser())[0];
    let recvUser = (await findUser())[1];
    const eventSource = new EventSource(`http://localhost:8100/sender/${sendUser.username}/receiver/${recvUser.username}`);
    eventSource.onmessage = (event) => {
        const data = JSON.parse(event.data);
        if(data.sender === sendUser.username) {
            initMessage(data, "my");
        } else {
            initMessage(data, "your");
        }
    }
}

function findUser() {
     return  fetch(`/chat/findUser`, {
        method: "post",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            sendUserId: sendUserId,
            recvUserId: recvUserId
        })
    }).then(resp =>  resp.json())
        .catch(err => console.log(err));
}

function initMessage(data, role) {
    let now = new Date(data.createdAt);

    let msgBox = document.querySelector(".msg-box");
    let sendBox = document.createElement("div");
    sendBox.className = `msg-box__content-${role} msg-box__content`;

    let spanBox = document.createElement("div");
    spanBox.className = "msg-box__my-clock";
    let spanDate = document.createElement("span");
    spanDate.innerText = `${String(now.getFullYear()).substring(2,4)} / ${String(now.getMonth()).padStart(2,"0")} / ${String(now.getDay()).padStart(2,"0")}`;
    let spanTime = document.createElement("span");

    if(now.getHours() > 12) {
        spanTime.innerText = `오후 ${String(now.getHours() - 12).padStart(2,"0")} : ${String(now.getMinutes()).padStart(2,"0")}`;
    } else {
        spanTime.innerText = `오전 ${String(now.getHours()).padStart(2,"0")} : ${String(now.getMinutes()).padStart(2,"0")}`;
    }

    let msgText = document.createElement("span");
    msgText.className = "msg-box__msg";
    msgText.innerText = data.msg;

    spanBox.append(spanDate);
    spanBox.append(spanTime);
    if(role === "my") {
        sendBox.append(spanBox);
        sendBox.append(msgText);
    } else  {
        sendBox.append(msgText);
        sendBox.append(spanBox);
    }
    msgBox.append(sendBox);

    document.documentElement.scrollTop = document.body.scrollHeight;
}

async function addMyMessage() {
    let chat = {
        sender: (await findUser())[0].username,
        receiver: (await findUser())[1].username,
        msg: sendBoxInput.value,
    };

    fetch(`http://localhost:8100/chat`, {
        method: "post",
        body: JSON.stringify(chat),
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        },
    });
    window.parent.document.querySelector(".send-box__content").value = "";
}

//init 메소드
initPage();

const sendBtn = window.parent.document.querySelector(".send-box__btn")
sendBtn.addEventListener("click", () => addMyMessage());

const sendBoxInput = window.parent.document.querySelector(".send-box__content")
sendBoxInput.addEventListener("keydown", (event) => {
    if(event.keyCode === 13) {
        addMyMessage();
    }
});

