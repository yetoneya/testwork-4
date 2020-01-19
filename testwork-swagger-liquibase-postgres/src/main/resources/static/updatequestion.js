$(document).ready(function () {
    $('#update-question').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/update/question",
            type: 'get',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: {
                id: $('#question-list').val(),
                content: $('#question-text').val()     
            },
            success: function (data) {
                $("#result").html("question with id = " + data + " is change");             
                $("#update-question").prop("disabled", true);
            },
            error: function (e) {

            }
        });
    });
});


