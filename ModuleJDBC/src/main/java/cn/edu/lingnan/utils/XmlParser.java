package cn.edu.lingnan.utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class XmlParser {
    public static Map<String, String> parse(String xmlName) {
        Map<String, String> map = null;
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
//            实例化一个saxParserFactory对象
            SAXParser saxParser = saxParserFactory.newSAXParser();
//         通过factory获得一个SAXParser对象，即SAX解析器
            File file = new File(xmlName);
//            SAXParser对象调用parse方法解析XML文件
            XmlHandler xmlHandler = new XmlHandler();//事件源
            saxParser.parse(file, xmlHandler);//事件处理器
            map = xmlHandler.getMap();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
