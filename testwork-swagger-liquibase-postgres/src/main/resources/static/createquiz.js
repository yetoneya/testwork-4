$(document).ready(function () {
    $('#quiz-save').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: "/create/quiz",
            type: "get",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: {
                name: $("#name").val(),
                start: $("#start").val(),
                end:$("#end").val(),
                active: $("#active").is(':checked')
            },
            success: function (data) {
                $("#quiz-result").html("quiz is created with id: " + data);
                
            },
            error: function (e) {
                $("#quiz-result").html("quiz is not created");
            }
        });
    });
});


