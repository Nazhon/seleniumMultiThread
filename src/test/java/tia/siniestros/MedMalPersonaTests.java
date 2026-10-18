package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MedMalPersonaSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MedMalPersonaTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA399(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Individual con la cobertura Gastos Defensa Legal (id:619) y no se permita avanzar sin los campos obligatorios."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[5], args[6], args[7], args[8]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[18]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.seleccionFechaSiniestro1();
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[19]);
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.validarIngresoCausa(args[20]);
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.completarTipoInformante(args[22]);
		TransversalSteps.completarNombreInformante(args[23]);
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.seleccionarLugarSiniestro(args[21]);
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.completarTipoDenuncia(args[24]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[25]);
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA400(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Individual con la cobertura Gastos Defensa Legal (id:619) con fecha de siniestro fuera de plazo y "
				+ "al cambiar a una fecha valida se siniestre y quede registrado la salida de correos."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[5], args[6], args[7], args[8]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[18]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.seleccionarFechaSiniestroMesesAntesDespues(args[26]);
		TransversalSteps.validarMensajeErrorFechaSiniestroSuperiorDiaActual();
		TransversalSteps.insertarFechaSiniestroSinParametro();
		TransversalSteps.ingresarFechaDenunciaHoy();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[19]);
		TransversalSteps.validarIngresoCausa(args[20]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLiquidador(args[27]);
		TransversalSteps.seleccionarLugarSiniestro(args[21]);
		TransversalSteps.completarTipoInformante(args[22]);
		TransversalSteps.completarNombreInformante(args[23]);
		TransversalSteps.completarTipoDenuncia(args[24]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[25]);
		TransversalSteps.seleccionarModuloComunicacion();
		TransversalSteps.verificarCargaEnvioCorreos(args[37],args[38],args[39]);
		TransversalSteps.ValidarSalidasCorreos(args[37],args[38],args[39]);
	}

	@Test(priority = 3, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA401(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Individual con la cobertura Gastos Defensa Legal (id:619) generando el item de Indemnización pagando de forma Parcial."));
		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[5], args[6], args[7], args[8]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[18]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.insertarFechaSiniestroSinParametro();
		TransversalSteps.ingresarFechaDenunciaHoy();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[19]);
		TransversalSteps.validarIngresoCausa(args[20]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[21]);
		TransversalSteps.completarTipoInformante(args[22]);
		TransversalSteps.completarNombreInformante(args[23]);
		TransversalSteps.completarTipoDenuncia(args[24]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[25]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.validarTipoItem();
		TransversalSteps.validarMontoReserva();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.modificarMontoReservaLiquidar(args[28]);
		TransversalSteps.itemPago(args[29]);
		TransversalSteps.botonSiguiente();
		TransversalSteps.instruccionPago();
		TransversalSteps.botonSiguiente();
		TransversalSteps.validarMontoPagar(args[28]);
		TransversalSteps.finalizarPago();
		TransversalSteps.seleccionarSubModuloPago();
		TransversalSteps.valicarCambiosEnValoresReserva();
	}

	@Test(priority = 4, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA402(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Individual con la cobertura Responsabilidad Civil (errores & omisiones) generando el item de Indemnización pagando de forma Parcial."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[5], args[6], args[7], args[8]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[18]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.insertarFechaSiniestroSinParametro();
		TransversalSteps.ingresarFechaDenunciaHoy();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[30]);
		TransversalSteps.validarIngresoCausa(args[31]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[21]);
		TransversalSteps.completarTipoInformante(args[22]);
		TransversalSteps.completarNombreInformante(args[23]);
		TransversalSteps.completarTipoDenuncia(args[24]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[32]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.validarTipoItem();
		TransversalSteps.validarMontoReserva();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.modificarMontoReservaLiquidar(args[28]);
		TransversalSteps.itemPago(args[29]);
		TransversalSteps.botonSiguiente();
		TransversalSteps.instruccionPago();
		TransversalSteps.botonSiguiente();
		TransversalSteps.validarMontoPagar(args[28]);
		TransversalSteps.finalizarPago();
		TransversalSteps.seleccionarSubModuloPago();
		TransversalSteps.valicarCambiosEnValoresReserva();
	}
	
	@Test(priority = 5, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA403(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Aperturar siniestro de Medmal Individual con la cobertura Otros Riesgos generando el item de Indemnización pagando de forma Parcial."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[5], args[6], args[7], args[8]);
		TransversalSteps.seleccionarFechaPolizaDosDiasAntes();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17]);
		MedMalPersonaSteps.completarRiesgoOtrosRiesgos(args[33]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[18]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.insertarFechaSiniestroSinParametro();
		TransversalSteps.ingresarFechaDenunciaHoy();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[34]);
		TransversalSteps.validarIngresoCausa(args[35]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[21]);
		TransversalSteps.completarTipoInformante(args[22]);
		TransversalSteps.completarNombreInformante(args[23]);
		TransversalSteps.completarTipoDenuncia(args[24]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[36]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.validarTipoItem();
		TransversalSteps.validarMontoReserva();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.modificarMontoReservaLiquidar(args[28]);
		TransversalSteps.itemPago(args[29]);
		TransversalSteps.botonSiguiente();
		TransversalSteps.instruccionPago();
		TransversalSteps.botonSiguiente();
		TransversalSteps.validarMontoPagar(args[28]);
		TransversalSteps.finalizarPago();
		TransversalSteps.seleccionarSubModuloPago();
		TransversalSteps.valicarCambiosEnValoresReserva();
	}

	@DataProvider(name = "dataMedMalPersona")
	public Object[][] dataMedMalPersona() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("siniestros" + File.separator + "CSVDataMedMalPersona.csv", 1, 40);
		return data;
	}
}
