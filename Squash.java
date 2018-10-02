import java.util.ArrayList;
import java.lang.Math;

import processing.core.PApplet;
import processing.core.PImage;


public class Squash extends PApplet {
// Paddle
	int xPosPad = 730;
	int yPosPad = 740;
	int breitePad = 192;
	int hoehePad = 20;
	int x1Pad, x2Pad;
	int y1Pad, y2Pad;
// Hintergrundbild
	PImage img;
// Ball
	int xPosBall = 500;
	int yPosBall = 500;
	int breiteBall = 8;
	int hoeheBall = 8;
	int x1Ball, x2Ball;
	int y1Ball, y2Ball;
	int radiusBall;
// Hindernisse
	ArrayList<Brick> bricks = new ArrayList<>();
	ArrayList<Brick> WallBricks = new ArrayList<>();
// Bewegung Ball
	int deltaXBall = -8;
	int deltaYBall = -4;
// SpielfeldGröße = Größe Hintergrundbild
	int x1PlayGround = 0;
	int y1PlayGround = 0;
	int x2PlayGround = 1024;
	int y2PlayGround = 768;
	int z = 1;
// Wertung
	int score = 0;
	int AnzahlSteine; // = 11;
	int AnzahlReihen; // = 3;
	
	boolean Ende = false;
	boolean Start = false;

	public static void main(String[] args) {
		PApplet.main("Squash");
	}

	public void settings() {
		size(x2PlayGround, y2PlayGround);

	}

	public void setup() {
		int x1,x2,y1,y2, value;
		int breiteStein, hoeheStein, lueckeStein,  abstandReihe;
		double bS, hS, lS;
		boolean active;
		img = loadImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Jellyfish.jpg");
		fill(10, 0, 240);
		x1Pad = xPosPad;
		x2Pad = xPosPad + breitePad;
		y1Pad = yPosPad;
		y2Pad = yPosPad + hoehePad;
		bS = Math.rint((double)x2PlayGround/((double)AnzahlSteine+((double)AnzahlSteine+1)/3));
		hS = Math.rint(bS / 2);
		lS = Math.rint(bS / 3);
		breiteStein = (int) bS;
		hoeheStein = (int) hS;
		lueckeStein = (int) lS;
		abstandReihe = lueckeStein;
		Brick Wand = new Brick();
		Wand.setBrickParm(x1PlayGround, y1PlayGround, x2PlayGround, (y2PlayGround+2*hoeheBall), 0, true);
		WallBricks.add(Wand);
		for (int r=0; r<AnzahlReihen; r++) {
			for (int i=0; i<AnzahlSteine; i++) {
				Brick Baustein = new Brick();
				x1=i*breiteStein + (i+1)*lueckeStein;
				y1=(r+1)*abstandReihe+r*hoeheStein;
				x2=x1+breiteStein;
				y2=y1+hoeheStein;
				value=1;
				active=true;
				Baustein.setBrickParm(x1, y1, x2, y2, value, active);
				bricks.add(Baustein);
				WallBricks.add(Baustein);
			}
		}
		
	}

	public void draw() {
		if (Start == false) {
			drawstart();
		} else {
			background(img);
		textSize(16);
		if (Ende) {
//			noLoop();
			background(img);
			text("Leider Verloren",300,200,100);
			text("Press n for new game", 300,250,50);
			text("Press e to end", 300,300,50);
				while (Ende == true) {

					if (keyCode == 78) {
						System.out.println(keyPressed + "Code: " + keyCode);
						Start = false;
						Ende = false;
					} else if (keyCode == 69) {
						System.out.println(keyPressed + "Code: " + keyCode);
						exit();
					}
				}
			}
		if (score==(AnzahlReihen*AnzahlSteine)){
			noLoop();
			text("Gewonnen -- Juhu --", 300,200,100);
			Start = true;
			Ende = true;
		}
		text("Score: "+ score, 10, 600);
		fill(64);
			if (Ende == false) {
				for (Brick b : bricks) {
					fill(128);
					if (b.getActive()) {
						rect(b.getX1(), b.getY1(), (b.getX2() - b.getX1()), (b.getY2() - b.getY1()));
					}
				}
				rect(xPosPad, yPosPad, breitePad, hoehePad, 10);
				ellipseMode(RADIUS);
				fill(255);
				ellipse(xPosBall, yPosBall, breiteBall, hoeheBall);
				move();
			}
	}
	}

	public void keyPressed() {
		if (Start==false) {
			level(keyCode);
		} else {
			movePad(keyCode);
		}
	}

	
	public void drawstart() {
		int auswahl;
		background(img);
		text("Difficulty: ",200,200,50);
		text("Press e = easy",200,300,30);
		text("      n = normal",200,350,30);
		text("      d = difficult",200,400,30);
	}
	
