package com.academicprojects.DbUtilities;

import java.sql.*;

public class DbConnection {


	Connection conn;                                                //our connnection to the db - presist for life of program

	public DbConnection(String db_file_name_prefix) throws Exception {    // note more general exception

		Class.forName("org.hsqldb.jdbcDriver");

		conn = DriverManager.getConnection("jdbc:hsqldb:"
				+ db_file_name_prefix,    // filenames
				"sa",                     // username
		"");                      // password
	}

	public void shutdown() throws SQLException {

		Statement st = conn.createStatement();

		st.execute("SHUTDOWN");
		conn.close();    // if there are no other open connection
	}

    public synchronized int getLastIndex(String tableName, String indexColumnName) throws SQLException {

        PreparedStatement ps = null;
        String expression = "Select MAX("+indexColumnName+") from "+tableName;
        ps = conn.prepareStatement(expression);         // statement objects can be reused with
        ResultSet rs = ps.executeQuery();// run the query
        int lastIndex = (rs.next()) ? rs.getInt(1) : -1;
        ps.close();    // NOTE!! if you close a statement the associated ResultSet is

        return lastIndex;
    }

    public synchronized void insertPatterns(String pattern, int id_pat, int id_sit) throws SQLException {

		PreparedStatement ps = null;
		String expression = "INSERT INTO PATTERNS_TO_RECOGNIZE_TOPIC (id_pat, pattern, id_sit) VALUES (?,?,?)";
		ps = conn.prepareStatement(expression);         // statement objects can be reused with
		ps.setInt(1, id_pat);
		ps.setString(2, pattern);
		ps.setInt(3, id_sit);
		ps.executeUpdate();// run the query

		ps.close();    // NOTE!! if you close a statement the associated ResultSet is

	}

    public synchronized void insertUserAnswer(int id, String answer, int id_situation, int note, int weight) throws SQLException {

        PreparedStatement ps = null;
        String expression = "INSERT INTO USERANSWERS (id, useranswer, id_situation, note,weight) VALUES (?,?,?,?,?)";
        ps = conn.prepareStatement(expression);         // statement objects can be reused with
        ps.setInt(1, id);
        ps.setString(2, answer);
        ps.setInt(3, note);
        ps.setInt(4, id_situation);
        ps.setInt(5, weight);
        ps.executeUpdate();// run the query

        ps.close();    // NOTE!! if you close a statement the associated ResultSet is
    }
	
	public synchronized void insertAverbs(String adverb, int id_adv, int statNote) throws SQLException {

		PreparedStatement ps = null;
		String expression = "INSERT INTO adverb (id_adv, adverb, statNote) VALUES (?,?,?)";
		ps = conn.prepareStatement(expression);         // statement objects can be reused with
		ps.setInt(1, id_adv);
		ps.setString(2, adverb);
		ps.setInt(3, statNote);
		ps.executeUpdate();// run the query

		ps.close();    // NOTE!! if you close a statement the associated ResultSet is
	}
	
	public synchronized void insertSituation(int i, String term, int lcu) throws SQLException {

		Statement st = null;
		//String expression = "Select * from PERSONALITY";
		
		String expression = "INSERT INTO situations(id, term, lcu) VALUES ("+i+",'"+term+"',"+lcu+");";
		st = conn.createStatement();         // statement objects can be reused with
		
		st.executeQuery(expression);    // run the query
		

		st.close();    // NOTE!! if you close a statement the associated ResultSet is
	}
	
	public synchronized void insertPhrase(int id, String word, int id_per, int lev)
	{
		PreparedStatement ps = null;
		String expression = "INSERT INTO phrase(id, word, id_per, level) VALUES(?,?,?,?)";
		try {
			ps = conn.prepareStatement(expression);         // statement objects can be reused with
			ps.setInt(1, id);
			ps.setString(2, word);
			ps.setInt(3, id_per);
            ps.setInt(4, lev);

			ps.executeUpdate();// run the query
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public synchronized void insertChatbotanswers(int id, String answer, int note)
    {
        PreparedStatement ps = null;
        String expression = "INSERT INTO chatbotanswers(id, answer, user_answer_note) VALUES(?,?,?)";
        try {
            ps = conn.prepareStatement(expression);         // statement objects can be reused with
            ps.setInt(1, id);
            ps.setString(2, answer);
            ps.setInt(3, note);

            ps.executeUpdate();// run the query
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void insertExceptionsChatbotanswers(int id, String answer, int note)
    {
        PreparedStatement ps = null;
        String expression = "INSERT INTO exceptions_chatbotanswers(id, answer, user_answer_note) VALUES(?,?,?)";
        try {
            ps = conn.prepareStatement(expression);         // statement objects can be reused with
            ps.setInt(1, id);
            ps.setString(2, answer);
            ps.setInt(3, note);

            ps.executeUpdate();// run the query
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public synchronized void insertUseranswers(int id_uans, String uanswer, int answernote, int id_atyp)
	{
		PreparedStatement ps = null;
		String expression = "INSERT INTO useranswers(id_uans, uanswer, answernote, id_atyp) VALUES(?,?,?,?)";
		try {
			ps = conn.prepareStatement(expression);         // statement objects can be reused with
			ps.setInt(1, id_uans);
			ps.setString(2, uanswer);
			ps.setInt(3, answernote);
			ps.setInt(4, id_atyp);
			
			ps.executeUpdate();// run the query
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void clearTable(String table) {
        PreparedStatement ps = null;
        String expression = "DELETE FROM "+table;
        try {
            ps.executeUpdate();// run the query
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



