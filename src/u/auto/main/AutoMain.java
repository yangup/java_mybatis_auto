package u.auto.main;

import u.auto.jdbc.mysql.ConnectionSingle;
import u.auto.jdbc.mysql.Constant;

public class AutoMain {

    public static void main(String[] args) {
        Constant.path = "C:\\Users\\wantw\\Desktop\\auto\\";
        Constant.isTable = Boolean.TRUE;
        Constant.package_ser_code = "com.sulu.biz.sms";
        Constant.package_dao_code = "com.sulu.biz.sms";
        Constant.package_model_code = "com.sulu.biz.sms";
        ConnectionSingle connection = ConnectionSingle.getSingle();
//        connection.prepare(new String[]{"org.postgresql.Driver", "jdbc:postgresql://13.250.231.4:5432/", "moneyking_test", "SfY3X2AU=2T=iw8v", "in"});
        connection.prepare(new String[]{"com.mysql.cj.jdbc.Driver", "jdbc:mysql://192.168.40.230:3307/", "moneyking_uer", "3^qp3Xqt4bG7", "MoneyKing",
                "?useSSL=false&serverTimezone=GMT&autoReconnect=true"});
        connection.start("s_user");
        connection.start("s_role");
        connection.start("s_privilege");
        connection.start("s_user_role");
        connection.start("s_role_privilege");
    }

}
