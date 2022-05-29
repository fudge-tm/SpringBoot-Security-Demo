package cn.edu.lingnan.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;


public class XmlTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String xmlPath = basePath + "database.conf.xml";
        String xsdPath = basePath + "database.conf.xsd";
        System.out.println(XmlValidate.validate(xmlPath, xsdPath));
        Map<String, String> map = XmlParser.parse(xmlPath);
        System.out.println(map);


    }

    @Test
    public void validateTest() {

    }
}
