package DAO;
/*
 * Insert an account into the account table.
 * 
 * create table account (
    account_id int primary key auto_increment,
    username varchar(255) unique,
    password varchar(255)
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Util.ConnectionUtil;


public class AccountDAO {
    

    public Account insertAccount(Account account){
        // see insertAuthor in AuthorDAO
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO account ( username, password) values ( ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString and setInt methods here.
            //preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next())
            {
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }  
         catch(SQLException e){System.out.println(e.getMessage());}
        return null;
    } 

    
    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "select * from Account where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password")) ;
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
       return null ;
    } 
      // return false;
    }



