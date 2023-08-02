package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        MethodSignature methodSignature;
        String methodName;

        MethodSignature.Argument argument = null;
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        String[] stringParts = signatureString.split("\\(");
        String[] firstPartWords = stringParts[0].split(" ");
        methodName = firstPartWords[firstPartWords.length - 1];
        stringParts[1] = stringParts[1].replaceAll("\\)", "");
        if (stringParts[1].length() > 0) {
            String[] secondPartWords = stringParts[1].split(", ");
            for (String string : secondPartWords) {
                String[] args = string.split(" ");
                argument = new MethodSignature.Argument(args[0], args[1]);
                arguments.add(argument);
            }
        }
        methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setReturnType(firstPartWords[1]);
        methodSignature.setMethodName(methodName);
        if (stringParts[0].contains("public")) {
            methodSignature.setAccessModifier("public");
        } else if (stringParts[0].contains("private")) {
            methodSignature.setAccessModifier("private");
        } else if (stringParts[0].contains("protected")) {
            methodSignature.setAccessModifier("protected");
        } else {
            methodSignature.setReturnType(firstPartWords[0]);
        }
        return methodSignature;
    }
}
