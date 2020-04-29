
public class MySystem {
	static String[] Memory = new String[1024];
	static String[] RegisterFile = new String[32];
	static String[] InstructionMemory;
	static String PC = String.format("%32s", Integer.toBinaryString(0)).replaceAll(" ", "0");
	
	public static void DummyData() {
		System.out.println("Memory are loaded with the values of each index");
		for(int i=0; i<MySystem.Memory.length ; i++) {
			MySystem.Memory[i]= String.format("%32s", Integer.toBinaryString(i)).replaceAll(" ", "0");
		}

		MySystem.RegisterFile[0]=String.format("%32s", Integer.toBinaryString(0)).replaceAll(" ", "0");
		System.out.println("Registers are loaded with the values of each index");
		System.out.print("RegisterFile: [ ");
		for(int i=1 ; i<MySystem.RegisterFile.length ; i++) {
			MySystem.RegisterFile[i]= String.format("%32s", Integer.toBinaryString(i)).replaceAll(" ", "0");
			System.out.print(MySystem.RegisterFile[i]+" ");
		}
		System.out.print("]\n");
	}
	
	public static void run(String[] Program) {
		InstructionMemory = Program;
		InstructionFetch IF = new InstructionFetch();
		InstructionDecode ID = new InstructionDecode();
		Execute EXEC = new Execute();
		MemoryAccess MEM = new MemoryAccess();
		WriteBack WB = new WriteBack();
		
		while((Integer.parseInt(PC,2)/4)<InstructionMemory.length) {
			String instruction = IF.InstFetch(PC);
			ID.InstDecode(instruction, IF.PCplus4);
			EXEC.execute(ID.ALUOp, ID.ALUSrc, ID.ReadData1, ID.ReadData2, ID.SignExtend, IF.PCplus4);
			MEM.MemAccess(EXEC.ALUresult, ID.ReadData2, ID.SignExtend, EXEC.ZeroFlag, EXEC.BranchAddressResult, ID.MemWrite, ID.MemRead, ID.Branch);
			WB.writeBack(EXEC.ALUresult, MEM.ReadData, ID.MemToReg, ID.RegDst, ID.RegWrite ,ID.rt,ID.rd);
		}
	}
	public static void main(String[] args) {
		DummyData();
		String[] p1 = new String[7];
		String i1 = "100011"+"00001"+"00110"+"0000000000000001"; // LW R6 (1)R1
		p1[0]=i1;
		String i2 = "000000"+"00001"+"00010"+"00001"+"00000"+"100000";//ADD R1 R1 R2
		p1[1]=i2;
		String i3 = "101011"+"00011"+"00010"+"0000000000000111"; //SW R2 7(R3)
		p1[2]=i3;
		String i4 = "000000"+"00101"+"00100"+"00101"+"00000"+"100010";//SUB R5 R5 R4
		p1[3]=i4;
		String i5 = "000100"+"00001"+"00011"+"0000000000000001";//BEQ R3 R1 1
		p1[4]=i5;
		String i6 = "000000"+"00010"+"00011"+"00100"+"00000"+"100000";//ADD R4 R2 R3
		p1[5]=i6;
		String i7 = "000000"+"00101"+"00110"+"00111"+"00000"+"100000";//ADD R7 R5 R6
		p1[6]=i7;
		run(p1);
	}
}
