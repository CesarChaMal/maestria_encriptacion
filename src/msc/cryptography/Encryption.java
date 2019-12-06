package msc.cryptography;

import java.util.Arrays;
import org.omg.CORBA.SystemException;
import java.util.*;
import java.security.*;

public class Encryption {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String mensaje = "el dia con sol paseando a mi perro";
//		String mensaje = " !el ";
		String mensajeEncriptado = "";
		String mensajeDesencriptado = "";
		
// abecedario codigo ascci los codigos imprimibles van del codigo 32 al 127
		int first = 32, last = 127, length = last - first, seed = first;
		char abecedario[] = new char[length];

		char mensajeArray[] = mensaje.toCharArray();
		char mensajeArrayEncriptado[] = mensaje.toCharArray();
		char mensajeArrayDesencriptado[] = mensaje.toCharArray();

//abecedario invertido
//metodo uno
		
		for(int i=0;i<length;i++)
		{
			abecedario[i] = (char) seed++;
//			System.out.println(abecedario[i]);
		}
		
		int pos = 0;
		for(int i=0; i<mensajeArray.length; i++)
		{
			pos = Arrays.binarySearch(abecedario,mensajeArray[i]);
			mensajeArrayEncriptado[i] = abecedario[(length - pos)-1];
			System.out.println(mensajeArrayEncriptado[i]);
		}
		
		mensajeEncriptado = String.valueOf(mensajeArrayEncriptado);
		System.out.println(mensajeEncriptado);
		
 		//mensajeArrayEncriptado = mensajeEncriptado.toCharArray();
		for(int i=0; i<mensajeArrayEncriptado.length; i++)
		{
			pos = Arrays.binarySearch(abecedario,mensajeArrayEncriptado[i]);
			mensajeArrayDesencriptado[i] = abecedario[(length - pos)-1];
			System.out.println(mensajeArrayDesencriptado[i]);
		}
		
		mensajeDesencriptado = String.valueOf(mensajeArrayDesencriptado);
		System.out.println(mensajeDesencriptado);

//metodo dos
		
		mensajeArray = mensaje.toCharArray();
		for(int i=0; i<mensajeArray.length; i++)
		{
			mensajeArrayEncriptado[i] = (char) ((int) mensajeArray[i] + (length-1) - ((int) mensajeArray[i] - first)*2);
			System.out.println(mensajeArrayEncriptado[i]);
		}
		mensajeEncriptado = String.valueOf(mensajeArrayEncriptado);
		System.out.println(mensajeEncriptado);

 		//mensajeArrayEncriptado = mensajeEncriptado.toCharArray();
		for(int i=0; i<mensajeArrayEncriptado.length; i++)
		{
			mensajeArrayDesencriptado[i] = (char) ((int) mensajeArrayEncriptado[i] + (length-1) - ((int) mensajeArrayEncriptado[i] - first)*2);
			System.out.println(mensajeArrayDesencriptado[i]);
		}
		
		mensajeDesencriptado = String.valueOf(mensajeArrayDesencriptado);
		System.out.println(mensajeDesencriptado);

//negacion

		byte[] original = mensaje.getBytes();
		byte[] encriptado = mensaje.getBytes();
		byte[] desencriptado = mensaje.getBytes();
		mensajeArray = mensaje.toCharArray();
		
		for(int i=0; i<mensajeArray.length; i++)
		{
			encriptado[i] = (byte) ~original[i];
			//System.out.println(encriptado[i]);
			mensajeArrayEncriptado[i] = (char) Math.abs(encriptado[i]); 
			System.out.println(mensajeArrayEncriptado[i]);
		}
		mensajeEncriptado = String.valueOf(mensajeArrayEncriptado);
		System.out.println(mensajeEncriptado);
		
		for(int i=0; i<mensajeArrayEncriptado.length; i++)
		{
			//desencriptado[i] = (byte) ~encriptado[i];
			desencriptado[i] = (byte) ~((int)-mensajeArrayEncriptado[i]);
			//System.out.println(desencriptado[i]);
			mensajeArrayDesencriptado[i] = (char) desencriptado[i]; 
			System.out.println(mensajeArrayDesencriptado[i]);
		}

		mensajeDesencriptado = String.valueOf(mensajeArrayDesencriptado);
		System.out.println(mensajeDesencriptado);

//Cifrado de Blaise de Vigenère sencillo
//Por coordenadas
//Alfabeto sencillo
//		abecedario[] = new char[length];
		mensaje = "CADBACABAGA";
		mensajeArray = mensaje.toCharArray();
		char alfabeto[] = {'A','B','C','D','E','F','G'};
		char alfabetoVigenere[][] = new char[alfabeto.length][alfabeto.length]; 

