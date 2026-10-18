package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.CascoMaritimoSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class CascoMaritimoTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataCasco")
	public void ATMOQA327(String[] args) {

		getTest().assignCategory("Casco").assignDevice("Siniestros");
		setStep(getTest().createNode("Aperturar siniestro de Casco con la cobertura Casco y Maquinaria (id:280)."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		if (environment.equals("QA")) {
			TransversalSteps.buscarEntidadAlternativo(args[4]);
		} else {
			TransversalSteps.buscarEntidadAlternativo(args[27]);
		}
		TransversalSteps.creacionDePolizaRRCCDirectoUnAnioAntes(args[2], args[3], args[5], args[6], args[7]);
		TransversalSteps.ingresarDeducible(args[8], args[9]);
		TransversalSteps.seleccionarContratoFac(args[10]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.completarPolizaIdLineaPoliza(args[11], args[12]);
		CascoMaritimoSteps.completarDetallesNave(args[13], args[14], args[15], args[16], args[17]);
		CascoMaritimoSteps.completarRiesgosCasco(args[18], args[19], args[20], args[21], args[22], args[23], args[24]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.seleccionFechaSiniestroDosDiasAntes();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[28]);
		TransversalSteps.validarIngresoCausa(args[29]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLugarSiniestro(args[30]);
		TransversalSteps.completarDatosInformante(args[31], args[32], args[33]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.generarSiniestro(args[34]);
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataCasco")
	public void ATMOQA328(String[] args) {

		getTest().assignCategory("Casco").assignDevice("Siniestros");
		setStep(getTest().createNode("Siniestro Casco modificar la indemnización, el honorario se ajuste automáticamente."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		if (environment.equals("QA")) {
			TransversalSteps.buscarEntidadAlternativo(args[4]);
		} else {
			TransversalSteps.buscarEntidadAlternativo(args[27]);
		}
		TransversalSteps.creacionDePolizaRRCCDirectoUnAnioAntes(args[2], args[3], args[5], args[6], args[7]);
		TransversalSteps.ingresarDeducible(args[8], args[9]);
		TransversalSteps.seleccionarContratoFac(args[10]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.completarPolizaIdLineaPoliza(args[11], args[12]);
		CascoMaritimoSteps.completarDetallesNave(args[13], args[14], args[15], args[16], args[17]);
		CascoMaritimoSteps.completarRiesgosCasco(args[18], args[19], args[20], args[21], args[22], args[23], args[24]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarElementoDaniado();
		TransversalSteps.seleccionFechaSiniestroDosDiasAntes();
		TransversalSteps.insertarDescripcionHecho("PruebaQA");
		TransversalSteps.validarIngresoEvento(args[28]);
		TransversalSteps.validarIngresoCausa(args[29]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLiquidador(args[35]);
		TransversalSteps.seleccionarLugarSiniestro(args[30]);
		TransversalSteps.completarDatosInformante(args[31], args[32], args[33]);
		TransversalSteps.AprobarPreguntaAsistente();
		TransversalSteps.crearReserva();
		TransversalSteps.crearNuevoItem();
		TransversalSteps.completarCampoItem(args[36]);
		TransversalSteps.completarCampoSubItem(args[37]);
		TransversalSteps.completarCampoReserva(args[38]);
		TransversalSteps.guardarReserva();
		TransversalSteps.verificarMontoReservaHonorarioLiquidador();
		TransversalSteps.validarTipoItem();
		TransversalSteps.modificarReserva(args[39]);
		TransversalSteps.guardarReserva();
		TransversalSteps.verificarModificacionHonorariosLiquidador();
		TransversalSteps.generarSiniestro(args[34]);
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.verificarCambioMontoReserva(args[39]);
	}

	@DataProvider(name = "dataCasco")
	public Object[][] dataCasco() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("siniestros" + File.separator + "CSVDataCasco.csv", 1, 40);
		return data;
	}
}
