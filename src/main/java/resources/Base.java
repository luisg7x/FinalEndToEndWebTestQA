package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {

	private WebDriver driver;
	public Properties proper;
	
	private Connection con;
	private ResultSet rs; 
	
	public WebDriver initializerDriver() throws Exception {
		
		String locationProper = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis = new FileInputStream(locationProper);
		
		proper = new Properties();
		proper.load(fis);
		
		String browser = proper.getProperty("browser");
		
		switch (browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\luisg\\Desktop\\Selenium\\chromedriver.exe");
			driver = new ChromeDriver(); 
		break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "C:\\Users\\luisg\\Desktop\\Selenium\\msedgedriver.exe");
		    driver = new EdgeDriver();
		break;
		case "chromeheadless":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\luisg\\Desktop\\Selenium\\chromedriver.exe");
			//running test on head less mode: running test on background, that means you no will see how the browser get open and run the test etc, some people thinks that this way is used for get the benefit of less resources utilization
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			driver = new ChromeDriver(options); 
		break;
		default:
			throw new Exception("No Browser selected");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public String getScreenShotPath(String testname, WebDriver driver) throws IOException {
		//long way:
		/*TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);*/
		
		//doing screenshot
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//one fact: saving files in: C:\ ... will be denied or will show a error, is because C drive always need admin privilliges
		//import apache io
		//getting current path of the project:
		String destinationFile = System.getProperty("user.dir") + /* adding folder-> */ "\\reports\\" + testname + ".png"; //note: folder is created automatically
		FileUtils.copyFile(src, new File(destinationFile));
		//return is necesary for extent report
		return destinationFile;
	}
	
	private ResultSet getDataBaseConnection(String query) throws SQLException {
		String host = "localhost";
		String port = "3306";
		String dataBase = "selenium";
		String connection = "jdbc:mysql://" + host + ":" + port + "/" + dataBase + "?useSSL=false";
		
		//import java.sql
		con = DriverManager.getConnection(connection, "root", "root");
		//import java.sql
		Statement s = con.createStatement();
		//import java.sql
		rs = s.executeQuery(query);
		
		return rs;
	}
	
	private void closeConnection() throws SQLException {
		con.close();
	}
	
	public String[] getFirstProduct() throws SQLException {
		getDataBaseConnection("select * from products LIMIT 1");
		rs.first();
		String product[] = new String[2];
				product[0] = rs.getString("name");
				product[1] = Integer.toString(rs.getInt("quantity"));
		closeConnection();
		return product;
	}
	
	public int getCountRow(String query) throws SQLException {
		getDataBaseConnection(query);
		//move cursor
		rs.last();
		int count = rs.getRow();
		closeConnection();
		return count;
	}
	
	public String[][] getProducts() throws SQLException {
		String query = "select * from products";

		String product[][] = new String[getCountRow(query)][2];
		
		getDataBaseConnection(query);
		
		int i = 0;
		while(rs.next()) {
			product[i][0] = rs.getString("name");
			product[i][1] = Integer.toString(rs.getInt("quantity"));
			i++;
		}
		closeConnection();
		return product;
	}
}
