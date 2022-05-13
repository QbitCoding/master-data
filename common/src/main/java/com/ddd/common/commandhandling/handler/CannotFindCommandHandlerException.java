package com.ddd.common.commandhandling.handler;


import com.ddd.common.commandhandling.CommandException;

/**
 * CannotFindCommandHandlerException
 *
 **/
public class CannotFindCommandHandlerException extends CommandException {

    public CannotFindCommandHandlerException(String msg) {
        super(msg);
    }
}
