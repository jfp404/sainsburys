package com.sainsbury.scraper;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Console {

    private final static String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public static Document getDocument(String link) throws MyException {
        if (link.isEmpty()) {
            throw new MyException("Link to scrapping page not found");
        }



        Document doc = null;
        try {
            doc = Jsoup.connect(link)//
                    .get();
        } catch (Exception e ){
            e.printStackTrace();
        }

        return doc;
    }

    public static Item getItem(Element product)  {

        Item item=null;
        try { //System.out.println(product.getElementsByClass("productNameAndPromotions").toString());
            if (product==null) {
                throw new MyException("Product is empty");
            }
            item = new Item();
            String title = product.select("a").text();
            String priceUnit = product.select("p.pricePerUnit").text().substring(1, 5);
            Elements links = product.getElementsByClass("productNameAndPromotions").select("a");
            String link = links.first().attr("abs:href");
            Document doc1 = getDocument(link);
            String description = doc1.selectFirst("div.productText").selectFirst("p").text();
            String energy = doc1.getElementsByClass("nutritionTable").select(" tbody > tr:nth-child(2) > td:nth-child(1)").text();

            item.setTitle(title);
            energy = (energy.length() > 0 ? energy.substring(0, energy.length() - 4) : "0");

            item.setEnergy(Integer.parseInt(energy));

            priceUnit = (priceUnit.length() > 0 ? priceUnit : "0");
            item.setPriceUnit(Float.parseFloat(priceUnit));

            item.setDescription((description.length() > 0 ? description : "N/A"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;

    }


    public static void main(String[] args) throws Exception {
        float VAT = 0.0f;
            if (args.length < 0) {

                throw new MyException("There is no VAT argument....java -jar  application.jar VAT");
            }
        try {
            Document doc = getDocument(url);
            Elements products = doc.getElementsByClass("product");

            Gson JSON = new GsonBuilder().setPrettyPrinting().create();

            ArrayList<Item> results = new ArrayList<>();

            /*Recover a list of product items*/

            List<Item> items = products.stream().map(item -> getItem(item)).collect(Collectors.toList());
            float total = items.stream().map(i -> i.getPriceUnit()).reduce(0f, (x, y) -> x + y);
            VAT = Float.parseFloat(args[0]);
            Total t = new Total();
            t.setGross(total);
            t.setVat((total * VAT) / 100);
            QueryResult qR = new QueryResult();
            qR.setResults(items);
            qR.setTotal(t);
            System.out.println(JSON.toJson(qR));
        }catch (MyException e){
            System.out.println(e.toString());
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
