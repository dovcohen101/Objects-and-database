package login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Login extends JFrame implements ActionListener
{
    JFrame frame = new JFrame("Login");
    JPanel panel = new JPanel();
    
    JLabel lblUsername = new JLabel("Username");
    JTextField txtUsername = new JTextField(20);
    
    JLabel lblPassword = new JLabel("Password");
    JTextField txtPassword = new JTextField(20);
    
    JButton btnOk = new JButton("Ok");
    JButton btnCancel = new JButton("Cancel"); 

    public static void main(String[] args)
    {
        Login log = new Login();
        log.myLogin();     
    }
    
    public void myLogin()
    {
        panel.setLayout(new GridLayout(3,2,2,3));
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnOk);  
        panel.add(btnCancel);
        
        //btnOk.setPreferredSize(new Dimension(5,5));
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);

        frame.add(panel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300,160);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        String u = txtUsername.getText();
        String p = txtPassword.getText();
        
        if (ob == btnOk)
        {
            try
            {
                Class<registerClass> classobj = registerClass.class;
                Object regObject = classobj.newInstance();
                
                Method met = classobj.getDeclaredMethod("validate_login", new Class[]{String.class, String.class});
                met.setAccessible(true);
                met.invoke(regObject, u,p);
            }
            catch (Exception ez)
            {
                System.out.println(ez);
            }      
            
        }
        
        if (ob == btnCancel)
        {
              System.exit(0);
        }
    }
    
}
