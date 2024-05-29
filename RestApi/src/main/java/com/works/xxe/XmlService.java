package com.works.xxe;

import com.works.models.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class XmlService {

    public List<Currency> xml() {
        List<Currency> currencyList = new ArrayList<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String stData = Jsoup.connect(url).ignoreContentType(true).timeout(15000).get().toString();
            Document doc = Jsoup.parse(stData, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            for (Element element : elements) {
                String CurrencyName = element.getElementsByTag("CurrencyName").text();
                String ForexBuying = element.getElementsByTag("ForexBuying").text();
                String ForexSelling = element.getElementsByTag("ForexSelling").text();
                Currency c = new Currency(CurrencyName, ForexBuying, ForexSelling);
                currencyList.add(c);
            }
        }catch (Exception ex) {
            System.err.println("xml Error :" + ex.getMessage());
        }
        return currencyList;
    }

}
