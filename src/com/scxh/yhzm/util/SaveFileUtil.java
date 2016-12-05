package com.scxh.yhzm.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SaveFileUtil {
	
	/**
	 * 必须向散列表中推入文件要保存的basePath<br/>
	 * 推入前台input中file的属性值如imageName<br/>
	 * @param paramMap
	 * @param saveDriectory
	 */
	public static String saveFile(Map<String, Object> paramMap,String saveDriectory){
		
		String parentPath = (String) paramMap.get("basePath") + saveDriectory;
		
		String imageName = (String) paramMap.get("imageName");
		
		CommonsMultipartFile mFile = (CommonsMultipartFile) paramMap.get(imageName);
		
		String fileName = mFile.getOriginalFilename();
		File parent = new File(parentPath);
		File[] files = parent.listFiles();
		File newFile = null;
		
		if(files.length > 0){
			for(File f : files){
				if(f.isFile()){
					if(fileName.equals(f.getName())){
						StringBuilder name = new StringBuilder(f.getName());
						String insert = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
						insert = "-" + insert;
						name.insert(name.lastIndexOf("."),insert);
						fileName = name.toString();
						break;
					}
				}
			}
		}
		
		newFile = new File(parent,fileName); 
		
		try {
			mFile.transferTo(newFile);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件保存失败！！");
		} 
	}	
}
