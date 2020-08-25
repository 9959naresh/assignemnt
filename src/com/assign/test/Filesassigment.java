package com.assign.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filesassigment {

	public void assig() {

		String path = "E:\\";
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File arr[] = dir.listFiles();
			Map<String, Integer> values = new HashMap<String, Integer>();
			for (File file : arr) {
				long lastModified = file.lastModified();
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(lastModified);
				String format = new SimpleDateFormat("MMM").format(cal.getTime());
				if (values.get(format) != null) {
					Integer integer = values.get(format);
					values.put(format, integer+1);
				}else {
					values.put(format, 1);
				}
			}
			System.err.println(values);

		}
	}

}
