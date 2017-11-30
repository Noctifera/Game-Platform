package modal;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import hibernate.MapsTable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class FileReader {

	private Map map;
	private Draw draw;

	private final File folder = new File("Maps");

	private List<MapsTable> mapList = null;

	public FileReader(Map map, Draw draw) {
		this.map = map;
		this.draw = draw;
	}

	public void getAllMapsFromFile() {
		mapList = new ArrayList<>();
		File[] files = folder.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.contains(".txt");
			}
		});
		for (File f : files) {
			mapList.add(new MapsTable(f.getName(), readMapFromFile(f), readImageFromFile(f.getName().replaceAll(".txt", ""))));
		}
	}

	@SuppressWarnings("unchecked")
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
		System.out.println(mapName);
		File f = new File(folder + "//" + mapName+".PNG");
		
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
			e.printStackTrace();
		}

		return data;
	}

	private void SaveImageToFile(String fileName) throws IOException {
		File file = new File(folder + "//" + fileName+".PNG");
		byte[] image = draw.getImage();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(image);
		fos.flush();
		fos.close();
	}

	public boolean saveMapToFile(String fileName) {
		String newfileName = "";
		if (!fileName.contains("txt")) {
			 newfileName = fileName + ".txt";
		}
		File file = new File(folder + "//" + newfileName);
		ObjectOutputStream obj = null;

		try {
			SaveImageToFile(fileName);
			obj = new ObjectOutputStream(new FileOutputStream(file));
			obj.writeObject(map.getMap());
			obj.flush();
			obj.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
