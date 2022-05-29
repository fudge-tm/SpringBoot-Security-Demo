package cn.edu.lingnan.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;


public class XmlHandler extends DefaultHandler {
    private StringBuffer stringBuffer = new StringBuffer();
    Map<String, String> map = new HashMap();


    public Map<String, String> getMap() {
        return map;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuffer.delete(0, stringBuffer.length());
    }

    //    一个元素读完了，把读到的内容保存起来
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        map.put(qName, stringBuffer.toString());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuffer.append(ch, start, length);

    }
}
