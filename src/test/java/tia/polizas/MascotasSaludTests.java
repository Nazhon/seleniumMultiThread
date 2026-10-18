package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.pageSteps.tia.productos.MascotasSaludSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.config.OpenCSV;
import test.java.BaseTest;

public class MascotasSaludTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA38(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar polizaa
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA39(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro raza"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasRaza(args[10]);
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 3, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA40(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo, añadiendo cremación"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasRaza(args[10]);
		MascotasSaludSteps.datosPlan(args[8]);
		MascotasSaludSteps.seleccionCremacion();
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 4, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA41(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo, añadiendo examenes"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasRaza(args[10]);
		MascotasSaludSteps.datosPlan(args[8]);
		MascotasSaludSteps.seleccionExamenes();
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 5, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA42(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo, añadiendo examenes y cremación"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		MascotasSaludSteps.seleccionCremacion();
		MascotasSaludSteps.seleccionExamenes();
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 6, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA37(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo con un asegurador menor de edad"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[36], args[37]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.validarMensajeContratanteMenorEdad(args[9]);
	}

	@Test(priority = 7, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA43(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo con un asegurador fallecido"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[38], args[39]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.validarMensajeContratanteFallecido(args[9]);
	}
	
	@Test(priority = 8, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA147(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Salud intermediado, del plan S y Brokers en TIA"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[16], args[14], args[15],args[40] ,args[32]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[17]); 
		TransversalSteps.validarPorcentajeComision(args[13]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 9, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA148(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Salud intermediado, del plan M, Brokers online y Asegura Online en TIA"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[21], args[19], args[20],args[40], args[33]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]); 
		TransversalSteps.validarPorcentajeComision(args[18]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 10, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA149(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Salud intermediado, del plan L, Brokers online SB y Lider BCI en TIA"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[25], args[23], args[24],args[40] ,args[34]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[26]); 
		TransversalSteps.validarPorcentajeComision(args[22]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 11, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA150(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza Mascota Salud intermediado, del plan Senior, Brokers online SB y Lider BCI en TIA"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaIntermediador(args[2], args[30], args[28], args[29],args[40], args[35]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[31]); 
		TransversalSteps.validarPorcentajeComision(args[27]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

//UAT	
	
	
	@Test(priority = 12, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA186(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,"uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 13, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA187(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro raza"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasRaza(args[10]);
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	
	@Test(priority = 14, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA188(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo, añadiendo cremación"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasRaza(args[10]);
		MascotasSaludSteps.datosPlan(args[8]);
		MascotasSaludSteps.seleccionCremacion();
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}
	@Test(priority = 15, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA189(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo, añadiendo examenes"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasRaza(args[10]);
		MascotasSaludSteps.datosPlan(args[8]);
		MascotasSaludSteps.seleccionExamenes();
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 16, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA190(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo, añadiendo examenes y cremación"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		MascotasSaludSteps.seleccionCremacion();
		MascotasSaludSteps.seleccionExamenes();
		TransversalSteps.calcularPrimaTotal();
		// comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 17, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA191(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo con un asegurador menor de edad"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[36], args[37]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.validarMensajeContratanteMenorEdad(args[9]);
	}

	@Test(priority = 18, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA192(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de póliza en TIA con cobertura Plan de cobertura asegurando una mascota de tipo perro mestizo con un asegurador fallecido"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[38], args[39]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.validarMensajeContratanteFallecido(args[9]);
	}
	
	
	
	@DataProvider(name = "dataMascotasSalud")
	public Object[][] dataMascotasSalud() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataMascotasSalud.csv",
				1, 41);
		return data;
	}
}
