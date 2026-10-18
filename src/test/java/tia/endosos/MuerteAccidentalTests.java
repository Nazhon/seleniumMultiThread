package test.java.tia.endosos;

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
	public void ATMOQA60(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode("Emision de poliza para Muerte Accidental endosando el Plan de cobertura 1 a plan cobertura 2"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment); 
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]); 
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
		//ingreso a la poliza
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[11]);
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();
		MuerteAccidentalSteps.datosPlanes2();
		TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
        //MuerteAccidentalSteps.validarEndosoAP();
        TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA61(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode("Emision de poliza para Muerte Accidental endosando el Plan de cobertura 2 a plan de cobertura 1"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
		//ingreso a la poliza
		TransversalSteps.ingresarPolizaBusquedaRapida();;
		TransversalSteps.ingresarTipoEndoso(args[11]);
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();
		MuerteAccidentalSteps.datosPlanes();
		TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
//        MuerteAccidentalSteps.validarEndosoAP();
        TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 3, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA62(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode("Validar que se permita realizar un endoso cuando haya 2 beneficiarios y eliminar 1 beneficiario "
				+ "(datos y porcentaje) para una póliza de Accidentes Personales."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[10]);
		MuerteAccidentalSteps.datosBeneficiarios(args[8], args[9], args[10]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
		//ingreso a la poliza
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[11]);
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();
        MuerteAccidentalSteps.eliminarBeneficiarioAP();
        MuerteAccidentalSteps.cambiarPorcentajeAP(args[6]);
		TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
//        MuerteAccidentalSteps.validarEndosoAP();
        TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 4, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA220(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode("Emision de poliza para Muerte Accidental endosando el Plan de cobertura 1 a plan cobertura 2"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat"); 
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]); 
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
		//ingreso a la poliza
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[11]);
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();
		MuerteAccidentalSteps.datosPlanes2();
		TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
        TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 5, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA221(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode("Emision de poliza para Muerte Accidental endosando el Plan de cobertura 2 a plan de cobertura 1"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
		//ingreso a la poliza
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[11]);
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();
		MuerteAccidentalSteps.datosPlanes();
		TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
        TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 6, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA222(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode("Validar que se permita realizar un endoso cuando haya 2 beneficiarios y eliminar 1 beneficiario "
				+ "(datos y porcentaje) para una póliza de Accidentes Personales."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[10]);
		MuerteAccidentalSteps.datosBeneficiarios(args[8], args[12], args[10]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
		//ingreso a la poliza
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[11]);
        TransversalSteps.ingresarObjeto();
        TransversalSteps.ajustarObjeto();
        MuerteAccidentalSteps.eliminarBeneficiarioAP();
        MuerteAccidentalSteps.cambiarPorcentajeAP(args[6]);
		TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //validar endoso
        TransversalSteps.validarEndoso();
	}
	
	@DataProvider(name = "dataMuerteAccidental")
	public Object[][] dataMuerteAccidental() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataMuerteAccidental.csv", 1, 13);
		return data;
	}
}
