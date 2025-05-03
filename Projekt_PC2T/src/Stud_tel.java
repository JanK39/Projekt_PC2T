public class Stud_tel extends Student_p {

	public Stud_tel(String jmeno, String prijmeni, int rok_narozeni, int idx) {
		super(jmeno, prijmeni, rok_narozeni, idx);
	}
	
	public void preklad(String slovo) {
		char[] abeceda = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
                'y', 'z'};
		
		String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
		
		slovo = slovo.toLowerCase();
		char[] word = slovo.toCharArray();
		
		for (int i = 0; i < slovo.length(); i++) {
			for (int b = 0; b<abeceda.length; b++) {
				if (word[i] == abeceda[b]) {
					System.out.print(morse[b] + " ");
				}
			}
		}
		System.out.println();
	}
	
	@Override
	public void dovednost() {
		System.out.print("Jmeno v morseove abecede: ");
		preklad(getJmeno());
		
		System.out.print("Prijmeni v morseove abecede: ");
		preklad(getPrijmeni());
	}

}
