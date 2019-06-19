package u.auto.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import u.auto.jdbc.mysql.Constant;
import u.auto.jdbc.mysql.model.ColumnInfo;
import u.auto.jdbc.mysql.model.Table;
import u.auto.sys.order.Order;

public class ReaderWriter {

	/**
	 * <p>
	 * description： 将模板文件读入temp文件<br>
	 * 返回读取temp文件的reader<br>
	 * </p>
	 *
	 * @param reader
	 * @param list
	 * @param tableName<br>
	 * @author YangPu
	 * @createTime 2016年7月27日 上午9:50:18
	 */
	public static BufferedReader readWrite(BufferedReader reader, Table table) {
		BufferedReader result = null;
		try {
			File file = new File(Constant.temp);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			// 表名称--将下划线+小写转化为大写,首字母大写
			String modelName = CharUtil.UnderlineToUppercaseTitle(table.getTableName());
			modelName = CharUtil.obtainTableName(modelName);
			// 表名称--将下划线+小写转化为大写
			String modelNameParam = CharUtil.param(modelName);
			// 表的注释
			String modelComment = table.getTableComment();
			String line = null;
			ColumnInfo id = table.getId();
			while ((line = reader.readLine()) != null) {
				// id的处理
				if (id != null) {
					// id的字段名称
					line = Order.change(line, Order.Id, id.getColumnName());
					// id的java类型
					line = Order.change(line, Order.IdType, id.getDataTypeJava());
				}
				// 表的注释
				line = Order.change(line, Order.modelComment, modelComment);
				// house_side_info -> housesideinfo
				// 表名称->全部小写
				line = Order.change(line, Order.modelLower, modelNameParam.toLowerCase());
				// house_side_info -> HouseSideInfo
				// 表名称-> 将下划线+小写转化为大写,首字母大写
				line = Order.change(line, Order.modelName, modelName);
				// house_side_info -> houseSideInfo
				// 将下划线+小写转化为大写
				line = Order.change(line, Order.modelNameParam, modelNameParam);
				line = Order.user(line);
				line = Order.date(line);
				line = Order.dateYMDHM(line);
				// 将ctr中的包名改成packageCtr
				line = Order.change(line, Order.packageCtr, Constant.package_ctr_code);
				// 将ser中的包名改成packageSer
				line = Order.change(line, Order.packageSer, Constant.package_ser_code);
				// 将dao中的包名改成packageDao
				line = Order.change(line, Order.packageDao, Constant.package_dao_code);
				// 将model中的包名改成packageModel
				line = Order.change(line, Order.packageModel, Constant.package_model_code);
				// 将mapper中的包名改成packageMapper
				line = Order.change(line, Order.packageMapper, Constant.package_mapper_code);
				// 处理id相关的
				if (id != null) {
					// 有id
					// id的类型
					// 例如：String
					line = Order.change(line, Order.modelNameIdType, id.getDataTypeJava());
					// id..house_side_info -> houseSideInfo
					// 将下划线+小写转化为大写
					line = Order.change(line, Order.modelNameId, CharUtil.UnderlineToUppercase(id.getColumnName()));
					// id..house_side_info -> HouseSideInfo
					// 将下划线+小写转化为大写,首字母大写
					line = Order.change(line, Order.modelNameIdTitle, CharUtil.UnderlineToUppercaseTitle(id.getColumnName()));
				}
				writer.write(line + "\n");
			}
			writer.close();
			reader.close();
			result = new BufferedReader(new FileReader(file));

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
