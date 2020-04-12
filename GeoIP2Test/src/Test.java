import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;

/**
 * 
 */

/**
 * @author Akash
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws GeoIp2Exception 
	 */
	public static void main(String[] args) throws GeoIp2Exception {
		// TODO Auto-generated method stub
		String ip="103.91.100.207";
		String path="E://Spring//GeoLite2-City_20181009//GeoLite2-City.mmdb";
		File db=new File(path);
		
		try {
			DatabaseReader reader=new DatabaseReader.Builder(db).build();
			InetAddress ipaddr=InetAddress.getByName(ip);
			CityResponse city=reader.city(ipaddr);
			System.out.println(city);
			Country country=city.getCountry();
			System.out.println(country);
			System.out.println(country.getName());
			System.out.println(country.getIsoCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
