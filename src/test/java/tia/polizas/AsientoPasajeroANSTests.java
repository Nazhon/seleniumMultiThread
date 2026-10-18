package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.AsientoPasajeroANSSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class AsientoPasajeroANSTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA334(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"TIA-EMISIÓN-AHAPJ- Emisión de Póliza en TIA para Producto Asiento pasajero AHAPJ con la información del Tipo de vehiculo Auto, "
						+ "Plan A-B, minimo 5 asientos con vigencia de 12 meses y una comisión automática del 25%"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[2], args[3], args[4], args[5], args[6]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[7]);
		TransversalSteps.seleccionarCanalPago(args[8]);
		TransversalSteps.seleccionarCodigoMoneda(args[9]);
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.seleccionarAgentePoliza(args[10], args[11]);
		TransversalSteps.insertarParticipacionAgente(args[12]);
		TransversalSteps.verificarPorcentajeComision();
		TransversalSteps.guardarCambios();
		TransversalSteps.verificarPorcentajeComision();
		TransversalSteps.verificarDuracionAnioPoliza();
		TransversalSteps.completarPolizaConIdLineaPoliza(args[13]);
		AsientoPasajeroANSSteps.contrataPoliza(args[14], args[15], args[16], args[17], args[18], args[19], args[20],
				args[21], args[22], args[23]);
		AsientoPasajeroANSSteps.riesgoMuerteAccidental(args[24]);
		AsientoPasajeroANSSteps.riesgoIncapacidadPermanente();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA335(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Emision_Poliza");
		setStep(getTest().createNode(
				"Emisión de Póliza en TIA para Producto Asiento pasajero ANS con la información del Tipo de vehiculo Bus, Plan Full, "
						+ "maximo de 2500 asientos con vigencia de 6 meses y una comisión maxima del 25%"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPolizaSinModificarFechas(args[2], args[3], args[4], args[5], args[6]);
		TransversalSteps.seleccionarCambioFechaMeses(args[26]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[7]);
		TransversalSteps.seleccionarCanalPago(args[8]);
		TransversalSteps.seleccionarCodigoMoneda(args[9]);
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.seleccionarAgentePoliza(args[10], args[11]);
		TransversalSteps.insertarParticipacionAgente(args[12]);
		TransversalSteps.verificarPorcentajeComision();
		TransversalSteps.guardarCambios();
		TransversalSteps.verificarPorcentajeComision();
		TransversalSteps.completarPolizaConIdLineaPoliza(args[27]);
		AsientoPasajeroANSSteps.contrataPoliza(args[28], args[29], args[30], args[31], args[18], args[32], args[33],
				args[34], args[35], args[36]);
		AsientoPasajeroANSSteps.riesgoMuerteAccidental(args[37]);
		AsientoPasajeroANSSteps.riesgoIncapacidadPermanente();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
	}

	@DataProvider(name = "dataAsientoPasajeroANS")
	public Object[][] dataAsientoPasajeroANS() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("emision" + File.separator + "CSVDataAsientoPasajeroANS.csv", 1, 38);
		return data;
	}

}
