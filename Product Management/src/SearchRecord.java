import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchRecord
{
      public JFrame frame = new JFrame("Search Record Form");
      private JLabel label= new JLabel("Enter Product Id");
      private JTextField textbox=new JTextField();
      private JButton button=new JButton("Search Record");
      private JPanel panel=new JPanel();
      private JLabel[] heading=new JLabel[3];
      private JLabel[] data=new JLabel[3];
      private  PreparedStatement ps;
      
   public SearchRecord()
   {
        frame.setSize(600,550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        ps=DbConnection.search;
        addComponents();
        addPanel();
        frame.setVisible(true);
   }
   private void addComponents()
   {
	  int y=50;
	  Font font=new Font("arial",Font.PLAIN,22);
	  Font font1=new Font("arial",Font.PLAIN,19);
	  label.setBounds(50, y, 250,30);
	  label.setFont(font);
	  frame.add(label);
	  textbox.setBounds(300, y,250,30);
	  textbox.setFont(font1);
	  frame.add(textbox);
	  button.setBounds(50, 120,500,35);
	  button.setFont(font1);
	  frame.add(button);
	  button.addActionListener(new SearchListener());
	  
   }
   private void addPanel()
   {
	   panel.setBounds(50,200,500,230);
	   panel.setBackground(Color.orange);
	   panel.setVisible(false);
	   frame.add(panel);
	   addResult();
   }
   private void addResult()
   {
	   panel.setLayout(new GridLayout(3,2,100,0));
	   Font font=new Font("Lucida Calligraphy",Font.BOLD,20);
	   Font font1=new Font("arial",Font.PLAIN,20);
	   String[] str= {"Name","Brand","Price"};
	   for(int i=0; i<3; i++)
	   {
		   heading[i]=new JLabel("Product "+str[i]);
		   panel.add(heading[i]);
		   heading[i].setFont(font);
		   data[i]=new JLabel();
		   panel.add(data[i]);
		   data[i].setFont(font1);
		   data[i].setForeground(Color.blue);
		   
	   }
	   
   }
   
   class SearchListener implements ActionListener
   {

	public void actionPerformed(ActionEvent evt)
	{
		  int pid=Integer.parseInt(textbox.getText());
		  try
		  {
			  ps.setInt(1, pid);
			  ResultSet rst=ps.executeQuery();
			  if(rst.next())
			  {
				  data[0].setText(rst.getString(2));
				  data[1].setText(rst.getString(3));
				  data[2].setText(rst.getString(4));
				  panel.setVisible(true);
			  }
			  else
			  {
				  panel.setVisible(false);
				  JOptionPane.showMessageDialog(frame,"Product not Found");
			  }
		  }
		  catch(Exception ex)
		  {
			  System.out.println(ex);
		  }
		
		
	}
	   
   }
}

