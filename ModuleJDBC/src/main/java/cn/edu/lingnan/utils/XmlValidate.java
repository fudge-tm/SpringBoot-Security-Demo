package cn.edu.lingnan.utils;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidate {
    //    用xsd文件验证，xml文件是否有效
    public static boolean validate(String xmlName, String xsdName) {
        boolean flag = false;

        try {
            //    1.    创建模式工厂
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
//        2.通过.xsd文件创建模式Schema
            File file = new File(xsdName);
            Schema schema = schemaFactory.newSchema(file);
//            3.由模式创建验证器Validator
            Validator validator = schema.newValidator();
//            4.使用验证器验证xml文件
            Source source = new StreamSource(xmlName);
            validator.validate(source);
            flag = true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }


        return flag;
    }
}
