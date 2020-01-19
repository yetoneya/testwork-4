$(document).ready(function () {
    $("#update-quiz").click(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/update/quiz",
            type: "get",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: {
                id: $("#quiz-list").val(),
                name: $("#name").val(),
                start: $("#start").val(),
                end: $("#end").val(),
                active: $("#active").is(':checked')
            },
            success: function (data) {
                $('#update-quiz').prop("disabled", true);
                $('#show-button').prop('disabled', false);
                $('#delete-button').prop('disabled', false);
                $("#result").html("quiz with id = " + data + " is change");
            },
            error: function (e) {
                $("#quiz-result").html("quiz is not updated: " + e);
            }
        });
    });
});


