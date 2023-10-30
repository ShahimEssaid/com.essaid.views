package com.essaid.model.internal.map;

public interface RequestHandler {

    String GET = "get";
    String CGET = "cget";
    String SET = "set";
    String CSET = "cset";
    String ADD = "add";
    String TO_STRING = "toString";
    String HASH_CODE = "hashCode";
    String EQUALS = "equals";
    String _TO_STRING = "_toString";
    String _HASH_CODE = "_hashCode";
    String _EQUALS = "_equals";

    Object handleRequest(Request request);
}
