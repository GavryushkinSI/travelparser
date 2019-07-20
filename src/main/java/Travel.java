import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gavryushkin S.I. 20.07.2019
 */
public class Travel {
    public static void main(String[] args) throws IOException {
        //Google Chrone WebDriver version 75.0.3770.142 included in this project (folder driver)
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Stas\\Desktop\\travelparser\\src\\main\\java\\driver\\chromedriver.exe"
                );
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://welcometver.ru/events/archive");
        for(int i=0;i<15;i++) {
            driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div[5]/a")).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,String> map=new HashMap<String,String>();
        List<WebElement> list=driver.findElements(By.className("card__body"));
        List<WebElement> list_2=driver.findElements(By.className("card__date"));
        ArrayList<String> date=new ArrayList<String>();
        ArrayList<String> meet=new ArrayList<String>();
        for(int i=0;i<list_2.size();i++){
            WebElement element=list.get(i).findElement(By.tagName("a"));
            meet.add(element.getText());
            System.out.println(element.getText());
            WebElement element_2=list_2.get(i);
            date.add(element_2.getText().replaceAll("\n"," "));
            System.out.println(element_2.getText().replaceAll("\n"," "));
            //map.put(element.getText(),element_2.getText().replaceAll("\n"," "));
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedWriter writer=new BufferedWriter(new FileWriter("C:\\Users\\Stas\\Desktop\\meet.txt"));
//        for(Map.Entry<String,String> maper:map.entrySet()){
//            writer.write("Дата: "+ maper.getValue()+""+"Мероприятие: "+maper.getKey()+"\r\n");
//        }
        for(int i=0;i<meet.size();i++){
            writer.write(date.get(i)+" "+meet.get(i)+"\r\n");
        }
        writer.flush();
        writer.close();
        System.out.println(date.size()==meet.size());
    }

}
