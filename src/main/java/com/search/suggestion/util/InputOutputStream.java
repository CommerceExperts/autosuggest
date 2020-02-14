package com.search.suggestion.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.search.suggestion.data.RawResponse;
import com.search.suggestion.data.RawSearchUpdateRequest;
import com.search.suggestion.interfaces.ServerInterface;

/**
 * Created by nikhil on 29/7/17.
 */
public class InputOutputStream implements Runnable{

    private ServerInterface server;
    private Socket socket;
    public InputOutputStream(ServerInterface server)
    {
        this.server = server;
    }
    @Override
    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(server.getPort());
        Logger.log("Now listening on port "+server.getPort());
        while (true)
        {
            socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String input = "";

            input = br.readLine();

            try (OutputStream os = socket.getOutputStream()){

                ObjectMapper objectMapper = new ObjectMapper();
                RawSearchUpdateRequest rawRequest = objectMapper.readValue(input, RawSearchUpdateRequest.class);

                List<RawResponse> list = this.server.getResponse(rawRequest);
                objectMapper.writeValue(os, list);

            } catch(Exception e) {

                e.printStackTrace();
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write("Server Error");
                bw.flush();
                bw.close();

            }

        }
    }
}
