app = (function () {

    function insertarMensaje() {
        console.log("llegue al java script");
        const promise = $.post({
            url: "http://localhost:8080/insertar-mensajes",
            data: JSON.stringify( "{ \"mensaje\":" + "\"" + $("#mensaje") + "\"" + "}"),
            contentType: "application/json"
        });
        promise.then(function (data) {
            console.log(data);
        }, function (error) {
            alert("Error al insertar el usuario " + data);
        });
    }

    return {
        insertarMensaje: insertarMensaje
    }
})();