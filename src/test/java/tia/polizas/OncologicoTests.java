package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.OncologicoSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class OncologicoTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA63(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Fonasa Individual con mismo asegurado y contratante en tramo 1"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal("3,050069");
		TransversalSteps.completarPoliza("Cotización");
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA380(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Isapre Individual con mismo asegurado y contratante en tramo 2"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[32], args[33]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal("13,731546");
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	@Test(priority = 3, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA381(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Fonasa Individual con mismo asegurado y contratante en tramo 3"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[34], args[35]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal("21,815523");
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	@Test(priority = 4, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA64(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Fonasa Familiar con distinto asegurado y contratante"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarDiferenteAsegurado(args[4], args[5]);
		OncologicoSteps.completarPlanFonasaFamiliar();
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal("13,286093");
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 5, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA65(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Isapre Individual con mismo asegurado y contratante aplicando un descuento"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanIsapreIndividual(args[6]);
		OncologicoSteps.calcularPrimaTotalOncoDsct(args[11], args[14]);
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 6, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA66(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Isapre Familiar con mismo asegurado y contratante"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarDiferenteAsegurado(args[4], args[5]);
		OncologicoSteps.completarPlanIsapreFamiliar(args[6]);
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
	}

	@Test(priority = 7, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA67(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA donde el contratante sea menor de edad"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[7], args[8]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.validarMensajeContratanteMenorEdad(args[10]);
	}

	@Test(priority = 8, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA68(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA donde el contratante tenga fecha de fallecimiento"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[12], args[13]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.validarMensajeContratanteFallecido(args[10]);
	}

	@Test(priority = 9, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA151(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers\" y plataforma \"Marsh\" en TIA de un plan Fonasa Individual."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[15], args[18], args[20],args[26] ,args[22]);
		TransversalSteps.validarPorcentajeComision(args[24]);
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 10, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA152(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers online\" y plataforma \"Wallmart\" en TIA de un plan Isapre Individual."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[17], args[19], args[21],args[26], args[23]);
		TransversalSteps.validarPorcentajeComision(args[25]);
		OncologicoSteps.completarPlanIsapreIndividual(args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 11, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA153(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers online SB\" y plataforma \"Marsh\" en TIA de un plan Fonasa familiar."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[16], args[18], args[20], args[26], args[22]);
		TransversalSteps.validarPorcentajeComision(args[24]);
		OncologicoSteps.completarPlanFonasaFamiliar();
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 12, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA154(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers online SB\" y plataforma \"Wallmart\" en TIA de un plan Isapre familiar."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[16], args[19], args[21], args[26], args[23]);
		TransversalSteps.validarPorcentajeComision(args[25]);
		OncologicoSteps.completarPlanIsapreFamiliar(args[6]);
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
//UAT
	
	@Test(priority = 13, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA199(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Fonasa Individual con mismo asegurado y contratante"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 14, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA200(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Fonasa Familiar con distinto asegurado y contratante"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarDiferenteAsegurado(args[4], args[5]);
		OncologicoSteps.completarPlanFonasaFamiliar();
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 15, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA201(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Isapre Individual con mismo asegurado y contratante aplicando un descuento"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaConIDLineaPoliza(args[2], args[3],"ONCOI - Oncológico IRAM");
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanIsapreIndividual(args[6]);
		OncologicoSteps.calcularPrimaTotalOncoDsct(args[11], args[14]);
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.cancelarPoliza();
	}

	@Test(priority = 16, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA202(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA de un plan Isapre Familiar con mismo asegurado y contratante"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarDiferenteAsegurado(args[4], args[5]);
		OncologicoSteps.completarPlanIsapreFamiliar(args[6]);
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
	}

	@Test(priority = 17, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA203(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA donde el contratante sea menor de edad"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[7], args[8]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.validarMensajeContratanteMenorEdad(args[10]);
	}

	@Test(priority = 18, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA204(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA donde el contratante tenga fecha de fallecimiento"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[12], args[13]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		TransversalSteps.identificarMismoAsegurado();
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.validarMensajeContratanteFallecido(args[10]);
	}

	@Test(priority = 19, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA205(String[] args) {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers\" y plataforma \"Marsh\" en TIA de un plan Fonasa Individual."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[15], args[18], args[20], args[22]);
		TransversalSteps.validarPorcentajeComision(args[24]);
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 20, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA206(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers online\" y plataforma \"Wallmart\" en TIA de un plan Isapre Individual."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[17], args[19], args[21], args[23]);
		TransversalSteps.validarPorcentajeComision(args[25]);
		OncologicoSteps.completarPlanIsapreIndividual(args[6]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 21, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA207(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers online SB\" y plataforma \"Marsh\" en TIA de un plan Fonasa familiar."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[16], args[18], args[20], args[22]);
		TransversalSteps.validarPorcentajeComision(args[24]);
		OncologicoSteps.completarPlanFonasaFamiliar();
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 22, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA208(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza intermediada con canal de venta \"Brokers online SB\" y plataforma \"Wallmart\" en TIA de un plan Isapre familiar."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[3], args[16], args[19], args[21], args[23]);
		TransversalSteps.validarPorcentajeComision(args[25]);
		OncologicoSteps.completarPlanIsapreFamiliar(args[6]);
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}	
	
	@Test(priority = 23, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA319(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza colectiva generando Póliza Maestra con canal de venta \"Brokers online SB\" y plataforma \"AON\" en TIA y "
				+ "luego asociando un plan familiar con Isapre."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[29], args[30]);
		TransversalSteps.crearPolizaIntermediador(args[31], args[16], args[27], args[28], args[26], args[23]);
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.visitarPaginaHome();
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[2], args[16], args[27], args[28], args[26], args[23]);
		OncologicoSteps.completarPlanIsapreFamiliar(args[6]);
		OncologicoSteps.agregarAseguradoAdicional(args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 24, enabled = true, dataProvider = "dataOncologico")
	public void ATMOQA318(String args[]) throws InterruptedException {

		getTest().assignCategory("Oncológico").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza colectiva generando Póliza Maestra con canal de venta \"Brokers online SB\" y plataforma \"AON\" en TIA y "
				+ "luego asociando un plan individual con Fonasa."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[29], args[30]);
		TransversalSteps.crearPolizaIntermediador(args[31], args[16], args[27], args[28], args[26], args[23]);
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.visitarPaginaHome();
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediadorConPolizaMaestra(args[2], args[16], args[27], args[28], args[26], args[23]);
		OncologicoSteps.completarPlanFonasaIndividual();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@DataProvider(name = "dataOncologico")
	public Object[][] dataOncologico() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataOncologico.csv", 1,36);
		return data;
	}
}
