package com.bingoabin.plugins;

import com.bingoabin.util.VinciMbgPluginUtils;
import com.bingoabin.util.XmlAttribute;
import com.bingoabin.util.XmlEleType;
import org.apache.commons.collections.CollectionUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VinciSqlMapsFieldPlugin extends PluginAdapter {

    private static final String FIELD_TITLE = "<!--  === field === -->";
    private static final String RECODE_TITLE = "<!--  === record field === -->";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        XmlElement parentElement = document.getRootElement();

        parentElement.addElement(new TextElement(VinciMbgPluginUtils.ENTER_STR));
        parentElement.addElement(new TextElement(String.format(VinciMbgPluginUtils.TITLE_FORMAT, "自定义生成字段代码")));

        List<Element> elements;
        if (VinciMbgPluginUtils.isWithBlobs(introspectedTable)) {
            elements = genColumnsWithBlob(introspectedTable);
        } else {
            elements = genColumnsWithoutBlob(introspectedTable);
        }

        if (!CollectionUtils.isEmpty(elements)) {
            elements.forEach(parentElement::addElement);
        }

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private List<Element> genColumnsWithoutBlob(IntrospectedTable introspectedTable) {
        List<Element> result = new ArrayList<>();

        result.add(new TextElement(FIELD_TITLE));
        List<IntrospectedColumn> columns = introspectedTable.getNonPrimaryKeyColumns();

        List<String> fieldNameList = columns.stream()
                .map(MyBatis3FormattingUtilities::getEscapedColumnName)
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_NAME_FIELD, fieldNameList));

        List<String> fieldProperties = columns.stream()
                .map(MyBatis3FormattingUtilities::getParameterClause)
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_PROPERTIES, fieldProperties));

        result.add(new TextElement(RECODE_TITLE));

        List<String> fieldNameRecords = columns.stream()
                .map(e -> VinciMbgPluginUtils.RECORD_PREFIX + MyBatis3FormattingUtilities.getEscapedColumnName(e))
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_NAME_FIELD_RECORD, fieldNameRecords));

        List<String> fieldPropertiesRecords = columns.stream()
                .map(e -> MyBatis3FormattingUtilities.getParameterClause(e, VinciMbgPluginUtils.RECORD_PREFIX))
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_PROPERTIES_RECORD, fieldPropertiesRecords));

        return result;
    }

    private List<Element> genColumnsWithBlob(IntrospectedTable introspectedTable) {
        List<Element> result = new ArrayList<>();

        Map<String, IntrospectedColumn> nonBLOBColumnsMap = new HashMap<>();
        Map<String, IntrospectedColumn> blobColumnsMap = new HashMap<>();
        for (IntrospectedColumn nonBLOBColumn : introspectedTable.getNonBLOBColumns()) {
            String columnName = MyBatis3FormattingUtilities.getEscapedColumnName(nonBLOBColumn);
            nonBLOBColumnsMap.put(columnName, nonBLOBColumn);
        }
        for (IntrospectedColumn blobColumn : introspectedTable.getBLOBColumns()) {
            String columnName = MyBatis3FormattingUtilities.getEscapedColumnName(blobColumn);
            blobColumnsMap.put(columnName, blobColumn);
        }

        List<IntrospectedColumn> columnNameList = new ArrayList<>();
        List<IntrospectedColumn> columnNameBlobList = new ArrayList<>();
        List<IntrospectedColumn> columnPropertiesList = new ArrayList<>();
        List<IntrospectedColumn> columnPropertiesBlobList = new ArrayList<>();
        for (IntrospectedColumn column : introspectedTable.getNonPrimaryKeyColumns()) {
            String columnName = MyBatis3FormattingUtilities.getEscapedColumnName(column);
            if (nonBLOBColumnsMap.containsKey(columnName)) {
                columnNameList.add(column);
                columnPropertiesList.add(column);
            } else if (blobColumnsMap.containsKey(columnName)) {
                columnNameBlobList.add(column);
                columnPropertiesBlobList.add(column);
            }
        }

        result.add(new TextElement(FIELD_TITLE));

        List<String> noIdFields = columnNameList.stream()
                .map(MyBatis3FormattingUtilities::getEscapedColumnName)
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_NAME_FIELD, noIdFields));

        List<String> noIdFieldsBlob = columnNameBlobList.stream()
                .map(MyBatis3FormattingUtilities::getEscapedColumnName)
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_NAME_FIELD_BLOB, noIdFieldsBlob));

        List<String> noIdProperties = columnPropertiesList.stream()
                .map(MyBatis3FormattingUtilities::getParameterClause)
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_PROPERTIES, noIdProperties));

        List<String> noIdPropertiesBlob = columnPropertiesBlobList.stream()
                .map(MyBatis3FormattingUtilities::getParameterClause)
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_PROPERTIES_BLOB, noIdPropertiesBlob));

        result.add(new TextElement(RECODE_TITLE));

        List<String> noIdFieldsRecord = columnNameList.stream()
                .map(e -> VinciMbgPluginUtils.RECORD_PREFIX + MyBatis3FormattingUtilities.getEscapedColumnName(e))
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_NAME_FIELD_RECORD, noIdFieldsRecord));

        List<String> noIdFieldsBlobRecord = columnNameBlobList.stream()
                .map(e -> VinciMbgPluginUtils.RECORD_PREFIX + MyBatis3FormattingUtilities.getEscapedColumnName(e))
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_NAME_FIELD_BLOB_RECORD, noIdFieldsBlobRecord));

        List<String> noIdPropertiesRecord = columnPropertiesList.stream()
                .map(e -> MyBatis3FormattingUtilities.getParameterClause(e, VinciMbgPluginUtils.RECORD_PREFIX))
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_PROPERTIES_RECORD, noIdPropertiesRecord));

        List<String> noIdPropertiesBlobRecord = columnPropertiesBlobList.stream()
                .map(e -> MyBatis3FormattingUtilities.getParameterClause(e, VinciMbgPluginUtils.RECORD_PREFIX))
                .collect(Collectors.toList());
        result.add(genXmlElementSQL(VinciMbgPluginUtils.NO_ID_PROPERTIES_BLOB_RECORD, noIdPropertiesBlobRecord));

        return result;
    }

    private XmlElement genXmlElementSQL(String id, List<String> fields) {
        XmlElement element = new XmlElement(XmlEleType.SQL.type);
        element.addAttribute(new Attribute(XmlAttribute.ID.type, id));
        element.addElement(new TextElement(String.join(VinciMbgPluginUtils.SEPARATOR, fields)));
        return element;
    }
}
