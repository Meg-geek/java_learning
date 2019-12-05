package exampleParcing;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ExampleParcer {
    public static void main(String[] args){
        try{
            SAXParser saxParser = SAXParserFactory
                    .newInstance()
                    .newSAXParser();
            DefaultHandler handler = new PrintHandler();
            saxParser.parse(new File("src\\resources\\example.xml"), handler);
        } catch(SAXException| ParserConfigurationException | IOException ex){
            ex.printStackTrace();
        }
    }
}
