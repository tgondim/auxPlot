package br.com.tg.util;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador extends Object {

	//valida se o email é valido
	public static boolean validaEmail(String email) {
		if (email != null) {
			Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher m = p.matcher(email);
			boolean matchFound = m.matches();
			if (matchFound)
				return true;
		}
		return false;
	}

	// valida data no formato dd/MM/yyyy
	public static boolean validaData(String data) {
		boolean anoBisexto = false;
		char chars[];
		StringTokenizer dataTokens = new StringTokenizer(data, "-");
		String[] dataArray = new String[3];
		int i;
		for (i = 0; i < dataArray.length; i++) {
			dataTokens.hasMoreTokens();
			dataArray[i] = dataTokens.nextToken();
		}

		// valida ano
		chars = dataArray[2].toCharArray();
		for (i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				return false;
			}
		}
		int ano = Integer.parseInt(dataArray[2]);
		if (ano < 1800)
			return false;

		// verifica se o ano eh bisexto
		if (ano % 400 == 0)
			anoBisexto = true;
		else if (ano % 100 == 0)
			anoBisexto = false;
		else if (ano % 4 == 0)
			anoBisexto = true;
		else
			anoBisexto = false;

		// valida mes

		chars = dataArray[1].toCharArray();
		for (i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				return false;
			}
		}
		int mes = Integer.parseInt(dataArray[1]);
		if (mes > 12)
			return false;

		// valida dia
		chars = dataArray[0].toCharArray();
		for (i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				return false;
			}
		}
		if (mes == 2) {
			if (Integer.parseInt(dataArray[0].substring(0, 1)) > 2)
				return false;
			if (anoBisexto) {
				if (Integer.parseInt(dataArray[0]) > 29)
					return false;
			} else {
				if (Integer.parseInt(dataArray[0]) > 28)
					return false;
			}
		} else {
			if (Integer.parseInt(dataArray[0].substring(0, 1)) > 3)
				return false;

			if (Integer.parseInt(dataArray[0].substring(0, 1)) == 3) {
				if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
					if (Integer.parseInt(dataArray[0].substring(1)) > 0)
						return false;
				} else {
					if (Integer.parseInt(dataArray[0].substring(1)) > 1)
						return false;
				}
			}
			if (Integer.parseInt(dataArray[0].substring(1)) > 9)
				return false;
		}

		return true;
	}

	// calcula digito verificador do CPF a partir dos 9 numeros passados
	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();
	}

	public static String geraCPF() {
		String iniciais = "";
		Integer numero;
		for (int i = 0; i < 9; i++) {
			numero = new Integer((int) (Math.random() * 10));
			iniciais += numero.toString();
		}
		return iniciais + calcDigVerif(iniciais);
	}

	// valida CPF com ou sem máscara
	public static boolean validaCPF(String cpf) {
		cpf = cpf.trim();
		int i;
		char chars[] = cpf.toCharArray();
		for (i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				return false;
			}
		}
		cpf = unmask(cpf.trim());
		String numDig = cpf.substring(0, 9);
		return calcDigVerif(numDig).equals(cpf.substring(9, 11));
	}

	// tira a mascara da String ("/", ".", "-")
	public static String unmask(String masked) {
		StringTokenizer tokens = new StringTokenizer(masked, ".");
		String unmasked = "";
		while (tokens.hasMoreTokens()) {
			unmasked = unmasked + tokens.nextToken();
		}
		masked = unmasked;
		unmasked = "";
		tokens = new StringTokenizer(masked, "/");
		while (tokens.hasMoreTokens()) {
			unmasked = unmasked + tokens.nextToken();
		}
		masked = unmasked;
		unmasked = "";
		tokens = new StringTokenizer(masked, "-");
		while (tokens.hasMoreTokens()) {
			unmasked = unmasked + tokens.nextToken();
		}
		return unmasked;
	}

	// valida CNPJ com ou sem máscara
	public static boolean validaCNPJ(String cnpj) {
		int soma = 0, dig;
		cnpj = unmask(cnpj);
		String cnpj_calc = cnpj.substring(0, 12);
		char[] chr_cnpj = cnpj.toCharArray();

		// Primeira parte
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
			}
		for (int i = 0; i < 8; i++) {
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
			}
		}
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		// Segunda parte
		soma = 0;
		for (int i = 0; i < 5; i++) {
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
			}
		}
		for (int i = 0; i < 8; i++) {
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
			}
		}
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		return cnpj.equals(cnpj_calc);
	}
}
