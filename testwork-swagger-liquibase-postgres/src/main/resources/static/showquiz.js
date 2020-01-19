$(document).ready(function () {
    $('#show-button').click(function (event) {
        event.preventDefault();
        var id = $("#quiz-list").val();
        $.ajax({
            url: "/show",
            type: 'get',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: {id: id},
            success: function (data) {              
                $("#show-button").prop("disabled", true);
                $("#delete-button").prop("disabled", true);
                $("#id").val(data.id);
                $("#name").val(data.name);
                $("#start").val(data.start);
                $("#end").val(data.end);
                $("#active").prop("checked", true);
                $("#question-list").empty();
                var questions = data.questions;
                for (var i = 0; i < questions.length; i++) {
                    $('#question-list').append('<option value="' + questions[i].id + '">' + questions[i].seq + ", " + questions[i].content + '</option>');
                }
                $("#update-quiz").prop("disabled", false);
                $("#question-text").val('');  
            },
            error: function (e) {
                $("#result").html(e);
            }
        });
    });
});

