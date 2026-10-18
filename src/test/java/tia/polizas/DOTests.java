package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.CyberSteps;
import main.java.pageSteps.tia.productos.DOSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class DOTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataDo")
	public void ATMOQA414(String[] args) {

		getTest().assignCategory("D&O").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto D&O con linea de póliza \"Instituciones comerciales - No cotiza en bolsa\" con tipo de moneda UF con una vigencia estándar de 1 año, canal de ventas directo."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirecto(args[1], args[2], args[3], args[4], args[5]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.completarPolizaIdLineaPoliza(args[6], args[7]);
		DOSteps.completarformularioDO(args[8]);
		DOSteps.completarRiesgosDOInstitucionesComerNoBolsa(args[9],args[10],args[11],args[12]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[13]);
		TransversalSteps.aceptarDocumentosPolizaSinCondicionesParticulares();
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataDo")
	public void ATMOQA415(String[] args) {

		getTest().assignCategory("D&O").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto D&O con linea de póliza \"D&O Copropiedades\" con tipo de moneda USD con una vigencia mínima de 1 mes, canal de ventas tradicional con corredor y tipo de póliza provisoria"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirecto(args[1], args[19], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaFuturaDias(args[29]);
		TransversalSteps.seleccionarPProvisoria(args[28]);
		TransversalSteps.insertarComision(args[23]);
		TransversalSteps.seleccionarAgentePoliza(args[20], args[21]);
		TransversalSteps.insertarParticipacionAgente(args[22]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[14]);
		TransversalSteps.seleccionarCanalPago(args[15]);
		TransversalSteps.completarPolizaIdLineaPoliza(args[18], args[27]);
		DOSteps.completarformularioDO(args[8]);
		DOSteps.completarRiesgosDOCoPropiedades(args[9],args[10],args[11],args[12]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[13]);
		TransversalSteps.aceptarDocumentosPolizaSinCondicionesParticulares();
	}
	
	
	@Test(priority = 3, enabled = true, dataProvider = "dataDo")
	public void ATMOQA421(String[] args) {

		getTest().assignCategory("D&O").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto D&O con linea de póliza \"Instituciones Comerciales y Financieras - Sí cotiza en bolsa (Local-EEUU)\" con tipo de moneda USD con una vigencia mínima de 1 mes, canal de ventas tradicional con corredor y tipo de póliza provisoria"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirecto(args[1], args[19], args[3], args[4], args[5]);
		TransversalSteps.seleccionarFechaFuturaDias(args[31]);
		TransversalSteps.insertarComision(args[23]);
		TransversalSteps.seleccionarAgentePoliza(args[20], args[21]);
		TransversalSteps.insertarParticipacionAgente(args[22]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[14]);
		TransversalSteps.seleccionarCanalPago(args[15]);
		TransversalSteps.completarPolizaIdLineaPoliza(args[25], args[30]);
		DOSteps.completarformularioDO(args[8]);
		DOSteps.completarRiesgosComercialYFinancieras(args[9],args[10],args[11],args[12]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[13]);
		TransversalSteps.aceptarDocumentosPolizaSinCondicionesParticulares();
	}

	@DataProvider(name = "dataDo")
	public Object[][] dataDO() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataDO.csv", 1,32);
		return data;
	}
}
