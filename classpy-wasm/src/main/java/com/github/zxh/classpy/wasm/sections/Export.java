package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinReader;

public class Export extends WasmBinComponent {

    private int funcIdx = -1;

    public int getFuncIdx() {
        return funcIdx;
    }

    @Override
    protected void readContent(WasmBinReader reader) {
        String name = readName(reader, "name");
        Desc desc = read(reader, "desc", new Desc());
        if (desc.b == 0) {
            funcIdx = desc.idx;
            setDesc(name + "()");
        } else {
            setDesc(name);
        }
    }


    private static class Desc extends WasmBinComponent {

        private int b;
        private int idx;

        @Override
        protected void readContent(WasmBinReader reader) {
            b = readByte(reader, null);
            switch (b) {
                case 0x00: idx = readIndex(reader, "func");   break; // funcidx
                case 0x01: idx = readIndex(reader, "table");  break; // tableidx
                case 0x02: idx = readIndex(reader, "mem");    break; // memidx
                case 0x03: idx = readIndex(reader, "global"); break; // globalidx
                default: throw new ParseException("Invalid export desc: " + b);
            }
        }

    }

}
