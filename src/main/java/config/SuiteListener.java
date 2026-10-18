package main.java.config;

import java.util.List;
import java.util.Map;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.healenium.model.HealingResult;

import test.java.BaseTest;


public class SuiteListener extends BaseTest implements ITestListener, IAnnotationTransformer{
	
	@Override
	public void onTestStart(ITestResult result) {
		ITestListener.super.onTestStart(result);
		Utils.reemplazarLog();
	}
	// se agregan con el fin de poder visualizar si se integran al reporte de testng
	@Override
    public void onTestSuccess(ITestResult result) {
        // Si la prueba es exitosa, también puedes adjuntar información si hubo curación
        attachHealingInfo(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Si la prueba falla, adjuntar la información de curación de Healenium
        attachHealingInfo(result);
    }
//	-------------------
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
		
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
		   
	}
	
    private void attachHealingInfo(ITestResult result) {
        HealingResult healingResult = (HealingResult) result.getAttribute("healingResult");
        
//        if (healingResult != null) {
//            
//            result.setAttribute("healingInfo", healingResult.toString());
//        }
    	 if (healingResult != null) {
             // Añadir la información de la curación al resultado de la prueba y log
             result.setAttribute("healingInfo", healingResult.toString());
//             Reporter.log("Mejoras realizadas por Healenium durante la prueba:", true);
//             Reporter.log(healingResult.toString(), true);
             Utils.outputInfo("Mejoras realizadas por Healenium durante la prueba:");
             Utils.outputInfo(healingResult.toString());
         } else {
//             Reporter.log("No se realizaron mejoras de Healenium en esta prueba.", true);
        	 Utils.outputInfo("No se realizaron mejoras de Healenium en esta prueba.");
         }
    }
	
}
