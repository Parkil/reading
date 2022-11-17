package com.daekyo;

import com.daekyo.vo.TextNode;
import com.daekyo.vo.QBankNode;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {

    private static QBankNode convertNode(Node node) {
        String nodeName = node.getNodeName();
        QBankNode qBankNode = new QBankNode(nodeName, convertTextNode(node), node);

        NamedNodeMap attrMap = node.getAttributes();
        for(int i = 0 ; i < attrMap.getLength() ; i++) {
            Node mapNode = attrMap.item(i);
            qBankNode.setAttribute(mapNode.getNodeName(), mapNode.getNodeValue());
        }

        return qBankNode;
    }

    private static List<TextNode> convertTextNode(Node node) {
        if(!node.getNodeName().equals("CHAR")) {
            return Collections.emptyList();
        }

        NodeList textNodeList = node.getChildNodes();

        List<TextNode> convertTextNodeList = new ArrayList<>();
        for(int i = 0 ; i< textNodeList.getLength() ; i++) {
            Node textNode = textNodeList.item(i);

            String plainText = null;
            QBankNode textQBankNode = null;

            if(textNode.getNodeName().equals("#text")) { // 순수한 텍스트로만 구성되어 있는 경우
                plainText = textNode.getNodeValue();
            }else { // qBank 태그 안에 텍스트가 저장되어 있는 경우
                plainText = textNode.getTextContent();
                textQBankNode = convertNode(textNode);
            }

            convertTextNodeList.add(new TextNode(plainText, textQBankNode));
        }

        return convertTextNodeList;
    }

    public static void main(String[] args) throws Exception{
        String xml="<TEXTBOX borderleft=\"solid #000000 1px\" borderright=\"solid #000000 1px\" borderbottom=\"solid #000000 1px\" bordertop=\"solid #000000 1px\"><TEXT align=\"center\"><CHAR size=\"15.6\"><BOLD>할아버지와 노오란 머리핀</BOLD></CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\"> </CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">“오늘부터 추위가 풀릴 거야!”</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">아침부터 어머니는 식구들을 붙잡고 이 말만 계속 반복했습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">어머니의 말에 식구들의 반응은 저마다 달랐습니다. 무뚝뚝한 아버지는 “알았어.”라고 했고, 중학생인 오빠는 “한 번만 더 들으면 열 번이에요.” 하며 능청을 떨었습니다. 나미는 “이상으로 나미네 일기 예보를 마치겠습니다.” 하며 애교를 부렸습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">나미는 창밖을 내다보았습니다. 아랫동네로 밝은 햇살이 넓게 퍼지고 있었습니다. 산 밑에 있는 나미네 집까지 햇살이 오려면 아직도 멀었습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">나미는 소파에 털썩 주저앉으며 어제 읽다가 둔 동화책을 집어 들고 읽기 시작했습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">시간이 사르르 흘러갔습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">시계 속에 들어앉은 뻐꾸기가 열 번 울 때까지 나미는 책에서 눈을 떼지 않았습니다. 그러다가 문득 소영이 생각이 났습니다. 나미는 책에서 눈을 떼지 않은 채 전화번호를 눌렀습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">길게 신호음이 울렸습니다. 나미가 “여보세요.”라고 말하려는데 갑자기 저쪽에서 먼저 말을 했습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">“전화 잘못 걸었수다.”</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">연세 지긋한 할아버지의 목소리였습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">“365, 7891번 맞지요?”</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">나미는 또박또박 전화번호를 말했습니다. 그러자 할아버지가 더듬거리며 전화번호를 일러 주었습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">“여기는 665, 7891번이오.”</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">“죄송합니다.”</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">나미는 기어들어 가는 목소리로 사과를 했습니다. 전화가 ‘딸깍’ 하고 끊어졌습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">아무래도 이상했습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">누구나 전화가 걸려 오면 상대방의 말을 들은 다음, 잘못 걸려 온 전화라면 그때 “<IMAGE path=\"/bank/header/2140/214005/_hcv_hr214005_1.gif\" width=\"121\" height=\"23\"/>”라고 말합니다. 그런데 할아버지는 나미가 뭐라고 말을 하기도 전에 전화를 잘못 걸었다고 말했습니다.</CHAR></TEXT><TEXT textindent=\"13\"><CHAR size=\"15.6\">나미는 다시 한번 전화를 걸어 보고 싶었습니다. 살살 전화번호를 눌렀습니다. 신호가 갔습니다. ㉡</CHAR><CHAR size=\"15.6\"><UNDERLINE>나미는 꼴깍 침을 삼켰습니다.</UNDERLINE></CHAR><CHAR size=\"15.6\"> 잠시 후에 조금 전 그 할아버지의 음성이 나미의 귀를 파고들었습니다.</CHAR></TEXT></TEXTBOX>";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        NodeList nodeList = (NodeList)xpath.evaluate("//*", doc, XPathConstants.NODESET);

        List<QBankNode> tempList = new ArrayList<>();

        for(int i = 0 ; i< nodeList.getLength() ; i++) {
            Node node = nodeList.item(i);
            QBankNode qBankNode = convertNode(node);
            tempList.add(qBankNode);

            Node parentNode = node.getParentNode();
            /*
            exclude 조건
                #document : XML 최상위 노드
                CHAR : 하위에는 text + text 장식 node 만 존재하기 때문에 여기에서 계층구조를 구성하지 않음
             */
            if(!parentNode.getNodeName().equals("#document") && !parentNode.getNodeName().equals("CHAR")) {
                Optional<QBankNode> parentNodeOption = tempList.stream()
                        .filter(row -> row.getXmlNode().equals(parentNode))
                        .reduce((prev, next) -> prev);

                parentNodeOption.ifPresent(parent -> parent.getSubNodeList().add(qBankNode));
            }
        }

        QBankNode rootNode = tempList.stream().filter(QBankNode::isRootNode).collect(Collectors.toList()).get(0);

        System.out.println(rootNode.convertHtml());

        /*
            xml -> html tag 변환정보
            속성 -> html 속성 변환정보
            속성 -> style 속성 변환정보
         */
    }
}