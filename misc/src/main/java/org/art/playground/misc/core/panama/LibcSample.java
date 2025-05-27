package org.art.playground.misc.core.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class LibcSample {

    public static void main(String[] args) throws Throwable {
        Linker linker = Linker.nativeLinker();

        SymbolLookup stdlib = linker.defaultLookup();

        MethodHandle printf = linker.downcallHandle(
            stdlib.find("printf").orElseThrow(),
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)  // int printf(const char*, ...)
        );

        try (Arena arena = Arena.ofConfined()) {
            MemorySegment cString = arena.allocateFrom("Hello from native C!\n");
            printf.invoke(cString);
        }
    }
}
