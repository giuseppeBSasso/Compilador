package app.br.v1.compilador.model;

import android.graphics.Color;
import android.widget.TextView;

public class Linguagem {


    public  static  final String[] PALAVRAS_RESERVADAS = {
            "int", "float", "String", "double", "boolean", "char", "void", "public", "private", "igor",
            "vasco", "return", "if", "else", "for", "while", "break", "continue", "funcao", "hame",
            "true", "false", "switch", "case", "default"
    };

    public static final String[] OPERADORES = {
            "+", "-", "/", "*", "%", "=", ">", "<", ">=", "<=", "!", "!=", "==", "&", "|", "++", "--",
            "+=", "-=", "/=", "*="
    };

    public static final String COMENTARIO = "//";
    public static final String COMENTARIO_INICIO = "/*";
    public static final String COMENTARIO_FIM = "*/";

    public  static  final  String[] DELIMITADOR ={
            "{", "}", "[", "]", "(", ")", COMENTARIO_INICIO, COMENTARIO_FIM, COMENTARIO, "'", ";", ","
    };

}
