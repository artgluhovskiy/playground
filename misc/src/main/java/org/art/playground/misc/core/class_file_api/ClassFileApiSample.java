package org.art.playground.misc.core.class_file_api;

import lombok.SneakyThrows;

import java.lang.classfile.ClassFile;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.classfile.ClassFile.ACC_PUBLIC;
import static java.lang.classfile.ClassFile.ACC_STATIC;

public class ClassFileApiSample {

    @SneakyThrows
    public static void main(String[] args) {

        // Generation Phase
        ClassFile.of()
            .buildTo(Path.of("HelloWorld.class"),
                ClassDesc.of("HelloWorld"),
                classBuilder -> classBuilder
                    .withMethodBody("main",
                        MethodTypeDesc.ofDescriptor("([Ljava/lang/String;)V"),
                        ACC_PUBLIC | ACC_STATIC,
                        codeBuilder -> codeBuilder
                            .getstatic(ClassDesc.of("java.lang.System"), "out", ClassDesc.of("java.io.PrintStream"))
                            .ldc("Hello World From Generated Class")
                            .invokevirtual(ClassDesc.of("java.io.PrintStream"), "println",
                                MethodTypeDesc.ofDescriptor("(Ljava/lang/String;)V"))
                            .return_()));


        // Invocation Phase
        byte[] classBytes = Files.readAllBytes(Path.of("HelloWorld.class"));

        ClassLoader loader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) {
                return defineClass(name, classBytes, 0, classBytes.length);
            }
        };

        Class<?> clazz = loader.loadClass("HelloWorld");
        Method main = clazz.getMethod("main", String[].class);
        main.invoke(null, (Object) new String[0]);
    }
}