	void level(int auswahl) {
		while (Start == false) {
			switch (auswahl) {
			case 69:
				AnzahlSteine = 4;
				AnzahlReihen = 2;
				Start = true;
				Ende = false;
				break;
			case 78:
				AnzahlSteine = 7;
				AnzahlReihen = 3;
				Start = true;
				Ende = false;
				break;
			case 68:
				AnzahlSteine = 11;
				AnzahlReihen = 4;
				Start = true;
				Ende = false;
				break;
			default:
				Start = false;
				Ende = false;
				break;
			}
			settings();
			setup();

		}
	}

	/**
	 * Paddle bewegen a+<- = links; s+-> = rechts Integer: val
	 * 
	 * @param val = ASCII-Code der gedrückten Taste
	 */
	public void movePad(int val) {
		System.out.println("val=" + val);
		switch (val) {
		case 37: // Taste Pfeil links
		case 65: // Taste a
			xPosPad -= 30;
			break;
		case 39: // Taste Pfeil rechts
		case 83: // Taste s
			xPosPad += 30;
			break;
		default:
			break;
		}

		// Paddle stößt an linken/rechten Rand an und bleibt dort stehen
		if (xPosPad >= 832) {
			xPosPad = 832;
		}
		if (xPosPad <= 0) {
			xPosPad = 0;
		}

	}


	public void move() {
		int WandX1 = 0;
		int WandY1 = 0;
		int WandX2 = 0; //1024;
		int WandY2 = 0; //768;
		float mx, my; // Vektor Ball
		float x1, y1; // Pos Ball zu Beginn
		float x2, y2; // Pos Ball am Ende
		float fx2;
		float fy2;
		
		mx = deltaXBall;
		my = deltaYBall;
		x1 = xPosBall;
		y1 = yPosBall;

		x2 = x1 + mx;
		y2 = y1 + my;

		if (y2 >= yPosPad ) { // Treffer waagrecht von oben   && (abs(yPosPad - y1)) <= abs(my)
			x2 = (y1 - ((my / mx) * x1) - yPosPad) * (-1) * (mx / my);
			x2 = Math.round(x2);
			x2 = (int) Math.abs(x2);
			if (x2 >= xPosPad && x2 <= (xPosPad + breitePad)) {
				y2 = yPosPad;
				deltaYBall = (int) (-1 * my);
			} else {
				y2 = y1 + my;
				x2 = x1 + mx;
				if (y2 > WandY2) {
					Ende = true;
				}
			}
		}
				
		for (Brick b : WallBricks) {
			WandX1 = b.getX1();
			WandX2 = b.getX2();
			WandY1 = b.getY1();
			WandY2 = b.getY2();
			
			if (b.getActive()) {
			if (x2 <= WandX1 && (abs(WandX1 - x2)) < abs(mx)) { // Treffer Senkrecht von rechts
				fy2 = y1 + (my / mx) * (WandX1 - x1);
				fy2 = round(fy2);
				fy2 = (int) abs(fy2);
				if (fy2 >= WandY1 && fy2 <= WandY2) {
					x2 = WandX1;
					y2 = fy2;
					deltaXBall = (int) (-1 * mx);
					if (x2!=x1PlayGround) {
						b.setActive(false);
						score += b.getValue();
					}
					break;
				}
			} else if (x2 >= WandX2 && (abs(WandX2 - x2)) < abs(mx)) { // Treffer senkrecht von links
				fy2 = y1 + (my / mx) * (WandX2 - x1);
				fy2 = round(fy2);
				fy2 = (int) abs(fy2);
				if (fy2 >= WandY1 && fy2 <= WandY2) {
					x2 = WandX2;
					y2 = fy2;
					deltaXBall = (int) (-1 * mx);
					if (x2!=x2PlayGround) {
						b.setActive(false);
						score += b.getValue();
					}
					break;
				}
			} else if (y2 <= WandY2 && (abs(WandY2 - y2)) < abs(my)) { // Treffer waagrecht von unten
				fx2 = (y1 - (my / mx) * x1 - WandY2) * (-1) * (mx / my);
				fx2 = round(fx2);
				fx2 = (int) abs(fx2);
				if (fx2 >= WandX1 && fx2 <= WandX2) {
					x2 = fx2;
					y2 = WandY2;
					deltaYBall = (int) (-1 * my);
					if (y2!=y1PlayGround) {
						b.setActive(false);
						score += b.getValue();
					}
					b.setActive(false);
					break;
				}
			} else if (y2 >= WandY1 && (abs(WandY1 - y2)) < abs(my)) { // Treffer waagrecht von oben
				fx2 = (y1 - ((my / mx) * x1) - WandY1) * (-1) * (mx / my);
				fx2 = Math.round(fx2);
				fx2 = (int) Math.abs(fx2);
				if (fx2 >= WandX1 && x2 <= WandX2) {
					x2 = fx2;
					y2 = WandY1;
					deltaYBall = (int) (-1 * my);
					if (y2!=y2PlayGround) {
						b.setActive(false);
						score += b.getValue();
					}
					break;
				}
			}
			}
					// Ende for (Brick b:bricks)
					xPosBall = (int) x2;
					yPosBall = (int) y2;
					z++;
				}
			}
}
	

	
				
