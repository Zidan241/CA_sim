
public class Execute {
	String ALUOperation;
	String BranchAddressResult;
	String ALUresult;
	boolean ZeroFlag;
	String ALUOperationName;

	public void execute(String ALUOp, boolean ALUSrc, String ReadData1, String ReadData2, String SignExtend,
			String PCplus4) {

		int intPCplus4 = Integer.parseInt(PCplus4, 2);

		int R1 = Integer.parseInt(ReadData1, 2);
		int R2 = Integer.parseInt(ReadData2, 2);
		int immediate = Integer.parseInt(SignExtend, 2);
		Integer res = null;
		String funct = SignExtend.substring(26, 32);

		ALUOperation = ALUcontrol(funct, ALUOp);

		switch (ALUOperation) {
		case "0010":
			ALUOperationName="ADD";
			res = ALUSrc ? R1 + immediate : R1 + R2;
			break;
		case "0110":
			ALUOperationName="SUB";
			res = ALUSrc ? R1 - immediate : R1 - R2;
			break;
		case "0000":
			ALUOperationName="AND";
			res = ALUSrc ? R1 & immediate : R1 & R2;
			break;
		case "0001":
			ALUOperationName="OR";
			res = ALUSrc ? R1 | immediate : R1 | R2;
			break;
		case "0111":
			ALUOperationName="Set-on-less-than";
			res = ALUSrc ? (R1 < immediate ? 1 : 0) :  (R1 < R2 ? 1 : 0);
			break;
		default:
			System.out.println("code undefined");
		}

		if (res == 0)
			ZeroFlag = true;
		else
			ZeroFlag = false;

		BranchAddressResult = String.format("%32s", Integer.toBinaryString(intPCplus4 + (immediate * 4)))
				.replaceAll(" ", "0");
		ALUresult = String.format("%32s", Integer.toBinaryString(res)).replaceAll(" ", "0");
		
		System.out.println("3-Execute Stage: \n");
		
		System.out.println("ALU Operation name: "+ALUOperationName+"\n");
		//System.out.println("PC: " + MySystem.PC);
		System.out.println("ALU Operation (4-bits): "+ALUOperation);
		System.out.println("zero flag: "+ZeroFlag);
		System.out.println("branch address: "+BranchAddressResult);
		System.out.println("ALU result/address: "+ALUresult+"\n");
		

	}

	public String ALUcontrol(String funct, String ALUOp) {
		switch (ALUOp) {
		case "00":
			return "0010";
		case "01":
			return "0110";
		case "10":
			switch (funct) {
			case "100000":
				return "0010";
			case "100010":
				return "0110";
			case "100100":
				return "0000";
			case "100101":
				return "0001";
			case "101010":
				return "0111";
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
