package test.java.tia.siniestros;

import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MascotasSaludSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

public class MascotasSaludTests extends BaseTest {

	@Test(priority = 1, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA44(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Siniestros");
		setStep(getTest().createNode("Generación de un siniestro sin una reserva previa."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[2], args[3]);
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza 
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		MascotasSaludSteps.seleccionProducto();
		TransversalSteps.seleccionFechaSiniestro();
		TransversalSteps.insertarDescripcionHecho(args[10]);
		TransversalSteps.validarIngresoEvento(args[11]);
		TransversalSteps.validarIngresoCausa(args[12]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLiquidador(args[13]);
		TransversalSteps.seleccionarLugarSiniestro(args[15]);
		MascotasSaludSteps.ingresarDescripcionLugar(args[16]);
		TransversalSteps.completarDatosInformante(args[17], args[18], args[20]);
		MascotasSaludSteps.ingresarContactoInformante(args[19]);
		MascotasSaludSteps.ingresaMontoReclamado(args[21]);
		MascotasSaludSteps.ingresarPregAsigLiquidador(args[22]);
		MascotasSaludSteps.ingresarRutInformante(args[28]);
		MascotasSaludSteps.generarSiniestro();
	}

	@Test(priority = 2, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA46(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Siniestros");
		setStep(getTest().createNode("Generación de un siniestro sin una reserva previa, ingresando una fecha invalida."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[2], args[3]);
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		MascotasSaludSteps.seleccionProducto();
		MascotasSaludSteps.seleccionFechaInvalidaSiniestro(args[23]);
		MascotasSaludSteps.validaFechaInvalida();
	}

	@Test(priority = 3, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA45(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Siniestros");
		setStep(getTest().createNode("Generación de un siniestro con una reserva de un item."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[2], args[3]);
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		MascotasSaludSteps.seleccionProducto();
		TransversalSteps.seleccionFechaSiniestro();
		TransversalSteps.insertarDescripcionHecho(args[10]);
		MascotasSaludSteps.ingresarNumeroLineaPoliza();
		TransversalSteps.validarIngresoEvento(args[11]);
		TransversalSteps.validarIngresoCausa(args[12]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		TransversalSteps.seleccionarLiquidador(args[13]);
		TransversalSteps.seleccionarLugarSiniestro(args[15]);
		MascotasSaludSteps.ingresarDescripcionLugar(args[16]);
		TransversalSteps.completarDatosInformante(args[17], args[18], args[20]);
		MascotasSaludSteps.ingresarContactoInformante(args[19]);
		MascotasSaludSteps.ingresaMontoReclamado(args[21]);
		MascotasSaludSteps.ingresarPregAsigLiquidador(args[22]);
		MascotasSaludSteps.ingresarRutInformante(args[28]);
		MascotasSaludSteps.crearReserva();
		MascotasSaludSteps.validarPaginaReserva();
		MascotasSaludSteps.ingresarNuevoItem();
		TransversalSteps.completarCampoItem(args[24]);
		TransversalSteps.completarCampoSubItem(args[25]);
		MascotasSaludSteps.ingresaReserva(args[26]);
		MascotasSaludSteps.guardarItem();
		MascotasSaludSteps.generarSiniestro();

	}

	@Test(priority = 4, enabled = true, dataProvider = "dataMascotasSalud")
	public void ATMOQA47(String args[]) throws InterruptedException {

		getTest().assignCategory("Mascotas_Salud").assignDevice("Siniestros");
		setStep(getTest().createNode("Generación de un siniestro sin una reserva previa con un gestor distinto al User actual."));

		TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
		TransversalSteps.buscarEntidad(args[0], args[1]);
		// Crear nueva póliza
		TransversalSteps.crearPolizaDiaAnterior(args[2], args[3]);
		MascotasSaludSteps.datosMascotas(args[4], args[5], args[6], args[7]);
		MascotasSaludSteps.datosMascotasMestizo();
		MascotasSaludSteps.datosPlan(args[8]);
		TransversalSteps.calcularPrimaTotal();
		// completar poliza
		TransversalSteps.completarPoliza(args[9]);
		TransversalSteps.aceptarDocumentosPoliza();
		// completar datos siniestro
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.ingresarSiniestroCreateClaim();
		MascotasSaludSteps.seleccionProducto();
		TransversalSteps.seleccionFechaSiniestro();
		TransversalSteps.insertarDescripcionHecho(args[10]);
		MascotasSaludSteps.ingresarNumeroLineaPoliza();
		TransversalSteps.validarIngresoEvento(args[11]);
		TransversalSteps.validarIngresoCausa(args[12]);
		TransversalSteps.validarIngresoCoberturaSubCobertura();
		MascotasSaludSteps.ingresaGestorSiniestro(args[27]);
		TransversalSteps.seleccionarLiquidador(args[13]);
		TransversalSteps.seleccionarLugarSiniestro(args[15]);
		MascotasSaludSteps.ingresarDescripcionLugar(args[16]);
		TransversalSteps.completarDatosInformante(args[17], args[18], args[20]);   
		MascotasSaludSteps.ingresarContactoInformante(args[19]);
		MascotasSaludSteps.ingresaMontoReclamado(args[21]);
		MascotasSaludSteps.ingresarPregAsigLiquidador(args[22]);
		MascotasSaludSteps.ingresarRutInformante(args[28]);
		MascotasSaludSteps.generarSiniestro();

	}

	@DataProvider(name = "dataMascotasSalud")
	public Object[][] dataMascotasSalud() throws CsvValidationException, InterruptedException, IOException {

		Object[][] data = OpenCSV
				.getCSVParametersDescription("siniestros" + File.separator + "CSVDataMascotasSalud.csv", 1, 29);
		return data;
	}
}
