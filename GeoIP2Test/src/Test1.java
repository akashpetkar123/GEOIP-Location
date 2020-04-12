import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;

public class Test1 extends JFrame implements ActionListener {

	private JButton btnClick;
	private JLabel lblMessage,lblResponse;
	private JTextArea txtArea;
	private JTextField txtIP;
	
	Test1(){
		btnClick=new JButton("CLICK");
		lblMessage=new JLabel("Enter IP Here");
		lblResponse=new JLabel("");
		txtIP=new JTextField(30);
		txtArea=new JTextArea("Response");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 300);
		setVisible(true);
		setLayout(new FlowLayout());
		
		add(lblMessage);
		add(txtIP);
		add(btnClick);
		add(lblResponse);
		add(txtArea);
		
		btnClick.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new Test1();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		lblResponse.setVisible(false);
		
		String ip=txtIP.getText();
		String path="E://Spring//GeoLite2-City_20181009//GeoLite2-City.mmdb";
		File db=new File(path);
		
		try {
			DatabaseReader reader=new DatabaseReader.Builder(db).build();
			InetAddress ipaddr=InetAddress.getByName(ip);
			CityResponse city=reader.city(ipaddr);
			System.out.println(city);
			String s=city.toString();
			lblResponse.setText(city.toString());
			txtArea.setText(s);
			Country country=city.getCountry();
			System.out.println(country.getName());
			System.out.println(country.getGeoNameId());
			//System.out.println(country.get);
		} catch (IOException e1) {
			lblResponse.setText("No IP Address found in DB");
			e1.printStackTrace();
		} catch (GeoIp2Exception e1) {
			lblResponse.setText("No IP Address found in DB");
			e1.printStackTrace();
		}
		txtArea.setBounds(10,30, 200,200);
		lblResponse.setVisible(true);
	}

}
