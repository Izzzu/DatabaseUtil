package com.academicprojects.DbUtilities;

import com.academicprojects.util.DictionaryMaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) throws SQLException {
		DbConnection db = null;
		File file = new File("phrase.txt");
		File fileSituations = new File("situations.txt");
		File filePattern = new File("situations/1.txt");
		File fileAdv = new File("adverbs.txt");
		File fileChatbotAnswers = new File("chatbotanswers.txt");
		File fileUAWorries = new File("worries.txt");


        DictionaryMaker dictionaryMaker = new DictionaryMaker();

		try {
            dictionaryMaker.main();
            //db.shutdown();
			//db = new DbConnection("C:/Users/Cookiemonster/workspace/Chatbot/db/chatbotDb");


            /*for(int i = 0; i<10; i++) {
                if (i == 8) continue;
                fillUseranswersTableFromManyFiles(db, new File("uanswer" + i + ".txt"));
            }*/
            //fillUseranswersTableFromManyFiles(db, new File("ua.txt"));
            //fillSituations(db, new File("situations.txt"));  //fileSituations);
            //fillExceptionsChatbotAnswers(db, new File("situations/exception_chatbotanswers.txt"));
            //fillChatbotAnswers(db, new File("chatbotanswers_nowe.txt"));
            //fillPhrasesForPersonality(db, new File("phrase.txt"));
            // TODO Auto-generated catch block
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) { 
			e.printStackTrace();
		}
        catch (Exception e) {

        }
        finally {

           // db.shutdown();
        }
		System.out.println( "Hello World!" );
	}

    private static void fillUseranswersTableFromManyFiles(DbConnection db, File filePattern) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new FileReader(filePattern));
        String s = null;

        int i =db.getLastIndex("useranswers","id")+1;
        while ((s=br.readLine()) != null)
        {
            String [] tab = s.split(" ");
            db.insertUserAnswer(i, tab[0], Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]));
            i++;
        }
        br.close();
    }

    private static void fillPhrasesForPersonality(DbConnection db, File file) throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        System.out.println("lol1");
        //db.clearTable("phrase");
        System.out.println("lol2");
        int i =db.getLastIndex("phrase","id")+1;
        System.out.println("lol3");
        while ((s=br.readLine()) != null)
        {
            System.out.println("lol");
            String [] tab = s.split(" ");

            db.insertPhrase(i, tab[0].replace("_", " "), Integer.parseInt(tab[1]), Integer.parseInt(tab[2]));
            i++;
        }
        br.close();

    }


    private static void fillChatbotAnswers(DbConnection db, File file) throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        db.clearTable("chatbotanswers");
        int i = db.getLastIndex("chatbotanswers","id")+1;
        while ((s=br.readLine()) != null)
        {
            String [] tab = s.split(" ");

            db.insertChatbotanswers(i, tab[0].replace("\\"," "), Integer.parseInt(tab[1]));
            i++;
        }
        br.close();

    }



    private static void fillExceptionsChatbotAnswers(DbConnection db, File file) throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;

        int i =db.getLastIndex("exceptions_chatbotanswers","id")+1;
        while ((s=br.readLine()) != null)
        {
            //String [] tab = s.split(" ");

            db.insertExceptionsChatbotanswers(i, s, -1);
            i++;
        }
        br.close();

    }
}
