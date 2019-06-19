package u.auto.jdbc.mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import u.auto.jdbc.mysql.model.ColumnInfo;
import u.auto.jdbc.mysql.model.Table;
import u.auto.sys.order.Order;
import u.auto.util.CharUtil;
import u.auto.util.ReaderWriter;

/**
 * <p>
 * yangpu.jdbc.mysql.ModelCreate.java
 * </p>
 * <p>
 * description：
 * </p>
 * 
 * @author YangPu
 * @createTime 2016年7月21日 下午3:50:33
 */
public class DaoCreate {

	/**
	 * 加载模板
	 * 
	 * @param modelName
	 */
	public DaoCreate(Table table) {
		try {
			String modelName = CharUtil.UnderlineToUppercaseTitle(table.getTableName());
			modelName = CharUtil.obtainTableName(modelName);
			String fileName = null;
			if (Constant.isTable) {
				fileName = Constant.path + modelName.toLowerCase() + File.separator + modelName + "Mapper.java";
			} else {
				fileName = Constant.path + Constant.package_dao + File.separator + modelName + "Mapper.java";
			}
			File file = new File(fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			BufferedReader reader = new BufferedReader(new FileReader(Constant.dao));
			reader = ReaderWriter.readWrite(reader, table);
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.contains(Order.getOrder(Order.sqlFieldRaw))) {
					StringBuffer sb = new StringBuffer();
					int temp_total = 0;
					for (ColumnInfo c : table.getColumnInfos()) {
						if (Order.checkNeed(c.getColumnName())) {
							if (temp_total % 5 == 0 && temp_total != 0) {
								sb.append(CharUtil.w + CharUtil.s + CharUtil.w + CharUtil.j + CharUtil.n + CharUtil.t
										+ CharUtil.t + CharUtil.t + CharUtil.s);
							}
							sb.append(CharUtil.w + c.getColumnName() + CharUtil.d);
							temp_total++;
						}
					}
					line = line.replace(Order.getOrder(Order.sqlFieldRaw), sb.subSequence(0, sb.length() - 1));
				}
				if (line.contains(Order.getOrder(Order.sqlFieldValue))) {
					StringBuffer sb = new StringBuffer();
					int temp_total = 0;
					for (ColumnInfo c : table.getColumnInfos()) {
						if (Order.checkNeed(c.getColumnName())) {
							if (temp_total % 5 == 0 && temp_total != 0) {
								sb.append(CharUtil.w + CharUtil.s + CharUtil.w + CharUtil.j + CharUtil.n + CharUtil.t
										+ CharUtil.t + CharUtil.t + CharUtil.s);
							}
							sb.append(CharUtil.w + "#{" + c.getColumnNameJava() + "}" + CharUtil.d);
							temp_total++;
						}
					}
					line = line.replace(Order.getOrder(Order.sqlFieldValue), sb.subSequence(0, sb.length() - 1));
				}
				if (line.contains(Order.getOrder(Order.sqlFieldNameValue))) {
					StringBuffer sb = new StringBuffer();
					int temp_total = 0;
					for (ColumnInfo c : table.getColumnInfos()) {
						if (Order.checkNeedUpdate(c.getColumnName())) {
							if (temp_total % 5 == 0 && temp_total != 0) {
								sb.append(CharUtil.w + CharUtil.s + CharUtil.w + CharUtil.j + CharUtil.n + CharUtil.t
										+ CharUtil.t + CharUtil.t + CharUtil.s);
							}
							temp_total++;							
							sb.append(CharUtil.w + c.getColumnName() + CharUtil.w + "=");
							if (Order.checkNeedUpdateValue(c.getColumnName())) {
								sb.append(CharUtil.w + "now()" + CharUtil.d);
							} else {
								sb.append(CharUtil.w + "#{" + c.getColumnNameJava() + "}" + CharUtil.d);
							}
						}
					}
					line = line.replace(Order.getOrder(Order.sqlFieldNameValue), sb.subSequence(0, sb.length() - 1));
				}
				if (line.contains(Order.getOrder(Order.tableName))) {
					line = line.replace(Order.getOrder(Order.tableName), table.getTableName());
				}
				writer.write(line + "\n");

			}
			writer.close();
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
