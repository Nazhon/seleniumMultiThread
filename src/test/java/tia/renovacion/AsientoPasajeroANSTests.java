package test.java.tia.renovacion;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.AsientoPasajeroANSSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class AsientoPasajeroANSTests extends BaseTest {
	
	
	@Test(priority = 1, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA345(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Renovación");
		setStep(getTest().createNode(
				"Renovación de póliza en TIA para producto Asiento pasajero ANS donde la póliza tenga una vigencia de 12 meses se deba renovar correctamente."));

		//Pasos transversales
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0],args[1]); 	
		TransversalSteps.crearPolizaIntermediadorRenovacion(args[2],args[3],args[4],args[5],args[6],args[7]);	
		//Pasos producto
//		AsientoPasajeroANSSteps.contrataPoliza(args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);	
		//Pasos transversales
		TransversalSteps.completarPoliza(args[16]);
		TransversalSteps.aceptarDocumentosPoliza();				
		// Metodos para renovacion 
		TransversalSteps.renovarPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEstadoPoliza(args[21]);
		
	}
	
	@DataProvider(name = "dataAsientoPasajeroANS")
	public Object[][] dataAsientoPasajeroANS() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("renovacion" + File.separator + "CSVDataAsientoPasajeroANS.csv", 1, 22);
		return data;
	}

}
