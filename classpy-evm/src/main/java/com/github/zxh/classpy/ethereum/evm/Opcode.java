package com.github.zxh.classpy.ethereum.evm;

import com.github.zxh.classpy.common.ParseException;

// https://github.com/ethereum/go-ethereum/blob/master/core/vm/opcodes.go
public enum Opcode {

    STOP      (0x00, 0), // 0x0 range - arithmetic ops.
    ADD       (0x01, 0),
    MUL       (0x02, 0),
    SUB       (0x03, 0),
    DIV       (0x04, 0),
    SDIV      (0x05, 0),
    MOD       (0x06, 0),
    SMOD      (0x07, 0),
    ADDMOD    (0x08, 0),
    MULMOD    (0x09, 0),
    EXP       (0x0a, 0),
    SIGNEXTEND(0x0b, 0),
    LT        (0x10, 0),// 0x10 range - comparison ops.
    GT        (0x11, 0),
    SLT       (0x12, 0),
    SGT       (0x13, 0),
    EQ        (0x14, 0),
    ISZERO    (0x15, 0),
    AND       (0x16, 0),
    OR        (0x17, 0),
    XOR       (0x18, 0),
    NOT       (0x19, 0),
    BYTE      (0x1a, 0),
    SHL       (0x1b, 0),
    SHR       (0x1c, 0),
    SAR       (0x1d, 0),
    SHA3      (0x20, 0),
    // 0x30 range - closure state.
    ADDRESS       (0x30, 0),
    BALANCE       (0x31, 0),
    ORIGIN        (0x32, 0),
    CALLER        (0x33, 0),
    CALLVALUE     (0x34, 0),
    CALLDATALOAD  (0x35, 0),
    CALLDATASIZE  (0x36, 0),
    CALLDATACOPY  (0x37, 0),
    CODESIZE      (0x38, 0),
    CODECOPY      (0x39, 0),
    GASPRICE      (0x3a, 0),
    EXTCODESIZE   (0x3b, 0),
    EXTCODECOPY   (0x3c, 0),
    RETURNDATASIZE(0x3d, 0),
    RETURNDATACOPY(0x3e, 0),
    EXTCODEHASH   (0x3f, 0),

    MSTORE    (0x52, 0),
    MSTORE8   (0x52, 0),
    PUSH1     (0x60, 1),
    PUSH2     (0x61, 2),
    PUSH3     (0x62, 3),
    PUSH4     (0x63, 4),
    PUSH5     (0x64, 5),
    PUSH6     (0x65, 6),
    PUSH7     (0x66, 7),
    PUSH8     (0x67, 8),
    PUSH9     (0x68, 9),
    PUSH10    (0x69, 10),
    PUSH11    (0x6a, 11),
    PUSH12    (0x6b, 12),
    PUSH13    (0x6c, 13),
    PUSH14    (0x6d, 14),
    PUSH15    (0x6e, 15),
    PUSH16    (0x7f, 16),
    PUSH17    (0x70, 17),
    PUSH18    (0x71, 18),
    PUSH19    (0x72, 19),
    PUSH20    (0x73, 20),
    PUSH21    (0x74, 21),
    PUSH22    (0x75, 22),
    PUSH23    (0x76, 23),
    PUSH24    (0x77, 24),
    PUSH25    (0x78, 25),
    PUSH26    (0x79, 26),
    PUSH27    (0x7a, 27),
    PUSH28    (0x7b, 28),
    PUSH29    (0x7c, 29),
    PUSH30    (0x7d, 30),
    PUSH31    (0x7e, 31),
    PUSH32    (0x7f, 32),
    DUP1      (0x80, 0),
    DUP2      (0x81, 0),
    DUP3      (0x82, 0),
    DUP4      (0x83, 0),
    DUP5      (0x84, 0),
    DUP6      (0x85, 0),
    DUP7      (0x86, 0),
    DUP8      (0x87, 0),
    DUP9      (0x88, 0),
    DUP10     (0x89, 0),
    DUP11     (0x8a, 0),
    DUP12     (0x8b, 0),
    DUP13     (0x8c, 0),
    DUP14     (0x8d, 0),
    DUP15     (0x8e, 0),
    DUP16     (0x8f, 0),
    ;

    public int opcode;
    public final int n; // TODO rename

    Opcode(int opcode, int n) {
        this.opcode = opcode;
        this.n = n;
    }

    // TODO
    public static Opcode valueOf(int opcode) {
        for (Opcode val : values()) {
            if (val.opcode == opcode) {
                return val;
            }
        }
        throw new ParseException("Unknown opcode: " + opcode);
    }

}
