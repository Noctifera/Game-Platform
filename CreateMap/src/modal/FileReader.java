package modal;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class FileReader {

	private Map map;
	private Draw draw;

	private final File folder = new File("Maps");
	private final File picture = new File("Pictures");

	private List<MapsTable> mapList = null;

	public FileReader(Map map, Draw draw) {
		this.map = map;
		this.draw = draw;
	}

	public void getAllMapsFromFile() {
		mapList = new ArrayList<>();
		File[] files = folder.listFiles();
		for (File f : files) {
			mapList.add(new MapsTable(f.getName(), readMapFromFile(f), readImageFromFile(f.getName())));
		}
	}

	private HashMap<Point, String> readMapFromFile(File fileName) {

		HashMap<Point, String> tmp = new HashMap<>();
		try {

			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			tmp = (HashMap<Point, String>) dataIn.readObject();
			dataIn.close();
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public HashMap<Point, String> getMap(String mapName) {
		for (MapsTable m : mapList) {
			if (m.getMapName().equals(mapName)) {
				System.out.println(m.getMapData());
				return m.getMapData();
			}
		}
		return null;

	}

	private byte[] readImageFromFile(String mapName) {
		File f = new File(picture + "//" + mapName+".PNG");
		
		Image image = new  Image(f.toURI().toString());
		
		byte[] data = null;
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ImageIO.write(bImage, "PNG", baos);
			baos.flush();
			data = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	private void SaveImageToFile(String fileName) throws IOException {
		File file = new File(picture + "//" + fileName+".PNG");
		byte[] image = draw.getImage();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(image);
		fos.flush();
		fos.close();
	}

	public boolean saveMapToFile(String fileName) {
		if (!fileName.contains("txt")) {
			fileName = fileName + ".txt";
		}
		File file = new File(folder + "//" + fileName);
		ObjectOutputStream obj = null;

		try {
			SaveImageToFile(fileName);
			obj = new ObjectOutputStream(new FileOutputStream(file));
			obj.writeObject(map.getMap());
			obj.flush();
			obj.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<String> GetMapNamesFromFile() {
		List<String> list = new ArrayList<>();
		for (MapsTable mt : mapList) {
			list.add(mt.getMapName());
		}
		return list;
	}
}
