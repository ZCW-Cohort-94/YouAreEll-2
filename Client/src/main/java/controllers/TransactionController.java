package controllers;

import models.Id;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder s = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            s.append(inputLine).append("\n");
        }
        in.close();
        System.out.println(s);
        return s.toString();
    }
}
