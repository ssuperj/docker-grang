const items = document.querySelectorAll("article");
const aside = document.querySelector("aside");
const close = aside.querySelector("div");
const staticImg = aside.querySelector("aside img");

for (let el of items) {
  el.addEventListener("click", (e) => {
    let tit = e.currentTarget.querySelector("h2").innerText;
    let txt = e.currentTarget.querySelector("p").innerText;
    let img = e.currentTarget.querySelector("img");

    aside.querySelector("h1").innerText = tit;
    aside.querySelector("p").innerText = txt;
    console.log(img);
    staticImg.src = img.src;

    aside.classList.add("on");
  });

  close.addEventListener("click", () => {
    aside.classList.remove("on");
  });
}
