package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class VeriTabani {
	
	public boolean kullaniciVarmi(String id,String parola) {
		
		PreparedStatement ifade = null;
		ResultSet sonuc =null;
		Connection c = null;
		java.sql.Statement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kullanici","postgres", "12345678");
			
			
			stmt = c.createStatement();			
			sonuc = stmt.executeQuery("SELECT *FROM kullanicilar");
			
			while(sonuc.next()) {
				if(id.compareTo(sonuc.getString("id"))==0&&parola.compareTo(sonuc.getString("sifre"))==0) {
					c.close();
					return true;
				}
					
			}
			c.close();
			return false;
		
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+": "+e.getMessage());
		//	System.exit(0);
			return false;
		}
		
	}
	
	public boolean kisiVarmi(String tc) {
		
		
		PreparedStatement ifade = null;
		ResultSet sonuc =null;
		Connection c = null;
		java.sql.Statement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/liste","postgres", "12345678");
			
			
			stmt = c.createStatement();			
			sonuc = stmt.executeQuery("SELECT *FROM kisiler");
			
			while(sonuc.next()) {
				if(tc.compareTo(sonuc.getString("tcno"))==0) {
					c.close();
					return true;
				}
					
			}
			c.close();
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+": "+e.getMessage());
		//	System.exit(0);
			return false;
		}
		
		
	}
	
	public Insan goruntule(String tcNo) {		
		PreparedStatement ifade = null;
		ResultSet sonuc =null;
		Connection c = null;
		java.sql.Statement stmt = null;
		Insan insan = new Insan();
		
		
		if(kisiVarmi(tcNo)) {			
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/liste","postgres", "12345678");		
				
				stmt = c.createStatement();		
				sonuc = stmt.executeQuery("SELECT * FROM kisiler where tcno='"+tcNo+"'");			
				
				sonuc.next();
				
				insan.İsim = sonuc.getString("isim");
				insan.Soyİsim = sonuc.getString("soyisim");
				insan.TcNo = sonuc.getString("tcno");
				insan.DoğumTarih = sonuc.getString("dogum");
				insan.DoğumYeri = sonuc.getString("dogumyer");
				insan.Cinsiyet = sonuc.getString("cinsiyet");
				insan.BabaAdı = sonuc.getString("baba");
				insan.AnneAdı = sonuc.getString("anne");			
				insan.CezaiDurum = sonuc.getString("cezai");
				
				insan.SabıkaKaydı1 = sonuc.getString("s1");
				insan.SabıkaKaydıTarih1 = sonuc.getString("s1tarih");
				insan.SabıkaKaydı2 = sonuc.getString("s2");
				insan.SabıkaKaydıTarih2 = sonuc.getString("s2tarih");
				insan.SabıkaKaydı3 = sonuc.getString("s3");
				insan.SabıkaKaydıTarih3 = sonuc.getString("s3tarih");
				insan.SabıkaKaydı4 = sonuc.getString("s4");
				insan.SabıkaKaydıTarih4 = sonuc.getString("s4tarih");
				insan.SabıkaKaydı5 = sonuc.getString("s5");
				insan.SabıkaKaydıTarih5 = sonuc.getString("s5tarih");
				
				c.close();
				return insan;			
												
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getClass().getName()+": "+e.getMessage());
			//	System.exit(0);
				return null;
			}
			
		}
		else
			return null;
			// System.out.println("Ki�i bulunamam�st�r.");
		
		
	}
	
	
	public boolean cezaiGuncelle(String ceza,String tc) {
		
		Connection c = null;
		java.sql.Statement stmt = null;
		
		
		if(kisiVarmi(tc)) {
			
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/liste","postgres", "12345678");
				
				 stmt = c.createStatement();
		         String sql = "UPDATE kisiler set cezai = '"+ceza+"' where tcno='"+tc+"'";
		         stmt.executeUpdate(sql);
		        // System.out.println(tc+" TC nolu ki�inin cezai durumu '"+ceza+"' olarak g�ncellenmi�tir.");
				
		         return true;
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getClass().getName()+": "+e.getMessage());
			//	System.exit(0);
				return false;
			}	
		}
		else
			return false;
		
		
		
		
	}
	
	public boolean sabikaEkle(String tcNo,String sabika,String tarih) {
		
		ResultSet sonuc = null ;
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/liste","postgres", "12345678");
			
			stmt = c.createStatement();		
			sonuc = stmt.executeQuery("SELECT * FROM kisiler where tcno='"+tcNo+"'");			
			
			sonuc.next();
			
			if(sonuc.getString("s1")==null) {
				String sql = "UPDATE kisiler set s1 = '"+sabika+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        sql = "UPDATE kisiler set s1tarih = '"+tarih+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        return true;
			}
			else if(sonuc.getString("s2")==null) {
				String sql = "UPDATE kisiler set s2 = '"+sabika+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        sql = "UPDATE kisiler set s2tarih = '"+tarih+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        return true;
			}
			else if(sonuc.getString("s3")==null) {
				String sql = "UPDATE kisiler set s3 = '"+sabika+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        sql = "UPDATE kisiler set s3tarih = '"+tarih+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        return true;
			}
			else if(sonuc.getString("s4")==null) {
				String sql = "UPDATE kisiler set s4 = '"+sabika+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        sql = "UPDATE kisiler set s4tarih = '"+tarih+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        return true;
			}
			else {
				String sql = "UPDATE kisiler set s5 = '"+sabika+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        sql = "UPDATE kisiler set s5tarih = '"+tarih+"' where tcno='"+tcNo+"'";
		        stmt.executeUpdate(sql);
		        return true;
			}
			 
	       
		//	System.out.println(tcNo+" TC nolu ki�inin sab�ka kayd� g�ncellenmi�tir.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+": "+e.getMessage());
		//	System.out.println("tc no bulunamadi");
		//	System.exit(0);
			return false;
		}
		
	}
	
	public String kullanici(String id,String sifre) {
		
		ResultSet sonuc = null;
		Connection c = null;
		java.sql.Statement stmt = null;
		
		if(kullaniciVarmi(id, sifre)) {
			try {
                            
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kullanici","postgres", "12345678");
				
				 stmt = c.createStatement();
		         sonuc = stmt.executeQuery("SELECT * FROM kullanicilar where id = '"+id+"'");
		         
		         sonuc.next();
		         if(sonuc.getString("Unvan").compareTo("Polis")==0)
		        	 return "Polis";	  // kullan�c�n�n polis olma durumu        
		         else
		        	 return "Memur";    // kullan�c�n�n memur olma durumu
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getClass().getName()+": "+e.getMessage());
			//	System.exit(0);
                           
				return null;
			}
			
		}
                else{
			return null;
                }
		
		
	}
	
	
	
	
	
	
	
	

}
