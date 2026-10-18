package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.RGMSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class RGMTests extends BaseTest{
	@Test(priority = 1, enabled = true,  dataProvider = "dataRGM")
	public void ATMOQA242(String[] args) {

		getTest().assignCategory("RGM").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emitir póliza de Reembolso Gastos Médicos modalidad familiar con Isapre "));


		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[10], args[14], args[11],args[20], args[12]);
		TransversalSteps.validarPorcentajeComision(args[13]);
		RGMSteps.completarPlanIsapreFamiliar(args [5]);
		RGMSteps.agregarAseguradoAdicional(args[6], args[7], args[8]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	
	@Test(priority = 2, enabled = true,  dataProvider = "dataRGM")
	public void ATMOQA243(String[] args) {

		getTest().assignCategory("RGM").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emitir póliza de Reembolso Gastos Médicos modalidad individual con Fonasa "));


		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[10], args[14], args[11],args[20], args[12]);
		TransversalSteps.validarPorcentajeComision(args[13]);
		RGMSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	
	@Test(priority = 3, enabled = true,  dataProvider = "dataRGM")
	public void ATMOQA245(String[] args) {

		getTest().assignCategory("RGM").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emitir póliza de Reembolso Gastos Médicos modalidad colectiva "));


		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[15], args[16]);
		TransversalSteps.crearPolizaIntermediador(args[17], args[10], args[14], args[18],args[20], args[19]);
		TransversalSteps.validarPorcentajeComision(args[13]);
		RGMSteps.agregarAseguradoColectivo(args[6], args[7]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}




@DataProvider(name = "dataRGM")
public Object[][] dataRGM() throws CsvValidationException, InterruptedException, IOException {

	Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataRGM.csv", 1,21);
	return data;
}
}