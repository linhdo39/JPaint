package model;

import java.awt.Color;
import java.util.EnumMap;

public class ColorSingleton {
	private static final EnumMap<ShapeColor, Color> colorMap = new EnumMap<>(ShapeColor.class);
	private static ColorSingleton instance;
	private ColorSingleton() {
		colorMap.put(ShapeColor.BLACK, Color.BLACK);
		colorMap.put(ShapeColor.BLUE, Color.BLUE);
		colorMap.put(ShapeColor.CYAN, Color.CYAN);
		colorMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
		colorMap.put(ShapeColor.GRAY, Color.GRAY);
		colorMap.put(ShapeColor.GREEN, Color.GREEN);
		colorMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
		colorMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
		colorMap.put(ShapeColor.ORANGE, Color.ORANGE);
		colorMap.put(ShapeColor.PINK, Color.PINK);
		colorMap.put(ShapeColor.RED, Color.RED);
		colorMap.put(ShapeColor.WHITE, Color.WHITE);
		colorMap.put(ShapeColor.YELLOW, Color.YELLOW);
	}
	
	public static Color getColor(ShapeColor color) {
		if(instance == null)
			instance = new ColorSingleton();
		return colorMap.get(color);
	}
}
