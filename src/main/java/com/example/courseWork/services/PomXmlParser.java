package com.example.courseWork.services;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;


import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class PomXmlParser {
    private File xmlFile;

    public PomXmlParser() {
        this.xmlFile = new File("./pom.xml");
    }

    public String getData() throws IOException, SAXException, ParserConfigurationException {
        String str = null;
//        File xmlFile = new File("courseWork/target/pom.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        System.out.println(xmlFile.toString());

        // Получаем корневой элемент (обычно это <project>)
        Element root = document.getDocumentElement();

        // Извлекаем информацию о версии проекта
        String version = getElementValue(root, "version");
        String name = getElementValue(root, "name");
        System.out.println("{\nProject Version: " + version + "\n"
        + "Name: " + name + "\n}");
        str = "{\nProject Version: " + version + "\n"
                + "Name: " + name + "\n}";

        // Пример получения зависимостей
//        NodeList dependencies = ((org.w3c.dom.Element) root).getElementsByTagName("dependency");
//        for (int i = 0; i < dependencies.getLength(); i++) {
//            Element dependency = (Element) dependencies.item(i);
//            String name = getElementValue(dependency, "name");
//            String depVersion = getElementValue(dependency, "version");
//            System.out.println("{\nname: " + name + ",\n" + "version: " + depVersion + "\n}");
//            str = "{\nname: " + name + ",\n" + "version: " + depVersion + "\n}";
//        }

        return str;
    }

    private static String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);

        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }

}
