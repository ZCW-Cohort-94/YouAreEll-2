package youareell;

import controllers.*;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll (MessageController m, IdController i){
        this(new TransactionController(m,i));
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.makeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.makeURLCall("/messages", "GET", ""));
    }

    private String makeURLCall(String s, String get, String s1) {
        return null;
    }

    public String get_ids() {
        return tt.makecall("/ids", "GET", "");
    }

    public String get_messages() {
        return makeURLCall("/messages", "GET", "");
    }


}
