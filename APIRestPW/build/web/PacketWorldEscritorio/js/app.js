const API_BASE = "http://localhost:8080/APIRestPW/api/";

document.addEventListener("DOMContentLoaded", () => {
  const inputGuia = document.getElementById("inputGuia");
  const btnBuscar = document.getElementById("btnBuscar");
  const mensaje = document.getElementById("mensaje");
  const resultado = document.getElementById("resultado");

  btnBuscar.addEventListener("click", async () => {
    const guia = inputGuia.value.trim();
    limpiarMensaje();
    ocultarResultado();

    if (!guia) {
      mostrarMensaje("Escribe un número de guía.", "warning");
      return;
    }

    mostrarMensaje("Buscando envío...", "info");

    try {
      //consultar envío
      const envio = await obtenerEnvioPorGuia(guia);

      if (!envio) {
        mostrarMensaje("No se encontró un envío con esa guía.", "error");
        return;
      }

      //mostrar datos del envío
      mostrarEnvio(envio);

      //mostrar estatus con imagen
      mostrarEstatus(envio);

      //consultar paquetes
      const paquetes = await obtenerPaquetesPorEnvio(envio.idEnvio);
      mostrarPaquetes(paquetes);

      limpiarMensaje();
      resultado.classList.remove("hidden");

    } catch (error) {
      console.error(error);
      mostrarMensaje("Error al consultar el servicio.", "error");
    }
  });

  function mostrarMensaje(texto, tipo) {
    mensaje.textContent = texto;
    mensaje.className = "mensaje";

    switch (tipo) {
      case "warning": mensaje.style.background = "#fff3cd"; break;
      case "error": mensaje.style.background = "#f8d7da"; break;
      default: mensaje.style.background = "#d1ecf1";
    }

    mensaje.classList.remove("hidden");
  }

  function limpiarMensaje() {
    mensaje.classList.add("hidden");
    mensaje.textContent = "";
  }

  function ocultarResultado() {
    resultado.classList.add("hidden");
  }


  async function obtenerEnvioPorGuia(guia) {
    const url = API_BASE + "envio/consultar/" + encodeURIComponent(guia);
    const response = await fetch(url);

    if (response.status === 404) return null;
    if (!response.ok) throw new Error("Error HTTP " + response.status);

    return response.json();
  }

  async function obtenerPaquetesPorEnvio(idEnvio) {
    const url = API_BASE + "paquete/consultar-por-envio/" + idEnvio;
    const response = await fetch(url);

    if (response.status === 404) return [];
    if (!response.ok) throw new Error("Error HTTP " + response.status);

    return response.json();
  }

  function mostrarEnvio(envio) {
    const tbody = document.querySelector("#tablaEnvio tbody");
    tbody.innerHTML = "";

    const direccion = `${envio.calle || ""} #${envio.numero || ""}, ${envio.colonia || ""}, CP ${envio.codigoPostal || ""}, ${envio.ciudad || ""}, ${envio.estado || ""}`;

    tbody.innerHTML = `
      <tr>
        <td>${envio.numeroGuia}</td>
        <td>${envio.destiNomb}</td>
        <td>${envio.destiApellidoPaterno}</td>
        <td>${envio.destiApellidoMaterno}</td>
        <td>${direccion}</td>
        <td>${envio.idEstatus}</td>
      </tr>
    `;
  }

  function mostrarEstatus(envio) {
    const banner = document.getElementById("statusBanner");

    let texto = "";
    let imagen = "";
    let clase = "";

    switch (envio.idEstatus) {
      case 1:
        texto = "En tránsito";
        imagen = "assets/status-transito.png";
        clase = "status-transito";
        break;

      case 2:
        texto = "Detenido";
        imagen = "assets/status-detenido.png";
        clase = "status-detenido";
        break;

      case 3:
        texto = "Entregado";
        imagen = "assets/status-entregado.png";
        clase = "status-entregado";
        break;

      case 4:
        texto = "Cancelado";
        imagen = "assets/status-cancelado.png";
        clase = "status-cancelado";
        break;

      default:
        texto = "Estatus desconocido";
        imagen = "";
        clase = "";
    }

    banner.className = `status-banner ${clase}`;
    banner.innerHTML = `
      ${imagen ? `<img src="${imagen}" alt="${texto}" class="status-icon">` : ""}
      <span>${texto}</span>
    `;
  }

  function mostrarPaquetes(paquetes) {
    const tbody = document.querySelector("#tablaPaquetes tbody");
    tbody.innerHTML = "";

    if (!paquetes || paquetes.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="5">Este envío no tiene paquetes registrados.</td>
        </tr>
      `;
      return;
    }

    paquetes.forEach(p => {
      const fila = `
        <tr>
          <td>${p.peso}</td>
          <td>${p.alto}</td>
          <td>${p.ancho}</td>
          <td>${p.descripcion}</td>
          <td>${p.profundidad}</td>
        </tr>
      `;
      tbody.insertAdjacentHTML("beforeend", fila);
    });
  }
});
