package app.br.v1.compilador.controller;

import android.graphics.Color;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import app.br.v1.compilador.R;
import app.br.v1.compilador.model.Linguagem;

public class Scanner {

    private StringBuilder str = new StringBuilder();
    private boolean ignore = false;
    private boolean comment = false;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String generateLog(String font) {
        String[] lines = font.split("\n");
        int nLine = 1;
        boolean keyWord = false;

        for (String line : lines) { // Analisando por linha
            String[] words = line.split(" ");
            for (String  word : words) { // analisando por palavra
                word = word.trim();
                if (word.isEmpty()) continue;
                keyWord = false;
                if (word.contains(Linguagem.COMENTARIO_FIM)) {
                    ignore = false;
                }
                if (ignore) continue;
                try {
                    Integer.parseInt(word);
                    str.append("Linha ").append(nLine).append(": (").append(word).append(", INTEIRO)").append("\n");
                    continue;
                } catch (Exception e) {
                }
                try {
                    Float.parseFloat(word);
                    str.append("Linha ").append(nLine).append(": (").append(word).append(", REAL)").append("\n");
                    continue;
                } catch (Exception e) {
                }
                for (String key : Linguagem.PALAVRAS_RESERVADAS) {
                    if (key.equals(word)) {
                        str.append("Linha ").append(nLine).append(": (").append(word).append(", PALAVRAS RESERVADAS)").append("\n");
                        keyWord = true;
                        break;
                    }
                }
                for (String op : Linguagem.OPERADORES) {
                    if (op.equals(word)) {
                        str.append("Linha ").append(nLine).append(": (").append(word).append(", OPERADORES)").append("\n");
                        keyWord = true;
                        break;
                    }
                }
                for (String dl : Linguagem.DELIMITADOR) {
                    if (dl.equals(word)) {
                        str.append("Linha ").append(nLine).append(": (").append(word).append(", DELIMITADOR)").append("\n");
                        keyWord = true;
                        break;
                    }
                }
                if (word.equals(Linguagem.COMENTARIO)) break;
                if (word.equals(Linguagem.COMENTARIO_INICIO)) {
                    ignore = true;
                    break;
                }
                if (!keyWord) {
                    scannerIdentifier(word, nLine);
                    if (comment) {
                        comment = false;
                        break;
                    }
                    //    str.append("Linha ").append(nLine).append(": (").append(word).append(", IDENTIFICADOR)").append("\n");
                }
            }
            nLine++;
        }
        return str.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void scannerIdentifier(String word, int nLine) {

        StringBuilder aux = new StringBuilder();
        int i = 0;
        if (word.contains(Linguagem.COMENTARIO_FIM)) {
            str.append("Linha ").append(nLine).append(": (").append(Linguagem.COMENTARIO_FIM).append(", DELIMITADOR)").append("\n");
            i = word.indexOf(Linguagem.COMENTARIO_FIM) + 2;
        }
        for (; i < word.length(); i++) {
            if (Character.isAlphabetic(word.getBytes()[i]) || Character.isDigit(word.toCharArray()[i])) {
                aux.append(word.substring(i, i + 1));
            } else {
                break;
            }
        }
        //metodo para iginorar espaço em braco com o trim()
        if (!aux.toString().trim().isEmpty()) {
            str.append("Linha ").append(nLine).append(": (").append(aux.toString()).append(", IDENTIFICADOR)").append("\n");
        }

        if (i >= word.length()) return;

        if (i + 2 <= word.length()) {
            if (word.substring(i, i + 2).equals(Linguagem.COMENTARIO)) {
                comment = true;
                str.append("Linha ").append(nLine).append(": (").append(Linguagem.COMENTARIO).append(", DELIMITADOR)").append("\n");
                return;
            }
            if (word.substring(i, i + 2).equals(Linguagem.COMENTARIO_INICIO)) {
                ignore = true;
                str.append("Linha ").append(nLine).append(": (").append(Linguagem.COMENTARIO_INICIO).append(", DELIMITADOR)").append("\n");
                return;
            }
        }
    }
}
