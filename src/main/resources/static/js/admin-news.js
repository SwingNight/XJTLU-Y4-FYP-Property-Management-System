function listen(div){
    var modal = document.getElementById("simpleModal");
    modal.style.display = "block";
    window.addEventListener("click", function(e){
        if (e.target == modal) {
                modal.style.display = "none";
                div.remove();
            }
    });
}

function showContent(title, content){
    var place = document.getElementById("main");
    var div1 = document.createElement("div");
    div1.id = "simpleModal";
    div1.className = "modal";
    place.appendChild(div1);
    var div2 = document.createElement("div");
    div2.className = "modal-content";
    div1.appendChild(div2);
    var div3 = document.createElement("div");
    div3.className = "modal-header";
    div2.appendChild(div3);
    var h2 = document.createElement("h5");
    h2.innerHTML = title;
    div3.appendChild(h2);
    var div4 = document.createElement("div");
    div4.className = "modal-body";
    div4.innerHTML = content;
    div2.appendChild(div4);
    var div5 = document.createElement("div");
    div5.className = "modal-footer";
    div5.innerHTML = "Click outside to return.";
    div2.appendChild(div5);

    listen(div1);

}

function deleteItem(id){
    $.post(
        url = "/adminDelNotice",
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

function createRow(id, title, content, author, time, category) {
    var place = document.getElementById("tBody");
    var tr = document.createElement("tr");
    place.appendChild(tr);
    var tTitle = document.createElement("td");
    tTitle.innerHTML = title;
    tr.appendChild(tTitle);
    tTitle.onclick = function(){
        showContent(title, content);
    };
    var tAuthor = document.createElement("td");
    tAuthor.innerHTML = author;
    tr.appendChild(tAuthor);
    var tTime = document.createElement("td");
    tTime.innerHTML = time;
    tr.appendChild(tTime);
    var tCate = document.createElement("td");
    tCate.innerHTML = category;
    tr.appendChild(tCate);
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
    return b.id - a.id;
}

function loading() {
    $.get(
        url = "/getNoticeList",
        function (data) {
            data = JSON.parse(data);
            data = data.sort(sortById);
            for (var i = 0; i < data.length; i++) {
                var temp = data[i];
                var noticeId = temp.id;
                var id = i;
                var title = temp.title;
                var content = temp.content;
                var author = temp.author;
                var time = temp.time;
                var category = temp.category;
                createRow(noticeId, title,content,author,time,category);
            }
        },
        type = "text"
    )
}