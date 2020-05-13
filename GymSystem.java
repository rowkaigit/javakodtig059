import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import java.sql.*;
import org.sqlite.SQLiteConfig;

public class GymSystem { //tig059javakoden e liksom hela altting, sqlitejdbc plus java

   // Sökväg till SQLite-databas. OBS! Ändra sökväg så att den pekar ut din databas
   public static final String DB_URL = "jdbc:sqlite:C:/Users/zeren/SQlite/slutuppgift.sql";  
   // Namnet på den driver som används av java för att prata med SQLite
   public static final String DRIVER = "org.sqlite.JDBC";  

   public static void main(String[] args) throws IOException {
      Connection conn = null;

      // Kod för att skapa uppkoppling mot SQLite-dabatasen
      try {
         Class.forName(DRIVER);
         SQLiteConfig config = new SQLiteConfig();  
         config.enforceForeignKeys(true); // Denna kodrad ser till att sätta databasen i ett läge där den ger felmeddelande ifall man bryter mot någon främmande-nyckel-regel
         conn = DriverManager.getConnection(DB_URL,config.toProperties());  
      } catch (Exception e) {
         // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sökväg eller om driver inte hittas) så kommer ett felmeddelande skrivas ut
         System.out.println( e.toString() );
         System.exit(0);
      }
      
      
      // PLATS FÖR DEKLARATIONER OCH KOD //
      
 while(true){
	
        Scanner console = new Scanner(System.in);
      
        boolean valid;
        int	Persnr =	0;
        int	Persnr2=	0;
        int	Persnr3=	0;
        int	Persnr4 = 0;
        int	date = 0;
        int	antalTimmar	= 0;
        boolean flag	= true;
        String input =	"";


do{
valid	= true;
System.out.println("Please Select of the Following Alternatives: " +
"\nP (New Person) T (New Tidrapport) L (View Tidrapporter for Person) S (View Total Work Hours for Person) " +
"\nA (View All People and Their Tidrapport) Q (Close Program) ");

input	= console.nextLine();

   if	(!isValid(input)){
   valid	= false;
   System.out.print("Error, You Did Not Enter a Valid Menu Option, ");
   }
   
} while(!valid);





switch (input)	{
		 
  case "P":
		do	{
		 valid =	true;
		 System.out.print("Enter Person's Personummer In the Format of (YYYYMMDD): ");
			
          try {
				  Persnr	= console.nextInt();
				  
				  String	strPersnr =	String.valueOf(Persnr);
				  
				  
				  if (!strPersnr.matches("[0-9]{8}")) {
						throw	new IllegalArgumentException("Error, Wrong Format, Try Again.");
				  }
				  
				  
			 }	catch	(IllegalArgumentException e) {
				  System.out.println(e.getMessage());
				  valid = false;
			 }
			 
		} while (!valid);
			 
		 
        
			String s = JOptionPane.showInputDialog("Enter Person's First Name: "); //ibland så kommer inte joption panelen fram, stäng ner och starta bara om Jgrasp eller liknande
			String firstName = s; //måste ha joption här annars printas bägge system.out.prints samtidigt :c
         
         
		  
		  s = JOptionPane.showInputDialog("Enter Person's Surname: ");
		  String	surName = s;		  
		  
		  
			 
			 try{
          
		  Statement	statement =	conn.createStatement();
		  
		  statement.executeUpdate("INSERT INTO Person (PNr, FNamn, ENamn)" +	"VALUES ('"+Persnr+"', '"+firstName+"', '"+surName+"')");
						  
		  }
		  catch (Exception e){
			 System.err.println("Error! "); 
				System.err.println(e.getMessage());
		  }
			 
				break;
 
 
 
 
 case	"T": 
 
 do {
    valid =	true;
    System.out.print("Enter Person's Personummer In the Format of (YYYYMMDD): ");
       try {
        Persnr2 =	console.nextInt();
        
        String	strPersnr2 = String.valueOf(Persnr2);
        
  
           if (!strPersnr2.matches("[0-9]{8}")) {
         	throw	new IllegalArgumentException("You Printed Wrong Format, Try Again ");
           }
 
             }	catch	(IllegalArgumentException e) {
              System.out.println(e.getMessage());
              valid = false;
             }
 
} while (!valid);


 
  do {
    valid =	true;
    System.out.print("Enter Datum In the Format of (YYYYMMDD): ");
       try {
        date =	console.nextInt();
        
        String	strdate = String.valueOf(date);
  
  
           if (!strdate.matches("[0-9]{8}")) {
         		throw	new IllegalArgumentException("The Entered Datum is in the Wrong Format, Try Again ");
           }
  
             }	catch	(IllegalArgumentException e) {
              System.out.println(e.getMessage());
              valid = false;
             }
 
 
} while (!valid);



   System.out.print("Enter Person's Number of Work Hours: ");
   antalTimmar	= console.nextInt();



 try{
  Statement	statement =	conn.createStatement();
  
  statement.executeUpdate("INSERT INTO Tidbok (PNr, Datum, AntalTimmar)" +	"VALUES ('"+Persnr2+"', '"+date+"', '"+antalTimmar+"')");
  
     }
     catch (Exception e){
      System.err.println("Got an exception! "); 
       System.err.println(e.getMessage());
     }
     
     
break;





 case	"L":
  
   do{
    valid =	true;
    System.out.print("Enter Person's Personummer In the Format of (YYYYMMDD): ");

       try {
        Persnr3 =	console.nextInt();
        
        String	strPersnr3 = String.valueOf(Persnr3);
  
  
           if (!strPersnr3.matches("[0-9]{8}")) {
         		throw	new IllegalArgumentException("The Entered Datum is in the Wrong Format, Try Again ");
           }
  
  
             }	catch	(IllegalArgumentException e) {
              System.out.println(e.getMessage());
              valid = false;
             }
             
 }	while	(!valid);

  
 
 try{
 
Statement statement = conn.createStatement(); 
  String	query	= "SELECT Datum, AntalTimmar from Tidbok where PNr = " + Persnr3;
  
  
      ResultSet rs =	statement.executeQuery(query); 
        while (rs.next()) { 
        
      System.out.println(rs.getInt(1) + " " + rs.getInt(2)); 

  }
  
}	

      catch	(SQLException se)	{ 
       
       }
  
 break; 
		
      
        
		  
 case	"S":		  
 
  do {
    valid =	true;
    System.out.print("Enter Person's Personummer In the Format of (YYYYMMDD): ");
			
          try {
				  Persnr4 =	console.nextInt();
				  
				  String	strPersnr4 = String.valueOf(Persnr4);
				  
					  
					  if (!strPersnr4.matches("[0-9]{8}")) {
							throw	new IllegalArgumentException("The Entered Date is in the Wrong Format, Try Again");
					  }
				  
   					 }	catch	(IllegalArgumentException e) {
   						  System.out.println(e.getMessage());
   						  valid = false;
   					 }
					 
} while (!valid);


try{						 
	  
Statement statement = conn.createStatement(); 
String	query	= "select PNr, sum(AntalTimmar) from Tidbok where PNr = '"+Persnr4+"' group by PNr;";
		  
		ResultSet rs =	statement.executeQuery(query); 
		  while (rs.next()) { 
			  
				System.out.println("Person With Personnummer "	+ rs.getInt(1)	+ " "	+ "Has Total Work Hours Of: " +	rs.getInt(2) +	" Hours"); 


		  }
		  
	 }	catch	(SQLException se)	{ 
	 
	  }

break;


 case "A":
 
 try{
						 	  
Statement statement = conn.createStatement(); 
  String	query	= "SELECT Person.PNr, Person.Fnamn, Person.ENamn, Tidbok.Datum, Tidbok.AntalTimmar FROM Person left JOIN Tidbok ON Person.PNr = Tidbok.PNr";
  
		  
		ResultSet rs =	statement.executeQuery(query); 
		  while (rs.next()) { 
			  
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3) + "     " + rs.getInt(4) + "  " + rs.getInt(5)); 

		  }
        
	 }	catch	(SQLException se)	{ 
	 
	 
	 }


break;
 
 
 

case "Q":
 
 System.exit(0); 
 
 break;
  
		  
	}  
		  
}
	 
	}  
	 
	 public static	boolean isValid (String	n){ 
switch(n){
case "P":
case "T":
case "L":
case "S":
case "A":
case "Q":
return true;
default:
return false;
     }
   }
 }