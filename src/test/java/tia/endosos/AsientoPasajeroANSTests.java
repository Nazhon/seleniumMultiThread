package test.java.tia.endosos;

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
	public void ATMOQA341(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode(
				"Endoso de póliza en TIA para producto Asiento pasajero AHAPJ donde se pueda aumentar el capital asegurado de la Póliza."));
		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0],args[1]); 						
		TransversalSteps.crearPolizaSinModificarFechas(args[2], args[3], args[4], args[5], args[6]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[7]);
		TransversalSteps.seleccionarCanalPago(args[8]);
		TransversalSteps.seleccionarCodigoMoneda(args[9]);
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.seleccionarAgentePoliza(args[10], args[11]);
		TransversalSteps.insertarParticipacionAgente(args[12]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[13]);
		AsientoPasajeroANSSteps.contrataPoliza(args[14], args[15], args[16], args[17], args[18], args[19], args[20],
				args[21], args[22], args[23]);
		AsientoPasajeroANSSteps.riesgoMuerteAccidental(args[24]);
		AsientoPasajeroANSSteps.riesgoIncapacidadPermanente();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[26]);
		TransversalSteps.insertarNota(args[27]);
		TransversalSteps.guardarCambios();
		TransversalSteps.ingresarObjeto();
		TransversalSteps.ajustarObjeto();
		AsientoPasajeroANSSteps.nuevoMontoAsegurado(args[28]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		TransversalSteps.completarPoliza(args[29]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.validarEndoso();
	}
	

	@Test(priority = 2, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA339(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode(
				"Endoso de póliza en TIA para producto Asiento pasajero ANS donde se pueda incluir Vehiculos(multilinea) a la Póliza"));
		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0],args[1]); 						
		TransversalSteps.crearPolizaSinModificarFechas(args[2], args[3], args[4], args[5], args[6]);
		TransversalSteps.verificarModuloInformacionPago();
		TransversalSteps.nuevoMetodoPago(args[7]);
		TransversalSteps.seleccionarCanalPago(args[8]);
		TransversalSteps.seleccionarCodigoMoneda(args[9]);
		TransversalSteps.ingresarHoyFechaVencimientoInfoPago();
		TransversalSteps.seleccionarAgentePoliza(args[10], args[11]);
		TransversalSteps.insertarParticipacionAgente(args[12]);
		TransversalSteps.completarPolizaConIdLineaPoliza(args[30]);
		AsientoPasajeroANSSteps.contrataPoliza(args[31], args[32], args[33], args[34], args[18], args[35], args[20],
				args[21], args[22], args[23]);
		AsientoPasajeroANSSteps.riesgoMuerteAccidental(args[24]);
		AsientoPasajeroANSSteps.riesgoIncapacidadPermanente();
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[25]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[26]);
		TransversalSteps.insertarNota(args[27]);
		TransversalSteps.guardarCambios();
		TransversalSteps.ingresarObjeto();
		TransversalSteps.ajustarObjeto();
		AsientoPasajeroANSSteps.nuevaCantidadAsientos(args[43]);
		AsientoPasajeroANSSteps.completarTipoVehiculo(args[42]);
		AsientoPasajeroANSSteps.nuevoMontoAsegurado(args[28]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		TransversalSteps.completarPoliza(args[29]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 3, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA340(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode(
				"Endoso de póliza en TIA para producto Asiento pasajero ANS donde se pueda excluir Vehiculos de la Póliza."));

	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0],args[1]); 				
		TransversalSteps.crearPolizaIntermediador(args[2],args[3],args[4],args[5],args[6],args[7]);	
//		AsientoPasajeroANSSteps.contrataPoliza(args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);	
		TransversalSteps.completarPoliza(args[16]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[17]);
		TransversalSteps.ingresarObjeto();
		TransversalSteps.ajustarObjeto();
		AsientoPasajeroANSSteps.nuevaCantidadAsientos(args[20]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		TransversalSteps.completarPoliza(args[19]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 4, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA342(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode(
				"Endoso de póliza en TIA para producto Asiento pasajero ANS donde se pueda disminuir el capital asegurado de la Póliza"));

		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0],args[1]); 				
		TransversalSteps.crearPolizaIntermediador(args[2],args[3],args[4],args[5],args[6],args[7]);	
//		AsientoPasajeroANSSteps.contrataPoliza(args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);	
		TransversalSteps.completarPoliza(args[16]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[17]);
		TransversalSteps.ingresarObjeto();
		TransversalSteps.ajustarObjeto();
		AsientoPasajeroANSSteps.nuevoMontoAsegurado(args[18]);
		TransversalSteps.calcularPrimaTotalEndosoComplejo();
		TransversalSteps.completarPoliza(args[19]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.validarEndoso();
	}
	
	@Test(priority = 5, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA343(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode(
				"Endoso de póliza en TIA para producto Asiento pasajero ANS donde se pueda modificar el metodo y/o plan de pago de la Póliza"));

		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0],args[1]); 				
		TransversalSteps.crearPolizaIntermediador(args[2],args[3],args[4],args[5],args[6],args[7]);	
//		AsientoPasajeroANSSteps.contrataPoliza(args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);	
		TransversalSteps.completarPoliza(args[16]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[17]);
		TransversalSteps.nuevoPlanCuota(args[22]);
		TransversalSteps.nuevoMetodoPago(args[23]);
		TransversalSteps.completarPoliza(args[24]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.validarEndoso();
		
	}
	
	@Test(priority = 6, enabled = true, dataProvider = "dataAsientoPasajeroANS")
	public void ATMOQA344(String args[]) throws InterruptedException {

		getTest().assignCategory("Pasajero_ANS").assignDevice("Endoso_Poliza");
		setStep(getTest().createNode(
				" Endoso de póliza en TIA para producto Asiento pasajero ANS donde se pueda modificar el deudor/pagador de la Póliza."));
	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor,environment);
		TransversalSteps.buscarEntidad(args[0],args[1]); 				
		TransversalSteps.crearPolizaIntermediador(args[2],args[3],args[4],args[5],args[6],args[7]);	
//		AsientoPasajeroANSSteps.contrataPoliza(args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);	
		TransversalSteps.completarPoliza(args[16]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarTipoEndoso(args[17]);
		TransversalSteps.clickInformacionPago(); // <<< --- 2  
		TransversalSteps.nuevoNrodeCuenta("11830508-6");
		TransversalSteps.nuevoFacturador("11830508-6");
		TransversalSteps.ingresaTransferenciaPremium("No");
		TransversalSteps.completarPoliza(args[24]);
		TransversalSteps.aceptarDocumentosPoliza();
		TransversalSteps.validarEndoso();  
		
	}

	@DataProvider(name = "dataAsientoPasajeroANS")
	public Object[][] dataAsientoPasajeroANS() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVAsientoPasajeroANS.csv", 1, 51);
		return data;
	}

}
