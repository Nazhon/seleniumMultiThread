package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageEvents.tia.transversal.TransversalEvents;
import main.java.pageSteps.tia.productos.MuerteAccidentalSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MuerteAccidentalTests extends BaseTest {
	
	// Generación de un siniestro para una póliza Muerte Accidental
		@Test(priority = 1, enabled = true, dataProvider = "dataMuerteAccidental")
		public void ATMOQA20(String args[]) throws InterruptedException {

			getTest().assignCategory("Muerte_Accidental").assignDevice("Siniestros");
			setStep(getTest().createNode("Generación de siniestro asociado a una póliza vigente del producto Muerte Accidental."));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			TransversalSteps.buscarEntidad(args[0], args[1]);
			MuerteAccidentalSteps.crearPolizaAP(args[20], args[21]);
			MuerteAccidentalSteps.datosPlanes();
			MuerteAccidentalSteps.datosRiesgos();
			MuerteAccidentalSteps.datosBeneficiarios(args[22], args[23], args[24]);
			TransversalSteps.calcularPrimaTotal();
			TransversalSteps.completarPoliza(args[25]);
			TransversalSteps.aceptarDocumentosPoliza();
			
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaimAlt();
			// selecciona siniestro de tipo Muerte Accidental
			MuerteAccidentalSteps.seleccionarProductoSiniestrarSaludAP();
			// completa datos de un siniestro Muerte Accidental
			TransversalSteps.insertarFechaSiniestroSinParametro();
//			TransversalSteps.insertarFechaSiniestro(args[2]);
//			TransversalSteps.insertarFechaDenuncia(args[3]);
			TransversalSteps.buscarPolizaAlt();
			MuerteAccidentalSteps.completarEvento(args[6], args[7]);
			TransversalSteps.insertarDescripcionHecho(args[4]);
			TransversalSteps.seleccionarLugarSiniestro(args[8]);
			TransversalSteps.completarDatosInformante( args[9], args[10], args[11]);
			MuerteAccidentalSteps.responderPreguntasAP(args[12], args[13], args[14], args[15]);
			// generar siniestro
			TransversalSteps.generarSiniestro(args[6]);
			// verificar creacion

		}
		
		@Test(priority = 2, enabled = true, dataProvider = "dataMuerteAccidental")
		public void ATMOQA35(String args[]) throws InterruptedException {

			getTest().assignCategory("Muerte_Accidental").assignDevice("Siniestros");
			setStep(getTest().createNode("Generación de siniestro por muerte accidental con creacion de reserva."));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			
			TransversalSteps.buscarEntidad(args[0], args[1]);
			MuerteAccidentalSteps.crearPolizaAP(args[20], args[21]);
			MuerteAccidentalSteps.datosPlanes();
			MuerteAccidentalSteps.datosRiesgos();
			MuerteAccidentalSteps.datosBeneficiarios(args[22], args[23], args[24]);
			TransversalSteps.calcularPrimaTotal();
			TransversalSteps.completarPoliza(args[25]);
			TransversalSteps.aceptarDocumentosPoliza();
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaimAlt();
			// selecciona siniestro de tipo AP
			MuerteAccidentalSteps.seleccionarProductoSiniestrarSaludAP();
			// completa datos de un siniestro AP
			TransversalEvents.ingresarFechaSiniestro();
//			TransversalSteps.insertarFechaSiniestro(args[2]);
//			TransversalSteps.insertarFechaDenuncia(args[3]);
			TransversalSteps.buscarPolizaAlt();
			MuerteAccidentalSteps.completarEvento(args[6], args[7]);
			TransversalSteps.insertarDescripcionHecho(args[4]);
			TransversalSteps.seleccionarLugarSiniestro(args[8]);
			TransversalSteps.completarDatosInformante( args[9], args[10], args[11]);
			MuerteAccidentalSteps.responderPreguntasAP(args[12], args[13], args[14], args[15]);
			// Crear reserva
			TransversalSteps.crearReserva();
			TransversalSteps.crearNuevoItem();
			TransversalSteps.completarCamposNuevoItem(args[16], args[17],  args[18], args[19]);
			TransversalSteps.guardarReserva();
			// generar siniestro
			TransversalSteps.generarSiniestro(args[6]);
			// verificar creacion

		}
		
		@Test(priority = 3, enabled = true, dataProvider = "dataMuerteAccidental")
		public void ATMOQA161(String args[]) throws InterruptedException {

			getTest().assignCategory("Muerte_Accidental").assignDevice("Siniestros");
			setStep(getTest().createNode("Generar siniestro de un producto AP no genere el item de Deducible."));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			
			TransversalSteps.buscarEntidad(args[0], args[1]);
			TransversalSteps.crearPolizaDiaAnterior(args[20], args[21]);
			MuerteAccidentalSteps.datosPlanes();
			MuerteAccidentalSteps.datosRiesgos();
			MuerteAccidentalSteps.datosBeneficiarios(args[22], args[23], args[24]);
			TransversalSteps.calcularPrimaTotal();
			TransversalSteps.completarPoliza(args[25]);
			TransversalSteps.aceptarDocumentosPoliza();
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaimAlt();

			//TransversalSteps.crearSiniestro();
			// selecciona siniestro de tipo AP
			MuerteAccidentalSteps.seleccionarProductoSiniestrarSaludAP();
			// completa datos de un siniestro AP
			TransversalEvents.ingresarFechaSiniestro();
//			TransversalSteps.insertarFechaSiniestro(args[2]);
//			TransversalSteps.insertarFechaDenuncia(args[3]);
			TransversalSteps.buscarPolizaAlt();
			MuerteAccidentalSteps.completarEvento(args[6], args[7]);
			TransversalSteps.insertarDescripcionHecho(args[4]);
			TransversalSteps.seleccionarLugarSiniestro(args[8]);
			TransversalSteps.completarDatosInformante( args[9], args[10], args[11]);
			MuerteAccidentalSteps.responderPreguntasAP(args[12], args[13], args[14], args[15]);
			// Crear reserva
			//TransversalSteps.crearReserva(args[16], args[17],  args[18], args[19]);
			//TransversalSteps.guardarReserva();
			// generar siniestro
			TransversalSteps.generarSiniestro(args[6]);
			// Verificar que no genere Deducible
			TransversalSteps.validarNoDeducibleSiniestro();

		}

		
		
		@DataProvider(name = "dataMuerteAccidental")
		public Object[][] dataMuerteAccidental() throws CsvValidationException, InterruptedException, IOException {

			Object[][] data = OpenCSV
					.getCSVParametersDescription("siniestros" + File.separator + "CSVDataMuerteAccidental.csv", 1, 26);
			return data;
		}

}
