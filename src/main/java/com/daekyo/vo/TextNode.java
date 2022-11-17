package com.daekyo.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TextNode {
    private final String plainText;
    private final QBankNode textDecoNode;

    public String convertText() {
        String opener = "";
        String closer = "";

        if(textDecoNode != null) {
            String style = textDecoNode.getAttributeMap().entrySet().stream()
                    .map(entry -> String.format("%s:%s", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(" "));

            opener = String.format("<%s style='%s'>", textDecoNode.getNodeName(), style);
            closer = String.format("</%s>", textDecoNode.getNodeName());
        }

        return opener + plainText + closer;
    }
}
