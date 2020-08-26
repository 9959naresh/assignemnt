package com.assign.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Filesassigment {

	public void assig() {

		String path = "E:\\New folder";
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File arr[] = dir.listFiles();

			List<response> res = new ArrayList<response>();
			for (File file : arr) {
				List<response> date = null;

				File[] listFiles = file.listFiles();

				if (listFiles.length >= 0) {
					date = recursive(listFiles);
				}
				String format = null;
				String d = null;
				long lastModified = file.lastModified();
				SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
				String strDate = formatter.format(lastModified);
				String absolutePath= file.getAbsolutePath();
				int compareTo = 0;
				if (date != null) {
					List<response> collect = date.stream().sorted((x,y)->x.getDate().compareTo(y.getDate())).collect(Collectors.toList());
					response response = collect.get(0);
					compareTo = response.getDate().compareTo(strDate);
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(lastModified);
					format = new SimpleDateFormat("MMM").format(cal.getTime());
					d = new SimpleDateFormat("ddMMyyyy").format(cal.getTime());
					 absolutePath = response.getPath();

				}

				if (compareTo == 0) {
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(lastModified);
					format = new SimpleDateFormat("MMM").format(cal.getTime());
					d = new SimpleDateFormat("ddMMyyyy").format(cal.getTime());
					
				}

				response r = new response();
				r.setDate(d);
				r.setMonth(format);
				r.setPath(absolutePath);
				res.add(r);

			}
			res.stream().forEach(rs -> {
				System.err.println(rs.getMonth());
				System.err.println(rs.getDate());
				System.err.println(rs.getPath());
			});

		}
	}

	public List<response> recursive(File[] listFiles) {
		List<response> date = null;
		if (listFiles.length >= 0) {
			date = new ArrayList<>();

			for (File file2 : listFiles) {
				File[] listFiles2 = file2.listFiles();
				if (listFiles2 != null && listFiles2.length > 0) {

					recursive(listFiles2);
				}
				long lastModified = file2.lastModified();
				response r = new response();
				SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
				String strDate = formatter.format(lastModified);

				r.setDate(strDate);
				r.setPath(file2.getAbsolutePath());
				date.add(r);
			}
		}
		return date;
	}
}
