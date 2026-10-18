package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.pageSteps.tia.productos.AsientoPasajeroANSSteps;
import main.java.pageSteps.tia.productos.MascotasSaludSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.properties.PolizaProperties;
import main.java.config.OpenCSV;
import test.java.BaseTest;

public class PagoManualTests extends BaseTest {
	
	
	// Emision de Poliza Mascota Mestizo con chip
	@Test(priority = 1, enabled = true, dataProvider = "dataPagoManual")
	public void ATMOQA301(String args[]) throws InterruptedException {

		getTest().assignCategory("Pago_Manual").assignDevice("Pago_Manual");
		setStep(getTest().createNode(
				"Realización de un pago manual de una póliza correctamente."
				+ "En poliza de producto Mascota Salud"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar pago
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarWorkpad();
		TransversalSteps.crearItem(args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17]);
		TransversalSteps.publicarItem();
		TransversalSteps.pagoMatch();
		TransversalSteps.validarPago(args[13]);
	

	}
	
	
	// Emision de Poliza Mascota Mestizo con chip
		@Test(priority = 1, enabled = true, dataProvider = "dataAsientoPasajeroANS")
		public void ATMOQA3011(String args[]) throws InterruptedException {

			getTest().assignCategory("Pago_Manual").assignDevice("Pago_Manual");
			setStep(getTest().createNode(
					"PagoManual"));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
			TransversalSteps.buscarEntidad(args[0],args[1]); 				
			TransversalSteps.crearPolizaIntermediador(args[2],args[3],args[4],args[5],args[6],args[7]);	
//			AsientoPasajeroANSSteps.contrataPoliza(args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);
			TransversalSteps.calcularPrimaTotal();
			TransversalSteps.completarPoliza(args[16]);
			TransversalSteps.aceptarDocumentosPoliza();
			TransversalSteps.ingresarWorkpad();
			TransversalSteps.crearItem(PolizaProperties.idPoliza,"1","5 Pago entrante",PolizaProperties.idEntidad,"1043908","UF Unidad de Fomento",PolizaProperties.primaSinDescuento,"1110162320 BANCO BCI 10689141 RECAUDACION Abono");
			TransversalSteps.publicarItem();
			TransversalSteps.pagoMatch();
			TransversalSteps.validarPago(PolizaProperties.idEntidad);
			//"14485724"

		}
	@Test(priority = 1, enabled = true, dataProvider = "dataPagoManual")
	public void ATMOQA302(String args[]) throws InterruptedException {

		getTest().assignCategory("Pago_Manual").assignDevice("Pago_Manual");
		setStep(getTest().createNode(
				"Realización de un pago manual excluyendo la fecha del deposito para validar mensaje de error"
				+ "en poliza de producto Mascota Salud"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);		// completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar pago
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarWorkpad();
		TransversalSteps.validarErrorFecha(args[10], args[12], args[13], args[14], args[15], args[16], args[17]);
		TransversalSteps.validarErrorFechaDeposito();
	

	}
	
	
	@DataProvider(name = "dataAsientoPasajeroANS")
	public Object[][] dataAsientoPasajeroANS() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataAsientoPasajeroANS.csv", 1, 20);
		return data;
	}
	
	@DataProvider(name = "dataPagoManual")
	public Object[][] dataPagoManual() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("emision" + File.separator + "CSVDataPagoManual.csv", 1, 18);
		return data;
	}
}
