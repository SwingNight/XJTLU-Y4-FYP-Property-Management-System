function saveNews() {
    var titleInput = document.getElementById("newsTitle");
    var title = titleInput.value;
    var content = tinyMCE.activeEditor.getContent();
    var select = document.getElementById("category");
    var index = select.selectedIndex;
    var category = select.options[index].value;
    var feedback = document.getElementById("feedback");
    feedback.style.display = "none";

    var btnSubmit = document.getElementById("submit");
    btnSubmit.disabled = "true";
    if (title == "" && content == "") {
        feedback.style.display = "";
        feedback.style.color = "red";
        feedback.innerHTML = "Please do not submit blank article."
        btnSubmit.removeAttribute("disabled");
    } else if (title == "") {
        feedback.style.display = "";
        feedback.style.color = "red";
        feedback.innerHTML = "Please fill in the title."
        btnSubmit.removeAttribute("disabled");
    } else if (content == "") {
        feedback.style.display = "";
        feedback.style.color = "red";
        feedback.innerHTML = "Please add content."
        btnSubmit.removeAttribute("disabled");
    } else {
        $.post(
            url = "/adminAddArticle",
            {
                "title": title,
                "category": category,
                "content": content
            },
            function (data) {
                if (data == 0) {
                    feedback.style.display = "";
                    feedback.style.color = "green";
                    feedback.innerHTML = "Article submitted successfully."
                    titleInput.value = "";
                    tinyMCE.activeEditor.setContent("");
                    btnSubmit.removeAttribute("disabled");
                }
            },
            type = "text"
        )



    }
}