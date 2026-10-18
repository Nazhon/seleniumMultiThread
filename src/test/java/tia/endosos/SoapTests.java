package test.java.tia.endosos;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.pageSteps.tia.productos.SoapSteps;
import test.java.BaseTest;

public class SoapTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataSoap")
	public void ATMOQA178(String[] args) throws InterruptedException {

		getTest().assignCategory("Soap").assignDevice("Endoso");
		setStep(getTest().createNode("Validar que no se pueda realizar un endoso modificando el tipo de vehículo para el canal de venta 2"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		SoapSteps.completarDetalleVehiculo(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		// Completar poliza
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		// Crear endoso
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarLineaPoliza();
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();  
        // Cambiar Tipo de Plan y validar que no pueda guardar los cambio
        SoapSteps.endosoCambioVehiculo(args[11]);// agregar nuevo tipo de vehiculo
        
		SoapSteps.validarErrorControlado(args[12]);// validar error controlado
	}
	
	@DataProvider(name = "dataSoap")
	public Object[][] dataOncologico() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataSoap.csv",
				1, 13);
		return data;
	}
}