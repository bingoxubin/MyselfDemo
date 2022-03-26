package com.bingoabin.plugins;

import com.bingoabin.util.VinciMbgPluginUtils;
import com.bingoabin.util.XmlAttribute;
import com.bingoabin.util.XmlEleType;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class VinciSqlMapsMethodPlugin extends PluginAdapter {

    private static final String LIST_TYPE = "List<%s>";

    private static final String INSERT_INTO = "insert into";
    private static final String VALUES = "values";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        XmlElement parentElement = document.getRootElement();

        parentElement.addElement(new TextElement(VinciMbgPluginUtils.ENTER_STR));
        parentElement.addElement(new TextElement(String.format(VinciMbgPluginUtils.TITLE_FORMAT, "自定义生成方法代码")));
        parentElement.addElement(genInsertElement(introspectedTable));

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private XmlElement genInsertElement(IntrospectedTable introspectedTable) {
        XmlElement insert = new XmlElement(XmlEleType.INSERT.type);
        String id = VinciMbgPluginUtils.isWithBlobs(introspectedTable) ?
                VinciMbgPluginUtils.INSERT_BATCH_BLOB : VinciMbgPluginUtils.INSERT_BATCH;
        insert.addAttribute(new Attribute(XmlAttribute.ID.type, id));
        insert.addElement(new TextElement(INSERT_INTO));
        insert.addElement(new TextElement(introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        insert.addElement(new TextElement("("));

        XmlElement include = new XmlElement(XmlEleType.INCLUDE.type);
        include.addAttribute(new Attribute(XmlAttribute.REFID.type, VinciMbgPluginUtils.NO_ID_NAME_FIELD));
        insert.addElement(include);

        if (VinciMbgPluginUtils.isWithBlobs(introspectedTable)) {
            insert.addElement(new TextElement(","));
            XmlElement includeBlob = new XmlElement(XmlEleType.INCLUDE.type);
            includeBlob.addAttribute(new Attribute(XmlAttribute.REFID.type, VinciMbgPluginUtils.NO_ID_NAME_FIELD_BLOB));
            insert.addElement(includeBlob);
        }

        insert.addElement(new TextElement(")"));
        insert.addElement(new TextElement(VALUES));

        XmlElement foreach = new XmlElement(XmlEleType.FOREACH.type);
        foreach.addAttribute(new Attribute(XmlAttribute.COLLECTION.type, VinciMbgPluginUtils.RECORDS));
        foreach.addAttribute(new Attribute(XmlAttribute.ITEM.type, VinciMbgPluginUtils.RECORD));
        foreach.addAttribute(new Attribute(XmlAttribute.SEPARATOR.type, ","));
        foreach.addElement(new TextElement("("));

        XmlElement foreachInclude = new XmlElement(XmlEleType.INCLUDE.type);
        foreachInclude.addAttribute(new Attribute(XmlAttribute.REFID.type, VinciMbgPluginUtils.NO_ID_PROPERTIES_RECORD));
        foreach.addElement(foreachInclude);

        if (VinciMbgPluginUtils.isWithBlobs(introspectedTable)) {
            foreach.addElement(new TextElement(","));
            XmlElement foreachIncludeBlob = new XmlElement(XmlEleType.INCLUDE.type);
            foreachIncludeBlob.addAttribute(new Attribute(XmlAttribute.REFID.type, VinciMbgPluginUtils.NO_ID_PROPERTIES_BLOB_RECORD));
            foreach.addElement(foreachIncludeBlob);
        }

        foreach.addElement(new TextElement(")"));
        insert.addElement(foreach);

        return insert;
    }

    @Override
    public boolean clientGenerated(
            Interface interfaze,
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {

        if (VinciMbgPluginUtils.isWithBlobs(introspectedTable)) {
            interfaze.addMethod(generateInsertBatchWithBlob(introspectedTable));
        } else {
            interfaze.addMethod(generateInsertBatch(introspectedTable));
        }
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    private Method generateInsertBatch(IntrospectedTable introspectedTable) {
        Method m = new Method(VinciMbgPluginUtils.INSERT_BATCH);
        m.setReturnType(FullyQualifiedJavaType.getIntInstance());
        String baseRecordType = introspectedTable.getBaseRecordType();
        String type = String.format(LIST_TYPE, baseRecordType);
        Parameter param = new Parameter(new FullyQualifiedJavaType(type),
                VinciMbgPluginUtils.RECORDS, VinciMbgPluginUtils.RECOEDS_ANNOTATION);
        m.addParameter(param);
        return m;
    }

    private Method generateInsertBatchWithBlob(IntrospectedTable introspectedTable) {
        Method m = new Method(VinciMbgPluginUtils.INSERT_BATCH_BLOB);
        m.setReturnType(FullyQualifiedJavaType.getIntInstance());
        String baseRecordType = introspectedTable.getRecordWithBLOBsType();
        String typeStr = String.format(LIST_TYPE, baseRecordType);
        Parameter param = new Parameter(new FullyQualifiedJavaType(typeStr),
                VinciMbgPluginUtils.RECORDS, VinciMbgPluginUtils.RECOEDS_ANNOTATION);
        m.addParameter(param);
        return m;
    }
}
