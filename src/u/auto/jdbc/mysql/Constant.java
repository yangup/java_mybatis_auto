package u.auto.jdbc.mysql;

public class Constant {

	// 位置不能随意变更
	public static final String[][] fieldType = { //
			{ "bool", "boolean" } // Boolean--0
			, { "bit", "tinyint", "smallint", "int", "integer", "mediummint", "int4" } // Integer--1
			, { "bigint", "int8" } // Long--2
			, { "float", "real" } // Float--3
			, { "dec", "decimal", "double", "dec", "decimal", "double", "numeric" } // Double--4
			, { "enum", "binary", "blob", "char", "enum", "fixed", "longblob", "tinyblob", "tinytext", "varbinary", "varchar", "longtext", "mediumblob", "set", "text" } // String--5
			, { "timestamp", "year", "datetime" }// Timestamp--6
			, { "year" }// Year--7
			, { "date", "timestamptz" }// Date--8
			, { "time" }// Time--9
			, { "byte" }// byte--10
	};

	// 位置不能随意变更
	// 修改的时候注意修改modelCreate中的值
	public static final String[] fieldTyepName = { //
			"Boolean"// Boolean--0
			, "int"// Integer--1
			, "long"// Long--2
			, "Float"// Float--3
			, "BigDecimal"// BigDecimal--4
			, "String"// String--5
			, "Timestamp" // Timestamp--6
			, "Year" // Year--7
			, "Date" // Date--8
			, "time"// Time--9
			, "Byte"// Byte--10
			, "String" // default--10
	};

	// 修改的时候注意修改modelCreate中的值
	// 用在doc中的类型
	public static final String[] fieldTyepNameDoc = { //
			"布尔型数据"// Boolean--0
			, "整数"// Integer--1
			, "整数"// Long--2
			, "浮点型"// Float--3
			, "浮点型"// BigDecimal--4
			, "字符串型"// String--5
			, "时间" // Timestamp--6
			, "时间" // Year--7
			, "时间" // Date--8
			, "时间"// Time--9
			, "字节型"// Byte--10
			, "字符串型" // default--10
	};
	
	// 用在doc中的说明
	public static final String[] fieldTyepNameDocNote = { //
			"只能填写true或false"// Boolean--0
			, ""// Integer--1
			, ""// Long--2
			, ""// Float--3
			, ""// BigDecimal--4
			, ""// String--5
			, "格式：yyyy-MM-dd HH:mm:ss  例如：2017-01-01 12:13:14" // Timestamp--6
			, "格式：yyy  例如：2017" // Year--7
			, "格式：yyyy-MM-dd  例如：2017-01-01" // Date--8
			, "格式：HH:mm:ss  例如：12:13:14"// Time--9
			, ""// Byte--10
			, "" // default--10
	};

	public static final String[] Long = { //
			"Long"//
			, "BigInteger" //
	};//

	private static String pre;

	static {
		pre = Class.class.getClass().getResource("/").getPath().substring(1, Class.class.getClass().getResource("/").getPath().length());
	}
	private static final String template = pre + "u/auto/resources/";
	public static final String json = template + "templateJson.up";
	public static final String model = template + "templateModel.up";
	public static final String do1 = template + "templateDo.up";
	public static final String dto = template + "templateDto.up";
	public static final String vo = template + "templateVo.up";
	public static final String modelAnnotation = template + "templateModelAnnotation.up";
	public static final String mapper = template + "templateMapper.up";
	public static final String dao = template + "templateDao.up";
	public static final String ser = template + "templateSer.up";
	public static final String ctr = template + "templateCtr.up";
	public static final String doc = template + "templateDoc.up";
	public static final String ctrRestful = template + "templateCtrRestful.up";
	public static final String temp = template + "temp.up";
	public static String package_model_code = "model";
	public static final String package_model = "model";
	public static final String package_json = "json";
	public static final String package_do = "do";
	public static final String package_dto = "dto";
	public static final String package_vo = "vo";
	public static final String package_model_annotation = "modelAnnotation";
	public static String package_mapper_code = "mapper";//mapper代码中的包路径
	public static final String package_mapper = "mapper";
	public static String package_dao_code = "dao";//dao代码中的包路径
	public static final String package_dao = "dao";
	public static String package_ser_code = "ser";//ser代码中的包路径
	public static final String package_ser = "service";
	public static String package_ctr_code = "ctr";//ctr代码中的包路径
	public static final String package_ctr = "controller";
	public static final String package_ctr_restful = "controllerRestful";
	public static final String package_doc = "doc";
	public static int limit = 15;
	public static String path = "E:/study/auto/";
	public static Boolean isTable = Boolean.FALSE;
	public static Boolean isConstructor = Boolean.FALSE;

}
