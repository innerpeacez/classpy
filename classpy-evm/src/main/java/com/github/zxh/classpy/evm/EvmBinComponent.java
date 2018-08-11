package com.github.zxh.classpy.evm;

import com.github.zxh.classpy.common.FileComponent;

public class EvmBinComponent extends FileComponent {

    public final void read(EvmBinReader reader) {
        int offset = reader.getPosition();
        readContent(reader);
        int length = reader.getPosition() - offset;
        super.setOffset(offset);
        super.setLength(length);
    }

    protected void readContent(EvmBinReader reader) {
        for (FileComponent bc : getComponents()) {
            ((EvmBinComponent) bc).read(reader);
        }
    }

}
