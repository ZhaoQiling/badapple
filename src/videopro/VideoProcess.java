package videopro;
 
import java.awt.image.BufferedImage;  

import java.util.ArrayList;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.FFmpegFrameGrabber;  
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;  

//import java.awt.image.BufferedImage;  
//import java.awt.image.DataBufferByte;  
//import java.awt.image.WritableRaster;  
//import java.io.File;  
  
//import javax.imageio.ImageIO;  
//  
//import org.bytedeco.javacpp.BytePointer;  
//import org.bytedeco.javacpp.opencv_core.IplImage;  
//import org.bytedeco.javacpp.opencv_highgui; 

public class VideoProcess { 
	private static BufferedImage IplImageToBufferedImage(IplImage src) {
	    OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
	    Java2DFrameConverter paintConverter = new Java2DFrameConverter();
	    Frame frame = grabberConverter.convert(src);
	    return paintConverter.getBufferedImage(frame,1);
	}
    
	public static ArrayList<BufferedImage> fetchFrames(String videofile) throws Exception{  
    	ArrayList<BufferedImage> fetchedimgs = new ArrayList<>();
    	Loader.load(opencv_objdetect.class);  
    	FrameGrabber grabber =FFmpegFrameGrabber.createDefault(videofile);
    	grabber.start();
    	
    	OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();  
    	int length = grabber.getLengthInFrames();
    	Frame grabframe = null;
    	IplImage grabbedImage =null;  
    	for(int i = 0; i < length; i++) {
    		grabframe = grabber.grab();
    		grabbedImage = converter.convert(grabframe);

    		if(grabbedImage == null)
    			continue;

			BufferedImage bi = IplImageToBufferedImage(grabbedImage);
			
			fetchedimgs.add(bi);
    	}
    	return fetchedimgs;
    }  
    public static void main(String[] args) throws Exception {
    	System.out.println(VideoProcess.fetchFrames(".\\res\\badapple.mp4").size());
    }

}
