import java.util.Scanner;

public class App {
	
	public static int celaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = celaCisla(sc);
		}
		return cislo;
	}
	
	public static int RokChyba(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("zadejte prosim platný rok narozeni ");
			sc.nextLine();
			cislo = RokChyba(sc);
		}
		return cislo;
	}
	
	public static void main(String[] args) {
		Connect pripojeni = new Connect();
		pripojeni.connect();
		pripojeni.vytvorTable();
		pripojeni.disconnect();
		
		Databaze mojeDatabaze=new Databaze();
		mojeDatabaze.nactiDatabazi();
		
		Scanner sc=new Scanner(System.in);
		
		int volba;
		boolean run=true;
		while(run)
		{
			System.out.println();
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1  - vlozeni studenta");
			System.out.println("2  - pridani znamky studentovi");
			System.out.println("3  - propusteni studenta");
			System.out.println("4  - vyhledani studenta");
			System.out.println("5  - spustit dovednost studenta");
			System.out.println("6  - vypis studentu");
			System.out.println("7  - vypis prumeru v oborech");
			System.out.println("8  - vypis poctu studentu v oborech");
			System.out.println("9  - uloz studenta do souboru");
			System.out.println("10 - nacti studenta ze souboru");
			System.out.println("11 - zavrit program");
			
			volba=celaCisla(sc);
			switch(volba) {
				case 1:
					mojeDatabaze.pridejStudenta();
					break;
					
				case 2:
					int ID;
					int znamka;
					System.out.println("Zadejte ID studenta a znamku: ");
					ID = celaCisla(sc);
					znamka = sc.nextInt();
					mojeDatabaze.addZnamka(ID, znamka);
					break;
					
				case 3:
					int ID_remove;
					System.out.println("Zadejte ID studenta: ");
					ID_remove = celaCisla(sc);
					mojeDatabaze.propustStudenta(ID_remove);
					break;
				
				case 4:
					int ID_show;
					System.out.println("Zadejte ID studenta: ");
					ID_show = celaCisla(sc);
					mojeDatabaze.zobrazStudenta(ID_show);
					break;
					
				case 5:
					int ID_skill;
					System.out.println("Zadejte ID studenta: ");
					ID_skill = celaCisla(sc);
					mojeDatabaze.spustitDovednost(ID_skill);
					break;
					
				case 6:
					mojeDatabaze.vypisDlePrijmeni();
					break;
				
				case 7:
					mojeDatabaze.obecnyPrumer();
					break;
					
				case 8:
					mojeDatabaze.vypisVeSkupinach();
					break;
				
				case 9:
					String jmenoSouboru;
					int ID_save;
					System.out.println("Zadejte ID studenta a nazev souboru: ");
					
					ID_save = sc.nextInt();
					jmenoSouboru = sc.next();
					mojeDatabaze.ulozStudenta(jmenoSouboru, ID_save);
					break;
					
				case 10:
					String soubor;
					System.out.println("Zadejte nazev souboru: ");
					soubor = sc.next();
					mojeDatabaze.nactiStudenta(soubor);
					break;
				
				case 11:
					mojeDatabaze.ulozDatabazi();
					run = false;
					break;
			}
		}
	}
}
