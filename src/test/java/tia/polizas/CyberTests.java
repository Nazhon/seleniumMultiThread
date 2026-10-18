package test.java.tia.polizas;

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
	public void ATMOQA398(String[] args) {

		getTest().assignCategory("Cyber").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Cyber con la linea de póliza Responsabilidad Proteccion de Datos CMF con "
				+ "tipo de moneda UF con una vigencia estandar de 1 año, canal de ventas directo y reaseguro QS configurado"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirectoUnAnioAntes(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.completarPolizaIdLineaPoliza(args[6], args[7]);
		CyberSteps.completarFormularioCyber(args[8], args[9], args[10], args[11]);
		CyberSteps.completarIncomeSplitFinancial(args[12]);
		CyberSteps.completarGeographicalWorksplit();
		CyberSteps.completarRatingAdjustments();
		CyberSteps.completarRiesgoDatosPersonales();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.verificarReaseguroCyber(args[13]);
		TransversalSteps.completarPoliza(args[14]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataCyber")
	public void ATMOQA410(String[] args) {

		getTest().assignCategory("Cyber").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Cyber con la linea de póliza Responsabilidad Proteccion de Datos CMF con tipo de moneda USD "
				+ "con una vigencia minima de 1 mes, canal de ventas broker y reaseguro QS configurado."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirectoUnAnioAntes(args[1], args[20], args[3], args[4], args[5]);
		TransversalSteps.seleccionarCambioFechaMeses(args[25]);
		TransversalSteps.insertarComision(args[24]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[15]);
		TransversalSteps.seleccionarCanalPago(args[16]);
		TransversalSteps.seleccionarFechaMesesDiasAntes(args[17],args[18]);
		TransversalSteps.seleccionarCodigoMoneda(args[19]);
		TransversalSteps.seleccionarAgentePoliza(args[21], args[22]);
		TransversalSteps.insertarParticipacionAgente(args[23]);
		TransversalSteps.completarPolizaIdLineaPoliza(args[6], args[7]);
		CyberSteps.completarFormularioCyber(args[8], args[9], args[10], args[11]);
		CyberSteps.completarIncomeSplitFinancial(args[12]);
		CyberSteps.completarGeographicalWorksplit();
		CyberSteps.completarRatingAdjustments();
		CyberSteps.completarRiesgoDatosPersonales();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.verificarReaseguroCyber(args[13]);
		TransversalSteps.completarPoliza(args[14]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 3, enabled = true, dataProvider = "dataCyber")
	public void ATMOQA411(String[] args) {

		getTest().assignCategory("Cyber").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Cyber con la linea de póliza Responsabilidad Proteccion de Datos CMF con tipo de moneda USX "
				+ "con una vigencia máxima de 18 mes, canal de ventas broker y reaseguro QS configurado."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirectoUnAnioAntes(args[1], args[20], args[3], args[4], args[5]);
		TransversalSteps.seleccionarCambioFechaMeses(args[27]);
		TransversalSteps.insertarComision(args[24]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[15]);
		TransversalSteps.seleccionarCanalPago(args[16]);
		TransversalSteps.seleccionarFechaMesesDiasAntes(args[17],args[18]);
		TransversalSteps.seleccionarCodigoMoneda(args[26]);
		TransversalSteps.seleccionarAgentePoliza(args[21], args[22]);
		TransversalSteps.insertarParticipacionAgente(args[23]);
		TransversalSteps.completarPolizaIdLineaPoliza(args[6], args[7]);
		CyberSteps.completarFormularioCyber(args[8], args[9], args[10], args[11]);
		CyberSteps.completarIncomeSplitFinancial(args[12]);
		CyberSteps.completarGeographicalWorksplit();
		CyberSteps.completarRatingAdjustments();
		CyberSteps.completarRiesgoDatosPersonales();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.verificarReaseguroCyber(args[13]);
		TransversalSteps.completarPoliza(args[14]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@DataProvider(name = "dataCyber")
	public Object[][] dataCyber() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataCyber.csv", 1,28);
		return data;
	}
}
