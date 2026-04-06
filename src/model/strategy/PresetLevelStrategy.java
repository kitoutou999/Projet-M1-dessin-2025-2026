package model.strategy;

import model.Point;
import model.shapes.Circle;
import model.shapes.Rectangle;
import model.shapes.Shape;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PresetLevelStrategy implements LevelStrategy {

    private final List<List<Shape>> levels;
    private int currentIndex = 0;

    public PresetLevelStrategy() {
        this.levels = loadFromXml();
    }

    @Override
    public List<Shape> generateLevel() {
        List<Shape> source = levels.get(currentIndex % levels.size());
        currentIndex++;
        return copyShapes(source);
    }

    private List<List<Shape>> loadFromXml() {
        List<List<Shape>> result = new ArrayList<>();
        try {
            InputStream is = getClass().getResourceAsStream("/resources/levels.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList levelNodes = doc.getElementsByTagName("level");
            for (int i = 0; i < levelNodes.getLength(); i++) {
                Element levelEl = (Element) levelNodes.item(i);
                result.add(parseShapes(levelEl));
            }
        } catch (Exception e) {
            throw new RuntimeException("Impossible de charger levels.xml", e);
        }
        return result;
    }

    private List<Shape> parseShapes(Element levelEl) {
        List<Shape> shapes = new ArrayList<>();

        NodeList circles = levelEl.getElementsByTagName("circle");
        for (int i = 0; i < circles.getLength(); i++) {
            Element el = (Element) circles.item(i);
            int cx     = Integer.parseInt(el.getAttribute("cx"));
            int cy     = Integer.parseInt(el.getAttribute("cy"));
            int radius = Integer.parseInt(el.getAttribute("radius"));
            shapes.add(new Circle(new Point(cx, cy), radius));
        }

        NodeList rectangles = levelEl.getElementsByTagName("rectangle");
        for (int i = 0; i < rectangles.getLength(); i++) {
            Element el = (Element) rectangles.item(i);
            int x1 = Integer.parseInt(el.getAttribute("x1"));
            int y1 = Integer.parseInt(el.getAttribute("y1"));
            int x2 = Integer.parseInt(el.getAttribute("x2"));
            int y2 = Integer.parseInt(el.getAttribute("y2"));
            shapes.add(new Rectangle(new Point(x1, y1), new Point(x2, y2)));
        }

        return shapes;
    }

    private List<Shape> copyShapes(List<Shape> source) {
        List<Shape> copy = new ArrayList<>();
        for (Shape s : source) copy.add(s.translated(0, 0));
        return copy;
    }
}
