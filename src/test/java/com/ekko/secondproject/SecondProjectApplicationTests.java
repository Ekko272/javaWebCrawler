package com.ekko.secondproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class SecondProjectApplicationTests {

    @Test
    void contextLoads() {
        String html = "<div class=\"club-card-contacts\">\n" +
                "    <h4 class=\"mb-0\">Contact Info</h4>\n" +
                "    <hr class=\"hr-grey mb-4\" />\n" +
                "    <p>\n" +
                "        <span class=\"far fa-envelope mr-1 fa-fw\"></span>\n" +
                "        <a href=\"mailto:deanza3dprinting@gmail.com\">deanza3dprinting@gmail.com</a>\n" +
                "    </p>\n" +
                "    <p class=\"mb-3 pl-2 club-contact\" style=\"border-left:5px solid #E5E5E5\">\n" +
                "        <strong>Max Gilleland</strong>\n" +
                "        <br />\n" +
                "        <span class=\"far fa-phone mr-1 fa-fw\"></span>\n" +
                "        (408) 864-5578\n" +
                "        <br />\n" +
                "        <span class=\"far fa-envelope mr-1 fa-fw\"></span>\n" +
                "        <a href=\"mailto:gillelandmax@fhda.edu\">gillelandmax@fhda.edu</a>\n" +
                "        <br />\n" +
                "    </p>\n" +
                "</div>";

        Document doc = Jsoup.parse(html);

        Element divElement = doc.selectFirst("div.club-card-contacts");
        if (divElement != null) {
            List<String> textList = new ArrayList<>();

            Elements allElements = divElement.getAllElements();
            for (Element element : allElements) {
                String text = element.ownText().trim();
                if (!text.isEmpty()) {
                    textList.add(text);
                }
            }


            for (String text : textList) {
                System.out.println("Extracted Text: " + text);
            }
        } else {
            System.out.println("No matching <div> element found.");
        }
    }

    @Test
    void testUrl() throws Exception{
        Document doc = Jsoup.parse(new URL("https://www.deanza.edu/clubs/club-list.html"), 1000);
        //使用标签选择器，获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    void testDom() throws Exception{
        Document doc = Jsoup.parse(new URL("https://www.deanza.edu/clubs/club-list.html"), 1000);
        List<Club> clubList = new ArrayList<>();

        //All club-card-title class div(Club Names)
        Elements clubNames = doc.getElementsByClass("club-card-title");
        //All club-car-description class div(Club Description)
        Elements clubDescriptions = doc.getElementsByClass("club-card-description");
        Elements contactsSmartWay = doc.getElementsByClass("club-card-contacts");
        Elements contacts = doc.getElementsByClass("far fa-envelope mr-1 fa-fw");
        Elements contactNames = doc.getElementsByTag("strong");
        Elements contactPhones = doc.getElementsByClass("far fa-phone mr-1 fa-fw");

        for(int i=0 ; i<clubNames.size();i++){
            String nameText = clubNames.get(i).text();
            System.out.println("Name of the club: " + nameText);
            String descriptionText = clubDescriptions.get(i).text();
            System.out.println("Description: " + descriptionText);
//            String clubEmail = contacts.get(i).nextElementSibling().text();
//            System.out.println("Club Email: " + clubEmail);
//            String contactName = contactNames.get(i).text();
//            System.out.println("Contact Name: " + contactName);
//            String contactPhone = contactPhones.get(i).text();
            List<String> textList = new ArrayList<>();

            //1. club email
            //2. contact phone
            //3. contact name
            //4. contact email
            Elements allElements = contactsSmartWay.get(i).getAllElements();
            for (Element element : allElements) {
                String text = element.ownText().trim();
                if (!text.isEmpty()) {
                    textList.add(text);
                }
            }

            //private String name;
            //    private String phoneNumber;
            //    private String email;

            for (int j = 1; j < textList.size();j++) {
                System.out.println("Extracted Text: " + textList.get(j));
                //Club club = new Club();
                //club.setClubEmail(textList.get(j));
                //System.out.println("这个是不是name： " + textList.get(j+2));
                //Contact contact = new Contact(textList.get(j+2))
            }
        }

//
//        Element nameElement = doc.selectFirst(".club-contact strong");
//            String contactName = nameElement.text();
//            System.out.println(contactName);
//            Element phoneElement = doc.selectFirst(".club-contact .far.fa-phone");
//            String phoneNumber = phoneElement.nextSibling().toString().trim();
//            System.out.println("phoneNumber: " + phoneNumber);







            Element secondEmailElement = doc.select(".club-contact a[href^=mailto]").last();
            String secondEmailAddress = secondEmailElement.text();
            System.out.println(secondEmailAddress);
            Elements aElements = doc.select(".club-card-links a");
            List<Link> links = new ArrayList<>();
            for (Element aElement : aElements) {
                String linkText = aElement.text();
                String linkHref = aElement.attr("href");
                links.add(new Link(linkText,linkHref));
                System.out.println("Link Text: " + linkText);
                System.out.println("Link Href: " + linkHref);
                System.out.println(); // Add an empty line for separation
            }
            //clubList1.add(new Club(nameText,descriptionText,emailAddress,links,"",new Contact(contactName,phoneNumber,secondEmailAddress)))
//

        Elements emailElement = doc.select("a[href^=mailto]");
        String emailAddress = emailElement.text();
        System.out.println(emailAddress);

    }

}
