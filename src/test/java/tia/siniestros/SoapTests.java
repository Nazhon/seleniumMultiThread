package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.pageSteps.tia.productos.SoapSteps;
import test.java.BaseTest;

public class SoapTests extends BaseTest {
	
	//Se desactivan test de SOAP porque no se peuden Siniestrar - Hasta abril

	@Test(priority = 1, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA156(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado, categoría lesionado, fallecido y beneficiario con indemnización si se cumple sobre: 300 UF en caso de muerte."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[13]);
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroMuerte(args[24], args[25], args[26], args[27]);
		TransversalSteps.generarSiniestro(args[32]);
		
		// Validar Detalles de Siniestro
		TransversalSteps.seleccionarModuloDetalleSiniestro();
		SoapSteps.validarDetallesSiniestro(args[15], args[4], args[13], args[49], args[4]);
		
		// Ir Terceros
		TransversalSteps.seleccionarModuloTerceros();
		// Agregar tercero
		SoapSteps.agregarTerceros(args[40], args[41], args[42],args[43], args[44], args[45]);
		SoapSteps.validarPartesInvolucradas(args[43], args[44], args[45], args[46], args[47], args[48]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[37]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite300();

	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA157(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar la indemnización 300 UF, incapacidad permanente total, pago referido."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[50]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[50]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Completar pasos para Liquidar
		SoapSteps.completarCamposYLiquidar(args[36], args[15]);
		// Completar Pago - hasta pago referido
		SoapSteps.completarPagoReferido();

	}
	
	@Test(priority = 3, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA159(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado,  indemnización si se cumple: hasta 200 UF en caso de incapacidad permanente parcial."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[52]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[52]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[54]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite200();

	}
	
	@Test(priority = 4, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA158(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado, indemnización si se cumple: sobre 300 UF por conceptos de gastos médicos."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[53]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[53]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[54]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite300();

	}
	
	@Test(priority = 5, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA160(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado, indemnización 300 UF, incapacidad permanente total."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[50]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[50]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[54]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite300();

	}
	
	@Test(priority = 6, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA229(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado, categoría lesionado, fallecido y beneficiario con indemnización si se cumple sobre: 300 UF en caso de muerte."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[13]);
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroMuerte(args[24], args[25], args[26], args[27]);
		TransversalSteps.generarSiniestro(args[32]);
		
		// Validar Detalles de Siniestro
		TransversalSteps.seleccionarModuloDetalleSiniestro();
		SoapSteps.validarDetallesSiniestro(args[15], args[4], args[13], args[49], args[4]);
		
		// Ir Terceros
		TransversalSteps.seleccionarModuloTerceros();
		// Agregar tercero
		SoapSteps.agregarTerceros(args[40], args[41], args[42],args[43], args[44], args[45]);
		SoapSteps.validarPartesInvolucradas(args[43], args[44], args[45], args[46], args[47], args[48]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[37]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite300();

	}
	
	@Test(priority = 7, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA230(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar la indemnización 300 UF, incapacidad permanente total, pago referido."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[50]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[50]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Completar pasos para Liquidar
		SoapSteps.completarCamposYLiquidar(args[36], args[15]);
		// Completar Pago - hasta pago referido
		SoapSteps.completarPagoReferido();

	}
	
	@Test(priority = 8, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA231(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado, indemnización si se cumple: sobre 300 UF por conceptos de gastos médicos."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[53]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[53]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[54]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite300();

	}
	
	
	
	@Test(priority = 9, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA232(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado,  indemnización si se cumple: hasta 200 UF en caso de incapacidad permanente parcial."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[52]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[52]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[54]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite200();

	}
	
	@Test(priority = 10, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA233(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Siniestros");
		setStep(getTest().createNode("Validar mensaje limite superado, indemnización 300 UF, incapacidad permanente total."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		SoapSteps.seleccionarProducto();
		
		// Completar datos siniestro
		TransversalSteps.seleccionFechaSiniestro();
		SoapSteps.completarQuePaso(args[11], args[12], args[50]); //cambiar cobertura
		TransversalSteps.insertarDescripcionHecho(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[20]);
		TransversalSteps.localizacionMapa(args[28]);
		TransversalSteps.completarDatosInformante(args[21], args[22], args[23]);
		SoapSteps.responderPreguntasSiniestroIncapacidadTotal(args[24], args[27], args[51], args[29]);
		TransversalSteps.generarSiniestro(args[50]);
		
		// Ir Reservas y Pagos
		TransversalSteps.seleccionarModuloReservasPagos();
		// Seleccionar item
		SoapSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[34]);
		TransversalSteps.completarCampoSubItem(args[35]);
		SoapSteps.ingresaReserva(args[54]);
		SoapSteps.guardarItem();
		// Validar mensaje
		SoapSteps.validarMensajeLimite300();

	}

	@DataProvider(name = "dataSoap")
	public Object[][] dataOncologico() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("siniestros" + File.separator + "CSVDataSoap.csv",
				1, 55);
		return data;
	}
}