package test.java.tia.renovacion;



import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MascotasResponsableSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;


public class MascotasResponsableTests extends BaseTest {

	 @Test(priority = 1, enabled = true, dataProvider = "dataMascotasResponsables")
	    public void ATMOQA78(String args[]) throws InterruptedException {
	        getTest().assignCategory("Mascotas_Responsable").assignDevice("Renovacion");
	        setStep(getTest().createNode("Renovación manual de una póliza de tipo Mascota Responsable en la plataforma TIA cumplido el año de vigencia"));
	        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	        TransversalSteps.buscarEntidad(args[0], args[1]);
	        // Creando póliza MR para renovarla
	        TransversalSteps.crearPolizaRenovar(args[2], args[3]);
	        MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
			TransversalSteps.calcularPrimaTotal();
			// completar póliza
			TransversalSteps.completarPoliza(args[9]);
			TransversalSteps.aceptarDocumentosPoliza();
	        
	        //valida renovación
			TransversalSteps.renovarPoliza();
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.validarEstadoPoliza("renovación");
			
	    }
//UAT 
	 
	 @Test(priority = 2, enabled = true, dataProvider = "dataMascotasResponsables")
	    public void ATMOQA263(String args[]) throws InterruptedException {
	        getTest().assignCategory("Mascotas_Responsable").assignDevice("Renovacion");
	        setStep(getTest().createNode("Renovación manual de una póliza de tipo Mascota Responsable en la plataforma TIA cumplido el año de vigencia"));
	        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
	        TransversalSteps.buscarEntidad(args[0], args[1]);
	        // Creando póliza MR para renovarla
	        TransversalSteps.crearPolizaRenovar(args[2], args[3]);
	        MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
			TransversalSteps.calcularPrimaTotal();
			// completar póliza
			TransversalSteps.completarPoliza(args[9]);
			TransversalSteps.aceptarDocumentosPoliza();
	        
	        //valida renovación
			TransversalSteps.renovarPoliza();
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.validarEstadoPoliza("renovación");
			
	    }
	 
	    @DataProvider(name = "dataMascotasResponsables")
	    public Object[][] dataMascotasResponsables() throws CsvValidationException, InterruptedException, IOException {

	        Object[][] data = OpenCSV.getCSVParametersDescription("renovacion" + File.separator + "CSVDataMascotasResponsable.csv",1, 13);
	        return data;
	    }
}
