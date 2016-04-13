package systemComponents;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * The Sprite class is used to animate given sprite sheets.
 * I think its pretty cool.
 * This is my pride and Joy.
 * I would take a bullet for this class. I poured my life blood into this. You guys dont even
 * appreciate it, DO YOU!?!? THIS IS THE DEFINITIVE SPRITE CLASS! AAAAAAAAAAAAAAAAAAAAA!
 * @author Kile_DaSilva
 *
 */
public class Sprite
{
	/**
	 * Holds a reference to an array of images
	 */
	private BufferedImage[] frames;
	
	/**
	 * The number of frames in the sprite
	 */
	private int numFrames;
	
	/**
	 * The current index of the frame being shown
	 */
	private int index;
	
	/**
	 * The width of the actual sheet
	 */
	public int sheetHeight;
	
	/**
	 * The height of the actual sheet
	 */
	public int sheetWidth;
	
	/**
	 * How quickly it cycles through. Speed is how much the counter needs to be to increment/decrement
	 * the frame. SpeedCurrent is a counter. When speedCurrent==speed, speedCurrent is set to zero
	 * and something happens to the index
	 */
	private int speed, speedCurrent;
	
	/**
	 * Controls how the sprite reacts when finished
	 */
	private boolean reverseWhenFinished = false;
	private boolean reversing = false;

	/**
	 * Constructor
	 * @param imagePath The file location of the image
	 * @param width The width of each frame
	 * @param height The height of each frame
	 */
	public Sprite(String imagePath, int width, int height)
	{
		// By default this is zero
		speed = 0;
		speedCurrent = 0;
		
		// Local sprite sheet to be loaded
		BufferedImage sheet;
		
		// Try to load the image, if it fails everything goes wrong
		try {
			sheet = ImageIO.read((getClass().getResource(imagePath)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showInputDialog(imagePath+" could not be loaded");
			e.printStackTrace();
			System.out.println(imagePath);
			sheet = null;
		}

		// If the sheet is not null prep an array
		if(sheet!=null)
		{
			numFrames = (sheet.getWidth()/width)*(sheet.getHeight()/height);
			frames = new BufferedImage[numFrames];
		}

		// Start the index at 0
		int index = 0;

		// If the sheet is exists
		if(sheet!=null)
		{
			// Set up dimensions
			sheetWidth = sheet.getWidth();
			sheetHeight = sheet.getHeight();
		
			// Loop through and grab each frame
			for(int j = 0; j<sheet.getHeight();j+=height)
			{
				for(int i = 0; i<sheet.getWidth(); i+=width)
				{
					BufferedImage frame = sheet.getSubimage(i, j, width, height);
					frames[index++] = frame;
				}
			}
		}
	}

	/**
	 * Set the speed at which this loops
	 * @param speed how fast it loops
	 */
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	/**
	 * Set whether or not the animation reverses when finished
	 * @param rev boolean true is yes, false is no
	 */
	public void setReverseWhenFinished(boolean rev)
	{
		reverseWhenFinished = rev;
	}

	/**
	 * Switch to the next frame in the sequence
	 */
	public void nextFrame()
	{
		if(frames!=null)
			if(!reversing)
			{
				index++;
				if(index==frames.length)
					if(!reverseWhenFinished)
						index = 0;
					else
					{
						index--;
						reversing = true;
					}	

			}
			else
			{
				index--;
				if(index<0)
				{
					if(!reverseWhenFinished)
						index=frames.length-1;
					else
					{
						index=1;
						reversing = false;
					}
				}
			}
	}

	/**
	 * Increment frame after the set speed (or time) has passed
	 */
	public void timedIncrementFrame()
	{
		speedCurrent++;
		if(speedCurrent>=speed)
		{
			incrementFrame();
			speedCurrent = 0;
		}
	}

	/**
	 * Decrement frame after the set speed (or time) has passed
	 */
	public void timedDecrementFrame()
	{
		speedCurrent++;
		if(speedCurrent>=speed)
		{
			decrementFrame();
			speedCurrent = 0;
		}
	}
	
	/**
	 * Increment the frame shown
	 */
	public void incrementFrame()
	{
		if(frames!=null){
		index++;
		if(index>=frames.length)
			index = 0;
		}
	}

	/**
	 * Decrement the frame shown
	 */
	public void decrementFrame()
	{
		if(frames!=null)
		{
			index--;
			if(index<0)
				index = frames.length-1;
		}
	}

	/**
	 * Get a reference to the selected frame
	 * @param f index of desired frame
	 * @return reference to the frame
	 */
	public BufferedImage getFrame(int f)
	{
		if(frames!=null)
			return frames[f];
		else
			return new BufferedImage(0,0,0);
	}

	/**
	 * First frame returns to the first frame of the sprite
	 */
	public void firstFrame()
	{
		index = 0;
	}
	
	/**
	 * Draw the current frame to the pane
	 * @param pane Graphics object
	 * @param x location of sprite (x)
	 * @param y location of sprite (y)
	 */
	public void draw(Graphics pane, int x, int  y)
	{
		if(frames!=null)
			pane.drawImage(frames[index],x,y,null);
	}

	/**
	 * Draw the current frame to the pane scaled
	 * @param pane Graphics object
	 * @param x location of sprite (x)
	 * @param y location of sprite (y)
	 * @param scaleX How much the width is scaled by
	 * @param scaleY How much the height is scaled by
	 */
	public void draw(Graphics pane, int x, int  y, double scaleX, double scaleY)
	{

		if(frames!=null){
			AffineTransform at = new AffineTransform();
			BufferedImage after = new BufferedImage((int)(frames[index].getWidth()*scaleX),
					(int)(frames[index].getHeight()*scaleY),BufferedImage.TYPE_INT_ARGB);
			at.scale(scaleX, scaleY);
			AffineTransformOp scaler = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

			after = scaler.filter(frames[index], after);
			pane.drawImage(after,x,y,null);
		}
	}

}
