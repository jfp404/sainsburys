package com.sainsbury.scraper;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class console {

    final String authUser = "ferperja";
    final String authPassword = "cds750MBS";

    public static void main(String[] args) {
        System.out.println( "Hello World!" );
        try {


//            System.setProperty("http.proxyHost", "proxy.jcyl.es");
//            System.setProperty("http.proxyPort", "80");
            Proxy proxy = new Proxy (
                    Proxy.Type.HTTP, InetSocketAddress.createUnresolved("proxy.jcyl.es",80)
            );

            String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
            int VAT = 20;
            Document doc = Jsoup.connect(url)//
                           .proxy(proxy) //
                           .get();
            System.out.printf("Title: %s\n", doc.title());
            System.out.println( "Get realizado" );
            Elements products = doc.getElementsByClass("product");
            float total = 0.0f;
            for (Element product:products) {
                //System.out.println(product.getElementsByClass("productNameAndPromotions").toString());
                String title = product.select("a").text();
                String priceUnit = product.select("p.pricePerUnit").text().substring(1,5);
                total += Float.parseFloat(priceUnit);
                Elements links = product.getElementsByClass("productNameAndPromotions").select("a");
                String link = links.first().attr("href");
                String product_link = link.substring(link.lastIndexOf('/')+1);
                String url_base = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/";
                Document doc1 = Jsoup.connect(url_base+product_link).proxy(proxy).get();
                String description = doc1.selectFirst("div.productText").selectFirst("p").text();
                String energy = doc1.getElementsByClass("nutritionTable").select(" tbody > tr:nth-child(2) > td:nth-child(1)").text();
                //System.out.println("Title: " + doc1.title());
                System.out.println("--------------------------------------------------");
                System.out.println("Title "+ title);
                System.out.println("Calorias: "+ (energy.length()>0 ? energy.substring(0, energy.length()-4) : "N/A")) ;
                System.out.println("Price per unit: "+ priceUnit);
                System.out.println("Description: "+ (description.length()>0 ? description : "N/A"));
            }
            System.out.println("Total: " + total);
            System.out.println("Vat: " + (total*100)/VAT);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
