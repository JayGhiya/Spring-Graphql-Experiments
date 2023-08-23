package com.graphql.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class CustomGraphQLResponseWrapper extends HttpServletResponseWrapper {

    public CustomGraphQLResponseWrapper(HttpServletResponse response) {
        super(response);
        //TODO Auto-generated constructor stub
    }

    private final ByteArrayOutputStream capture = new ByteArrayOutputStream();
    private final ServletOutputStream output = new CaptureServletOutputStream(capture);
    private PrintWriter writer;


    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return output;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(capture, getCharacterEncoding()));
        }
        return writer;
    }

    public String getCapturedResponse() {
        return capture.toString();
    }

    private static class CaptureServletOutputStream extends ServletOutputStream {
        private final ByteArrayOutputStream capture;

        public CaptureServletOutputStream(ByteArrayOutputStream capture) {
            this.capture = capture;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
            // No-op
        }

        @Override
        public void write(int b) throws IOException {
            capture.write(b);
        }
    }
}
