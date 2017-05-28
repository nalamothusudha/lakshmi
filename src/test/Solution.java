package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

	public static void main(String[] args) {

		String q="photo.jpg, Warsaw, 2013-09-05 14:08:15\n"
				+"john.png, London, 2015-06-20 15:13:22\n"
				+ "my-Friends.png, Warsaw, 2013-09-05 14:07:13\n"
				+ "Eiffel.ppg, Paris, 2015-07-23 08:03:02\n"
				+ "pisatower.jpg, Paris, 2015-07-22 23:59:59\n"
				+ "BOB.ipg, London, 2015-08-05 00:02:03\n"
				+ "notredame.png, Paris, 2015-09-01 12:00:00\n"
				+ "me.jpg, Warsaw, 2013-09-06 15:40: 22\n"
				+ "a.png, Warsaw, 2016-02-13 13:33:50\n"
				+ "b.jpg, Warsaw, 2016-01-02 15:12:22\n"
				+ "c.jpg, Warsaw, 2016-01-02 14:34:30\n"
				+ "d.jpg, Warsaw, 2016-01-02 15:15:01\n"
				+ "e.png, Warsaw, 2016-01-02 09:49:09\n"
				+ "f.png, Warsaw, 2016-01-02 10:5:32\n"
				+ "g.jpg, Warsaw, 2016-02-29 22:13:11";

		Solution s = new Solution();
		s.solution(q);
	}

	public String solution(String S) {
		String imageNames[] = S.split("\\n");
		List<Image> list = new ArrayList<Image>();
		for(int i = 0; i < imageNames.length; i++) {
			try {
				list.add(Image.parsePhoto(imageNames[i]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//Get map of images group by city and sorted by time asc
		Map<String, List<Image>> cityMap = getSortedImages(list);

		StringBuilder result = new StringBuilder(); 
		return getOrderedString(list, cityMap, result);

	}

	/**
	 * 
	 * @param list - List of images
	 * @return Map of sorted images 
	 */
	private Map<String, List<Image>> getSortedImages(List<Image> list) {
		Map<String, List<Image>> cityMap = list
				.stream()
				.sorted(new PhotoComparatorAndSorter())
				.collect(Collectors.groupingBy(Image::getCity));
		return cityMap;
	}

	/**
	 * 
	 * @param list - List of images
	 * @param cityMap - map of images group by city and sorted by time asc
	 * @param result -  result before appending leading zeros
	 * @return final solution string
	 */
	private String getOrderedString(List<Image> list, Map<String, List<Image>> cityMap, StringBuilder result) {
		for(int i = 0; i < list.size(); i++) {
			Image image = list.get(i);
			int position = cityMap.get(image.imageCity).indexOf(image)+1;
			int zeroes = cityMap.get(image.imageCity).size()/10 - position/10;
			result.append(image.imageCity);
			for(int j = 0; j < zeroes; j++) {
				result.append("0");
			}
			result.append(position);
			result.append(".").append(image.imageType).append("\n");
		}
		return result.toString();
	}

	/**
	 * 
	 * @author sudha
	 * image date comparator
	 */
	public static class PhotoComparatorAndSorter implements Comparator<Image>{

		@Override
		public int compare(Image image1, Image image2) {
			return image1.getDate().compareTo(image2.getDate());
		}
	}

	/**
	 * 
	 * @author sudha
	 *	Image Model Class and utility
	 */
	public static class Image {
		String imageName;
		String imageType;
		Date imageDate;
		String imageCity;
		static DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		public String getCity() {
			return imageCity;
		}

		public Date getDate() {
			return imageDate;
		}

		public Image(String imgName, String emgExt, Date imgDate, String imgCity) {
			this.imageName = imgName;
			this.imageType = emgExt;
			this.imageDate = imgDate;
			this.imageCity = imgCity;
		}

		public static Image parsePhoto(String s) throws ParseException {
			String[] imageAttributes = s.split(",");
			String imageName = imageAttributes[0].trim();
			String imageCity = imageAttributes[1].trim();
			Date imageDate = null;
			try {
				imageDate = dateFormater.parse(imageAttributes[2].trim());
			} catch (ParseException e) {
				throw e;
			}
			String[] cityAndFileType = imageName.split("\\.");
			return new Image(cityAndFileType[0], cityAndFileType[1], imageDate, imageCity);
		}
	}

}
