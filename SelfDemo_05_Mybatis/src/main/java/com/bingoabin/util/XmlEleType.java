package com.bingoabin.util;

/**
 * @author HanJunsheng
 * @date 2021/3/18
 * @copyright sankuai.com
 */
public enum XmlEleType {
    SQL("sql"),
    SELECT("select"),
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete"),
    FOREACH("foreach"),
    INCLUDE("include"),
    ;

    public String type;

    XmlEleType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "XmlElement{" +
                "type='" + type + '\'' +
                '}';
    }
}
