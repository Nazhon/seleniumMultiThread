package test.java.tia.renovacion;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MedMalInstitucionSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MedMalInstitucionTests extends BaseTest{
	
	@Test(priority = 1, enabled = true, dataProvider = "dataMedMalInstitucion")
	public void ATMOQA390(String[] args) {

		getTest().assignCategory("MedMal").assignDevice("Renovacion_Poliza");
		setStep(getTest().createNode(
				"Generar la renovación de una póliza de Med Mala Praxis Clínica en donde se pueda resuscribir y retarificar a través de un endoso "
				+ "modificando los valores de coberturas y prima."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[0]);
		TransversalSteps.creacionDePolizaRRCCDirectoUnAnioAntes(args[3], args[7], args[4], args[5], args[6]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[8]);
		TransversalSteps.seleccionarCanalPago(args[9]);
		TransversalSteps.seleccionarCodigoMoneda(args[10]);
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.completarPolizaConIdLineaPoliza(args[11]);
		MedMalInstitucionSteps.camposObligatoriosInformacionAseguradoContratante(args[12], args[13], args[14], args[15],
				args[16], args[17], args[18], args[19], args[20], args[21], args[22]);
		MedMalInstitucionSteps.camposArriendoInstalaciones(args[23], args[24], args[25]);
		TransversalSteps.verificarModuloRiesgoDesplegado();
		MedMalInstitucionSteps.camposRiesgoMedMal(args[26], args[27]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[28]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaRenovar();
		TransversalSteps.manejoLineaPolizaDesdeEntidad();
		TransversalSteps.verificarActualizacionFechasPolizaRenovada();
		TransversalSteps.ajustaPolizaTrans(args[29]);
		TransversalSteps.seleccionarCategoriaRespaldo(args[30]);
		TransversalSteps.insertarNota(args[31]);
		TransversalSteps.guardarCambios();
		TransversalSteps.ingresarManejoObjetoPoliza();
		TransversalSteps.iniciarAjusteObjetoFechaHoy();
		MedMalInstitucionSteps.completarRiesgoResponsabilidadCivil(args[32]);
		MedMalInstitucionSteps.completarRiesgoOtrosRiesgos(args[33]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[34]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@DataProvider(name = "dataMedMalInstitucion")
	public Object[][] dataMedMalInstitucion() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("renovacion" + File.separator + "CSVDataMedMalInstitucion.csv", 1, 35);
		return data;
	}
}