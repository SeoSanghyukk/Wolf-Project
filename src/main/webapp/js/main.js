// 모드 변경 시 호출되는 함수
function toggleMode() {
    var sun = document.querySelector(".sun"); //아래 원 
    var navi = document.querySelector(".navi"); //위 메뉴
    let body = document.body;

    body.classList.toggle("light"); // <body>에 'light' 클래스 토글하기

    // 아이콘 토글하기
    let darkIcon = document.querySelector(".fa-regular.fa-lightbulb"); //none 으로 숨겨진 아이콘
    let lightIcon = document.querySelector(".fa-solid.fa-lightbulb");

    // 아이콘 토글하기 , <body>에 'light' 클래스가 있을 때 색상 변경하기 
    if (body.classList.contains("light")) {
        // 아이콘 숨기기
        darkIcon.style.display = "none";
        lightIcon.style.display = "inline-block";
        //색상 변경 
        navi.style.backgroundColor = "var(--bg-dark)";
        sun.style.backgroundColor = "var(--bg-dark)";
        sun.style.boxShadow = `5px -5px 10px var(--bg-dark)`;
        // 로컬 스토리지에 모드 상태 저장
        localStorage.setItem("mode", "light");
    } else {
        // 아이콘 변경 
        darkIcon.style.display = "inline-block";
        lightIcon.style.display = "none";
        //색상 변경 
        navi.style.backgroundColor = "var(--bg-nav)";
        sun.style.backgroundColor = "var(--bg-nav)";
        sun.style.boxShadow = `5px -5px 10px var(--bg-light)`;
        // 로컬 스토리지에서 모드 상태 제거
        localStorage.removeItem("mode");
    }
}

// 페이지 로드 시 모드 상태 복원
document.addEventListener("DOMContentLoaded", function () {
    // 저장된 모드 상태 확인
    var mode = localStorage.getItem("mode");
    console.log("test: " + mode);
    if (mode === "light") {
        toggleMode(); // light 모드인 경우 토글 함수 호출하여 변경
    }

    // 현재 페이지 주소
    var pathname = window.location.pathname;
    if (pathname === "/" || pathname === "/index.jsp") homeBinding();


});

// 모드 변경 이벤트 리스너 등록
let mode = document.getElementById("mode"); //버튼
mode.addEventListener("click", toggleMode);


/** Home 입장 시 게임 데이터 받아서 카드에 바인딩 **/
const homeBinding = () => {
    $.ajax({
        url: "/home.web",
        method: "post",
        dataType: "json"
    })
        .done((res) => {
            console.log("res === ", res);
            if (res.result === "ok") {
                res.data.forEach(item => homeCardSetting(item));
            } else {
                console.log("No 'data' property found in response.");
            }
        });
}

const homeCardSetting = (res) => {
    let item = `
		<div class="item flex-grow-1">
			<div class="card d-flex">
				<img src="${res.thumbnail}" class="card-img-top" alt="..."
					style="flex: 7;">
				<div class="card-body" style="flex: 3;">
					<p class="card-text">${res.title}</p>
				</div>
			</div>
		</div>`

    $("#card-form").append(item);
}
