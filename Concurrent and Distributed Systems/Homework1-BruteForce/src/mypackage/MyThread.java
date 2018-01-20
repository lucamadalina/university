package mypackage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

/**
 * Created by madalina.luca on 10/18/2017.
 */
public class MyThread  implements Runnable {
    private int pagina;
    boolean status = false;
    Produs produsNou;
    String link;
    String name;
    String id;
    String price;
    Dekker dekker;

    MyThread() {
    }

    MyThread(int pag) {
        this.pagina = pag;
        dekker = new Dekker(pagina);
    }

    @Override
    public void run(){
            parseEmag();
            parseCel();
    }

    public void parseEmag() {
        Document document = null;
        Document documentFirstPage = null;
        final int noOfRecordsPerPage = 60;
        try {
            documentFirstPage = Jsoup.parse(new File("C:\\Users\\madalina.luca\\Desktop\\htmlEmag\\file1.txt"), "UTF-8");
            document = Jsoup.parse(new File("C:\\Users\\madalina.luca\\Desktop\\htmlEmag\\file" + pagina + ".txt"),
                   "UTF-8");
            //document = Jsoup.connect("https://www.emag.ro/casti-pc/p"+pagina+"/c").get();
            //documentFirstPage = Jsoup.connect("https://www.emag.ro/casti-pc/p"+1+"/c").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int nr = 0;
        if ((!(document.text().equalsIgnoreCase(documentFirstPage.text())) && pagina > 1) || pagina == 1) {
            for (int i = 0; i < noOfRecordsPerPage; i++) {
                try {
                    Element element = document.select("h2.card-body > a").get(i);
                    link = element.attr("href");
                    name = element.text();

                    element = document.select("p.product-new-price").get(i);
                    price = element.text();
                    price = price.substring(0, price.length() - 6) + "," + price.substring(price.length() - 6, price.length());

                    element = document.select("form > input[name=product[]]").get(i - nr);
                    id = element.attr("value");

                    produsNou = new Produs(name, id, link, price);
                    dekker.run(produsNou);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

    public void parseCel() {

        Document document = null;
        final int noOfRecordsPerPage = 40;

        try {
            document = Jsoup.parse(new File("C:\\Users\\madalina.luca\\Desktop\\htmlCel\\file" + pagina + ".txt"),"UTF-8");
            //document = Jsoup.connect("http://www.cel.ro/casti/0a-" + pagina).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

         boolean scroollPage = document.select("a[ajaxp=true]").isEmpty();

         if ( scroollPage == false ) {
                for (int i = 0; i < noOfRecordsPerPage; i++) {
                    try {
                        Element element = document.select("a.productListing-data-b").get(i);
                        link = element.attr("href");
                        name = element.text();

                        element = document.select("b[itemprop=price]").get(i);
                        price = element.text()+" "+"lei";

                        element = document.select("div.stoc_list > span").get(i);
                        id = element.attr("id");
                        id = id.substring(1, id.length() - 2);

                        produsNou = new Produs(name, id, link, price);
                        dekker.run(produsNou);

                    } catch (IndexOutOfBoundsException e) {

                    }
                }

            }
        }
}



