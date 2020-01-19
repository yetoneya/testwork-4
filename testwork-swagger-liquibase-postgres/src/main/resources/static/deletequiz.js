$(document).ready(function () {
    $('#delete-button').click(function (event) {
        event.preventDefault();
        var id = $("#quiz-list").val();
        $.ajax({
            url: "/delete",
            type: 'get',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: {id: id},
            success: function (data) {
                $("#show-button").prop("disabled", true);
                $("#delete-button").prop("disabled", true);
                $("#quiz-list option[value='" + data + "']").remove();
            },
            error: function (e) {

            }
        });
    });
});


