document.addEventListener("DOMContentLoaded", function() {

    const sidebar = document.querySelector(".barra-lateral");
    const boton = document.getElementById("botonSidebar");

    if (sidebar && boton) {

        boton.addEventListener("click", function() {
            sidebar.classList.toggle("minimizada");
        });

    }

});