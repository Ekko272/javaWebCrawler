package com.ekko.secondproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    void crawler() throws Exception{
        Document doc = Jsoup.parse(new URL("https://www.deanza.edu/clubs/club-list.html"), 1000);
        List<Club> clubList = new ArrayList<>();

        //All club-card-title class div(Club Names)
        Elements clubNames = doc.getElementsByClass("club-card-title");
        //All club-car-description class div(Club Description)
        Elements clubDescriptions = doc.getElementsByClass("club-card-description");
        Elements contactsSmartWay = doc.getElementsByClass("club-card-contacts");

        for(int i=0 ; i<clubNames.size();i++){
            String nameText = clubNames.get(i).text();
            String descriptionText = clubDescriptions.get(i).text();

            Club club = new Club();
            club.setName(nameText);
            club.setDescription(descriptionText);
            club.setActivityDate("");
            club.setLinks(null);

            List<String> textList = new ArrayList<>();
            Elements allElements = contactsSmartWay.get(i).getAllElements();
            for (Element element : allElements) {
                String text = element.ownText().trim();
                if (!text.isEmpty()) {
                    textList.add(text);
                }
            }
            club.setClubEmail(textList.get(1));

            int index = (textList.size() - 2) / 3;
            for(int j = 0; j < index ;++j){
                Contact contact = new Contact();
                contact.setPhoneNumber(textList.get(j*3 + 2));
                contact.setName(textList.get(j*3 + 3));
                contact.setEmail(textList.get(j*3 + 4));
                club.addContact(contact);
            }

            String clubstring = club.toString();
            System.out.println("Club information: " + clubstring);
            clubList.add(club);

        }

        Elements aElements = doc.select(".club-card-links a");
        List<Link> links = new ArrayList<>();
        for (Element aElement : aElements) {
            String linkText = aElement.text();
            String linkHref = aElement.attr("href");
            links.add(new Link(linkText,linkHref));
            System.out.println("Link Text: " + linkText);
            System.out.println("Link Href: " + linkHref);
            System.out.println();
        }

    }
}
