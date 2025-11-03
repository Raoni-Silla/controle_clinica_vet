package infra;

public class Utilitarios {
    private  Utilitarios(){}

    public static boolean isCpfValido(String cpf) {
        int soma = 0;
        int resto;

        // Primeiro dígito verificador
        for (int i = 1; i <= 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i - 1)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;
        if (resto != Character.getNumericValue(cpf.charAt(9))) return false;

        // Segundo dígito verificador
        soma = 0;
        for (int i = 1; i <= 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i - 1)) * (12 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) resto = 0;
        return resto == Character.getNumericValue(cpf.charAt(10));
    }

}
