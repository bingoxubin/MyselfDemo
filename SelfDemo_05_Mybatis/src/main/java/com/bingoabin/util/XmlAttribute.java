package com.bingoabin.util;

/**
 * @author HanJunsheng
 * @date 2021/3/18
 * @copyright sankuai.com
 */
public enum XmlAttribute {

    ID("id"),
    REFID("refid"),
    COLLECTION("collection"),
    ITEM("item"),
    SEPARATOR("separator"),
    ;

    public String type;

    XmlAttribute(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "XmlAttribute{" +
                "type='" + type + '\'' +
                '}';
    }
}
