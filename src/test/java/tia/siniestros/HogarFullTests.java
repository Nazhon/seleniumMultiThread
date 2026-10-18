package test.java.tia.siniestros;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.HogarFullSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

public class HogarFullTests extends BaseTest {
	// Generación de un siniestro para una póliza Hogar Full
	@Test(priority = 1, enabled = true, dataProvider = "dataHogarFull")
	public void ATMOQA79(String args[]) throws InterruptedException {
		getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
		setStep(getTest().createNode("Generación de un siniestro en TIA para una póliza vigente Hogar Full"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.busquedaRapidaElemento(args[60]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]); 
		HogarFullSteps.seleccionarTipoContratante(args[29]);
		if(environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[61]);
		}else {
			HogarFullSteps.identificarPropiedad(args[30]);
		}
		HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
		HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[38]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		HogarFullSteps.seleccionarProducto(); // pasarlo a transversal
		// completa datos de un siniestro Hogar Full
		TransversalSteps.seleccionFechaSiniestro();
		HogarFullSteps.completarEvento(args[6], args[7], args[8]);
		HogarFullSteps.insertarDescripcionHechoHF(args[4]); // usar el del transversal
		HogarFullSteps.seleccionarLugarSiniestroHF(args[9]); // usar el del transversal
		HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]); //usar el del transversal
		HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
		// generar siniestro
		HogarFullSteps.generarSiniestroHF(args[6]); // usar el de transversal
	}

	// Generación de un siniestro para una póliza Hogar Full definiendo un
	// gestor de siniestro diferente
	@Test(priority = 2, enabled = true, dataProvider = "dataHogarFull")
	public void ATMOQA80(String args[]) throws InterruptedException {
		getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Generación de un siniestro en TIA para una póliza vigente Hogar Full con un gestor de siniestro distinto al usuario autenticado"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.busquedaRapidaElemento(args[60]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
		HogarFullSteps.seleccionarTipoContratante(args[29]);
		if(environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[61]);
		}else {
			HogarFullSteps.identificarPropiedad(args[30]);
		}
		HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
		HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[38]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		// selecciona siniestro de tipo Hogar Full
		HogarFullSteps.seleccionarProducto();
		// completa datos de un siniestro Hogar Full
		TransversalSteps.seleccionFechaSiniestro();
		HogarFullSteps.completarEvento(args[6], args[7], args[8]);
		HogarFullSteps.insertarDescripcionHechoHF(args[4]);
		TransversalSteps.seleccionarGestor(args[17]);
		HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
		HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
		HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
		// generar siniestro
		HogarFullSteps.generarSiniestroHF(args[6]);
	}

	// Generación de un siniestro para una póliza Hogar Full agregando una reserva
	@Test(priority = 3, enabled = true, dataProvider = "dataHogarFull")
	public void ATMOQA81(String args[]) throws InterruptedException {
		getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Generación de un siniestro en TIA para una póliza vigente Hogar Full agregando una reserva"));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.busquedaRapidaElemento(args[60]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
		HogarFullSteps.seleccionarTipoContratante(args[29]);
		if(environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[61]);
		}else {
			HogarFullSteps.identificarPropiedad(args[30]);
		}
		HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
		HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
		HogarFullSteps.calcularPrimaTotalHF();
		// completar poliza
		TransversalSteps.completarPoliza(args[38]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		// selecciona siniestro de tipo Hogar Full
		HogarFullSteps.seleccionarProducto();
		// completa datos de un siniestro Hogar Full
		TransversalSteps.seleccionFechaSiniestro();
//		HogarFullSteps.insertarFechaSiniestroHF(args[2]);
//		HogarFullSteps.insertarFechaDenunciaHF(args[3]);
//		HogarFullSteps.buscarPolizaHF(args[5]);
		HogarFullSteps.completarEvento(args[6], args[7], args[8]);
		HogarFullSteps.insertarDescripcionHechoHF(args[4]);
		HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
		HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
		HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
		// generar reserva
		HogarFullSteps.crearReservaHF(args[20], args[21], args[22], args[23]);
		HogarFullSteps.guardarReservaHF();
		// generar siniestro
		HogarFullSteps.generarSiniestroHF(args[6]);
	}

	// Generación de un siniestro en TIA para una póliza vigente Hogar Full
	// asignando un Liquidador
	@Test(priority = 4, enabled = true, dataProvider = "dataHogarFull")
	public void ATMOQA82(String args[]) throws InterruptedException {
		getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Generación de un siniestro en TIA para una póliza vigente Hogar Full asignando un Liquidador"));
		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.busquedaRapidaElemento(args[60]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
		HogarFullSteps.seleccionarTipoContratante(args[29]);
		if(environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[61]);
		}else {
			HogarFullSteps.identificarPropiedad(args[30]);
		}
		HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
		HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
		HogarFullSteps.calcularPrimaTotalHF();
		// completar poliza
		TransversalSteps.completarPoliza(args[38]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		// selecciona siniestro de tipo Hogar Full
		HogarFullSteps.seleccionarProducto();
		// completa datos de un siniestro Hogar Full
		TransversalSteps.seleccionFechaSiniestro();
		HogarFullSteps.completarEvento(args[6], args[7], args[8]);
		HogarFullSteps.insertarDescripcionHechoHF(args[4]);
		HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
		HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
		HogarFullSteps.seleccionarLiquidadorHF(args[24]);
		HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
		// validar reserva automática con Liquidador asignado
		HogarFullSteps.validarReservaAutomaticaHF(args[24], args[25], args[39]);
		HogarFullSteps.guardarReservaHF();
		// generar siniestro
		HogarFullSteps.generarSiniestroHF(args[6]);
	}

	// Liquidación de un siniestro en TIA para una póliza vigente Hogar Full
	@Test(priority = 5, enabled = true, dataProvider = "dataHogarFull")
	public void ATMOQA84(String args[]) throws InterruptedException {
		getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
		setStep(getTest().createNode(
				"Liquidación de ítem de un siniestro asociado a una póliza vigente del producto Hogar Full."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.busquedaRapidaElemento(args[60]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
		HogarFullSteps.seleccionarTipoContratante(args[29]);
		if(environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[61]);
		}else {
			HogarFullSteps.identificarPropiedad(args[30]);
		}
		HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
		HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
		HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
		HogarFullSteps.calcularPrimaTotalHF();
		// completar poliza
		TransversalSteps.completarPoliza(args[38]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		// selecciona siniestro de tipo Hogar Full
		HogarFullSteps.seleccionarProducto();
		// completa datos de un siniestro Hogar Full
		TransversalSteps.seleccionFechaSiniestro();
		HogarFullSteps.completarEvento(args[6], args[7], args[8]);
		HogarFullSteps.insertarDescripcionHechoHF(args[4]);
		HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
		HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
		HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
		// generar reserva
		HogarFullSteps.crearReservaHF(args[20], args[21], args[22], args[23]);
		HogarFullSteps.guardarReservaHF();
		// generar siniestro
		HogarFullSteps.generarSiniestroHF(args[6]);
		// liquidar reserva
		HogarFullSteps.liquidarReservaHF(args[26], "0001");
	}
	
	
		@Test(priority = 6, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA162(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode(
					"Generar siniestro de un producto HF con cobertura Incendio Edificio se genere el item de Deducible "
					+ "con el monto correctamente (deducible indicado en la póliza: 10% de Pérdida/Min UF 10)."));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			if(environment.equals("UAT")) {
				HogarFullSteps.identificarPropiedad(args[61]);
			}else {
				HogarFullSteps.identificarPropiedad(args[30]);
			}
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			TransversalSteps.seleccionFechaSiniestro();
//			HogarFullSteps.insertarFechaSiniestroHF(args[2]);
//			HogarFullSteps.insertarFechaDenunciaHF(args[3]);
//			HogarFullSteps.buscarPolizaHF(args[5]);
			HogarFullSteps.completarEvento(args[6], args[7], args[8]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
			// generar reserva
			//HogarFullSteps.crearReservaHF(args[20], args[21], args[22], args[23]);
			//HogarFullSteps.guardarReservaHF();
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[6]);
			// Validar que se muestre el deducible automatico
			TransversalSteps.validarMontoDeducibleSiniestro();
			// Validar que si el 10% es menor a 10UF quede en 10UF.
			TransversalSteps.validarMontoMenorUF(args[41], args[42]);
			//Verificar la modificación del monto de reserva y verificar el 10%
			TransversalSteps.validarModificacionReserva(args[40]);
			//Verificar el porcentaje
			TransversalSteps.validarporcentajeUF(args[41]);
			
			
			
		}
		
		@Test(priority = 7, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA163(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode("Generar siniestro del producto HF con cobertura Sismo (deducible indicado en la póliza: 1% monto asegurado/Min UF 30)"));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			if(environment.equals("UAT")) {
				HogarFullSteps.identificarPropiedad(args[61]);
			}else {
				HogarFullSteps.identificarPropiedad(args[30]);
			}
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[49], args[50]);
			// Seleccionar cobertura Sismo
			HogarFullSteps.agregarCoberturaSismo();
			HogarFullSteps.agregarCoberturaSismoEdificioContenido();
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			HogarFullSteps.clicGenerarSiniestroHF();
			TransversalSteps.seleccionFechaSiniestro();
			HogarFullSteps.completarEvento(args[46], args[47], args[48]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[46]);
			// Validar que se muestre el deducible automatico
			TransversalSteps.validarMontoDeducibleSiniestro();
			// Validar que si el 10% es menor a 10UF quede en 10UF.
			TransversalSteps.validarMontoMenorUF(args[44], args[45]);
			//Verificar la modificación del monto de reserva y verificar el 10%
			TransversalSteps.validarModificacionReserva(args[43]);
			//Verificar el porcentaje
			TransversalSteps.validarporcentajeUF(args[44]);	
		}
		@Test(priority = 8, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA166(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode(
					"Generar siniestro del producto HF y modificar el item de deducible."));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			if(environment.equals("UAT")) {
				HogarFullSteps.identificarPropiedad(args[61]);
			}else {
				HogarFullSteps.identificarPropiedad(args[30]);
			}
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			TransversalSteps.seleccionFechaSiniestro();
			HogarFullSteps.completarEvento(args[6], args[7], args[8]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
			HogarFullSteps.generarSiniestroHF(args[6]);
			// Validar que se muestre el deducible automatico
			TransversalSteps.validarMontoDeducibleSiniestro();
			//Verificar la modificación del monto deducible
			TransversalSteps.validarModificacionDeducible(args[51]);			
		}
		@Test(priority = 9, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA165(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode(
					"HF Generar siniestro de un producto HF con cobertura Reembolso Gastos Médicos (AP) "
					+ "se genere el item de Deducible con el monto correctamente (1 UF por evento)"));


			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			if(environment.equals("UAT")) {
				HogarFullSteps.identificarPropiedad(args[61]);
			}else {
				HogarFullSteps.identificarPropiedad(args[30]);
			}
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
			HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			TransversalSteps.seleccionFechaSiniestro();
//			HogarFullSteps.insertarFechaSiniestroHF(args[2]);
//			HogarFullSteps.insertarFechaDenunciaHF(args[3]);
//			HogarFullSteps.buscarPolizaHF(args[5]);
			HogarFullSteps.completarEvento(args[52], args[53], args[54]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			// generar reserva
			//HogarFullSteps.crearReservaHF(args[20], args[21], args[22], args[23]);
			//HogarFullSteps.guardarReservaHF();
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[55]);
			// Validar que se muestre el deducible automatico
			TransversalSteps.validarMontoDeducibleSiniestro();
		}
		
		@Test(priority = 10, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA164(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode("TIA-SINIESTROS-HF Generar siniestro de un producto HF con cobertura rotura cristales "
					+ "se genere el item de Deducible con el monto correctamente (2 UF)"));
			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			if(environment.equals("UAT")) {
				HogarFullSteps.identificarPropiedad(args[61]);
			}else {
				HogarFullSteps.identificarPropiedad(args[30]);
			}
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[49], args[50]);
			// Seleccionar cobertura Sismo
			HogarFullSteps.agregarCoberturaSismo();
			HogarFullSteps.agregarCoberturaSismoEdificioContenido();
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			HogarFullSteps.clicGenerarSiniestroHF();
			TransversalSteps.seleccionFechaSiniestro();
			HogarFullSteps.completarEvento(args[57], args[58], args[59]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			HogarFullSteps.responderPreguntasCristales(args[13],args[56], args[14], args[15], args[16]);
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[57]);
			// Validar que se muestre el deducible automatico
			TransversalSteps.validarMontoDeducibleSiniestro();
		}
		
		// Generación de un siniestro para una póliza Hogar Full - UAT
		@Test(priority = 11, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA225(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode("Generación de un siniestro en TIA para una póliza vigente Hogar Full"));
			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]); 
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			HogarFullSteps.identificarPropiedad(args[61]);
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
			TransversalSteps.calcularPrimaTotal();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			HogarFullSteps.seleccionarProducto(); // pasarlo a transversal
			// completa datos de un siniestro Hogar Full
			TransversalSteps.seleccionFechaSiniestro();
			HogarFullSteps.completarEvento(args[6], args[7], args[8]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]); // usar el del transversal
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]); // usar el del transversal
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]); //usar el del transversal
			HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[6]); // usar el de transversal
		}
		
		// Generación de un siniestro para una póliza Hogar Full agregando una reserva
		@Test(priority = 12, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA226(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode(
					"Generación de un siniestro en TIA para una póliza vigente Hogar Full agregando una reserva"));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			HogarFullSteps.identificarPropiedad(args[61]);
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			TransversalSteps.seleccionFechaSiniestro();
//			HogarFullSteps.insertarFechaSiniestroHF(args[2]);
//			HogarFullSteps.insertarFechaDenunciaHF(args[3]);
//			HogarFullSteps.buscarPolizaHF(args[5]);
			HogarFullSteps.completarEvento(args[6], args[7], args[8]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
			// generar reserva
			HogarFullSteps.crearReservaHF(args[20], args[21], args[22], args[23]);
			HogarFullSteps.guardarReservaHF();
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[6]); 
		}
		
		// Generación de un siniestro en TIA para una póliza vigente Hogar Full
		// asignando un Liquidador
		@Test(priority = 13, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA227(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode(
					"Generación de un siniestro en TIA para una póliza vigente Hogar Full asignando un Liquidador"));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			HogarFullSteps.identificarPropiedad(args[61]);
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			TransversalSteps.seleccionFechaSiniestro();
			HogarFullSteps.completarEvento(args[6], args[7], args[8]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			HogarFullSteps.seleccionarLiquidadorHF(args[24]);
			HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
			// validar reserva automática con Liquidador asignado
			HogarFullSteps.validarReservaAutomaticaHF(args[24], args[25], args[39]);
			HogarFullSteps.guardarReservaHF();
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[6]);
		}
		
		// Liquidación de un siniestro en TIA para una póliza vigente Hogar Full
		@Test(priority = 14, enabled = true, dataProvider = "dataHogarFull")// False - queda pendiente de pago UAT
		public void ATMOQA228(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode(
					"Liquidación de ítem de un siniestro asociado a una póliza vigente del producto Hogar Full."));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			HogarFullSteps.identificarPropiedad(args[61]);
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[36], args[37]);
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			TransversalSteps.seleccionFechaSiniestro();
			HogarFullSteps.completarEvento(args[6], args[7], args[8]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			HogarFullSteps.responderPreguntas(args[13], args[14], args[15], args[16]);
			// generar reserva
			HogarFullSteps.crearReservaHF(args[20], args[21], args[22], args[23]);
			HogarFullSteps.guardarReservaHF();
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[6]);
			// liquidar reserva
			HogarFullSteps.liquidarReservaHF(args[26], "0001");
		}
		
		@Test(priority = 15, enabled = true, dataProvider = "dataHogarFull")
		public void ATMOQA234(String args[]) throws InterruptedException {
			getTest().assignCategory("Hogar_Full").assignDevice("Siniestros");
			setStep(getTest().createNode("Generar siniestro del producto HF con cobertura Sismo (deducible indicado en la póliza: 1% monto asegurado/Min UF 30)"));

			TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
			TransversalSteps.busquedaRapidaElemento(args[60]);
			// Crear nueva póliza
			TransversalSteps.crearPolizaDiaAnterior(args[27], args[28]);
			HogarFullSteps.seleccionarTipoContratante(args[29]);
			HogarFullSteps.identificarPropiedad(args[61]);
			HogarFullSteps.seleccionarPropiedadCasaCondominio(args[31]);
			HogarFullSteps.completarInformacionPropiedad(args[32], args[33], args[34], args[35]);
			HogarFullSteps.seleccionarCoberturaIncendio(args[49], args[50]);
			// Seleccionar cobertura Sismo
			HogarFullSteps.agregarCoberturaSismo();
			HogarFullSteps.agregarCoberturaSismoEdificioContenido();
			HogarFullSteps.calcularPrimaTotalHF();
			// completar poliza
			TransversalSteps.completarPoliza(args[38]);
			TransversalSteps.aceptarDocumentosPoliza();
			// completar datos siniestro
			TransversalSteps.ingresarPolizaBusquedaRapida();
			TransversalSteps.ingresarSiniestroCreateClaim();
			// selecciona siniestro de tipo Hogar Full
			HogarFullSteps.seleccionarProducto();
			// completa datos de un siniestro Hogar Full
			HogarFullSteps.clicGenerarSiniestroHF();
			TransversalSteps.seleccionFechaSiniestro();
			HogarFullSteps.completarEvento(args[46], args[47], args[48]);
			HogarFullSteps.insertarDescripcionHechoHF(args[4]);
			HogarFullSteps.seleccionarLugarSiniestroHF(args[9]);
			HogarFullSteps.completarDatosInformanteHF(args[10], args[11], args[12]);
			// generar siniestro
			HogarFullSteps.generarSiniestroHF(args[46]);
			// Validar que se muestre el deducible automatico
			TransversalSteps.validarMontoDeducibleSiniestro();
			// Validar que si el 10% es menor a 10UF quede en 10UF.
			TransversalSteps.validarMontoMenorUF(args[44], args[45]);
			//Verificar la modificación del monto de reserva y verificar el 10%
			TransversalSteps.validarModificacionReserva(args[43]);
			//Verificar el porcentaje
			TransversalSteps.validarporcentajeUF(args[44]);	
		}
		
	@DataProvider(name = "dataHogarFull")
	public Object[][] dataHogarFull() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV.getCSVParametersDescription("siniestros" + File.separator + "CSVDataHogarFull.csv", 1,
				62);
		return data;
	}
}
