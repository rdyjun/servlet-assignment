package com.dongyang.moviewreviewweb.moviereviewer.member.service;

import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DeleteAccountService {
    void deleteAccount (HttpSession httpSession) throws SQLException, ClassNotFoundException;
    void deleteAccountAtDB (String userId, Connection connection) throws SQLException;
    int deleteReviewAtAccount (String userId, Connection connection)  throws SQLException, ClassNotFoundException;
}