		char claveSobrePuesta[] = new char[mensajeArray.length];
		String clave = "GEA";
		char claveArray[] = new char[clave.toCharArray().length]; 
		claveArray = clave.toCharArray();
		int cont = 0; 

		cont = alfabeto.length;
		System.out.println();
//		System.out.println("   " + String.String.valueOf(alfabeto));
		System.out.print("   ");
		for(int i=0; i<alfabeto.length; i++)
			System.out.print(alfabeto[i] + " ");
		System.out.println();
		for(int i=0; i<alfabeto.length; i++)
		{
			System.out.print(alfabeto[i] +"  ");
			for(int j=0; j<alfabeto.length; j++, cont--)
			{
				if((j+i)>=alfabeto.length)
					alfabetoVigenere[i][j] = alfabeto[Math.abs(cont-i)];
				else
					alfabetoVigenere[i][j] = alfabeto[j+i];
				System.out.print(alfabetoVigenere[i][j] + " ");
			}
			cont = alfabeto.length;
			System.out.println();
		}

		System.out.println();
		System.out.println("  " + mensaje);
		System.out.print("+ " );
		cont = claveArray.length;
		for(int i=0; i<mensajeArray.length; i++, cont--)
		{
			if(Math.abs(cont)==claveArray.length)
			{
				cont = 0;
			}
			if(i>=claveArray.length)
				claveSobrePuesta[i] = claveArray[Math.abs(cont)];
			else
				claveSobrePuesta[i] = claveArray[i];
			System.out.print(claveSobrePuesta[i]);
		}
		System.out.println();
		System.out.println("-------------");
		
//		mensajeArrayEncriptado = mensaje.toCharArray();
		mensajeEncriptado = "";
		mensajeArrayEncriptado = mensajeEncriptado.toCharArray();
		boolean band = false;

		for(int c=0; c<mensajeArray.length; c++)
		{
			for(int i=0; i<alfabeto.length; i++)
			{
				for(int j=0; j<alfabeto.length; j++)
				{
					if(alfabeto[i] == mensajeArray[c] && alfabeto[j] == claveSobrePuesta[c])
					{
						mensajeEncriptado += alfabetoVigenere[j][i];
						band = true;
						break;
						//mensajeArrayEncriptado = Encryption.addElement(mensajeArrayEncriptado, alfabetoVigenere[i][j]);
					}
				}
				if(band != false)
				{
					band = false;
					break;
				}
			}
		}
		
		System.out.println("= " + mensajeEncriptado);
		mensajeArrayEncriptado = mensajeEncriptado.toCharArray();

		mensajeDesencriptado = "";
		mensajeArrayDesencriptado = mensajeDesencriptado.toCharArray();
		
		System.out.println();
		System.out.println("  " + String.valueOf(claveSobrePuesta));
		System.out.println("- " + mensajeEncriptado);
		System.out.println("-------------");

		for(int c=0; c<mensajeArrayEncriptado.length; c++)
		{
			for(int i=0; i<alfabeto.length; i++)
			{
				for(int j=0; j<alfabeto.length; j++)
				{
					if(alfabeto[j] == claveSobrePuesta[c] && alfabetoVigenere[j][i] == mensajeArrayEncriptado[c])
					{
						mensajeDesencriptado += alfabeto[i];
						band = true;
						break;
					}
				}
				if(band != false)
				{
					band = false;
					break;
				}
			}
		}

		System.out.println("= " + mensajeDesencriptado);
		mensajeArrayDesencriptado = mensajeDesencriptado.toCharArray();

//Cifrado de Blaise de Vigenère sencillo
//Por coordenadas
//Alfabeto Ascii
		mensaje = "el dia con sol paseando a mi perro";
		mensajeArray = mensaje.toCharArray();
		alfabetoVigenere = new char[abecedario.length][abecedario.length]; 

		claveSobrePuesta = new char[mensajeArray.length];
		clave = "perro";
//		claveArray = new char[clave.toCharArray().length]; 
		claveArray = clave.toCharArray();
		cont = 0; 

