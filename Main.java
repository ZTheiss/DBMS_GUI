package mypackage;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


import com.jcraft.*;
//https://stackoverflow.com/questions/1968293/connect-to-remote-mysql-database-through-ssh-using-java


public class Main extends JFrame implements ActionListener{
	static int lport;
    static String rhost;
    static int rport;    
    JPanel mpp;
    JButton query1,query2,query3,query4,query5,query6;
    
    public Main(){
    	
		this.setSize(800,900);
		this.setLocationRelativeTo(null);
		this.setTitle("Group 21 DBMS GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		mpp = new JPanel();
		mpp.setLayout(new GridBagLayout());
		
		query1 = new JButton("Query 1");
//		query2 = new JButton("Query 2");
//		query3 = new JButton("Query 3");
//		query4 = new JButton("Query 4");
//		query5 = new JButton("Query 5");
//		query6 = new JButton("Query 6");
		addComp(mpp, query1, 0,2,2,3, GridBagConstraints.WEST, GridBagConstraints.NONE);
//		addComp(mpp, query2, 5,2,2,3, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//		addComp(mpp, query3, 10,2,2,3, GridBagConstraints.EAST, GridBagConstraints.NONE);
//		addComp(mpp, query4, 0,6,2,3, GridBagConstraints.WEST, GridBagConstraints.NONE);
//		addComp(mpp, query5, 5,6,2,3, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//		addComp(mpp, query6, 10,6,2,3, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		if(query1.getModel().isPressed())
			System.out.println("pressed");
		query1.addActionListener(this);
//		query2.addActionListener(myButtonListener);
//		query3.addActionListener(myButtonListener);
//		query4.addActionListener(myButtonListener);
//		query5.addActionListener(myButtonListener);
//		query6.addActionListener(myButtonListener);
		
		this.add(mpp);
		this.pack();
		this.setVisible(true);

    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==query1){
			execute("query1");
		}
		dispose();
	}
    
    public void execute(String s){
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + rhost +":" + lport + "/";
        String db = "zatheiss";
        String dbUser = "zatheiss";
	    String dbPasswd = "Tshodan01!";
    	if(s.compareTo("query1")==0)
    	    try{
    	    	Class.forName(driver);
    	        con = DriverManager.getConnection(url+db, dbUser, dbPasswd);
    	        try{
    	        	Statement st = con.createStatement();
    	        	String sql = "select * from Book";
    	        	ResultSet response = st.executeQuery(sql);
    	        	while(response.next()){
    	        		int ISBN = response.getInt("ISBN");
    	        		String title = response.getString("Title");
    	        		double price = response.getDouble("Price");
    	        		int quantity = response.getInt("Quantity");
    	        		System.out.println(ISBN + " " + title + " " + price + " " + quantity);
    	        	}
    	        	System.out.println("executed");
    	        }
    	        catch (SQLException x){
    	        	System.out.println(x);
    	      	}
    	    }
    	    catch (Exception e){
    	    	e.printStackTrace();
    	    }
    }
	private void addComp(JPanel thePanel, JComponent comp, int xP, int yP, int w, int h, int place, int stretch)
	{
		GridBagConstraints gridC = new GridBagConstraints();
		gridC.gridx = xP;
		gridC.gridy = yP;
		gridC.gridwidth = w;
		gridC.gridheight = h;
		gridC.insets = new Insets(5,5,5,5);
		gridC.anchor = place;
		gridC.fill = stretch;
		thePanel.add(comp, gridC);	
	}
    
    public static void go(){
        String user = "zatheiss";
        String password = "Grad2015!";
        String host = "turing.csce.uark.edu";
        int port=22;
        try
            {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            lport = 4106;
            rhost = "localhost";
            rport = 3306;
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
	        session.connect();
	        int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            }
        catch(Exception e){System.err.print(e);}
    }
	
    
    public static void main(String[] args) {
		      try{
		      go();
		  } catch(Exception ex){
		      ex.printStackTrace();
		  }
    	Main test = new Main();



    }




}

