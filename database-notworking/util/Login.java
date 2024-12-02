package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class Login {
    
    public LoginSession authenticateUser(Connection conn, String email, String password) {
        LoginSession session = new LoginSession();
        
        try {
            CallableStatement stmt = conn.prepareCall("{CALL LoginUser(?, ?, ?, ?, ?)}");
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.registerOutParameter(3, java.sql.Types.INTEGER);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            stmt.execute();
            
            String status = stmt.getString(5);
            if ("Login Successful".equals(status)) {
                int personId = stmt.getInt(3);
                String userType = stmt.getString(4);
                session.setAuthenticated(true);
                session.setPersonId(personId);
                session.setUserType(userType);
            } else {
                session.setAuthenticated(false);
                session.setStatus(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAuthenticated(false);
            session.setStatus("Database error: " + e.getMessage());
        }
        
        return session;
    }
	
//	public LoginSession authenticateUser(Connection conn, String email, String password) {
//	    LoginSession session = new LoginSession();
//	    
//	    try {
//	        // Call stored procedure for user authentication
//	        CallableStatement stmt = conn.prepareCall("{CALL LoginUser(?, ?, ?, ?, ?)}");
//	        stmt.setString(1, email);
//	        stmt.setString(2, password);
//	        stmt.registerOutParameter(3, java.sql.Types.INTEGER);  // personId
//	        stmt.registerOutParameter(4, java.sql.Types.VARCHAR);   // userType
//	        stmt.registerOutParameter(5, java.sql.Types.VARCHAR);   // status message
//	        
//	        stmt.execute();
//	        
//	        String status = stmt.getString(5);
//	        
//	        // If login is successful
//	        if ("Login Successful".equals(status)) {
//	            int personId = stmt.getInt(3);
//	            String userType = stmt.getString(4);
//	            
//	            // Now retrieve the card number from the database
//	            String sqlCardNumber = "SELECT card_number FROM RegisteredCustomer WHERE person_id = ?";
//	            PreparedStatement stmtCard = conn.prepareStatement(sqlCardNumber);
//	            stmtCard.setInt(1, personId);
//	            ResultSet rs = stmtCard.executeQuery();
//	            
//	            if (rs.next()) {
//	                String cardNumber = rs.getString("card_number");
//	                session.setCardNumber(cardNumber);  // Set card number in session
//	            }
//
//	            // Set session attributes
//	            session.setAuthenticated(true);
//	            session.setPersonId(personId);
//	            session.setUserType(userType);
//	        } else {
//	            session.setAuthenticated(false);
//	            session.setStatus(status);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        session.setAuthenticated(false);
//	        session.setStatus("Database error: " + e.getMessage());
//	    }
//	    
//	    return session;
//	}


}