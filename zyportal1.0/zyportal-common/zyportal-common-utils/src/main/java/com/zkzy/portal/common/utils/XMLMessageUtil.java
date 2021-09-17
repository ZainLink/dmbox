package com.zkzy.portal.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zkzy.portal.common.model.WeiXinTextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WWH on 2018-06-18 0018.
 */
public class XMLMessageUtil {

    /**
     * xml转为map集合
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        //System.out.println("进xmlToMap");

        Map<String,String> map=new HashMap<String, String>();

        //dom4j saxReader解析xml
        SAXReader reader=new SAXReader();

        //从request中获取输入流
        InputStream ins = request.getInputStream();

        //解析xml文档
        Document doc =reader.read(ins);

        //获得根节点
        Element root = doc.getRootElement();

        //List存储  遍历
        List<Element> list=  root.elements();

        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();
        //  System.out.println(map.toString());
        return map;
    }

    /**
     * 将文本消息对象转换为xml
     * @param textMessage
     * @return
     */

    //xtream jar包 ->  XStrem类提供对象转xml
    public static String textMessageToXml(WeiXinTextMessage textMessage){
        // System.out.println("进textMessageToXml");
        /**
         * new StaxDriver()这个很重要 没有这个就错了
         * XStream xstream=new XStream(new StaxDriver());
         */
        XStream xstream=new XStream(new StaxDriver());
        xstream.alias("xml", textMessage.getClass());
        // System.out.println("textMessage");
        return xstream.toXML(textMessage);
    }
}
