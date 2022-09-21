
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class Scrapper {

    public Elements scrap(String url){
        Document doc = null;
        Elements message = null;
        try{
            doc = Jsoup.connect(url).get();
            message = doc.getElementsByTag("tr");
        } catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
