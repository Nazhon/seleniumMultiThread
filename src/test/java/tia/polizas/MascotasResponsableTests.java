package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.pageSteps.tia.productos.MascotasResponsableSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.config.OpenCSV;
import test.java.BaseTest;

public class MascotasResponsableTests extends BaseTest {

	// Emision de Poliza Mascota Mestizo con chip
	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA11(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Responsable en TIA asegurando una mascota de linaje mestizo con chip"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
		MascotasResponsableSteps.seleccionarChip();
		TransversalSteps.calcularPrimaTotal();
		// completar póliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	// Emision de Poliza Mascota de raza
	@Test(priority = 2, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA13(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza Mascota Responsable en TIA asegurando una mascota de linaje raza"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaRaza(args[4], args[5], args[6], args[7], "Raza", args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar póliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	

	// Emision de Poliza a menor edad
	@Test(priority = 4, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA22(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza de Mascota Responsable donde el contratante es menor de edad"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad("Hernandez Hdz", "Samuel");
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
		TransversalSteps.validarMensajeContratanteMenorEdad(args[9]);
	}

	// Emision de Poliza fallecido
	@Test(priority = 5, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA18(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza de Mascotas Responsable donde el contratante esté fallecido"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad("El Fallecido", "Juan");
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
		TransversalSteps.validarMensajeContratanteFallecido(args[9]);
	}

	// Emision de Poliza entidad que no sea persona
	@Test(priority = 6, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA29(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Responsable donde el contratante no sea de tipo entidad 'Persona' dentro de la plataforma de TIA."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad("Empresa Prueba", "Empresa Prueba");
		TransversalSteps.crearPolizaAlternativo(args[2]);
		// validar mensaje de error
		TransversalSteps.validarMensajeContratanteErroneo();
	}

	// Emision de Poliza para una persona inhabilitada en el sistema
	@Test(priority = 7, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA34(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Responsable donde el contratante esté inhabilitado dentro de la plataforma de TIA."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad("Bilitado Cortés", "Elina");
		TransversalSteps.crearPolizaAlternativo(args[2]);
		// validar mensaje de error
		TransversalSteps.validarMensajeContratanteInhabilitado();
	}

//UAT
	
	

	// Emision de Poliza Mascota Mestizo con chip
	@Test(priority = 8, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA193(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Responsable en TIA asegurando una mascota de linaje mestizo con chip"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
		MascotasResponsableSteps.seleccionarChip();
		TransversalSteps.calcularPrimaTotal();
		// completar póliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	// Emision de Poliza Mascota de raza
	@Test(priority = 9, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA194(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza Mascota Responsable en TIA asegurando una mascota de linaje raza"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaRaza(args[4], args[5], args[6], args[7], "Raza", args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar póliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	

	// Emision de Poliza a menor edad
	@Test(priority = 10, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA195(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza de Mascota Responsable donde el contratante es menor de edad"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad("Hernandez Hdz", "Samuel");
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
		TransversalSteps.validarMensajeContratanteMenorEdad(args[9]);
	}

	// Emision de Poliza fallecido
	@Test(priority = 11, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA196(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza de Mascotas Responsable donde el contratante esté fallecido"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad("El Fallecido", "Juan");
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], args[12]);
		TransversalSteps.validarMensajeContratanteFallecido(args[9]);
	}

	// Emision de Poliza entidad que no sea persona
	@Test(priority = 12, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA197(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Responsable donde el contratante no sea de tipo entidad 'Persona' dentro de la plataforma de TIA."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad("Empresa Prueba", "Empresa Prueba");
		TransversalSteps.crearPolizaAlternativo(args[2]);
		// validar mensaje de error
		TransversalSteps.validarMensajeContratanteErroneo();
	}

	// Emision de Poliza para una persona inhabilitada en el sistema
	@Test(priority = 13, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA198(String args[]) {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Responsable donde el contratante esté inhabilitado dentro de la plataforma de TIA."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad("Bilitado Cortés", "Elina");
		TransversalSteps.crearPolizaAlternativo(args[2]);
		// validar mensaje de error
		TransversalSteps.validarMensajeContratanteInhabilitado();
	}

	
	
	@DataProvider(name = "dataMascotasResponsable")
	public Object[][] dataMascotasResponsable() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("emision" + File.separator + "CSVDataMascotasResponsable.csv", 1, 13);
		return data;
	}
}
