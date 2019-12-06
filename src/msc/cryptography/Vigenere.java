package msc.cryptography;

import java.io.*;
public class Vigenere{
	public static String alfa = "abcdefghijklmnopqrstuvwxyz";//este es el alfabeto..
	public static void main(String args[]) throws IOException{//si algo sale mal lo tiro a IOException
		if(args.length<3){
			ayuda();//tiro ayuda si no le meten todos los argumentos
			return;
		}
		byte res[];//donde se va a almacenar los bytes del String ya cifrado o descifrado
		byte lec[];
		String texto = args[1];//este va a ser el texto o en su defecto el nombre del archivo
		String pass = args[2];//la clave a usar
		if(args[0].equalsIgnoreCase("-c")){//si solo es cifrar un String y no un archivo
			res = enc(texto,pass).getBytes();//meto los bytes de la cadena cifrada a res
		}else if(args[0].equalsIgnoreCase("-d")){//si es descifrar una cadena
			res = dec(texto,pass).getBytes();//lo mismo meto el los bytes de la cadena descifrada
		}else if(args[0].equalsIgnoreCase("-cf")||args[0].equalsIgnoreCase("-df")){//si hay un archivo por ahi :P
			if(!new File(texto).exists()){//si el archivo no existe imprima el error
				System.err.println("File "+texto+" Doesn't exists!");
				return;
			}
			FileInputStream lect = new FileInputStream(texto);//abro el handler o Stream del archivo
			lec = new byte[(int)new File(texto).length()];//inicio el lec y es ahi donde meto lo que tenga el archivo
			lect.read(lec);//leo
			if(args[0].equalsIgnoreCase("-cf")){//si es cifrar
				res = enc(new String(lec,0,lec.length),pass).getBytes();//res queda como el cifrado de lo que hay en el archivo
			}else{
				res = dec(new String(lec,0,lec.length),pass).getBytes();//si no es cifrado queda con el descifrado del archivo
			}
		}else{
			ayuda();//si no es ninguna de las dos tiro ayuda
			return;
		}
		if(args.length==5){//si hay 5 argumentos quiere decir que hay un -o para cambiar el Stream de stdin a un archivo
			if(args[3].equalsIgnoreCase("-o")){//si si es -o
				if(new File(args[4]).exists()){//si el archivo si existe mande error
					System.out.println("File "+args[4]+" already exists..");
					return;
				}else{
					FileOutputStream esc = new FileOutputStream(args[4]);//el stream
					esc.write(res);//escriba res que contiene el resultado
					esc.close();//cierre el handler
					System.out.println("Done!");//imprima que lo hizo
				}
			}else{
				ayuda();//si no mande ayuda
				return;
			}
		}else{
			System.out.println(new String(res,0,res.length));//si nunca se le cambio el stream se saca por stdout el resultado 
		}
	}
	public static void ayuda(){//metodo ayuda imprime la ayuda 
		System.out.println("Phicar Vigenere:\nUsage:java Vigenere <option> <String/File> <password> -o <File>"+
				"\n\n<option>\n-c\tEncode a String\n-d\t Decode a String\n-cf\tEncode a "+
				"File\n-df\tDecode a File\n\nThe use of -o is unnecesary, is just for write "+
				"in a file the process.");
	}
	/*ver explicacion de descifrar...solo cambia que este suma las posiciones en vez de restarlas*/
	public static String enc(String txt,String pass){//metodo enc cifra
		pass = genPass(pass);
		String res = "";
		int contaPass = 0;
		for(int n = 0;n<txt.length();n++){
			if(contaPass==pass.length())contaPass*=0;
			int tmp = 0;
			if((tmp = alfa.indexOf(String.valueOf(txt.charAt(n)).toLowerCase())) != -1){
				boolean ma = alfa.indexOf(txt.charAt(n))==-1;
				int tmpcla = alfa.indexOf(pass.charAt(contaPass));
				int tmp2=((tmp+tmpcla)<alfa.length())?(tmp+tmpcla):(tmp+tmpcla)-alfa.length();
				res+=(ma)?String.valueOf(alfa.charAt(tmp2)).toUpperCase():alfa.charAt(tmp2);
			}else{
				res += txt.charAt(n);
				contaPass--;
			}
			contaPass++;
		}
		return res;
	}
	public static String dec(String txt,String pass){//metodo dec descifra
		pass = genPass(pass);//genero la password valida
		String res = "";//res es donde se almacena todo el resultado
		int contaPass = 0;//contaPass sirve para iterar los caracteres de la clave
		for(int n = 0;n<txt.length();n++){//paso cada caracter del texto a descifrar
			if(contaPass == pass.length())contaPass*=0;//si la clave se acaba antes que el texto entonces vuelve a el prim carac
			int temp = 0;
			if((temp = alfa.indexOf(String.valueOf(txt.charAt(n)).toLowerCase())) != -1){//si ese caracter se encuentra, hagale
				int tmpcla = alfa.indexOf(pass.charAt(contaPass));//la posicion en el alfabeto del caracter de la password
				int tmp = ((temp-tmpcla)>=0)?(temp-tmpcla):(temp-tmpcla)+alfa.length();//aca hago la resta de posiciones
				boolean ma = (alfa.indexOf(txt.charAt(n))==-1);//si el caracter es letra y es mayuscula 1 si no 0
				res+=(ma)?String.valueOf(alfa.charAt(tmp)).toUpperCase():alfa.charAt(tmp);//si es mayuscula hago la resta y lo devuelvo a mayuscula
			}else{
				res+=txt.charAt(n);//esto si el caracter no estaba en el alfabeto solamente lo devuelvo a la respuesta
				contaPass--;//si no estaba entonces no desperdicio password :P
			}
			contaPass++;//itero la password
		}
		return res;//retorno
	}
	public static String genPass(String a){//metodo genpass para poner alguna contrasegna valida
		String temp = "";
		for(int n = 0;n<a.length();n++)
			temp +=(alfa.indexOf(String.valueOf(a.charAt(n)).toLowerCase())!= -1)?a.charAt(n):"";//busca si el caracter si esta en el alfabeto entocnes vale como caracter valido sino..tonces no entra
			if(temp.length()<1)temp="phicar";//si la clave no es valida en su totalidad queda como clave phicar :P
			return temp.toLowerCase();//retorne lo que haya en minuscula
	}
}