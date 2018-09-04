package summary.knowledge.common.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.Properties;

public class XMLDOM4JUtil {

    public static String transformToString(Document document) {
        DOMSource domSource = new DOMSource(document);
        return transformToString(domSource);
    }

    public static String transformToString(Node node) {
        DOMSource domSource = new DOMSource(node);
        return transformToString(domSource);
    }

    public static String transformToString(DOMSource domsource) {
        StreamResult streamResult = new StreamResult();
        Transformer transformer = getTransformer();
        try {
            transformer.transform(domsource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return streamResult.getWriter().toString();
    }

    public static DOMSource transformToDOMSource(String xml) {
        Document document = transformToDocument(xml);
        return new DOMSource(document);
    }

    public static Document transformToDocument(String xml) {
        StringReader stringReader = new StringReader(xml);
        InputSource inputSource = new InputSource(stringReader);
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            stringReader.close();
        }
    }

    private static Transformer getTransformer() {
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();

        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        Properties properties = transformer.getOutputProperties();
        properties.setProperty(OutputKeys.ENCODING, "utf-8");
        properties.setProperty(OutputKeys.METHOD, "xml");
        properties.setProperty(OutputKeys.VERSION, "1.0");
        properties.setProperty(OutputKeys.INDENT, "no");
        return transformer;
    }

    public static String getNodeValue(Node node,String expression){
        try {
            return (String)getXpath().evaluate(expression,node, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static NodeList getNodeList(Node node,String expression){
        try {
            return (NodeList) getXpath().evaluate(expression,node, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Node getNode(Node node,String expression){
        try {
            return (Node)getXpath().evaluate(expression,node, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getValue(Node node,String expression,QName qName){
        try {
            return (T)getXpath().evaluate(expression,node,qName);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private static XPath getXpath(){
        return XPathFactory.newInstance().newXPath();
    }
}
