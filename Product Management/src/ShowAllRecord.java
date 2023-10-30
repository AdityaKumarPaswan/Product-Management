import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ShowAllRecord {

	public JFrame frame=new JFrame("Show All Record");
	private JTable table;
	private JScrollPane pane;
	private DefaultTableModel model=new DefaultTableModel();
	private Statement st;
	
	public ShowAllRecord()
	{
		frame.setSize(600,550);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.cyan);
		st=DbConnection.all;
		showResultSet();
		frame.setVisible(true);
	}
	private void showResultSet()
	{
		table=new JTable(model);
		pane=new JScrollPane(table);
		frame.add(pane);
		model.addColumn("Product Id");
		model.addColumn("Product Name");
		model.addColumn("Product Brand");
		model.addColumn("Product Price");
		try
		{
			ResultSet rst=st.executeQuery("select * from productInfo");
			while(rst.next())
			{
				Object[] data= {rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4)};
				model.addRow(data);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
	}
}
