package widget;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.ShapeElement;

public class Widget extends JFrame {

	public Widget() {
		this.setUndecorated(true);
		this.setSize(200, 200);
		this.add(new JLabel(new ImageIcon("apple.jpg")));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Map<String, Object> map = getRightPosition(getSize());
		
		this.setLocation(Integer.parseInt(map.get("width").toString())
				, Integer.parseInt(map.get("height").toString()));
		
		Shape shape = new Ellipse2D.Float(0, 0, 200, 200);
		shape = getShape("apple.svg");
		
		this.setShape(shape);
		this.setAlwaysOnTop(true);
	}
	
	public Shape getShape(String svgFile) {
		try {
			URI uri = new File(svgFile).toURI();
			SVGDiagram diagram = SVGCache.getSVGUniverse().getDiagram(uri);
			SVGElement element = diagram.getElement("path51398");
			java.util.List list = (java.util.List)(element.getPath(null));
			
			return ((ShapeElement)(list.get(0))).getShape();
		}
		catch (Exception e) {
			
		}
		return null;
	}
	
	public Map<String, Object> getRightPosition(Dimension frameSize) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("width", (screenSize.width - frameSize.width));
		map.put("height", (0));
		
		return map;
	}
	
	public static void main(String[] args) {
		new Widget();
	}
}
