import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SubwayMap {

	public enum Lines {
		A, B, C, D, E, H;
	}

	/** The texture that will hold the image details */
	private Texture texture;
	private Graphics graphics;
	private static SubwayMap subwayMap = null;
	private List<Train> trains;
	private List<Station> stations;
	private Font font;
	private TrueTypeFont ttf;
	private int passengersNotLoadedA = 0;
	private int passengersNotLoadedB = 0;
	private int passengersNotLoadedC = 0;
	private int passengersNotLoadedD = 0;
	private int passengersNotLoadedE = 0;
	private int passengersNotLoadedH = 0;
	private Image arrow_right;
	private Image arrow_left;
	private Image arrow_up;
	private Image arrow_down;

	public static SubwayMap getInstance() {
		if (subwayMap == null) {
			try {
				subwayMap = new SubwayMap();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return subwayMap;
	}

	private SubwayMap() throws SlickException {
		graphics = new Graphics();
		Graphics.setCurrent(graphics);
		trains = new ArrayList<Train>();
		stations = new ArrayList<Station>();
	}

	/**
	 * Start the example
	 */
	public void start() {
		initGL(928, 700);
		init();
		try {
			arrow_down = new Image("resources/icon_arrow_down.gif");
			arrow_up = new Image("resources/icon_arrow_up.gif");
			arrow_left = new Image("resources/icon_arrow_left.gif");
			arrow_right = new Image("resources/icon_arrow_right.gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		render();

		Display.update();
		Display.sync(100);

		if (Display.isCloseRequested()) {
			Display.destroy();
			System.exit(0);
		}
	}

	/**
	 * Initialize the GL display
	 * 
	 * @param width
	 *            The width of the display
	 * @param height
	 *            The height of the display
	 */
	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setVSyncEnabled(true);
			Display.setTitle("Subway Simulator");
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// enable alpha blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	/**
	 * Initialize resources
	 */
	private void init() {

		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader
					.getResourceAsStream("resources/mapa_estaciones2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		font = new Font("Arial", Font.BOLD, 10);
		ttf = new TrueTypeFont(font, true);
	}

	/**
	 * draw a quad with the image on it
	 */
	public void render() {
		Color.white.bind();
		texture.bind(); // or GL11.glBind(texture.getTextureID());

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 0);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(texture.getTextureWidth(), 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(texture.getTextureWidth(), texture.getTextureHeight());
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, texture.getTextureHeight());
		GL11.glEnd();
		for (Train t : trains) {
			// graphics.setColor(t.getColor());
			// graphics.fill(new Circle(t.getX(), t.getY(), 5));
			if (t.getLine().getLineLetter().equals(Lines.H)) {
				if (t.getDirection() == Train.Direction.TO_END)
					graphics.drawImage(arrow_up, t.getX() - 5, t.getY() - 7);
				else
					graphics.drawImage(arrow_down, t.getX() - 5, t.getY() - 7);
			} else if (t.getLine().getLineLetter().equals(Lines.C)) {
				if (t.getDirection() == Train.Direction.TO_START)
					graphics.drawImage(arrow_up, t.getX() - 5, t.getY() - 7);
				else
					graphics.drawImage(arrow_down, t.getX() - 5, t.getY() - 7);
			} else {
				if (t.getDirection() == Train.Direction.TO_END)
					graphics.drawImage(arrow_right, t.getX() - 5, t.getY() - 7);
				else
					graphics.drawImage(arrow_left, t.getX() - 5, t.getY() - 7);
			}
		}
		for (Station s : stations) {
			graphics.setColor(Color.white);
			graphics.fill(new Rectangle(s.getX() - 5, s.getY() - 20, 20, 15));
			Color c = Color.red;
			if (s.getTotalPassengers() < 50)
				c = Color.green.darker();
			else if (s.getTotalPassengers() < 100)
				c = Color.yellow.darker((float) 0.2);
			ttf.drawString(s.getX() - 5, s.getY() - 20, s.getTotalPassengers()
					.toString(), c);
		}
		graphics.setColor(Color.black);
		// ttf.drawString(10, 10, "Pasajeros no subidos: " +
		// Integer.toString(passengersNotLoaded), Color.black);
		Display.update();
		Display.sync(100);
		if (Display.isCloseRequested()) {
			System.exit(0);
		}
	}

	public boolean addTrain(Train t) {
		return this.trains.add(t);
	}

	public boolean removeTrain(Train t) {
		return this.trains.remove(t);
	}

	public boolean addStation(Station s) {
		return this.stations.add(s);
	}

	public void addPassengersNotLoaded(Lines line, int qtty) {
		switch (line) {
		case A:
			passengersNotLoadedA += qtty;
			break;
		case B:
			passengersNotLoadedB += qtty;
			break;
		case C:
			passengersNotLoadedC += qtty;
			break;
		case D:
			passengersNotLoadedD += qtty;
			break;
		case E:
			passengersNotLoadedE += qtty;
			break;
		case H:
			passengersNotLoadedH += qtty;
			break;

		default:
			break;
		}
	}

	public int getPassengersNotLoadedA() {
		return passengersNotLoadedA;
	}

	public int getPassengersNotLoadedB() {
		return passengersNotLoadedB;
	}

	public int getPassengersNotLoadedC() {
		return passengersNotLoadedC;
	}

	public int getPassengersNotLoadedD() {
		return passengersNotLoadedD;
	}

	public int getPassengersNotLoadedE() {
		return passengersNotLoadedE;
	}

	public int getPassengersNotLoadedH() {
		return passengersNotLoadedH;
	}

}