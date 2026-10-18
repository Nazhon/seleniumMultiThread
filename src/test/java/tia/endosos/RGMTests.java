package test.java.tia.endosos;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.RGMSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class RGMTests extends BaseTest {

	@Test(priority = 1, enabled = true,  dataProvider = "dataRGM")
	public void ATMOQA248(String[] args) {

		getTest().assignCategory("RGM").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Endosar póliza de Reembolso Gastos Médicos modalidad colectiva "));


		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[6], args[11], args[8], args[7],args[9]);
		TransversalSteps.validarPorcentajeComision(args[10]);
		RGMSteps.agregarAseguradoColectivo(args[3], args[4]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[5]);
		TransversalSteps.aceptarDocumentosPoliza();
		//completar datos de endosos
		TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[12]);
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();
        RGMSteps.agregarAseguradoColectivo(args[17], args[18]);
        TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[19]);
		TransversalSteps.aceptarDocumentosPoliza();
		//validar endoso
        TransversalSteps.validarEndoso();
	}

@DataProvider(name = "dataRGM")
public Object[][] dataRGM() throws CsvValidationException, InterruptedException, IOException {

	Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataRGM.csv", 1,19);
	return data;
}

	
}
