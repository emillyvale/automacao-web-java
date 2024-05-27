package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "Comparacao.csv")
public class Validacao {
    private WebDriver navegador;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\emill\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        navegador.get("http://demo.redmine.org/");
    }

    @Test
    public void paginacaoValidacao (@Param(name="type")String type, @Param(name="title")String title, @Param(name="situation")String situation, @Param(name="priority")String priority){

        navegador.findElement(By.xpath("//*[@id=\"top-menu\"]/ul/li[2]/a")).click();
        navegador.findElement(By.xpath("//*[@id=\"projects-index\"]/ul/li[7]/div/a")).click();
        navegador.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/p/a[1]")).click();
        navegador.findElement(By.xpath("//*[@id=\"content\"]/p[1]/a[2]")).click();
        navegador.findElement(By.xpath("//*[@id=\"issue-300645\"]/td[3]")).click();

        WebElement tracker = navegador.findElement(By.className("tracker"));
        String textoNoElementoTracker = tracker.getText();
        assertEquals(type, textoNoElementoTracker);

        WebElement status = navegador.findElement(By.className("status"));
        String textoNoElementoStatus = status.getText();
        assertEquals(situation, textoNoElementoStatus);

        WebElement priorityElement = navegador.findElement(By.xpath("//*[@id=\"issue-300645\"]/td[5]"));
        String textoNoElementoPriority = priorityElement.getText();
        assertEquals(priority, textoNoElementoPriority);

        WebElement titleElement = navegador.findElement(By.xpath("//*[@id=\"issue-300645\"]/td[6]/a"));
        String textoNoElementoTitulo = titleElement.getText();
        assertEquals(title, textoNoElementoTitulo);

    }
    @After
    public void tearDown(){
        navegador.quit();
    }
}
