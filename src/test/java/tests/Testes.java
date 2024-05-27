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
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")

public class Testes {
    private WebDriver navegador;
    @Before
    public void setUp(){
        // remember to not use hardcoded path in submission
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\emilly\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        navegador.get("https://www.grocerycrud.com/v1.x/demo/bootstrap_theme");
    }

    @Test
    public void testCadastroUsuario(){

        //

        navegador.findElement(By.id("switch-version-select")).click();
        navegador.findElement(By.xpath("//*[@id=\"switch-version-select\"]/option[2]")).click();
        navegador.findElement(By.xpath("//*[@id=\"gcrud-search-form\"]/div[1]/div[1]/a")).click();
        navegador.findElement(By.name("customerName")).sendKeys("Teste Sicredi");
        navegador.findElement(By.name("contactLastName")).sendKeys("Teste");
        navegador.findElement(By.name("contactFirstName")).sendKeys("Emilly");
        navegador.findElement(By.name("phone")).sendKeys("51 9999-9999");
        navegador.findElement(By.name("addressLine1")).sendKeys("Av Assis Brasil, 3970");
        navegador.findElement(By.name("addressLine2")).sendKeys("Torre D");
        navegador.findElement(By.name("city")).sendKeys("Porto Alegre");
        navegador.findElement(By.name("state")).sendKeys("RS");
        navegador.findElement(By.name("postalCode")).sendKeys("91000-000");
        navegador.findElement(By.name("country")).sendKeys("Brasil");
        navegador.findElement(By.id("field_salesRepEmployeeNumber_chosen")).click();
        //Fixter

        navegador.findElement(By.name("creditLimit")).sendKeys("200");
        navegador.findElement(By.id("form-button-save")).click();

        WebElement msg = navegador.findElement(By.id("report-success"));

        String textNoElementMsg = msg.getText();
        assertEquals("Your data has been successfully stored into the database. ", textNoElementMsg);

    }

    @Test
    public void criarProjeto(){

        navegador.findElement(By.linkText("create your own project")).click();
        navegador.findElement(By.id("username")).sendKeys("emilly123");
        navegador.findElement(By.id("password")).sendKeys("123456");
        navegador.findElement(By.name("login")).click();
        navegador.findElement(By.id("project_name")).sendKeys("Credito");
        navegador.findElement(By.id("project_description")).sendKeys("Teste");
        navegador.findElement(By.id("project_identifier")).sendKeys("001");
        navegador.findElement(By.xpath("//*[@id=\"project_trackers\"]/label[2]")).click();
        navegador.findElement(By.xpath("//*[@id=\"project_trackers\"]/label[3]")).click();
        navegador.findElement(By.name("continue")).click();
    }

    @Test
    public void cadastroTarefas(@Param(name="tipo")String tipo, @Param(name="titulo")String titulo, @Param(name="situacao")String situacao, @Param(name="prioridade")String prioridade)
    {
        navegador.findElement(By.className("login")).click();
        navegador.findElement(By.id("username")).sendKeys("emilly");
        navegador.findElement(By.id("password")).sendKeys("1234");
        navegador.findElement(By.name("login")).click();
        navegador.findElement(By.xpath("//*[@id=\"top-menu\"]/ul/li[3]/a")).click();
        navegador.findElement(By.xpath("//*[@id=\"projects-index\"]/ul/li[7]/div/a")).click();
        navegador.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[4]/a")).click();
        navegador.findElement(By.id("issue_tracker_id")).sendKeys(tipo);
        navegador.findElement(By.id("issue_subject")).sendKeys(titulo);
        navegador.findElement(By.id("issue_status_id")).sendKeys(situacao);
        navegador.findElement(By.id("issue_priority_id")).sendKeys(prioridade);
        navegador.findElement(By.name("commit")).click();

    }


    @After
    public void tearDown(){
        navegador.quit();
    }
}
