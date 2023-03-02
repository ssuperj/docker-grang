const currentHostname = window.location.hostname;
console.log(currentHostname)
const index1 = {
    isExit : false,
    init: function () {
        $(".send-box__btn").on("click", () => {
            this.save();
        });
        $("#search-input").on("keyup", (event) => {
            if(event.target.value !== "") {
                this.search();
            }
        });
        $("#findUserToggleBtn").on("click", () => {
            this.toggleFindUserBox()
        });
        const userDetail = document.querySelectorAll(".msg__main__content");
        userDetail.forEach((target)=>{
            target.addEventListener("click", ()=>this.showDetail(target.dataset.id))
        })
    },
    search: function () {
        const search = $("#search-input").val();

        $.ajax({
            url: "/auth/api/findUser",
            data: {username: search},
        }).done(function (resp) {
            const user = resp;
            const userBox = document.querySelector(".findUser-box__user");
            while (userBox.hasChildNodes()) {
                userBox.removeChild(userBox.firstChild);
            }
            for (i of user) {
                const div = document.createElement("div");
                div.classList.add("findUser-box__user__column");
                const div2 = document.createElement("div");
                div2.classList.add("findUser-box__user__column__content");
                const span = document.createElement("span");
                const span2 = document.createElement("span");
                span2.innerText = i.username;
                const img = document.createElement("img");
                console.log(i.profileImage)
                if(i.profileImage != "/image/normal.jpg"){
                    img.src = `/profile/${i.profileImage}`;
                } else {
                    img.src = "/image/normal.jpg";
                }
                img.width = 30;
                img.height = 30;
                const div3 = document.createElement("div");
                div3.classList.add("findUser-box__user__column__btn");
                const icon = document.createElement("i");
                icon.classList.add("fa-solid")
                icon.classList.add("fa-plus")
                icon.classList.add("fa-xl")

                const loginTime = Date.now() - Date.parse(i.loginTime);
                div.setAttribute("data-time", index1.calcDiffTime(loginTime));
                div.setAttribute("data-id", i.id);

                span.appendChild(img)
                div2.appendChild(span)
                div2.appendChild(span2)
                div3.appendChild(icon)
                div.appendChild(div2);

                div.appendChild(div3);
                userBox.appendChild(div);
                div.addEventListener("click", ()=> this.showDetail(124, 123));
            }
            index1.appendUser();
        }).fail(function (error) {
            console.log(JSON.stringify(error))
        });
    },

    calcDiffTime: function (time) {
        const minute = Math.floor(time /(1000 * 60));
        const hour = Math.floor(time /(1000 * 60 * 60));
        const day = Math.floor(time /(1000 * 60 * 60 * 24));
        if(day >= 1) {
            return day + '일전';
        }
        if(hour >= 1) {
            return hour +  '시간전';
        }
        return minute + '분전';
    },

    appendUser: function () {
        const userBox = $(".msg__main__user");

        $(".findUser-box__user__column__btn").on("click", (event)=>{
            const userElement = event.target.parentElement.parentElement;
            const node = document.querySelectorAll(".findUser-box__user__column");
            const number = Array.from(node).indexOf(userElement);
            const userInfo = node[number];
            if(this.checkDuplicationUser(userInfo.dataset.id)) {
                return;
            }
            if(this.checkMyId(userInfo.dataset.id)) {
                return;
            }
            const img = userInfo.children[0].children[0].children[0];
            img.width = 30;
            const text = userInfo.children[0].children[1];
            const span = document.createElement("span");
            span.innerText = userInfo.dataset.time;

            const div = document.createElement("div");
            div.classList.add("msg__main__content");
            div.classList.add("slow");
            div.setAttribute("data-id", userInfo.dataset.id);
            const div2 = document.createElement("div");
            div2.classList.add("msg__main__content__image");
            const div3 = document.createElement("div");
            div3.classList.add("msg__main__content__name");
            const span2 = document.createElement("span");
            span2.classList.add("msg__main__content__btn");
            const i = document.createElement("i");
            i.className = "fa-solid fa-xmark";

            const cloneImg = img.cloneNode(true);
            cloneImg.width = 50;
            cloneImg.height = 50;
            div2.appendChild(cloneImg);
            div3.appendChild(text.cloneNode(true));
            div3.appendChild(span);
            div.appendChild(div2);
            div.appendChild(div3);
            span2.appendChild(i);
            div.appendChild(span2);

            userBox.append(div);
            div.addEventListener("click", ()=> this.showDetail(userInfo.dataset.id));
            span2.addEventListener("click", ()=> this.deleteChatUser(event))
            this.saveUser(userInfo);
        });
    },

    checkDuplicationUser: function (userId) {
        const chattingFriend = document.querySelectorAll(".msg__main__content");

        for(i of chattingFriend) {
            if(i.dataset.id === userId) return true;
        }
        return false;
    },

    checkMyId: function (userId) {
        const myId = document.querySelector("#myId").value;
        return userId === myId;
    },

    toggleFindUserBox: function () {
        const findUserBox = document.querySelector(".findUser-box");
        findUserBox.classList.toggle("remove");
    },

    saveUser: function (user) {
        const data = {
            sendUserId: $("#myId").val(),
            recvUserId: user.dataset.id,
        }
        $.ajax({
            type: "post",
            url: "/room/userSave/",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=UTF-8"
        });
    },

    showDetail: function (id) {
        const iframe = document.querySelector("#iframe");
        iframe.src = `/detail/${id}`
    },
    
    deleteChatUser: function (event) {
        const userBox = document.querySelectorAll(".msg__main__content");
        const clickedUserBox = event.target.parentElement.parentElement;
        const myId = document.querySelector("#myId").value;
        const clickId = clickedUserBox.dataset.id;

        let data = {
            sendUserId: myId,
            recvUserId: clickId
        }

        fetch("/room", {
            method: "delete",
            body: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json; charset=UTF-8",
            },
        }).then(resp => resp.json())
            .then(resp => {
                this.deleteMongoChatDb(resp)
            })
            .catch(err => console.log(err));

        for(i of userBox) {
            if(i.dataset.id === clickId) {
                i.remove();
            }
        }
    },

    deleteMongoChatDb: function (user) {
        let data = {
            sender: user[0].username,
            receiver: user[1].username
        }

        fetch("http://localhost:8100/chat", {
            method: "delete",
            body: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json; charset=UTF-8",
            },
        }).catch(err => console.log(err));
    }
};
index1.init();