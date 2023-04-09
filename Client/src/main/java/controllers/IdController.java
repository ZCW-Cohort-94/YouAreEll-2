package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import models.Id;

public class IdController {
    private HashMap<String, Id> allIds;

    Id myId;

    public ArrayList<Id> getIds() {
        return new ArrayList<>(allIds.values());
    }

    public void setMyId(Id id){
        myId = id;
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj"

        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}