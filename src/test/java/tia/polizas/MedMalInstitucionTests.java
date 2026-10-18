package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MedMalInstitucionSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MedMalInstitucionTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA388(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Med Mala Praxis Clínica asegurando una Institución Médica (Entidad Natural) con tipo de Moneda UF "
				+ "con una vigencia mínimo de 3 meses, Canal de Ventas directo y Reaseguro configurado."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaConFechaTermino(args[3], args[4], args[5], args[6], args[7], args[8]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17], args[18], args[19], args[20], args[21], args[22], args[23]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[24], args[25], args[26]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[27], args[28]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[29]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[30]);
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA392(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Med Mala Praxis Clínica asegurando una Institución Médica (Entidad Jurídica)con tipo de Moneda UF"
				+ " con una vigencia máxima de 18 meses, Canal de Ventas Broker Tradicional y Reaseguro configurado."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[3], args[5], args[6], args[7], args[31]);
		TransversalSteps.seleccionarCambioFechaMeses(args[32]);
		TransversalSteps.insertarComision(args[33]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.seleccionarAgentePoliza(args[34], args[35]);
		TransversalSteps.insertarParticipacionAgente(args[36]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[37], args[38], args[39],
				args[40], args[41], args[42], args[43], args[44], args[45], args[46]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[47], args[25], args[26]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[48], args[49]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[29]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[30]);
	}

	@Test(priority = 3, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA391(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una emisión del producto Med Mala Praxis Clínica asegurando una Institución Médica con tipo de Moneda UF con una vigencia de 12 meses,"
				+ " Canal de Ventas directo y Reaseguro configurado."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[3], args[5], args[6], args[7], args[8]);
		TransversalSteps.verificarDuracionAnioPoliza();
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[37], args[38], args[39],
				args[40], args[41], args[42], args[43], args[44], args[45], args[46]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[47], args[25], args[26]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[48], args[49]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[29]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.verificarReaseguro(args[30]);
	}
	
	@Test(priority = 4, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA389(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Generar una cotización para Med Mala Praxis Clínica asegurando una institución médica con una vigencia de 15 días (estándar)."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.crearPolizaSinModificarFechas(args[3], args[5], args[6], args[7], args[8]);
		TransversalSteps.seleccionarFechaFuturaDias(args[50]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17], args[18], args[19], args[20], args[21], args[22], args[23]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[24], args[25], args[26]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[27], args[28]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[51]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@DataProvider(name = "dataMedMalInstitucion")
	public Object[][] dataMedMalInstitucion() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("emision" + File.separator + "CSVDataMedMalInstitucion.csv", 1, 52);
		return data;
	}
}
