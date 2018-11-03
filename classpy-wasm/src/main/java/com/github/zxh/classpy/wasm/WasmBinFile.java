package com.github.zxh.classpy.wasm;

import com.github.zxh.classpy.wasm.sections.Export;
import com.github.zxh.classpy.wasm.sections.Import;
import com.github.zxh.classpy.wasm.sections.Section;
import com.github.zxh.classpy.wasm.types.FuncType;
import com.github.zxh.classpy.wasm.values.Index;

import java.util.List;
import java.util.stream.Collectors;

public class WasmBinFile extends WasmBinComponent {

    private List<FuncType> funcTypes;
    private List<Import> imports;
    private List<Import> importedFuncs;
    private List<Index> funcs;
    private List<Export> exportedFuncs;

    public List<FuncType> getFuncTypes() { return funcTypes; }
    public List<Import> getImports() { return imports; }
    public List<Index> getFuncs() { return funcs; }
    public List<Import> getImportedFuncs() { return importedFuncs; }
    public List<Export> getExportedFuncs() { return exportedFuncs; }

    @Override
    protected void readContent(WasmBinReader reader) {
        readBytes(reader, "magic", 4);
        readBytes(reader, "version", 4);
        readSections(reader);
        funcTypes = getSectionItems(1, FuncType.class);
        imports = getSectionItems(2, Import.class);
        importedFuncs = imports.stream()
                .filter(Import::isFunc)
                .collect(Collectors.toList());
        funcs = getSectionItems(3, Index.class);
        System.out.println(funcTypes);
        findExportedFuncs();
    }

    private void readSections(WasmBinReader reader) {
        while (reader.remaining() > 0) {
            Section section = new Section();
            add("section", section);
            section.read(reader);
        }
    }

    private <T> List<T> getSectionItems(int secID, Class<T> itemClass) {
        return getComponents().stream()
                .filter(c -> c instanceof Section)                // section?
                .map(c -> (Section) c)                            // yes
                .filter(sec -> sec.getID() == secID)              // section
                .map(sec -> (Vector) sec.getComponents().get(2))  // vector
                .flatMap(v -> v.getComponents().stream().skip(1)) // items
                .map(c -> itemClass.cast(c))                      // Ts
                .collect(Collectors.toList());
    }

    private void findExportedFuncs() {
        exportedFuncs = getComponents().stream()
                .filter(c -> c instanceof Section)                // section?
                .map(c -> (Section) c)                            // yes
                .filter(sec -> sec.getID() == 7)                  // exports?
                .map(sec -> (Vector) sec.getComponents().get(2))  // vector
                .flatMap(v -> v.getComponents().stream().skip(1)) // exports
                .map(c -> (Export) c)                             // exports
                .filter(x -> x.getFuncIdx() >= 0)                 // function
                .collect(Collectors.toList());
    }

}
