
public class MemoryAccess {

	boolean PCsrc;
	String ReadData;

	public void MemAccess(String ALUresult, String ReadData2, String SignExtend, boolean ZeroFlag,
			String BranchAddressResult, boolean MemWrite, boolean MemRead, boolean Branch) {
		System.out.println("4-Memory Stage: \n");
		PCsrc = ZeroFlag && Branch;

		if (PCsrc) {
			MySystem.PC = BranchAddressResult;
			System.out.println("*PC updated to branch address -> "+MySystem.PC+"\n");
		} else {
			InstructionFetch.ProgCount();
			System.out.println("*PC incremented by 4 -> "+MySystem.PC+"\n");
		}
		
		//System.out.println("PC: " + MySystem.PC);
		System.out.println("PCsrc: " + PCsrc);
		
		if (MemRead) {
			int memIndex = Integer.parseInt(ALUresult, 2);
			ReadData = MySystem.Memory[memIndex];
			
			System.out.println("Read Data From Memory");
			System.out.println("Data -> "+ReadData);
			System.out.println("Memory index (ALUResult) -> "+ALUresult);
			//System.out.println("Memory index in decimal (ALUResult) -> "+memIndex);
		}

		if (MemWrite) {
			int memIndex = Integer.parseInt(ALUresult, 2);
			MySystem.Memory[memIndex] = ReadData2;
			
			System.out.println("Write Data to Memory");
			System.out.println("Data -> "+ReadData2);
			System.out.println("Memory index (ALUResult) -> "+ALUresult);
			//System.out.println("Memory index in decimal (ALUResult) -> "+memIndex);
		}
		System.out.println();
		
	}
}
