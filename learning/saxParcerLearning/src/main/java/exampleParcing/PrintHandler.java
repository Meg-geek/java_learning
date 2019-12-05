package exampleParcing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.security.sasl.SaslException;

public class PrintHandler extends DefaultHandler {

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes){
        System.out.println("---startElement()---");
        System.out.println("uri: " + uri);
        System.out.println("localName: " + localName);
        System.out.println("qName: "+qName);
        System.out.println("attributes: " + attributes);
    }

    @Override
    public void endElement (String uri, String localName, String qName)
            throws SAXException
    {
        System.out.println("---endElement()---");
        System.out.println("uri: " + uri);
        System.out.println("localName: " + localName);
        System.out.println("qName: "+qName);
    }

    public void characters (char[] ch, int start, int length)
            throws SAXException
    {
        System.out.println("---characters()---");
        System.out.println(new String(ch, start, length));
    }

}
