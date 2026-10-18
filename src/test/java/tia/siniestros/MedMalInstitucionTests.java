package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MedMalInstitucionSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.properties.PolizaProperties;
import test.java.BaseTest;

public class MedMalInstitucionTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA405(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Siniestro");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Clínica con la cobertura Gastos Defensa Legal (id:619) generando el item de Indemnización pagando de forma Parcial."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad2(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[2], args[3], args[4], args[5], args[6]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.seleccionarCodigoMoneda(args[7]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[8]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[9], args[10], args[11], args[12],
				args[13], args[14], args[15], args[16], args[17], args[18], args[19]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[20], args[21], args[22]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[23], args[24]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.busquedaRapidaElemento(PolizaProperties.idPoliza);
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.insertarFechaSiniestroSinParametro();
		TransversalSteps.ingresarFechaDenunciaHoy();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[26]);
		TransversalSteps.validarIngresoCausa(args[26]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[27]);
		TransversalSteps.completarTipoInformante(args[28]);
		TransversalSteps.completarNombreInformante(args[29]);
		TransversalSteps.completarTipoDenuncia(args[30]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[31]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.validarTipoItem();
		TransversalSteps.validarMontoReserva();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.modificarMontoReservaLiquidar(args[32]);
		TransversalSteps.itemPago(args[33]);
		TransversalSteps.botonSiguiente();
		TransversalSteps.instruccionPago();
		TransversalSteps.botonSiguiente();
		TransversalSteps.validarMontoPagar(args[34]);
		TransversalSteps.finalizarPago();
		TransversalSteps.seleccionarSubModuloPago();
		TransversalSteps.valicarCambiosEnValoresReserva();
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA406(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Clínica con la cobertura Responsabilidad Civil (errores & omisiones) generando el item de Indemnización pagando de forma Parcial."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.seleccionarCodigoMoneda(args[6]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[7]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[8], args[9], args[10], args[11],
				args[12], args[13], args[14], args[15], args[16], args[17], args[18]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[19], args[20], args[21]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[22], args[23]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[24]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.insertarFechaSiniestroSinParametro();
		TransversalSteps.ingresarFechaDenunciaHoy();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[34]);
		TransversalSteps.validarIngresoCausa(args[34]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[26]);
		TransversalSteps.completarTipoInformante(args[27]);
		TransversalSteps.completarNombreInformante(args[28]);
		TransversalSteps.completarTipoDenuncia(args[29]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[34]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.validarTipoItem();
		TransversalSteps.validarMontoReserva();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.modificarMontoReservaLiquidar(args[31]);
		TransversalSteps.itemPago(args[32]);
		TransversalSteps.botonSiguiente();
		TransversalSteps.instruccionPago();
		TransversalSteps.botonSiguiente();
		TransversalSteps.validarMontoPagar(args[31]);
		TransversalSteps.finalizarPago();
		TransversalSteps.seleccionarSubModuloPago();
		TransversalSteps.valicarCambiosEnValoresReserva();
	}
	
	@Test(priority = 3, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA407(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Clínica con la cobertura Otros Riesgos generando el ítem de Indemnización pagando de forma Parcial."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.seleccionarCodigoMoneda(args[6]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[7]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[8], args[9], args[10], args[11],
				args[12], args[13], args[14], args[15], args[16], args[17], args[18]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[19], args[20], args[21]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[22], args[23]);
		MedMalInstitucionSteps.completarRiesgoOtrosRiesgos(args[35]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[24]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.insertarFechaSiniestroSinParametro();
		TransversalSteps.ingresarFechaDenunciaHoy();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[36]);
		TransversalSteps.validarIngresoCausa(args[36]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[26]);
		TransversalSteps.completarTipoInformante(args[27]);
		TransversalSteps.completarNombreInformante(args[28]);
		TransversalSteps.completarTipoDenuncia(args[29]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[37]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.validarTipoItem();
		TransversalSteps.validarMontoReserva();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.modificarMontoReservaLiquidar(args[31]);
		TransversalSteps.itemPago(args[32]);
		TransversalSteps.botonSiguiente();
		TransversalSteps.instruccionPago();
		TransversalSteps.botonSiguiente();
		TransversalSteps.validarMontoPagar(args[31]);
		TransversalSteps.finalizarPago();
		TransversalSteps.seleccionarSubModuloPago();
		TransversalSteps.valicarCambiosEnValoresReserva();
	}

	@DataProvider(name = "dataMedMalInstitucion")
	public Object[][] dataMedMalInstitucion() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("siniestros" + File.separator + "CSVDataMedMalInstitucion.csv", 1, 38);
		return data;
	}
}
