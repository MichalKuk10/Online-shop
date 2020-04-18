package controllers;

import exceptions.MyExceptions;

import java.sql.SQLException;

public interface RunnableController {
    void run() throws SQLException, MyExceptions;
}
