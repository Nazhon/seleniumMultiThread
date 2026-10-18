package main.java.pageEvents.tia.transversal;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import main.java.config.Context;
import main.java.config.ElementFetch;
import main.java.config.Utils;
import main.java.config.Validation;
import main.java.pageEvents.portalSB.transversal.TransversalPortalSBEvents;
import main.java.pageObjects.tia.productos.MuerteAccidentalElements;
import main.java.pageObjects.tia.transversal.TransversalElements;
import main.java.properties.EntidadProperties;
import main.java.properties.PolizaProperties;
import main.java.properties.SiniestroProperties;
import main.java.utils.Constants;
import main.java.utils.Events;
import test.java.BaseTest;

public class TransversalEvents extends BaseTest implements TransversalElements {
	static String rutAsegurado = "";

	@Context(description = "Valida que se haya ingresado a la URL de TIA QA/UAT", page = "Login", functionality = "Transversal")
	public static void validarAmbienteTIAQAUAT(String opcionAmbiente) {
		// Se debe ingresar aqui la URL correspondiente
		try {
			switch (opcionAmbiente) {
			case "QA":
				opcionAmbiente = "qa";
				getDriver().get(Constants.url);
				Utils.outputInfo("La opción de ambiente es: " + opcionAmbiente);
				break;
			case "UAT":
				opcionAmbiente = "uat";
				getDriver().get(Constants.urlUAT);
				Utils.outputInfo("La opción de ambiente es: " + opcionAmbiente);
				break;
			default:
				Utils.eventFailed(
						"La opción ingresada para desplegar el portal TIA es inválida, favor escoger entre las siguientes opciones: QA; UAT");
			}

			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(50));
			ElementFetch elementFetch = new ElementFetch();

			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			});

			Utils.outputInfo("Tiempo de espera de 5 segundos para carga de la plataforma TIA");
			WebElement element = elementFetch.getWebElement("XPATH", buttonIniciarSesion);
			Utils.takeAdditionalScreenshot("tia_segundo_plano", "extra");
			wait.until(ExpectedConditions.visibilityOf(element));
			String url = BaseTest.getDriver().getCurrentUrl().toString();
			Validation.trueBooleanCondition(url.contains(opcionAmbiente),
					"Se ha ingresado al ambiente " + opcionAmbiente + " de TIA correctamente",
					"No se ha ingresado al ambiente de TIA");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida que la poliza se haya renovado", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validarPolizaRenovado() {
		try {
			String tituloLineaPoliza = Events.getText(MuerteAccidentalElements.labelPaginaRenovada);
			Utils.outputInfo("La sección actual es: " + tituloLineaPoliza);
			Validation.trueBooleanCondition(tituloLineaPoliza.contains("R Renovación"),
					"Se ha ingresado a estado de la póliza correctamente: " + tituloLineaPoliza,
					"No se ha ingresado a estado de la póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Pagina Entidad ", page = "Entidad", functionality = "Renovacion")
	public static void buttonRenovar() {
		try {
			Events.clickButton(buttonRenovacion);
			Events.clickButton(buttonCompletarRenovacion);
			Events.clickButton(buttonAceptarRenovacion);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar mensaje de error de código CSPC y PPC", page = "Manejo Objeto", functionality = "Emision")
	public static void validarMensajeErrorFecha() {
		try {
			// Esperar a que cargue la advertencia
			TransversalEvents.loadingTIAGeneral(120);

			// Obtener el texto del mensaje de advertencia
			String mensajeAdvertencia = Events.getText(labelErrorFecha);
			Utils.outputInfo("Mensaje de advertencia mostrado: " + mensajeAdvertencia);

			// Validar que contenga el texto esperado
			if (mensajeAdvertencia.contains("Este campo es obligatorio")) {
				Utils.outputInfo("Mensaje de error correcto.");
			} else {
				Utils.eventFailed(
						"No se encontró el mensaje de error esperado. Mensaje encontrado: " + mensajeAdvertencia);
			}

			// Espera final por carga
			TransversalEvents.loadingTIAGeneral(120);

		} catch (Exception e) {
			Utils.eventFailed("Error inesperado al validar el mensaje de advertencia: " + e.getMessage());
		}
	}

	@Context(description = "Entrar al sistema TIA", page = "Login", functionality = "Transversal")
	public static void iniciarSesion(String user, String pass) {

		try {
			Events.sendKeys(inputUsuario, user);
			Events.sendKeys(inputPassword, pass);
			Events.clickButton(buttonIniciarSesion);
			((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete");
			String tittle = Events.getText(labelPaginaPrincipal);

			Utils.outputInfo("La sección actual es: " + tittle);
			Validation.trueBooleanCondition(tittle.contains("Página principal"),
					"Se ha ingresado al sistema correctamente", "No se ha ingresado al sistema de forma correcta");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Buscar entidad por apellido", page = "Buscar Entidades", functionality = "Transversal")
	public static void buscarEntidad(String apellido) {

		try {
			loadingTIAGeneral();
			Events.clickButton(aBuscarEntidad);
			loadingTIAGeneral();
			Events.limpiarCajaDeTexto(inputApellido);
			loadingTIAGeneral();
			Events.sendKeys(inputApellido, apellido);
			loadingTIAGeneral();
			Events.clickButton(buttonBuscarEntidad);
			loadingTIAGeneral();
			Utils.takeAdditionalScreenshot(String.format("Se ingresa y busca a la entidad %s", apellido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el ID según el nombre completo dentro del resultado de la búsqueda por apellido", page = "Buscar Entidades", functionality = "Transversal")
	public static void seleccionarContratante(String nombreCompleto) {

		try {
			Events.clickButton(aNumeroEntidad.replace("Nombre", nombreCompleto));
			loadingTIAGeneral(120);
			String tituloPagina = Events.getText(labelPaginaResumenEntidad);
			Utils.outputInfo("La sección actual es: " + tituloPagina);
			Validation.trueBooleanCondition(tituloPagina.contains(nombreCompleto),
					"Se ha ingresado al resumen de la entidad correctamente: " + nombreCompleto,
					"No se ha ingresado al resumen de la entidad");
			EntidadProperties.nombreContratante = nombreCompleto;
			String idEntidad = Events.getText(aResumenIdEntidad);
			PolizaProperties.idEntidad = idEntidad;
			Utils.outputInfo("El ID  de la entidad es: " + idEntidad);
			Utils.takeAdditionalScreenshot(String.format("Ingreso al resumen de la entidad %s", nombreCompleto),
					"extra");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el ID según el nombre completo dentro del resultado de la búsqueda por apellido", page = "Buscar Entidades", functionality = "Transversal")
	public static void paginaEntidad(String nombreCompleto) {

		try {
			loadingTIAGeneral(120);
			String tituloPagina = Events.getText(labelPaginaResumenEntidad);
			Utils.outputInfo("La sección actual es: " + tituloPagina);
			Validation.trueBooleanCondition(tituloPagina.contains(nombreCompleto),
					"Se ha ingresado al resumen de la entidad correctamente: " + nombreCompleto,
					"No se ha ingresado al resumen de la entidad");
			EntidadProperties.nombreContratante = nombreCompleto;
			String idEntidad = Events.getText(aResumenIdEntidad);
			PolizaProperties.idEntidad = idEntidad;
			Utils.outputInfo("El ID  de la entidad es: " + idEntidad);
			Utils.takeAdditionalScreenshot(String.format("Ingreso al resumen de la entidad %s", nombreCompleto),
					"extra");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea una póliza para un producto", page = "Resumen Entidad", functionality = "Emision")
	public static void crearNuevaPoliza(String producto) {

		try {
			loadingTIAGeneral(60);
			Events.clickButton(buttonNuevaPoliza);
			loadingTIAGeneral(60);
			List<WebElement> productos = Events.getElementList(listOpciones);
			for (int i = 0; i < productos.size(); i++) {
				String opcion = productos.get(i).getText();
				String[] nombreOpcion = opcion.split("-");

				if (nombreOpcion[1].trim().toLowerCase().equalsIgnoreCase(producto)) {
					productos.get(i).click();
					Utils.outputInfo("Se ha seleccionado la opción: " + opcion);
					PolizaProperties.producto = producto;
					PolizaProperties.idProducto = opcion.substring(0, 2);
					break;
				}
			}
			Utils.takeAdditionalScreenshot(String.format("Se selecciona el producto %s", PolizaProperties.producto),
					"extra");
			Events.clickButton(buttonSi);
			loadingTIAGeneral(60);
			String tituloPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPoliza);
			Validation.trueBooleanCondition(tituloPoliza.contains(producto),
					"Se ha creado una póliza para el producto correcto: " + producto,
					"No se ha creado la póliza correctamente");

			int inicio = tituloPoliza.indexOf("-");
			int fin = tituloPoliza.indexOf("-", inicio + 1);
			String idPolizaRaw = tituloPoliza.substring(inicio + 1, fin).trim();
			String idPoliza = idPolizaRaw.replaceAll("[^0-9]", "");
			PolizaProperties.idPoliza = idPoliza;
			Utils.takeAdditionalScreenshot(String.format("Poliza creada incialmente %s", PolizaProperties.idPoliza),
					"extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inicia la creación de una póliza para un producto, pero no culmina el proceso", page = "Resumen Entidad", functionality = "Emision")
	public static void crearNuevaPolizaAlternativo(String producto) {

		try {
			Events.clickButton(buttonNuevaPoliza);
			List<WebElement> productos = Events.getElementList(listOpciones);
			for (int i = 0; i < productos.size(); i++) {
				String opcion = productos.get(i).getText();
				if (opcion.contains(producto)) {
					productos.get(i).click();
					Utils.outputInfo("Se ha seleccionado la opción: " + opcion);
					PolizaProperties.producto = producto;
				}
			}

			Events.clickButton(buttonSi);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el canal de venta del producto", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarFechaPoliza(String Finicio) {

		try {
			loadingTIAGeneral();
			Events.sendKeys(inputFechaInicioCobertura, Finicio);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que el módulo \"Información de póliza\" esté desplegado.", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarModuloInformacionPolizaDesplegado() {
		try {
			loadingTIAGeneral();
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Información de póliza"));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el canal de venta del producto", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarCanalVenta(String canalVenta) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(selectCanalVenta, canalVenta);
			Utils.takeAdditionalScreenshot(String.format("Se ingresa el canal de venta %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el canal de venta del producto", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarClaseItem(String claseItem) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(SelectClaseItem, claseItem);

			Utils.takeAdditionalScreenshot(String.format("Se ingresa el canal de venta %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el canal de venta del producto", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarUnidadMoneda(String unidadMoneda) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByContainsText(SelectMontoMoneda, unidadMoneda);
			loadingTIAGeneral();
			Utils.takeAdditionalScreenshot(String.format("Se ingresa la unidad de moneda %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el canal de venta del producto", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarCodigoContador(String codigoContador) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(SelectCodigoContador, codigoContador);
			Utils.takeAdditionalScreenshot(String.format("Se ingresa el codigo contador %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea una nueva línea de póliza", page = "Manejo Póliza", functionality = "Emision")
	public static void crearNuevaLineaPoliza() {
		try {
			loadingTIAGeneral();
			Events.clickButton(buttonNuevaLineaPoliza);
			Events.clickButton(buttonAceptar);
			loadingTIAGeneral(60);
			String tituloObjeto = Events.getText(labelManejoObjeto);
			Utils.outputInfo("La sección actual es: " + tituloObjeto);
			if (!tituloObjeto.contains(PolizaProperties.producto)) {
				Validation.trueBooleanCondition((tituloObjeto.contains("Muerte Accidental")
						|| tituloObjeto.contains("Reembolso de Gastos Médicos")
						|| tituloObjeto.contains("Garantía extendida") || tituloObjeto.contains("Manejo de objetos")),
						"1. Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"1. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			} else {
				Validation.trueBooleanCondition(tituloObjeto.contains(PolizaProperties.producto),
						"2. Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"2. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			}

			validarDespliegueModulos(
					imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Información general del objeto"));
			String idObjeto = Events.getText(labelObjeto);
			PolizaProperties.idObjeto = idObjeto;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea una nueva línea de póliza", page = "Manejo Póliza", functionality = "Emision")
	public static void crearNuevaLineaPolizaFechaFinalizacion(String fechaFinalizacion) {
		try {
			loadingTIAGeneral();
			Events.clickButton(buttonNuevaLineaPoliza);
			loadingTIAGeneral(60);
			Events.sendKeys(inputFechaFinalizacion, fechaFinalizacion);
			loadingTIAGeneral(60);
			Events.clickButton(buttonAceptar);
			loadingTIAGeneral(60);
			String tituloObjeto = Events.getText(labelManejoObjeto);
			Utils.outputInfo("La sección actual es: " + tituloObjeto);
			if (!tituloObjeto.contains(PolizaProperties.producto)) {
				Validation.trueBooleanCondition((tituloObjeto.contains("Muerte Accidental")
						|| tituloObjeto.contains("Reembolso de Gastos Médicos")
						|| tituloObjeto.contains("Garantía extendida") || tituloObjeto.contains("Manejo de objetos")),
						"Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"1. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			} else {
				Validation.trueBooleanCondition(tituloObjeto.contains(PolizaProperties.producto),
						"Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"2. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			}

			validarDespliegueModulos(
					imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Información general del objeto"));
			String idObjeto = Events.getText(labelObjeto);
			PolizaProperties.idObjeto = idObjeto;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Crea una nueva línea de póliza", page = "Manejo Póliza", functionality = "Emision")
	public static void crearNuevaLineaPolizaConIdLineaPoliza(String idLineaPoliza) {
		try {
			loadingTIAGeneral();
			Events.clickButton(buttonNuevaLineaPoliza);
			loadingTIAGeneral();
			Events.selectByExactText(selectIDLineaPoliza, idLineaPoliza);
			Events.clickButton(buttonAceptar);
			loadingTIAGeneral(60);
			String tituloObjeto = Events.getText(labelManejoObjeto);
			Utils.outputInfo("La sección actual es: " + tituloObjeto);
			if (!tituloObjeto.contains(PolizaProperties.producto)) {
				Validation.trueBooleanCondition((tituloObjeto.contains("Muerte Accidental")
						|| tituloObjeto.contains("Reembolso de Gastos Médicos")
						|| tituloObjeto.contains("Garantía extendida") || tituloObjeto.contains("Manejo de objetos")),
						"1. Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"1. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			} else {
				Validation.trueBooleanCondition(tituloObjeto.contains(PolizaProperties.producto),
						"2. Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"2. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			}

			validarDespliegueModulos(
					imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Información general del objeto"));
			String idObjeto = Events.getText(labelObjeto);
			PolizaProperties.idObjeto = idObjeto;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Calcula la prima total de la póliza", page = "Manejo Objeto", functionality = "Emision")
	public static void calcularPrimaTotal() {

		try {
			loadingTIAGeneral();
			Events.scroll(buttonCalculate);
			loadingTIAGeneral();
			Events.clickButton(buttonCalculate);
			loadingTIAGeneral();
			String primaTotal = Events.getText(labelPrimaTotal);
			Utils.outputInfo("La prima total es: " + primaTotal);
			Validation.trueBooleanCondition(!primaTotal.equals("0,000000"),
					"La prima total se ha calculado correctamente", "La prima total no se ha calculado");
			PolizaProperties.primaSinDescuento = primaTotal;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Calcula la prima total de la póliza", page = "Manejo Objeto", functionality = "Emision")
	public static void calcularPrimaTotal(String primaCalculada) {

		try {
			loadingTIAGeneral();
			Events.scroll(buttonCalculate);
			loadingTIAGeneral();
			Events.clickButton(buttonCalculate);
			loadingTIAGeneral();
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(50));
			wait.until(ExpectedConditions
					.not(ExpectedConditions.attributeContains(By.xpath(labelPrimaTotal), "innerText", "0,000000")));
			String primaTotal = Events.getText(labelPrimaTotal);
			Utils.outputInfo("La prima total es: " + primaTotal);
			Validation.trueBooleanCondition(primaTotal.equals(primaCalculada),
					"La prima total se ha calculado correctamente", "La prima total no se ha calculado");
			PolizaProperties.primaSinDescuento = primaTotal;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Calcula la prima total de la póliza sin validación de cambio en la prima", page = "Manejo Objeto", functionality = "Emision")
	public static void calcularPrimaTotalAlternativo() {

		try {
			Events.clickButton(buttonCalculate);
			String primaTotal = Events.getText(labelPrimaTotal);
			Utils.outputInfo("La prima total es: " + primaTotal);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Guarda la prima calculada", page = "Manejo Objeto", functionality = "Emision")
	public static void guardarCambios() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGuardar);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Guarda la prima calculada", page = "Manejo Objeto", functionality = "Emision")
	public static void guardarItemCuenta() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGuardarItem);
			loadingTIAGeneral(60);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Guarda la prima calculada", page = "Manejo Objeto", functionality = "Emision")
	public static void publicarItem() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonPublicarItem);
			loadingTIAGeneral(60);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Se desplaza hasta el Manejo de póliza", page = "Manejo de Póliza", functionality = "Emision")
	public static void ingresarPolizaMaestra() {
		try {
			Events.clickButton(aPolizaV2);
			String tituloLineaPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloLineaPoliza);
			Validation.trueBooleanCondition(tituloLineaPoliza.contains(PolizaProperties.producto),
					"Se ha ingresado a la línea de póliza para el producto correcto: " + PolizaProperties.producto,
					"No se ha ingresado a la línea de póliza para el objeto de póliza correctamente");
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresa la Póliza Maestra", page = "Manejo de Póliza", functionality = "Emision")
	public static void ingresarIdPolizaMaestra() {
		try {
			Events.sendKeys(inputPolizaMaestra, PolizaProperties.idPolizaMaestra);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Se desplaza hasta la línea de póliza", page = "Manejo Línea de Póliza", functionality = "Emision")
	public static void ingresarLineaPoliza() {
		try {
			validarDespliegueModulos(
					imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Resumen de la línea de póliza"));
			Events.scroll(enlaceLineaPolizaEnPolizaMaestra);
			Events.clickButton(enlaceLineaPolizaEnPolizaMaestra);
			loadingTIAGeneral(60);
			String tituloLineaPoliza = Events.getText(labelManejoLineaPoliza);
			TransversalEvents.loadingTIAGeneral();
			Utils.outputInfo("La sección actual es: " + tituloLineaPoliza);
			if (!(PolizaProperties.producto == null)) {

				String lineaPoliza = "";
				if (PolizaProperties.producto.toLowerCase().contains("Accidentes Personales".toLowerCase())) {
					lineaPoliza = "Muerte Accidental";
				} else if (PolizaProperties.producto.toLowerCase().contains("Mascota Salud".toLowerCase())) {
					lineaPoliza = "Mascota";
				} else if (PolizaProperties.producto.toLowerCase().contains("Oncológico".toLowerCase())) {
					lineaPoliza = "Oncológico";
				} else if (PolizaProperties.producto.toLowerCase().contains("Soap".toLowerCase())) {
					lineaPoliza = "Soap";
				} else {
					lineaPoliza = PolizaProperties.producto;
				}
				Validation.trueBooleanCondition(tituloLineaPoliza.toLowerCase().contains(lineaPoliza.toLowerCase()),
						"Se ha ingresado a la línea de póliza para el producto correcto: " + lineaPoliza,
						String.format(
								"Se ha ingresado al la línea de póliza '%s' y se esperaba la línea de póliza del producto '%s'",
								tituloLineaPoliza.trim(), lineaPoliza));
				int inicio = tituloLineaPoliza.indexOf("-");
				int fin = tituloLineaPoliza.indexOf("-", inicio + 1);
				String idLineaPoliza = tituloLineaPoliza.substring(inicio + 2, fin - 1);
				PolizaProperties.idLineaPoliza = idLineaPoliza;
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Se desplaza hasta la línea de póliza", page = "Manejo Línea de Póliza", functionality = "Emision")
	public static void ingresarLineaPolizaV2() {
		try {
			loadingTIAGeneral();
			Events.clickButton(spanManejoLineaPolizaV2);
			String tituloLineaPoliza = Events.getText(labelManejoLineaPoliza);
			Utils.outputInfo("La sección actual es: " + tituloLineaPoliza);
			Validation.trueBooleanCondition(tituloLineaPoliza.contains("Manejo de líneas de pólizas -"),
					"Se ha ingresado a la línea de póliza para el producto correcto: " + PolizaProperties.producto,
					"No se ha ingresado a la línea de póliza para el objeto de póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Obtiene la línea de póliza", page = "Manejo Línea de Póliza", functionality = "Emision")
	public static void obtenerLineaPoliza() {
		try {

			String idLineaPoliza = Events.getText(labelNumeroLineaPoliza);
			PolizaProperties.idLineaPoliza = idLineaPoliza;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Agrega un código de descuento a la prima total", page = "Manejo Línea de póliza", functionality = "Emision")
	public static void agregarDescuento(String codigo) {

		try {
			Events.sendKeys(inputCodigoDescuento, codigo);
			Events.clickButton(buttonGuardar);
			loadingTIAGeneral();
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(50));
			wait.until(ExpectedConditions
					.attributeToBeNotEmpty(BaseTest.getDriver().findElement(By.xpath(inputPorcentajeDescuento)), "value"));
			String porcentajeDescuento = Events.getAtributte(inputPorcentajeDescuento, "value");
			Validation.trueBooleanCondition(!porcentajeDescuento.equalsIgnoreCase(""),
					"El código se ha aplicado correctamente, el porcentaje de descuento es: " + porcentajeDescuento,
					"El código no se ha aplicado correctamente");

			String precioConDescuento = Events.getText(labelPrecioPagado);
			Utils.outputInfo("La nueva prima con el descuento aplicado es: " + precioConDescuento);
			PolizaProperties.primaConDescuento = precioConDescuento;
			String primaCalculada = calcularDescuento(porcentajeDescuento);
			Validation.trueBooleanCondition(primaCalculada.contains(precioConDescuento),
					"El descuento se ha aplicado correctamente, la prima cambió de "
							+ PolizaProperties.primaSinDescuento + " a " + PolizaProperties.primaConDescuento,
					"El descuento no se ha aplicado correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Confirmar descuento realizado a la póliza", page = "Manejo Línea de póliza", functionality = "Emision")
	public static void confirmarDescuento() {

		try {
//			
			String tituloLineaPoliza = Events.getText(labelManejoLineaPoliza);
			Utils.outputInfo("La sección actual es: " + tituloLineaPoliza);
			Validation.trueBooleanCondition(tituloLineaPoliza.contains(PolizaProperties.producto),
					"Se ha ingresado a la línea de póliza para el producto correcto: " + PolizaProperties.producto,
					"No se ha ingresado a la línea de póliza para el objeto de póliza correctamente");
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(50));
			wait.until(ExpectedConditions
					.attributeToBeNotEmpty(BaseTest.getDriver().findElement(By.xpath(inputPorcentajeDescuento)), "value"));
			String porcentajeDescuento = Events.getAtributte(inputPorcentajeDescuento, "value");
			Validation.trueBooleanCondition(!porcentajeDescuento.equalsIgnoreCase(""),
					"El código se ha aplicado correctamente, el porcentaje de descuento es: " + porcentajeDescuento,
					"El código no se ha aplicado correctamente");

			Events.clickButton(buttonGuardar);
			TransversalEvents.loadingTIAGeneral();

			String precioConDescuento = Events.getText(labelPrecioPagado);
			Utils.outputInfo("La nueva prima con el descuento aplicado es: " + precioConDescuento);
			PolizaProperties.primaConDescuento = precioConDescuento;
			String precioSinDscto = Events.getText(LabelPrecioTarifa);
			PolizaProperties.primaSinDescuento = precioSinDscto;
			System.out.println("Valor prima sin descuento:" + PolizaProperties.primaSinDescuento);
			String primaCalculada = calcularDescuento(porcentajeDescuento);
			Validation.trueBooleanCondition(primaCalculada.contains(precioConDescuento),
					"El descuento se ha aplicado correctamente, la prima cambió de "
							+ PolizaProperties.primaSinDescuento + " a " + PolizaProperties.primaConDescuento,
					"El descuento no se ha aplicado correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Agrega un código de descuento a la prima total", page = "Manejo Línea de póliza", functionality = "Emision")
	public static String calcularDescuento(String porcentaje) {

		try {
			float primaTotal = Float.parseFloat(PolizaProperties.primaSinDescuento.replace(",", "."));
			float porcentajeNum = Float.parseFloat(porcentaje);
			float descuento = (primaTotal * (porcentajeNum / 100));
			float primaConDescuento = primaTotal - descuento;
			String formattedNumber = String.format("%.6f", primaConDescuento).replace(".", ",");
			return formattedNumber;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
			return null;
		}
	}

	@Context(description = "Completa la póliza según el estado", page = "Manejo Objeto", functionality = "Emision")
	public static void completarPoliza(String estado) {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonCompletar);
			loadingTIAGeneral();
			List<WebElement> productos = Events.getElementList(listOpciones);
			for (int i = 0; i < productos.size(); i++) {
				String opcion = productos.get(i).getText();
				if (opcion.toLowerCase().contains(estado.toLowerCase())) {
					productos.get(i).click();
					Utils.outputInfo("Se ha seleccionado la opción: " + opcion);
					Utils.takeAdditionalScreenshot("opcionSeleccionada", "extra");
					PolizaProperties.estado = estado;
					break;
				} else if (i == productos.size() - 1) {
					Utils.eventFailed(String.format("No se encontro el elemento indicado: %s", estado));
					Utils.takeAdditionalScreenshot("listaOpciones", "extra");
				}
			}

			Events.clickButton(buttonSi);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Quita la modal para visualizar el mensaje de error", page = "Manejo Objeto", functionality = "Emision")
	public static void quitarModalCompletarPoliza() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonCerrar);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Quita la modal de Selección de producto para visualizar el mensaje de error", page = "Manejo Objeto", functionality = "Emision")
	public static void quitarModalSeleccionarProducto() {

		try {
			Events.clickButton(buttonCerrarSeleccion);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida la aparición del mensaje que no permite la contratación por parte de un menor de edad", page = "Manejo Objeto", functionality = "Emision")
	public static void validarMensajeContratanteMenorEdad() {

		try {
			String mensaje = Events.getText(labelMensajeContratanteMenorEdad);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("18 años"), "El mensaje ha aparecido correctamente",
					"No ha aparecido el mensaje correcto");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida la aparición del mensaje que no permite la contratación por parte de un fallecido", page = "Manejo Objeto", functionality = "Emision")
	public static void validarMensajeContratanteFallecido() {

		try {
			String mensaje = Events.getText(labelMensajeContratanteFallecido);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("no está vivo"), "El mensaje ha aparecido correctamente",
					"No ha aparecido el mensaje correcto");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "No aceptar los documentos de la póliza y cancelar la transacción.", page = "Manejo Objeto", functionality = "Emision")
	public static void cancelarPoliza() {

		try {
			Events.clickButton(buttonCancelarCompletarPoliza);
			loadingTIAGeneral();
			Events.clickButton(buttonSiCancelarTransaccion);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida la aparición del mensaje que no permite la contratación de una entidad errónea", page = "Manejo Objeto", functionality = "Emision")
	public static void validarMensajeContratanteErroneo() {

		try {
			String mensaje = Events.getText(labelMensajeContratanteErroneo);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("Sólo los particulares"),
					"El mensaje ha aparecido correctamente", "No ha aparecido el mensaje correcto");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida la aparición del mensaje que no permite la contratación de una persona inahbilitada", page = "Manejo Objeto", functionality = "Emision")
	public static void validarMensajeContratanteInahabilitado() {

		try {
			String mensaje = Events.getText(labelMensajeContratanteInhabilitado);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("está suspendido"),
					"El mensaje ha aparecido correctamente", "No ha aparecido el mensaje correcto");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Acepta documentos de póliza", page = "Manejo Objeto", functionality = "Emision")
	public static void aceptarDocumentosPoliza() {

		try {
			TransversalEvents.loadingTIAGeneral(120);
			List<String> doc = new ArrayList<>();
			boolean sinDoc = false;
			int i = 0;
			String tituloPagina = Events.getText(labelModalDocumentosPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPagina);
			Validation.trueBooleanCondition(tituloPagina.contains("Documentos de póliza"),
					"Se ha ingresado al resumen de la entidad correctamente: " + tituloPagina,
					"No se ha ingresado al resumen de la entidad");

			int inicio = tituloPagina.indexOf("-");
			int fin = tituloPagina.length();
			String idPoliza = tituloPagina.substring(inicio + 2, fin);
			if (idPoliza.contains("-")) {
				Utils.outputInfo("Es una póliza Normal: " + idPoliza);
				PolizaProperties.idPoliza = idPoliza;
			} else {
				Utils.outputInfo("Es una póliza Maestra: " + idPoliza);
				PolizaProperties.idPolizaMaestra = idPoliza;
			}

			// Recorrera y guardara todos los documentos generados, si no hay doc saldra del
			// bucle
			while (true) {
				String xpath = String.format(labelDocumentoPoliza.replace("num", "%d"), i);
				List<WebElement> docEncontrados = getDriver().findElements(By.xpath(xpath));

				if (docEncontrados.isEmpty()) {
					sinDoc = true;
					break; // Si no se encuentran más elementos, se sale del bucle
				}

				for (WebElement elemento : docEncontrados) {
					doc.add(elemento.getText());
				}

				i++; // Incrementa el índice para la siguiente iteración
			}

			if (sinDoc) {
				Utils.outputInfo("Producto no posee Documentos al finalizar el proceso");
			} else {
				// Se validara que no se repita el documento
				Set<String> uniqueElements = new HashSet<>();

//		        doc.add("Emisión");
				String firstDoc = doc.get(0);

				for (String element : doc) {
					if (!uniqueElements.add(element)) {
						Utils.outputInfo(String.format("Existe más de un Documento de %s", firstDoc));
						Utils.eventFailed(String.format("Error!!! Existe más de un Documento de %s", firstDoc));
					}
				}
			}

			Utils.takeAdditionalScreenshot("polizaSinRegistros", "extra");
			Events.clickButton(buttonAceptaCompletarPoliza);

			loadingTIAGeneral(120);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verifica que los datos del contratantes correspondan con la entidad seleccionada", page = "Manejo Objeto", functionality = "Emision")
	public static void BusquedaTransversalPoliza() {

		try {
			Events.clickButton(BusquedaTransversal.replace("id-poliza", PolizaProperties.idPoliza));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verifica que los datos del contratantes correspondan con la entidad seleccionada", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarDatosContratante() {

		try {
			String nombreContratante = Events.getValue(inputNombreContratante);
			Validation.trueBooleanCondition(nombreContratante.equalsIgnoreCase(EntidadProperties.nombreContratante),
					"El contratante corresponde con la entidad seleccionada: " + nombreContratante,
					"El contratante no corresponde con la entidad seleccionada, se esperaba: "
							+ EntidadProperties.nombreContratante + ", y se encontró: " + nombreContratante);
			String rutContratante = Events.getValue(inputRutContratante);
			Utils.outputInfo("El rut del contratante es: " + rutContratante);
			EntidadProperties.rutContratante = rutContratante;
			String fechaNacimiento = Events.getValue(inputNacimientoContratante);
			Utils.outputInfo("La fecha de nacimiento del contratante es: " + fechaNacimiento);
			EntidadProperties.fechaNacimientoContratante = fechaNacimiento;

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verifica que los datos del asegurado sean los mismos que los del contratante", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarMismosDatosContratanteAsegurado() {

		try {
			String nombreAsegurado = Events.getValue(inputNombreAsegurado);
			Validation.trueBooleanCondition(nombreAsegurado.equalsIgnoreCase(EntidadProperties.nombreContratante),
					"El asegurado corresponde con el contratante: " + nombreAsegurado,
					"El asegurado no es igual al contratante, se esperaba: " + EntidadProperties.nombreContratante
							+ ", y se encontró: " + nombreAsegurado);
			rutAsegurado = Events.getValue(inputRutAsegurado);
			Utils.outputInfo("El rut del asegurado es: " + rutAsegurado);
			String fechaNacimiento = Events.getValue(inputNacimientoAsegurado);
			Utils.outputInfo("La fecha de nacimiento del asegurado es: " + fechaNacimiento);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea siniestro para una póliza vigente", page = "Siniestro", functionality = "Siniestros")
	public static void crearSiniestro() {

		try {
			Events.clickButton(buttonCrearSiniestro);
			String tittle = Events.getText(labelPaginaSiniestro);
			Utils.outputInfo("La sección actual es: " + tittle);
			Validation.trueBooleanCondition(tittle.contains("Crear siniestro"),
					"Se ha ingresado a la página para la creación de un siniestro",
					"No se ha ingresado correctamente para crear un siniestro");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el producto a siniestrar", page = "Manejo de objetos")
	public static void seleccionarProductoDaniadoSiniestrar() {
		try {
			loadingTIAGeneral();
			Events.clickButton(spanOpcionSiniestrar.replace("TextoBuscado", "Comercial"));
			String tituloDanio = Events.getText(labelQueFueDaniado);
			Validation.trueBooleanCondition(tituloDanio.contains("dañado"),
					"Se ingreso correctamente a la creación del siniestro", "No se ingreso a la creación de siniestro");
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

//	@Context(step = "Se genera un siniestro en estado abierto", page = "Siniestro" , functionality = "Siniestro")
//	public static void generarSiniestro() {
//		
//		try {
//			Thread.sleep(1000);
//			Events.clickButton(buttonGenerarSiniestro);
//			String tittle = Events.getText(labelSiniestroAbierto);
//			Utils.outputInfo("La sección actual es: " + tittle);
//			int inicio = tittle.indexOf("-");
//			int fin = tittle.indexOf("(", inicio+ 1);
//			String idSiniestro = tittle.substring(inicio+2, fin-1);
//			Validation.trueBooleanCondition(tittle.contains("(Notificado)"),
//					"Se ha generado el siniestro correctamente con ID: " + idSiniestro, "No se ha generado el siniestro de forma correcta");
//
//		} catch (Exception e) {
//			Utils.eventFailed(e.getMessage());
//		}
//	}

//	@Context(step = "Se genera un siniestro en estado abierto", page = "Siniestro" , functionality = "Siniestro")
//	public static void generarSiniestro(String nombProducto) {
//		
//		try {
//			TransversalEvents.loadingTIAGeneral();
//			Events.clickButton(buttonGenerarSiniestro);
//			Events.clickButton(buttonBarraLateral);
//			String tittle = Events.getText(labelNotificacionSiniestro);
//			String tittle2 = Events.getText(labelSiniestro.replace("Nom_producto", nombProducto));
//			int inicio = tittle2.indexOf("-");
//			int fin = tittle2.indexOf("(", inicio+ 1);
//			String idSiniestro = tittle2.substring(inicio+2, fin-1);
//			Validation.trueBooleanCondition(tittle.contains("Notificación"),
//					"Se ha generado el siniestro correctamente con ID: " + idSiniestro, "No se ha generado el siniestro de forma correcta");
//		} catch (Exception e) {
//			Utils.eventFailed(e.getMessage());
//		}
//	}

	@Context(step = "Se intenta generar un siniestro pero no se culmina por errores controlados ", page = "Siniestro", functionality = "Siniestro")
	public static void generarSiniestroFallido() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonGenerarSiniestro);
			String mensaje = Events.getText(labelSiniestroError);
			Utils.outputInfo("Se ha mostrado el mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("Error"), "Se ha mostrado el mensaje de Error",
					"No se ha mostrado el mensaje de Error");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	// enviar al grupo

	@Context(description = "Selecciona el lugar donde ocurrió el siniestro", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarLugarSiniestro(String lugar) {

		try {
			loadingTIAGeneral();
			Events.selectByContainsText(selectLugar, lugar);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el tipo de informante", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarTipoInformante(String tipoInform) {

		try {
			loadingTIAGeneral();
			Events.selectByContainsText(selectInformante, tipoInform);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inserta el nombre del informante", page = "Siniestro", functionality = "Siniestros")
	public static void insertarNombInformante(String nombInform) {

		try {
			Events.sendKeys(inputNombInform, nombInform);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el tipo de denuncia", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarTipoDenuncia(String tipoDenuncia) {

		try {
			loadingTIAGeneral();
			Events.selectByContainsText(selectTipoDenuncia, tipoDenuncia);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Presionar el botón aprobar del asistente de preguntas.", page = "Manejo de objetos")
	public static void presionarBotonAprobarPreguntaAsistente() {
		try {
			int vecesClic = 0;
			int maxVecesClic = 3;
			while (Events.isVisible(buttonAceptarPreguntaAsistente) && vecesClic < maxVecesClic) {
				loadingTIAGeneral();
				Utils.outputInfo("Aprobando pregunta del asistente sin ingresar información en el campo.");
				Events.clickButton(buttonAceptarPreguntaAsistente);
				loadingTIAGeneral();
				vecesClic++;
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Inserta descripción del hecho", page = "Siniestro", functionality = "Siniestros")
	public static void insertarDescripHecho(String descripHecho) {

		try {
			Events.sendKeys(inputDescripHecho, descripHecho);
			Validation.trueBooleanCondition(!(descripHecho.equals("")),
					"Se ha introducido correctamente la descripción del hecho",
					"No se ha introducido descripción del hecho");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Guarda los datos de reserva", page = "Siniestro", functionality = "Siniestros")
	public static void guardarReserva() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGuardarReserva);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Busca el liquidador designado para la atención del siniestro", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarLiquidador(String idLiquidador) {

		try {

			Events.clickButton(imgBuscarLiquidador);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarLiquidador.replace("IDLiquidador", idLiquidador));
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAceptarLiquidador);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inserta fecha del siniestro", page = "Siniestro", functionality = "Siniestros")
	public static void insertarFechaSiniestro(String fechaSiniestro) {

		try {
			Events.sendKeys(inputFechaSiniestro, fechaSiniestro);
			Validation.trueBooleanCondition(!(fechaSiniestro.equals("")),
					"Se ha introducido correctamente la fecha del siniestro",
					"No se ha introducido fecha del siniestro");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresa la fecha de hoy como la 'Fecha de Siniestro'", page = "Manejo de objetos")
	public static void ingresarFechaSiniestro() {
		try {
			loadingTIAGeneral();
			Date fechaActual = new Date();
			SimpleDateFormat formatDateFormat = new SimpleDateFormat("dd.MM.yyyy H:mm");
			String fechaFormatedaString = formatDateFormat.format(fechaActual);
			Events.sendKeys(inputFechaSiniestro, fechaFormatedaString, Keys.ENTER);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresa la fecha de hoy como la 'Fecha de denuncia'", functionality = "Siniestro")
	public static void ingresarFechaDenunciaHoy() {
		try {
			loadingTIAGeneral();
			Date fechaActual = new Date();
			SimpleDateFormat formatDateFormat = new SimpleDateFormat("dd.MM.yyyy H:mm");
			String fechaFormatedaString = formatDateFormat.format(fechaActual);
			Events.sendKeys(elementGenerico.replace("TextoBuscado", "Fecha de denuncia"), fechaFormatedaString,
					Keys.ENTER);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Inserta fecha venta", page = "Garantia extendida", functionality = "EW")
	public static void insertarFechaVenta(String fechaVenta) {

		try {
			Events.sendKeys(inputFechaVenta, fechaVenta);
			Validation.trueBooleanCondition(!(fechaVenta.equals("")),
					"Se ha introducido correctamente la fecha de venta", "No se ha introducido fecha de venta");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Activar casilla 'Fecha Aproximada'", page = "Manejo de objetos")
	public static void seleccionarFechaAproximadaTrans() {
		try {
			Events.clickButton(checkBoxFechaEstimadaTrans);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Inserta fecha de denuncia", page = "Siniestro", functionality = "Siniestros")
	public static void insertarFechaDenuncia(String fechaDenuncia) {

		try {
			Events.sendKeys(inputFechaDenuncia, fechaDenuncia);
			Validation.trueBooleanCondition(!(fechaDenuncia.equals("")),
					"Se ha introducido correctamente la fecha de denuncia", "No se ha introducido fecha de denuncia");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Busca póliza vigente", page = "Siniestro", functionality = "Siniestros")
	public static void buscarPoliza(String idPoliza) {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonBusquedaPolizaAlt);
			loadingTIAGeneral();
			Events.sendKeys(inputIdLineaPoliza, idPoliza);
			getDriver().findElement(By
					.xpath("//label[contains(text(),'Mostrar información del objeto acordeón')]//following::input[3]"))
					.sendKeys(Keys.ENTER);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelPolizaSiniestrar.replace("id-poliza", idPoliza));
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAceptaSeleccion);
			TransversalEvents.loadingTIAGeneral();

			String objeto = Events.getAtributte(inputObjeto, "value");
			Validation.trueBooleanCondition(!objeto.equalsIgnoreCase(""),
					"La póliza se ha seleccionado correctamente, el No.Objeto es: " + objeto,
					"La póliza no se ha seleccionado correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Busca póliza vigente", page = "Siniestro", functionality = "Siniestros")
	public static void buscarPolizaAlt() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonBusquedaPolizaAlt);
			loadingTIAGeneral();
			Events.limpiarCajaDeTexto(fechaSiniestroLimpiarCaja);
			loadingTIAGeneral();
			Events.sendKeys(inputIdLineaPoliza, PolizaProperties.idPoliza);
			getDriver().findElement(By
					.xpath("//label[contains(text(),'Mostrar información del objeto acordeón')]//following::input[3]"))
					.sendKeys(Keys.ENTER);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelPolizaSiniestrar.replace("id-poliza", PolizaProperties.idPoliza));
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAceptaSeleccion);
			TransversalEvents.loadingTIAGeneral();

			String objeto = Events.getAtributte(inputObjeto, "value");
			Validation.trueBooleanCondition(!objeto.equalsIgnoreCase(""),
					"La póliza se ha seleccionado correctamente, el No.Objeto es: " + objeto,
					"La póliza no se ha seleccionado correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Crea nuevo item", page = "Siniestro", functionality = "Siniestro")
	public static void crearNuevoItem() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonNuevoItem);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona tipo de ítem dentro de las opciones disponibles", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarTipoItem(String tipoItem) {

		try {
			loadingTIAGeneral();
			Events.selectByContainsText(elementGenerico.replace("TextoBuscado", "Tipo de item"), tipoItem);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona tipo de sub ítem dentro de las opciones disponibles", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarTipoSubItem(String tipoSubItem) {

		try {
			loadingTIAGeneral();
			Events.selectByContainsText(elementGenerico.replace("TextoBuscado", "Tipo de sub item"), tipoSubItem);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el receptor del pago en el cual se basa el ítem o reserva", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarReceptor(String idReceptor) {

		try {
			Events.clickButton(buttonBusquedaReceptor);
			Events.sendKeys(inputIdReceptor, idReceptor);
			Events.enter(inputIdReceptor);
			Events.clickButton(labelNombreReceptor);
			String nombreReceptor = Events.getText(labelNombreReceptor);
			String rolReceptor = Events.getText(labelRolReceptor);
			Events.clickButton(buttonAceptaSeleccion);
			loadingTIAGeneral();
			Utils.outputInfo("Se ha seleccionado el Receptor con Id: " + idReceptor + ", Nombre: " + nombreReceptor
					+ " y Rol: " + rolReceptor);
			validarNombreReceptor(nombreReceptor);
			validarRolReceptor(rolReceptor);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inserta la reserva que corresponda según el tipo de siniestro", page = "Siniestro", functionality = "Siniestros")
	public static void insertarMontoReserva(String montoReserva) {

		try {
			loadingTIAGeneral();
			Events.sendKeys(elementGenerico.replace("TextoBuscado", "Reserva"), montoReserva);
			Validation.trueBooleanCondition(!montoReserva.equals(""),
					"Se ha introducido correctamente el valor de la reserva",
					"No se ha introducido correctamente el valor de la reserva");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida que se cargue automáticamente el nombre del Receptor", page = "Siniestro", functionality = "Siniestros")
	public static void validarNombreReceptor(String nombreReceptor) {

		try {

			String nombre = Events.getAtributte(inputNombreReceptor, "value");
			Validation.trueBooleanCondition(nombre.equalsIgnoreCase(nombreReceptor),
					"Se ha cargado correctamente el nombre del receptor",
					"No se ha cargado correctamente el nombre del receptor");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida que se cargue automáticamente el rol del Receptor", page = "Siniestro", functionality = "Siniestros")
	public static void validarRolReceptor(String rolReceptor) {

		try {

			String rol = Events.getAtributte(inputRolReceptor, "value");
			Validation.trueBooleanCondition(rol.equalsIgnoreCase(rolReceptor),
					"Se ha cargado correctamente el rol del receptor",
					"No se ha cargado correctamente el rol del receptor");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Se ingresa a la página para crear una reserva", page = "Siniestro", functionality = "Siniestro")
	public static void crearReserva() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonReserva);
			loadingTIAGeneral();
			String tittle = Events.getText(labelItems);
			Utils.outputInfo("La sección actual es: " + tittle);
			Validation.trueBooleanCondition(tittle.contains("Items"),
					"Se ha generado ingresado a la página para la creación de una reserva",
					"No se ha ingresado correctamente a la página de creación de reserva");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida que se cargue automáticamente el id del Liquidador", page = "Siniestro", functionality = "Siniestros")
	public static void validarIdLiquidador(String idLiquidador) {

		try {

			String nombre = Events.getAtributte(inputIdLiquidReceptor, "value");
			Validation.trueBooleanCondition(nombre.equalsIgnoreCase(idLiquidador),
					"Se ha cargado correctamente el Id del liquidador",
					"No se ha cargado correctamente el Id del liquidador");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona fecha de siniestro", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarFechaSiniestro() {
		try {
			Events.clickButton(buttonSeleccionarFechaSiniestro);
			Events.clickButton(buttonFechaSuperiorDiaActual);
			Events.clickButton(buttonAceptarFecha);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona fecha de denuncia", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarFechaDenuncia() {

		try {
			Events.clickButton(buttonSeleccionarFechaDenuncia);
			Events.clickButton(buttonFechaSuperiorDiaActual);
			Events.clickButton(buttonAceptarFecha);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida mensaje de error en Fecha de siniestro superior al día actual", page = "Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaSiniestroSuperiorDiaActual() {
		try {
			loadingTIAGeneral();
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(msgErrorFechaSiniestroSuperiorDiaActual)));
			String mensaje = Events.getText(msgErrorFechaSiniestroSuperiorDiaActual);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("fecha del siniestro"),
					"El mensaje ha aparecido correctamente ", "No  ha aparecido el mensaje correcto");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida mensaje de error en Fecha de denuncia superior al día actual", page = "Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaDenunciaSuperiorDiaActual() {

		try {

			String mensaje = Events.getText(msgErrorFechaDenunciaSuperiorDiaActual);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("Fecha de denuncia"),
					"El mensaje ha aparecido correctamente ", "No  ha aparecido el mensaje correcto");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida mensaje de error en Fecha de siniestro superior que fecha de denuncia", page = "Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaSiniestroSuperiorFechaDenuncia() {
		try {
			String mensaje = Events.getText(msgErrorFechaSiniestroSuperiorDenuncia);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("Fecha del siniestro"),
					"El mensaje ha aparecido correctamente ", "No  ha aparecido el mensaje correcto");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida mensaje de error en Fecha de siniestro en blanco", page = "Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaSiniestroEnBlanco() {
		try {
			String mensaje = Events.getText(msgErrorFechaSiniestroEnBlanco);
			Utils.outputInfo("Ha aparecido el siguiente mensaje: " + mensaje);
			Validation.trueBooleanCondition(mensaje.contains("Debe introducir"),
					"El mensaje ha aparecido correctamente ", "No  ha aparecido el mensaje correcto");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona un gestor de siniestro", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarGestor(String gestor) {

		try {

			Events.sendKeys(inputIdGestor, gestor);
			Events.enter(inputIdGestor);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Se genera un siniestro en estado abierto", page = "Siniestro", functionality = "Siniestro")
	public static void generarSiniestro() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGenerarSiniestro);
			loadingTIAGeneral();
			Events.clickButton(buttonBarraLateral);
			loadingTIAGeneral();
			String tittle = Events.getText(labelNotificacionSiniestro);
			Events.clickButton(LabelDetalleSiniestro);
			String tittle2 = Events.getValue(labelSiniestro2);
			int inicio = tittle2.indexOf("-");
			int fin = tittle2.indexOf("(", inicio + 1);
			String idSiniestro = tittle2.substring(inicio + 2, fin - 1);
			Validation.trueBooleanCondition(tittle.contains("Notificación"),
					"Se ha generado el siniestro correctamente con ID: " + idSiniestro,
					"No se ha generado el siniestro de forma correcta");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Se genera un siniestro en estado abierto", page = "Siniestro", functionality = "Siniestro")
	public static void generarSiniestroAlt() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGenerarSiniestro);
			loadingTIAGeneral();
			loadingTIAGeneral();
			verificaBitacoraDesplegada();
			String tittle = Events.getText(labelNotificacionSiniestro);
			Events.clickButton(LabelDetalleSiniestro);
			loadingTIAGeneral(90);
			String tittle2 = Events.getValue(labelSiniestro2);
			String idSiniestro = tittle2;// tittle2.substring(inicio + 2, fin - 1);
			Validation.trueBooleanCondition(tittle.contains("Notificación"),
					"Se ha generado el siniestro correctamente con ID: " + idSiniestro,
					"No se ha generado el siniestro de forma correcta");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Se genera un siniestro en estado abierto", page = "Siniestro", functionality = "Siniestro")
	public static void generarSiniestro(String nombEvento) {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGenerarSiniestro);
			loadingTIAGeneral();
			verificaBitacoraDesplegada();
			loadingTIAGeneral();
			String tittle = Events.getText(labelNotificacionSiniestro);
			String tittle2 = Events.getText(labelSiniestro.replace("Nom_producto", nombEvento));
			int inicio = tittle2.indexOf("-");
			int fin = tittle2.indexOf("(", inicio + 1);
			String idSiniestro = tittle2.substring(inicio + 2, fin - 1);
			Validation.trueBooleanCondition(tittle.contains("Notificación"),
					"Se ha generado el siniestro correctamente con ID: " + idSiniestro,
					"No se ha generado el siniestro de forma correcta");
			Utils.takeAdditionalScreenshot(String.format("Se genera siniestro con el tipo de evento %s.", tittle2),
					"extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPoliza() {

		try {
			Events.sendKeys(inputBusqueda, PolizaProperties.idPoliza);
			Events.clickButton(listOpciones);
			Robot robot2 = new Robot();
			robot2.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1500);
			Events.scroll(buttonNuevoScroll);
			Thread.sleep(2000);
			String tituloPaginaPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPaginaPoliza);
			Validation.trueBooleanCondition(tituloPaginaPoliza.contains(PolizaProperties.idPoliza),
					"Se ha ingresado a la póliza correcta: " + PolizaProperties.idPoliza,
					"No se ha ingresado a la póliza correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ir a página Administración", page = "Administración")
	public static void cargarPaginaAdmin() {
		try {
			Events.clickButton(buttonAdministracion);
			loadingTIAGeneral();
			String paginaAdmin = Events.getText(labelPaginaAdmin);
			Validation.trueBooleanCondition(paginaAdmin.equalsIgnoreCase("Administración"),
					"Se ha cargado correctamente la página Administración",
					"No se ha cargado la página de Administración");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona de manera tranversal una sección", page = "Configuración")
	public static void cargarSeccionTranversal(String nombreSeccion) {
		try {
			Events.clickButton(buttonConfiguracion.replace("nombre-seccion", nombreSeccion));
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Despliega de manera tranversal los elementos aninados", page = "Configuración")
	public static void despliegaSeccionTranversalConfiguracion(String nombreSeccion) {
		try {

			loadingTIAGeneral(5);

			boolean rsValidacionBoolean = validarElementoPantalla(
					buttonConfiguracionTranversal.replace("nombre-seccion", nombreSeccion));

			if (rsValidacionBoolean == true) {
				Events.clickButton(buttonConfiguracionTranversal.replace("nombre-seccion", nombreSeccion));
				loadingTIAGeneral();
			} else {
				Utils.outputInfo(String.format("El elemento %s ya se encontraba desplegado", nombreSeccion));
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());

		}
	}

	@Context(description = "Despliega filtro", page = "Configuración")
	public static void despliegaFiltro() {
		try {

			loadingTIAGeneral(5);

			boolean rsValidacionBoolean = validarElementoPantalla(buttonFiltroTranversal);

			if (rsValidacionBoolean == true) {
				Events.clickButton(buttonFiltroTranversal);
				loadingTIAGeneral();
			} else {
				Utils.outputInfo(String.format("El filtro ya se encuentra desplegado"));
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());

		}
	}

	@Context(description = "Ingresa valor de producto a buscar dentro del buscador en Consitencia de Productos", page = "Configuración")
	public static void ingresaProducto(String producto) {
		try {

			Events.sendKeys(inputBusquedaProducto, producto, Keys.ENTER);
			loadingTIAGeneral(5);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());

		}
	}

	@Context(description = "Ingresa valor de producto a buscar dentro del buscador en Consitencia de Productos", page = "Configuración")
	public static void valoresDelDiv() {
		try {

			WebElement tabla = BaseTest.getDriver()
					.findElement(By.xpath("//div[contains(@id,'ProductVersionLogDetailsTab::db')]"));

			List<WebElement> filas = tabla.findElements(By.xpath(".//tr[@role='row']"));

			for (int i = 0; i < filas.size(); i++) {
				filas = tabla.findElements(By.xpath(".//tr[@role='row']"));
				WebElement fila = filas.get(i);

				if (!fila.findElements(By.xpath(".//input")).isEmpty()
						|| !fila.findElements(By.xpath(".//select")).isEmpty()) {

					System.out.println("---- Fila Editable encontrada (número " + (i + 1) + ") ----");

					List<WebElement> celdas = fila.findElements(By.xpath(".//td"));

					for (int j = 0; j < celdas.size(); j++) {
						celdas = fila.findElements(By.xpath(".//td"));
						WebElement celda = celdas.get(j);

						String valor = "";
						if (!celda.findElements(By.xpath(".//input")).isEmpty()) {
							valor = celda.findElement(By.xpath(".//input")).getAttribute("value").trim();
						} else if (!celda.findElements(By.xpath(".//select")).isEmpty()) {
							WebElement select = celda.findElement(By.xpath(".//select"));
							// valor = new Select(select).getFirstSelectedOption().getText().trim();
						} else if (!celda.findElements(By.xpath(".//span")).isEmpty()) {
							valor = celda.findElement(By.xpath(".//span")).getText().trim();
						}

						System.out.print(valor + " | ");
					}
					System.out.println();
				}
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());

		}
	}

	@Context(description = "Ir a página Buscar item Cuenta", page = "Administración")
	public static void cargarPaginaBuscarItemCuenta() {
		try {
			Events.clickButton(buttonCuenta);
			loadingTIAGeneral();
			Events.clickButton(buttonCuenta2);
			loadingTIAGeneral();
			Events.clickButton(buttonCuenta3);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Buscar número de Item de Cuenta para factura de pago de cuota", page = "Administración")
	public static void buscarItem() {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputIdPoliza, PolizaProperties.idPoliza);
			loadingTIAGeneral();
			Events.clickButton(buttonBuscarItemCuenta);
			loadingTIAGeneral();
			PolizaProperties.noCuentaPagoCuota = Events.getText(labelNoCuenta);
			PolizaProperties.itemCuentaPagoCuota = Events.getText(labelItemCuenta);
			PolizaProperties.estadoPago = Events.getText(labelEstadoPago);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar item para pago ", page = "Administración")
	public static void seleccionarItemPagar() {
		try {
			Events.clickButton(buttonPagosCobros);
			loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarItemPagar);
			loadingTIAGeneral();
			Events.clickButton(buttonContinuar);
			loadingTIAGeneral();
			Events.selectByContainsText(selectMetodosPago, "CUPONERA Cuponera Digital");
			Events.sendKeys(inputNoCuenta, PolizaProperties.noCuentaPagoCuota);
			Events.clickButton(buttonItemCuenta);
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputItemCuenta, PolizaProperties.itemCuentaPagoCuota);
			getDriver().findElement(By.xpath("(//input[contains(@id,'AccItemNoLovPC') and contains(@class,'x25')])[1]"))
					.sendKeys(Keys.ENTER);
			loadingTIAGeneral();
			Events.clickButton(labelItemCuentaSeleccionar.replace("no-item", PolizaProperties.itemCuentaPagoCuota));
			Events.clickButton(buttonAceptar1);
			loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarPago);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar que se haya seleccionado el item de cuenta para realizar el pago", page = "Administración")
	public static void validarEstadoItemSeleccionado() {
		try {
			TransversalEvents.loadingTIAGeneral();
			buscarItem3();
			Validation.trueBooleanCondition(PolizaProperties.estadoPago.contains("2"),
					"Se ha seleccionado el item de cuenta para realizar pago  ",
					"No se ha seleccionado el item de cuenta");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inicia el ajuste de una Póliza para realizar cancelaciones", page = "Manejo Póliza", functionality = "Cancelación")
	public static void ajustarPolizaTransversal(String tipoEndoso) {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonAjustartransversal);
			loadingTIAGeneral();
			Events.selectByContainsText(selectTipoEndosoTransversal, tipoEndoso);
			loadingTIAGeneral();
			Events.clickButton(buttonAceptaAjusteTransversal);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Completar el endoso de una Póliza sin modificar la fecha", page = "Manejo de objetos", functionality = "Endoso")
	public static void iniciarAjusteObjetoFechaHoy() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonAjustartransversal);
			loadingTIAGeneral();
			Events.clickButton(buttonFechaAjusteObjeto);
			Events.clickButton(buttonAumentarAnnoPoliza);
			Events.clickButton(buttonSeleccionarDia);
			loadingTIAGeneral();
			Events.clickButton(buttonAceptaAjusteTransversal);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Buscar número de Item de Cuenta para factura de pago de cuota", page = "Administración")
	public static void buscarItem3() {
		try {
			Events.clickButton(buttonCuenta3);
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputIdPoliza, PolizaProperties.idPoliza);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonBuscarItemCuenta);
			TransversalEvents.loadingTIAGeneral();
			PolizaProperties.noCuentaPagoCuota = Events.getText(labelNoCuenta);
			PolizaProperties.itemCuentaPagoCuota = Events.getText(labelItemCuenta);
			PolizaProperties.estadoPago = Events.getText(labelEstadoPago);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crear pago para item seleccionado", page = "Administración")
	public static void crearPago() {
		try {
			Events.clickButton(buttonCrearPagoCobros);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonContinuar);
			TransversalEvents.loadingTIAGeneral();
			Events.selectByContainsText(selectMetodosPago, "CUPONERA Cuponera Digital");
			Events.sendKeys(inputNoCuenta, PolizaProperties.noCuentaPagoCuota);
			Events.sendKeys(inputItemCuenta2, PolizaProperties.itemCuentaPagoCuota);
			Thread.sleep(3000);
			Events.clickButton(buttonCrearPago);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Buscar número de Item de Cuenta para factura de pago de cuota", page = "Administración")
	public static void buscarItem2() {
		try {
			Events.clickButton(buttonCuenta3);
			// Events.clickButton(buttonContinuar);
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputIdPoliza, PolizaProperties.idPoliza);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonBuscarItemCuenta);
			TransversalEvents.loadingTIAGeneral();
			PolizaProperties.noCuentaPagoCuota = Events.getText(labelNoCuenta);
			PolizaProperties.itemCuentaPagoCuota = Events.getText(labelItemCuenta);
			PolizaProperties.estadoPago = Events.getText(labelEstadoPago);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar monto revertido", page = "Administración")
	public static void validarMontoRevertido() {
		try {
			String montorevertido = Events.getText(labelMontorevertido);
			Validation.trueBooleanCondition(montorevertido.contains("-"), "Se ha revertido el pago correctamente",
					"No se ha realizado la reversión del pago");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar que se haya realizado el pago correctamente", page = "Administración")
	public static void validarEstadoEnviadoSistemaPago() {
		try {
			buscarItem3();
			Validation.trueBooleanCondition(PolizaProperties.estadoPago.contains("3"),
					"Se ha enviado el pago del a sistema correctamente", "No se ha enviado el pago");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPolizaMA() {

		try {
			Events.clickButton(buttonAmpliarVentanaPolizas);
			loadingTIAGeneral();
			Events.clickButton(aPolizav3.replace("id-poliza", PolizaProperties.idPoliza));
			loadingTIAGeneral();
			String tituloPaginaPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPaginaPoliza);
			Validation.trueBooleanCondition(tituloPaginaPoliza.contains(PolizaProperties.idPoliza),
					"Se ha ingresado a la póliza correcta: " + PolizaProperties.idPoliza,
					"No se ha ingresado a la póliza correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPolizaBusquedaRapida() {

		try {
			loadingTIAGeneral();
			Events.clickButton(labelHome);
			loadingTIAGeneral();

			String idP = PolizaProperties.idPoliza;

			WebElement elemnt = getDriver().findElement(By.xpath(inputBusquedaRapida));
			Actions builder = new Actions(getDriver());

			elemnt.clear();
			Events.sendKeys(inputBusquedaRapida, idP);
			Utils.takeAdditionalScreenshot(String.format("Busqueda de poliza %s", PolizaProperties.idPoliza), "extra");
			Thread.sleep(2000);

			builder.moveToElement(elemnt).sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ENTER)
					.build().perform();

			loadingTIAGeneral();
			Events.clickButton(labelInformacionDelPago);
			loadingTIAGeneral();

			String tituloPaginaPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPaginaPoliza);
			Validation.trueBooleanCondition(tituloPaginaPoliza.contains(PolizaProperties.idPoliza),
					"Se ha ingresado a la póliza correcta: " + PolizaProperties.idPoliza,
					"No se ha ingresado a la póliza correctamente");
			Utils.takeAdditionalScreenshot(String.format("despliegue de poliza %s", PolizaProperties.idPoliza),
					"extra");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el Plan Cuotas", page = "Asiento Pasajero ANS", functionality = "Endoso")
	public static void botonPlanCuotas() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonPlanCuota);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el tipo de Plan Cuotas", page = "Asiento Pasajero ANS", functionality = "Endoso")
	public static void seleccionarPlanCuotas(String numPlan) {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputPlanCuotas, numPlan, Keys.ENTER);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(selectPlanCuotas.replace("numPlan", numPlan));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Acepta el tipo de Plan Cuotas", page = "Asiento Pasajero ANS", functionality = "Endoso")
	public static void botonAceptarPlanCuotas() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonAceptarPlanCuota);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Informacion Pago", page = "Manejo de objetos", functionality = "Endoso")
	public static void botonInformacionPago() {

		try {

			// Validar aca el despliegue de la informacion del pago.

			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelInformacionDelPago);
			TransversalEvents.loadingTIAGeneral();

			String tituloPaginaPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPaginaPoliza);
			Validation.trueBooleanCondition(tituloPaginaPoliza.contains(PolizaProperties.idPoliza),
					"Se ha ingresado a la póliza correcta: " + PolizaProperties.idPoliza,
					"No se ha ingresado a la póliza correctamente");
			Utils.takeAdditionalScreenshot(String.format("despliegue de poliza %s", PolizaProperties.idPoliza),
					"extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarBusquedaRapida(String valorBuscarString) {
		try {
			Events.limpiarCajaDeTexto(inputBusquedaRapida);
			Events.sendKeys(inputBusquedaRapida, valorBuscarString);
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));

			Boolean rsLoading = wait
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loadingBusquedaRapida)));

			if (!rsLoading) {
				Utils.eventFailed(
						"El tiempo de carga al buscar un elemento en busqueda rapida, excedio los 60 segundos");
			}

			Events.clickButton(listOpcionBusquedaRapida);
			WebElement elemento = getDriver().findElement(By.xpath(inputBusquedaRapida));
			elemento.sendKeys(Keys.ENTER);
			loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Renovar manualmente una póliza.", page = "Manejo Poliza", functionality = "Renovación")
	public static void renovarPoliza() {
		try {
			loadingTIAGeneral();
			Events.sendKeys(inputBuscarPolizaEntidad, PolizaProperties.idPoliza, Keys.ENTER);
			loadingTIAGeneral();
			Events.clickButton(buttonRenovar);
			String tituloPagina = Events.getText(labelPaginaRenovacion);
			String polizaRenovar = Events.getText(labelPolizaRenovar);
			Utils.outputInfo("Ha ingresado a la página: " + tituloPagina);
			Validation.trueBooleanCondition(PolizaProperties.idPoliza.equals(polizaRenovar),
					"Se ha cargado la página correctamente", "No se ha cargado la página correcta");
			Events.clickButton(buttonRenovacion);
			loadingTIAGeneral();
			Utils.takeAdditionalScreenshot("Despliegue ventana renovacion", "extra");
			Events.clickButton(buttonCompletarRenovacion);
			loadingTIAGeneral();
			Utils.takeAdditionalScreenshot("Despliegue confirmacion renovacion", "extra");
			String nomDoc = Events.getText(labelModalDocumentosPoliza);
			Validation.trueBooleanCondition(nomDoc.contains(PolizaProperties.idPoliza),
					String.format("Poliza a renovar %s correctamente", PolizaProperties.idPoliza),
					"Poliza a renovar no es la correcta");
			Events.clickButton(buttonAceptarRenovacion);
			loadingTIAGeneral(180);
			Utils.takeAdditionalScreenshot("Finalizacion renovacion", "extra");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el tipo de endoso que se modificará posteriormente", page = "Manejo Poliza", functionality = "Endosos")
	public static void seleccionarTipoEndoso(String tipoEndoso) {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonAjustarPoliza);
			loadingTIAGeneral();
			Events.selectByContainsText(selectTipoEndoso, tipoEndoso);
			loadingTIAGeneral();
			Events.clickButton(buttonAceptarAjuste);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el Objeto en el árbol de pólizas de la entidad seleccionada previamente", page = "Manejo Objeto", functionality = "Endosos")
	public static void ingresarObjeto(String nombre) {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonCerrarSegventana);
			TransversalEvents.loadingTIAGeneral();
			TransversalEvents.seleccionarContratante(nombre);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAmpliarVentanaPolizas);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(aPolizav3.replace("id-poliza", PolizaProperties.idPoliza));
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(aObjeto.replace("id-objeto", PolizaProperties.idObjeto));
			String tituloPaginaObjeto = Events.getText(labelManejoObjeto);
			Utils.outputInfo("La sección actual es: " + tituloPaginaObjeto);
			Validation.trueBooleanCondition(tituloPaginaObjeto.contains(PolizaProperties.idObjeto),
					"Se ha ingresado al objeto correcto: " + PolizaProperties.idObjeto,
					"No se ha ingresado al objeto correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el Objeto en el árbol de pólizas de la entidad seleccionada previamente", page = "Manejo Objeto", functionality = "Endosos")
	public static void ingresarObjeto() {

		try {
			loadingTIAGeneral();
			String idObjeto = PolizaProperties.idObjeto;
			String xpathObjeto = aObjeto.replace("id-objeto", idObjeto);
			boolean objetoEncontrado = false;

			WebElement contenedorScroll = getDriver().findElement(By.xpath(arbolLateralPoliza));
			JavascriptExecutor jsScroll = (JavascriptExecutor) getDriver();
			WebDriverWait waitGeneral = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
			
			List<WebElement> busquedaInicial = getDriver().findElements(By.xpath(xpathObjeto));
			
			if (!busquedaInicial.isEmpty()) {
				WebElement objetoBuscado = busquedaInicial.get(0);
				jsScroll.executeScript("arguments[0].scrollIntoView({block: 'center'})", objetoBuscado);
				waitGeneral.until(ExpectedConditions.elementToBeClickable(objetoBuscado)).click();
				objetoEncontrado = true;
				Utils.outputInfo("Objeto localizado. Se omite la búsqueda.");
			} else {
				jsScroll.executeScript("arguments[0].scrollTop = 0;", contenedorScroll);
				loadingTIAGeneral();
				waitGeneral.until(ExpectedConditions.visibilityOf(contenedorScroll));

				for (int i = 0; i < 20; i++) {
					List<WebElement> listaBarraLateral = getDriver().findElements(By.xpath(xpathObjeto));

					if (!listaBarraLateral.isEmpty()) {
						WebElement objetoBuscado = listaBarraLateral.get(0);
						jsScroll.executeScript("arguments[0].scrollIntoView({block: 'center'})", objetoBuscado);
						waitGeneral.until(ExpectedConditions.elementToBeClickable(objetoBuscado)).click();
						objetoEncontrado = true;
						break;
					}

					jsScroll.executeScript("arguments[0].scrollTop += 400;", contenedorScroll);

					try {
						new WebDriverWait(getDriver(), Duration.ofSeconds(2))
								.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathObjeto)));
					} catch (TimeoutException ignored) {

					}
				}
			}

			if (!objetoEncontrado) {
				Utils.eventFailed(
						"El objeto " + idObjeto + " no apareció en el árbol lateral tras el barrido completo.");
				return;
			}

			loadingTIAGeneral();
			String tituloPaginaObjeto = Events.getText(labelManejoObjeto);
			Validation.trueBooleanCondition(tituloPaginaObjeto.contains(idObjeto),
					"Se ha ingresado al objeto correcto: " + idObjeto, "No se ha ingresado al objeto correctamente");

			Utils.outputInfo("La sección actual es: " + tituloPaginaObjeto);

		} catch (

		Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inicia el ajuste de un Objeto para realizar algún tipo de endoso actualizando fecha de ajuste", page = "Manejo Objeto", functionality = "Endosos")
	public static void ajustarObjeto(String fechaAjuste) {

		try {
			Events.clickButton(buttonAjustar);
			Events.sendKeys(inputFechaAjuste, fechaAjuste);
			Events.clickButton(buttonAceptaAjuste);
			String estadoPaginaEdicion = Events.getAtributte(labelEditarActivado, "title");
			Validation.trueBooleanCondition(estadoPaginaEdicion.contains("Activado"),
					"Se ha ingresado al ajuste del objeto correctamente: ",
					"No se ha ingresado al ajuste del objeto correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inicia el ajuste de un Objeto para realizar algún tipo de endoso con fecha de ajuste por defecto", page = "Manejo Objeto", functionality = "Endosos")
	public static void ajustarObjeto() {

		try {
			loadingTIAGeneral(60);
			Events.clickButton(buttonAjustar);
			Events.clickButton(buttonAceptaAjuste);
			loadingTIAGeneral(60);
			String estadoPaginaEdicion = Events.getAtributte(labelEditarActivado, "title");
			Validation.trueBooleanCondition(estadoPaginaEdicion.contains("Activado"),
					"Se ha ingresado al ajuste del objeto correctamente: ",
					"No se ha ingresado al ajuste del objeto correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona id de una entidad para acceder a su información", page = "Manejo entidad", functionality = "Endosos")
	public static void seleccionarEntidad(String idEntidad) {

		try {
			Events.clickButton(aEntidad.replace("id-entidad", idEntidad));
			String paginaInformacionEntidad = Events.getText(labelPaginaInformacionEntidad);
			Validation.trueBooleanCondition(paginaInformacionEntidad.contains("Información de la entidad"),
					"Se ha ingresado correctamente a la información de la entidad: " + idEntidad,
					"No se ha ingresado a la información de la entidad correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Buscar entidad por su Id", page = "Buscar Entidades", functionality = "Transversal")
	public static void buscarEntidadAlternativo(String idEntidad) {
		try {
			loadingTIAGeneral();
			Events.clickButton(aBuscarEntidad);
			loadingTIAGeneral();
			Events.sendKeys(inputIdEntidad, idEntidad);
			loadingTIAGeneral();
			Events.clickButton(buttonBuscarEntidad);
			loadingTIAGeneral();
			Events.clickButton(aEntidad.replace("id-entidad", idEntidad));
			loadingTIAGeneral();
			String tituloPagina = Events.getText(labelPaginaResumenEntidad);
			Utils.outputInfo("La sección actual es: " + tituloPagina);
			Validation.trueBooleanCondition(tituloPagina.contains("Resumen de entidad"),
					"Se ha ingresado al resumen de la entidad correctamente: " + idEntidad,
					"No se ha ingresado al resumen de la entidad");
			PolizaProperties.idEntidad = idEntidad;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Cierra la página de Búsqueda Entidades", page = "Pagina Búsqueda", functionality = "Transversal")
	public static void cerrarPaginaBusqueda() {
		try {
			loadingTIAGeneral();
			Events.clickButton(aCerrarPaginaBusqueda);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ir a la página Home", page = "Pagina Home", functionality = "Transversal")
	public static void visitarPaginaHome() {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(aHome);
			TransversalEvents.loadingTIAGeneral();
			String tittle = Events.getText(labelPaginaPrincipal);
			Utils.outputInfo("La sección actual es: " + tittle);
			Validation.trueBooleanCondition(tittle.contains("Página principal"),
					"Se ha ingresado a la Página Principal",
					"No se ha ingresado a la Página Principal de forma correcta");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inicia el ajuste de una Póliza para realizar cancelaciones", page = "Manejo Póliza", functionality = "Cancelación")
	public static void ajustarPoliza(String fechaAjuste) {

		try {
			Events.clickButton(buttonAjustar);
			Events.sendKeys(inputFechaAjuste, fechaAjuste);
			Events.clickButton(buttonAceptaAjuste);
			String tipoTransaccion = Events.getText(labelTipoTransaccion);
			Validation.trueBooleanCondition(tipoTransaccion.contains("Ajuste"),
					"Se ha ingresado al ajuste de la póliza correctamente: ",
					"No se ha ingresado al ajuste de la póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona Método de pago.", page = "Manejo de objetos", functionality = "Transversal")
	public static void seleccionarMetodoPago(String selMetodoPago) {

		try {
			loadingTIAGeneral();
			String metodoPagoSeleccionado = Events
					.selectByExactText(elementGenerico.replace("TextoBuscado", "Método de pago"), selMetodoPago);
			loadingTIAGeneral();
			Utils.takeAdditionalScreenshot(String.format("Se selecciona el método de pago %s", metodoPagoSeleccionado),
					"extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inicia el ajuste de una Póliza para realizar cancelaciones", page = "Manejo Póliza", functionality = "Cancelación")
	public static void ajustarPoliza() {

		try {
			Events.clickButton(buttonAjustar);
			Events.clickButton(buttonAceptaAjuste);
			loadingTIAGeneral();
			String tipoTransaccion = Events.getText(labelTipoTransaccion);
			Validation.trueBooleanCondition(tipoTransaccion.contains("Ajuste"),
					"Se ha ingresado al ajuste de la póliza correctamente: ",
					"No se ha ingresado al ajuste de la póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el motivo de cancelación", page = "Manejo Póliza", functionality = "Cancelación")
	public static void seleccionarMotivoCancelacionPoliza(String motivoCancelacion) {
		try {
			Events.selectByContainsText(selectMotivoCancelacion, motivoCancelacion);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida estado de cancelación", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validarTransaccionCancelacionPoliza() {
		try {
			String tipoTransaccion = Events.getText(labelEstadoTransaccion);
			Validation.trueBooleanCondition(tipoTransaccion.contains("C Cancelar póliza"),
					"El estado de la póliza: " + PolizaProperties.idPoliza + tipoTransaccion,
					"No se ha cancelado la póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida estado de cancelación", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validarTransaccionCancelacionPolizaUAT() {
		try {
			String tipoTransaccion = Events
					.getText(labelPolizaCanceladaUAT.replace("id-poliza", PolizaProperties.idPoliza));
			Validation.trueBooleanCondition(tipoTransaccion.contains("- Cancelado"),
					"El estado de la póliza: " + PolizaProperties.idPoliza + tipoTransaccion,
					"No se ha cancelado la póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida estado de cancelación", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validarTransaccionCancelacionPolizaMR() {
		try {
			Events.clickButton(labelIngresarPoliza.replace("id-poliza", PolizaProperties.idPoliza));
			loadingTIAGeneral();

			String tipoTransaccion = Events.getText(labelEstadoPolizaCancelada);
			Validation.trueBooleanCondition(tipoTransaccion.contains("Cancelar"),
					"El estado de la póliza: " + PolizaProperties.idPoliza + " " + tipoTransaccion,
					"No se ha cancelado la póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida endoso de objeto", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validarEndoso() {
		try {
			String tipoTransaccion = Events.getText(labelTipoTransaccion);
			Validation.trueBooleanCondition(tipoTransaccion.contains("Ajuste"),
					"Se ha realizado el ajuste correctamente, el código de transacción es :" + tipoTransaccion,
					"No se ha realizado el ajuste correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Esperar la actualización de la ventana/modulo", page = "Manejo de objetos", functionality = "Transversal")
	public static void loadingTIAGeneral() {
		try {
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(50));
			LocalDateTime horaAntesEjeDateTime = LocalDateTime.now();

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loadingTia)));

			LocalDateTime horaDespsEjeDateTime = LocalDateTime.now();

			Utils.outputInfo(String.format(
					"La espera de carga finalizo luego de %s segundos con una espera configurada de 50 segundos",
					String.valueOf(horaDespsEjeDateTime.toEpochSecond(java.time.ZoneOffset.UTC)
							- horaAntesEjeDateTime.toEpochSecond(java.time.ZoneOffset.UTC))));

		} catch (Exception e) {
			Utils.eventFailed(String.format("La espera de carga en TIA excedio el tiempo de %s segundos", "50"));
		}
	}

	@Context(description = "Esperar la actualización de la ventana/modulo", page = "Manejo de objetos", functionality = "Transversal")
	public static void loadingTIAGeneral(long tiempoEspera) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(tiempoEspera));

			LocalDateTime horaAntesEjeDateTime = LocalDateTime.now();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loadingTia)));
			LocalDateTime horaDespsEjeDateTime = LocalDateTime.now();
			Utils.outputInfo(String.format(
					"La espera de carga finalizo luego de %s segundos con una espera configurada de %s segundos",
					String.valueOf(horaDespsEjeDateTime.toEpochSecond(java.time.ZoneOffset.UTC)
							- horaAntesEjeDateTime.toEpochSecond(java.time.ZoneOffset.UTC)),
					String.valueOf(tiempoEspera)));

		} catch (Exception e) {
			Utils.eventFailed(String.format("La espera de carga en TIA excedio el tiempo de %s segundos",
					String.valueOf(tiempoEspera)));
		}
	}

	@Context(description = "Selecciona una póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Renovación")
	public static void ingresarPoliza(String idPoliza) {
		try {
			Events.clickButton(aOrdenarFechaRenov);
			loadingTIAGeneral();
			Events.clickButton(buttonAmpliarVentanaPolizas);
			Events.clickButton(aPoliza.replace("id-poliza", idPoliza));
			loadingTIAGeneral();
			String tituloPaginaPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPaginaPoliza);
			Validation.trueBooleanCondition(tituloPaginaPoliza.contains(idPoliza),
					"Se ha ingresado a la póliza correcta: " + idPoliza,
					"No se ha ingresado a la póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona la fecha de siniestro", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaDiaHoySinDenuncia() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarFechaSiniestro);
			loadingTIAGeneral();
			Events.clickButton(buttonAceptarFecha);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona la fecha de siniestro", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaDiaHoy() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarFechaSiniestro);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAceptarFecha);
			String fechaDelSiniestro = Events.getValue(elementGenerico.replace("TextoBuscado", "Fecha del siniestro"));
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputFechaDenuncia, fechaDelSiniestro);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona la fecha de siniestro", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaDenunciaDiaSiguiente() {

		try {
			TransversalEvents.loadingTIAGeneral();

			//
			TransversalEvents.loadingTIAGeneral();
			LocalDateTime hora = LocalDateTime.now();
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
			String fecha = hora.plusDays(1).format(fa);
			System.out.println("Fecha de mañana = " + fecha);
			//

			Events.sendKeys(inputFechaDenuncia1, fecha);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona la fecha de siniestro", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaDenunciaDiaSiguiente2() {

		try {
			TransversalEvents.loadingTIAGeneral();
			//
			LocalDateTime hora = LocalDateTime.now();
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
			String fecha = hora.plusDays(1).format(fa);
			System.out.println("Fecha de mañana = " + fecha);
			//

			Events.sendKeys(buttonAceptarFecha, fecha);

			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAceptarFecha);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar el modulo Reservas y Pagos", page = "Siniestro", functionality = "Manejo Objeto")
	public static void seleccionarModuloReservasPagosSiniestro() {
		try {
			loadingTIAGeneral();
			String isSelected = Events.getAtributte(aModuloReservasPagos, "aria-selected");
			if (!"true".equals(isSelected)) {
				Events.clickButton(aModuloReservasPagos);
				Utils.outputInfo("Se hizo clic en la pestaña \"Reservas y Pagos\".");
			} else {
				Utils.outputInfo("La pestaña ya estaba seleccionada. Se omite el clic.");
			}
			loadingTIAGeneral();
			String tituloPagosReserva = Events.getText(labelPagosReservas);
			Validation.trueBooleanCondition(tituloPagosReserva.equalsIgnoreCase("total de siniestros"),
					"Se despliega correctamente el modulo Reservas y Pagos",
					"No se desplego el modulo Reservas y Pagos");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar el modulo \"Comunicación\".", page = "Siniestro", functionality = "Manejo Objeto")
	public static void seleccionarModuloComunicacion() {
		try {
			loadingTIAGeneral();
			String isSelected = Events.getAtributte(aModuloComunicacion, "aria-selected");
			if (!"true".equals(isSelected)) {
				Events.clickButton(aModuloComunicacion);
				Utils.outputInfo("Se hizo clic en la pestaña \"Comunicación\".");
			} else {
				Utils.outputInfo("La pestaña ya estaba seleccionada.");
			}
			loadingTIAGeneral();
			String tituloPagosReserva = Events.getText(labelComunicacion);
			Validation.trueBooleanCondition(tituloPagosReserva.equalsIgnoreCase("Comunicación"),
					"Se despliega correctamente el modulo \"Comunicación\".",
					"No se desplego el modulo \"Comunicación\".");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar cambio en el valor del monto reserva de Reembolso.", page = "Siniestro", functionality = "Manejo Objeto")
	public static void verificarMontoReembolso(String reservaModificada) {
		try {
			loadingTIAGeneral();
			String montoValidarReembolso = Events.getText(spanMontoReservaReembolso);
			Validation
					.trueBooleanCondition(montoValidarReembolso.contains(reservaModificada),
							"Se verifica que el monto se ha actualizado con el valor modificado, que corresponde a "
									+ montoValidarReembolso,
							"No se ha podido verificar el cambio de monto de reemoblso.");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Verificar cambio en el valor de reserva en el detalle del item.", page = "Siniestro", functionality = "Manejo Objeto")
	public static void verificarMontoDetalleReserva(String reservaModificada) {
		try {
			loadingTIAGeneral();
			String montoValidarReembolso = Events.getValue(elementGenerico.replace("TextoBuscado", "Reserva"));
			Validation
					.trueBooleanCondition(montoValidarReembolso.contains(reservaModificada),
							"Se verifica que el monto se ha actualizado con el valor modificado, que corresponde a "
									+ montoValidarReembolso,
							"No se ha podido verificar el cambio de monto de reserva.");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Seleccionar item a pagar", page = "Siniestro", functionality = "Siniestros")
	public static void seleccionarItemPago() {
		try {
			loadingTIAGeneral();
			Events.clickButton(inputCheckBoxSeleccionarItemPago);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Liquidar siniestro", page = "Siniestro", functionality = "Siniestros")
	public static void liquidarItemPago() {
		try {
			loadingTIAGeneral();
			Events.clickButton(buttonLiquidar);
			loadingTIAGeneral();
			String tituloVentanaPago = Events.getText(labelPago);
			Validation.trueBooleanCondition(tituloVentanaPago.contains("Pago"),
					"Se ha cargado la ventana para ejecutar el pago correctamente",
					"No se ha cargado la ventana para ejecutar el pago");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Extraer el número de siniestro")
	public static void extraerNumeroSiniestro() {
		try {
			String tituloSiniestro = Events.getText(labelTituloSiniestro);

			String limpiezaTituloString = tituloSiniestro.substring(tituloSiniestro.indexOf("-") + 1);
			limpiezaTituloString = limpiezaTituloString.substring(0, limpiezaTituloString.indexOf("(")).trim();

//			SiniestroProperties siniestro = new SiniestroProperties();
//			siniestro.numeroSiniestro = limpiezaTituloString;

			SiniestroProperties.numeroSiniestro = limpiezaTituloString;
			System.out.println(SiniestroProperties.numeroSiniestro);

			Utils.outputInfo("Número del siniestro es " + limpiezaTituloString);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Valida que la poliza ha sido renovada de forma automática", page = "Manejo Poliza", functionality = "Renovacion")
	public static void validarRenovacionAutomatica() {
		try {
			Events.clickButton(buttonDetallesRenovacion);
			String tituloPaginaDetallesRenovacion = Events.getText(labelDetallesRenovacion);
			Utils.outputInfo("La sección actual es: " + tituloPaginaDetallesRenovacion);
			String loteRenovacion = Events.getText(labelLoteRenovacion);
			Validation.trueBooleanCondition(!tituloPaginaDetallesRenovacion.contains(" "),
					"Se ha realizado la renovación automática de forma correcta. El lote de renovación es: "
							+ loteRenovacion,
					"No se ha realizado la renovación automática correctamente. Se muestra el siguiente mensaje: "
							+ Events.getText(labelNoRenovacion));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona la fecha en que se realiza la factura", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaFactura() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarFechaHora);
			TransversalEvents.loadingTIAGeneral();
			LocalDate fechaHoy = LocalDate.now();
			int numDiaHoy = fechaHoy.getDayOfMonth();
			String diaString = String.valueOf(numDiaHoy);
			Events.clickButton(buttonDiaFactura.replace("dia", diaString));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona la fecha de siniestro", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaDenunciaDiaAnterior() {
		try {
			loadingTIAGeneral();
			LocalDateTime fechaHoraAyer = LocalDateTime.now().minusDays(1);
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
			String fechaDenunciaSiniestro = fechaHoraAyer.format(fa);
			String xpathInputFechaDenuncia = elementGenerico.replace("TextoBuscado", "Fecha de denuncia");
			Events.getText(xpathInputFechaDenuncia);
			Events.limpiarCajaDeTexto(xpathInputFechaDenuncia);
			Utils.outputInfo(
					"Fecha para ingresar con un día antes de la fecha actual corresponde a: " + fechaDenunciaSiniestro);
			Events.sendKeys(xpathInputFechaDenuncia, fechaDenunciaSiniestro);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar siniestro actual para modificar valor de reserva.")
	public static void seleccionarSiniestroLista(String nuevoValorReserva) {
		try {
			loadingTIAGeneral();
			Events.clickButton(checkBoxSeleccionSiniestro);
			loadingTIAGeneral();
			Events.sendKeys(inputReservaPagosReserva, nuevoValorReserva);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Liquida un item de una reserva", page = "Reserva", functionality = "Siniestros")
	public static void modificarMontoReservaLiquidar(String monto) {
		try {
			loadingTIAGeneral();
			String inputMontoPagar = elementGenerico.replace("TextoBuscado", "Monto a pagar");
			Events.sendKeys(inputMontoPagar, monto, Keys.ENTER);
			Utils.outputInfo("Valor ingresado en el campo \"Monto a pagar\":" + Events.getValue(inputMontoPagar));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Presionar el botón \"Siguiente\" en la ventana de \"Pago\".", page = "Pago", functionality = "Siniestros")
	public static void botonSiguiente() {
		loadingTIAGeneral();
		Events.clickButton(buttonSiguiente);
		Utils.outputInfo("Se ha presionado el botón " + Events.getText(buttonSiguiente));
	}

	@Context(description = "Completar instrucción de pago.", page = "Pago", functionality = "Siniestros")
	public static void instruccionPago() {
		loadingTIAGeneral();
		Events.clickButton(buttonSeleccionaInstPago);
		Events.clickButton(labelInstPago);
		Events.clickButton(buttonAceptaInstPago);
		loadingTIAGeneral();
		Utils.outputInfo("Información ingresada en el campo Instrucción de pago: "
				+ Events.getValue(elementGenerico.replace("TextoBuscado", "Instrucción de pago")));
	}

	@Context(description = "Validar monto a pagar antes de finalizar el pago.", page = "Pago", functionality = "Siniestros")
	public static void validarMontoPagar(String montoIngresado) {
		try {
			loadingTIAGeneral();
			String validarMontoPagar = Events.getText(labelMontoPagar);
			Validation.trueBooleanCondition(validarMontoPagar.contains(montoIngresado),
					String.format("El monto a paga corresponde a %s.", validarMontoPagar),
					String.format("El valor detectado es diferente al valor ingresado %s. Valor detectado: %s",
							montoIngresado, validarMontoPagar));
		} catch (Exception e) {
			Utils.eventFailed("No se ha podido validar el monto por el siguiente motivo: " + e.getMessage());
		}
	}

	@Context(description = "Finalizar pago.", page = "Pago", functionality = "Siniestros")
	public static void finalizarPago() {
		loadingTIAGeneral();
		Events.clickButton(buttonFinalizarPago);
		loadingTIAGeneral();
		String montoPagado = Events.getText(labelPaid);
		Validation.trueBooleanCondition(!montoPagado.equalsIgnoreCase(""),
				"La liquidación se ha realizado correctamente, el monto pagado es: " + montoPagado,
				"No se ha realizado la liquidación correctamente");
	}

	@Context(description = "Seleccionar el sub modulo Pagos.", page = "Siniestro", functionality = "Manejo Objeto")
	public static void seleccionarSubModuloPago() {
		try {
			loadingTIAGeneral();
			Events.clickButton(aSubModuloPago);
			Utils.outputInfo("Se hizo clic en la pestaña \"Pagos\".");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar cambios en los valores del sub modulo Pagos.", page = "Pago", functionality = "Siniestro")
	public static void valicarCambiosEnValoresReserva() {

		try {
			loadingTIAGeneral();
			String valorReservaInicialRaw = Events.getText(labelReservaInicial);
			long valorReservaInicial = Long.parseLong(valorReservaInicialRaw.split(",")[0].replace(".", ""));

			String valorReservaRestanteRaw = Events.getText(spanReservaRestante);
			long valorReservaRestante = Long.parseLong(valorReservaRestanteRaw.split(",")[0].replace(".", ""));

			String valorPagadoRaw = Events.getText(spanPagado);
			long valorPagado = Long.parseLong(valorPagadoRaw.split(",")[0].replace(".", ""));

			Long validaValor = valorReservaRestante + valorPagado;
			Boolean calculoCorrecto = (validaValor == valorReservaInicial);
			String detalleValores = String.format("\n-Reserva inicial: %s\n-Reserva restante: %s\n-Pagado: %s",
					valorReservaInicialRaw, valorReservaRestanteRaw, valorPagadoRaw);
			Validation.trueBooleanCondition(calculoCorrecto,
					String.format("Se valida información de reserva correctamente: \n%s", detalleValores),
					String.format(
							"La suma del valor pagado %s con el valor restante %s no coincide con el valor inicial %s. Detalle de valores: \n%s",
							valorPagadoRaw, valorReservaRestanteRaw, valorReservaInicialRaw, detalleValores));
			Utils.takeAdditionalScreenshot(
					String.format("Se valida información de reserva correctamente: %s", detalleValores), "extra");
		} catch (Exception e) {
			Utils.eventFailed("No se ha podido llevar a cabo la vlaidación: " + e.getMessage());
		}
	}

	@Context(description = "Recalcula la prima de la póliza ante un endoso complejo", page = "Manejo Objeto", functionality = "Endoso")
	public static void calcularPrimaTotalEndosoComplejo() {

		try {
			String primaTotalAntesEndoso = Events.getText(labelPrimaTotal);
			Events.clickButton(buttonCalculate);
			loadingTIAGeneral();
			String primaTotalDespuesEndoso = Events.getText(labelPrimaTotal);
			Validation.trueBooleanCondition(!primaTotalAntesEndoso.equals(primaTotalDespuesEndoso),
					"Ha sido calculada correctamente la nueva prima, cambió de " + primaTotalAntesEndoso + " a "
							+ primaTotalDespuesEndoso,
					"No se ha calculado correctamente la nueva prima");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea siniestro de forma alternativa, desde el manejo de una póliza", page = "Manejo de póliza", functionality = "Siniestros")
	public static void crearSiniestroCreateClaim() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonCreateclaim);
			String tittle = Events.getText(labelPaginaSiniestro);
			Utils.outputInfo("La sección actual es: " + tittle);
			Validation.trueBooleanCondition(tittle.contains("Crear siniestro"),
					"Se ha ingresado a la página para la creación de un siniestro",
					"No se ha ingresado correctamente para crear un siniestro");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea siniestro de forma alternativa, desde el manejo de una póliza", page = "Manejo de póliza", functionality = "Siniestros")
	public static void crearSiniestroCreateClaimAlt() {

		try {
			// OncologicoEvents.validarDespliegueSiniestro();
			loadingTIAGeneral();
			Events.clickButton(buttonCreateclaim);
			String tittle = Events.getText(labelPaginaSiniestro);
			Utils.outputInfo("La sección actual es: " + tittle);
			Validation.trueBooleanCondition(tittle.contains("Crear siniestro"),
					"Se ha ingresado a la página para la creación de un siniestro",
					"No se ha ingresado correctamente para crear un siniestro");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea siniestro de forma alternativa, desde el manejo de una póliza", page = "Manejo de póliza", functionality = "Siniestros")
	public static void tab() {

		try {
			WebElement elemnt = getDriver().findElement(By.xpath(inputFechaSiniestro));

			Actions builder = new Actions(getDriver());
			Action seriesOfActions = builder.keyDown(elemnt, Keys.TAB).keyUp(elemnt, Keys.TAB).build();
			loadingTIAGeneral();
			seriesOfActions.perform();

			String tittle = Events.getText(labelSiniestroError);
			Utils.outputInfo("Se ha mostrado el mensaje: " + tittle);
			Validation.trueBooleanCondition(tittle.contains("Error"), "Se ha mostrado el mensaje de Error",
					"No se ha mostrado el mensaje de Error");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Scroll arriba", page = "Manejo Poliza", functionality = "Endosos")
	public static void scrollArriba() {

		try {
			loadingTIAGeneral();
			Events.scroll(buttonNuevoScroll);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPolizaV2() {

		try {
			Events.clickButton(clicEntidad);
			TransversalEvents.loadingTIAGeneral();

			String tituloClicPoliza = Events.getText(clicPoliza);
			Events.clickButton(clicPoliza);
			TransversalEvents.loadingTIAGeneral();

			String tituloPaginaPoliza = Events.getText(labelManejoPoliza);
			Utils.outputInfo("La sección actual es: " + tituloPaginaPoliza);
			Validation.trueBooleanCondition(tituloPaginaPoliza.contains(tituloClicPoliza),
					"Se ha ingresado a la póliza correcta: " + PolizaProperties.idPoliza,
					"No se ha ingresado a la póliza correctamente");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar que el módulo Siniestro se encuentre desplegado ", page = "Manejo de objetos")
	public static void validarDespliegueSiniestro() {
		try {

			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(10));
			wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));

			if (!BaseTest.getDriver().findElements(By.xpath(collapseSiniestroHF)).isEmpty()) {
				WebElement element = BaseTest.getDriver().findElement(By.xpath(collapseSiniestroHF));

				wait.until(ExpectedConditions.visibilityOf(element));

				String buttonName = Events.getName(element, collapseSiniestroHF);
				Actions actions = new Actions(BaseTest.getDriver());

				actions.moveToElement(element).perform();

				if (element.isEnabled()) {
					element.click();
					Utils.outputInfo("Se ha hecho clic en el botón: " + buttonName);
				}
				loadingTIAGeneral();
			} else {
				Utils.outputInfo("Modulo Siniestro se encuentra desplegado");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(step = "Verifica si está despleagada la bitácora del Siniestro", page = "Siniestro", functionality = "Siniestro")
	public static void verificaBitacoraDesplegada() {

		try {
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(50));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonBarraLateralHF)));
			String bitacoraDesplegado = Events.getAtributte(buttonBarraLateralHF, "aria-expanded");
			if (!bitacoraDesplegado.equals("true")) {
				Events.clickButton(buttonBarraLateralHF);
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la fecha de inicio de cobertura de la póliza un año antes.", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarFechaInicioPolizaUnAnioAntes() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarFechaInicioPoliza);
			loadingTIAGeneral();
			Events.clickButton(buttonDisminuirAnnoPoliza);
			loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarDia);
			Utils.takeAdditionalScreenshot("Se ingresa fecha inicio de póliza", "extra");
			loadingTIAGeneral();
			String fechaInicio = Events.getValue(inputFechaInicioCobertura);
			PolizaProperties.fechaInicio = fechaInicio;
			loadingTIAGeneral();
			String fechaRenovacion = Events.getValue(inputFechaRenovacionCobertura);
			PolizaProperties.fechaRenovacion = fechaRenovacion;

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona botón Buscar Entidad en barra superior", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarSeccionSuperior() {

		try {
			loadingTIAGeneral(5);
			Events.clickButton(buttonBuscarEntidadbarraSuperior);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Manejo de abertura de secciones barra lateral izquierda", page = "Manejo Póliza", functionality = "Transversal")
	public static void desplegarSeccionBarraLateral(String nombreSeccion) {
		try {

			loadingTIAGeneral();

			boolean rsValidacionBoolean = validarElementoPantalla(
					collapseLateralBar.replace("nombre-seccion", nombreSeccion));

			if (rsValidacionBoolean == true) {
				List<WebElement> elements = BaseTest.getDriver()
						.findElements(By.xpath(collapseLateralBar.replace("nombre-seccion", nombreSeccion)));
				elements.get(0).click();
				loadingTIAGeneral(40);
				Utils.takeAdditionalScreenshot(String.format("Se desplego el elemento %s", nombreSeccion), "extra");
			} else {
				Utils.outputInfo(String.format("El elemento %s ya se encontraba desplegado", nombreSeccion));
				Utils.takeAdditionalScreenshot(
						String.format("El elemento %s ya se encontraba desplegado", nombreSeccion), "extra");

			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());

		}
	}

	@Context(description = "Selecciona el Plan Cuotas", page = "Manejo de objetos", functionality = "Endoso")
	public static void botonFacturador() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonFacturador);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Botón que despliega opciones dentro de Nro de Cuenta", page = "Manejo de Poliza", functionality = "Endoso")
	public static void botonNroCuenta() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonNCuenta);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Botón busqueda Entidad en sección Nro de Cuenta", page = "Manejo de Poliza", functionality = "Endoso")
	public static void botonBuscarEntidadNroCuenta() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonBuscarEntidadNroCuenta);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "IDEntidad", page = "Manejo de objetos", functionality = "Endoso")
	public static void ingresoRutEntidad(String RutEntidad) {

		try {
			TransversalEvents.loadingTIAGeneral();

			boolean checkedBusquedaRut = Boolean.parseBoolean(Events.getAtributte(checkBoxBusquedaRut, "checked"));
			if (!checkedBusquedaRut) {
				Events.clickButton(checkBoxBusquedaRut);
				Utils.outputInfo("Se ha seleccionado el checbkox: busqueda por Rut");
			} else {
				Utils.outputInfo("El checbkox: busqueda por Rut ya estaba seleccionado");
			}

			Events.sendKeys(inputRutEntidad, RutEntidad);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Buscar", page = "Manejo de objetos", functionality = "Endoso")
	public static void botonBuscar() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonBuscar);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "ID Entidad", page = "Manejo de objetos", functionality = "Endoso")
	public static void botonIdEntidad() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonIdIdentidad);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "ID Entidad", page = "Manejo de Poliza", functionality = "Endoso")
	public static void seleccionarTipodeCuenta() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelTipoDeCuentaPago);
			clickAceptar();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Click botón Aceptar", page = "Manejo de Poliza", functionality = "Endoso")
	public static void clickAceptar() {

		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAceptar1);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Manejo de abertura de secciones barra lateral izquierda", page = "Manejo Póliza", functionality = "Transversal")
	public static void desplegarSeccionBarraLateralIzquierda(String nombreSeccionIzquierda) {
		try {

			loadingTIAGeneral(5);

			boolean rsValidacionBoolean = validarElementoPantalla(
					collapseLateralBarIzquierda.replace("Restaurar Panel", nombreSeccionIzquierda));

			if (rsValidacionBoolean == true) {
				List<WebElement> elements = BaseTest.getDriver().findElements(
						By.xpath(collapseLateralBarIzquierda.replace("Restaurar Panel", nombreSeccionIzquierda)));
				elements.get(0).click();
				loadingTIAGeneral(40);
				Utils.takeAdditionalScreenshot(String.format("Se desplego el elemento %s", nombreSeccionIzquierda),
						"extra");
			} else {
				Utils.outputInfo(String.format("El elemento %s ya se encontraba desplegado", nombreSeccionIzquierda));
				Utils.takeAdditionalScreenshot(
						String.format("El elemento %s ya se encontraba desplegado", nombreSeccionIzquierda), "extra");

			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());

		}
	}

	@Context(description = "Manejo de estados de pólizas", page = "Manejo Póliza", functionality = "Transversal")
	public static void validarEstadoPoliza(String estadoPoliza) {
		try {
			loadingTIAGeneral();
			String estadoPolString = Events.getText(labelEstadoTransaccion);

			Validation.trueBooleanCondition(
					estadoPolString.trim().toLowerCase().contains(estadoPoliza.trim().toLowerCase()),
					String.format("Poliza con estado %s ", estadoPoliza.toUpperCase()),
					String.format("Se esperaba una póliza con estado %s y se visualiza con estado %s ",
							estadoPoliza.toUpperCase(), estadoPolString.toUpperCase()));
			Utils.takeAdditionalScreenshot(String.format("Poliza con estado %s correctamente", estadoPoliza), "extra");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Confirmar despliegue de elemtentos en pantalla", page = "Manejo Elementos", functionality = "Transversal")
	public static boolean validarElementoPantalla(String xpathString) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(3));

			wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(xpathString))));

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Context(description = "Ingresar en resumen entidad", page = "Manejo Elementos", functionality = "Transversal")
	public static void ingresarResumenEntidad() {
		try {
			boolean rsValidacion = validarElementoPantalla(collapseLateralBar.replace("nombre-seccion", "Relaciones"));
			if (rsValidacion == true) {
				Events.clickButton(aNombreEntidadSeccionRelacion);
				loadingTIAGeneral();
			}
			loadingTIAGeneral();
			Events.clickButton(aNombreEntidadSeccionRelacion);
			loadingTIAGeneral();
			String resumen = Events.getText(labelPaginaResumenEntidad);

			Validation.trueBooleanCondition(resumen.trim().toLowerCase().contains("resumen de entidad"),
					"Se ha retornado de forma correcta a la ventana Resumen de Entidad",
					"No se redirecciono a la ventana Resumen de entidad");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Despliegue de modulos", page = "Manejo Elementos", functionality = "Transversal")
	public static void validarDespliegueModulos(String xpathString) {

		try {
			boolean rsVal = validarElementoPantalla(xpathString);
			if (rsVal == true) {
				Events.scroll(xpathString);
				Events.clickButton(xpathString);
				loadingTIAGeneral(60);
			} else {
				Utils.outputInfo("El elemento ya se encuentra desplegado");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(step = "Selecciona una fecha anterior para una poliza a siniestrar/endosar/cancelar.", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaPolizaDosDiasAntes() {

		try {
			loadingTIAGeneral();
			LocalDateTime fechaDiaAnterior = LocalDateTime.now();
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			String fecha = fechaDiaAnterior.plusDays(-2).format(fa);
			System.out.println("Fecha actual = " + fecha);
			Events.sendKeys(fechaInicioPoliza, fecha);
			loadingTIAGeneral();
			String fechaInicio = Events.getValue(inputFechaInicioCobertura);
			PolizaProperties.fechaInicio = fechaInicio;
			loadingTIAGeneral();
			String fechaRenovacion = Events.getValue(inputFechaRenovacionCobertura);
			PolizaProperties.fechaRenovacion = fechaRenovacion;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona una fecha con 11 días anterior para una poliza a siniestrar/endosar/cancelar.", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaPolizaOnceDiasAntes() {

		try {
			loadingTIAGeneral();

			//
			LocalDateTime fechaDiaAnterior = LocalDateTime.now();
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			String fecha = fechaDiaAnterior.plusDays(-12).format(fa);
			System.out.println("Fecha actual = " + fecha);
			//
			Events.sendKeys(fechaInicioPoliza, fecha);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona una fecha anterior para una poliza a siniestrar/endosar/cancelar.", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaPolizaConFechaVariable(int dias) {

		try {
			loadingTIAGeneral();

			//
			LocalDateTime fechaDiaAnterior = LocalDateTime.now();
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			String fecha = fechaDiaAnterior.plusDays(-dias).format(fa);
			System.out.println("Fecha actual = " + fecha);
			//
			Events.sendKeys(fechaInicioPoliza, fecha);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el tipo de endoso que se modificará posteriormente", page = "Manejo Poliza", functionality = "Endosos")
	public static void seleccionarTipoEndosoConDiasPosterior(String tipoEndoso) {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonAjustarPoliza);
			loadingTIAGeneral();
			String fechaSiniestro = LocalDateTime.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			Events.sendKeys(inputFechaAjuste, fechaSiniestro);
			Events.selectByContainsText(selectTipoEndoso, tipoEndoso);
			Events.clickButton(buttonAceptarAjuste);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar evento en siniestro ", page = "Manejo de objetos")
	public static void seleccionarEvento(String opcionEvento) {
		try {
			loadingTIAGeneral();
			String opcionRetornadaString = Events.selectByContainsText(selectListaEvento, opcionEvento);
			Validation.trueBooleanCondition(opcionRetornadaString.contains(opcionEvento),
					String.format("Se selecciono correctamente la opción %s", opcionEvento),
					String.format("No se encontro el valor %s dentro del listado del Evento", opcionEvento));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Seleccionar Causa en siniestro ", page = "Manejo de objetos")
	public static void seleccionarCausa(String opcionCausa) {
		try {
			loadingTIAGeneral();
			String opcionRetornadaString = Events.selectByContainsText(selectCasua, opcionCausa);
			Validation.trueBooleanCondition(opcionRetornadaString.contains(opcionCausa),
					String.format("Se selecciono correctamente la opción %s en el listado de Causa", opcionCausa),
					String.format("No se encontro el valor %s dentro del listado de la causa", opcionCausa));
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Validar selección de cobertura", page = "Manejo de objetos")
	public static void seleccionarNumeroCobertura() {
		try {
			loadingTIAGeneral();
			Events.clickButton(spanBuscarNumeroCobertura);
			loadingTIAGeneral();
			Events.clickButton(labelCoberturaActiva);
			Events.clickButton(buttonAceptar1);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Validar el rellenado de la subcobertura de forma automatica ", page = "Manejo de objetos")
	public static void validarLlenadoSubCoberturaAutomatico() {
		try {
			String opcionSeleccionada = Events.getAtributte(selectSubCoberturaNumero, "textContent");
			Validation.trueBooleanCondition(opcionSeleccionada.trim() != null || opcionSeleccionada.trim() != "",
					String.format("Se selecciono correctamente la opción %s de forma automática", opcionSeleccionada),
					"No se realizo de forma automatica el llenado del campo 'Sub Cobertura N°'");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Valida el porcentaje de la comision", page = "Emision", functionality = "Emision")
	public static void validarPorcentajeComision(String porcentaje) {

		try {

			String pc = Events.getAtributte(porcentajeComision, "value");
			Validation.trueBooleanCondition(pc.equalsIgnoreCase(porcentaje), "Comision correcta",
					"Comision incorrecta");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}
	
	@Context(description = "Verificar que se haya ingresado el porcentaje de la comision.", page = "Emision", functionality = "Emision")
	public static void verificarPorcentajeComision() {

		try {
			loadingTIAGeneral();
			String pc = Events.getAtributte(porcentajeComision, "value");
			if (!pc.isBlank()) {
				Utils.outputInfo(String.format("Valor de comisión identificado: %s", pc));
			} else {
				Utils.outputInfo("No existe un valor de comisión registrado.");
			}

		} catch (Exception e) {
			Utils.eventFailed("No se pa podido verificar el porcentaje de comisión: " + e.getMessage());
		}
	}

	@Context(description = "Selecciona plataforma de venta del producto", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarPlataforma(String plataforma) {

		try {
			loadingTIAGeneral(30);
			// Events.clickButton(selectPlataforma);
			// loadingTIAGeneral();
			String rtContenido = Events.selectByContainsText(selectPlataforma, plataforma);
			Utils.takeAdditionalScreenshot(String.format("Se selecciona la plataforma %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona Sponsor de venta del producto", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarSponsor(String sponsor) {

		try {
			loadingTIAGeneral();
			Events.clickButton(selectSponsor);
			loadingTIAGeneral();
			String rtContenido = Events.selectByContainsText(selectSponsor, sponsor);
			Utils.takeAdditionalScreenshot(String.format("Se selecciona el sponsor %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el agente", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarAgente(String conceptoAgente, String numAgente) {

		try {
			loadingTIAGeneral(60);
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Agentes de pólizas"));
			loadingTIAGeneral();
			Events.clickButton(buttonNuevoAgente);
			loadingTIAGeneral();
			Events.clickButton(selectCategoriaAgente);
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(selectCategoriaAgente, conceptoAgente);
			Utils.takeAdditionalScreenshot(String.format("Se selecciona la opción %s", rtContenido), "extra");
			Events.clickButton(imgBuscarNumAgente);
			loadingTIAGeneral();
			Events.sendKeys(inputNAgente, numAgente, Keys.ENTER);
			loadingTIAGeneral();
			Events.clickButton(buttonSeleccionarCorredor.replace("numAgente", numAgente));
			loadingTIAGeneral();
			Events.clickButton(buttonAceptar1);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inserta el porcentaje de participación que corresponde al agente.", page = "Manejo Póliza", functionality = "Emisión")
	public static void insertarParticipacionAgente(String montoParticipacion) {

		try {
			loadingTIAGeneral();
			Events.sendKeys(inputPctjParticipacion, montoParticipacion);
			String pctjIngresado = Events.getValue(inputPctjParticipacion);
			Validation.trueBooleanCondition(montoParticipacion.equals(pctjIngresado),
					String.format("Se ha introducido correctamente el porcentaje de participación del agente: %s.",
							pctjIngresado),
					"No se ha introducido correctamente el porcentaje de participación del agente.");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Valida el corredor seleccionado", page = "Emision", functionality = "Emision")
	public static void validarCorredoraSeleccionada(String nombreCorredora) {

		try {
			loadingTIAGeneral();
			String getNombreCorredora = inputNombreCorredora.replace("nombreCorredora", nombreCorredora);
			loadingTIAGeneral();
			Validation.trueBooleanCondition(
					Events.getAtributte(getNombreCorredora, "value").equalsIgnoreCase(nombreCorredora),
					"La corredora seleccionada es: " + Events.getAtributte(getNombreCorredora, "value"),
					"No se ha seleccionado la corredora correcta.");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresa la patente del vehiculo", page = "Manejo de objetos")
	public static void ingresarPatente(String patente) {
		try {
			Events.sendKeys(inputPatenteVehiculo, patente);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar el año del vehiculo", page = "Manejo de objetos")
	public static void ingresarAnioVehiculo(String anioVehiculo) {
		try {
			Events.sendKeys(inputAnioVehiculo, anioVehiculo);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar el modelo del vehiculo", page = "Manejo de objetos")
	public static void ingresarModeloVehiculo(String modeloVehiculo) {
		try {
			Events.sendKeys(inputModeloVehiculo, modeloVehiculo);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar tipo de vehiculo", page = "Manejo de objetos")
	public static void seleccionarTipoVehiculo(String tipoVehiculo) {
		try {
			Events.selectByContainsText(selecTipoVehiculo, tipoVehiculo);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar Transferencia Premium", page = "Manejo de poliza")
	public static void seleccionarTransferenciaPremium(String transferenciaPremium) {
		try {
			Events.selectByExactText(selectTransferenciaPremium, transferenciaPremium);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar marca del vehiculo", page = "Manejo de objetos")
	public static void ingresarMarcaVehiculo(String marcaVehiculo) {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputMarcaVehiculo, marcaVehiculo);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar numero de motor del vehiculo", page = "Manejo de objetos")
	public static void ingresarNumMotorVehiculo(String numMotorVehiculo) {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.sendKeys(inputNumMotorVehiculo, numMotorVehiculo);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la cobertura para el evento y causa definida", page = "Manejo Objeto", functionality = "Siniestros")
	public static void seleccionarCobertura(String cobertura) {
		try {
			Events.clickButton(spanBuscarNumeroCobertura);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelCoberturaS.replace("cobertura", cobertura));
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonAceptaSeleccion);
			String inputCober = Events.getText(inputCobertura);

			Validation.trueBooleanCondition(!inputCobertura.equalsIgnoreCase(""),
					"La cobertura se ha seleccionado correctamente. N cobertura de cobertura: " + inputCober,
					"La cobertura no se ha seleccionado correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "localizacion en el Mapa", page = "Manejo Objeto", functionality = "Siniestros")
	public static void localizacionMapa(String localizacion) {
		try {
			Events.sendKeys(inputLocalizacionMapa, localizacion);
			loadingTIAGeneral();
			Events.teclaAbajo(inputLocalizacionMapa, 1);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar el modulo Reservas y Pagos")
	public static void seleccionarModuloReservasPagos() {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelReservaYPagos);
			TransversalEvents.loadingTIAGeneral();

			String tituloPagosReserva = Events.getText(labelPagosReservas);
			TransversalEvents.loadingTIAGeneral();
			Validation.trueBooleanCondition(tituloPagosReserva.equalsIgnoreCase("total de siniestros"),
					"Se despliega correctamente el modulo Reservas y Pagos",
					"No se desplego el modulo Resrvas y Pagos");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Seleccionar modulo Terceros")
	public static void seleccionarModuloTerceros() {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelTerceros);
			TransversalEvents.loadingTIAGeneral();

			String tituloTerceros = Events.getText(labelTituloTerceros);
			TransversalEvents.loadingTIAGeneral();
			Validation.trueBooleanCondition(tituloTerceros.equalsIgnoreCase("Terceros"),
					"Se despliega correctamente el modulo Terceros", "No se desplego el modulo Terceros");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Seleccionar modulo Terceros")
	public static void seleccionarModuloDetalleSiniestro() {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelDetalleDeSiniestro);
			TransversalEvents.loadingTIAGeneral();

			String tituloDetalleEvento = Events.getText(labelDetalleEvento);
			TransversalEvents.loadingTIAGeneral();
			Validation.trueBooleanCondition(tituloDetalleEvento.equalsIgnoreCase("Detalles del evento"),
					"Se despliega correctamente el Detalles del evento",
					"No se desplego el modulo Detalles del evento");

			SiniestroProperties.numeroSiniestro = Events.getValue(inputIdSiniestro);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Seleccionar modulo de la Poliza Siniestro")
	public static void seleccionarModuloPolizaSiniestro() {
		try {
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(labelModuloPoliza);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Seleccionar ID linea de Poliza")
	public static void seleccionarIdLineaPoliza(String idLineaPoliza) {
		try {
			Events.selectByExactText(selectIDLineaPoliza, idLineaPoliza);
			loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Obtiene la fecha actual.", page = "Manejo Objeto", functionality = "Manejo Objeto")
	public static String fechaActualddmmyyyy() {
		String fecha = "";
		try {
			loadingTIAGeneral();

			//
			LocalDateTime fechaDiaAnterior = LocalDateTime.now();
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			fecha = fechaDiaAnterior.format(fa);
			System.out.println("Fecha actual = " + fecha);
			//

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
		return fecha;
	}

	@Context(description = "Obtiene la fecha actual.", page = "Manejo Objeto", functionality = "Manejo Objeto")
	public static String fechaAnioAnterior() {
		String fecha = "";
		try {
			loadingTIAGeneral();

			//
			LocalDateTime fechaDiaAnterior = LocalDateTime.now();
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			fecha = fechaDiaAnterior.plusDays(-1).plusYears(-1).format(fa);
			System.out.println("Fecha con un dia y año anterior = " + fecha);
			//

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
		return fecha;
	}

	@Context(description = "Corroborar la información de la entidad", page = "Entidad", functionality = "Manejo Objeto")
	public static void validarInformacionEntidad() {
		try {
			String rutEntidadObtenido = Events.getText(labelRutContratanteResumen);
			String nombreEntidadObtenido = Events.getText(labelNombreContratanteResumen);
			String correoEntidadObtenido = Events.getText(labelCorreoContratanteResumen);

			Validation.trueBooleanCondition(
					rutEntidadObtenido.contains(EntidadProperties.rutContratante.replace(".", "")),
					"Rut visualizado es correcto",
					String.format("El rut visualizado en TIA '%s' no coincide con el rut ingresado en el portal '%s'",
							rutEntidadObtenido, EntidadProperties.rutContratante.replace(".", "")));

			Validation
					.trueBooleanCondition(
							nombreEntidadObtenido.trim().toLowerCase()
									.contains(EntidadProperties.nombreContratante.trim()
											.toLowerCase() + " "
											+ EntidadProperties.apellidoContratante.trim().toLowerCase()),
							"El nombre visualizado en TIA es correocto",
							String.format(
									"El nombre visualizado en TIA '%s' no coincide con el nombre ingresado en el portal '%s'",
									nombreEntidadObtenido, EntidadProperties.nombreContratante.trim().toLowerCase()
											+ " " + EntidadProperties.apellidoContratante.trim().toLowerCase()));

			Validation.trueBooleanCondition(
					correoEntidadObtenido.trim().toLowerCase()
							.contains(EntidadProperties.correoContratante.toLowerCase().trim()),
					"correo visualizado es correcto",
					String.format(
							"El correo visualizado en TIA '%s' no coincide con el rut ingresado en el portal '%s'",
							correoEntidadObtenido, EntidadProperties.correoContratante));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Corroborar la información que no se muestre deducible", page = "Siniestro", functionality = "Manejo Objeto")
	public static void validarNoDeducibleSiniestro() {
		try {
			boolean botonReservaYPagos = validarElementoPantalla(buttonReservaYPagos);
			if (botonReservaYPagos == true) {
				Utils.outputInfo("Se ha encontrado la seccion Reserva Y Pagos correctamente");
			} else {
				Utils.eventFailed("No se ha encontrado la seccion Reserva y Pagos ");
			}

			Events.clickButton(buttonReservaYPagos);
			TransversalEvents.loadingTIAGeneral();

			boolean noDeducible = validarElementoPantalla(labelSiniestroDeducible);
			if (noDeducible == true) {
				Utils.eventFailed("Se ha encontrado el deducible esto no debe suceder.");
			} else {
				Utils.outputInfo("Se valida correctamente que el deducible no existe");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Validar que se muestre el deducible automatico", page = "Siniestro", functionality = "Manejo Objeto")
	public static void validarMontoDeducibleSiniestro() {
		try {
			boolean botonReservaYPagos = validarElementoPantalla(buttonReservaYPagos);
			if (botonReservaYPagos == true) {
				Utils.outputInfo("Se ha encontrado la seccion Reserva Y Pagos correctamente");
			} else {
				Utils.eventFailed("No se ha encontrado la seccion Reserva y Pagos ");
			}

			Events.clickButton(buttonReservaYPagos);
			TransversalEvents.loadingTIAGeneral();

			String valorReservaDeducible = Events.getAtributte(labelReservaDeducible, "textContent");

			boolean vistaDeducible = validarElementoPantalla(labelSiniestroDeducible);
			if (vistaDeducible == true) {
				Utils.outputInfo(
						"Se valida correctamente que se encuentra el deducible, con el valor de la reserva de: "
								+ valorReservaDeducible + "");

			} else {
				Utils.eventFailed("No se ha encontrado el deducible correctamente.");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Validar que se muestre el deducible automatico", page = "Siniestro", functionality = "Manejo Objeto")
	public static void validarMontoIndemnizacionSiniestro() {
		try {
			boolean botonReservaYPagos = validarElementoPantalla(buttonReservaYPagos);
			if (botonReservaYPagos == true) {
				Utils.outputInfo("Se ha encontrado la seccion Reserva Y Pagos correctamente");
			} else {
				Utils.eventFailed("No se ha encontrado la seccion Reserva y Pagos ");
			}

			Events.clickButton(buttonReservaYPagos);
			loadingTIAGeneral();

			String valorReservaIndemnización = Events.getAtributte(labelReservaIndemnizacion, "textContent");

			boolean vistaIndemnización = validarElementoPantalla(labelSiniestroIndemnizacion);
			if (vistaIndemnización == true) {
				Utils.outputInfo(
						"Se valida correctamente que se encuentra la indemnización, con el valor de la reserva de: "
								+ valorReservaIndemnización + "");

			} else {
				Utils.eventFailed("No se ha encontrado el deducible correctamente.");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Validar si el 10% de la indeminzación es menor a 10 UF se mantenga en 10 UF", page = "Siniestro", functionality = "Manejo Objeto")
	public static void validarMontoMenorUF(String porcentaje, String minimo) {

		try {
			String montoIndemnizacion = Events.getAtributte(labelReservaIndeminizacion, "innerText");
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator(',');
			DecimalFormat decimal = new DecimalFormat();
			decimal.setDecimalFormatSymbols(symbols);
			float numero = decimal.parse(montoIndemnizacion).floatValue();
			float convMin = decimal.parse(minimo).floatValue();
			float convPorc = Float.parseFloat(porcentaje);
			float diezPorciento = numero * (convPorc / 100);

			String montoDeducible = Events.getAtributte(labelReservaDeducible, "innerText");
			float deducible = decimal.parse(montoDeducible).floatValue();

			if (diezPorciento < convMin && deducible == convMin) {

				Utils.outputInfo("Se valida que el monto minimo del deducible es  " + deducible + " UF");

			} else {
				Utils.eventFailed("El monto del deducible" + deducible + "no es el 10% de la indemnización");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Validar la modificacion del campo reserva", page = "Siniestro", functionality = "Manejo Objeto")
	public static void validarModificacionReserva(String montoReserva) {
		try {
			boolean inputReserva = validarElementoPantalla(inputReservaSiniestro);
			if (inputReserva == true) {
				Utils.outputInfo("Se ha encontrado el input de Reserva");
				String reservaActual = Events.getValue(inputReservaSiniestro);
				Events.sendKeys(inputReservaSiniestro, montoReserva);
				Events.clickButton(buttonGuardarReserva);
				loadingTIAGeneral();
				Validation.trueBooleanCondition(!reservaActual.equals(montoReserva),
						"Se ha actualizado correctamente el valor de la reserva",
						"No se ha actualizado correctamente el valor de la reserva");

			} else {
				Utils.eventFailed("No se ha encontrado el input de la Reserva ");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Verificar y guardar valor del monto reserva de Honorarios del liquidador", page = "Siniestro", functionality = "Manejo Objeto")
	public static void verificarMontoHonorariosLiquidador() {
		try {
			loadingTIAGeneral();
			PolizaProperties.reservaHonorarioLiquidador = Events.getText(spanMontoReservaHonorariosLiquidador);
			Utils.outputInfo("El monto de reserva para la fila \"Honorarios de liquidador\" es: "
					+ PolizaProperties.reservaHonorarioLiquidador);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Verificar cambio en el valor del monto reserva de Honorarios del liquidador", page = "Siniestro", functionality = "Manejo Objeto")
	public static void verificarCambioMontoHonorariosLiquidador() {
		try {
			loadingTIAGeneral();
			String nuevoMontoHonorario = Events.getText(spanMontoReservaHonorariosLiquidador);
			Double nuevoMonto = Double.parseDouble(nuevoMontoHonorario.replace(".", "").replace(",", "."));
			Double montoAnterior = Double
					.parseDouble(PolizaProperties.reservaHonorarioLiquidador.replace(".", "").replace(",", "."));
			Validation.trueBooleanCondition(
					nuevoMonto > montoAnterior, "Se ha actualizado correctamente el valor de la reserva, de "
							+ montoAnterior + " a " + nuevoMonto + ".",
					"No se ha actualizado correctamente el valor de la reserva");
		} catch (NumberFormatException e) {
			Utils.eventFailed("Error de formato al convertir montos: " + e.getMessage());
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar la información de indemnización de siniestro.", page = "Siniestro", functionality = "Siniestro")
	public static void validarTipoItem() {

		try {
			loadingTIAGeneral();
			String locatorTipoItem = String.format(spanTipoItemIndemnizacion, "Indemnización");
			Utils.outputInfo("Tipo de item detectado: " + Events.getText(locatorTipoItem));
			Utils.takeAdditionalScreenshot("Se muestra la información del tipo de item Indemnización", "extra");
			Events.clickButton(locatorTipoItem);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar monto de reserva.", page = "Siniestro", functionality = "Siniestro")
	public static void validarMontoReserva() {
		try {
			loadingTIAGeneral();
			String montoReserva = Events.getText(labelReservaIndemnizacion);
			Utils.outputInfo(String.format("Monto de reserva detectado: %s", montoReserva));
			Utils.takeAdditionalScreenshot("Se valida el valor de la reserva de: " + montoReserva + ".", "extra");
		} catch (Exception e) {
			Utils.eventFailed("No se ha podido detectar el monto de reserva: " + e.getMessage());
		}

	}

	@Context(description = "Validar el porcentaje de la UF", page = "Siniestro", functionality = "Manejo Objeto")
	public static void validarporcentajeUF(String porcentaje) {

		try {
			String montoIndemnizacion = Events.getAtributte(labelReservaIndeminizacion, "innerText");
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator(',');
			DecimalFormat decimal = new DecimalFormat();
			decimal.setDecimalFormatSymbols(symbols);
			float numero = decimal.parse(montoIndemnizacion).floatValue();
			float convPorc = Float.parseFloat(porcentaje);
			float diezPorciento = numero * (convPorc / 100);
			PolizaProperties.monto = montoIndemnizacion;

			String montoDeducible = Events.getAtributte(labelReservaDeducible, "innerText");
			float deducible = decimal.parse(montoDeducible).floatValue();

			if (diezPorciento == deducible) {
				Utils.outputInfo("Se valida que el monto " + deducible + " es el 10% de " + montoIndemnizacion
						+ " correspondiente al monto de indeminización");
			} else {
				Utils.eventFailed("El monto del deducible" + deducible + "no es el 10% de la indemnización");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Obtener información de una api", page = "API", functionality = "Obtener informacion")
	public static String obtenerPolizaRutEstadoAPI(String rutContratante, String estadoPol) {
		String resp = "";
		HttpURLConnection connection = null;
		try {
			String baseURLApi = "http://192.168.30.27/API_IntegracionCorePoliza/api/polizas";
			String paramRut = rutContratante.replace(".", "");
			String paramTipoPol = estadoPol.toUpperCase();
			String apiURL = String.format("%s?Rut=%s&TipoPoliza=%s", baseURLApi, paramRut, paramTipoPol);

			URL url = new URL(apiURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(240000);

			int responseCode = connection.getResponseCode();
			Utils.outputInfo(
					String.format("La conexión con la API Obtener póliza retorno con codigo %d", responseCode));
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				resp = response.toString();
			} else {
				Utils.eventFailed(String.format("La API '%s' retorno con codigo %s", baseURLApi, responseCode));
			}
			return resp;
		} catch (IOException e) {
			Utils.eventFailed(e.getMessage());
			System.out.print(e.getMessage());
			return e.getMessage();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

	}

	@Context(description = "Rescatar poliza", page = "API", functionality = "Obtener poliza")
	public static String obtenerPolizaAPIRutEstado(String rutContratante, String estadoPol, String idProducto) {
		String idPol = "";
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Z"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			Utils.outputInfo("Se inicia la consulta a la API");
			String infoApi = obtenerPolizaRutEstadoAPI(rutContratante, estadoPol);
			ZonedDateTime postEjecucionApi = ZonedDateTime.now(ZoneId.of("Z"));
			Utils.outputInfo(String.format("La duración de la obtención de la póliza mediante API tardo %s segundos",
					Long.toString(Duration.between(now, postEjecucionApi).getSeconds())));

			if (!infoApi.contains("{")) {
				Utils.outputInfo("La api no retorno información");
			} else {
				Utils.outputInfo("La API retorno la información correctamente");
			}
			if (infoApi.isBlank() || infoApi.isEmpty() || infoApi.trim().equals("") || infoApi.trim().equals(" ")) {
				Utils.eventFailed("El retorno de información de la API fue vacío");
			}
			JSONObject jsonObject = new JSONObject(infoApi);
			JSONArray polArray = jsonObject.getJSONArray("polizas");

			int idTransaccion = 0;
			for (int i = polArray.length() - 1; i >= 0; i--) {
				JSONObject pol = polArray.getJSONObject(i);

				// Parsear la fecha en formato ISO 8601
				ZonedDateTime dateTime = ZonedDateTime.parse(pol.get("fechaInicio").toString());

				// Obtener la fecha y hora actuales

				// Obtener el producto relacionada a la póliza
				JSONObject producto = pol.getJSONObject("producto");
				// logica para recorrer las pólizas e identificar las correspondiente a la fecha
				// actualy obtener la ultima registrada

				if (dateTime.toLocalDate().isEqual(now.toLocalDate())
						&& producto.get("id").toString().equals(idProducto.toUpperCase())) {

					Utils.outputInfo(String.format("La poliza: %s del producto %s, esta dentro de la fecha %s",
							pol.get("numeroPoliza"), producto.get("id"), now.format(formatter)));
					if (idTransaccion < pol.getInt("id")) {
						idPol = pol.getString("numeroPoliza");
						idTransaccion = pol.getInt("id");

					}
				}
			}
			if (idPol.isEmpty()) {
				Utils.eventFailed(
						String.format("No se encontro póliza para el día de hoy %s relacionada al producto %s",
								now.format(formatter), idProducto));
			}
			Utils.outputInfo(String.format("La poliza encontrada y a setear: %s", idPol));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		} finally {
			return idPol;
		}
	}

	@Context(description = "Corroborar la información de la entidad", page = "Entidad", functionality = "Manejo Objeto")
	public static void validarCorredor(String nombrePlataforma, String canalDeVenta) {

		try {
			// Obtener textos de la plataforma y el canal de ventas
			String nombreDeLaPlataforma = Events.getText(labelTipoPlataforma).trim().toLowerCase();
			String canalDeVentas = Events.getText(labelNombreCanalDeVentas).trim().toLowerCase();

			// Validar nombre de la plataforma
			Validation.trueBooleanCondition(nombreDeLaPlataforma.contains(nombrePlataforma.trim().toLowerCase()),
					"El nombre de la plataforma es correcto",
					String.format(
							"El nombre de la plataforma visualizado en TIA '%s' no coincide con el nombre ingresado en el portal '%s'",
							nombreDeLaPlataforma, nombrePlataforma));

			// Validar canal de ventas
			Validation.trueBooleanCondition(canalDeVentas.contains(canalDeVenta.trim().toLowerCase()),
					"El nombre visualizado en TIA es correcto",
					String.format(
							"El nombre visualizado en TIA '%s' no coincide con el nombre ingresado en el portal '%s'",
							canalDeVentas, canalDeVenta));

		} catch (Exception e) {
			// Manejo de errores
			Utils.eventFailed("Error al validar la información del corredor: " + e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarEntidadTIA() {

		try {
			Events.clickButton(TransversalElements.buttonEntidadTIA);
			loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresaraPlandePagos() {

		try {
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Cuenta"));
			Boolean pestañaPagos = TransversalPortalSBEvents.validarElementoPantalla(buttonPlandePago, 180);
			if (!pestañaPagos) {
				Utils.eventFailed("No se desplego la pantalla de plan de pago");
			}
			Events.clickButton(buttonPlandePago);
			loadingTIAGeneral();

			boolean despliegueFiltro = validarElementoPantalla(inputNPolizaPlanPago);
			if (!despliegueFiltro) {
				Events.clickButton(aFiltroPlanesPagoPlazo);
				loadingTIAGeneral();
			}
			WebElement elemento = getDriver().findElement(By.xpath(inputNPolizaPlanPago));
			elemento.sendKeys(PolizaProperties.idPoliza);
			elemento.sendKeys(Keys.ENTER);
			Utils.takeAdditionalScreenshot(String.format("El N° de la Póliza se visualiza correctamente", elemento),
					"extra");
			loadingTIAGeneral();
			String saldoCuentaTIA = Events.getAtributte(saldoCuenta, "textContent");
			loadingTIAGeneral();
			Validation.trueBooleanCondition(saldoCuentaTIA.trim().contains("0,000000"),
					"El saldo de la cuenta es " + saldoCuentaTIA + " y es correcto.",
					String.format("El saldo que se visualiza en pantalla '%s', no coinciden", saldoCuentaTIA));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarCrearEntidad() {
		try {
			boolean rsBuscarEntidad = validarElementoPantalla(aBuscarEntidad);
			if (!rsBuscarEntidad) {
				Utils.eventFailed("No se desplego correctamente el botón de 'Buscar Entidad'");
			}
			Events.clickButton(aBuscarEntidad);

			loadingTIAGeneral();

			boolean rsCampoApellido = validarElementoPantalla(inputApellido);
			if (!rsCampoApellido) {
				Utils.eventFailed("No se visualiza elemento esperado en pantalla: 'Campo Apellido'");
			}
			Utils.outputInfo("Se ingreso correctamente a la sección 'Busqueda entidad'");
			Utils.takeAdditionalScreenshot("ingresoBusquedaEntidad", "extra");

			boolean rsCrearEntidad = validarElementoPantalla(buttonCrearEntidad);
			if (!rsCrearEntidad) {
				Utils.eventFailed("No se encontro el botón 'Crear entidad");
			}
			Events.clickButton(buttonCrearEntidad);
			loadingTIAGeneral(90);

			Validation.trueBooleanCondition(validarElementoPantalla(hTituloCrearEntidad),
					"Se ingreso correctamente al modulo crear entidad", "No se ingreso al modulo");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Selecciona la póliza en el listado de pólizas del resumen de la entidad seleccionada previamente", page = "Manejo Poliza", functionality = "Endosos")
	public static void crearEntidadCorredor(String primerNombre, String segundoNombre, String apellidos,
			String fechaNacimiento, String tipoRutPasaporte, String rutPass, String codCMF,
			String categoriaTributaria) {
		try {

			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Tipo de entidad"));
			boolean rsRdEntidad = validarElementoPantalla(radioPersonaEntidad);
			if (!rsRdEntidad) {
				Utils.eventFailed("No se encontro el campo de 'Persona' dentro del modulo 'Tipo de Entidad'");
			}

			boolean checkedRdPersona = Boolean.parseBoolean(Events.getAtributte(radioPersonaEntidad, "checked"));
			if (!checkedRdPersona) {
				Events.clickButton(radioPersonaEntidad);
				Utils.outputInfo("Se ha seleccionado el tipo de entidad 'Persona'");
			}

			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Detalles de la entidad"));
			boolean rsPrimerNombre = validarElementoPantalla(inputPrimerNombreEntidad);
			boolean rsSegundoNombre = validarElementoPantalla(inputSegundoNombreEntidad);
			boolean rsApellido = validarElementoPantalla(inputApellidoEntidad);
			boolean rsTributario = validarElementoPantalla(selecCategoriaTributarioEntidad);
			boolean rsFechaNacimiento = validarElementoPantalla(inputFechaNacimientoEntidad);
			boolean rsTipoDocumento = validarElementoPantalla(selectTipoDocumentoEntidad);
			boolean rsNumeroDocumento = validarElementoPantalla(inputNumeroDocumentoEntidad);
			boolean rsCMF = validarElementoPantalla(inputCMFEntidad);
			String rut = "";
			if (rutPass.isEmpty() && tipoRutPasaporte.trim().toLowerCase().contains("rut")) {
				rut = generarRut();
				Utils.outputInfo(String.format("Se ha generado de forma automatica el siguiente rut: %s", rut));
			} else {
				rut = rutPass;
			}

			if (!rsPrimerNombre) {
				Utils.eventFailed("No se encontro el campo Primer nombre dentro del modulo 'Detalles de la entidad");
			}
			if (!rsSegundoNombre) {
				Utils.eventFailed("No se encontro el campo Segundo nombre dentro del modulo 'Detalles de la entidad");
			}
			if (!rsApellido) {
				Utils.eventFailed("No se encontro el campo Apellido dentro del modulo 'Detalles de la entidad");
			}
			if (!rsTributario) {
				Utils.eventFailed("No se encontro el campo Tributario dentro del modulo 'Detalles de la entidad");
			}
			if (!rsFechaNacimiento) {
				Utils.eventFailed("No se encontro el campo Fecha nacimiento dentro del modulo 'Detalles de la entidad");
			}
			if (!rsTipoDocumento) {
				Utils.eventFailed("No se encontro el campo Tipo documento dentro del modulo 'Detalles de la entidad");
			}
			if (!rsNumeroDocumento) {
				Utils.eventFailed("No se encontro el campo Numero documento dentro del modulo 'Detalles de la entidad");
			}
			if (!rsCMF) {
				Utils.eventFailed("No se encontro el campo CMF dentro del modulo 'Detalles de la entidad");
			}

			Events.sendKeysWithReturnVariableName(inputPrimerNombreEntidad, primerNombre);
			Events.sendKeysWithReturnVariableName(inputSegundoNombreEntidad, segundoNombre);
			Events.sendKeysWithReturnVariableName(inputApellidoEntidad, apellidos);
			Events.sendKeysWithReturnVariableName(inputFechaNacimientoEntidad,
					fechaNacimiento.replace("/", ".").replace("-", "."));
			Events.sendKeysWithReturnVariableName(inputCMFEntidad, codCMF);

			Events.selectByContainsText(selecCategoriaTributarioEntidad, categoriaTributaria);
			Events.selectByContainsText(selectTipoDocumentoEntidad, tipoRutPasaporte);

			Events.sendKeysWithReturnVariableName(inputNumeroDocumentoEntidad, rut.replace(".", ""));

			Utils.outputInfo("Se ingreso correctamente la información de la entidad persona");
			Utils.takeAdditionalScreenshot("infoEntidad", "extra");

			Utils.takeAdditionalScreenshot("crearEntidad", "extra");

			Events.clickButton(buttonGuardarEntidad);

			loadingTIAGeneral();

			Boolean despliegueIdEntidad = validarElementoPantalla(labelIdCrearEntidad);
			if (!despliegueIdEntidad) {
				Utils.eventFailed("No se ha creado el ID de la entidad que se ha intentado crear.");
			}
			String idEntidad = Events.getText(labelIdCrearEntidad);
			EntidadProperties.entidadIdTexto = idEntidad;
			Utils.outputInfo(String.format("El Id de entidad que se genero es: %s", idEntidad));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Generar rut de forma dinamica", page = "Manejo Poliza", functionality = "Endosos")
	public static String generarRut() {

		Random random = new Random();
		int min = 30000000;
		int max = 40000000;

		String randomRut = String.valueOf(random.nextInt(max - min + 1) + min);

		List<Integer> listaRut = new ArrayList<>();
		for (char c : randomRut.toCharArray()) {
			listaRut.add(Character.getNumericValue(c));
		}
		int sumaNumRut = 0;
		int numMultSerie = 2;
		for (int num = listaRut.size() - 1; num >= 0; num--) {

			if (numMultSerie >= 8) {
				numMultSerie = 2;
			}
			sumaNumRut += listaRut.get(num) * numMultSerie;

			numMultSerie += 1;
		}
		String calDv = "";

		int resto = sumaNumRut % 11;
		if (resto == 0) {
			calDv = "0";
		} else if (resto == 1) {
			calDv = "K";
		} else {
			calDv = Integer.toString(11 - resto);
		}

		return randomRut + "-" + calDv;

	}

	@Context(description = "Validar la modificacion del campo deducible", page = "Siniestro", functionality = "Manejo Objeto")
	public static void validarModificacionDeducible(String montoDeducible) {
		try {
			boolean inputReserva = validarElementoPantalla(labelReservaDeducible);
			if (inputReserva == true) {
				Utils.outputInfo("Se ha encontrado el label del Deducible");
				Events.clickButton(labelReservaDeducible);
				loadingTIAGeneral();
				String reservaActual = Events.getValue(inputReservaSiniestro);
				loadingTIAGeneral();
				Events.sendKeys(inputReservaSiniestro, montoDeducible);
				Events.clickButton(buttonGuardarReserva);
				loadingTIAGeneral();
				Validation.trueBooleanCondition(!reservaActual.equals(montoDeducible),
						"Se ha actualizado correctamente el valor del deducible",
						"No se ha actualizado correctamente el valor del deducible");

			} else {
				Utils.eventFailed("No se ha encontrado el input del deducible ");
			}

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar correo correspondiente al corredor", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarCorreosCorredor(String correoGenrico) {
		try {
			validarDespliegueModulos(
					imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Información del contacto"));

			List<String> listaEmails = new ArrayList<>();
			listaEmails.add("Email Poliza 1");
			listaEmails.add("Email Cobranza 1");
			listaEmails.add("Email Comisiones 1");
			listaEmails.add("Email Siniestros 1");
			listaEmails.add("Email Primas 1");

			for (String emails : listaEmails) {
				Events.clickButton(buttonNuevoContacto);
				loadingTIAGeneral();
				Events.selectByContainsText(selectTipoContacto, emails);
				loadingTIAGeneral();
				Events.sendKeysWithReturnVariableName(inputContacto, correoGenrico);
				loadingTIAGeneral();
			}
			Events.clickButton(buttonGuardarEntidad);
			loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar correo correspondiente al corredor", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarResumenEntidadCreada() {
		try {
			Events.clickButton(buttonResumen);
			loadingTIAGeneral();
			Boolean rsSegundoResumen = validarElementoPantalla(buttonResumen);
			if (rsSegundoResumen) {
				Events.clickButton(buttonResumen);
				loadingTIAGeneral();
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar a sección de cuenta en entidad", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void desplegarModuloCuenta() {
		try {
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Cuenta"));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar a sección de cuenta en entidad", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarCuenta(String tipoCuenta, String nombreCuenta) {
		try {
			Events.clickButton(buttonCuentas);
			loadingTIAGeneral();

			String tituloCuenta = Events.getText(labelTituloModulo);
			if (!tituloCuenta.trim().toLowerCase().contains("cuenta")) {
				Utils.eventFailed(
						String.format("No se ingreso al modulo esperado, se ingreso a %s y se espera ingresar a %s",
								tituloCuenta, "Cuentas"));
			}
			Events.clickButton(buttonCrearNuevaCuenta);
			loadingTIAGeneral();

			Events.selectByContainsText(selectTipoCuenta, tipoCuenta);
			loadingTIAGeneral();

			Events.sendKeysWithReturnVariableName(inputNombreCuenta, nombreCuenta);

			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Detalles de la cuenta"));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar a sección de detalle pago en entidad", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarDetallePago(String metodoPago, String nombreBanco, String numeroCBU,
			String tipoDeCuenta) {
		try {
			Events.clickButton(buttonDetallePago);
			loadingTIAGeneral();
			String tituloCuenta = Events.getText(labelTituloModulo);
			if (!tituloCuenta.trim().toLowerCase().contains("detalles de pago")) {
				Utils.eventFailed(
						String.format("No se ingreso al modulo esperado, se ingreso a %s y se espera ingresar a %s",
								tituloCuenta, "Resumen de detalles de pago"));
			}
			Events.clickButton(buttonNuevoDetallePago);
			loadingTIAGeneral();
			Events.selectByContainsText(selectMetodosPago, metodoPago);
			loadingTIAGeneral();
			Events.clickButton(buttonBuscarCodBanco);
			loadingTIAGeneral();

			WebElement campoBuscaBanco = getDriver().findElement(By.xpath(inputBuscarNombreBanco));

			campoBuscaBanco.click();
			campoBuscaBanco.sendKeys(nombreBanco);
			campoBuscaBanco.sendKeys(Keys.ENTER);
			loadingTIAGeneral();
			Utils.outputInfo(String.format("Se ingreso el valor: %s", nombreBanco));

			List<WebElement> lstBancos = Events.getElementList(listOpcionesBusquedaBanco);
			WebElement elementBanco = null;
			for (WebElement banco : lstBancos) {
				if (banco.getText().toLowerCase().contains(nombreBanco.toLowerCase())) {
					elementBanco = banco;
					break;
				}
			}

			if (elementBanco == null) {
				Utils.eventFailed("No se desplego el banco buscado");
			}
			elementBanco.click();
			Events.clickButton(buttonAceptar);
			loadingTIAGeneral();

			String numBanco = Events.getValue(inputIdBanco);

			if (numBanco.isBlank() || numBanco.isEmpty()) {
				Utils.eventFailed("No se desplego el id del banco al seleccionar el código del banco.");
			}

			Events.sendKeys(inputNumCBU, numeroCBU);

			// ingresta tipo cuenta
			try {
				Boolean rsSelectTipoCuenta = validarElementoPantalla(selectTipodeCuentaCorredor);
				if (!rsSelectTipoCuenta) {
					Utils.eventFailed("No se desplego el campo tipo cuenta");
				}

				Events.selectByContainsText(selectTipodeCuentaCorredor, tipoDeCuenta);

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			Utils.takeAdditionalScreenshot("datosDetallePago", "extra");

			Events.clickButton(buttonGuardar);
			loadingTIAGeneral();
			Utils.outputInfo("Se han cargado correctamente la información de detalle pago");
			Utils.takeAdditionalScreenshot("okDetallePAGO", "extra");

			Events.clickButton(buttonResumen);
			loadingTIAGeneral();

			String idEntidad = Events.getText(aResumenIdEntidad);
			if (idEntidad.isBlank() || idEntidad.isEmpty()) {
				Utils.eventFailed("No se retorno a la entidad correctamente");
			}
			Utils.outputInfo(String.format("Se retorno a la entidad %s", idEntidad));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar instruccion de pago entrante", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarInstruccionPagoEntrante() {
		try {

			boolean rsElement = validarElementoPantalla(aInstruccionPagoEntrante);
			if (!rsElement) {
				Utils.eventFailed("No se encuentra desplegado elemento Busqueda Instrucción Pago Entrante");
			}

			Events.clickButton(aInstruccionPagoEntrante);
			loadingTIAGeneral();

			boolean rsPopup = validarElementoPantalla(labelPopUpMetodoPago);
			if (!rsPopup) {
				Utils.eventFailed("No se desplego ventana emergente de busqueda instrucción de pago entrante");
			}

			boolean rsInstruccionPago = validarElementoPantalla(trPopupInstruccionPago);
			if (!rsInstruccionPago) {
				Utils.eventFailed("No se desplego instrucción de pago entrante");
			}

			Events.clickButton(trPopupInstruccionPago);
			loadingTIAGeneral();

			Events.clickButton(buttonAceptar);
			loadingTIAGeneral();

			String inpIns = Events.getValue(inputInstrucionPagoEntrante);

			Validation.trueBooleanCondition(!inpIns.isBlank(),
					String.format("Se selecciono la instruccion de pago %s.", inpIns),
					"No se obtuvo la instrucción de pago seleccionada previamente.");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar metodo de pago salientes", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarMetodoPagoSalientes(String pagoSaliente) {
		try {
			Boolean rsSelectPagoSal = validarElementoPantalla(selectMetodoPagoSaliente);
			if (!rsSelectPagoSal) {
				Utils.eventFailed("No se desplego el campo métodos de pago salientes");
			}

			Events.selectByContainsText(selectMetodoPagoSaliente, pagoSaliente);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar metodo de pago salientes", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarMetodoPagoEntrante(String pagoEntrante) {
		try {
			Boolean rsSelectPagoEnt = validarElementoPantalla(selectMetodoPagoEntrante);
			if (!rsSelectPagoEnt) {
				Utils.eventFailed("No se desplego el campo métodos de pago entrante");
			}

			Events.selectByExactText(selectMetodoPagoEntrante, pagoEntrante);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Crear enlace del corredor", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void vincularEnlaceCorredor(String corredor, String idCorredor) {
		{
			// presionar "Nuevo"
			loadingTIAGeneral();
			try {
				validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Agentes de pólizas"));
				Boolean rsBotonNuevo = validarElementoPantalla(buttonNuevo);
				if (!rsBotonNuevo) {
					Utils.eventFailed("No se desplego la Descripción para crear nuevo enlace a corredor");
				}
				Events.clickButton(buttonNuevo);
				loadingTIAGeneral();

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// llenar campos del nuevo enlace
			// 1- corredor

			try {
				loadingTIAGeneral();
				Boolean rsSelectTipoCuenta = validarElementoPantalla(selectTipoCorredor2);
				if (!rsSelectTipoCuenta) {
					Utils.eventFailed("No se desplego el campo tipo corredor");
				}
				loadingTIAGeneral();
				Events.selectByContainsText(selectTipoCorredor2, corredor);
				loadingTIAGeneral();

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// 2- asociar el corredor

			try {

				Events.clickButton(aInstruccionEntidadCorredor);
				loadingTIAGeneral();
				Events.sendKeysWithReturnVariableName(inputIdEntidadCorredor, idCorredor);
				WebElement elemento = getDriver().findElement(By.xpath(inputIdEntidadCorredor));
				elemento.sendKeys(Keys.ENTER);
				loadingTIAGeneral();
				Events.clickButton(trPopupSeleccionarAgente);
				loadingTIAGeneral();
				Events.clickButton(buttonAceptar);
				loadingTIAGeneral();

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// presionar boton guardar
			try {
				loadingTIAGeneral();
				Events.clickButton(buttonGuardar);
				loadingTIAGeneral(60);

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}
		}

	}

	@Context(description = "Ingresar metodo de Cobranza, instruccion de pago", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarCobranzaInstruccionPago() {
		try {
			Boolean rsBusqCobranzaPago = validarElementoPantalla(aCobranzaInstruccionPago);
			if (!rsBusqCobranzaPago) {
				Utils.eventFailed("No se desplego el campo Cobranza, instrucción de pago");
			}
			Events.clickButton(aCobranzaInstruccionPago);
			loadingTIAGeneral();

			boolean rsPopup = validarElementoPantalla(labelPopUpMetodoPago);
			if (!rsPopup) {
				Utils.eventFailed("No se desplego ventana emergente de busqueda instrucción de pago");
			}

			boolean rsInstruccionPago = validarElementoPantalla(trPopupInstruccionPago);
			if (!rsInstruccionPago) {
				Utils.eventFailed("No se desplego instrucción de pago");
			}

			Events.clickButton(trPopupInstruccionPago);
			loadingTIAGeneral();

			Events.clickButton(buttonAceptar);
			loadingTIAGeneral();

			String inpIns = Events.getValue(inputCobranzaInstrucionPago);
			loadingTIAGeneral();

			Validation.trueBooleanCondition(!inpIns.isBlank(),
					String.format("Se selecciono la instruccion de pago cobranza %s.", inpIns),
					"No se obtuvo la instrucción de pago seleccionada para cobranza previamente.");
			loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar metodo de pago salientes", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarInformacionEntidad() {
		try {
			Boolean rsIdEntidad = validarElementoPantalla(aResumenIdEntidad);
			if (!rsIdEntidad) {
				Utils.eventFailed("No se desplego ID de la entidad");
			}
			Events.clickButton(aResumenIdEntidad);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(step = "Presionar Boton Crear Siniestro", page = "Siniestro", functionality = "Siniestro")
	public static void presionarBotonCrearSiniestro() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGenerarSiniestro);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Presionar Boton Agregar Agente Poliza", page = "Siniestro", functionality = "Siniestro")
	public static void presionarBotonAgregarAgentePoliza() {

		try {
			loadingTIAGeneral();
			Events.clickButton(buttonAgregarAgentePoliza);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar moneda en cuenta", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarMonedaCuenta(String monedaCuenta) {
		try {
			Boolean rsSelectPagoSal = validarElementoPantalla(selectMonedaCuentaCorredor);
			if (!rsSelectPagoSal) {
				Utils.eventFailed("No se desplego el campo moneda");
			}

			Events.selectByContainsText(selectMonedaCuentaCorredor, monedaCuenta);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar Condiciones de Pago OUT", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarCondicionesdePagoOUT(String condicionesDePagoOUT) {
		try {
			Boolean rsSelectcondicionesDePagoOUT = validarElementoPantalla(selectCondicionesDePagoOut);
			if (!rsSelectcondicionesDePagoOUT) {
				Utils.eventFailed("No se desplego el campo moneda");
			}

			Events.selectByContainsText(selectCondicionesDePagoOut, condicionesDePagoOUT);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar a la información de la entidad", page = "Crear entidad", functionality = "Manejo Objeto")
	public static String ingresaralaInformaciondelaEntidad() {

		String entidadIdTexto = ""; // Variable para almacenar el texto del ID de la entidad

		try {
			Boolean rsIdEntidad = validarElementoPantalla(aResumenIdEntidad);
			if (!rsIdEntidad) {
				Utils.eventFailed("No se desplegó ID de la entidad");
			}
			entidadIdTexto = Events.getText(inputIdEntidad2); // Obtiene y almacena el texto
			Events.clickButton(inputIdEntidad2);

			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
		return entidadIdTexto; // Devuelve el texto para poder reutilizarlo
	}

	@Context(description = "Ingresar al detalle de informacion del corredor", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarADetallesdelCorredor() {
		try {
			Boolean rsBotonCorredor = validarElementoPantalla(buttonDetallesdelCorredor);
			if (!rsBotonCorredor) {
				Utils.eventFailed("No se desplego la Descripción General del Corredor");
			}
			Events.clickButton(buttonDetallesdelCorredor);
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Ingresar a la informacion del tipo de corredor", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void ingresarTipodeCorredor(String tipoCorredor) {
		// seleccionar tipo de corredor"
		try {
			Boolean rsSelectTipoCorredor = validarElementoPantalla(selectTipoCorredor);
			if (!rsSelectTipoCorredor) {
				Utils.eventFailed("No se desplego el tipo corredor");
			}

			Events.selectByContainsText(selectTipoCorredor, tipoCorredor);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Inserta fecha efectiva del corredor", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void insertarFechaEfectivaCorredor(String fechaEfectivaCorredor) {

		try {
			Events.sendKeys(inputFechaEfectivaCorredor, fechaEfectivaCorredor);
			Validation.trueBooleanCondition(!(fechaEfectivaCorredor.equals("")),
					"Se ha introducido correctamente la fecha del corredor", "No se ha introducido fecha del corredor");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar a sección de cuenta en entidad", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void desplegarModuloAgenteCorredor() {
		try {
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Cuenta"));

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Selecciona producto Hogar Full para siniestrar", page = "Manejo Objeto", functionality = "Emisión")
	public static void seleccionarProductoSiniestrar() {
		try {
			Events.clickButton(imgSeleccionarProducto);
			String tituloPagina = Events.getText(labelSiniestroH1);
			Utils.outputInfo("La sección actual es: " + tituloPagina);
			Validation.trueBooleanCondition(tituloPagina.contains("Crear siniestro"),
					"Se ha ingresado a la página para la creación de un siniestro de una póliza",
					"No se ha ingresado correctamente para crear un siniestro de una póliza");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crear enlace del corredor", page = "Crear entidad", functionality = "Manejo Objeto")
	public static void crearEnlaceCorredor(String canalDeVentas, String iDdelProducto, String plataformaCorredor,
			String tipoDePagoCorredor, String comisionPredeterminada, String comisionMinima, String comisionMaxima) {
		{
			// presionar flecha despliegue enlace del corredor
			// presionar "Nuevo Enlace"

			try {
				validarDespliegueModulos(
						imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Enlace del corredor"));
				Boolean rsBotonNuevoEnlace = validarElementoPantalla(buttonNuevoEnlace);
				if (!rsBotonNuevoEnlace) {
					Utils.eventFailed("No se desplego la Descripción General del Corredor");
				}
				Events.clickButton(buttonNuevoEnlace);
				loadingTIAGeneral();
			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}
			// llenar campos del nuevo enlace
			// 1- canal de ventas

			try {
				Boolean rsSelectCanaldeVentas = validarElementoPantalla(selectCanaldeVentasCorredor);
				if (!rsSelectCanaldeVentas) {
					Utils.eventFailed("No se desplego el canal de ventas");
				}

				Events.selectByExactText(selectCanaldeVentasCorredor, canalDeVentas);
				loadingTIAGeneral();

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// 2- ID del producto
			try {
				Boolean rsSelectIdProductoCorredor = validarElementoPantalla(selectIdProductoCorredor);
				if (!rsSelectIdProductoCorredor) {
					Utils.eventFailed("No se desplego el Id del Producto");
				}

				Events.selectByExactText(selectIdProductoCorredor, iDdelProducto);
				loadingTIAGeneral();

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// 3- Plataforma
			try {
				Boolean rsSelectPlataformaCorredor = validarElementoPantalla(selectPlataformaCorredor);
				if (!rsSelectPlataformaCorredor) {
					Utils.eventFailed("No se desplego la Plataforma del corredor");
				}

				Events.selectByExactText(selectPlataformaCorredor, plataformaCorredor);
				loadingTIAGeneral();

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// 4- Tipo de pago
			try {
				Boolean rsSelectTipodePagoCorredor = validarElementoPantalla(selectTipodePagoCorredor);
				if (!rsSelectTipodePagoCorredor) {
					Utils.eventFailed("No se desplego el tipo corredor");
				}

				Events.selectByExactText(selectTipodePagoCorredor, tipoDePagoCorredor);
				loadingTIAGeneral();

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// 5- Comision predeterminada

			try {
				Events.sendKeys(inputComisionPredeterminadaCorredor, comisionPredeterminada);
				Validation.trueBooleanCondition(!(comisionPredeterminada.equals("")),
						"Se ha introducido correctamente la comision predeterminada del corredor",
						"No se ha introducido fecha del corredor");

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// 6- Comision minima

			try {
				Events.sendKeys(inputComisionMinimaCorredor, comisionMinima);
				Validation.trueBooleanCondition(!(comisionMinima.equals("")),
						"Se ha introducido correctamente la comision predeterminada del corredor",
						"No se ha introducido fecha del corredor");

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// 7- Comision maxima

			try {
				Events.sendKeys(inputComisionMaximaCorredor, comisionMaxima);
				Validation.trueBooleanCondition(!(comisionMaxima.equals("")),
						"Se ha introducido correctamente la comision predeterminada del corredor",
						"No se ha introducido fecha del corredor");

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}

			// presionar boton guardar
			try {
				loadingTIAGeneral();
				Events.clickButton(buttonGuardar);
				loadingTIAGeneral(60);

			} catch (Exception e) {
				Utils.eventFailed(e.getMessage());
			}
			Events.clickButton(labelHome);
			loadingTIAGeneral();

		}

	}

	@Context(description = "Crea una nueva línea de póliza con fecha de Terminacion", page = "Manejo Póliza", functionality = "Emision")
	public static void crearNuevaLineaPolizaConFechaDeFinalizacion(String Dias) {
		try {
			loadingTIAGeneral();
			Events.clickButton(TransversalElements.buttonNuevaLineaPoliza);
			// driver.switchTo().frame("AFMaskingFrame");
			LocalDate fechaHoy = LocalDate.now();
			LocalDate fechaFutura = fechaHoy.plusDays(Long.parseLong(Dias));
			String fechaString = fechaFutura.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			loadingTIAGeneral();
			Events.sendKeys(fechaFinalizacionPoliza, fechaString);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(TransversalElements.buttonAceptar);
			loadingTIAGeneral();
			String tituloObjeto = Events.getText(TransversalElements.labelManejoObjeto);
			Utils.outputInfo("La sección actual es: " + tituloObjeto);
			if (!tituloObjeto.contains(PolizaProperties.producto)) {
				Validation.trueBooleanCondition((tituloObjeto.contains("Muerte Accidental")
						|| tituloObjeto.contains("Reembolso de Gastos Médicos")
						|| tituloObjeto.contains("Garantía extendida") || tituloObjeto.contains("Manejo de objetos")),
						"Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"1. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			} else {
				Validation.trueBooleanCondition(tituloObjeto.contains(PolizaProperties.producto),
						"Se ha creado el objeto de póliza para el producto correcto: " + PolizaProperties.producto,
						"2. No se ha creado el objeto de póliza correctamente" + tituloObjeto);
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea una nueva línea de póliza con fecha de Terminacion", page = "Manejo Póliza", functionality = "Emision")
	public static void crearNuevaLineaPolizaConFechaDeVenta(String Dias) {
		try {
			loadingTIAGeneral();
			LocalDate fechaHoy = LocalDate.now();
			LocalDate fechaFutura = fechaHoy.plusDays(Long.parseLong(Dias));
			String fechaString = fechaFutura.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			loadingTIAGeneral();
			Events.sendKeys(inputFechaVenta, fechaString);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crea una nueva línea de póliza con fecha de Terminacion", page = "Manejo Póliza", functionality = "Emision")
	public static void ingresarFechadeWorkpad(String Dias) {
		try {
			loadingTIAGeneral();
			LocalDate fechaHoy = LocalDate.now();
			LocalDate fechaFutura = fechaHoy.plusDays(Long.parseLong(Dias));
			String fechaString = fechaFutura.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			loadingTIAGeneral();
			Events.sendKeys(FechaPago, fechaString);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Crear item de cuenta", page = "Manejo Objeto", functionality = "Emisión")
	public static void CrearItemCuenta() {

		try {
			Events.clickButton(buttonCuenta1);
			TransversalEvents.loadingTIAGeneral(120);
			Events.clickButton(buttonCuenta2);
			TransversalEvents.loadingTIAGeneral(120);
			Events.clickButton(buttonWorkpad);
			TransversalEvents.loadingTIAGeneral(120);

			String labelWorkpad1 = Events.getText(labelWorkpad);
			Utils.outputInfo("Titulo mostrado: " + labelWorkpad1);
			TransversalEvents.loadingTIAGeneral(120);

			Events.clickButton(buttonCrearArea);

			TransversalEvents.loadingTIAGeneral(120);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Acepta el cuadro de diálogo de publicación de ítems", page = "Items de cuenta", functionality = "Publicación")
	public static void aceptarPublicacionItems() {
		try {
			TransversalEvents.loadingTIAGeneral(60);

			String textoDialogo = Events.getText(Advertencia);
			Utils.outputInfo("Texto del cuadro de diálogo: " + textoDialogo);

			Events.clickButton(buttonSiPublica);
			Utils.outputInfo("Se presionó el botón 'Sí' para confirmar publicación.");

			TransversalEvents.loadingTIAGeneral(120);

		} catch (Exception e) {
			Utils.eventFailed("Error al confirmar la publicación de ítems: " + e.getMessage());
		}
	}

	@Context(description = "Acepta el cuadro de diálogo de publicación de ítems", page = "Items de cuenta", functionality = "Publicación")
	public static void pagoMatch() {
		try {

			Events.clickButton(buttonPago);
			Utils.outputInfo("Se presionó el botón 'Sí' para confirmar publicación.");

			TransversalEvents.loadingTIAGeneral(60);
			String textoDialogo = Events.getText(labelPagoMatch);
			Utils.outputInfo("Texto del cuadro de diálogo: " + textoDialogo);

			TransversalEvents.loadingTIAGeneral(120);

		} catch (Exception e) {
			Utils.eventFailed("Error al confirmar la publicación de ítems: " + e.getMessage());
		}
	}

	@Context(description = "Accede a planes de pago a plazo", page = "Items de cuenta", functionality = "Publicación")
	public static void planesPago() {
		try {

			Events.clickButton(buttonPagoPlazo);
			Utils.outputInfo("Se presionó el botón deposito pago plazo");

			TransversalEvents.loadingTIAGeneral();
			String textoDialogo = Events.getText(labelItemsPago);
			Utils.outputInfo("Texto del cuadro de diálogo: " + textoDialogo);

			Events.sendKeys(inputPolizaPlanes, PolizaProperties.idPoliza, Keys.ENTER);
			Utils.outputInfo("Se filtra a traves del numero de poliza");
			TransversalEvents.loadingTIAGeneral();

			String textoDialogoSaldo = Events.getText(labelSaldo);
			Utils.outputInfo("Texto del cuadro de diálogo: " + textoDialogoSaldo);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed("Error al confirmar la publicación de ítems: " + e.getMessage());
		}
	}

	@Context(description = "Acceder a pago match", page = "Items de cuenta", functionality = "Publicación")
	public static void facturasPagoMatch() {
		try {

			if (!Events.isVisible(inputPoliza)) {
				Events.clickButton(buttonFiltro);
				Utils.outputInfo("Se presionó el botón de filtro");
				TransversalEvents.loadingTIAGeneral();
			} else {
				Utils.outputInfo("El campo de filtro ya estaba visible, no se presiona el botón");
			}

			Events.sendKeys(inputPoliza, PolizaProperties.idPoliza, Keys.ENTER);
			Utils.outputInfo("Se filtra a través del número de póliza");
			TransversalEvents.loadingTIAGeneral();

			Events.clickButton(buttonMarcar);
			Utils.outputInfo("Se marca la póliza");
			TransversalEvents.loadingTIAGeneral();

			Events.clickButton(buttonAnterior);
			Utils.outputInfo("Se presiona el botón anterior");
			TransversalEvents.loadingTIAGeneral();

			String labelWorkpad1 = Events.getText(labelWorkpad);
			Utils.outputInfo("Título mostrado: " + labelWorkpad1);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed("Error al confirmar la publicación de ítems: " + e.getMessage());
		}
	}

	@Context(description = "Ingresar titulo", page = "Manejo Objeto", functionality = "Emisión")
	public static void ingresarTitulo(String titulo) {

		try {
			Events.sendKeys(inputTitulo, titulo);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(buttonCrearItem);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar Fecha de deposito", page = "Manejo Objeto", functionality = "Emisión")
	public static void IngresarFecha(String fechaDeposito) {

		try {

			Events.sendKeys(FechaPago, fechaDeposito);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar Fecha de deposito", page = "Manejo Objeto", functionality = "Emisión")
	public static void IngresarIdEntidad(String idEntidad) {

		try {
			Events.sendKeys(inputId, idEntidad, Keys.ENTER);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Aceptar receptor", page = "Manejo Objeto", functionality = "Emisión")
	public static void clickBusquedaNroCuenta() {
		try {
			Events.clickButton(buttonBuscarNCuenta);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar Fecha de deposito", page = "Manejo Objeto", functionality = "Emisión")
	public static void IngresarNumeroCuenta(String numeroCuenta) {

		try {
			Events.sendKeys(inputNumero, numeroCuenta);
			TransversalEvents.loadingTIAGeneral(120);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar Fecha de deposito", page = "Manejo Objeto", functionality = "Emisión")
	public static void buscarTipodeCuenta(String tipoCuenta) {

		try {
			Events.sendKeys(inputTipoDeCuenta, tipoCuenta, Keys.ENTER);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar Fecha de deposito", page = "Manejo Objeto", functionality = "Emisión")
	public static void IngresarMontoMoneda(String montoMoneda) {

		try {
			Events.sendKeys(inputMontoMoneda, montoMoneda, Keys.ENTER);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar Fecha de deposito", page = "Manejo Objeto", functionality = "Emisión")
	public static void IngresarIdSiniestro(String idSiniestro) {

		try {
			Events.sendKeys(inputIdSiniestro, idSiniestro);
			TransversalEvents.loadingTIAGeneral();

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar item de pago", page = "Manejo Objeto", functionality = "Emisión")
	public static void itemPago(String tipoPago) {
		try {
			Events.selectByContainsText(selectTipoPago, tipoPago);
			loadingTIAGeneral();
			String seleccionTipoPago = Events.getAtributte(selectTipoPago, "title");
			Validation.trueBooleanCondition(!seleccionTipoPago.isEmpty(),
					String.format("Se ingreso el valor %s en el campo tipo pago", seleccionTipoPago),
					"No se pudo seleccionar un valor en el campo Tipo pago");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Validar item de pago cerrado", page = "Manejo Objeto", functionality = "Emisión")
	public static void validarItemPagoCerrado() {
		try {
			Events.scroll(spanEstadoItemPago);
			String estadoItemPago = Events.getText(spanEstadoItemPago);
			Validation.trueBooleanCondition(estadoItemPago.trim().equalsIgnoreCase("cerrado"),
					"El item de pago se encuentra cerrado.", "El item de pago se cuentra abierto.");
			String pag1 = Events.getText(spanCampoPagadoReservaPago);
			String pagadoItem = Events.getText(spanPagadoItemPago);

			Validation.trueBooleanCondition(pag1.trim().equalsIgnoreCase(pagadoItem.trim()),
					"item de pago posee el pago correcto",
					"No coincide el campo pagado con la columna pagado del item de pago");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Aprobar pago", page = "Manejo Objeto", functionality = "Emisión")
	public static void aprobarPago() {
		try {
			String tituloItemPago = Events.getText(labelTituloItemPago);
			Validation.trueBooleanCondition(tituloItemPago.trim().equalsIgnoreCase("Items de pago"),
					"Se ingreso correctamente al item de pago en liquidar.",
					"No se ingreso a la ventana de items de pago al liquidar");
			Events.clickButton(aFinalizarPago);

			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Especificacion de pago", page = "Manejo Objeto", functionality = "Emisión")
	public static void especificacionPago(String numFactura) {
		try {
			String tituloItemPago = Events.getText(labelTituloEspecificacionPago);
			Validation.trueBooleanCondition(tituloItemPago.trim().equalsIgnoreCase("Especificación de pago"),
					"Se ingreso correctamente a la Especificación de pago en liquidar.",
					"No se ingreso a la ventana de Especificación de pago al liquidar");

			Events.clickButton(aBuscarInstruccionPago);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(spanInstruccionPago);
			TransversalEvents.loadingTIAGeneral();
			Events.clickButton(TransversalElements.buttonAceptar1);
			loadingTIAGeneral();
			;
			Events.sendKeys(textareaNumeroDeFactura, numFactura);
			TransversalEvents.loadingTIAGeneral();
			TransversalEvents.seleccionarFechaFactura();
			TransversalEvents.loadingTIAGeneral();
			String getContenidoInstruccionPago = Events.getValue(inputInstruccionPago);
			Validation.trueBooleanCondition(!getContenidoInstruccionPago.trim().isEmpty(),
					String.format("Se selecciono correctamente el valor %s dentro del campo Instruccion de pago",
							getContenidoInstruccionPago.trim()),
					"No se pudo seleccionar un valor dentro del campo Instruccion de pago.");

			Events.clickButton(buttonSiguiente);
			TransversalEvents.loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Aceptar receptor", page = "Manejo Objeto", functionality = "Emisión")
	public static void aceptarReceptor() {
		try {
			String nombreRecep = Events.getText(spanNombreDestinatario);
			if (nombreRecep.trim().isEmpty()) {
				Events.clickButton(spanAprobarReceptor);
				TransversalEvents.loadingTIAGeneral();
			} else {
				Utils.outputInfo("Item de pago ya tiene un receptor aprobado.");
			}
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}

	}

	@Context(description = "Acceder a la póliza en el arbol de Pólizas", page = "Manejo Poliza", functionality = "Emisión")
	public static void accederPolizaArbol() {
		try {
			loadingTIAGeneral();
			Events.clickButton(aObjetoPoliza);
			loadingTIAGeneral();
			String tituloPoliza = Events.getText(labelManejoObjeto);
			Utils.outputInfo("La sección actual es: " + tituloPoliza);
			Validation.trueBooleanCondition(tituloPoliza.contains("Manejo de objetos"),
					"Se ha ingresado al manejo de objetos de póliza para el producto correcto: "
							+ PolizaProperties.producto,
					"No se ha ingresado a la línea de póliza para el objeto de póliza correctamente");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el origen del producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarCodigoSic(String codigoSic) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(selectCodigoSic, codigoSic);
			Utils.takeAdditionalScreenshot(String.format("Se ingresa el origen %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el origen del producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarOrigen(String origen) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(selectOrigen, origen);
			Utils.takeAdditionalScreenshot(String.format("Se selecciona el origen %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona Sub Ramo CMF para el producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarSubRamoCmf(String subRamoCmf) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(selectSubRamoCmf, subRamoCmf);

			Utils.takeAdditionalScreenshot(String.format("Se selecciona Sub Ramo CMF %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona tipo de deducible para el producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarTipoDeducible(String tipoDeducible) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(selectTipoDeducible, tipoDeducible);

			Utils.takeAdditionalScreenshot(String.format("Se selecciona tipo de deducible %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar el deducible de la póliza", page = "Manejo Póliza", functionality = "Transversal")
	public static void ingresarDeducible(String deducible) {
		try {
			loadingTIAGeneral();
			Events.sendKeys(inputDeducible, deducible);
			Utils.takeAdditionalScreenshot(String.format("Se ingresa monto de deducible: %s", deducible), "extra");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona el tipo de póliza que se quiere crear.", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarTipoPoliza(String tipoPoliza) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(elementGenerico.replace("TextoBuscado", "Tipos de Pólizas"),
					tipoPoliza);

			Utils.takeAdditionalScreenshot(String.format("Se selecciona tipo de póliza %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona contrato FAC para el producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarContratoFac(String contratoFac) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(selectContratoFac, contratoFac);

			Utils.takeAdditionalScreenshot(String.format("Se selecciona el contrato FAC %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Inserta el porcentaje de comisión.", page = "Manejo Póliza", functionality = "Emisión")
	public static void insertarComision(String montoComision) {

		try {
			loadingTIAGeneral();
			Events.sendKeys(elementGenerico.replace("TextoBuscado", "comisión"), montoComision);
			loadingTIAGeneral();
			String pctjIngresado = Events.getValue(elementGenerico.replace("TextoBuscado", "comisión"));
			Validation.trueBooleanCondition(montoComision.equals(pctjIngresado),
					String.format("Se ha introducido correctamente el porcentaje de comisión: %s.", pctjIngresado),
					"No se ha introducido correctamente el porcentaje de comisión.");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que el módulo \"% of Geographical Worksplit\" esté desplegado.", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarModuloInformacionPago() {
		try {
			loadingTIAGeneral();
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Información"));
			loadingTIAGeneral();
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ingresar la fecha de vencimiento del pago de la cuota de la póliza.", page = "Manejo Objeto", functionality = "Emision")
	public static void ingresarHoyFechaVencimientoInfoPago() {
		try {
			loadingTIAGeneral();
			LocalDateTime fechaHoy = LocalDateTime.now();
			String fechaString = fechaHoy.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			Events.sendKeys(inputGenericoManejoPoliza.replace("TextoBuscado", "Fecha vencimiento"),
					fechaString);
			Utils.takeAdditionalScreenshot(
					String.format("Se ingresa la fecha de vencimiento en la sección \"Información de pago\": %s",
							fechaString),
					"extra");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona Tipo de moneda para el producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarCanalPago(String canalPago) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(elementGenerico.replace("TextoBuscado", "Canal de Pago"),
					canalPago);

			Utils.takeAdditionalScreenshot(String.format("Se selecciona el canal de pago %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}
	
	@Context(description = "Selecciona Tipo de moneda para el producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarPProvisoria(String provisoria) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events.selectByExactText(elementGenerico.replace("TextoBuscado", "Tipos de Pólizas"),
					provisoria);

			Utils.takeAdditionalScreenshot(String.format("Se selecciona el tipo de póliza %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}
	
	@Context(description = "Selecciona Tipo de moneda para el producto", page = "Manejo Póliza", functionality = "Transversal")
	public static void seleccionarCodigoMoneda(String codigoMoneda) {

		try {
			loadingTIAGeneral();
			String rtContenido = Events
					.selectByContainsText(elementGenerico.replace("TextoBuscado", "Código de Moneda"), codigoMoneda);

			Utils.takeAdditionalScreenshot(String.format("Se selecciona el código de moneda %s", rtContenido), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que el módulo \"Verificación de cobertura de reaseguro\" esté desplegado.", page = "Manejo línea de póliza", functionality = "Emision")
	public static void verificarModuloCoberturaReaseguroDesplegado() {
		try {
			TransversalEvents.loadingTIAGeneral();
			loadingTIAGeneral();
			validarDespliegueModulos(
					imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Verificación de cobertura de reaseguro"));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Actualizar la información del módulo \"Verificación de cobertura de reaseguro\".", page = "Manejo línea de póliza", functionality = "Emision")
	public static void actualizarCoberturaReaseguro() {
		try {
			loadingTIAGeneral();
			Events.clickButton(buttonActualizarVerificacionReaseguro);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que el módulo \"Tratados aplicables\" esté desplegado.", page = "Manejo línea de póliza", functionality = "Emision")
	public static void verificarModuloTratadosAplicablesDesplegado() {
		try {
			TransversalEvents.loadingTIAGeneral();
			loadingTIAGeneral();
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Tratados"));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Guardar la información del el módulo \"Tratados aplicables\".", page = "Manejo línea de póliza", functionality = "Emision")
	public static void guardarTratadosAplicables() {
		try {
			loadingTIAGeneral();
			Events.clickButton(buttonGuardarTratadosAplicables);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Ajustar la información de la sección \"Tratado aplicables\".", page = "Manejo línea de póliza", functionality = "Emision")
	public static void ajustarTratadosAplicables() {
		try {
			loadingTIAGeneral();
			Events.clickButton(checkBoxTratadoFilaUno);
			Utils.takeAdditionalScreenshot(String.format("Se deja solo un tratado activo"), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar cobertura de reaseguro para la poliza casco marítimo", page = "Manejo línea de póliza", functionality = "Emisión")
	public static void verificarReaseguro(String nroProgramaBuscado) {
		loadingTIAGeneral();
		ElementFetch elementFetch = new ElementFetch();
		WebElement element = elementFetch.getWebElement("CSS", spanNroProgamaCoberturaReaseguro);
		String nroProgramaVerificar = element.getText();
		Validation.trueBooleanCondition(nroProgramaVerificar.equals(nroProgramaBuscado),
				"El n° de programa buscado " + nroProgramaBuscado + " coincide con el contrato FAC en el sistema: "
						+ nroProgramaVerificar,
				"No se podido verificar la cobertura de reaseguro de la póliza correctamente.");
		Utils.takeAdditionalScreenshot("Se verifica reaseguro", "Extra");
	}

	@Context(description = "Seleccionar una fecha de término de la póliza ingresando la cantidad de días futuros.", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarFechaFuturaDias(String cantidadDias) {
		try {
			LocalDate fechaHoy = LocalDate.now();
			LocalDate fechaFutura = fechaHoy.plusDays(Long.parseLong(cantidadDias));
			String fechaString = fechaFutura.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			Events.sendKeys(inputGenericoManejoPoliza.replace("TextoBuscado", "Fecha de renovación"), fechaString);
			TransversalEvents.loadingTIAGeneral();
			Utils.takeAdditionalScreenshot(String.format("Se selecciona fecha " + cantidadDias
					+ " días adicionales a la fecha de hoy, correspondiente a " + fechaString), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar una fecha de término de la póliza ingresando la cantidad de meses.", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarCambioFechaMeses(String cantidadMeses) {
		try {
			LocalDate fechaFutura = LocalDate.now().plusMonths(Long.parseLong(cantidadMeses));
			String fechaString = fechaFutura.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			Events.sendKeys(inputGenericoManejoPoliza.replace("TextoBuscado", "Fecha de renovación"), fechaString);
			loadingTIAGeneral();
			Utils.takeAdditionalScreenshot(String.format("Se selecciona fecha %s.", fechaString), "extra");
			loadingTIAGeneral();
			PolizaProperties.fechaRenovacion = fechaString;
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Selecciona la fecha de inicio de cobertura de la póliza", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarVencimientoAnno() {

		try {
			Events.clickButton(buttonSeleccionarFechaInicioPoliza);
			Events.clickButton(buttonAumentarAnnoPoliza);
			Events.clickButton(buttonSeleccionarDia);
			Utils.takeAdditionalScreenshot(
					"Se ingresa fecha de vencimiento de pago, con un año extra a la fecha de hoy", "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona una fecha anterior para una poliza a siniestrar/endosar/cancelar.", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaSiniestroDosDiasAntes() {

		try {
			loadingTIAGeneral();
			LocalDateTime fechaHoraAntesAyer = LocalDateTime.now().minusDays(2);
			DateTimeFormatter fa = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
			String fechaSiniestro = fechaHoraAntesAyer.format(fa);
			Utils.outputInfo(
					"Fecha para ingresar con dos días antes de la fecha actual corresponde a: " + fechaSiniestro);
			Events.sendKeys(elementGenerico.replace("TextoBuscado", "Fecha del siniestro"), fechaSiniestro);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(step = "Selecciona una fecha meses antes o posteriores para siniestrar.", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarFechaSiniestroMesesAntesDespues(String cantidadMeses) {

		try {
			loadingTIAGeneral();
			LocalDateTime fechaHoy = LocalDateTime.now();
			LocalDateTime fechaModificada = fechaHoy.plusMonths(Long.parseLong(cantidadMeses));
			String fechaString = fechaModificada.format(DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm"));
			Utils.outputInfo("Fecha para ingresar con dos días antes de la fecha actual corresponde a: " + fechaString);
			Events.sendKeys(elementGenerico.replace("TextoBuscado", "Fecha del siniestro"), fechaString, Keys.ENTER);
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que el módulo \"Riesgo\" esté desplegado.", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarModuloRiesgoDesplegado() {
		try {
			loadingTIAGeneral();
			validarDespliegueModulos(imgDespliegueModuloInteroObjeto.replace("TituloModulo", "riesgo"));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que el módulo \"Información del asegurado y contratante\" esté desplegado.", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarModuloInformacionAseguradoContratanteDesplegado() {
		try {
			loadingTIAGeneral();
			validarDespliegueModulos(
					imgDespliegueModuloInteroObjeto.replace("TituloModulo", "Información del asegurado y contratante"));
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que el tiempo entre el inicio de la póliza y la fecha de renovación sea de un año.", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarDuracionAnioPoliza() {
		try {
			loadingTIAGeneral();
			String fechaInicioPoliza = Events
					.getValue(elementGenerico.replace("TextoBuscado", "Fecha de inicio de cobertura"));
			loadingTIAGeneral();
			String fechaRenovacionPoliza = Events
					.getValue(elementGenerico.replace("TextoBuscado", "Fecha de renovación"));
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate fInicioPoliza = LocalDate.parse(fechaInicioPoliza, formato);
			LocalDate fRenovacionPoliza = LocalDate.parse(fechaRenovacionPoliza, formato);

			LocalDate fechaEsperada = fInicioPoliza.plusYears(1);

			Validation.trueBooleanCondition(fechaEsperada.equals(fRenovacionPoliza),
					String.format(
							"La duración de la póliza es de un año: fecha de inicio: %s - fecha de renovación: %s.",
							fInicioPoliza, fechaEsperada),
					"La duración es diferente a un año.");

		} catch (Exception e) {
			Utils.eventFailed("Error al validar el año de vigencia: " + e.getMessage());
		}
	}

	@Context(description = "Verificar fecha de poliza después de la renovación.", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarActualizacionFechasPolizaRenovada() {
		try {
			loadingTIAGeneral();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd.MM.yyyy");

			// Capturar datos de la interfaz
			String fechaInicioRenovada = Events
					.getValue(elementGenerico.replace("TextoBuscado", "Fecha de inicio de cobertura"));
			loadingTIAGeneral();
			String fechaRenovacionRenovada = Events
					.getValue(elementGenerico.replace("TextoBuscado", "Fecha de renovación"));

			// Formatear las fechas de inicio y renovación caturadas al crear la póliza
			LocalDate fInicioAnterior = LocalDate.parse(PolizaProperties.fechaInicio, formato);
			LocalDate fRenovacionAnterior = LocalDate.parse(PolizaProperties.fechaRenovacion, formato);

			// Capturar y formatear las fechas post renovación
			LocalDate fInicioPolizaRenovada = LocalDate.parse(fechaInicioRenovada, formato);
			LocalDate fRenovacionPolizaRenovada = LocalDate.parse(fechaRenovacionRenovada, formato);

			// Calcular las fechas esperadas
			LocalDate fechaEsperadaInicio = fInicioAnterior.plusYears(1);
			LocalDate fechaEsperadaRenovacion = fRenovacionAnterior.plusYears(1);

			// validaciones y mensajes de salida
			boolean fInicioOk = fechaEsperadaInicio.equals(fInicioPolizaRenovada);
			boolean fRenovacionOk = fechaEsperadaRenovacion.equals(fRenovacionPolizaRenovada);

			String detallePaso = String.format(
					"Fecha de inicio anterior: %s; Fecha de inicio después de renovar: %s; Fecha de renovación anterior: %s; Fecha de renovación después de renovar: %s.",
					fInicioAnterior.format(formato), fInicioPolizaRenovada.format(formato),
					fRenovacionAnterior.format(formato), fRenovacionPolizaRenovada.format(formato));
			Validation.trueBooleanCondition(fInicioOk && fRenovacionOk, "Actualización exitosa. " + detallePaso,
					"Error en actualización de fechas. " + detallePaso);
		} catch (DateTimeParseException e) {
			Utils.eventFailed("Error de formato en las fechas. No se pudo procesar la comparación: " + e.getMessage());
		} catch (Exception e) {
			Utils.eventFailed("Fallo inesperado al validar fechas post-renovación: " + e.getMessage());
		}
	}

	@Context(description = "Selecciona la opción de \"Categoría de respaldo\" al realizar un endoso.", page = "Manejo Poliza", functionality = "Endosos")
	public static void seleccionarCategoriaRespaldo(String categoriaRespaldo) {

		try {
			loadingTIAGeneral();
			Events.selectByContainsText(elementGenerico.replace("TextoBuscado", "Categoría de respaldo"),
					categoriaRespaldo);

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Insertar una nota al realizar un endoso.", page = "Manejo Poliza", functionality = "Endosos")
	public static void insertarNota(String nota) {

		try {
			loadingTIAGeneral();
			Events.sendKeys(elementGenerico.replace("TextoBuscado", "Notas"), nota);
			loadingTIAGeneral();
			String strIngresado = Events.getValue(elementGenerico.replace("TextoBuscado", "Notas"));
			Validation.trueBooleanCondition(nota.equals(strIngresado),
					String.format("Se ha introducido correctamente la nota: %s.", strIngresado),
					"No se ha introducido correctamente la nota.");

		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar que carguen las acciones de envío de correos.", page = "Comunicación", functionality = "Siniestro")
	public static void verificarCargaEnvioCorreos(String... tiposEsperados) {
		try {
			loadingTIAGeneral();
			int intentos = 0;
			int maxIntentos = 5;

			while (intentos <= maxIntentos) {
				boolean mensajesCargados = true;

				for (String tipo : tiposEsperados) {
					String xpathNotificacion = String.format(spanNotificacionGenerico, tipo);

					if (!Events.isVisible(xpathNotificacion)) {
						mensajesCargados = false;
						break;
					}
				}

				if (mensajesCargados) {
					Utils.outputInfo(
							"Las actividades de envío de correos son visibles: " + String.join(", ", tiposEsperados));
					return;
				}

				if (intentos <= maxIntentos) {
					intentos++;
					Utils.outputInfo("Ejecutando reintento n°" + intentos + ".");
					Events.clickButton(divConsultarMedianteEjemplo);
					loadingTIAGeneral(20);
				} else {
					break;
				}
			}

			Utils.eventFailed("No se cargaron las actividades después de " + maxIntentos + " reintentos de consulta.");
		} catch (

		Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	private static boolean validarVisibleSalidaCorreo(String tipoSalida) {
		String xpathNotificacion = String.format(spanNotificacionGenerico, tipoSalida);
		String xpathSalidaCorreo = String.format(spanSalidaCartaGenerico, tipoSalida);
		return !BaseTest.getDriver().findElements(By.xpath(xpathNotificacion)).isEmpty()
				&& !BaseTest.getDriver().findElements(By.xpath(xpathSalidaCorreo)).isEmpty();
	}

	@Context(description = "Validar salida de correo de notificación de siniestro.", page = "Comunicación", functionality = "Siniestros")
	public static void ValidarSalidasCorreos(String... tiposEsperados) {
		try {
			loadingTIAGeneral();
			for (String tipo : tiposEsperados) {
				boolean existeSalida = validarVisibleSalidaCorreo(tipo);
				Validation.trueBooleanCondition(existeSalida,
						"El correo de notificación de " + tipo + " se ha enviado correctamente. ",
						"No se ha encontrado registro de la salida del correo: " + tipo);
			}
			Utils.takeAdditionalScreenshot("Se verifica salida de correo de Notificación de siniestro.", "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Verificar creación del nuevo ítem.", page = "Siniestro", functionality = "Siniestro")
	public static void verificarNuevoItem(String tipoItem) {

		try {
			loadingTIAGeneral();
			String locatorTipoItem = String.format(spanTipoItemIndemnizacion, tipoItem);
			Utils.outputInfo("Nuevo ítem detectado: " + Events.getText(locatorTipoItem));
			Utils.takeAdditionalScreenshot(
					String.format("Se verifica la creación de un nuevo ítem con el tipo de ítem %s.", locatorTipoItem),
					"extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}

	@Context(description = "Seleccionar una fecha de término de la póliza ingresando la cantidad de meses.", page = "Manejo Póliza", functionality = "Emision")
	public static void seleccionarFechaMesesDiasAntes(String cantidadMeses, String cantidadDias) {
		try {
			LocalDate fechaSeleccionar = LocalDate.now().plusMonths(Long.parseLong(cantidadMeses))
					.plusDays(Long.parseLong(cantidadDias));
			String fechaString = fechaSeleccionar.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			Events.sendKeys(inputGenericoManejoPoliza.replace("TextoBuscado", "Fecha vencimiento"), fechaString);
			loadingTIAGeneral();
			Utils.takeAdditionalScreenshot(String.format("Se selecciona fecha %s.", fechaString), "extra");
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	
	}
	@Context(description = "Desclickear las condiciones particulares" , page = "Manejo Objeto", functionality = "Emision")
	public static void desclickearCondicionesParticulares() {
		try {
			TransversalEvents.loadingTIAGeneral();
			if(Events.isSelected(checkboxCondiciones)== true) {
				Events.clickButton(checkboxCondiciones);
			
			} else {
				Utils.outputInfo("El elemento no esta seleccionado");
			}
			
		} catch (Exception e) {
			Utils.eventFailed(e.getMessage());
		}
	}
}
