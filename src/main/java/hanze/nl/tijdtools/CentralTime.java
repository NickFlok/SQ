package hanze.nl.tijdtools;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;

public class CentralTime {

    public static Tijd getCentralTime()
    {
        try {
            HTTPFuncties httpFuncties = new HTTPFuncties();
            String result = httpFuncties.executeGet("xml");
            XStream xstream = new XStream();
            xstream.alias("Tijd", Tijd.class);
            return (Tijd)xstream.fromXML(result);

        } catch (IOException e) {
            e.printStackTrace();
            return new Tijd(0,0,0);
        }
    }
}
