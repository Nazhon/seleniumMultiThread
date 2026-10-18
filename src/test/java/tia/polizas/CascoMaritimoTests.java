package test.java.tia.polizas;

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
	public void ATMOQA387(String[] args) {

		getTest().assignCategory("Casco").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Validar la emisión del producto Casco Marítimo con la línea de póliza Casco Marítimo del Instituto a Término (CMITB) "
				+ "agregando un contrato FAC y sus atributos correspondientes a nivel de Póliza y Manejo de Póliza"));

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
		TransversalSteps.verificarReaseguro(args[26]);
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataCasco")
	public void ATMOQA385(String[] args) {

		getTest().assignCategory("Casco").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Casco Maritimo con la linea de póliza Riesgo Portuario Navegación Limitada (RPNLB) "
				+ "agregando un contrato FAC y sus atributos correspondientes a nivel de Póliza y Manejo de Póliza."));

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
		TransversalSteps.completarPolizaIdLineaPoliza(args[11], args[28]);
		CascoMaritimoSteps.completarDetallesNave(args[13], args[14], args[15], args[16], args[17]);
		CascoMaritimoSteps.completarRiesgosCasco(args[18], args[19], args[20], args[21], args[22], args[23], args[24]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[26]);
	}

	@Test(priority = 3, enabled = true, dataProvider = "dataCasco")
	public void ATMOQA383(String[] args) {

		getTest().assignCategory("Casco").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Casco Maritimo con la linea de póliza Pérdida Total Solamente (PTOSB) "
				+ "agregando un contrato FAC y sus atributos correspondientes a nivel de Póliza y Manejo de Póliza."));

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
		TransversalSteps.completarPolizaIdLineaPoliza(args[11], args[29]);
		CascoMaritimoSteps.completarDetallesNave(args[13], args[14], args[15], args[16], args[17]);
		CascoMaritimoSteps.completarRiesgosCasco(args[18], args[19], args[20], args[21], args[22], args[23], args[24]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[26]);
	}

	@Test(priority = 4, enabled = true, dataProvider = "dataCasco")
	public void ATMOQA384(String[] args) {

		getTest().assignCategory("Casco").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Casco Maritimo con la linea de póliza Pérdida Total Solamente Ampliada (PTSAB) "
				+ "agregando un contrato FAC y sus atributos correspondientes a nivel de Póliza y Manejo de Póliza."));

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
		TransversalSteps.completarPolizaIdLineaPoliza(args[11], args[30]);
		CascoMaritimoSteps.completarDetallesNave(args[13], args[14], args[15], args[16], args[17]);
		CascoMaritimoSteps.completarRiesgosCasco(args[18], args[19], args[20], args[21], args[22], args[23], args[24]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[26]);
	}

	@Test(priority = 5, enabled = true, dataProvider = "dataCasco")
	public void ATMOQA386(String[] args) {

		getTest().assignCategory("Casco").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Casco Maritimo con la linea de póliza Riesgo Portuario Navegación (RPNAB) "
				+ "agregando un contrato FAC y sus atributos correspondientes a nivel de Póliza y Manejo de Póliza."));

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
		TransversalSteps.completarPolizaIdLineaPoliza(args[11], args[31]);
		CascoMaritimoSteps.completarDetallesNave(args[13], args[14], args[15], args[16], args[17]);
		CascoMaritimoSteps.completarRiesgosCasco(args[18], args[19], args[20], args[21], args[22], args[23], args[24]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[26]);
	}

	@DataProvider(name = "dataCasco")
	public Object[][] dataCasco() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataCasco.csv", 1, 32);
		return data;
	}
}
