package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageEvents.tia.transversal.TransversalEvents;
import main.java.pageSteps.tia.productos.MascotasResponsableSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class MascotasResponsableTests extends BaseTest {

	// Generación de un siniestro para una póliza Mascota Responsable
	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA55(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Siniestros");
		setStep(getTest().createNode("Generación de un siniestro en TIA para una póliza vigente Mascota Responsable"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		//crear poliza MR
		MascotasResponsableSteps.crearPolizaMR(args[21], args[22]);
		//completar datos objeto
	    MascotasResponsableSteps.completarDatosMascotaMestiza(args[23], args[24], args[25], args[26], "Mestizo");
	    MascotasResponsableSteps.seleccionarChip();
	  	TransversalSteps.calcularPrimaTotal();
	  	//completar póliza
		TransversalSteps.completarPoliza(args[27]);
		TransversalSteps.aceptarDocumentosPoliza();
		
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaimAlt();

		// selecciona siniestro de tipo Mascota Responsable
		MascotasResponsableSteps.seleccionarProducto();
		// completa datos de un siniestro Mascota Responsable
		TransversalEvents.ingresarFechaSiniestro();
		TransversalEvents.seleccionarFechaAproximadaTrans();	
		TransversalSteps.buscarPolizaAlt();	
		MascotasResponsableSteps.completarEvento(args[6], args[7]);
		TransversalSteps.insertarDescripcionHecho(args[4]);
		TransversalSteps.seleccionarLugarSiniestro(args[9]);
		TransversalSteps.completarDatosInformante( args[10], args[11], args[12]);
		MascotasResponsableSteps.responderPreguntas(args[13], args[14], args[28], args[29], args[30]);
		// generar siniestro
		TransversalSteps.generarSiniestroAlt();
		// verificar creacion

	}

	// Generación de un siniestro para una póliza Mascota Responsable definiendo un gestor de siniestro diferente
	@Test(priority = 2, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA56(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Generación de un siniestro en TIA para una póliza vigente Mascota Responsable con un gestor de siniestro distinto al usuario autenticado"));
		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		//crear poliza MR
		MascotasResponsableSteps.crearPolizaMR(args[21], args[22]);
		//completar datos objeto
	    MascotasResponsableSteps.completarDatosMascotaMestiza(args[23], args[24], args[25], args[26], "Mestizo");
	    MascotasResponsableSteps.seleccionarChip();
	  	TransversalSteps.calcularPrimaTotal();
	  	//completar póliza
		TransversalSteps.completarPoliza(args[27]);
		TransversalSteps.aceptarDocumentosPoliza();
		
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaimAlt();

		//TransversalSteps.crearSiniestro();
		// selecciona siniestro de tipo Mascota Responsable
		MascotasResponsableSteps.seleccionarProducto();
		// completa datos de un siniestro Mascota Responsable
		TransversalEvents.ingresarFechaSiniestro();
		TransversalEvents.seleccionarFechaAproximadaTrans();
		
		MascotasResponsableSteps.completarEvento(args[6], args[7]);
		TransversalSteps.insertarDescripcionHecho(args[4]);
		
		
		TransversalSteps.seleccionarGestor(args[15]);
		TransversalSteps.seleccionarLugarSiniestro(args[9]);
		TransversalSteps.completarDatosInformante( args[10], args[11], args[12]);
		MascotasResponsableSteps.responderPreguntas(args[13], args[14], args[28], args[29], args[30]);
		// generar siniestro
		TransversalSteps.generarSiniestroAlt();
		// verificar creacion
	}

	// Generación de un siniestro con fecha de siniestro superior a la fecha de denuncia
	@Test(priority = 3, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA58(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Generación de un siniestro en TIA para una póliza vigente de Mascota Responsable introduciendo una fecha de siniestro superior a la fecha de denuncia."));

		  // completa datos de un siniestro Mascota Responsable
		  TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		  TransversalSteps.buscarEntidad(args[0], args[1]);
		  //crear poliza MR
		  MascotasResponsableSteps.crearPolizaMR(args[21], args[22]);
		  //completar datos objeto
		  MascotasResponsableSteps.completarDatosMascotaMestiza(args[23], args[24], args[25], args[26], "Mestizo");
		  MascotasResponsableSteps.seleccionarChip();
			TransversalSteps.calcularPrimaTotal();
			//completar póliza
		  TransversalSteps.completarPoliza(args[27]);
		  TransversalSteps.aceptarDocumentosPoliza();
		  
		  // completar datos siniestro
		  TransversalSteps.ingresarPolizaBusquedaRapida();
		  TransversalSteps.ingresarSiniestroCreateClaimAlt();
  
		  // selecciona siniestro de tipo Mascota Responsable
		  MascotasResponsableSteps.seleccionarProducto();
		  // completa datos de un siniestro Mascota Responsable
		  TransversalSteps.insertarFechaSiniestro(args[16]);
		  TransversalEvents.seleccionarFechaAproximadaTrans();	
		  TransversalSteps.buscarPolizaAlt();	
		  MascotasResponsableSteps.completarEvento(args[6], args[7]);
		  TransversalSteps.insertarDescripcionHecho(args[4]);
		  TransversalSteps.seleccionarLugarSiniestro(args[9]);
		  TransversalSteps.completarDatosInformante( args[10], args[11], args[12]);
		  MascotasResponsableSteps.responderPreguntas(args[13], args[14], args[28], args[29], args[30]);
		  // generar siniestro
		  TransversalSteps.presionarBotonCrearSiniestro();
		// validar mensaje de error de fecha inválida
		TransversalSteps.validarMensajeErrorFechaSiniestroSuperiorFechaDenuncia();
	}

	// Generación de un siniestro con fecha de denuncia inválida

	@Test(priority = 4, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA59(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Generación de un siniestro en TIA para una póliza vigente de Mascota Responsable introduciendo una fecha de denuncia superior a día actual."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		//crear poliza MR
		MascotasResponsableSteps.crearPolizaMR(args[21], args[22]);
		//completar datos objeto
	    MascotasResponsableSteps.completarDatosMascotaMestiza(args[23], args[24], args[25], args[26], "Mestizo");
	    MascotasResponsableSteps.seleccionarChip();
	  	TransversalSteps.calcularPrimaTotal();
	  	//completar póliza
		TransversalSteps.completarPoliza(args[27]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaimAlt();
		// selecciona siniestro de tipo Mascota Responsable
		MascotasResponsableSteps.seleccionarProducto();
		// completa datos de un siniestro Mascota Responsable
		TransversalEvents.ingresarFechaSiniestro();
		TransversalEvents.seleccionarFechaAproximadaTrans();
		MascotasResponsableSteps.completarEvento(args[6], args[7]);
		TransversalSteps.insertarDescripcionHecho(args[4]);
		TransversalSteps.insertarFechaDenuncia(args[3]);

		TransversalSteps.seleccionarLugarSiniestro(args[9]);
		TransversalSteps.completarDatosInformante( args[10], args[11], args[12]);
		MascotasResponsableSteps.responderPreguntas(args[13], args[14], args[28], args[29], args[30]);
		// validar mensaje de error de fecha inválida
		TransversalSteps.generarSiniestroFallido();
		TransversalSteps.validarMensajeErrorFechaDenunciaSuperiorDiaActual();
		// verificar creacion
	}

	// Generación de un siniestro para una póliza Mascota Responsable agregando una reserva
	@Test(priority = 5, enabled = true, dataProvider = "dataMascotasResponsable")
	public void ATMOQA57(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Responsable").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Generación de un siniestro en TIA para una póliza vigente Mascota Responsable agregando una reserva"));
		
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		//crear poliza MR
		MascotasResponsableSteps.crearPolizaMR(args[21], args[22]);
		//completar datos objeto
	    MascotasResponsableSteps.completarDatosMascotaMestiza(args[23], args[24], args[25], args[26], "Mestizo");
	    MascotasResponsableSteps.seleccionarChip();
	  	TransversalSteps.calcularPrimaTotal();
	  	//completar póliza
		TransversalSteps.completarPoliza(args[27]);
		TransversalSteps.aceptarDocumentosPoliza();
		
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaimAlt();

		// selecciona siniestro de tipo Mascota Responsable
		MascotasResponsableSteps.seleccionarProducto();
		// completa datos de un siniestro Mascota Responsable
		TransversalEvents.ingresarFechaSiniestro();
		TransversalEvents.seleccionarFechaAproximadaTrans();
		
		MascotasResponsableSteps.completarEvento(args[6], args[7]);
		TransversalSteps.insertarDescripcionHecho(args[4]);
		TransversalSteps.seleccionarLugarSiniestro(args[9]);
		TransversalSteps.completarDatosInformante( args[10], args[11], args[12]);
		MascotasResponsableSteps.responderPreguntas(args[13], args[14], args[28], args[29], args[30]);
		
		
		// generar reserva
		TransversalSteps.crearReserva();
		TransversalSteps.crearNuevoItem();
		TransversalSteps.completarCamposNuevoItem(args[17], args[18],  args[19], args[20]);
		TransversalSteps.guardarReserva();
		// generar siniestro
		TransversalSteps.generarSiniestroAlt();
		// verificar creacion

	}
	@DataProvider(name = "dataMascotasResponsable")
	public Object[][] dataMascotasResponsable() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("siniestros" + File.separator + "CSVDataMascotasResponsable.csv", 1, 31);
		return data;
	}

}
