package com.bingoabin.util;

import org.mybatis.generator.api.IntrospectedTable;

/**
 * @author HanJunsheng
 * @date 2021/3/18
 * @copyright sankuai.com
 */
public class VinciMbgPluginUtils {

    private static final Integer DEFAULT_GEN_BLOBS = 2;

    public static final String TITLE_FORMAT = "<!--  ==================== %s ==================== -->";

    public static final String ENTER_STR = "\n";
    public static final String SEPARATOR = ",\n    ";
    public static final String RECORD_PREFIX = "record.";
    public static final String RECORDS = "records";
    public static final String RECORD = "record";
    public static final String RECOEDS_ANNOTATION = "@Param(\"records\")";

    public static final String NO_ID_NAME_FIELD = "noIdFields";
    public static final String NO_ID_NAME_FIELD_BLOB = "noIdFieldsBlob";
    public static final String NO_ID_PROPERTIES = "noIdProperties";
    public static final String NO_ID_PROPERTIES_BLOB = "noIdPropertiesBlob";
    public static final String NO_ID_NAME_FIELD_RECORD = "noIdFieldsRecord";
    public static final String NO_ID_NAME_FIELD_BLOB_RECORD = "noIdFieldsBlobRecord";
    public static final String NO_ID_PROPERTIES_RECORD = "noIdPropertiesRecord";
    public static final String NO_ID_PROPERTIES_BLOB_RECORD = "noIdPropertiesBlobRecord";

    public static final String INSERT_BATCH = "insertBatch";
    public static final String INSERT_BATCH_BLOB = "insertBatchWithBlob";


    public static boolean isWithBlobs(IntrospectedTable introspectedTable) {
        return introspectedTable.getBLOBColumns().size() >= DEFAULT_GEN_BLOBS;
    }
}
