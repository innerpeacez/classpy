package com.github.zxh.classpy.ethereum;

import com.github.zxh.classpy.ethereum.evm.Opcode;

public class Instruction extends EvmBinComponent {

    @Override
    protected void readContent(EvmBinReader reader) {
        Opcode opcode = Opcode.valueOf(reader.readByte());
        byte[] operands = reader.readBytes(opcode.n);
        setName(opcode.name());
        if (operands.length > 0) {
            setDesc(EvmHelper.encodeHexStr(operands));
        }
    }

}
