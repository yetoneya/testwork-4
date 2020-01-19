$(document).ready(function () {
    $('#question-list').change(function (event) {
        event.preventDefault();
        var id = $('#question-list').val();
        $.ajax({
            url: "/get/question",
            type: 'get',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: {id: id},
            success: function (data) {                                        
                 $("#question-text").val(data.content);
                 $("#update-question").prop("disabled", false); 
            },
            error: function (e) {

            }
        });
    });
});