		cont = abecedario.length;
		System.out.println();
		System.out.print("    ");
		for(int i=0; i<abecedario.length; i++)
			System.out.print(abecedario[i] + " ");
		System.out.println();
		System.out.println();
		for(int i=0; i<abecedario.length; i++)
		{
			System.out.print(abecedario[i] +"   ");
			for(int j=0; j<abecedario.length; j++, cont--)
			{
				if((j+i)>=abecedario.length)
					alfabetoVigenere[i][j] = abecedario[Math.abs(cont-i)];
				else
					alfabetoVigenere[i][j] = abecedario[j+i];
				System.out.print(alfabetoVigenere[i][j] + " ");
			}
			cont = abecedario.length;
			System.out.println();
		}


		System.out.println();
		System.out.println("  " + mensaje);
		System.out.print("+ " );
		cont = claveArray.length;
		for(int i=0; i<mensajeArray.length; i++, cont--)
		{
			if(Math.abs(cont)==claveArray.length)
			{
				cont = 0;
			}
			if(i>=claveArray.length)
				claveSobrePuesta[i] = claveArray[Math.abs(cont)];
			else
				claveSobrePuesta[i] = claveArray[i];
			System.out.print(claveSobrePuesta[i]);
		}
		System.out.println();
		System.out.println("-------------");
		
		mensajeEncriptado = "";
		mensajeArrayEncriptado = mensajeEncriptado.toCharArray();
		band = false;

		for(int c=0; c<mensajeArray.length; c++)
		{
			for(int i=0; i<abecedario.length; i++)
			{
				for(int j=0; j<abecedario.length; j++)
				{
					if(abecedario[i] == mensajeArray[c] && abecedario[j] == claveSobrePuesta[c])
					{
						mensajeEncriptado += alfabetoVigenere[j][i];
						band = true;
						break;
						//mensajeArrayEncriptado = Encryption.addElement(mensajeArrayEncriptado, alfabetoVigenere[i][j]);
					}
				}
				if(band != false)
				{
					band = false;
					break;
				}
			}
		}
		
		System.out.println("= " + mensajeEncriptado);
		mensajeArrayEncriptado = mensajeEncriptado.toCharArray();

		mensajeDesencriptado = "";
		mensajeArrayDesencriptado = mensajeDesencriptado.toCharArray();
		
		System.out.println();
		System.out.println("  " + String.valueOf(claveSobrePuesta));
		System.out.println("- " + mensajeEncriptado);
		System.out.println("-------------");

		for(int c=0; c<mensajeArrayEncriptado.length; c++)
		{
			for(int i=0; i<abecedario.length; i++)
			{
				for(int j=0; j<abecedario.length; j++)
				{
					if(abecedario[j] == claveSobrePuesta[c] && alfabetoVigenere[j][i] == mensajeArrayEncriptado[c])
					{
						mensajeDesencriptado += abecedario[i];
						band = true;
						break;
					}
				}
				if(band != false)
				{
					band = false;
					break;
				}
			}
		}

		System.out.println("= " + mensajeDesencriptado);
		mensajeArrayDesencriptado = mensajeDesencriptado.toCharArray();
		
		
//Cifrado de Blaise de Vigenère sencillo
//Por formula matematica
		
//		 M
//		C 
//
//		  3
//		27 
//
//		encriptado
//		
//		C = alfabeto
//		M = clave
//
//		Ci = (Ti + Ki) mod m
//
//		desencriptado
//		
//		Ti = (Ci - Ki) mod m
//		
//		Ki numero de ubicacion horizontal (Clave replicada)
//		Ti numero de ubicacion vertical (Texto a traducir)
//		m total del alfabeto
		
		System.out.println();

		int combinaciones = 0, Ki = 0, Ti = 0, Ci = 0, m = 0;
		m = abecedario.length;
		combinaciones = (int) Math.pow(m, clave.toCharArray().length);
		System.out.println(combinaciones);

		mensaje = "el dia con sol paseando a mi perro";
		mensajeArray = mensaje.toCharArray();
		clave = "perro";
		claveArray = clave.toCharArray();
		mensajeEncriptado = "";
		mensajeArrayEncriptado = mensajeEncriptado.toCharArray();
		band = false;
		for(int c=0; c<mensajeArray.length; c++)
		{
			for(int i=0; i<abecedario.length; i++)
			{
				for(int j=0; j<abecedario.length; j++)
				{
					if(abecedario[i] == mensajeArray[c] && abecedario[j] == claveSobrePuesta[c])
					{
						Ki = j;
						Ti = i;
						Ci = (Ti + Ki) % m;
						mensajeEncriptado += abecedario[Ci];
						band = true;
						break;
					}
				}
				if(band != false)
				{
					band = false;
					break;
				}
			}
		}
		System.out.println(mensajeEncriptado);
		mensajeArrayEncriptado = mensajeEncriptado.toCharArray();
		
