package u.auto.jdbc.mysql.model;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @description: <br/>
 * @version: 1<br/>
 * @package u.auto.jdbc.mysql.model.ColumnInfo.java
 * @author YangPu
 * @date 2017年1月16日 上午11:51:52
 */
public class ColumnInfo {
	
	private String tableName;
	private String columnName;
		private String columnNameJava;// java中的驼峰命名

	private String isNullable;
	private String dataType;// 数据库中的数据类型
		private String dataTypeJava;// java中的数据类型
		private int dataTypeIndex;// 在Constant.fieldTyepName中的下标值
	private String characterMaximumLength;
	private String numericPrecision;
	private String numericScale;

	private String characterSetName;
	private String collationName;
	private String columnType;
	private String columnKey;
	private String columnComment;
	private String tableComment;

	private String columnDefault;
	

	public String getColumnDefault() {
		return columnDefault;
	}



	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}



	public String getTableName() {
		return tableName;
	}



	public void setTableName(String tableName) {
		this.tableName = tableName;
	}



	public String getColumnName() {
		return columnName;
	}



	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}



	public String getColumnNameJava() {
		return columnNameJava;
	}



	public void setColumnNameJava(String columnNameJava) {
		this.columnNameJava = columnNameJava;
	}



	public String getIsNullable() {
		return isNullable;
	}



	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}



	public String getDataType() {
		return dataType;
	}



	public void setDataType(String dataType) {
		this.dataType = dataType;
	}



	public String getDataTypeJava() {
		return dataTypeJava;
	}



	public void setDataTypeJava(String dataTypeJava) {
		this.dataTypeJava = dataTypeJava;
	}



	public int getDataTypeIndex() {
		return dataTypeIndex;
	}



	public void setDataTypeIndex(int dataTypeIndex) {
		this.dataTypeIndex = dataTypeIndex;
	}



	public String getCharacterMaximumLength() {
		return characterMaximumLength;
	}



	public void setCharacterMaximumLength(String characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}



	public String getNumericPrecision() {
		return numericPrecision;
	}



	public void setNumericPrecision(String numericPrecision) {
		this.numericPrecision = numericPrecision;
	}



	public String getNumericScale() {
		return numericScale;
	}



	public void setNumericScale(String numericScale) {
		this.numericScale = numericScale;
	}



	public String getCharacterSetName() {
		return characterSetName;
	}



	public void setCharacterSetName(String characterSetName) {
		this.characterSetName = characterSetName;
	}



	public String getCollationName() {
		return collationName;
	}



	public void setCollationName(String collationName) {
		this.collationName = collationName;
	}



	public String getColumnType() {
		return columnType;
	}



	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}



	public String getColumnKey() {
		return columnKey;
	}



	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}



	public String getColumnComment() {
		if (StringUtils.isEmpty(columnComment)) {
			return " ";
		}
		return columnComment;
	}



	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}



	public String getTableComment() {
		return tableComment;
	}



	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}



	public static Set<String> FieldSet() {
		Set<String> keys = new HashSet<>();
		Field[] fields = ColumnInfo.class.getDeclaredFields();
		for (Field field : fields) {
			if (!"datatypejava".equals(field.getName().toLowerCase()) && !"columnnamejava".equals(field.getName().toLowerCase()) && !"datatypeindex".equals(field.getName().toLowerCase())) {
				keys.add(field.getName());
			}
		}
		return keys;
	}
	
	public static Set<String> FieldSet1() {
		Set<String> keys = new HashSet<>();
		keys.add("tableName");
		keys.add("columnName");
		keys.add("dataType");
		keys.add("numericPrecision");
		keys.add("characterMaximumLength");
		keys.add("isNullable");
		return keys;
	}


}
