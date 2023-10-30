import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainClass
{
	
	JFrame frame =new JFrame("Product Management System");
	JButton[] bt=new JButton[5];
	public MainClass()
	{
		frame.setSize(600,550);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.ORANGE);
		DbConnection.createConnection();
		addButtons();
		frame.setVisible(true);
	}
	
	private void addButtons()
	{
		frame.setLayout(null);
		int y=80;
		String[] str= {"Insert","Delete","Show All","Update","Search"};
		Font fo=new Font("arial",Font.PLAIN,20);
		MenuListener listener=new MenuListener();
		for(int i=0; i<5; i++)
		{
		    bt[i]=new JButton(str[i]+" Record");	
		    bt[i].setBounds(200,y,200,35);
		    bt[i].setFont(fo);
		    bt[i].addActionListener(listener);
		    frame.add(bt[i]);
		    y+=75;
		}
	}
	class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			JButton bb=(JButton)evt.getSource();
			if(bb==bt[0]) //insert Record
			{
				new InsertRecord(); 
			}
			else if(bb==bt[1]) // Delete Record
			{
				new DeleteRecord();
			}
			else if(bb==bt[2]) // Show All Record
			{
				new ShowAllRecord();
			}
			else if(bb==bt[3])
			{
				new UpdateRecord();
			}
			else if(bb==bt[4]) // Search Record 
			{
				new SearchRecord();
			}
		}
		
	}
	
	public static void main(String[]args)
	{
		new MainClass();
	}
}
