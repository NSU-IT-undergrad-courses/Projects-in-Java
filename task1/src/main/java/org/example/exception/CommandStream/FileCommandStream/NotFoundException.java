package org.example.exception.CommandStream.FileCommandStream;

public class NotFoundException extends FleCommandStreamException{
    public NotFoundException(String file) {
        super("Can'not find file: "+file);
    }
}
