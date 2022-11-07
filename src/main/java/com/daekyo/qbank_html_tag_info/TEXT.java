package com.daekyo.qbank_html_tag_info;


import java.util.HashMap;
import java.util.Map;

public class TEXT {
    private final String NODE_NAME = "TEXT";
    private final String HTML_TAG_NAME = "div";

    private final Map<String, String> attrToHtmlAttrMap = Map.of(
            "marginleft", "marginleft",
            "marginright", "marginright",
            "textindent", "textindent",
            "align", "align"
    );

    private final Map<String, String> attrToStyleMap = new HashMap<>();
}