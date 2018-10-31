package com.github.zxh.classpy.wasm.sections;

import com.github.zxh.classpy.common.ParseException;
import com.github.zxh.classpy.wasm.WasmBinComponent;
import com.github.zxh.classpy.wasm.WasmBinFile;
import com.github.zxh.classpy.wasm.WasmBinReader;
import com.github.zxh.classpy.wasm.types.FuncType;
import com.github.zxh.classpy.wasm.types.GlobalType;
import com.github.zxh.classpy.wasm.types.Limits;
import com.github.zxh.classpy.wasm.types.TableType;

public class Import extends WasmBinComponent {

    private int funcTypeIdx = -1;

    @Override
    protected void readContent(WasmBinReader reader) {
        String module = readName(reader, "module");
        String name = readName(reader, "name");
        Desc desc = read(reader, "desc", new Desc());
        setDesc(module + "." + name);
        if (desc.b == 0) { // func
            funcTypeIdx = desc.funcTypeIdx;
            setDesc(getDesc() + "()");
        }
    }

    @Override
    protected void postRead(WasmBinFile wasm) {
        if (funcTypeIdx >= 0) {
            FuncType funcType = wasm.getFuncTypes().get(funcTypeIdx);
            setDesc(getDesc().replace("()", funcType.getDesc()));
        }
    }


    private static class Desc extends WasmBinComponent {

        private int b;
        private int funcTypeIdx = -1;

        @Override
        protected void readContent(WasmBinReader reader) {
            b = readByte(reader, null);
            switch (b) {
                case 0x00: funcTypeIdx = readIndex(reader, "type");  break; // typeidx
                case 0x01: read(reader, "table",  new TableType());  break; // tabletype
                case 0x02: read(reader, "mem",    new Limits());     break; // memtype
                case 0x03: read(reader, "global", new GlobalType()); break; // globaltype
                default: throw new ParseException("Invalid import desc: " + b);
            }
        }

    }

}
