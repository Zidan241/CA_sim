
public class WriteBack {
	boolean writeFlag=false;
	public void writeBack(String ALUresult, String ReadData, boolean MemToReg, boolean RegDst, boolean RegWrite,
			String rt, String rd) {
		int dstReg;
		System.out.println("5-Write Back Stage: \n");
		// System.out.println("PC: " + MySystem.PC);
		if (RegWrite) {
			if (RegDst)
				dstReg = Integer.parseInt(rd, 2);
			else
				dstReg = Integer.parseInt(rt, 2);

			if (MemToReg) {
				regWrite(dstReg, ReadData);
				System.out.println("data from memory written to register");
				System.out.println("Dst Register -> " + dstReg);
			} else {
				regWrite(dstReg, ALUresult);
				System.out.println("data from ALU written to register");
				System.out.println("Dst Register -> " + dstReg);
			}
		}
	}

	public void regWrite(int index, String Data) {
		if (index == 0) {
			System.out.println("cannot change value of Regsister $ZERO");
		} else {
			writeFlag=true;
			MySystem.RegisterFile[index] = Data;
			writeFlag=false;
		}
	}
}
