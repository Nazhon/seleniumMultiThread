package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageEvents.tia.transversal.TransversalEvents;
import main.java.pageSteps.tia.productos.MuerteAccidentalSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.properties.EntidadProperties;
import test.java.BaseTest;

public class MuerteAccidentalTests extends BaseTest {
	
	@Test(priority = 1, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA12(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 1 con comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA14(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 2 con comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 3, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA16(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 1 sin comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 4, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA17(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 2 sin comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}


	@Test(priority = 5, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA181(String args[]) {
		
		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Crear un corredor y posteriormente realizar una contratación el mismo"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.crearEntidadCorredor("Luis", "Fernandez","Corredor","23-09-1998","RUT","","1234","Primera Categoria");
		TransversalSteps.ingresarCorreosCorredor("pruebas.qa@sbins.cl");
		TransversalSteps.ingresarResumenEntidadCreada();
		TransversalSteps.crearDetallePago("BBANK Corredor transferencia bancaria","Banco BICE","1234","CV Cuenta Vista");
		TransversalSteps.crearCuenta("Corredor","Cuenta Corredor","BBANK Corredor transferencia bancaria","BBANK Corredor transferencia bancaria","UF Unidad de Fomento","MONTH End Month");
		TransversalSteps.convertirEntidadenCorredor("10 Corredor de seguro","24.10.2024","Brokers","All Products","Offline","1R 1era recaudacion","20","0","50");
		//TransversalSteps.ingresarResumenEntidadCreada();
		TransversalEvents.buscarEntidad("Muerte Accemi");
		TransversalEvents.seleccionarContratante("Auto Muerte Accemi");
		TransversalEvents.crearNuevaPoliza("Accidentes Personales");
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta("3 Brokers");
		TransversalEvents.seleccionarPlataforma("Offline");
		TransversalEvents.vincularEnlaceCorredor("Corredor",EntidadProperties.entidadIdTexto);
		TransversalEvents.crearNuevaLineaPoliza();

		//TransversalEvents.guardarCambios();

	}

//UAT
	
	@Test(priority = 6, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA212(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 1 con comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 7, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA213(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 2 con comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosRiesgos();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 8, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA214(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 1 sin comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 9, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA215(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan de cobertura Plan 2 sin comision y 1 beneficiario"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		MuerteAccidentalSteps.datosPlanes2();
		MuerteAccidentalSteps.datosBeneficiarios(args[4], args[5], args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}	

	@Test(priority = 10, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA306(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan Estandar, Monto asegurado de 3500 UF,"
				+ " vigencia 12 meses, minimo de 1 empleado y comision del 20%"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.busquedaRapidaElemento("29941300-4");
		TransversalSteps.crearPolizaIntermediador(args[12], args[11], args[8], args[9], args[10],args[13]);
		MuerteAccidentalSteps.completaInformaciónLaboral(args[14], args[15], args[16]);
		MuerteAccidentalSteps.completaPlanesTerminosCondicionesyCobertura(args[17], args[18], args[19],args[20]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 11, enabled = true, dataProvider = "dataMuerteAccidental")
	public void ATMOQA309(String args[]) throws InterruptedException {

		getTest().assignCategory("Muerte_Accidental").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Muerte Accidental con Plan Full, Monto asegurado de 100 UF, "
				+ "vigencia 12 meses, minimo de 1 empleado y comision del 20%"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.busquedaRapidaElemento("29941300-4");
		TransversalSteps.crearPolizaIntermediador(args[12], args[11], args[8], args[9], args[10],args[13]);
		MuerteAccidentalSteps.completaInformaciónLaboral(args[14], args[24], args[23]);
		MuerteAccidentalSteps.completaPlanesTerminosCondicionesyCobertura(args[21], args[18], args[19],args[22]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[7]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	
	

	@DataProvider(name = "dataMuerteAccidental")
	public Object[][] dataMuerteAccidental() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataMuerteAccidental.csv", 1, 25);
		return data;
	}

}
