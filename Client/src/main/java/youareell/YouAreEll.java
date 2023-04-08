package youareell;

import controllers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll (MessageController m, IdController i){
        this(new TransactionController(m,i));
    }

    public static void main(String[] args) throws IOException {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.makeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.makeURLCall("/messages", "GET", ""));
    }

    private String makeURLCall(String area, String type, String extra) throws IOException {
        URL url = new URL("http://zipcode.rocks:8085" + area);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(type);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder s = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            s.append(inputLine).append("\n");
        }
        in.close();
//        System.out.println(s);
        return s.toString();
    }

    public String get_ids() {
        try {
            return tt.makecall("/ids", "GET", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get_messages() throws IOException {
        try {
            return tt.makecall("/messages", "GET", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
