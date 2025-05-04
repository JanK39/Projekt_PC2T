public class Stud_kyber extends Student_p {

	public Stud_kyber(String jmeno, String prijmeni, int rok_narozeni) {
		super(jmeno, prijmeni, rok_narozeni);
	}

	@Override
	public void dovednost() {
		System.out.println("Jmeno v hashi: " + getJmeno().hashCode());
		System.out.println("Prijmeni v hashi: " + getPrijmeni().hashCode());
	}

}
