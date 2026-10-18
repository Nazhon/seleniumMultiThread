package test.java.tia.renovacion;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MascotasSaludSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;


public class MascotasSaludTests extends BaseTest {
	
	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA52(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Renovacion");
		setStep(getTest().createNode("Visualización de una renovación manual de una póliza en la plataforma TIA cumplido el año de vigencia."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaParaRenovar(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		
		// ingresar a la poliza para validar estado
        TransversalSteps.renovarPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEstadoPoliza("renovación");
	}
	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA264(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Renovacion");
		setStep(getTest().createNode("Visualización de una renovación manual de una póliza en la plataforma TIA cumplido el año de vigencia."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaParaRenovar(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		
		// ingresar a la poliza para validar estado
        TransversalSteps.renovarPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEstadoPoliza("renovación");
	}
	
	
	@DataProvider(name = "dataMascotasSalud")
	public Object[][] dataMascotasSalud() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("renovacion" + File.separator + "CSVDataMascotasSalud.csv",1, 12);
		return data;
	}
}