		mensajeDesencriptado = "";
		mensajeArrayDesencriptado = mensajeDesencriptado.toCharArray();
		for(int c=0; c<mensajeArrayEncriptado.length; c++)
		{
			for(int i=0; i<abecedario.length; i++)
			{
				for(int j=0; j<abecedario.length; j++)
				{
					if(abecedario[j] == claveSobrePuesta[c] && abecedario[((i + j) % m)] == mensajeArrayEncriptado[c])
					{
//						Ki = j;
//						Ci = i;
//						Ti = (Ci + Ki) % m;
						mensajeDesencriptado += abecedario[i];
						band = true;
						break;
					}
				}
				if(band != false)
				{
					band = false;
					break;
				}
			}
		}
		System.out.println(mensajeDesencriptado);
		mensajeArrayDesencriptado = mensajeDesencriptado.toCharArray();
		
//Algoritmo RC4
		
		byte state[] = new byte[256];
		int x;
		int y;
		
		String keyStr = "12345";
		byte[] keyBytes = keyStr.getBytes();
		String cadenaStr = "Criptografia";
		byte[] cadenaBytes = cadenaStr.getBytes();

		System.out.println();
		System.out.println("Key: "+ keyStr);
		System.out.println("Texto a encriptar: "+ cadenaStr);

		//encriptacion
		x = 0;
		y = 0;

		for (int i=0; i < 256; i++) {
			state[i] = (byte)i;
		}

		int index1 = 0;
		int index2 = 0;

		byte tmp;

		if (keyBytes == null || keyBytes.length == 0) {
			throw new NullPointerException();
		}

		for (int i=0; i < 256; i++) {

			index2 = ((keyBytes[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;

			tmp = state[i];
			state[i] = state[index2];
			state[index2] = tmp;

			index1 = (index1 + 1) % keyBytes.length;
		}

        int xorIndex;
        byte tmp1;
        
        byte[] textoEncriptado = new byte[cadenaBytes.length];
        
        for (int i=0; i < cadenaBytes.length; i++) {

            x = (x + 1) & 0xff;
            y = ((state[x] & 0xff) + y) & 0xff;

            tmp1 = state[x];
            state[x] = state[y];
            state[y] = tmp1;
            
            xorIndex = ((state[x] &0xff) + (state[y] & 0xff)) & 0xff;
            textoEncriptado[i] = (byte)(cadenaBytes[i] ^ state[xorIndex]);
        }
       
		System.out.println();
        String textoEncriptadoString = new String(textoEncriptado);
        System.out.println("Bytes: " + String.valueOf(textoEncriptado));
        System.out.println("Texto encriptado: " + textoEncriptadoString);


		//desencriptacion
		x = 0;
		y = 0;

		for (int i=0; i < 256; i++) {
			state[i] = (byte)i;
		}

		index1 = 0;
		index2 = 0;

		byte tmp2;

		if (keyBytes == null || keyBytes.length == 0) {
			throw new NullPointerException();
		}

		for (int i=0; i < 256; i++) {

			index2 = ((keyBytes[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;

			tmp2 = state[i];
			state[i] = state[index2];
			state[index2] = tmp2;

			index1 = (index1 + 1) % keyBytes.length;
		}


        xorIndex = 0;
        byte tmp3;

        byte[] textoDesencriptado = new byte[textoEncriptado.length];
        
        for (int i=0; i < textoEncriptado.length; i++) {

            x = (x + 1) & 0xff;
            y = ((state[x] & 0xff) + y) & 0xff;

            tmp3 = state[x];
            state[x] = state[y];
            state[y] = tmp3;
            
            xorIndex = ((state[x] &0xff) + (state[y] & 0xff)) & 0xff;
            textoDesencriptado[i] = (byte)(textoEncriptado[i] ^ state[xorIndex]);
        }
       
		System.out.println();
        String textoDesencriptadoString = new String(textoDesencriptado);
        System.out.println("Bytes: " + textoDesencriptado.toString());
        System.out.println("Texto Desencriptado: " + textoDesencriptadoString);
		
	}
	
	static public char[] addElement(char[] org, char added) {
	    char[] result = Arrays.copyOf(org, org.length +1);
	    result[org.length] = added;
	    return result;
	}
	
}
