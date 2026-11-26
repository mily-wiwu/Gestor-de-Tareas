const tipoAsignacion = document.getElementById("tipoAsignacionFecha");

const campoFechaLimite = document.getElementById("campoFechaLimite");
const campoFechaProgramada = document.getElementById("campoFechaProgramada");

const fechaLimite = document.getElementById("fechaLimite");
const fechaProgramada = document.getElementById("fechaProgramada");

function cambiarCampoFecha() {

    const tipo = tipoAsignacion.value;

    campoFechaLimite.style.display = "none";
    campoFechaProgramada.style.display = "none";

    fechaLimite.required = false;
    fechaProgramada.required = false;

    fechaLimite.disabled = true;
    fechaProgramada.disabled = true;

    if (tipo === "FECHA_LIMITE") {

        campoFechaLimite.style.display = "block";
        fechaLimite.required = true;
        fechaLimite.disabled = false;

    } else if (tipo === "DIA_ESPECIFICO") {

        campoFechaProgramada.style.display = "block";
        fechaProgramada.required = true;
        fechaProgramada.disabled = false;
    }
}

tipoAsignacion.addEventListener("change", cambiarCampoFecha);