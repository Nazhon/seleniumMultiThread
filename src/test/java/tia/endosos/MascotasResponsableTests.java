package test.java.tia.endosos;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MascotasResponsableSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MascotasResponsableTests extends BaseTest{
	// Emision de Poliza Mascota Mestizo con chip
		@Test(priority = 1, enabled = true, dataProvider = "dataMascotasResponsable")
		public void EmisionPolizaMascotaResponsableMestizoChip(String args[]) throws InterruptedException {

			getTest().assignCategory("Mascotas_Responsable").assignDevice("Endoso_Poliza");
			setStep(getTest().createNode("Generación de un Endoso, modificando si tiene chip luego de emitir una póliza."));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			TransversalSteps.buscarEntidad(args[0], args[1]);
			TransversalSteps.crearPoliza(args[2], args[3]);
			//completar datos objeto
		    MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
		    MascotasResponsableSteps.seleccionarChip();
		  	TransversalSteps.calcularPrimaTotal();
		  	//completar póliza
			TransversalSteps.completarPoliza(args[9]);
			TransversalSteps.aceptarDocumentosPoliza();
			//completar datos endoso
	  		TransversalSteps.ingresarPolizaBusquedaRapida();
		    TransversalSteps.ingresarTipoEndoso(args[11]);
	        TransversalSteps.ingresarObjeto();
	        TransversalSteps.ajustarObjeto();
	        //cambiar plan
	        MascotasResponsableSteps.seleccionarChip();
	        TransversalSteps.completarPoliza(args[13]);
	        TransversalSteps.aceptarDocumentosPoliza();
	        //validar endoso
	        TransversalSteps.validarEndoso();
		}

		@DataProvider(name = "dataMascotasResponsable")
		public Object[][] dataMascotasResponsable() throws CsvValidationException, InterruptedException, IOException {

			Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataMascotasResponsable.csv",
					1, 14);
			return data;
		}
}
