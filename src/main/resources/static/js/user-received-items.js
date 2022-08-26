function createRow(sender, subject, content, time) {
    var place = document.getElementById("place");
    var li = document.createElement("li");
    li.className = "msg_list_ul_li";
    place.appendChild(li);
    var span1 = document.createElement("span");
    span1.className = "msg_type1";
    span1.innerHTML = sender;
    li.appendChild(span1);
    var span2 = document.createElement("span");
    span2.className = "msg_type";
    span2.innerHTML = time;
    li.appendChild(span2);
    var span3 = document.createElement("span");
    span3.className = "msg_title";
    span3.innerHTML = subject;
    li.appendChild(span3);
    var div = document.createElement("div");
    div.className = "msg_content";
    div.innerHTML = content;
    li.appendChild(div);
}

function sortById(a, b) {
    return b.id - a.id;
}

function loading(){
    $.get(
        url = "/userGetMessageListByReceiver",
        function (data) {
            data = JSON.parse(data);
            data = data.sort(sortById);
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var temp = data[i];
                var sender = temp.sender;
                var subject = temp.subject;
                var content = temp.content;
                var time = temp.time;
                createRow(sender, subject, content, time);
            }
        },
        type = "text"
    )
}