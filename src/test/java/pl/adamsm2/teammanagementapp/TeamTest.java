package pl.adamsm2.teammanagementapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class TeamTest {

    private static final String TEAMS_ENDPOINT_ADDRESS = "http://localhost:8080/teams";
    private WebDriver webDriver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get(TEAMS_ENDPOINT_ADDRESS);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    @DirtiesContext
    void givenTeamData_whenCreateTeam_thenTeamIsCreated() throws Exception {
        String teamName = "team1";
        String abbreviation = "t1";
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[contains(text(),'Create')]")).click();
        webDriver.findElement(By.id("name")).sendKeys(teamName);
        webDriver.findElement(By.id("abbreviation")).sendKeys(abbreviation);
        Thread.sleep(500);
        ((JavascriptExecutor) webDriver).executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(500);
        WebElement teamNameElement = webDriver.findElement(By.xpath("//*[contains(text(),'" + teamName + "')]"));
        assertThat(teamNameElement.isDisplayed()).isTrue();
    }

/*    @Test
    @DirtiesContext
    void failedTest() throws Exception {
        String teamName = "team1";
        String abbreviation = "t1";
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[contains(text(),'Create')]")).click();
        webDriver.findElement(By.id("name")).sendKeys(teamName);
        webDriver.findElement(By.id("abbreviation")).sendKeys(abbreviation);
        Thread.sleep(500);
        ((JavascriptExecutor) webDriver).executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(500);
        WebElement teamNameElement = webDriver.findElement(By.xpath("//*[contains(text(),'" + teamName + "')]"));
        assertThat(teamNameElement.isDisplayed()).isFalse();
    }*/

}
