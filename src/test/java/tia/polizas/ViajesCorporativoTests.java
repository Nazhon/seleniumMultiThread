package test.java.tia.polizas;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.ViajesCorporativosSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class ViajesCorporativoTests extends BaseTest {
	@Test(priority = 1, enabled = true,  dataProvider = "dataViajesCorporativos")
	public void ATMOQA238(String[] args) {

		getTest().assignCategory("Viajes_Corporativos").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Viajes Corporativo con Entidad Empresa - Plan Internacional - Gold Plus INT - Destino Internacional."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.busquedaRapidaElemento(args[0]);
        TransversalSteps.crearPolizaIntermediador(args[1], args[2], args[3], args[4],args[14], args[5]);
        ViajesCorporativosSteps.seleccionarTipodePlan(args[6], args[7], args[8], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		
	}
	
	@Test(priority = 2, enabled = true,  dataProvider = "dataViajesCorporativos")
	public void ATMOQA235(String[] args) {

		getTest().assignCategory("Viajes_Corporativos").assignDevice("Emision_Poliza");
		setStep(getTest().createNode("Emisión de póliza en TIA para producto Viajes Corporativo con  Entidad Empresa - Plan Internacional - Platinum Plus INT - Destino Internacional"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.busquedaRapidaElemento(args[0]);
        TransversalSteps.crearPolizaIntermediador(args[1], args[2], args[3], args[4],args[14], args[5]);
        ViajesCorporativosSteps.seleccionarTipodePlan(args[11], args[12], args[13], args[9]);
		TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[10]);
		TransversalSteps.aceptarDocumentosPoliza();
		
	}
	
	
	@DataProvider(name = "dataViajesCorporativos")
	public Object[][] dataViajesCorporativo() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("emision" + File.separator + "CSVDataViajesCorporativos.csv", 1, 15);
		return data;
	}
}
