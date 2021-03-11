app = (function () {

    function insertarMensaje() {
        console.log("llegue al java script");
        var cadena = {"mensaje":$("#mensaje").val()};
        console.log(cadena);
        const promise = $.post({
            url: "http://localhost:8080/insertarMensajes",
            data: JSON.stringify(cadena),
            contentType: "application/json"
        });
        promise.then(function (data) {
            var obj = JSON.parse(data);
            var objFinal = JSON.parse(obj);
            agregarMensajes(objFinal.mensajes)
        }, function (error) {
            alert("Error al insertar el usuario " + data);
        });
    }

    function agregarMensajes(daticos){
        if (daticos === undefined){
            alert("Error en la consulta")
        } else {
            $("#table-mensajes tbody").empty();
            for(let i=0; i < daticos.length; i++){
                $("#table-mensajes > tbody:last").append($("<tr><td>" + daticos[i].mensaje + "</td><td>" + daticos[i].fecha + "</td></tr>"))
            }
        }
    }

    return {
        insertarMensaje: insertarMensaje
    }
})();