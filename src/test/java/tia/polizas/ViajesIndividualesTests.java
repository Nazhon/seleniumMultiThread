package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageEvents.tia.productos.ViajesIndividualesEvents;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.pageSteps.tia.productos.ViajesIndividualesSteps;
import test.java.BaseTest;

public class ViajesIndividualesTests extends BaseTest {
	
	@Test(priority = 1, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA310(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Intermediado ANS con la información del viaje, "
				+ "información de Pasajeros con Plan Nacional(AC Domestic) Límite máximo de edad : 75 años y duración máxima de viaje: 90 días con comisión maxima 30%"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
	    ViajesIndividualesSteps.seleccionarTipodePlan(args[8], args[9], args[10], args[11], args[12]);
	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	@Test(priority = 2, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA311(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Intermediado ANS con la información del viaje,"
				+ " información de Pasajeros con Plan Conosur(AC10) con comisión maxima 30%"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[2], args[3], args[4], args[5], args[6], args[13]);
	    ViajesIndividualesSteps.seleccionarTipodePlan(args[14], args[15], args[16], args[17], args[18]);
//	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	@Test(priority = 3, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA317(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Intermediado ANS con la información del viaje,"
				+ " información de Pasajeros con Plan Internacional(AC35) con comisión maxima 30%"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[2], args[3], args[4], args[5], args[6], args[19]);
	    ViajesIndividualesSteps.seleccionarTipodePlan(args[20], args[21], args[10], args[17], args[18]);
//	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	@Test(priority = 4, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA321(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Intermediado ANS con la información del viaje,"
				+ " información de Pasajeros con Plan Premiun(AC60) con comisión maxima 30%"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[2], args[3], args[4], args[5], args[6], args[19]);
	    ViajesIndividualesSteps.seleccionarTipodePlan(args[14], args[22], args[10], args[17], args[18]);
	    ViajesIndividualesEvents.SeleccionarCoberturaCovid();
//	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	@Test(priority = 5, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA322(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Intermediado ANS con la información del viaje, "
				+ "información de Pasajeros con Plan Platinum(AC150) con comisión maxima 30%"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[2], args[3], args[4], args[5], args[6], args[19]);
	    ViajesIndividualesSteps.seleccionarTipodePlan(args[14], args[23], args[10], args[17], args[18]);
	    ViajesIndividualesEvents.SeleccionarCoberturaCovid();
//	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	@Test(priority = 6, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA323(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Corredores independientes con la información del viaje, "
				+ "información de Pasajeros con Plan AC 35 Límite máximo de edad : sin limite de edad y duración máxima de viaje: 365 días."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[24], args[25], args[4], args[5], args[6], args[19]);
		ViajesIndividualesEvents.seleccionarDestino(args[27]);
		ViajesIndividualesEvents.seleccionarTipoDeCobertura2(args[26]);
		ViajesIndividualesEvents.IngresarComision2(args[10]);
		ViajesIndividualesEvents.IngresarPrimaManual(args[18]);
//	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	@Test(priority = 7, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA324(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Corredores independientes con la información del viaje,"
				+ " información de Pasajeros con Plan AC 60 Límite máximo de edad : sin limite de edad y duración máxima de viaje: 365 días."));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[24], args[25], args[4], args[5], args[6], args[19]);
		ViajesIndividualesEvents.seleccionarDestino(args[27]);
		ViajesIndividualesEvents.seleccionarTipoDeCobertura2(args[28]);
		ViajesIndividualesEvents.IngresarComision2(args[10]);
		ViajesIndividualesEvents.IngresarPrimaManual(args[18]);
//	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	@Test(priority = 8, enabled = true, dataProvider = "CSVDataViajesIndividuales")
	public void ATMOQA325(String []args) {

		getTest().assignCategory("Viajes_Individuales").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de Póliza en TIA para Producto Viaje Individual Corredores independientes con la información del viaje, "
				+ "información de Pasajeros con Plan AC 150 Límite máximo de edad : 70 años y duración máxima de viaje: 365 días"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
	    TransversalSteps.busquedaRapidaElemento(args[0]);
	    TransversalSteps.crearPolizaIntermediadorConFechaDeFinalizacion(args[1], args[24], args[25], args[4], args[5], args[6], args[19]);
		ViajesIndividualesEvents.seleccionarDestino(args[27]);
		ViajesIndividualesEvents.seleccionarTipoDeCobertura2(args[29]);
		ViajesIndividualesEvents.IngresarComision2(args[10]);
		ViajesIndividualesEvents.IngresarPrimaManual(args[18]);
///	    TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza("Póliza");
		TransversalSteps.aceptarDocumentosPoliza();
			
		}
	
	
	@DataProvider(name = "CSVDataViajesIndividuales")
	public Object[][] CSVDataViajesIndividuales() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataViajesIndividuales.csv", 1, 30);
		return data;
	}
}
