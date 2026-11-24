
document.addEventListener("DOMContentLoaded", function() {



    const modal = document.getElementById("modalConfirmacion");
    const titulo = document.getElementById("modalTitulo");
    const mensaje = document.getElementById("modalMensaje");

    const botonCancelar = document.getElementById("botonCancelar");
    const botonConfirmar = document.getElementById("botonConfirmar");

    console.log("Modal:", modal);
    console.log("Título:", titulo);
    console.log("Mensaje:", mensaje);
    console.log("Cancelar:", botonCancelar);
    console.log("Confirmar:", botonConfirmar);

    let formularioActual = null;

    document.querySelectorAll(".formulario-confirmacion").forEach(formulario => {

        formulario.addEventListener("submit", function(event) {

            event.preventDefault();

            formularioActual = formulario;

            titulo.textContent = formulario.dataset.titulo;
            mensaje.textContent = formulario.dataset.mensaje;
            botonConfirmar.textContent = formulario.dataset.boton;

            modal.classList.add("activo");

        });

    });

    botonCancelar.addEventListener("click", function() {

        modal.classList.remove("activo");
        formularioActual = null;

    });

    botonConfirmar.addEventListener("click", function() {

        if (formularioActual) {
            formularioActual.submit();
        }

    });

});


