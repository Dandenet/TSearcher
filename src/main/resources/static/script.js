$(document).ready(function(){    
    $("#save_button").click(function(){

        console.log("button clicked");
        var id = $('#id_input').val();
        var msg   = $('#formx').serialize();
            $.ajax({
                            type: "POST",
                            url: "/user/" + id,
                            data: msg,
                    })
                    .done(function( data ) {
                    });		
    });

    $(".btn-grades").click(function(){
        id = $(this).data("id"); 
        $("tr").removeClass('alert-info');
        $.ajax({
                        type: "GET",
                        url: "/students/"+id+"/scores"
                })
                .done(function( data ) {

                        $("#student-"+id).addClass('alert-info');
                        $("#grades").html( "<h3>Оценки</h3>" );
                        for (var subject in data) {
                                $("#grades").append( subject+": "+ (data[subject] ? data[subject] : "-")+"<br>" );
                        }

                });		
})
 });