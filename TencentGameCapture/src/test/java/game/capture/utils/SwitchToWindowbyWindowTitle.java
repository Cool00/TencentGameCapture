package game.capture.utils;

import java.util.Set;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

public class SwitchToWindowbyWindowTitle {
    public boolean switchToWindow(WebDriver driver,String windowTitle){  
        boolean flag = false;  
        try {  
            String currentHandle = driver.getWindowHandle();  
            Set<String> handles = driver.getWindowHandles();  
            for (String s : handles) {  
                if (s.equals(currentHandle))  
                    continue;  
                else {  
                    driver.switchTo().window(s);  
                    if (driver.getTitle().contains(windowTitle)) {  
                        flag = true;  
                        break;  
                    } else  
                        continue;  
                }  
            }  
        } catch (NoSuchWindowException e) {  
            flag = false;  
        }  
        return flag;  
    }  
}
