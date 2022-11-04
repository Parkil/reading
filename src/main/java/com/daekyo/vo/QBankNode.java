package com.daekyo.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
public class QBankNode {
    private final String nodeName;

    private final List<TextNode> textNodeList;

    private final Node xmlNode;

    private final List<QBankNode> subNodeList = new ArrayList<>();

    private final Map<String, String> attributeMap = new HashMap<>();

    public QBankNode(String nodeName, List<TextNode> textNodeList, Node xmlNode) {
        this.nodeName = nodeName;
        this.textNodeList = textNodeList;
        this.xmlNode = xmlNode;
    }

    public void setAttribute(String key, String value) {
        attributeMap.put(key, value);
    }

    public boolean isRootNode() {
        return xmlNode.getParentNode().getNodeName().equals("#document");
    }
}