package test.java.tia.endosos;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.HogarFullSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;

public class HogarFullTests extends BaseTest {
    @Test(priority = 1, enabled = true,  dataProvider = "dataHogarFull")
    public void ATMOQA85(String[] args) { 
        getTest().assignCategory("Hogar_Full").assignDevice("Endoso_Poliza");
        setStep(getTest().createNode("Generación de un endoso complejo agregando coberturas adicionales a la póliza de Hogar Full (Gastos Médicos, Telemedicina, Mascotas), una vez que se ha emitido en la plataforma TIA."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
        
        HogarFullSteps.seleccionarTipoContratante(args[4]);
        if(environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[16]);
		}else {
			HogarFullSteps.identificarPropiedad(args[5]);
		}
        HogarFullSteps.seleccionarPropiedadDepartamentoAlternativo();
        HogarFullSteps.completarInformacionPropiedad(args[6], args[7], args[8], args[9]);
        HogarFullSteps.seleccionarCoberturaIncendio(args[10],args[11]);
        TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[12]);
		TransversalSteps.aceptarDocumentosPoliza();

        // ingresar al objeto para endoso
		TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[14]);
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        
        HogarFullSteps.ampliarVentana();
        HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
        HogarFullSteps.agregarAsistenciaMascotas();
        HogarFullSteps.agregarAsistenciaTelemedicina();
        HogarFullSteps.unirVentana();
        TransversalSteps.calcularPrimaTotalEndosoComplejo();
        TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();

        //validar endoso
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEndoso();
    }

    @Test(priority = 2, enabled = true,  dataProvider = "dataHogarFull")
    public void ATMOQA86(String[] args) {
        getTest().assignCategory("Hogar_Full").assignDevice("Endoso_Poliza");
        setStep(getTest().createNode("Generación de un endoso complejo cancelando coberturas adicionales a la póliza de Hogar Full (Gastos Médicos, Telemedicina, Mascotas), una vez que se ha emitido en la plataforma TIA."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
        HogarFullSteps.seleccionarTipoContratante(args[4]);
        if(environment.equals("UAT")) {
			HogarFullSteps.identificarPropiedad(args[16]);
		}else {
			HogarFullSteps.identificarPropiedad(args[5]);
		}
        HogarFullSteps.seleccionarPropiedadDepartamentoAlternativo();
        HogarFullSteps.completarInformacionPropiedad(args[6], args[7], args[8], args[9]);
        HogarFullSteps.seleccionarCoberturaIncendio(args[10],args[11]);
        HogarFullSteps.ampliarVentana();
        HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
        HogarFullSteps.agregarAsistenciaMascotas();
        HogarFullSteps.agregarAsistenciaTelemedicina();
        HogarFullSteps.unirVentana();
        TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[12]);
		TransversalSteps.aceptarDocumentosPoliza();
        TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[14]);
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        
        HogarFullSteps.ampliarVentana();
        HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
        HogarFullSteps.agregarAsistenciaMascotas();
        HogarFullSteps.agregarAsistenciaTelemedicina();
        HogarFullSteps.unirVentana();
        
        TransversalSteps.calcularPrimaTotalEndosoComplejo();
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        
        //validar endoso
		TransversalSteps.validarEndoso();
    }
    
    @Test(priority = 3, enabled = true,  dataProvider = "dataHogarFull")
    public void ATMOQA218(String[] args) { 
        getTest().assignCategory("Hogar_Full").assignDevice("Endoso_Poliza");
        setStep(getTest().createNode("Generación de un endoso complejo agregando coberturas adicionales a la póliza de Hogar Full (Gastos Médicos, Telemedicina, Mascotas), una vez que se ha emitido en la plataforma TIA."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
        
        HogarFullSteps.seleccionarTipoContratante(args[4]);
        HogarFullSteps.identificarPropiedad(args[16]);
        HogarFullSteps.seleccionarPropiedadDepartamentoAlternativo();
        HogarFullSteps.completarInformacionPropiedad(args[6], args[7], args[8], args[9]);
        HogarFullSteps.seleccionarCoberturaIncendio(args[10],args[11]);
        TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[12]);
		TransversalSteps.aceptarDocumentosPoliza();

        // ingresar al objeto para endoso
		TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[14]);
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        
        HogarFullSteps.ampliarVentana();
        HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
        HogarFullSteps.agregarAsistenciaMascotas();
        HogarFullSteps.agregarAsistenciaTelemedicina();
        HogarFullSteps.unirVentana();
        TransversalSteps.calcularPrimaTotalEndosoComplejo();
        
        TransversalSteps.completarPoliza(args[15]);
		TransversalSteps.aceptarDocumentosPoliza();

        //validar endoso
		TransversalSteps.ingresarPolizaBusquedaRapida();
		TransversalSteps.validarEndoso();
    }
    
    @Test(priority = 4, enabled = true,  dataProvider = "dataHogarFull")
    public void ATMOQA219(String[] args) {
        getTest().assignCategory("Hogar_Full").assignDevice("Endoso_Poliza");
        setStep(getTest().createNode("Generación de un endoso complejo cancelando coberturas adicionales a la póliza de Hogar Full (Gastos Médicos, Telemedicina, Mascotas), una vez que se ha emitido en la plataforma TIA."));

        //creando poliza a endosar
        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.crearPoliza(args[2], args[3]);
        HogarFullSteps.seleccionarTipoContratante(args[4]);
        HogarFullSteps.identificarPropiedad(args[16]);
        HogarFullSteps.seleccionarPropiedadDepartamentoAlternativo();
        HogarFullSteps.completarInformacionPropiedad(args[6], args[7], args[8], args[9]);
        HogarFullSteps.seleccionarCoberturaIncendio(args[10],args[11]);
        HogarFullSteps.ampliarVentana();
        HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
        HogarFullSteps.agregarAsistenciaMascotas();
        HogarFullSteps.agregarAsistenciaTelemedicina();
        HogarFullSteps.unirVentana();
        TransversalSteps.calcularPrimaTotal();
		TransversalSteps.completarPoliza(args[12]);
		TransversalSteps.aceptarDocumentosPoliza();

        // ingresar al objeto para endoso
        TransversalSteps.ingresarPolizaBusquedaRapida();
	    TransversalSteps.ingresarTipoEndoso(args[14]);
        TransversalSteps.ingresarObjeto(); 
        TransversalSteps.ajustarObjeto();
        
        HogarFullSteps.ampliarVentana();
        HogarFullSteps.agregarCoberturaReembolsoGastosMedicos();
        HogarFullSteps.agregarAsistenciaMascotas();
        HogarFullSteps.agregarAsistenciaTelemedicina();
        HogarFullSteps.unirVentana();
        
        TransversalSteps.calcularPrimaTotalEndosoComplejo();
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        
		TransversalSteps.validarEndoso();
    }

    // Generación de un endoso complejo agregando cobertura principal de Contenido a la póliza de Hogar Full, una vez que se ha emitido en la plataforma TIA.

    // Generación de un endoso complejo modificando la dirección (dirección, comuna y región) del riesgo asegurado,que incluye solo cobertura de contenido, una vez que se ha emitido una póliza de Hogar Full en la plataforma TIA.

    // Generación de un endoso complejo modificando la dirección (dirección, comuna y región) del riesgo asegurado, que incluye cobertura de edificio, una vez que se ha emitido una póliza de Hogar Full en la plataforma TIA.

    @DataProvider(name = "dataHogarFull")
    public Object[][] dataHogarFull() throws CsvValidationException, InterruptedException, IOException {

        Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataHogarFull.csv", 1, 17);
        return data;
    }
}
