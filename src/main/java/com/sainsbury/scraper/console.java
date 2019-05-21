package com.sainsbury.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class console {

    final String authUser = "ferperja";
    final String authPassword = "cds750MBS";

    public static void main(String[] args) {
        System.out.println( "Hello World!" );
        try {


//            System.setProperty("http.proxyHost", "proxy.jcyl.es");
//            System.setProperty("http.proxyPort", "80");

            String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
            Document doc = Jsoup.connect(url).get();
            System.out.printf("Title: %s\n", doc.title());
            System.out.println( "Get realizado" );
            Elements products = doc.getElementsByClass("product");
            for (Element product:products) {
                //System.out.println(product.getElementsByClass("productNameAndPromotions").toString());
                System.out.println("Descripcion: " + product.select("a").text());
                System.out.println("Price per unit: "+ product.select("p.pricePerUnit").text().);
                Elements links = product.getElementsByClass("productNameAndPromotions").select("a");
                String link = links.first().attr("href");
                String product_link = link.substring(link.lastIndexOf('/')+1);
                String url_base = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/";
                Document doc1 = Jsoup.connect(url_base+product_link).get();
                String energy = doc1.getElementsByClass("nutritionTable").select(" tbody > tr:nth-child(2) > td:nth-child(1)").text();

                //System.out.println("Title: " + doc1.title());
                System.out.println("Calorias: "+ energy);
                //System.out.println("Calorias: "+ doc1.select("#information > productcontent > htmlcontent > div:nth-child(4) > div > div > table > tbody > tr:nth-child(2) > td:nth-child(1)"));


            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
