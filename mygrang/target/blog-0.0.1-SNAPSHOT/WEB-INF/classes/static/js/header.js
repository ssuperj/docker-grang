let audio1 = new Audio("/music/sound1.mp3");
let searchValue = "";

document.querySelectorAll(".playmusic").forEach((target) => {
  target.addEventListener("click", function () {
    audio1.loop = true; // 반복재생하지 않음
    audio1.volume = 0.5; // 음량 설정
    audio1.play(); // sound1.mp3 재생
  });
});

document.querySelectorAll(".stopmusic").forEach((target) => {
  target.addEventListener("click", function () {
    audio1.pause();
  });
});

const dropIcon = document.querySelectorAll(".drop-link i");
const dropDown = document.querySelectorAll(".drop-link ul");

for (i of dropIcon) {
  i.addEventListener("click", clickDropIcon);
}

for (i of dropDown) {
  i.addEventListener("keypress", findUser);
}

function clickDropIcon(event) {
  const ul = event.target.nextElementSibling;
  ul.classList.toggle("remove");
}

function findUser(event) {
  if (event.keyCode === 13 && event.target.value !== "") {
    let url = new URL("http://localhost:8090/auth/api/findUser");
    const params = {username: event.target.value}
    url.search = new URLSearchParams(params).toString();

    fetch(url)
        .then(resp=>resp.json())
        .then(resp=>createNode(resp[0]))
        .catch(err=>console.log(err));

    event.target.value = "";
  }
}

function createNode(user) {
  const li = document.createElement("li");
  li.classList.add("drop-down__history");
  li.classList.add("slow");
  const a = document.createElement("a");
  a.href = `/userPage/${user.id}`;
  a.style = "color: #fff";
  const span = document.createElement("span");
  span.innerText = user.username;
  const button = document.createElement("button");
  button.innerText = "X";

  a.appendChild(span);
  li.appendChild(a);
  li.appendChild(button);
  dropDown[0].appendChild(li);
  let clone = li.cloneNode(true);
  dropDown[1].appendChild(clone);

  const deleteBtn = document.querySelectorAll(".drop-down__history");
  deleteBtn.forEach(target=>target.addEventListener("click", clickDeleteBtn));
}

function clickDeleteBtn() {
  const index = Array.from(this.parentNode.children).indexOf(this);
  deleteDropDown(index);
}

function deleteDropDown(number) {
  for (i of dropDown) {
    i.children[number].remove();
  }
}
