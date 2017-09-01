package imgpro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcess {
	private BufferedImage img = null;
	private Integer imgType = null;
	private Integer height = null;
	private Integer weight = null;
	private final Double perR = 0.3;
	private final Double perG = 0.59;
	private final Double perB = 0.11;
	
	private static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;
		return newPixel;
}
	public ImageProcess(File file) {
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		height = img.getHeight();
		weight = img.getWidth();
		imgType = img.getType();
	}
	public ImageProcess(BufferedImage img) {
		this.img = img;
		height = img.getHeight();
		weight = img.getWidth();
		imgType = img.getType();
	}
	public BufferedImage getGreyImg() {
		BufferedImage greyImg = new BufferedImage(weight, height, imgType);
		for(int x = 0; x < weight; x++)
			for(int y = 0; y < height; y++) {
				int originRGB = img.getRGB(x, y);
				int originR = (originRGB & 0xff0000) >> 16;
				int originG = (originRGB & 0xff00) >> 8;
				int originB = (originRGB & 0xff);
				int greyVal = (int) (originR * perR + originG * perG + originB * perB); 
				greyImg.setRGB(x, y, colorToRGB(255, greyVal, greyVal, greyVal));
			}
		return greyImg;
	}
	public static void main(String[] args) {

		
	}

}
