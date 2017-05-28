package com.sudha.domains;


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
	
	public String solution(String s) {
		String lines[] = s.split("\\n");
		List<Photo> list = new ArrayList<Photo>();
		for(int i = 0; i < lines.length; i++) {
			list.add(Photo.parsePhoto(lines[i]));
		}
		Map<String, List<Photo>> cityMap = list
		        .stream()
		        .sorted(new PhotoDateComparator())
		        .collect(Collectors.groupingBy(Photo::getCity));
		System.out.println(cityMap);
		
		StringBuilder sol = new StringBuilder(); 
		
		for(int i = 0; i < list.size(); i++) {
			Photo p = list.get(i);
			int idx = cityMap.get(p.city).indexOf(p)+1;
			int len = cityMap.get(p.city).size();
			int zeroes = len/10 - idx/10;
			sol.append(p.city);
			for(int j = 0; j < zeroes; j++) {
				sol.append("0");
			}
			sol.append(idx);
			sol.append(".").append(p.ext).append("\n");
		}
		System.out.println(sol.toString());
		return null;
	}
	
	public static class PhotoDateComparator implements Comparator<Photo>{

		@Override
		public int compare(Photo o1, Photo o2) {
			return o1.getDate().compareTo(o2.getDate());
		}
	}
	
	public static class Photo {
		String name;
		String ext;
		Date date;
		String city;
		static DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		
		public String getCity() {
			return city;
		}
		
		public Date getDate() {
			return date;
		}
		
		public Photo(String name, String ext, Date date, String city) {
			this.name = name;
			this.ext = ext;
			this.date = date;
			this.city = city;
		}
		
		public static Photo parsePhoto(String s) {
			String[] words = s.split(",");
			String photoName = words[0].trim();
			String city = words[1].trim();
			Date date = null;
			try {
				date = df.parse(words[2].trim());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] nameSplit = photoName.split("\\.");
			return new Photo(nameSplit[0], nameSplit[1], date, city);
		}
		
		public String toString() {
			return name+"."+ext + " " + city + " " + df.format(date);
					
		}
	}

}
