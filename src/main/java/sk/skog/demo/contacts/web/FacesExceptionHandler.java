package sk.skog.demo.contacts.web;

import javax.faces.context.ExceptionHandler;

import org.omnifaces.exceptionhandler.FacesMessageExceptionHandler;
import org.omnifaces.util.Exceptions;

public class FacesExceptionHandler extends FacesMessageExceptionHandler {
    
    public FacesExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }
    
    @Override
    protected String createFatalMessage(Throwable exception) {
        Throwable unwrappedException = Exceptions.unwrap(exception);
        return unwrappedException.getMessage() == null ? unwrappedException.toString() : unwrappedException.getMessage();
    }
    
}