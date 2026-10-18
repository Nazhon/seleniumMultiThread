package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.pageSteps.tia.productos.GarantiaExtendidaSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.config.OpenCSV;
import test.java.BaseTest;

public class GarantiaExtendidaTests extends BaseTest {

	// Emision de Poliza Mascota Mestizo con chip
	@Test(priority = 1, enabled = true, dataProvider = "dataGarantiaExtendida")
	public void ATMOQA293(String args[]) throws InterruptedException {

		getTest().assignCategory("Garantia_Extendida").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Creación de un certificado en TIA para el producto de garantía extendida para la "
				+ "compañía SBI con producto solo con servicio de Garantía extendida en Sponsor Hites."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediadorSponsor(args[2], args[3], args[4] ,args[5], args[6], args[7], args[8]);
		// completar datos objeto
		GarantiaExtendidaSteps.completarDatosGarantiaExtendida(args[9], args[10], args[11], args[12], args[13]);
		TransversalSteps.guardarCambios();
		TransversalSteps.calcularPrimaTotal(); // completar póliza
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
		GarantiaExtendidaSteps.aceptarDocumentosPoliza();
		 
	}
	
	@Test(priority = 1, enabled = true, dataProvider = "dataGarantiaExtendida")
	public void ATMOQA296(String args[]) throws InterruptedException {

		getTest().assignCategory("Garantia_Extendida").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Creación de un certificado en TIA para el producto de garantía extendida para la "
				+ "compañía SBI con producto solo con servicio de Garantía extendida en Sponsor Hites."
				+ "ingresando la fecha de compra de la GE después de la fecha de compra del producto"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaFechaFutura(args[2], args[3], args[4] ,args[5], args[6], args[7], args[8]);
		TransversalSteps.validarErrorFecha();
	
		 
	}

	
	  @Test(priority = 2, enabled = true, dataProvider = "dataGarantiaExtendida")
	  public void ATMOQA300(String args[]) throws InterruptedException {
	  
	  getTest().assignCategory("Garantia_Extendida").assignDevice("Emision_Poliza");
	  setStep(getTest().createNode( "Creación de un certificado en TIA para el producto de garantía extendida para la "
	  		+ "compañía SBI con producto solo con servicio de Garantía extendida en Sponsor Falabella."));
	  
	  TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,
	  environment); TransversalSteps.buscarEntidad(args[0], args[1]);
	  TransversalSteps.crearPolizaIntermediadorSponsor(args[2], args[3], args[4]
	  ,args[14], args[6], args[15], args[8]); 
	  // completar datos objeto
	  GarantiaExtendidaSteps.completarDatosGarantiaExtendida(args[16], args[10],
	  args[17], args[18], args[19]); 
	  TransversalSteps.guardarCambios();
	  TransversalSteps.calcularPrimaTotal(); 
	  // completar póliza
	  TransversalSteps.completarPoliza("Póliza");
	  TransversalSteps.aceptarDocumentosPoliza();
	  
	 }
	 
	  @Test(priority = 3, enabled = true, dataProvider = "dataGarantiaExtendida")
	  public void ATMOQA297(String args[]) throws InterruptedException {
	  
	  getTest().assignCategory("Garantia_Extendida").assignDevice("Emision_Poliza");
	  setStep(getTest().createNode( "Creación de un certificado en TIA para el producto de garantía extendida para la "
	  		+ "compañía SBI con producto solo con servicio de Garantía extendida en Sponsor Falabella."));
	  
	  TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,
	  environment); TransversalSteps.buscarEntidad(args[0], args[1]);
	  TransversalSteps.crearPolizaIntermediadorSponsor(args[2], args[3], args[4]
	  ,args[14], args[6], args[15], args[8]); 
	  // completar datos objeto
	  GarantiaExtendidaSteps.completarDatosGarantiaExtendida(args[16], args[10],
	  args[17], args[20], args[19]); 
	  TransversalSteps.guardarCambios();
	  GarantiaExtendidaSteps.ValidarMensajeError();
	  	 }
		 
	
	@DataProvider(name = "dataGarantiaExtendida")
	public Object[][] dataGarantiaExtendida() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("emision" + File.separator + "CSVDataGarantiaExtendida.csv", 1, 21);
		return data;
	}
}
