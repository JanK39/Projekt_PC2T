import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Databaze {
	
	private Scanner sc;
	ArrayList<Student_p> DatabazeStudentu;
	
	public Databaze() {
		DatabazeStudentu = new ArrayList<Student_p>();
		sc = new Scanner(System.in);
	}
	
	public void pridejStudenta() {
		System.out.print("Zadejte obor studenta(TLI, KYB): ");
		String obor = sc.next();
		obor = obor.toLowerCase();
		if (obor.equals("tli")) {
			System.out.print("Zadejte jmeno, prijmeni a rok narozeni studenta: ");
			String jmeno = sc.next();
			String prijmeni = sc.next();
			int rok_narozeni = App.RokChyba(sc);
			
			Stud_tel Student = new Stud_tel(jmeno, prijmeni, rok_narozeni, DatabazeStudentu.size());
			DatabazeStudentu.add(Student);
			System.out.println("Student uspesne pridan");
			
		} else if (obor.equals("kyb")) {
			System.out.print("Zadejte jmeno, prijmeni a rok narozeni studenta: ");
			String jmeno = sc.next();
			String prijmeni = sc.next();
			int rok_narozeni = App.RokChyba(sc);
			
			Stud_kyber Student = new Stud_kyber(jmeno, prijmeni, rok_narozeni, DatabazeStudentu.size());
			DatabazeStudentu.add(Student);
			System.out.println("Student uspesne pridan");
		} else {
			System.out.println("Neplatny obor");
		}	
	}
	
	public void addZnamka(int id, int znamka) {
		try {
		DatabazeStudentu.get(id).pridejZnamku(znamka);
		System.out.println("Znamka uspesne pridana");
		} catch(InputMismatchException e) {
			System.out.println("Zadana neplatna znamka");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Student neexistuje");
		}
	}
	
	public void propustStudenta(int id) {
		try {
			DatabazeStudentu.remove(id);
			System.out.println("Student propusten");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Student neexistuje");
		}
	}
	
	public void zobrazStudenta(int id) {
		try {
			System.out.print("ID: " + id + ", jmeno: " + DatabazeStudentu.get(id).getJmeno() + 
					", prijmeni: " + DatabazeStudentu.get(id).getPrijmeni() + ", rok narozeni: " + 
					DatabazeStudentu.get(id).getRokNarozeni() + ", prumer: ");
			if (DatabazeStudentu.get(id).getPrumer() == 0) {
				System.out.print("zadna znamka");
			} else {
				System.out.print(DatabazeStudentu.get(id).getPrumer());
			}
			if (DatabazeStudentu.get(id) instanceof Stud_tel) {
				System.out.println(", obor: Telekomunikace");
			} else {
				System.out.println(", obor: Kyberbezpecnost");
			}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Student neexistuje");
		}
	}
	
	public void spustitDovednost(int id) {
		try {
			DatabazeStudentu.get(id).dovednost();
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Student neexistuje");
		}
	}
	
	public boolean vypisDlePrijmeni() {
		
		if (DatabazeStudentu.isEmpty()) {
			System.out.println("Databaze je prazdna");
			return false;
		}
		
		ArrayList<Student_p> studenti_tel = new ArrayList<>();
		ArrayList<Student_p> studenti_kyb = new ArrayList<>();
		
		for (Student_p student:DatabazeStudentu) {
			if (student instanceof Stud_tel) {
				studenti_tel.add(student);
			} else if (student instanceof Stud_kyber) {
				studenti_kyb.add(student);
			}
		}
		
		Collections.sort(studenti_tel, new Comparator<Student_p>() {
			@Override
			public int compare(Student_p s1, Student_p s2) {
				return s1.getPrijmeni().compareTo(s2.getPrijmeni());
			}
		});
		Collections.sort(studenti_kyb, new Comparator<Student_p>() {
			@Override
			public int compare(Student_p s1, Student_p s2) {
				return s1.getPrijmeni().compareTo(s2.getPrijmeni());
			}
		});
		
		if (studenti_tel.isEmpty() == false) {
			System.out.println("Studenti telekomunikaci: ");
			for (Student_p i:studenti_tel) {
				System.out.print("ID: " + DatabazeStudentu.indexOf(i) + " ");
				System.out.println(i);
			}
		}
		
		if (studenti_kyb.isEmpty() == false) {
			System.out.println("Studenti kyberbezpecnosti: ");
			for (Student_p o:studenti_kyb) {
				System.out.print("ID: " + DatabazeStudentu.indexOf(o) + " ");
				System.out.println(o);
			}
		}
		return true;
	}
	
	public void obecnyPrumer() {
		int soucet_tel = 0, counter_tel = 0, soucet_kyb = 0, counter_kyb = 0;
		float prumer_tel, prumer_kyb;
		
		for (Student_p student:DatabazeStudentu) {
			if (student instanceof Stud_tel) {
				for (int znamka:student.seznam_znamek) {
					soucet_tel+=znamka;
					counter_tel+=1;
				}
			}
			else if (student instanceof Stud_kyber) {
				for (int znamka:student.seznam_znamek) {
					soucet_kyb+=znamka;
					counter_kyb+=1;
				}
			}	
		}
		
		
		
		if (counter_tel != 0) {
			prumer_tel = (float)soucet_tel / counter_tel;
			System.out.println("Prumer oboru telekomunikace: " + prumer_tel);
		}
		if (counter_kyb != 0) {
			prumer_kyb = (float)soucet_kyb / counter_kyb;
			System.out.println("Prumer oboru kyberbezpecnosti: " + prumer_kyb);
		}
	}
	
	public void vypisVeSkupinach() {
		int tel = 0, kyb = 0;
		for (Student_p student:DatabazeStudentu) {
			if (student instanceof Stud_tel) {
				tel+=1;
			}
			else if (student instanceof Stud_kyber) {
				kyb+=1;
			}
		}
		System.out.println("Pocet studentu telekomunikaci: " + tel);
		System.out.println("Pocet studentu kyberbezpecnosti: " + kyb);
	}
	
	public void ulozStudenta(String jmenoSouboru, int ID) {
		FileWriter fw = null;
		BufferedWriter out = null;
		try {
			fw = new FileWriter("Karel.txt");
			out = new BufferedWriter(fw);
			
			if (DatabazeStudentu.isEmpty() == false) {
				if (DatabazeStudentu.get(ID) instanceof Stud_tel) {
					out.write("tel ");
				}
				else if (DatabazeStudentu.get(ID) instanceof Stud_kyber) {
					out.write("kyber ");
				}
			
				out.write(DatabazeStudentu.get(ID).getJmeno() + " " + DatabazeStudentu.get(ID).getPrijmeni() +
						" " + DatabazeStudentu.get(ID).getRokNarozeni() + " " + DatabazeStudentu.get(ID).getPrumer());
			}
		}
		catch (IOException e) {
			System.out.println("Soubor nelze vytvorit");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Student neexistuje");
		}
		catch (NullPointerException e) {
			System.out.println("Soubor je prazdny");
		} finally
		{
			try
			{	if (out!=null)
				{
					out.close();
					fw.close();
				}
			System.out.println("Soubor uspesne ulozen");
			}
			catch (IOException e) {
				System.out.println("Soubor  nelze zavrit");
			} 
		}
	}
	
	public void nactiStudenta(String jmenoSouboru) {
		FileReader fr=null;
		BufferedReader in=null;
		try {
			fr = new FileReader(jmenoSouboru);
			in = new BufferedReader(fr);
			String radek=in.readLine();
			String oddelovac = "[ ]+";
			String[] castiRadku = radek.split(oddelovac);
			Student_p student;;
			if (castiRadku[0].equals("tel")) {
				student = new Stud_tel(castiRadku[1], castiRadku[2], Integer.parseInt(castiRadku[3]), DatabazeStudentu.size());
				DatabazeStudentu.add(student);
			}
			else if (castiRadku[0].equals("kyber")) {
				student = new Stud_kyber(castiRadku[1], castiRadku[2], Integer.parseInt(castiRadku[3]), DatabazeStudentu.size());
				DatabazeStudentu.add(student);
			}
			DatabazeStudentu.get(DatabazeStudentu.size() - 1).setPrumer(Float.parseFloat(castiRadku[4]));
			
		}
		catch (IOException e) {
			System.out.println("Soubor  nelze otevrit");
		} 
		catch (NumberFormatException e) {
			System.out.println("Nespravna data v souboru");
		}
		finally
		{
			try
			{	if (in!=null)
				{
					in.close();
					fr.close();
				}
			System.out.println("Student uspesne nacten");
			}
			catch (IOException e) {
				System.out.println("Soubor  nelze zavrit");
			} 
		}
	}
	
	public void ulozDatabazi() {
		Connect pripojeni = new Connect();
		pripojeni.connect();
		
		pripojeni.smazatZaznamy();
		
		for(Student_p student:DatabazeStudentu) {
			if (student instanceof Stud_tel) {
				pripojeni.ulozStudenta(student.getJmeno(), student.getPrijmeni(), student.getRokNarozeni(), student.getPrumer(), "TEL");
			}
			else if (student instanceof Stud_kyber) {
				pripojeni.ulozStudenta(student.getJmeno(), student.getPrijmeni(), student.getRokNarozeni(), student.getPrumer(), "KYB");
			}
			
		}
		pripojeni.disconnect();
	}
	
	public void nactiDatabazi() {
		Connect pripojeni = new Connect();
		pripojeni.connect();
		boolean end = true;
		Student_p student;
		int counter = 0;
		
		ResultSet rs = pripojeni.getResultSetFor_nactiStudenta();
		
		while(end) {
			if ((student = pripojeni.nactiStudenta(rs, counter)) != null) {
				DatabazeStudentu.add(student);
			}
			else {
				end = false;	
			}
			counter+=1;
		}
		pripojeni.disconnect();
	}
}
