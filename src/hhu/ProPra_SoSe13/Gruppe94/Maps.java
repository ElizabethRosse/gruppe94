package hhu.ProPra_SoSe13.Gruppe94;

public class Maps {
	private int i = 1;
	
	private int pos11[];
	private int pos12[];
	private int pos13[];
	
	private int pos21[];
	private int pos22[];
	private int pos23[];
	
	public int[] getPos1() {
		
		if (i == 1) {
			return pos11;
		}
		
		if (i == 2) {
			return pos12;
		}
		
		if (i == 3) {
			return pos13;
		}
		else {
			return pos11; //noch ändern zu einer Fehlermeldung
		}
	}
	
	public int[] getPos2() {

		if (i == 1) {
			i = i + 1;
			return pos21;
		}
		
		if (i == 2) {
			i = i + 1;
			return pos22;
		}
		
		if (i == 3) {
			i = i + 1;
			return pos23;
		}
		else {
			return pos21; //noch ändern zu einer Fehlermeldung
		}
	}
}
