function setColor(category) {
    if (category == "news") {
        return "#a7fea7";
    } else {
        return "#87CEFF";
    }
}

function createRow(id, title, content, author, time, category) {
    var place = document.getElementById("place");
    var div1 = document.createElement("div");
    div1.className = "body";
    var color = setColor(category);
    div1.style.backgroundColor = color;
    place.appendChild(div1);
    var h2 = document.createElement("h2");
    h2.className = "noticeTitle";
    h2.innerHTML = title;
    div1.appendChild(h2);
    var div2 = document.createElement("div");
    div2.className = "noticeInfo";
    div1.appendChild(div2);

    var tr = document.createElement("tr");
    div2.appendChild(tr);
    var td1 = document.createElement("td");
    td1.innerHTML = "Author: ";
    tr.appendChild(td1);
    var td2 = document.createElement("td");
    td2.innerHTML = author;
    tr.appendChild(td2);
    var td3 = document.createElement("td");
    td3.innerHTML = "&nbsp&nbsp&nbsp&nbspPublish Time: ";
    tr.appendChild(td3);
    var td4 = document.createElement("td");
    td4.innerHTML = time;
    tr.appendChild(td4);

    var div3 = document.createElement("div");
    div3.className = "noticeContent";
    div3.innerHTML = content;
    div1.appendChild(div3);
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
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var temp = data[i];
                var noticeId = temp.id;
                var id = i;
                var title = temp.title;
                var content = temp.content;
                var author = temp.author;
                var time = temp.time;
                var category = temp.category;
                createRow(noticeId, title, content, author, time, category);
            }
        },
        type = "text"
    )
}