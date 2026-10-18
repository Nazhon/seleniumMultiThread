package test.java.tia.cancelacion;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.productos.MascotasResponsableSteps;
import main.java.pageSteps.tia.transversal.TransversalSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;

public class MascotasResponsableTests extends BaseTest {
    @Test(priority = 1, enabled = true, dataProvider = "dataMascotasResponsable")
    public void ATMOQA75(String args[]) throws InterruptedException {
        getTest().assignCategory("Mascotas_Responsable").assignDevice("Cancelacion");
        setStep(getTest().createNode(
                "Cancelación de una póliza de tipo Mascota Responsable en la plataforma TIA seleccionando un motivo."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        // creando una póliza de MR para cancelarla
        TransversalSteps.crearPoliza(args[2], args[3]);
        MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], "Mestizo");
        TransversalSteps.calcularPrimaTotal();
        TransversalSteps.completarPoliza("Póliza");
        TransversalSteps.aceptarDocumentosPoliza();
        // ingresar a la poliza para cancelarla
        TransversalSteps.ingresarPolizaBusquedaRapida();
        //TransversalSteps.ajustarPoliza();
        
        MascotasResponsableSteps.ajustarPolizaMR("402");
        
        TransversalSteps.seleccionarMotivoCancelacionPoliza(args[10]);
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //valida cancelación
        TransversalSteps.validaTransaccionCancelacionPolizaMR();
    }
    @Test(priority = 2, enabled = true, dataProvider = "dataMascotasResponsable")
    public void ATMOQA77(String args[]) throws InterruptedException {
        getTest().assignCategory("Mascotas_Responsable").assignDevice("Cancelacion");
        setStep(getTest().createNode(
                "Cancelación de una póliza de tipo Mascota Responsable en la plataforma TIA, dentro de la fecha de retracto, con una cuota pagada."));
        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.buscarEntidad(args[0], args[1]);
        // creando una póliza de MR para cancelarla
        TransversalSteps.crearPoliza(args[2], args[3]);
        MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], "Mestizo");
        TransversalSteps.calcularPrimaTotal();
        TransversalSteps.completarPoliza("Póliza");
        TransversalSteps.aceptarDocumentosPoliza();
        
        TransversalSteps.pagarCuota();
       
        TransversalSteps.visitarPaginaHome();
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.ingresarPolizaBusquedaRapida();
        TransversalSteps.ajustaPolizaTrans("402");
        TransversalSteps.seleccionarMotivoCancelacionPoliza(args[10]);
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //valida cancelación
        TransversalSteps.validaTransaccionCancelacionPolizaMR();
        //validar reversion del monto
        TransversalSteps.visitarPaginaHome();
        TransversalSteps.validarReversionPagoCuota();
    }
    
    @Test(priority = 3, enabled = true, dataProvider = "dataMascotasResponsable")
    public void ATMOQA270(String args[]) throws InterruptedException {
        getTest().assignCategory("Mascotas_Responsable").assignDevice("Cancelacion");
        setStep(getTest().createNode(
                "TIAUAT-CANCELACION-MR-Seleccion Motivo Sin Pago Cuota"));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        // creando una póliza de MR para cancelarla
        TransversalSteps.crearPoliza(args[2], args[3]);
        MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], "Mestizo");
        TransversalSteps.calcularPrimaTotal();
        TransversalSteps.completarPoliza("Póliza");
        TransversalSteps.aceptarDocumentosPoliza();
        // ingresar a la poliza para cancelarla
        TransversalSteps.ingresarPolizaBusquedaRapida();
        //TransversalSteps.ajustarPoliza();
        
        MascotasResponsableSteps.ajustarPolizaMR("402");
        
        TransversalSteps.seleccionarMotivoCancelacionPoliza(args[10]);
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //valida cancelación
        TransversalSteps.validaTransaccionCancelacionPolizaMR();
    }
    
    @Test(priority = 4, enabled = true, dataProvider = "dataMascotasResponsable")
    public void ATMOQA271(String args[]) throws InterruptedException {
        getTest().assignCategory("Mascotas_Responsable").assignDevice("Cancelacion");
        setStep(getTest().createNode(
                "TIAUAT-CANCELACION-MR-Retracto Con Una Cuota Pagada"));
        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, "uat");
        TransversalSteps.buscarEntidad(args[0], args[1]);
        // creando una póliza de MR para cancelarla
        TransversalSteps.crearPoliza(args[2], args[3]);
        MascotasResponsableSteps.completarDatosMascotaMestiza(args[4], args[5], args[6], args[7], "Mestizo");
        TransversalSteps.calcularPrimaTotal();
        TransversalSteps.completarPoliza("Póliza");
        TransversalSteps.aceptarDocumentosPoliza();
        
        TransversalSteps.pagarCuota();
       
        TransversalSteps.visitarPaginaHome();
        TransversalSteps.buscarEntidad(args[0], args[1]);
        TransversalSteps.ingresarPolizaBusquedaRapida();
        TransversalSteps.ajustaPolizaTrans("402");
        TransversalSteps.seleccionarMotivoCancelacionPoliza(args[10]);
        TransversalSteps.completarPoliza("endoso");
        TransversalSteps.aceptarDocumentosPoliza();
        //valida cancelación
        TransversalSteps.validaTransaccionCancelacionPolizaMR();
        //validar reversion del monto
        TransversalSteps.visitarPaginaHome();
        TransversalSteps.validarReversionPagoCuota();
    }
   
    @DataProvider(name = "dataMascotasResponsable")
    public Object[][] dataMascotasResponsable() throws CsvValidationException, InterruptedException, IOException {

        Object[][] data = OpenCSV.getCSVParametersDescription("cancelacion" + File.separator + "CSVDataMascotasResponsable.csv", 1, 11);
        return data;
    }
}
