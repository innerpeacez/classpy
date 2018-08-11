package com.github.zxh.classpy.ethereum;

import com.github.zxh.classpy.ethereum.evm.Opcode;

public class Instruction extends EvmBinComponent {

    @Override
    protected void readContent(EvmBinReader reader) {
        Opcode opcode = Opcode.valueOf(reader.readByte());
        reader.readBytes(opcode.n);
        setName(opcode.name());
    }

}
