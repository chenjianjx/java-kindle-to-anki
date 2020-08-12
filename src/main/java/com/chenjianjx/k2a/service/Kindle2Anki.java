package com.chenjianjx.k2a.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Kindle2Anki {

    public String convert(String kindleHighlights) throws IOException {
        List<AnkiCard> ankiCards = parse(kindleHighlights);
        System.out.println(ankiCards.size() + " of anki cards in total");
        return cardsToCsv(ankiCards);

    }

    private String cardsToCsv(List<AnkiCard> ankiCards) throws IOException {
        StringWriter out = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL)) {
            for (AnkiCard ankiCard : ankiCards) {
                printer.printRecord(ankiCard.getFront(), ankiCard.getBack());
            }
        }
        return out.toString();
    }

    private List<AnkiCard> parse(String kindleHighlights) {
        Document doc = Jsoup.parse(kindleHighlights);
        Elements noteTexts = doc.select(".noteText");

        List<AnkiCard> ankiCards = new ArrayList<>();
        for (Element note : noteTexts) {
            String text = StringUtils.trimToNull(note.ownText());
            if (text == null) {
                continue;
            }
            AnkiCard card = new AnkiCard();
            card.setFront(text);
            card.setBack(null);
            ankiCards.add(card);
        }
        return ankiCards;
    }
}
