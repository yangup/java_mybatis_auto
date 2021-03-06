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
import u.auto.sys.annotation.Booelan;
import u.auto.sys.annotation.Length;
import u.auto.sys.annotation.Null;
import u.auto.sys.annotation.Number;
import u.auto.util.CharUtil;
import u.auto.util.ReaderWriter;

/**
 * yangpu.jdbc.mysql.ModelAnnotationCreate.java<br>
 * Description：<br>
 * 
 * @author YangPu
 * @createTime 2016年7月21日 下午3:50:33
 */
public class ModelAnnotationCreate {

	private BufferedWriter writer;
	private List<ColumnInfo> list;

	/**
	 * 加载模板
	 * 
	 * @param modelName
	 */
	public ModelAnnotationCreate(Table table) {
		try {
			this.list = table.getColumnInfos();
			// model的名称
			String modelName = CharUtil.UnderlineToUppercaseTitle(table.getTableName());
			// 文件路径
			String fileName = null;
			if (Constant.isTable) {
				fileName = Constant.path + modelName + File.separator + modelName + "Web.java";
			}
			else {
				fileName = Constant.path + Constant.package_model_annotation + File.separator + modelName + "Web.java";
			}
			File file = new File(fileName);
			// 创建父文件
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			// 创建文件
			if (!file.exists()) {
				file.createNewFile();
			}
			// 获得写入字符流
			writer = new BufferedWriter(new FileWriter(file));
			// 获得模板读出流
			BufferedReader reader = new BufferedReader(new FileReader(Constant.modelAnnotation));
			// 将模板和表信息写入到temp文件中,并返回temp文件的读出流
			reader = ReaderWriter.readWrite(reader, table);
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("-startField-")) {
					createField();
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
			for (int i = 0; i < list.size(); i++) {
				ColumnInfo columninfo = list.get(i);
				// 注释部分
				writer.write("\t/* " + columninfo.getColumnComment() + " */\n");
				String msg = columninfo.getColumnNameJava();
				// 注解部分
				switch (columninfo.getDataTypeIndex()) {
				case 0:
					// Boolean
					writer.write("\t" + Booelan.TRUE.ch(msg) + "\n");
					break;
				case 1:
					// Integer
					writer.write("\t" + Number.min.ch(msg, 0) + "\n");
					writer.write("\t" + Number.max.ch(msg, Integer.parseInt(columninfo.getNumericPrecision())) + "\n");
					break;
				case 4:
					// BigDecimal
					writer.write("\t" + Number.dMin.ch(msg, 0) + "\n");
					writer.write("\t" + Number.dMax.ch(msg, Integer.parseInt(columninfo.getNumericPrecision()), Integer.parseInt(columninfo.getNumericScale())) + "\n");
					writer.write("\t" + Number.digits.ch(msg, Integer.parseInt(columninfo.getNumericPrecision()), Integer.parseInt(columninfo.getNumericScale())) + "\n");
					break;
				case 5:
					// String
					writer.write("\t" + Null.NOTEMPTY.ch(msg) + "\n");
					writer.write("\t" + Length.LENGTH.ch(0, columninfo.getCharacterMaximumLength(), msg) + "\n");
					break;
				case 6:
					// Timestamp
					// 不能为空
					writer.write("\t" + Null.NOTNULL.ch(msg) + "\n");
					break;
				default:
					// 不能为空
					writer.write("\t" + Null.NOTNULL.ch(msg) + "\n");
					break;
				}
				// 代码部分
				writer.write("\t" + "private " + columninfo.getDataTypeJava() + " " + columninfo.getColumnNameJava() + ";" + "\n");
			}
			writer.write("\n");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
