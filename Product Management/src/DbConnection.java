import java.sql.*;
public class DbConnection {

	public static PreparedStatement insert,delete,search,update;
	public static Statement all;
	public static void createConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/product?createDatabaseIfNotExist=true","root","mysql");
			Statement st=cn.createStatement();
			st.execute("create table if not exists productInfo(pid int primary key,name varchar(30),brand varchar(30),price int)");
			insert=cn.prepareStatement("insert into productinfo values(?,?,?,?)");
			all=cn.createStatement();
		    search=cn.prepareStatement("select * from productInfo where pid=?");
		    delete=cn.prepareStatement("delete from productInfo where pid=?");
		    update=cn.prepareStatement("update productinfo set name=?,brand=?,price=? where pid=?");

		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}
