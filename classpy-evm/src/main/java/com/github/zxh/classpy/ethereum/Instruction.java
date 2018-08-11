package com.github.zxh.classpy.ethereum;

public class Instruction extends EvmBinComponent {

    @Override
    protected void readContent(EvmBinReader reader) {
        reader.readByte();
    }

}
