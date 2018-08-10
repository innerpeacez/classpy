package com.github.zxh.classpy.evm;

public class EvmBinReader {

    private String hexByteCodes;
    private int position;

    public EvmBinReader(String hexByteCodes) {
        this.hexByteCodes = hexByteCodes;
        if (this.hexByteCodes.startsWith("0x")) {
            position += 2;
        }
    }

}
