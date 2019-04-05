package login;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class registerClass
{

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    String name = "";
    String surname = "";
    int studentNo = 0;
    String subject = "";

    ArrayList<Student> stu = new ArrayList<Student>();
    int [] studentNum;
    
    private boolean validate_login(String username, String password)
    {
        boolean login = false;
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/students", "root", "");//first is the path, second is the username, third is the password
            System.out.println(conn);

            String sql = "Select * from login where Username = ? and Password = ?";

            try
            {
                pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);

                rs = pst.executeQuery();

                if (rs.next())
                {
                    JOptionPane.showMessageDialog(null, "Signed In");
                    login = true;
                    studentpresent();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
            catch (Exception e)
            {
                System.out.println("Error with login: " + e);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error with connection: " + e);
        }

        return login;
    }

    public void studentpresent() throws SQLException
    {
        int num = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount of students"));
        studentNum = new int [num];

        for (int i = 0; i < num; i++)
        {
            name = JOptionPane.showInputDialog("Please enter the name of the student");//gathering student name
            surname = JOptionPane.showInputDialog("Please enter the surname of the student");//gathering student surname
            studentNo = Integer.parseInt(JOptionPane.showInputDialog("Please enter the student number of the student"));//gathering student number
            subject = JOptionPane.showInputDialog("Please enter the subject of the student");//gathering student subject

            Student s = new Student(name, surname, studentNo, subject);//sending details to object
            stu.add(s);//adding object to arraylist
            studentNum[i] = studentNo;//adding student number to int array
        }

        insertDetails();
    }

    public void insertDetails() throws SQLException
    {
        Iterator i = stu.iterator();//creating an iterator of the arraylist

        java.util.Date d = new java.util.Date();
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");

        while (i.hasNext())
        {
            Student s = (Student) i.next();

            String sql = "INSERT INTO `students`.`register` (`Name`, `Surname`, `StudentNo`, `Subject`, `Date`) VALUES ('" + s.name + "', '" + s.surName + "','" + s.studentNo + "', '" + s.subject + "', '" + df.format(d) + "');";

            pst = conn.prepareStatement(sql);
            pst.execute();
            
            System.out.println("Name: " + s.name + "\n" + "Surname: " + s.surName + "\n" + "StudentNo: " + s.studentNo + "\n" + "Subject: " + s.subject + "\n" + "Date: " + d);
            
        }
        System.out.println("successfully inserted into the database");
       
    }
}
