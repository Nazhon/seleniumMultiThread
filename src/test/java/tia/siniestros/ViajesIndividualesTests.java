package test.java.tia.siniestros;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.ViajesIndividualesSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;

public class ViajesIndividualesTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataViajesIndividuales")
	public void ATMOQA349(String args[]) throws InterruptedException {
		getTest().assignCategory("Viajes_Individuales").assignDevice("Siniestros");
		setStep(getTest().createNode("TIA-SINIESTROS-INDIVIDUAL- Generar siniestro para producto viaje individual en el cual no tenga cobertura para incapacidad."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.busquedaRapidaElemento(args[0]);
        TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[2], args[3], args[4],args[21], args[5],args[22]);
        ViajesIndividualesSteps.seleccionarDestino(args[6], args[7]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[8]);
		TransversalSteps.aceptarDocumentosPoliza();;
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		ViajesIndividualesSteps.seleccionarProductoVC(); 
		// completa datos de un siniestro
		TransversalSteps.seleccionFechaSiniestro1();
		ViajesIndividualesSteps.completarEvento(args[9], args[10], args[11]);
		ViajesIndividualesSteps.validarCobertura();
		TransversalSteps.insertarDescripcionHecho(args[12]);
		TransversalSteps.seleccionarLugarSiniestro(args[13]);
		TransversalSteps.completarDatosInformante(args[14], args[15], args[16]);
		ViajesIndividualesSteps.preguntas(args[17], args[18], args[19]);
		// generar siniestro
		TransversalSteps.generarSiniestro(args[20]);
		TransversalSteps.recuperarNumeroSiniestro();
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.aceptarReceptor();
		TransversalSteps.itemPago(args[23]);
		TransversalSteps.especificacionPago(args[24]);
		TransversalSteps.aprobarPago();
		TransversalSteps.validarItemPagoCerrado();
		
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataViajesIndividuales")
	public void ATMOQA350(String args[]) throws InterruptedException {
		getTest().assignCategory("Viajes_Individuales").assignDevice("Siniestros");
		setStep(getTest().createNode("TIA-SINIESTROS-INDIVIDUAL- Generar siniestro de un producto de viaje individual solo para cobertura Muerte Accidental"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.busquedaRapidaElemento(args[0]);
        TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[2], args[3], args[4],args[21], args[5],args[22]);
        ViajesIndividualesSteps.seleccionarDestino(args[6], args[7]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[8]);
		TransversalSteps.aceptarDocumentosPoliza();;
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		ViajesIndividualesSteps.seleccionarProductoVC(); 
		// completa datos de un siniestro
		TransversalSteps.seleccionFechaSiniestro1();
		ViajesIndividualesSteps.completarEvento(args[9], args[10], args[11]);
		TransversalSteps.insertarDescripcionHecho(args[12]);
		TransversalSteps.seleccionarLugarSiniestro(args[13]);
		TransversalSteps.completarDatosInformante(args[14], args[15], args[16]);
		ViajesIndividualesSteps.preguntas(args[17], args[18], args[19]);
		// generar siniestro
		TransversalSteps.generarSiniestro(args[20]);
		TransversalSteps.recuperarNumeroSiniestro();
		TransversalSteps.seleccionarModuloReservasPagosSiniestro();
		TransversalSteps.seleccionarItemPago();
		TransversalSteps.liquidarItemPago();
		TransversalSteps.aceptarReceptor();
		TransversalSteps.itemPago(args[23]);
		TransversalSteps.especificacionPago(args[24]);
		TransversalSteps.aprobarPago();
		TransversalSteps.validarItemPagoCerrado();
		
	}

	@DataProvider(name = "dataViajesIndividuales")
	public Object[][] dataViajesInviduales() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("siniestros" + File.separator + "CSVDataViajesIndividuales.csv", 1,
				28);
		return data;
	}
}
