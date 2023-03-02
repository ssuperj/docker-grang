window.addEventListener("load", () => {
  const grid = new Isotope("section", {
    itemSelector: "article",
    columnWidth: "article",
    transitionDuration: "0.5s",
  });

  //클린한 모든 버튼 변수에 저장
  const btns = document.querySelectorAll("main ul li");

  //버튼의 개수만큼 반복해서
  for (let el of btns) {
    //각 버튼 클릭 이벤트 연결
    el.addEventListener("click", (e) => {
      e.preventDefault();
      //변수 sort에 클릭한 대상의 자식인 a요소의 href 속성값 저장
      const sort = e.currentTarget.querySelector("a").getAttribute("href");

      //grid에 저장된 결괏값을 불러와 재정렬 기능 연결
      grid.arrange({
        //옵션값으로 sort변수값 지정
        filter: sort,
      });
    });
  }
});
