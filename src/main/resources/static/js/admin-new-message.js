function sendMessage() {
    var receiverInput = document.getElementById("receiver");
    var receiver = receiverInput.value;
    var subjectInput = document.getElementById("subject");
    var subject = subjectInput.value;
    var content = tinyMCE.activeEditor.getContent();
    var feedback = document.getElementById("feedback");
    feedback.style.display = "none";
    var btnSubmit = document.getElementById("submit");
    btnSubmit.disabled = "true";
    if (receiver == "" && subject == "" && content == "") {
        feedback.style.display = "";
        feedback.style.color = "red";
        feedback.innerHTML = "Please do not submit blank message."
        btnSubmit.removeAttribute("disabled");
    } else if (receiver == "") {
        feedback.style.display = "";
        feedback.style.color = "red";
        feedback.innerHTML = "Please type a receiver."
        btnSubmit.removeAttribute("disabled");
    } else if (subject == "") {
        feedback.style.display = "";
        feedback.style.color = "red";
        feedback.innerHTML = "Please type a subject."
        btnSubmit.removeAttribute("disabled");      
    } else if (content == "") {
        feedback.style.display = "";
        feedback.style.color = "red";
        feedback.innerHTML = "Please add content."
        btnSubmit.removeAttribute("disabled");
    } else {
        $.post(
            url = "/adminAddMessage",
            {
                "receiver": receiver,
                "subject": subject,
                "content": content
            },
            function (data) {
                if (data == 0) {
                    feedback.style.display = "";
                    feedback.style.color = "green";
                    feedback.innerHTML = "Message sent successfully."
                    receiverInput.value = "";
                    subjectInput.value = "";
                    tinyMCE.activeEditor.setContent("");
                    btnSubmit.removeAttribute("disabled");
                } else {
                    feedback.style.display = "";
                    feedback.innerHTML = "The receiver is not exist."
                    btnSubmit.removeAttribute("disabled");
                }
            },
            type = "text"
        )



    }
}