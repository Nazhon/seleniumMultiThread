package test.java.tia.endosos;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MascotasSaludSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

public class MascotasSaludTests extends BaseTest{
	
	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasSalud")
    public void ATMOQA48(String args[]) throws InterruptedException {

        getTest().assignCategory("Mascotas_Salud").assignDevice("Endoso_Poliza");
        setStep(getTest().createNode("Generación de un Endoso, modificando el plan luego de emitir una póliza."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
        //completar datos objeto
  		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
  		MascotasSaludSteps.datosMascotasMestizo();
  		MascotasSaludSteps.datosPlan(args[8]);
  		TransversalSteps.calcularPrimaTotal();
  		//completar poliza
  		TransversalSteps.completarPoliza(args[9]);
  		TransversalSteps.aceptarDocumentosPoliza();
  		//completar datos endoso
  		TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[13]);
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        //cambiar plan
        MascotasSaludSteps.datosPlan(args[12]);
        TransversalSteps.completarPoliza(args[14]);
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
        TransversalSteps.validarEndoso();
    }
	
	@Test(priority = 2, enabled = true, dataProvider = "dataMascotasSalud")
    public void ATMOQA177(String args[]) throws InterruptedException {

        getTest().assignCategory("Mascotas_Salud").assignDevice("Endoso_Poliza");
        setStep(getTest().createNode("Generar una póliza mascota salud, realizar un endoso y una cancelación corroborando que se realice correctamente."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
        //completar datos objeto
  		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
  		MascotasSaludSteps.datosMascotasMestizo();
  		MascotasSaludSteps.datosPlan(args[8]);
  		TransversalSteps.calcularPrimaTotal();
  		//completar poliza
  		TransversalSteps.completarPoliza(args[9]);
  		TransversalSteps.aceptarDocumentosPoliza();
  		//completar datos endoso
  		TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[13]);
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        //cambiar plan
        MascotasSaludSteps.datosPlan(args[12]);
        TransversalSteps.completarPoliza(args[14]);
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
        TransversalSteps.validarEndoso();
        TransversalSteps.ingresarPolizaBusquedaRapida();
	  TransversalSteps.ajustaPolizaTrans("402");
      TransversalSteps.seleccionarMotivoCancelacionPoliza(args[17]);
      TransversalSteps.completarPoliza("endoso");
      TransversalSteps.aceptarDocumentosPoliza();
      TransversalSteps.validaTransaccionCancelacionPoliza();
    }
	
	@Test(priority = 3, enabled = true, dataProvider = "dataMascotasSalud")
    public void ATMOQA216(String args[]) throws InterruptedException {

        getTest().assignCategory("Mascotas_Salud").assignDevice("Endoso_Poliza");
        setStep(getTest().createNode("Generación de un Endoso, modificando el plan luego de emitir una póliza."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
        //completar datos objeto
  		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
  		MascotasSaludSteps.datosMascotasMestizo();
  		MascotasSaludSteps.datosPlan(args[8]);
  		TransversalSteps.calcularPrimaTotal();
  		//completar poliza
  		TransversalSteps.completarPoliza(args[9]);
  		TransversalSteps.aceptarDocumentosPoliza();
  		//completar datos endoso
  		TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[13]);
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        //cambiar plan
        MascotasSaludSteps.datosPlan(args[12]);
        TransversalSteps.completarPoliza(args[14]);
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
        TransversalSteps.validarEndoso();
    }
	
	@DataProvider(name = "dataMascotasSalud")
	public Object[][] dataMascotasSalud() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataMascotasSalud.csv", 1, 18);
		return data;
	}
	
}
