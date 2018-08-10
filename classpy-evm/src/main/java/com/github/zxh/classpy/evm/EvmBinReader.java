package com.github.zxh.classpy.evm;

import com.github.zxh.classpy.common.BytesReader;

import java.nio.ByteOrder;

public class EvmBinReader extends BytesReader {

    public EvmBinReader(String hexByteCodes) {
        super(hex2bin(hexByteCodes), ByteOrder.BIG_ENDIAN);
    }

    private static byte[] hex2bin(String hexByteCodes) {
        if (hexByteCodes.startsWith("0x")) {
            hexByteCodes = hexByteCodes.substring(2);
        }
        byte[] data = new byte[hexByteCodes.length() / 2];
        for (int i = 0; i < hexByteCodes.length() / 2; i++) {
            data[i] = (byte) Integer.parseInt(hexByteCodes.substring(i * 2, i * 2 + 2), 16);
        }
        return data;
    }

}
