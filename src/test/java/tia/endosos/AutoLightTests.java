package test.java.tia.endosos;

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
	public void ATMOQA347(String[] args) {

		getTest().assignCategory("AutoLight").assignDevice("Endoso");
		setStep(getTest().createNode("Endosar una póliza de producto AutoLight con cambio de plan"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad2(args[0], args[1]);
		if (environment.equals("QA")) {
			TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[4], args[5], args[6], args[7]);
		} else {
			TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[4], args[14], args[6], args[7]);
		}
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.visitarPaginaHome();
		TransversalSteps.buscarEntidad2(args[0], args[1]);
		if (environment.equals("QA")) {
			TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[15], args[3], args[4], args[5], args[6],args[7]);
		} else {
			TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[15], args[3], args[4], args[14], args[6],args[7]);
		}
		AutoLightSteps.completarDetalleVehiculo(args[8], args[9], args[10], args[11], args[12], args[13]);
		AutoLightSteps.completarDetallePlan("Opción 2");
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso("401");
		TransversalSteps.ingresarObjeto();
		TransversalSteps.ajustarObjeto();
		AutoLightSteps.completarDetallePlan("Opción1");
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		TransversalSteps.completarPoliza("Endoso");
		TransversalSteps.aceptarDocumentosPoliza();

		// validar endoso
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEndoso();

	}
	

	@Test(priority = 2, enabled = true,  dataProvider = "dataAutoLight")
	public void ATMOQA348(String[] args) {

		getTest().assignCategory("AutoLight").assignDevice("Endoso");
		setStep(getTest().createNode("Endosar una póliza de producto AutoLight con cambio de Patente"));


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
		TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso("401");
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        AutoLightSteps.endosoPatente(args[16]);
        TransversalSteps.calcularPrimaTotal();
        TransversalSteps.completarPoliza("Endoso");
		TransversalSteps.aceptarDocumentosPoliza();

        //validar endoso
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEndoso();
        
	}
	
	
	
	@DataProvider(name = "dataAutoLight")
	public Object[][] dataRGM() throws CsvValidationException, InterruptedException, IOException {
	
		Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataAutoLight.csv", 1,17);
		return data;
	}
}