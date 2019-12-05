package exampleParcing;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.security.sasl.SaslException;

public class PrintHandler extends DefaultHandler {
    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        System.out.println("---startPrefixMapping()---");
        System.out.println("prefix: " + prefix);
        System.out.println("uri: " + uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        System.out.println("---endPrefixMapping()---");
        System.out.println("prefix: " + prefix);
    }

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
        System.out.println("ch: " + new String(ch));
        System.out.println("start: " + start);
        System.out.println("length: " + length);
        System.out.println(new String(ch, start, length));
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        System.out.println("---ignorableWhiteSpaces--");
        System.out.println(new String(ch, start, length));
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        System.out.println("---processingInstruction---");
        System.out.println("target: " + target);
        System.out.println("data: " + data);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        System.out.println("---skippedEntity---");
    }

}
