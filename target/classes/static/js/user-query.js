function createRow(account, name, phone, email, relation) {
    var place = document.getElementById("tBody");

    var tr = document.createElement("tr");
    place.appendChild(tr);
    var tAccount = document.createElement("td");
    tAccount.innerHTML = account;
    tr.appendChild(tAccount);
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
}

function sortById(a, b) {
    return a.id - b.id;
}

function searchUser(){
    var divTable = document.getElementById("table");
    divTable.style.display = "block";
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
                var account = temp.account;
                var name = temp.name;
                var phone = temp.phone;
                var email = temp.email;
                var relation = temp.relation;
                createRow(account, name, phone, email, relation);
            }

        },
        type = "text"
    )

}