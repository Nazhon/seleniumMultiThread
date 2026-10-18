package test.java.tia.renovacion;

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
	public void ATMOQA395(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Renovacion_Poliza");
		setStep(getTest().createNode(
				"Generar la renovación de una póliza de Med Mala Praxis Individual en donde se pueda resuscribir y retarificar a través de un endoso modificando"
				+ " los valores de coberturas y prima."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.creacionDePolizaRRCCDirectoUnAnioAntes(args[4], args[8], args[5], args[6], args[7]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[9]);
		TransversalSteps.seleccionarCanalPago(args[10]);
		TransversalSteps.seleccionarCodigoMoneda(args[11]);
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.completarPolizaConIdLineaPoliza(args[12]);
		MedMalPersonaSteps.camposObligatoriosInformacionAseguradoContratante(args[13], args[14], args[15], args[16],
				args[17]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[18]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaRenovar();
		TransversalSteps.manejoLineaPolizaDesdeEntidad();
		TransversalSteps.verificarActualizacionFechasPolizaRenovada();
		TransversalSteps.ajustaPolizaTrans(args[19]);
		TransversalSteps.seleccionarCategoriaRespaldo(args[20]);
		TransversalSteps.insertarNota(args[21]);
		TransversalSteps.guardarCambios();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.iniciarAjusteObjetoFechaHoy();
		MedMalPersonaSteps.completarRiesgoResponsabilidadCivil(args[22]);
		MedMalPersonaSteps.completarRiesgoOtrosRiesgos(args[23]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[24]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@DataProvider(name = "dataMedMalPersona")
	public Object[][] dataMedMalPersona() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("renovacion" + File.separator + "CSVDataMedMalPersona.csv", 1, 25);
		return data;
	}
}