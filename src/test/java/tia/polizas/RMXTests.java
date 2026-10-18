package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.RMXSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class RMXTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataRMX")
	public void ATMOQA416(String[] args) {

		getTest().assignCategory("RMX").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto RMX con linea de póliza \"Terrestre\" con tipo de moneda UF con una vigencia estándar de 1 año, canal de ventas directo"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirecto(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.completarPolizaIdLineaPoliza(args[6], args[7]);
		RMXSteps.completarformularioRMXPrincipal(args[8], args[9], args[10], args[11]);
		RMXSteps.completarRiesgosRMXTerrestre(args[12],args[13]);
		RMXSteps.completarformularioTerrestre(args[32],args[33],args[34],args[35],args[36],args[37]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[14]);
		TransversalSteps.aceptarDocumentosPolizaSinCondicionesParticulares();
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataRMX")
	public void ATMOQA424(String[] args) {

		getTest().assignCategory("RMX").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto RMX con linea de póliza \"Maritimo\" con tipo de moneda USD con una vigencia maxima de 24 meses, canal de ventas con corredor"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirecto(args[1], args[20], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaFuturaDias(args[31]);
		TransversalSteps.insertarComision(args[24]);
		TransversalSteps.seleccionarAgentePoliza(args[21], args[22]);
		TransversalSteps.insertarParticipacionAgente(args[23]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[15]);
		TransversalSteps.seleccionarCanalPago(args[16]);
		TransversalSteps.completarPolizaIdLineaPoliza(args[19], args[28]);
		RMXSteps.completarformularioRMXPrincipal(args[8], args[9], args[10], args[11]);
		RMXSteps.completarRiesgosRMXMaritimo(args[12],args[13]);
		RMXSteps.completarformularioMaritimo(args[38],args[34],args[33],args[36],args[39],args[37]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[14]);
		TransversalSteps.aceptarDocumentosPolizaSinCondicionesParticulares();
	}
	
	
	@Test(priority = 3, enabled = true, dataProvider = "dataRMX")
	public void ATMOQA425(String[] args) {

		getTest().assignCategory("RMX").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto RMX con linea de póliza \"Maritimo\" con tipo de moneda UDX con una vigencia minima de 1 mes, canal de ventas con corredor y tipo de póliza provisoria"));
		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirecto(args[1], args[20], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaFuturaDias(args[30]);
		TransversalSteps.seleccionarPProvisoria(args[29]);
		TransversalSteps.insertarComision(args[24]);
		TransversalSteps.seleccionarAgentePoliza(args[21], args[22]);
		TransversalSteps.insertarParticipacionAgente(args[23]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[15]);
		TransversalSteps.seleccionarCanalPago(args[16]);
		TransversalSteps.completarPolizaIdLineaPoliza(args[26], args[28]);
		RMXSteps.completarformularioRMXPrincipal(args[8], args[9], args[10], args[11]);
		RMXSteps.completarRiesgosRMXMaritimo(args[12],args[13]);
		RMXSteps.completarformularioMaritimo(args[38],args[34],args[33],args[36],args[39],args[37]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[14]);
		TransversalSteps.aceptarDocumentosPolizaSinCondicionesParticulares();
	}

	@DataProvider(name = "dataRMX")
	public Object[][] dataRMX() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataRMX.csv", 1,40);
		return data;
	}
}
