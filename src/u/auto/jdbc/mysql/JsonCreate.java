package u.auto.jdbc.mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import u.auto.jdbc.mysql.model.ColumnInfo;
import u.auto.jdbc.mysql.model.Table;
import u.auto.sys.order.Order;
import u.auto.util.CharUtil;
import u.auto.util.ReaderWriter;

/**
 * yangpu.jdbc.mysql.ModelCreate.java<br>
 * Description：<br>
 * 
 * @author YangPu
 * @createTime 2016年7月21日 下午3:50:33
 */
public class JsonCreate {

	private BufferedWriter writer;
	private List<ColumnInfo> list;
	private List<String[]> fieldsAll;// field的信息.String[0]为数组下标,String[1]为类型type,string[2]为field名字,string[3]为为默认值

	/**
	 * 加载模板
	 * 
	 * @param modelName
	 */
	public JsonCreate(Table table) {
		try {
			this.list = table.getColumnInfos();
			String modelName = CharUtil.UnderlineToUppercaseTitle(table.getTableName());
			modelName = CharUtil.obtainTableName(modelName);
			String fileName = null;
			if (Constant.isTable) {
				fileName = Constant.path + modelName + File.separator + modelName + ".json";
			}
			else {
				fileName = Constant.path + Constant.package_json + File.separator + modelName + ".json";
			}
			File file = new File(fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file));
			// 读取json模板
			BufferedReader reader = new BufferedReader(new FileReader(Constant.json));
			reader = ReaderWriter.readWrite(reader, table);

			String line = reader.readLine();
			while (line != null) {
				if (Order.check(line, Order.jsonStart)) {
					createJson();
				}
				else {
					writer.write(line + "\n");
				}
				line = reader.readLine();
			}
			writer.close();
			reader.close();

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createJson() {
		try {
			boolean isFirst = false, isLast = false;
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					isFirst = true;
				}
				if (i == list.size() - 1) {
					isLast = true;
				}
				ColumnInfo columninfo = list.get(i);
				String field = columninfo.getColumnNameJava();
				writer.write("\t");
				writer.write(CharUtil.s + field + CharUtil.s + CharUtil.m + CharUtil.s + field + CharUtil.s);
				if (!isLast) {
					writer.write(CharUtil.d);
				}
				if (!isLast) {
					writer.write("\n");
				}
			}
			writer.write("\n");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Description：根据数据库的默认值生成构造方法 <br>
	 *
	 * @author YangPu
	 * @createTime 2016年8月20日 下午4:29:41
	 */
	private void createConstructor() {
		try {
			String temp = null;
			List<String[]> outs = new ArrayList<>();
			for (String[] strs : fieldsAll) {
				int index = Integer.parseInt(strs[0]);// list的下标
				String type = strs[1];// 类型
				String field = strs[2];// field名称
				String value = strs[3];// 默认值

			}
			for (String[] out : outs) {
				for (String o : out) {
					writer.write("\t\t" + o + ";\n");
				}
			}
			writer.write("\n");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createMethod() {
		try {
			for (ColumnInfo columninfo : list) {
				String field = CharUtil.UnderlineToUppercase(columninfo.getColumnName());
				String type = columninfo.getDataTypeJava();
				// get-start
				writer.write("\tpublic " + type + " " + CharUtil.UnderlineToUppercase("get_" + columninfo.getColumnName()) + "() {\n");
				writer.write("\t\treturn " + field + ";\n");
				writer.write("\t}\n");
				writer.write("\n");
				// get-end
				// set-start
				writer.write("\tpublic void " + CharUtil.UnderlineToUppercase("set_" + columninfo.getColumnName()) + "(" + type + " " + field + ") {\n");
				writer.write("\t\tthis." + field + " = " + field + ";\n");
				writer.write("\t}\n");
				writer.write("\n");
				// set-end
//				break;
			}
		}
		catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
