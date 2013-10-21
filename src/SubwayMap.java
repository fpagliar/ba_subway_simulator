
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SubwayMap {

	/** The texture that will hold the image details */
	private Texture texture;
	private Graphics graphics;
	private static SubwayMap subwayMap = null;
	private List<SubwaySpace> spaces;
	
	public static SubwayMap getInstance() {
		if(subwayMap == null)
			subwayMap = new SubwayMap();
		return subwayMap;
	}
	
	private SubwayMap() {
		graphics = new Graphics();
		Graphics.setCurrent(graphics);
		spaces = new ArrayList<SubwaySpace>();
		//this.start();
	}
	
	/**
	 * Start the example
	 */
	public void start() {
		initGL(928, 700);
		init();

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
	 * Initialise resources
	 */
	private void init() {

		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader
					.getResourceAsStream("resources/mapa_estaciones2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		for(SubwaySpace s : spaces) {
			graphics.setColor(Color.red);
			graphics.fill(new Circle(s.x, s.y, 5));
		}
		Display.update();
		Display.sync(100);
	}
	
	public boolean addSpace(SubwaySpace s) {
		return this.spaces.add(s);
	}
	
	public boolean removeSpace(SubwaySpace s) {
		return this.spaces.remove(s);
	}
	
}