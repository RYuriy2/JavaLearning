package ru.soap;

import org.testng.annotations.Test;
import ru.lavasoft.GeoIPService;
import ru.lavasoft.GetIpLocation;

public class GeoIPServiceTest {

    @Test
    public void testMyIp(){
        String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("46.164.232.224");
        System.out.println(ipLocation20);
    }
}
