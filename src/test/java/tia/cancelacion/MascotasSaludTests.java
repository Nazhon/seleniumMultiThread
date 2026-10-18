package test.java.tia.cancelacion;
import java.io.File;
import java.io.IOException;


import test.java.BaseTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.pageSteps.tia.productos.MascotasSaludSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import main.java.config.OpenCSV;

public class MascotasSaludTests extends BaseTest{

	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA49(String args[]) throws InterruptedException {
	
		getTest().assignCategory("Mascotas_Salud").assignDevice("Cancelacion");
		setStep(getTest().createNode("Cancelacion, dentro de los primeros 10 días de vigencia, con motivo Cancelación desistimiento del asegurado - Retracto de póliza"));
	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		//completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		//comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		//cancelar poliza
		MascotasSaludSteps.manejoPoliza();
//		TransversalSteps.ingresarPoliza();
  		MascotasSaludSteps.ajustarPolizaCancelación("402");
  		MascotasSaludSteps.seleccionaCancelacion(args[12]);
//		MascotasSaludSteps.seleccionaCancelacionPoliza(args[10]);
  		MascotasSaludSteps.aceptarYCompletarEndoso();
		MascotasSaludSteps.validarEstadoCancelacionPoliza();
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA50(String args[]) throws InterruptedException {
	
		getTest().assignCategory("Mascotas_Salud").assignDevice("Cancelacion");
		setStep(getTest().createNode("Cancelación,  dentro de los primeros 10 días de vigencia, con motivo Cancelación por desistimiento del asegurado - Retracto, con una cuota pagada."));
	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		//completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		//comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		//pagar una cuota
		TransversalSteps.pagarCuota();
        TransversalSteps.visitarPaginaHome();
        // ingresar a la poliza para cancelarla
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.ingresarPolizaBusquedaRapida();
        MascotasSaludSteps.ajustarPolizaCancelación("402");
        TransversalSteps.seleccionarMotivoCancelacionPoliza(args[12]);
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        // valida el estado de la poliza
        MascotasSaludSteps.validarEstadoCancelacionPoliza();
        //valida reversión del pago
        TransversalSteps.visitarPaginaHome();
        TransversalSteps.validarReversionPagoCuota();
        
	}

	@Test(priority = 3, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA51(String args[]) throws InterruptedException {
	
		getTest().assignCategory("Mascotas_Salud").assignDevice("Cancelacion");
		setStep(getTest().createNode("Cancelación de una póliza de tipo Mascotas Salud, dentro de los primeros 10 días de vigencia, con motivo Cancelación a solicitud del asegurado"));
	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		//completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		//comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		//cancelar poliza
		MascotasSaludSteps.manejoPoliza();
        MascotasSaludSteps.ajustarPolizaCancelación("402");
		MascotasSaludSteps.seleccionaCancelacion(args[13]);
		MascotasSaludSteps.validarErrorCancelacionAseg();
	}
	
	@Test(priority = 4, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA256(String args[]) throws InterruptedException {
	
		getTest().assignCategory("Mascotas_Salud").assignDevice("Cancelacion");
		setStep(getTest().createNode("TIAUAT-CANCELACION-MS- Cancelación dentro de los primeros 10 días de vigencia, con motivo \"Cancelación por desistimiento del asegurado - Retracto\"."));
	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		//completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		//comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		//cancelar poliza
		MascotasSaludSteps.manejoPoliza();
//		TransversalSteps.ingresarPoliza();
  		MascotasSaludSteps.ajustarPolizaCancelación("402");
  		MascotasSaludSteps.seleccionaCancelacion(args[12]);
//		MascotasSaludSteps.seleccionaCancelacionPoliza(args[10]);
  		MascotasSaludSteps.aceptarYCompletarEndoso();
		MascotasSaludSteps.validarEstadoCancelacionPoliza();
	}
	
	@Test(priority = 5, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA257(String args[]) throws InterruptedException {
	
		getTest().assignCategory("Mascotas_Salud").assignDevice("Cancelacion");
		setStep(getTest().createNode("TIAUAT-CANCELACION-MS- Cancelación dentro de los primeros 10 días de vigencia, con motivo \"Cancelación por desistimiento del asegurado - Retracto\", con una cuota pagada y sin siniestro activo."));
	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		//completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		//comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		//pagar una cuota
		TransversalSteps.pagarCuota();
        TransversalSteps.visitarPaginaHome();
        // ingresar a la poliza para cancelarla
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.ingresarPolizaBusquedaRapida();
        MascotasSaludSteps.ajustarPolizaCancelación("402");
        TransversalSteps.seleccionarMotivoCancelacionPoliza(args[12]);
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        // valida el estado de la poliza
        MascotasSaludSteps.validarEstadoCancelacionPoliza();
        //valida reversión del pago
        TransversalSteps.visitarPaginaHome();
        TransversalSteps.validarReversionPagoCuota();
        
	}
	
	@Test(priority = 6, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA269(String args[]) throws InterruptedException {
	
		getTest().assignCategory("Mascotas_Salud").assignDevice("Cancelacion");
		setStep(getTest().createNode("TIAUAT-CANCELACION-MS- Cancelación de una póliza de tipo Mascota Salud, dentro de los primeros 10 días de vigencia, con motivo \"Cancelación a solicitud del asegurado\"."));
	
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
		TransversalSteps.buscarEntidad(args[0], args[1]);
		TransversalSteps.crearPoliza(args[2], args[3]);
		//completar datos objeto
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		//comlpetar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		//cancelar poliza
		MascotasSaludSteps.manejoPoliza();
        MascotasSaludSteps.ajustarPolizaCancelación("402");
		MascotasSaludSteps.seleccionaCancelacion(args[13]);
		MascotasSaludSteps.validarErrorCancelacionAseg();
	}
	
	@DataProvider(name = "dataMascotasSalud")
	public Object[][] dataMascotasSalud() throws CsvValidationException, InterruptedException, IOException {
	
		Object[][] data = OpenCSV.getCSVParametersDescription("cancelacion" + File.separator + "CSVDataMascotasSalud.csv", 1, 14);
		return data;
	}
}
