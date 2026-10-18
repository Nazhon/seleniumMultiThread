package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.AutoLightSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class AutoLightTests extends BaseTest{
	
	@Test(priority = 1, enabled = true,  dataProvider = "dataAutoLight")
	public void ATMOQA329(String[] args) {

		getTest().assignCategory("AutoLight").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emite póliza de producto AutoLight con plan Opción 2"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad2(args[0], args[1]);
		if(environment.equals("QA")) {
			TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[4], args[5], args[6], args[7]);
		}else {
			TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[4], args[14], args[6], args[7]);
		}
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.visitarPaginaHome();
		TransversalSteps.buscarEntidad2(args[0], args[1]);
		if(environment.equals("QA")) {
			TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[15], args[3], args[4], args[5], args[6], args[7]);
		}else { 
			TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[15], args[3], args[4], args[14], args[6], args[7]);
		}
		AutoLightSteps.completarDetalleVehiculo(args[8], args[9], args[10], args[11], args[12], args[13]);
		AutoLightSteps.completarDetallePlan("Opción 2");
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 2, enabled = true,  dataProvider = "dataAutoLight")
	public void ATMOQA333(String[] args) {

		getTest().assignCategory("AutoLight").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emite póliza de producto AutoLight con Plan Opción 1"));


		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad2(args[0], args[1]);
		if(environment.equals("QA")) {
			TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[4], args[5], args[6], args[7]);
		}else {
			TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[4], args[14], args[6], args[7]);
		}
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.visitarPaginaHome();
		TransversalSteps.buscarEntidad2(args[0], args[1]);
		if(environment.equals("QA")) {
			TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[15], args[3], args[4], args[5], args[6], args[7]);
		}else {
			TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[15], args[3], args[4], args[14], args[6], args[7]);
		}
		AutoLightSteps.completarDetalleVehiculo(args[8], args[9], args[10], args[11], args[12], args[13]);
		AutoLightSteps.completarDetallePlan("Opción1");
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	
	
	
	@DataProvider(name = "dataAutoLight")
	public Object[][] dataRGM() throws CsvValidationException, InterruptedException, IOException {
	
		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataAutoLight.csv", 1,16);
		return data;
	}
}