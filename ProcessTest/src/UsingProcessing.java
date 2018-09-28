import processing.core.PApplet;
import processing.core.PImage;

public class UsingProcessing extends PApplet{
// Paddle
int xPosPad = 160;
int yPosPad = 740;
int breitePad = 192;
int hoehePad = 10;
int x1Pad, x2Pad;
int y1Pad, y2Pad;
// Hintergrundbild
PImage img;
// Ball
int xPosBall = 160;
int yPosBall = 160;
int breiteBall = 10;
int hoeheBall = 10;
int x1Ball, x2Ball;
int y1Ball, y2Ball;
int radiusBall;

// Bewegung Ball
int deltaXBall = 2;
int deltaYBall = 10;
// SpielfeldGröße = Größe Hintergrundbild
int x1PlayGround = 0;
int y1PlayGround = 0;
int x2PlayGround = 1024;
int y2PlayGround = 768;

	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}
	
	public void settings() {
			size(x2PlayGround,y2PlayGround);

	}
	
	public void setup() {
		img = loadImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Jellyfish.jpg");
//		colorMode(RGB, 255, 0, 64);		
		fill(10,0,240);
		x1Pad = xPosPad;
		x2Pad = xPosPad + breitePad;
		y1Pad = yPosPad;
		y2Pad = yPosPad + hoehePad;
	}
	
	public void draw() {
		ellipse(width/2,height/2,second(),second());
		//background(180,160,64);
		background(img);
		textSize(16);
		text("xPosLinks=" + xPosPad, 10, 60);
		text("xPosRechts=" + (xPosPad+breitePad), 10, 80);
		text("xBall=" + xPosBall, 800, 60);
		text("yBall=" + yPosBall, 800, 80);
		fill(64);
		rect(xPosPad,yPosPad,breitePad,hoehePad,10);
		ellipseMode(RADIUS);
		fill(255);
		ellipse(xPosBall,yPosBall,breiteBall,hoeheBall);
		System.out.println("xPosBall="+xPosBall);
	//	System.out.println("xPosLinks="+xPosPad);
	//  System.out.println("xPosRechts="+(xPosPad+breitePad));
//		System.out.println("yPosBall="+yPosBall);		
	//	System.out.println("yPosPad="+yPosPad);
  		moveBall();
	}
	
		public void keyPressed() {
		System.out.println("KeyCode = " + keyCode);	
		movePad(keyCode);
	}
		
		
	public void moveBall() {
		int xBall=60;
		int yBall=50;
		int xPad = 0;
		int yPad = 0;
		int xW = 0;
		int yW = 0;
		int mx = 10;
		int my = 5;
		int x1 = 0;
		int x2 = 0;
		int y1 = 0;
		int y2 = 0;
		int WandX1 = 0; //Koordinaten linke obere Ecke Spielfeld
		int WandY1 = 0;
		int WandX2 = 1024;
		int WandY2 = 768;
		int dmx, dmy;   //Vektor des Balles bis zum Hinderniss
		
		x1 = xPosBall;
		y1 = yPosBall;
		
   // Spielfeldrand getroffen?
		x2 = mx + x1;
		y2 = my + y1;
	// Kollision seitlich (links/rechts)		
		if (x2<WandX1 || x2>WandX2) {
			dmx = mx - ( WandX1 - x2);
			dmy = (dmx * my) / mx;
			xW = x1 + dmx;
			yW = y1 + dmy;
			mx = -1 * mx;	
			x2 = xW;
			y2 = yW;
			System.out.println("x2="+x2+"  y2="+y2);
	// Kollision horizontal (oben/unten)
		} else if (y2<WandY1 && y2>WandY2) {
			dmy = WandY1 - y1;
			dmx = (mx/my) * dmy;
			xW = x1 + dmx;
			yW = y1 + dmy;
			my = -1 * my;
			x2 = xW;
			y2 = yW;
			// Paddle getroffen?
		}  

		xPosBall = x2;
		yPosBall = y2;
		
//		xBall = xPosBall + deltaXBall;
//		yBall = yPosBall + deltaYBall;
//		
//		//-- links angestossen----
//		if ((xBall >  x1PlayGround) && (xBall<x2PlayGround)) {
//			// Ball befindet sich innerhalb des Spielfeldes
//		} else {
//			// Ball hat senkrechte Begrenzung überschritten
//			dmx = deltaXBall - ( x1PlayGround - xBall) ; // Vorzeichen passt noch nicht
//		}
		
		
		
//		//----------------------------------------------------------------
//		xBall = xPosBall + deltaXBall;
//		yBall = yPosBall + deltaYBall;
//		
//		// Paddle getroffen?
//		xPad = xPosPad;
//		yPad = yPosPad;
//		
//		if (xBall>=xPad && (xBall<=(xPad+breitePad)) && (yBall>yPad)) {
//			xPosBall = xPosBall+deltaXBall-xPad+xPosBall; 
//			yPosBall = yPosBall+deltaYBall-yPad+yPosBall;  
//			deltaYBall = -1*deltaYBall;
//		} else if (xBall>x2PlayGround) {
//			xPosBall = xPosBall+deltaXBall-x2PlayGround+xPosBall;  //Formel: xPlayGround-(XBall-xPlayGround) nur umgestellt
//			deltaXBall = -1*deltaXBall;
//		} else if (xBall<0) {
//			xPosBall = -1*(xPosBall+deltaXBall+xPosBall);  //Formel: xPlayGround-(XBall-xPlayGround) nur umgestellt
//			deltaXBall = -1*deltaXBall;
//		} else {
//			xPosBall = xBall;
//		}
//		if (yBall>y2PlayGround) {
//			yPosBall = yPosBall+deltaYBall-y2PlayGround+yPosBall;  //Formel: xPlayGround-(XBall-xPlayGround) nur umgestellt
//			deltaYBall = -1*deltaYBall;
//		}else if(yBall<0) {
//			yPosBall = -1*(yPosBall+deltaYBall+yPosBall);  //Formel: xPlayGround-(XBall-xPlayGround) nur umgestellt
//			deltaYBall = -1*deltaYBall;			
//		} else {
//			yPosBall = yBall;
//		}
//		
//		
//		// Paddle getroffen?
//		xPad = xPosPad;
//		yPad = yPosPad;
//		
//		if (xPosBall>=xPad && (xPosBall<=(xPad+breitePad)) && (yPosBall>yPad)) {
//			xPosBall = xPosBall+deltaXBall-xPad+xPosBall; 
//			yPosBall = yPosBall+deltaYBall-yPad+yPosBall;  
//			deltaYBall = -1*deltaYBall;
//		}
//		} else {
//			xPosBall = xBall;
//		}
//		if (yPosBall>yPad && yPosBall<(yPad+hoehePad)) {
//			yPosBall = yPosBall+deltaYBall-yPad+yPosBall;  //Formel: xPlayGround-(XBall-xPlayGround) nur umgestellt
//			deltaYBall = -1*deltaYBall;
//		}else  {
//			yPosBall = yBall;
//		}
		
//		System.out.println("Paddle");
	}
	
	
	

	
	/**
	 * Paddle bewegen a+<- = links; s+-> = rechts
	 * Integer: val 
	 * @param  val = ASCII-Code der gedrückten Taste
	 */
	public void movePad(int val) {
		System.out.println("val=" + val);
		switch (val) {
		case 37:				//Taste Pfeil links
		case 65: xPosPad -= 16; //Taste a
				break;
		case 39:  			    //Taste Pfreil rechts
		case 83: xPosPad += 16; //Taste s
				break;
		default:  break;
		}
		
		// Paddle stößt an linken/rechten Rand an und bleibt dort stehen
		if (xPosPad >= 832) {
			xPosPad = 832;
		}
		if (xPosPad <= 0) {
			xPosPad = 0;
		} 

	}
}
