package test.java.tia.renovacion;



import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.OncologicoSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;


public class OncologicoTests extends BaseTest {

	@Test(priority = 1, enabled = true,  dataProvider = "dataOncologico")
	public void ATMOQA125(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Renovacion");
		setStep(getTest().createNode("Renovación manual de una póliza de tipo Oncológico en la plataforma TIA cumplido el año de vigencia."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaRenovarConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.renovarPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEstadoPoliza("renovación");
	}
	
	@Test(priority = 1, enabled = true,  dataProvider = "dataOncologico")
	public void ATMOQA265(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Renovacion");
		setStep(getTest().createNode("Renovación manual de una póliza de tipo Oncológico en la plataforma TIA cumplido el año de vigencia."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaRenovar(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.renovarPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEstadoPoliza("renovación");
	}

	@DataProvider(name = "dataOncologico")
	public Object[][] dataOncologico() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("renovacion" + File.separator + "CSVDataOncologico.csv", 1, 11);
		return data;
	}
	
}
