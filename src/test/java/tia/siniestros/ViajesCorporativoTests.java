package test.java.tia.siniestros;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.ViajesCorporativosSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;

public class ViajesCorporativoTests extends BaseTest {
	@Test(priority = 1, enabled = true, dataProvider = "dataViajesCorporativo")
	public void ATMOQA251(String args[]) throws InterruptedException {
		getTest().assignCategory("Viajes_Corporativo").assignDevice("Siniestros");
		setStep(getTest().createNode("TIA-SINIESTROS-VC- Generación de un siniestro en TIA para una póliza vigente Viajes Corporativo."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.busquedaRapidaElemento(args[0]);
        TransversalSteps.crearPolizaIntermediadorSiniestro(args[1], args[2], args[3], args[4],args[26] ,args[5]);
        ViajesCorporativosSteps.seleccionarTipodePlan(args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();;
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		ViajesCorporativosSteps.seleccionarProductoVC(); 
		// completa datos de un siniestro
		TransversalSteps.seleccionFechaSiniestro();
		ViajesCorporativosSteps.completarEvento(args[14], args[15], args[16]);
		TransversalSteps.insertarDescripcionHecho(args[17]);
		TransversalSteps.seleccionarLugarSiniestro(args[18]);
		TransversalSteps.completarDatosInformante(args[19], args[20], args[21]);
		ViajesCorporativosSteps.preguntas(args[22], args[23], args[24]);
		// generar siniestro
		TransversalSteps.generarSiniestro(args[25]);
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataViajesCorporativo")
	public void ATMOQA252(String args[]) throws InterruptedException {
		getTest().assignCategory("Viajes_Corporativo").assignDevice("Siniestros");
		setStep(getTest().createNode("TIA-SINIESTROS-VC- Generar siniestro de un producto VC con cobertura Muerte Accidental y validar la reserva de la indemnización."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.busquedaRapidaElemento(args[0]);
        TransversalSteps.crearPolizaIntermediadorSiniestro(args[1], args[2], args[3], args[4],args[26], args[5]);
        ViajesCorporativosSteps.seleccionarTipodePlan(args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();;
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		ViajesCorporativosSteps.seleccionarProductoVC(); 
		// completa datos de un siniestro
		TransversalSteps.seleccionFechaSiniestro();
		ViajesCorporativosSteps.completarEvento(args[14], args[15], args[16]);
		TransversalSteps.insertarDescripcionHecho(args[17]);
		TransversalSteps.seleccionarLugarSiniestro(args[18]);
		TransversalSteps.completarDatosInformante(args[19], args[20], args[21]);
		ViajesCorporativosSteps.preguntas(args[22], args[23], args[24]);
		// generar siniestro
		TransversalSteps.generarSiniestro(args[25]);
		TransversalSteps.validarMontoIndemnizacionSiniestro();
	}

	@DataProvider(name = "dataViajesCorporativo")
	public Object[][] dataViajesCorporativo() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("siniestros" + File.separator + "CSVDataViajesCorporativos.csv", 1,
				27);
		return data;
	}
}
