package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.HogarFullSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class HogarFullTests extends BaseTest {
	@Test(priority = 1, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA10(String[] args) {

		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA asegurando una propiedad de tipo casa en condominio sin coberturas ni asistencias adicionales"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		HogarFullSteps.seleccionarTipoContratante(args[4]);
		if (environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[19]);
		} else {
			HogarFullSteps.identificarPropiedad(args[5]);
		}
		HogarFullSteps.seleccionarPropiedadCasaCondominio(args[6]);
		HogarFullSteps.completarInformacionPropiedad(args[9], args[10], args[11], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13], args[14]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
		
	}
	
	@Test(priority = 2, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA19(String[] args) {
		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA asegurando una propiedad de tipo departamento con cobertura adicional de Robo"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
		HogarFullSteps.seleccionarTipoContratante(args[4]);
		if (environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[19]);
		} else {
			HogarFullSteps.identificarPropiedad(args[5]);
		}
		HogarFullSteps.seleccionarPropiedadDepartamento(args[7], args[8]);
		HogarFullSteps.completarInformacionPropiedad(args[9], args[10], args[11], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13],args[14]);
		HogarFullSteps.agregarCoberturaRoboContenido(args[18]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 3, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA21(String[] args) {
		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA asegurando una propiedad añadiendo todas las coberturas y asistencias adicionales"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
		HogarFullSteps.seleccionarTipoContratante(args[4]);
		if (environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[19]);
		} else {
			HogarFullSteps.identificarPropiedad(args[5]);
		}
		HogarFullSteps.seleccionarPropiedadDepartamento(args[7], args[8]);
		HogarFullSteps.completarInformacionPropiedad(args[9], args[10], args[11], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13],args[14]);
		HogarFullSteps.ampliarVentana();
		HogarFullSteps.agregarCoberturaRoboContenido(args[18]);
		HogarFullSteps.agregarCoberturaSismo();
		HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
		HogarFullSteps.agregarAsistenciaCyber();
		HogarFullSteps.agregarAsistenciaMascotas();
		HogarFullSteps.agregarAsistenciaTelemedicina();
		HogarFullSteps.unirVentana();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

//UAT
	
	@Test(priority = 4, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA209(String[] args) {

		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA asegurando una propiedad de tipo casa en condominio sin coberturas ni asistencias adicionales"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
		HogarFullSteps.seleccionarTipoContratante(args[4]);
		if (environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[19]);
		} else {
			HogarFullSteps.identificarPropiedad(args[5]);
		}
		HogarFullSteps.seleccionarPropiedadCasaCondominio(args[6]);
		HogarFullSteps.completarInformacionPropiedad(args[9], args[10], args[11], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13],args[14]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
		
	}
	
	@Test(priority = 5, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA210(String[] args) {
		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA asegurando una propiedad de tipo departamento con cobertura adicional de Robo"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
		HogarFullSteps.seleccionarTipoContratante(args[4]);
		HogarFullSteps.identificarPropiedad(args[5]);
		HogarFullSteps.seleccionarPropiedadDepartamento(args[7], args[8]);
		HogarFullSteps.completarInformacionPropiedad(args[9], args[10], args[11], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13],args[14]);
		HogarFullSteps.agregarCoberturaRoboContenido(args[18]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 6, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA211(String[] args) {
		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA asegurando una propiedad añadiendo todas las coberturas y asistencias adicionales"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
		HogarFullSteps.seleccionarTipoContratante(args[4]);
		HogarFullSteps.identificarPropiedad(args[19]);
		HogarFullSteps.seleccionarPropiedadDepartamento(args[7], args[8]);
		HogarFullSteps.completarInformacionPropiedad(args[9], args[10], args[11], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13],args[14]);
		HogarFullSteps.ampliarVentana();
		HogarFullSteps.agregarCoberturaRoboContenido(args[18]);
		HogarFullSteps.agregarCoberturaSismo();
		HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
		HogarFullSteps.agregarAsistenciaCyber();
		HogarFullSteps.agregarAsistenciaMascotas();
		HogarFullSteps.agregarAsistenciaTelemedicina();
		HogarFullSteps.unirVentana();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 7, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA303(String[] args) {
		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de una póliza HGSV sin beneficiario"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPolizaCorredores(args[2], args[22], args[23], args[24], args [25]);
		HogarFullSteps.completarInformacionPropiedadcontipo(args[5], args[26], args[27], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13],args[14]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	//hay que complementar con la información del video de Alexander
	@Test(priority = 7, enabled = true,  dataProvider = "dataHogarFull")
	public void ATMOQA304(String[] args) {
		getTest().assignCategory("Hogar_Full").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de una póliza HGSV con beneficiario"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPolizaCorredores(args[2], args[22], args[23], args[24], args [25]);
		HogarFullSteps.completarInformacionPropiedadcontipo(args[5], args[26], args[27], args[12]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[13],args[14]);
		TransversalSteps.calcularPrimaTotal();
		HogarFullSteps.ingresarBeneficiario(args[28], args[29]);
		TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@DataProvider(name = "dataHogarFull")
	 
	 
	public Object[][] dataHogarFull() throws CsvValidationException, InterruptedException, IOException {	 
		 
	Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataHogarFull.csv", 1, 30);	 
	return data;	 
		}

}

