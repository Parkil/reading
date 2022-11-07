package com.daekyo.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.w3c.dom.Node;

import java.util.*;
import java.util.stream.Collectors;

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

    public String convertHtml() {
        String style = attributeMap.entrySet().stream()
                .map(entry -> String.format("%s:%s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(" "));

        String opener = String.format("<%s style='%s'>", nodeName, style);
        String closer = String.format("</%s>", nodeName);

        String textHtml = Optional.ofNullable(textNodeList).orElse(Collections.emptyList())
                .stream().map(TextNode::convertText).collect(Collectors.joining());

        StringBuilder innerHtml = new StringBuilder();
        subNodeList.forEach(row -> innerHtml.append(row.convertHtml()));

        return opener + textHtml + innerHtml + closer;
    }
}