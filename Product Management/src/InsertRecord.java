import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class InsertRecord {
	
	public JFrame frame=new JFrame("Insert Record");
	private JLabel[] label=new JLabel[4];
	private JTextField[] textbox=new JTextField[4];
	private JButton button = new JButton("Save Record");
	PreparedStatement ps,ps1;
	
	public InsertRecord()
	{
		frame.setSize(600,550);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		ps=DbConnection.insert;
		ps1=DbConnection.search;
		addComponent();
		frame.setVisible(true);
	}
	private void addComponent()
	{
		String[] str={"Id","Name","Brand","Price"};
		int y=60;
		Font font=new Font("arial",Font.PLAIN,22);
		Font font1=new Font("arial",Font.PLAIN,19);
		for(int i=0; i<label.length; i++)
		{
			label[i]= new JLabel("Enter Product "+str[i]);
			label[i].setFont(font);
			label[i].setBounds(40, y,250,30);
			frame.add(label[i]);
			textbox[i]=new JTextField();
			textbox[i].setBounds(260,y,300,33);
			textbox[i].setFont(font1);
			textbox[i].setForeground(Color.blue);
			frame.add(textbox[i]);
			y+=75;
		}
		button.setBounds(100, 400, 400, 35);
		button.setFont(font1);
		frame.add(button);
		button.addActionListener(new SaveListener());
	
	}
	class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			try 
			{
				int pid=Integer.parseInt(textbox[0].getText());
				ps1.setInt(1,pid);
				ResultSet rst=ps1.executeQuery();
				if(rst.next())
				{
					JOptionPane.showMessageDialog(frame,"Product with id "+pid+" already exists");
					return;
				}
				ps.setInt(1,pid);
				String name=textbox[1].getText();
				ps.setString(2,name);
				String brand=textbox[2].getText();
				ps.setString(3,brand);
				int price=Integer.parseInt(textbox[3].getText());
				ps.setInt(4,price);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(frame,"Record has been inserted");
				for(int i=0;i<4;i++)
					textbox[i].setText("");
				
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			
			
		}
	}
	

}
