package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.CyberSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class CyberTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataCyber")
	public void ATMOQA404(String[] args) {

		getTest().assignCategory("Cyber").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Cyber con la cobertura Responsabilidad Protección de Datos generando el item de Indemnización pagando de forma Parcial."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[6]);
		TransversalSteps.seleccionarCanalPago(args[7]);
		TransversalSteps.seleccionarCodigoMoneda(args[8]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[9]);
		CyberSteps.completarFormularioCyber(args[10], args[11], args[12], args[13]);
		CyberSteps.completarIncomeSplitFinancial(args[14]);
		CyberSteps.completarGeographicalWorksplit();
		CyberSteps.completarRatingAdjustments();
		CyberSteps.completarRiesgoDatosPersonales();
		CyberSteps.completarResponsabilidadDatosCorporativos();
		CyberSteps.completarInterrupcionRed(args[15], args[16]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[17]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.seleccionFechaSiniestro1();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[18]);
		TransversalSteps.validarIngresoCausa(args[18]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[19]);
		TransversalSteps.completarTipoInformante(args[20]);
		TransversalSteps.completarNombreInformante(args[21]);
		TransversalSteps.completarTipoDenuncia(args[22]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[23]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.validarTipoItem();
		TransversalSteps.validarMontoReserva();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.modificarMontoReservaLiquidar(args[24]);
		TransversalSteps.itemPago(args[25]);
		TransversalSteps.botonSiguiente();
		TransversalSteps.instruccionPago();
		TransversalSteps.botonSiguiente();
		TransversalSteps.validarMontoPagar(args[24]);
		TransversalSteps.finalizarPago();
		TransversalSteps.seleccionarSubModuloPago();
		TransversalSteps.valicarCambiosEnValoresReserva();
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataCyber")
	public void ATMOQA408(String[] args) {

		getTest().assignCategory("Cyber").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Cyber con cobertura \"Responsabilidad Protección de Datos\" y crear un nuevo ítem para el siniestro creado."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[6]);
		TransversalSteps.seleccionarCanalPago(args[7]);
		TransversalSteps.seleccionarCodigoMoneda(args[8]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[9]);
		CyberSteps.completarFormularioCyber(args[10], args[11], args[12], args[13]);
		CyberSteps.completarIncomeSplitFinancial(args[14]);
		CyberSteps.completarGeographicalWorksplit();
		CyberSteps.completarRatingAdjustments();
		CyberSteps.completarRiesgoDatosPersonales();
		CyberSteps.completarResponsabilidadDatosCorporativos();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[17]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.seleccionFechaSiniestro1();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[18]);
		TransversalSteps.validarIngresoCausa(args[18]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[19]);
		TransversalSteps.completarTipoInformante(args[20]);
		TransversalSteps.completarNombreInformante(args[21]);
		TransversalSteps.completarTipoDenuncia(args[22]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[23]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.crearNuevoItem();
		TransversalSteps.completarCampoItem(args[26]);
		TransversalSteps.completarCampoSubItem(args[27]);
		TransversalSteps.completarCampoReserva(args[28]);
		TransversalSteps.guardarReserva();
		TransversalSteps.verificarNuevoItem(args[26]);
	}
	
	@Test(priority = 3, enabled = true, dataProvider = "dataCyber")
	public void ATMOQA409(String[] args) {

		getTest().assignCategory("Cyber").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Cyber con cobertura \"Responsabilidad Protección de Datos\" y verificar registro de la salida de correos."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[6]);
		TransversalSteps.seleccionarCanalPago(args[7]);
		TransversalSteps.seleccionarCodigoMoneda(args[8]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[9]);
		CyberSteps.completarFormularioCyber(args[10], args[11], args[12], args[13]);
		CyberSteps.completarIncomeSplitFinancial(args[14]);
		CyberSteps.completarGeographicalWorksplit();
		CyberSteps.completarRatingAdjustments();
		CyberSteps.completarRiesgoDatosPersonales();
		CyberSteps.completarResponsabilidadDatosCorporativos();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[17]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.seleccionFechaSiniestro1();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[18]);
		TransversalSteps.validarIngresoCausa(args[18]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[19]);
		TransversalSteps.completarTipoInformante(args[20]);
		TransversalSteps.completarNombreInformante(args[21]);
		TransversalSteps.completarTipoDenuncia(args[22]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[23]);
		TransversalSteps.seleccionarModuloComunicacion();
		TransversalSteps.verificarCargaEnvioCorreos(args[29],args[30],args[31]);
		TransversalSteps.ValidarSalidasCorreos(args[29],args[30],args[31]);
	}

	@DataProvider(name = "dataCyber")
	public Object[][] dataCyber() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("siniestros" + File.separator + "CSVDataCyber.csv", 1,32);
		return data;
	}
}
