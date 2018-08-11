package com.github.zxh.classpy.evm;

public class EvmBinFile extends EvmBinComponent {

    @Override
    protected void readContent(EvmBinReader reader) {
        Instruction x = new Instruction();
        x.read(reader);
        add("xxx", x);
    }

}
