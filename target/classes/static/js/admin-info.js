function loaded() {
    console.log("Loading...");
    $.get(
        url = "/getAdminInfo",
        function (data) {
            data = JSON.parse(data);
            var id = document.getElementById("id");
            id.innerHTML = data.id;
            var account = document.getElementById("account");
            account.innerHTML = data.account;
            var name = document.getElementById("name");
            name.value = data.name;
            var phone = document.getElementById("phone");
            phone.value = data.phone;
            var email = document.getElementById("email");
            email.value = data.email;
            var office = document.getElementById("office");
            office.value = data.office;
            var duty = document.getElementById("duty");
            duty.value = data.duty;
        },
        type = "text"
    )
}

function change() {
    var name = document.getElementById("name").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var office = document.getElementById("office").value;
    var duty = document.getElementById("duty").value;

    var btnSubmit = document.getElementById("submit");
    btnSubmit.disabled = "true";
    $.post(
        url = "/adminInfoChange",
        {
            "name": name,
            "phone": phone,
            "email": email,
            "office":office,
            "duty": duty
        },
        function (data) {
            if(data==0){
                console.log("Personal Information is Changed Successfully.");
                btnSubmit.removeAttribute("disabled");
            }
        },
        type = "text"
    )

}

function logout() {
    $.get(
        url = "/adminLogout",
        function (data) {
            if(data==0){
                window.location.href="adminLogin.html";
            }
        },
        type = "text"
    )
}