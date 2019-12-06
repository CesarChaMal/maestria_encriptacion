package msc.cryptography.steganography;

public class Execute {

	public static void main(String args[])
	{
		Steganography_View steganography_View = new Steganography_View("Steganography");
		Steganography steganography = new Steganography();
		
		new Steganography_Controller(steganography_View,steganography);
	}

}
