
public class InstructionFetch {

	String PCplus4;

	public String InstFetch(String PCaddress) {

		int intPC = Integer.parseInt(PCaddress, 2);
		int index = intPC / 4;
		PCplus4 = String.format("%32s", Integer.toBinaryString(intPC + 4)).replaceAll(" ", "0");
		
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("1-Fetch Stage: \n");
		
		String instruction = MySystem.InstructionMemory[index];
		
		System.out.println("PC: "+ MySystem.PC );
		System.out.println("instruction: "+ instruction+"\n");
		
		return instruction;

	}

	public static void ProgCount() {
		int intPC = Integer.parseInt(MySystem.PC, 2)+4;
		MySystem.PC = String.format("%32s", Integer.toBinaryString(intPC)).replaceAll(" ", "0");
	}
}
