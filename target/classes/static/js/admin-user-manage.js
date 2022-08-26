function searchUser() {
    var acc = document.getElementById("inputAcc").value;
    var tBody = document.getElementById("tBody");
    while (tBody.firstChild) {
        tBody.removeChild(tBody.firstChild);
    }
    $.post(
        url = "/queryUserList",
        {
            "acc": acc
        },
        function (data) {
            data = JSON.parse(data);
            data = data.sort(sortById);
            for (var i = 0; i < data.length; i++) {
                var temp = data[i];
                var id = temp.id;
                var account = temp.account;
                var password = temp.password;
                var name = temp.name;
                var phone = temp.phone;
                var email = temp.email;
                var relation = temp.relation;
                createRow(id, account, password, name, phone, email, relation);
            }

        },
        type = "text"
    )

}

function deleteItem(id) {
    $.post(
        url = "/delUser",
        {
            "id": id
        },
        function (data) {
            if (data == 0) {
                window.location.reload(true);
            }
        },
        type = "text"
    )
}

function createRow(id, account, password, name, phone, email, relation) {
    var place = document.getElementById("tBody");

    var tr = document.createElement("tr");
    place.appendChild(tr);
    var tAccount = document.createElement("td");
    tAccount.innerHTML = account;
    tr.appendChild(tAccount);
    var tPassword = document.createElement("td");
    tPassword.innerHTML = password;
    tr.appendChild(tPassword);
    var tName = document.createElement("td");
    tName.innerHTML = name;
    tr.appendChild(tName);
    var tPhone = document.createElement("td");
    tPhone.innerHTML = phone;
    tr.appendChild(tPhone);
    var tEmail = document.createElement("td");
    tEmail.innerHTML = email;
    tr.appendChild(tEmail);
    var tRela = document.createElement("td");
    tRela.innerHTML = relation;
    tr.appendChild(tRela);
    var tEdit = document.createElement("td");
    tr.appendChild(tEdit);
    var tbut = document.createElement("button");
    tbut.innerHTML = "Delete";
    tbut.className = "btninfo";
    tEdit.appendChild(tbut);
    tbut.onclick = function () {
        deleteItem(id);
    };

}

function sortById(a, b) {
    return a.id - b.id;
}

function loading() {
    $.get(
        url = "/getUserList",
        function (data) {
            data = JSON.parse(data);
            data = data.sort(sortById);
            for (var i = 0; i < data.length; i++) {
                var temp = data[i];
                var id = temp.id;
                var account = temp.account;
                var password = temp.password;
                var name = temp.name;
                var phone = temp.phone;
                var email = temp.email;
                var relation = temp.relation;
                createRow(id, account, password, name, phone, email, relation);
            }
        },
        type = "text"
    )
}