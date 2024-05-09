package org.baseclass1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	public static WebDriver driver;
	
  //switch to frame by id/name
	public void switchToFrameByIdName(String idName) {
		driver.switchTo().frame(idName);
	}

	//switch to frame by index	
	public void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	//switch to frame by element
	public void switchToFrameByWebElement(WebElement element) {
		driver.switchTo().frame(element);
	}
	
	//switch to default content
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
    
	//switch to childwindow
	public void switchToChildWindow() {
		String pWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String eachWindow : allWindows) {
			if (!pWindow.equals(eachWindow)) {
				driver.switchTo().window(eachWindow);
			}

		}
	}
	
    // get alloptions from ddn as text
	public List<String> ddnGetAllOptionAsText(WebElement element) {
		List<String> allOptionsText = new ArrayList<>();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String text = webElement.getText();
			allOptionsText.add(text);

		}
		return allOptionsText;
	}

	//get all option from dropdown as attribute
	public List<String> ddnGetAllOptionAsAttribute(WebElement element) {
		List<String> allOptionsAttribute = new ArrayList<>();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String attribute = webElement.getAttribute("value");
			allOptionsAttribute.add(attribute);

		}
		return allOptionsAttribute;
	}
	
	//select all options in dropdown
	public void ddnselectalloptions(WebElement element) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for(int i=0;i<options.size();i++) {
			select.selectByIndex(i);
		}
	}
	
   // to get project path
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
	// to get property file value
	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties= new Properties();
		properties.load(new FileInputStream(getProjectPath()+"\\Config\\ConfigProperties"));
		String value=(String)properties.get(key);
		return value;
		
	}
	//Takesscreenshot
	public void screenshot(String name) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(getProjectPath() + "\\screenshot\\" + name + ".png"));
	}

	//Explicit wait
	public void visiblityOf(WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driverWait.until(ExpectedConditions.visibilityOf(element));

	}

	//Implicit wait
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	public void implicitWait(int secs) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));

	}

	//Sendkeys by javascript
	public void elementSendKeysJS(WebElement element, String data) {
		visiblityOf(element);
		if (isEnabled(element) && isDisplayed(element)) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('value','" + data + "')", element);
		}
	}

	//click by javascript
	public void clickByJavaScript(WebElement element) {
		  visiblityOf(element);
		  if (isEnabled(element) && isDisplayed(element)) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click()",element);
			}
		  
	  }
	//scroll down
	public void scrolldown(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	//select dropdown option by text
	public void ddnSelectOptionByText(WebElement element, String text) {
		visiblityOf(element);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	//select dropdown option by index
	public void ddnSelectOptionByIndex(WebElement element, int index) {
		visiblityOf(element);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	//select dropdown option by attribute 
	public void ddnSelectOptinByAttributeValue(WebElement element,String value) {
		  visiblityOf(element);
		  Select select= new Select(element);
		  select.selectByValue(value);
	  }
	
	//Browserlaunch
	public static void browserLaunch(String browserType) {
		switch (browserType) {
		case "CHROME":
			driver = new ChromeDriver();
			break;
		case "IE":
			driver = new InternetExplorerDriver();
			break;
		case "EDGE":
			driver = new FirefoxDriver();
			break;
		case "FIREFOX":
			driver = new ChromeDriver();
			break;

		default:
			break;
		}

	}

	// launch the chrome browser
	public void ChromeBrowserLaunch() {
		driver=new ChromeDriver();

	  }
	
	//enter url
	public static void urlEnter(String url) {
		driver.get(url);

	}

	//maximize window
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	//Threadsleep
	public void sleep() throws InterruptedException {
		Thread.sleep(3000);
	}

	//sendkeys
	public void elementSendKeys(WebElement element, String data) {
		visiblityOf(element);

		if (isEnabled(element) && isDisplayed(element)) {
			element.sendKeys(data);
		}
	}

	//click
	public void elementClick(WebElement element) {
		visiblityOf(element);
		if (isEnabled(element) && isDisplayed(element)) {
          element.click();
		}
	}

	//get title of webpage
	public String getTitleOfWebpage() {
		String title = driver.getTitle();
		return title;
	}
	



	//find locator by id
	public WebElement findLocatorById(String attributeValue) {
		WebElement element = driver.findElement(By.id(attributeValue));
		return element;
	}

	//find locator by name
	public WebElement findLocatorByName(String attributeValue) {
		WebElement element = driver.findElement(By.name(attributeValue));
		return element;
	}

	// find locator by class
	public WebElement findLocatorByClassName(String attributeValue) {
		WebElement element = driver.findElement(By.className(attributeValue));
		return element;
	}

	//find locator by xpath
	public WebElement findLocatorByXpath(String xpathExp) {
		WebElement element = driver.findElement(By.xpath(xpathExp));
		return element;
	}
    
	//findelements for xpath
	public List<WebElement> findLocatorsByXpath(String xpath) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		return elements;

	}
	
	//is displayed
	public boolean isDisplayed(WebElement element) {
		visiblityOf(element);
		boolean displayed = element.isDisplayed();
		return displayed;
	}

	//is enabled
	public boolean isEnabled(WebElement element) {
		visiblityOf(element);
		boolean displayed = element.isEnabled();
		return displayed;
	}

	//is selected
	public boolean isSelected(WebElement element) {
	    visiblityOf(element);
		boolean b = element.isSelected();
		return b;
	}
	
	//to get text
	public String elementGetText(WebElement element) {
		visiblityOf(element);
		String text = null;
		if (isDisplayed(element)) {
			text = element.getText();
		}
		return text;
	}

	//to get attribute-99%
	public String elementGetAttribute(WebElement element) {
		visiblityOf(element);
		String attribute = null;
		if (isDisplayed(element)) {
			attribute = element.getAttribute("value");
		}
		return attribute;
	}

	//get attribute -remaining 1%
	public String elementGetAttribute(WebElement element, String attributeName) {
		visiblityOf(element);
		String attribute = element.getAttribute(attributeName);
		return attribute;
	}
	
	//sendkeys and enter
	public void elementSendkeysAndEnter(WebElement element,String data) {
		  visiblityOf(element);
		  if (isEnabled(element) && isDisplayed(element)) {
			  element.sendKeys(data,Keys.ENTER);
			}
	}
	
	//click ok in alert
	public void alertOk() {
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
	  }
	
	//click cancel in alert
	public void alertCancel() {
		  Alert alert = driver.switchTo().alert();
		  alert.dismiss();
	  }
	
	//close all windows
	public void closeAllWindows() {
		driver.quit();
	  }
	  
	//close current window
	  public void closeCurrentWindow() {
		driver.close();
	  }
	
	//getcurrent url
	  public String getCurrentUrl() {
			String currentUrl = driver.getCurrentUrl();
	        return currentUrl;
		  }
	
	//get first select option from dropdown
	  public WebElement getFirstSelectedOptionInDropDown(WebElement element) {
		     Select select = new Select(element);
		     WebElement firstselectoption = select.getFirstSelectedOption();
		     return firstselectoption;
	  	}
	
	//verify dropdown is multipleoption
	  public boolean verifyDdnIsMultiple(WebElement element) {
		    Select select = new Select(element);
			boolean b = select.isMultiple();
			return b;
		  	}
	  
	//deselect by index
	  public void ddndeselectByIndex(int index,WebElement element) {
		  Select select = new Select(element);
			select.deselectByIndex(index);

		}
		  
	//deselct by attribute
		  public void ddndeSelectByAttribute(WebElement element,String attributevalue) {
			  Select select = new Select(element);
			select.deselectByValue("attributevalue");

		}
		  
	//deselect by text
		  public void ddndeSelectByText(WebElement element,String text) {
			  Select select = new Select(element);
			select.deselectByVisibleText("text");

		}
		  
	//deselect all
		  public void ddndeselectAll(WebElement element) {
			  Select select = new Select(element);
			select.deselectAll();

		}
		  
    //get parent window handle
		  public String getParentWindowHandle() {
				String windowHandle = driver.getWindowHandle();
				return windowHandle;
			}
			  
	//get all window handles
			  public Set<String> allWindowHandles() {
				Set<String> windowHandles = driver.getWindowHandles();
				return windowHandles;
			}
			  
	//clear textbox
			  public void clearTextBox(WebElement element) {
				  visiblityOf(element);
				  element.clear();
			}
	
	//mouseover
			  public void mouseover(WebElement element) {
					Actions actions= new Actions(driver);
					actions.moveToElement(element).perform();

				}
				  
	//drag and drop
				  public void dragAndDrop(WebElement element,WebElement element1) {
					  Actions actions= new Actions(driver);
					actions.dragAndDrop(element, element1).perform();
				}
				  
	//right click
				  public void rightClick(WebElement element) {
					  Actions actions= new Actions(driver);
					actions.contextClick(element).perform();

				}
				  
	//double click
				  public void doubleClick(WebElement element) {
					  Actions actions= new Actions(driver);
						actions.doubleClick(element).perform();

					}
	//refresh
				  public void refresh() {
						driver.navigate().refresh();

					}
	//deleteallcookies
				  public void deleteCookies() {
					  driver.manage().deleteAllCookies();
				}
	//Screenshot in bytes
				  public byte[] screenshotBytes() {
					  TakesScreenshot screenshot= (TakesScreenshot)driver;
					  byte[] bs = screenshot.getScreenshotAs(OutputType.BYTES);
					  return bs;
				}
	
	
	
	
	//BASE CLASS FOR EXCEL
	//1.To Get cell data
	public String getCellData(String sheetname,int rownum,int cellnum) throws IOException {
		  String res="";
		File file = new File(getProjectPath()+"\\Excel\\sheet2.xlsx");
		FileInputStream stream= new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		CellType Type = cell.getCellType();
		switch (Type) {
		case STRING:
			res = cell.getStringCellValue();
			break;
			
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateformat= new SimpleDateFormat("dd-MMM-yyyy");
				res = dateformat.format(dateCellValue);
			}
			else {
				double numericCellValue = cell.getNumericCellValue();
				long round = Math.round(numericCellValue);
				if(round==numericCellValue) {
					res = String.valueOf(round);
				}
				else {
					res = String.valueOf(numericCellValue);
				}
			
			}
         break;
		default:
			break;
		}
	
    return res;
	}
	
	
        // 2.To create cell and insert value in existing row
	public void createCellInExistingRow(String sheetname,int rownum,int cellnum,String data) throws IOException {
		File file = new File(getProjectPath()+"\\Excel\\Sheet2.xlsx");
		FileInputStream stream= new FileInputStream(file);
		Workbook workbook= new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(data);
		FileOutputStream outputstream= new FileOutputStream(file);
		workbook.write(outputstream);
  
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

