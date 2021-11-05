package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.google.common.primitives.Ints.max;
import static java.lang.Thread.sleep;


public class case1 {

    public static ChromeDriver driver;


    @Before
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://Chromedriver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown() {
        driver.quit();
        close();
    }

    @Дано("Вход в бсл через (.*) с паролем (.*)")
    public void вход_в_бсл_через_с_паролем(String login, String password) throws InterruptedException {
        driver.get("https://sso.kz00c1.kz.infra/opensso/UI/Login?goto=https%3A%2F%2Fbsl.kz00c1.kz.infra%2Fbsl%2Fhome%3F");
        driver.findElementById("IDToken1").sendKeys(login);
        sleep(2000);
        driver.findElementById("IDToken2").sendKeys(password);
        sleep(2000);
        driver.findElementById("kc-login").click();
        sleep(2000);
    }

    @Тогда("Нажать на создание банка")
    public void нажать_на_создание_банка()  throws InterruptedException {
        driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/a/img").click(); // click create bank
        sleep(2000);

    }

    @Дано("Код банка")
    public void код_банка() throws InterruptedException {

        driver.findElementById("id24").sendKeys("Ravil777");
        sleep(2000);

    }

    @Дано("Второстепенный банковский код")
    public void второстепенный_банковский_код() throws InterruptedException {
        driver.findElementById("id25").sendKeys("143");
        sleep(2000);

    }

    @Дано("Наименование банка")
    public void наименование_банка() throws InterruptedException {
        driver.findElementByXPath("/html/body/div[2]/div[1]/div/form/div[2]/div[1]/div/ul[1]/li/input").sendKeys("Akalzhan Bank");
        sleep(2000);

    }

    @Дано("Статус")
    public void статус() throws InterruptedException {
        WebElement status1 = driver.findElementByXPath("/html/body/div[2]/div[1]/div/form/div[2]/div[1]/div/ul[2]/li[1]/select");
        Select value = new Select(status1);
        value.selectByValue("ACTIVE");
        sleep(2000);

    }

    @Дано("Сдвиг даты платежа")
    public void сдвиг_даты_платежа() throws InterruptedException {
        driver.findElementById("id29").sendKeys("25");
        sleep(2000);

        driver.findElementById("id15").click();
        sleep(4000);


    }

    @Тогда("Проверка нового банка")
    public void проверка_нового_банка() throws InterruptedException {
        String name = driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div/div[1]/ul[1]/li[1]/span[2]/span").getText();
        Assert.assertNotNull(name, "not null");
    }




}

