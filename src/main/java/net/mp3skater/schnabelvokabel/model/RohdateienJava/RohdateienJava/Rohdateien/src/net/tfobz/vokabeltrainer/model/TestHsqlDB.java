package net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model;

import java.sql.*;
import java.util.Properties;

public class TestHsqlDB
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		try {
			// Registrieren des JDBC-Treibers f�r HSQLDB
			Class.forName("org.hsqldb.jdbcDriver");
			
			// Definition der Verbindungseigenschaften
			Properties p = new Properties();
			// Systemadministrator "SA" mit Passwort "" meldet sich an
			p.setProperty("user","SA");
			p.setProperty("password","");
			// Wenn das letzte Verbindungsobjekt zur Datenbank geschlossen wird,
			// wird ein SHUTDOWN durchgef�hrt, welches Daten�nderungen in 
			// .script-Datei schreibt
			p.setProperty("shutdown","true");
			// Datenbank wird angelegt falls nicht vorhanden
			// �ffnen der Datenbankverbindung. "file" bedeutet dass die Datenbank
			// aus Memory-Tabellen besteht. "mem" w�rde ausschlie�lich Temp-Tabellen
			// anlegen
			con = 
				DriverManager.getConnection("jdbc:hsqldb:file:testdb",p);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			// Anlegen der Testtabelle mit autoinkrementierenden Prim�rschl�ssel
			// falls noch nicht existent
			try {
				stmt.executeUpdate(
					"CREATE TABLE test( " +
					"  nummer INTEGER " +
					"    GENERATED BY DEFAULT AS IDENTITY (START WITH 1) " +
					"    NOT NULL PRIMARY KEY, " +
					"  name VARCHAR(100));");
			} catch (SQLException e) {
				// Fehler tritt auf, falls Tabelle schon existiert, tue also nichts
			}
			// Datens�tze einf�gen
			stmt.executeUpdate(
				"INSERT INTO test(name) VALUES ('Sepp');");
			stmt.executeUpdate(
				"INSERT INTO test(name) VALUES ('Rudi');");
			// Datens�tze abfragen
			ResultSet rs = stmt.executeQuery(
				"SELECT * " +
				"  FROM test;");
			while (rs.next())
				System.out.println(rs.getInt("nummer") + " " + rs.getString("name"));
			con.commit();
		} catch (SQLException e) {
			System.out.println(e.getClass().getName() + " " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getClass().getName() + " " + e.getMessage());
		} finally {
			try { stmt.close(); } catch (Exception e) { ; }
			try { con.close(); } catch (Exception e) { ; }
		}
	}

}
