package imgtostr;

import java.awt.image.BufferedImage;

public class ConvertImg {
	//18 chars
	private static final String[] ch = {"M","N","H","Q","$","O","C","?","7",">","!",":","¨C",";","."};
	public static String toStr(BufferedImage img) {
		StringBuffer sb = new StringBuffer();
		int width = img.getWidth();
		int height = img.getHeight();
		for(int y = 0; y < height; y += 5){
			for(int x = 0; x < width; x += 5) {
				int grey = img.getRGB(x, y) & 0xff;
				sb.append(ch[grey/18]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		
	}

}
