package controllers;

import models.Id;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {}

    public List<Id> getIds() {
        return null;
    }
    public String postId(String idtoRegister, String githubName) {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }

    public String makecall(String area, String type, String s1) throws IOException {
        URL url = new URL("http://zipcode.rocks:8085" + area);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(type);
        String id =  area.split("/")[2];
        System.out.println(id);

        if(type.equals("POST")){
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            try(OutputStream os = con.getOutputStream()) { //the blank timestamp was messing me up, but it worked if i just gave it a bad one :shrug emoji:
                String jsonInputString = "{\"sequence\": \"-\", \"timestamp\": \"2023-04-08T18:29:05.604264239Z\", \"fromid\": \"" + id +"\", \"toid\": \"\", \"message\": \""+ s1 +"\"}";
                System.out.println(jsonInputString);
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder s = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            s.append(inputLine).append("\n");
        }
        in.close();
        return s.toString();
    }
}
