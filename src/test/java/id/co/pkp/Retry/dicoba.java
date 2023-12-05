package id.co.pkp.Retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class dicoba implements IRetryAnalyzer {
    int retryCount = 0;
    int maxRetrycount = 3;
    @Override
    public boolean retry(ITestResult result){
        if (!result.isSuccess()){
            if (retryCount < maxRetrycount){
                System.out.println(
                        "Retrying Test : Re-running "+ result.getName() + "for " + (retryCount + 1)+ " time(s).");
                retryCount++; //increase the maxRetryCount +1
                result.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);

            }
        } else {
                result.setStatus(ITestResult.SUCCESS);
            }
            return false;
    }
}
