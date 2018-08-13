package com.github.zxh.classpy.ethereum;

public class EvmBinFile extends EvmBinComponent {

    @Override
    protected void readContent(EvmBinReader reader) {
        try {
            for (;;) {
                Instruction x = new Instruction();
                x.read(reader);
                add(null, x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
