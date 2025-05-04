import java.util.ArrayList;
import java.util.List;

public abstract class Student_p {
	private String jmeno;
	private String prijmeni;
	private int rok_narozeni;
	private float prumer = 0;
	List<Integer> seznam_znamek = new ArrayList<Integer>();
	private int idx;
	
	public Student_p(String jmeno, String prijmeni, int rok_narozeni, int idx) {
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.rok_narozeni = rok_narozeni;
		this.idx=idx;
	}
	public void setPrumer(float prumer) {
		this.prumer = prumer;
	}
	
	public String getJmeno() {
		return jmeno;
	}
	
	public String getPrijmeni() {
		return prijmeni;
	}
	
	public int getRokNarozeni() {
		return rok_narozeni;
	}
	
	public float getPrumer() {
		return prumer;
	}
	
	public int getIdx() {
		return idx;
	}
	
	public void pridejZnamku(int znamka) {
		if (znamka<1||znamka>5) {
			System.out.println("Zadana nespravna znamka");
		} else {
			seznam_znamek.add(znamka);
		}
		
		int suma=0;
		int counter=0;
		
		for (int i:seznam_znamek) {
			suma+=i;
			counter+=1;
		}
		prumer = (float)suma/counter;
	}
	
	public abstract void dovednost();
	
	public String toString() {
		if (prumer == 0) {
			return "jmeno: " + jmeno + " prijmeni: " + prijmeni + " rok narozeni: " + 
					rok_narozeni + " studijni prumer: nezadan";
		} else {
			return "jmeno: " + jmeno + " prijmeni: " + prijmeni + " rok narozeni: " + 
					rok_narozeni + " studijni prumer: " + prumer;
		}
		
	}
	
	
}


