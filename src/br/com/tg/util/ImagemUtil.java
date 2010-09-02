package br.com.tg.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagemUtil {
	
	public static ImageIcon imagemEscalonada(String url, int altura, int largura) throws IOException, NullPointerException {  
		BufferedImage bufferedImage = null;  
		bufferedImage = ImageIO.read(new File(url));  
		return new ImageIcon(bufferedImage.getScaledInstance(altura, largura, 1000));  
	} 

}
