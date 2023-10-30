import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateRecord
{
    public JFrame frame=new JFrame("Update Product Form");
    private JLabel label=new JLabel("Enter Product Id");
    private JTextField textbox=new JTextField();
    private JButton button=new JButton("Show Product");
    private JButton update=new JButton("Update Record");
    private JPanel panel=new JPanel();
    private PreparedStatement ps1,ps2;
    private JLabel[] heading=new JLabel[3];
    private JTextField[] data=new JTextField[3];
    int pid;
    
    public UpdateRecord()
    {
    	frame.setSize(600,500);
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
    	frame.setLayout(null);
    	addComponents();
    	addPanel();
    	ps1=DbConnection.search;
    	ps2=DbConnection.update;
    	frame.setVisible(true);
    }
    
    private void addComponents()
    {
    	Font font=new Font("arial",Font.PLAIN,22);
    	Font font1=new Font("arial",Font.PLAIN,19);
    	label.setBounds(50,50,250,30);
    	label.setFont(font);
    	frame.add(label);
    	textbox.setBounds(300,50,250,30);
    	textbox.setFont(font1);
    	frame.add(textbox);
    	button.setBounds(50,120,500,35);
    	button.setFont(font1);
    	frame.add(button);
    	button.addActionListener(new showListener());
    	update.setBounds(50,420,500,35);
    	update.setFont(font1);
    	frame.add(update);
    	update.setVisible(false);
    	update.addActionListener(new updateListener());
    }
    private void addPanel()
    {
    	panel.setBounds(50, 200,500,200);
    	panel.setBackground(Color.cyan);
    	frame.add(panel);
    	addResult();
    	panel.setVisible(false);
    }
    private void addResult()
    {
    	panel.setLayout(new GridLayout(3,2,0,50));
    	Font font=new Font("Lucida Calligraphy",Font.BOLD,20);
    	Font font1=new Font("arial",Font.PLAIN,20);
    	String[] str= {"Name","Brand","Price"};
    	for(int i=0;i<3; i++)
    	{
    		heading[i]=new JLabel("Edit Product "+str[i]);
    		panel.add(heading[i]);
    		heading[i].setFont(font);
    		data[i]=new JTextField();
    		panel.add(data[i]);
    		data[i].setFont(font1);
    		data[i].setForeground(Color.blue);
    	
    	}
    }
    
    class showListener implements ActionListener
    {
		public void actionPerformed(ActionEvent evt)
		{
		
			 pid=Integer.parseInt(textbox.getText());
			 try
			 {
				 ps1.setInt(1,pid);
				 ResultSet rst=ps1.executeQuery();
				 if(rst.next())
				 {
					 data[0].setText(rst.getString(2));
					 data[1].setText(rst.getString(3));
					 data[2].setText(rst.getString(4));
					 panel.setVisible(true);
					 update.setVisible(true);
				 }
				 else
				 {
					 panel.setVisible(false);
					 update.setVisible(false);
					 JOptionPane.showMessageDialog(frame, "Product Record does not Exist");
				 }
				 
			 }
			 catch(Exception ex)
			 {
				System.out.println(ex); 
			 }
		}
    }
    
    class updateListener implements ActionListener
    {

		
		public void actionPerformed(ActionEvent e) 
		{
            
			try
			{
				ps2.setString(1,data[0].getText());
				ps2.setString(2, data[1].getText());
				ps2.setString(3,data[2].getText());
				ps2.setInt(4,pid);
				ps2.executeUpdate();
				panel.setVisible(false);
				update.setVisible(false);
				
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}

			
		}
    	
    }
    
}
