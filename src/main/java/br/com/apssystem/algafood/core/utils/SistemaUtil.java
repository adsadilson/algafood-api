package br.com.apssystem.algafood.core.utils;


public class SistemaUtil {

    public static boolean isWindows() {
        return System.getProperty("os.name").toUpperCase().contains("WINDOWS");
    }

    public static String pathRelatorio() {
        if (SistemaUtil.isWindows()) {
            return "/relatorios";
        }
        return null;
    }
}
