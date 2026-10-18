package test.java.tia.consistencia;

import com.opencsv.exceptions.CsvValidationException;
import main.java.config.OpenCSV;
import main.java.pageSteps.tia.transversal.TransversalSteps;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;

public class ConsistenciaTests extends BaseTest {
    @Test(priority = 1, enabled = true,  dataProvider = "dataHogarFull")
    public void ATMOQA380(String[] args) { 
        getTest().assignCategory("Hogar_Full").assignDevice("Consistencia_Producto");
        setStep(getTest().createNode("Generación de un endoso complejo agregando coberturas adicionales a la póliza de Hogar Full (Gastos Médicos, Telemedicina, Mascotas), una vez que se ha emitido en la plataforma TIA."));

        TransversalSteps.ingresarComoSuscriptor(userSuscriptor, passSuscriptor, environment);
        TransversalSteps.ingresarRegistroDeVersion();
        TransversalSteps.buscarProductoConsistencia("HF");
        
    }

    

    @DataProvider(name = "dataHogarFull")
    public Object[][] dataHogarFull() throws CsvValidationException, InterruptedException, IOException {

        Object[][] data = OpenCSV.getCSVParametersDescription("endosos" + File.separator + "CSVDataHogarFull.csv", 1, 17);
        return data;
    }
}
