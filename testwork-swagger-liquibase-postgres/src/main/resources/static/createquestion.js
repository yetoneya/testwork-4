$(document).ready(function () {
    $('#question-form').submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/create/question",
            type: "get",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: {
                seq: $("#seq").val(),
                content: $("#content").val()
            },
            success: function (data) {
                $("#question-result").html("question number " + data + " is saved");
            },
            error: function (e) {
                $("#question-result").html("question number " + data + " is not saved");
            }
        });
    });
});


