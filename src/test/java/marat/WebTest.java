package marat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WebTest {
    @Test
    void MainPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/");
        assertEquals("Главная страница", driver.getTitle());
        driver.quit();
    }

    @Test
    void TitlesTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/");
        WebElement button;

        button = driver.findElement(By.id("rootLink"));
        button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Главная страница", driver.getTitle());

        button = driver.findElement(By.id("clientsLink"));
        button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Клиенты", driver.getTitle());

        button = driver.findElement(By.id("officesLink"));
        button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Отделения", driver.getTitle());

        button = driver.findElement(By.id("accountsLink"));
        button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Счета", driver.getTitle());

        driver.get("http://localhost:8080/abcd");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Ошибка", driver.getTitle());

        driver.quit();
    }

    @Test
    void ClientsTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/clients");
        assertEquals("Клиенты", driver.getTitle());

        driver.findElement(By.id("clientName")).sendKeys("abcd");
        driver.findElement(By.id("clientAddress")).sendKeys("abcd");
        driver.findElement(By.id("clientEmail")).sendKeys("abcd");
        driver.findElement(By.id("clientTel1")).sendKeys("abcd");
        driver.findElement(By.id("clientTel2")).sendKeys("abcd");
        driver.findElement(By.id("officeName")).sendKeys("name1");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Клиенты", driver.getTitle());
        WebElement table = driver.findElement(By.id("table"));
        List<WebElement> cells = table.findElements(By.tagName("td"));
        assertEquals("abcd", cells.get(cells.size() - 3).getText());

        cells.get(cells.size() - 3).findElement(By.tagName("a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Клиент", driver.getTitle());
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Редактировать", driver.getTitle());

        driver.findElement(By.id("clientName")).clear();
        driver.findElement(By.id("clientName")).sendKeys("abcde");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Клиент", driver.getTitle());
        table = driver.findElement(By.id("clientInfo"));
        cells = table.findElements(By.tagName("p"));
        assertEquals("ФИО: abcde", cells.get(0).getText());
        
        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Клиенты", driver.getTitle());
        table = driver.findElement(By.id("table"));
        cells = table.findElements(By.tagName("td"));
        assertNotEquals("abcde", cells.get(cells.size() - 1).getText());

        driver.quit();
    }

    @Test
    void OfficeTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/offices");
        assertEquals("Отделения", driver.getTitle());

        driver.findElement(By.id("officeName")).sendKeys("abcd");
        driver.findElement(By.id("officeAddress")).sendKeys("abcd");
        driver.findElement(By.id("officeEmail")).sendKeys("abcd");
        driver.findElement(By.id("officeTel1")).sendKeys("abcd");
        driver.findElement(By.id("officeTel2")).sendKeys("abcd");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Отделения", driver.getTitle());
        WebElement table = driver.findElement(By.id("table"));
        List<WebElement> cells = table.findElements(By.tagName("td"));
        assertEquals("abcd", cells.get(cells.size() - 3).getText());

        cells.get(cells.size() - 3).findElement(By.tagName("a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Отделение", driver.getTitle());
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Редактировать", driver.getTitle());
        driver.findElement(By.id("officeName")).sendKeys("e");
        driver.findElement(By.id("officeEmail")).sendKeys("abcd");
        driver.findElement(By.id("officeTel1")).sendKeys(" abcd");
        driver.findElement(By.id("officeTel2")).sendKeys(" abcd");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Отделение", driver.getTitle());
        table = driver.findElement(By.id("officeInfo"));
        cells = table.findElements(By.tagName("p"));
        assertEquals("ФИО: abcde", cells.get(0).getText());

        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Отделения", driver.getTitle());
        table = driver.findElement(By.id("table"));
        cells = table.findElements(By.tagName("td"));
        assertNotEquals("abcde", cells.get(cells.size() - 3).getText());

        driver.quit();
    }

    @Test
    void ClientErrorTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/clients");
        assertEquals("Клиенты", driver.getTitle());

        driver.findElement(By.id("clientName")).sendKeys("abcd");
        driver.findElement(By.id("clientAddress")).sendKeys("abcd");
        driver.findElement(By.id("clientEmail")).sendKeys("abcd");
        driver.findElement(By.id("clientTel1")).sendKeys("abcd");
        driver.findElement(By.id("clientTel2")).sendKeys("abcd");
        driver.findElement(By.id("officeName")).sendKeys("name 111");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Ошибка запроса", driver.getTitle());

        driver.quit();
    }

    @Test
    void AccountTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/accounts");
        assertEquals("Счета", driver.getTitle());

        driver.findElement(By.id("accountCurbalance")).sendKeys("1");
        driver.findElement(By.id("accountCuraccum")).sendKeys("111");
        driver.findElement(By.id("accountClientId")).sendKeys("1");
        driver.findElement(By.id("accountOfficeId")).sendKeys("1");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Счета", driver.getTitle());
        WebElement table = driver.findElement(By.id("table"));
        List<WebElement> cells = table.findElements(By.tagName("td"));
        assertEquals("name1", cells.get(cells.size() - 2).getText());

        cells.get(cells.size() - 3).findElement(By.tagName("a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Счет", driver.getTitle());
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Редактировать", driver.getTitle());
        driver.findElement(By.id("accountCurbalance")).sendKeys("111");
        driver.findElement(By.id("accountCuraccum")).sendKeys("222");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Счет", driver.getTitle());
        table = driver.findElement(By.id("accountInfo"));
        cells = table.findElements(By.tagName("p"));
        assertEquals("Баланс: 1111", cells.get(2).getText());

        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Счета", driver.getTitle());

        driver.quit();
    }

    @Test
    void AccountErrorTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/accounts");
        assertEquals("Счета", driver.getTitle());

        driver.findElement(By.id("accountCurbalance")).sendKeys("1");
        driver.findElement(By.id("accountCuraccum")).sendKeys("111");
        driver.findElement(By.id("accountClientId")).sendKeys("1");
        driver.findElement(By.id("accountOfficeId")).sendKeys("0");
        driver.findElement(By.id("submitButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        assertEquals("Ошибка", driver.getTitle());

        driver.quit();
    }
}
