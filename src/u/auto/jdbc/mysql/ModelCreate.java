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
public class ModelCreate {

	private BufferedWriter writer;
	private List<ColumnInfo> list;
	private List<String[]> fieldsAll;// field的信息.String[0]为数组下标,String[1]为类型type,string[2]为field名字,string[3]为为默认值

	/**
	 * 加载模板
	 * 
	 * @param modelName
	 */
	public ModelCreate(Table table) {
		try {
			this.list = table.getColumnInfos();
			String modelName = CharUtil.UnderlineToUppercaseTitle(table.getTableName());
			modelName = CharUtil.obtainTableName(modelName);
			String fileName = null;
			if (Constant.isTable) {
				fileName = Constant.path + CharUtil.param(modelName).toLowerCase() + File.separator + modelName + "Entity.java";
			}
			else {
				fileName = Constant.path + Constant.package_model + File.separator + modelName + "Entity.java";
			}
			File file = new File(fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file));
			// 读取model模板
			BufferedReader reader = new BufferedReader(new FileReader(Constant.model));
			reader = ReaderWriter.readWrite(reader, table);

			String line = reader.readLine();
			while (line != null) {
				if (Order.check(line, Order.startField)) {
					createField();
				}
				else if (Order.check(line, Order.createConstructor)) {
					if (Constant.isConstructor) {
						createConstructor();
					}
					else {
						writer.write("\n");
					}
				}
				else if (Order.check(line, Order.startMethod)) {
					createMethod();
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

	private void createField() {
		try {
			String temp = null;
			List<String[]> fields = new ArrayList<>();
			if (Constant.isConstructor) {
				fieldsAll = new ArrayList<>();
			}
			for (int i = 0; i < list.size(); i++) {
				ColumnInfo columninfo = list.get(i);
				String type = columninfo.getDataTypeJava();
				String field = columninfo.getColumnNameJava();
				String[] strings = new String[2];
				temp = new String("" + type + " " + field + ";");
				strings[0] = temp;// 代码部分
				temp = new String(columninfo.getColumnComment());
				strings[1] = temp;// 注释部分
				fields.add(strings);
				if (Constant.isConstructor) {
					fieldsAll.add(new String[] { String.valueOf(i), type, field, columninfo.getColumnDefault() });
				}
			}
			for (String[] strings : fields) {
				writer.write("\t/* " + strings[1] + " */\n");
				writer.write("\t" + strings[0] + "\n");
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
