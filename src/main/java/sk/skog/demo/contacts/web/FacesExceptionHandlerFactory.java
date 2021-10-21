package sk.skog.demo.contacts.web;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.omnifaces.exceptionhandler.FacesMessageExceptionHandlerFactory;

public class FacesExceptionHandlerFactory extends FacesMessageExceptionHandlerFactory {
    
    public FacesExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
        super(wrapped);
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new FacesExceptionHandler(getWrapped().getExceptionHandler());
    }

}
