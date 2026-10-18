package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.AutoLightSteps;
import main.java.pageSteps.tia.productos.HogarFullSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class AutoLightTests extends BaseTest{
	
	@Test(priority = 1, enabled = true,  dataProvider = "dataAutoLight")
	public void ATMOQA332(String[] args) {

		getTest().assignCategory("AutoLight").assignDevice("Siniestro");
		setStep(getTest().createNode("Siniestro de una póliza de producto AutoLight "));


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
		TransversalSteps.ingresarSiniestroCreateClaim();
		TransversalSteps.seleccionarProducto();
		TransversalSteps.seleccionFechaSiniestro();
		HogarFullSteps.completarEvento(args[16], args[17],args[18]);
		TransversalSteps.insertarDescripcionHecho("Descripción");
		TransversalSteps.seleccionarLugarSiniestro(args[19]);
		TransversalSteps.completarDatosInformante(args[20],args[21],args[22]);
		TransversalSteps.generarSiniestroAlt();
		
		//Falta generar la liquidación del siniestro.
	}
	
	
	
	@DataProvider(name = "dataAutoLight")
	public Object[][] dataRGM() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("siniestros" + File.separator + "CSVDataAutoLight.csv", 1,22);
		return data;
	}
}
