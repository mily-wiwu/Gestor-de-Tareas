function cambiarOpciones() {
    const criterio = document.getElementById("tipoFiltro").value;
    const opciones = document.getElementById("opcionFiltro");

    opciones.innerHTML = "";

    const opcionInicial = document.createElement("option");
    opcionInicial.value = "";
    opcionInicial.textContent = "Selecciona una opción";
    opciones.appendChild(opcionInicial);

    let fuente;

    if (criterio === "categoria") {
        fuente = document.getElementById("opcionesCategoria");
    } else if (criterio === "prioridad") {
        fuente = document.getElementById("opcionesPrioridad");
    } else if (criterio === "estado") {
        fuente = document.getElementById("opcionesEstado");
    }

    if (!fuente) {
        opciones.disabled = true;
        return;
    }

    const elementos = fuente.querySelectorAll("option");

    elementos.forEach(elemento => {
        const nuevaOpcion = document.createElement("option");

        nuevaOpcion.value = elemento.value;
        nuevaOpcion.textContent = elemento.textContent;

        opciones.appendChild(nuevaOpcion);
    });

    opciones.disabled = false;
}

window.addEventListener("DOMContentLoaded", function() {
    const tipoFiltro = document.getElementById("tipoFiltro");
    const opcionFiltro = document.getElementById("opcionFiltro");

    opcionFiltro.disabled = true;

    if (tipoFiltro.value !== "") {
        cambiarOpciones();

        const valorGuardado = opcionFiltro.dataset.seleccionado;

        if (valorGuardado) {
            opcionFiltro.value = valorGuardado;
        }
    }
});
