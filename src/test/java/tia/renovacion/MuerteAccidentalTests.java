package test.java.tia.renovacion;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MuerteAccidentalSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MuerteAccidentalTests extends BaseTest{
	
	@Test(priority = 1, enabled = true, dataProvider = "dataMuerteAccidental")
    public void ATMOQA76(String args[]) throws InterruptedException {
        getTest().assignCategory("Muerte_Accidental").assignDevice("Renovacion");
        setStep(getTest().createNode(
                "Visualización de una renovación manual de una póliza de tipo Muerte Accidental en la plataforma TIA cumplido el año de vigencia."));

        // creando una póliza de AP para renovarla
        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		MuerteAccidentalSteps.crearPolizaAP2(args[2], args[3],args[4]);
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[5], args[6], args[7]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[8]);
		TransversalSteps.aceptarDocumentosPoliza();

		TransversalSteps.renovarPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEstadoPoliza("renovación");
        // ingresar a la poliza para validar estado
/*		MuerteAccidentalSteps.renovarPolizaMA();
		
		TransversalSteps.ingresarPolizaRenovar();
		MuerteAccidentalSteps.renovacion();

        //valida renovación
		TransversalSteps.ingresarPoliza();
		MuerteAccidentalSteps.validarEstadoRenovadaPoliza();*/
    }
	@Test(priority = 1, enabled = true, dataProvider = "dataMuerteAccidental")
    public void ATMOQA268(String args[]) throws InterruptedException {
        getTest().assignCategory("Muerte_Accidental").assignDevice("Renovacion");
        setStep(getTest().createNode(
                "Visualización de una renovación manual de una póliza de tipo Muerte Accidental en la plataforma TIA cumplido el año de vigencia."));

        // creando una póliza de AP para renovarla
        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		MuerteAccidentalSteps.crearPolizaAP2(args[2], args[3],args[4]);
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[5], args[6], args[7]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[8]);
		TransversalSteps.aceptarDocumentosPoliza();

		TransversalSteps.renovarPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEstadoPoliza("renovación");
        // ingresar a la poliza para validar estado
//		MuerteAccidentalSteps.renovarPolizaMA();
		
//		TransversalSteps.ingresarPolizaRenovar();
//		MuerteAccidentalSteps.renovacion();

        //valida renovación
//		TransversalSteps.ingresarPoliza();
//		MuerteAccidentalSteps.validarEstadoRenovadaPoliza();
    }
    @DataProvider(name = "dataMuerteAccidental")
    public Object[][] dataHogarFull() throws CsvValidationException, InterruptedException, IOException {

        Object[][] data = OpenCSV.getCSVParametersDescription("renovacion" + File.separator + "CSVDataMuerteAccidental.csv",1, 9);
        return data;
    }

}
