function checkEmpty(obj) {
    if (obj == "") {
        return false;
    } else {
        return true;
    }
}

function userLoginSubmit() {
    var account = document.getElementById("userAccount").value;
    var password = document.getElementById("userPassword").value;
    if (checkEmpty(account) && checkEmpty(password)) {
        var btnSubmit = document.getElementById("loginButton");
        btnSubmit.disabled = "true";
        $.post(
            url = "/userLogin",
            {
                "account": account,
                "password": password
            },
            function (data) {
                if (data == 0) {
                    alert("Please correct the account.");
                    btnSubmit.removeAttribute("disabled");
                } else if (data == 1) {
                    console.log("Login Successfully.");
                    window.location.href="user-index.html";
                } else {
                    alert("Please correct the password.");
                    btnSubmit.removeAttribute("disabled");
                }
            },
            type = "text"
        )
    } else {
        alert("Please fill the blank.");
        btnSubmit.removeAttribute("disabled");
    }
}

function adminLoginSubmit() {
    var account = document.getElementById("userAccount").value;
    var password = document.getElementById("userPassword").value;
    if (checkEmpty(account) && checkEmpty(password)) {
        var btnSubmit = document.getElementById("loginButton");
        btnSubmit.disabled = "true";
        $.post(
            url = "/adminLogin",
            {
                "account": account,
                "password": password
            },
            function (data) {
                if (data == 0) {
                    alert("Please correct the account.");
                    btnSubmit.removeAttribute("disabled");
                } else if (data == 1) {
                    console.log("Login Successfully.")
                    window.location.href="admin-news.html";
                } else {
                    alert("Please correct the password.");
                    btnSubmit.removeAttribute("disabled");
                }
            },
            type = "text"
        )
    } else {
        alert("Please fill the blank.");
        btnSubmit.removeAttribute("disabled");
    }
}