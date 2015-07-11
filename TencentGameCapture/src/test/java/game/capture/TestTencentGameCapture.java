package game.capture;

import game.capture.utils.ClearJsonFileTool;
import game.capture.utils.JsonFileOutputWriterTool;
import game.capture.utils.JsonFileWriterTool;
import game.capture.utils.ReadLineTool;
import game.capture.utils.Sleep;
import game.capture.utils.SwitchToWindowbyWindowTitle;
import game.capture.utils.FileWriterTool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.alibaba.fastjson.JSON;


public class TestTencentGameCapture {
  
	  private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private game.capture.utils.Sleep sleepSomeMilliseconds = new Sleep();
	  private SwitchToWindowbyWindowTitle switchtowindow = new SwitchToWindowbyWindowTitle();
	  private FileWriterTool filewritertool = new FileWriterTool();
	  private ReadLineTool readlinetool = new ReadLineTool();
//	  private JsonFileOutputWriterTool jsonFileOutputWriterTool = new JsonFileOutputWriterTool();//local run
	  private JsonFileWriterTool jsonFileOutputWriterTool = new JsonFileWriterTool();//remote run
	  private ClearJsonFileTool clearjsonfiletool = new ClearJsonFileTool();
	  private int count = 0;
	  private int counttemp = 0;

	  @BeforeMethod
	  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:/selenium/drivers/chromedriver_win32/chromedriver.exe");
	    driver = new ChromeDriver();
	    baseUrl = "http://android.myapp.com/myapp/search.htm?kw=";
	    driver.get(baseUrl);
	    
	    //clear json file first
	    clearjsonfiletool.clearJsonFileTool();
	    //add json begin code [
	    jsonFileOutputWriterTool.jsonFileWriterTool("[");
	  }
	  @Test(priority = 1)
	  public void testTencentGameCapture() throws Exception {
		
		FileInputStream filetemp = new FileInputStream("TencentGameList.txt"); 
		
		BufferedReader buf= new BufferedReader(new InputStreamReader(filetemp,"utf-8"));
//		int count = 0;
		while((buf.readLine()) != null){
			count++;
		}
		buf.close();
		
		FileInputStream file = new FileInputStream("TencentGameList.txt"); 
		
		BufferedReader br= new BufferedReader(new InputStreamReader(file,"utf-8"));
		String line =  null;
		while((line = br.readLine()) != null){ 
			counttemp++;
			driver.quit();
			//print run test count
		    System.out.println("\n这是第 "+ counttemp +" 个游戏，游戏名："+line+"");
			driver = new ChromeDriver();
			driver.get(baseUrl + line);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[2]/div/div/a")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    switchtowindow.switchToWindow(driver, "搜索 - 应用宝官网");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    sleepSomeMilliseconds.sleep(3000);
		    
		    Map<String,String> game = new HashMap<String,String>();
		    
		    //gameName
		    game.put("gameName", driver.findElement(By.xpath("//div[3]/div/div/div[2]/div/div")).getText());
		    //version
		    game.put("version", driver.findElement(By.xpath("//div[3]/div/div[3]/div[2]")).getText());
		    //updateTime
		    game.put("updateTime", driver.findElement(By.xpath("//div[3]/div/div[3]/div[4]")).getText());
		    //score
		    game.put("score", driver.findElement(By.xpath("//div[3]/div/div/div[2]/div[2]/div[2]")).getText().substring(0, driver.findElement(By.xpath("//div[3]/div/div/div[2]/div[2]/div[2]")).getText().length()-1));
		    //comments
		    //download
		    game.put("download", driver.findElement(By.xpath("//div[3]/div/div/div[2]/div[3]/div")).getText());
		    //size
		    game.put("size", driver.findElement(By.xpath("//div[3]/div/div/div[2]/div[3]/div[3]")).getText());
		    //provider 厂商
		    game.put("provider", driver.findElement(By.xpath("//div[3]/div/div[3]/div[6]")).getText());
		    //category 类型
		    game.put("category", driver.findElement(By.id("J_DetCate")).getText());
		    //gameDesc 描述
		    game.put("gameDesc", driver.findElement(By.xpath("//div[3]/div/div[4]/div[2]/div")).getText());
		    //logoUrl
		    game.put("logoUrl", ((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName('img')[0].src")+"");
		    sleepSomeMilliseconds.sleep(500);
		    //imagesUrl
		    game.put("imagesUrl", ((JavascriptExecutor)driver).executeScript("return document.getElementById('picInImgBoxImg0').src")+","+((JavascriptExecutor)driver).executeScript("return document.getElementById('picInImgBoxImg1').src")+","+((JavascriptExecutor)driver).executeScript("return document.getElementById('picInImgBoxImg2').src")+","+((JavascriptExecutor)driver).executeScript("return document.getElementById('picInImgBoxImg3').src")+","+((JavascriptExecutor)driver).executeScript("return document.getElementById('picInImgBoxImg4').src"));
		    //downloadLink
		    filewritertool.fileWriterTool(driver.getPageSource());;
		    game.put("downloadLink", readlinetool.readLineTool().replace("\"", "").trim());
		    
		    sleepSomeMilliseconds.sleep(500);
		    //write to file
		    jsonFileOutputWriterTool.jsonFileWriterTool(JSON.toJSONString(game));
		    //add json end code ,
		    if(count!=counttemp){
		    	jsonFileOutputWriterTool.jsonFileWriterTool(",");
		    }
		}
		br.close();
		
	  }
	  @AfterMethod
	  public void tearDown() throws Exception {
		//add json end code ]
		jsonFileOutputWriterTool.jsonFileWriterTool("]");
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      Assert.fail(verificationErrorString);
	    }
	  }
}
