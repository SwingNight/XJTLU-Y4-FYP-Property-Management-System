function loaded() {
    $.get(
        url = "/getUserInfo",
        function (data) {
            data = JSON.parse(data);
            var id = document.getElementById("id");
            id.innerHTML = data.id;
            var house = document.getElementById("account");
            house.innerHTML = data.account;
            var name = document.getElementById("name");
            name.value = data.name;
            var phone = document.getElementById("phone");
            phone.value = data.phone;
            var email = document.getElementById("email");
            email.value = data.email;
            var relation = document.getElementById("relation");
            relation.value = data.relation;
        },
        type = "text"
    )
}

function change() {
    var name = document.getElementById("name").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var relation = document.getElementById("relation").value;

    var btnSubmit = document.getElementById("submit");
    btnSubmit.disabled = "true";
    $.post(
        url = "/userInfoChange",
        {
            "name": name,
            "phone": phone,
            "email": email,
            "relation": relation
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


