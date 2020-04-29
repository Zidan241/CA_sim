
public class InstructionDecode {

	boolean Branch;
	boolean RegDst;
	boolean RegWrite;
	boolean ALUSrc;
	boolean MemRead;
	boolean MemWrite;
	boolean MemToReg;
	String ALUOp;

	String ReadData1;
	String ReadData2;
	String SignExtend;

	String opCode;
	String rs;
	String rt;
	String rd;
	
	String type;

	public void InstDecode(String instruction, String PCplus4) {
		opCode = instruction.substring(0, 6);
		ContUnit(opCode);
		rs = instruction.substring(6, 11);
		rt = instruction.substring(11, 16);
		rd = instruction.substring(16, 21);
		String Immediate = instruction.substring(16,32);

		ReadData1 = MySystem.RegisterFile[Integer.parseInt(rs, 2)];
		ReadData2 = MySystem.RegisterFile[Integer.parseInt(rt, 2)];
		SignExtend = SignExtend(Immediate);

		System.out.println("2-Decode Stage: \n");

		System.out.println("Instruction Type: " + type+"\n");
		//System.out.println("PC: " + MySystem.PC);
		System.out.println("opCode: "+opCode);
		System.out.println("rs: " + rs);
		System.out.println("rt: " + rt);
		System.out.println("rd: " + rd);
		System.out.println("read data 1: " + ReadData1);
		System.out.println("read data 2: " + ReadData2);
		System.out.println("sign-extend: " + SignExtend);
		System.out.println("WB controls -> MemToReg:" + MemToReg + " , RegWrite:" + RegWrite);
		System.out.println(
				"MEM controls -> MemRead:" + MemRead + " , MemWrite:" + MemWrite + " , Branch:" + Branch);
		System.out.println("EX controls -> RegDest:" + RegDst + " , ALUOp:" + ALUOp + " , ALUSrc:" + ALUSrc + "\n");
	}

	public String SignExtend(String s) {

		if (s.charAt(0) == '1') {
			return "1111111111111111" + s;
		} else {
			return "0000000000000000" + s;
		}
	}

	public void ContUnit(String opCode) {
		switch (opCode) {
		case "000000":
			RegDst = true;
			RegWrite = true;
			ALUSrc = false;
			MemRead = false;
			MemWrite = false;
			MemToReg = false;
			ALUOp = "10";
			Branch = false;
			type = "R-type";
			break;
		case "100011":
			RegDst = false;
			RegWrite = true;
			ALUSrc = true;
			MemRead = true;
			MemWrite = false;
			MemToReg = true;
			ALUOp = "00";
			Branch = false;
			type = "LW";
			break;
		case "101011":
			RegDst = false;
			RegWrite = false;
			ALUSrc = true;
			MemRead = false;
			MemWrite = true;
			MemToReg = false;
			ALUOp = "00";
			Branch = false;
			type = "SW";
			break;
		case "000100":
			RegDst = false;
			RegWrite = false;
			ALUSrc = false;//?
			MemRead = false;
			MemWrite = false;
			MemToReg = false;
			ALUOp = "01";
			Branch = true;
			type = "BEQ";
			break;
		}
	}

}
