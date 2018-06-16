package myJunit.util;

import com.google.common.base.Strings;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/16 21:30
 * @Description: xml工具类
 */
public class XmlUtils {

    private static SAXReader saxReader = new SAXReader();

    //私有化构造器
    private XmlUtils() {
    }

    /**
     * 解析xml文件
     *
     * @param filePath xml文件的路径
     */
    public static void analysisXml(String filePath) {

        try {
            if (Strings.isNullOrEmpty(filePath)){
                return;
            }

            File xmlFile =  new File(filePath);
            if (!xmlFile.exists()) {
                return;
            }

            Document document = saxReader.read(xmlFile);
            Element rootElement = getRootElement(document);//root根节点
            parseNode(rootElement);
        } catch (DocumentException e) {
            e.printStackTrace();
            //TODO 解析失败日志打印，并且要把错误的栈信息打印出来
        }

    }

    /**
     * 解析节点数据
     *
     * @param element
     */
    public static void parseNode(Element element) {

        System.out.println("当前获取的节点是：" + element.getName());
        //当前节点的属性(注意并不是子节点)
        List<Attribute> attributeList = element.attributes();

        for (Attribute attr : attributeList) {
            System.out.println(attr.getName() + ":" + attr.getValue());
        }

        if (!Strings.isNullOrEmpty(element.getTextTrim())) {
            //TODO 这里要将读取的值进行储存
            System.out.println(element.getTextTrim());
        }

        //获取当前节点的所有子节点，并进行递归解析
        List<Element> elementList = element.elements();
        for (Element ele : elementList) {
            parseNode(ele);
        }

    }

    /**
     * 获取当前节点的所有子节点
     * @param element
     * @return 当输入的节点为空节点时，返回空集合
     */
    public static List<Element> getChildrenNode (Element element){

        if (element == null) {
           return Collections.EMPTY_LIST;//只读
        }
        return element.elements();

    }

    /**
     * 获取xml的根节点
     * @param document
     * @return
     */
    public static Element getRootElement (Document document){

        if (document == null) {
            return  null;
        }
        return document.getRootElement();

    }

}
