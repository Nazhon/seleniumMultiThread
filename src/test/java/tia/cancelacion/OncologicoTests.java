package test.java.tia.cancelacion;

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
	
	
	@Test(priority = 1, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA382(String args[]) throws InterruptedException {
		getTest().assignCategory("Oncológico").assignDevice("Cancelacion");
		setStep(getTest().createNode(
				"Cancelación de una póliza de tipo Oncologico Iram, dentro de los primeros 10 días de vigencia, con motivo Cancelación por desistimiento del asegurado - Retracto."));


		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal("3,050069");
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.manejoLineaPolizaDesdeEntidad();
		TransversalSteps.ajustaPolizaTrans("402");
		TransversalSteps.seleccionarMotivoCancelacionPoliza("1");
		TransversalSteps.completarPoliza("endoso");
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.manejoLineaPolizaDesdeEntidad();
		TransversalSteps.validaTransaccionCancelacionPoliza();
	}
	
	@DataProvider(name = "dataOncologico")
	public Object[][] dataOncologico() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataOncologico.csv", 1,36);
		return data;
	}
}
