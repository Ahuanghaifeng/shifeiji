package com.sfj.sfj.utils;


import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlUtil {
    private static XmlUtil xmlUtil;

    public static XmlUtil getInstence() {
        if (xmlUtil == null) {
            xmlUtil = new XmlUtil();
        }
        return xmlUtil;
    }

    public String saxXml(Context context, String province, String city) {
        String text = "";
        try {
            //传入文件名：language.xml；用来获取流
            InputStream is = context.getAssets().open("province_data.xml");
            //首先创造：DocumentBuilderFactory对象
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            //获取：DocumentBuilder对象
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            //将数据源转换成：document 对象
            Document document = dBuilder.parse(is);
            //获取根元素
            Element element = (Element) document.getDocumentElement();
            //获取子对象的数值 读取lan标签的内容
            NodeList nodeList = element.getElementsByTagName("province");
            for (int i = 0; i < nodeList.getLength(); i++) {
                //获取对应的对象
                Element lan = (Element) nodeList.item(i);
                //lan.getAttribute("id") 获取id的值
                String provinceZipcode = lan.getAttribute("zipcode");
                String provinceName = lan.getAttribute("name");
                if (provinceZipcode.equals(province)) {
                    text = provinceName;
                }
                NodeList nodeListCity = lan.getElementsByTagName("city");
                //获取city标签下的内容
                for (int j = 0; j < nodeListCity.getLength(); j++) {
                    Element cityLan = (Element) nodeListCity.item(j);
                    //获取标签下的内容
                    String cityZipcode = cityLan.getAttribute("zipcode");
                    String cityName = cityLan.getAttribute("name");
                    if (cityZipcode.equals(city)) {
                        text += cityName;
                        return text;
                    }
                }

            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return "";
    }


    public  String pullXMl(Context context, String province, String city) {

        String text = "";
        try {
            InputStream is = context.getAssets().open("province_data.xml");
            XmlPullParser parser = Xml.newPullParser();//获取pull解析器
            parser.setInput(is, "utf-8");//设置输入流的编码方式
            int eventType = parser.getEventType();//得到第一个事件类型
            while (eventType != XmlPullParser.END_DOCUMENT) {//直到文档结束一直循环处理
               if (eventType == XmlPullParser.START_TAG) {//标签开始
                   String tagName = parser.getName();//获取标签名称
                    if (tagName != null&& !TextUtils.isEmpty(tagName)) {
                        if (tagName.equals("province")) {
                            String provinceName = parser.getAttributeValue(0);
                            String provinceZipcode = parser.getAttributeValue(1);
                            if (provinceZipcode.equals(province)) {
                                text = provinceName;
                            }
                        } else if (tagName.equals("city")) {
                            String cityName = parser.getAttributeValue(0);
                            String cityZipcode = parser.getAttributeValue(1);
                            if (cityZipcode.equals(city)) {
                                text += cityName;
                                return text;
                            }

                        }
                    }
                }
                eventType = parser.next();
            }

        } catch (IOException  |NullPointerException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return "";
    }


}