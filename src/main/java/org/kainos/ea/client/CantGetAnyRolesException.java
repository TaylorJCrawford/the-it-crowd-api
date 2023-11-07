package org.kainos.ea.client;

public class CantGetAnyRolesException extends Throwable {
    @Override
    public String getMessage() {
        return "Can't Get Roles";
    }

}
