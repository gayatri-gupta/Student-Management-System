import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;


class Connect{
  Connection OpenCon(){
    try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/gg","root","root");
      return c;
    }
    catch(Exception e){
      System.out.println(e);
    }
return null;
  }
}

class Insert extends JFrame implements ActionListener{
 JButton b1;
 JTextField t1,t2,t3,t4;
 String naam;
 String sect;
 int rool;
 int year;

 Insert()
 {
   t1=new JTextField("Enter Name");
   t2=new JTextField("Enter Section");
   t3=new JTextField("Enter Roll no.");
   t4=new JTextField("Enter Year");
   b1=new JButton("Insert");
   t1.setBounds(20,60,200,40);
   t2.setBounds(20,110,200,40);
   t3.setBounds(20,160,200,40);
   t4.setBounds(20,210,200,40);
   b1.setBounds(40,260,100,40);
   add(t1);
   add(t2);
   add(t3);
   add(t4);
   add(b1);
   setLayout(null);
   b1.addActionListener(this);
   setSize(400,400);
   setVisible(true);
 }

public void actionPerformed(ActionEvent ae){

try{
  Connect obj=new Connect();
  Connection c=obj.OpenCon();
  Statement st = c.createStatement();
  naam = t1.getText();
  sect = t2.getText();
  rool = Integer.parseInt(t3.getText());
  year = Integer.parseInt(t4.getText());
  st.executeUpdate("insert into student values('"+naam+"','"+sect+"',"+rool+","+year+")");
  System.out.println("Values Inserted");
  dispose();
}
catch(Exception e){
  System.out.println(e);
    }
  }
}

class Update extends JFrame implements ActionListener{
  JButton b1;
  JTextField t1,t2;
  String sect;
  int rool;

  Update()
  {
    t1=new JTextField("Enter Section");
    t2=new JTextField("Enter Roll no.");
    b1=new JButton("Update");
    t1.setBounds(20,110,200,40);
    t2.setBounds(20,160,200,40);
    b1.setBounds(40,260,100,40);
    add(t1);
    add(t2);
    add(b1);
    setLayout(null);
    b1.addActionListener(this);
    setSize(400,400);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae){

  try{
  Connect obj=new Connect();
  Connection c=obj.OpenCon();
  Statement st = c.createStatement();
  sect = t1.getText();
  rool = Integer.parseInt(t2.getText());
  st.executeUpdate("update student set section='"+sect+"'where roll_no ="+rool);
  System.out.println("Values Updated");
  dispose();
  }
  catch(Exception e){
   System.out.println(e);
     }
   }
  }

  class Search extends JFrame implements ActionListener{
   JButton b1;
   JTextField t1;
   int rool;
   String naam;

   Search()
   {
     t1=new JTextField("Enter Name");
     b1=new JButton("Search");
     t1.setBounds(50,50,300,40);
     b1.setBounds(150,100,100,40);
     add(t1);
     add(b1);
     setLayout(null);
     b1.addActionListener(this);
     setSize(400,400);
     setVisible(true);
   }

  public void actionPerformed(ActionEvent ae){

    new SearchRes(t1.getText());
    dispose();
}
  }
class SearchRes extends JFrame{
  JTable jt;
  SearchRes(String nam){
    jt=new JTable();
    jt.setBounds(30,60,300,200);
    add(jt);
    try{
      Connect obj=new Connect();
      Connection c=obj.OpenCon();
      Statement s=c.createStatement();
      ResultSet rs=s.executeQuery("Select * from student where name='"+nam+"'");
      DefaultTableModel model =new DefaultTableModel(new String[]{"Name","section","Roll no","year"},0);
      while(rs.next()){
        model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4)});
      }
      jt.setModel(model);
      c.close();
    }
    catch(Exception e){
      System.out.println(e);
    }
    setLayout(null);
    setSize(400,400);
    setVisible(true);
  }
}
class Promote extends JFrame implements ActionListener{
  JButton b1;
  JLabel l1,l2;
  JComboBox cb1,cb2;
    Promote()
  {
    String years[]={"1","2","3"};
    String section[]={"A","B","C","D","E","F","G","H","I","J","K"};
    l1=new JLabel("Select year");
    l2=new JLabel("Select section");
    b1=new JButton("Promote");
    cb1=new JComboBox(years);
    cb2=new JComboBox(section);
    l1.setBounds(30,30,100,40);
    cb1.setBounds(30,80,100,40);
    l2.setBounds(30,130,100,40);
    cb2.setBounds(30,180,100,40);
    b1.setBounds(40,260,100,40);
    add(l1);
    add(cb1);
    add(l2);
    add(cb2);
    add(b1);
    setLayout(null);
    b1.addActionListener(this);
    setSize(400,400);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae){
    String yearr=""+cb1.getItemAt(cb1.getSelectedIndex());
    String sect=""+cb2.getItemAt(cb2.getSelectedIndex());
    int yr= Integer.parseInt(yearr);
    int yer=yr+1;
  try{
  Connect obj=new Connect();
  Connection c=obj.OpenCon();
  Statement st = c.createStatement();
  st.executeUpdate("update student set year="+yer+" where section='"+sect+"' and year="+yr);
  System.out.println("Values Updated");
  dispose();
  }
  catch(Exception e){
   System.out.println(e);
 }
   }
  }

public class FSwSql extends JFrame implements ActionListener{
 JButton b1,b2,b3,b4;

FSwSql(){

  b1= new JButton("Add a Student");
  b2=new JButton("Update data");
  b3=new JButton("Search a Student");
  b4=new JButton("Promote all Students");
  b1.setBounds(40,60,250,40);
  b2.setBounds(40,110,250,40);
  b3.setBounds(40,160,250,40);
  b4.setBounds(40,210,250,40);
  add(b1);
  add(b2);
  add(b3);
  add(b4);
  setLayout(null);
  b1.addActionListener(this);
  b2.addActionListener(this);
  b3.addActionListener(this);
  b4.addActionListener(this);
  setSize(400,400);
  setVisible(true);
}

public void actionPerformed(ActionEvent ae){
  if(ae.getSource()==b1)
  new Insert();
  else if(ae.getSource()==b2)
  new Update();
  else if(ae.getSource()==b3)
  new  Search();
  else if(ae.getSource()==b4)
  new Promote();
}

public static void main(String[] args) {
  new FSwSql();
}
}
