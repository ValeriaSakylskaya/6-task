package com.task;

import Server.Server;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Server server = new Server();
        server.run();
    }
}