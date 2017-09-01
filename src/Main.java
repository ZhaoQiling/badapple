import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import imgpro.ImageProcess;
import imgtostr.ConvertImg;
import videopro.VideoProcess;
public class Main {

	public static void main(String[] args) {
		try {
			BufferedImage bfimg = new ImageProcess(new File(".\\res\\lenna.jpg")).getGreyImg();
//			File outputfile = new File(".\\out\\out.jpg");
//			ImageIO.write(bfimg, "jpg", outputfile);
			
			ArrayList<BufferedImage> imgs = VideoProcess.fetchFrames(".\\res\\badapple.mp4");
			for(int i = 0; i < imgs.size(); i++) {
				System.out.println(i);
				System.out.println(ConvertImg.toStr(new ImageProcess(imgs.get(i)).getGreyImg()));
//				File outputfile = new File(".\\out\\" + String.format("%04d", i) + ".jpg");
//				ImageIO.write(imgs.get(i), "jpg", outputfile);
//				System.out.println("has output " + i + " imgs");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
