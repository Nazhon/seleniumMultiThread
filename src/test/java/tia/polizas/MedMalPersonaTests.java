package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MedMalPersonaSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MedMalPersonaTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA393(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Med Mala Praxis Individual asegurando un Médico (Entidad Natural) con tipo de Moneda UF"
				+ " con una vigencia mínimo de 3 meses, Canal de Ventas directo/ Broker Tradicional y Reaseguro configurado."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaConFechaTermino(args[4], args[5], args[6], args[7], args[8], args[9]);
		TransversalSteps.insertarComision(args[10]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[11]);
		TransversalSteps.seleccionarCanalPago(args[12]);
		TransversalSteps.seleccionarCodigoMoneda(args[13]);
		TransversalSteps.seleccionarAgentePoliza(args[14], args[15]);
		TransversalSteps.insertarParticipacionAgente(args[16]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[17]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[18], args[19], args[20], args[21],
				args[22]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[23]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[24]);
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA396(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Med Mala Praxis Individual asegurando un Médico con tipo de Moneda UF con una vigencia de 12 meses, "
				+ "Canal de Ventas directo/ Broker Tradicional y Reaseguro configurado."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[6], args[7], args[8], args[25]);
		TransversalSteps.verificarDuracionAnioPoliza();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[11]);
		TransversalSteps.seleccionarCanalPago(args[12]);
		TransversalSteps.seleccionarCodigoMoneda(args[13]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[17]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[18], args[19], args[20], args[21],
				args[22]);
		MedMalPersonaSteps.camposTituloConvalidadoAseguradoContratante(args[26]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[23]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[24]);
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA397(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Med Mala Praxis Individual asegurando un Médico con tipo de Moneda UF con una vigencia máxima de 18 meses,"
				+ " Canal de Ventas directo/ Broker Tradicional y Reaseguro configurado."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[6], args[7], args[8], args[9]);
		TransversalSteps.seleccionarCambioFechaMeses(args[27]);
		TransversalSteps.insertarComision(args[10]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[11]);
		TransversalSteps.seleccionarCanalPago(args[12]);
		TransversalSteps.seleccionarCodigoMoneda(args[13]);
		TransversalSteps.seleccionarAgentePoliza(args[14], args[15]);
		TransversalSteps.insertarParticipacionAgente(args[16]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[17]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[18], args[19], args[20], args[21],
				args[22]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[23]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[24]);
	}
	
	@Test(priority = 4, enabled = true, dataProvider = "dataMedMalPersona")
	public void ATMOQA394(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una cotización para Med Mala Praxis Individual asegurando un Médico con una vigencia de 15 días (estándar)."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[4], args[6], args[7], args[8], args[9]);
		TransversalSteps.seleccionarFechaFuturaDias(args[28]);
		TransversalSteps.insertarComision(args[10]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[11]);
		TransversalSteps.seleccionarCanalPago(args[12]);
		TransversalSteps.seleccionarCodigoMoneda(args[13]);
		TransversalSteps.seleccionarAgentePoliza(args[14], args[15]);
		TransversalSteps.insertarParticipacionAgente(args[16]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[17]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[18], args[19], args[20], args[21],
				args[22]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[29]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@DataProvider(name = "dataMedMalPersona")
	public Object[][] dataMedMalPersona() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataMedMalPersona.csv",
				1, 30);
		return data;
	}
}
