import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
//import javax.swing.JPanel;
//import java.awt.Graphics;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class App extends JFrame implements ActionListener {
JButton Button;
JTextField text;
Container c;

  public App() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Event Test");
    setSize(800, 300);
    c = getContentPane();

    text = new JTextField(50);

    Button = new JButton("SAVE");
    Button.addActionListener(this);

    c.add(text);
    c.add(Button);
    c.setBackground(Color.black);
    c.setLayout(new FlowLayout());
    
//myPanel p = new myPanel();
//c.add(p);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == Button) {
      JLabel label = new JLabel("Save");
      label.setForeground(Color.RED);
      JOptionPane.showMessageDialog(this, label);
      step1();
    }   
  }

//class myPanel extends JPanel {
//  @Override
//    public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    g.drawString("Hello.", 150, 100);
//    }
//}

  public static void main(String[] args) {
      App et = new App();
      et.setVisible(true);
  }

public void step1()
  {
    URL url = null;
    HttpURLConnection urlConnection = null;
    try {
        try{
            url = new URL(text.getText());
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setInstanceFollowRedirects(true);
        } catch (MalformedURLException e2){
            System.err.println("Malformed URL");
        }
        
    } catch (IOException  e) {
        e.printStackTrace();
    }
    
    InputStream in = null;
    
    try {
        in = urlConnection.getInputStream();
    }
    catch( IOException e) {
        e.printStackTrace();
    }
    
    byte[] buf = new byte[4096];
    int readSize;
    int total = 0;
    try {
        FileOutputStream fos = new FileOutputStream(".//TestJava_App.jpg");
        while( ( (readSize = in.read(buf) ) != -1 ) )
        {
            total = total + readSize;
            fos.write(buf,0,readSize);
        }
        fos.flush();
        fos.close();
        in.close();
    } catch( FileNotFoundException e ) {
        System.out.println("ERROR!!" );
    } catch(IOException e ) {
        e.printStackTrace();
    }

    System.out.println("Size:" + total );
  }

}
