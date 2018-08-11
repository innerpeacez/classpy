package com.github.zxh.classpy.evm;

public class Instruction extends EvmBinComponent {

    @Override
    protected void readContent(EvmBinReader reader) {
        reader.readByte();
    }

}
