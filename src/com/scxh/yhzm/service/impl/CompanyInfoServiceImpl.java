package com.scxh.yhzm.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConversionException;

import com.scxh.yhzm.dao.mapper.CompanyInfoMapper;
import com.scxh.yhzm.po.FirmInfo;
import com.scxh.yhzm.service.CompanyInfoService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.SaveFileUtil;

public class CompanyInfoServiceImpl  extends BaseServiceImpl<FirmInfo> implements CompanyInfoService<FirmInfo>{
	private CompanyInfoMapper<FirmInfo> companyInfoMapper;
	
	public void setCompanyInfoMapper(CompanyInfoMapper<FirmInfo> companyInfoMapper) {
		this.companyInfoMapper = companyInfoMapper;
	}
	
	@Override
	public Integer addEntry(Map<String,Object> map) {
		Integer backNum = null;
			try {
				String fileName = SaveFileUtil.saveFile(map,SAVEDIRECTORY);
				String state = (String) map.get("state");
				map.put("imagesAddr",fileName);
				FirmInfo firmInfo = CommonUtils.toBean(map,FirmInfo.class);
				
				if("1".equals(state)){
					companyInfoMapper.updateEntry(new FirmInfo(false));
				}
				backNum = companyInfoMapper.saveEntry(firmInfo);
			} catch (Exception e) {
				if(e instanceof ConversionException){
					throw new RuntimeException("不能转换的日期格式");
				}else{
					throw new RuntimeException(e);
				}
			}
		
		return backNum;
	}
	
	
	
	
	
	@Override
	public List<FirmInfo> getAllEntry2(Map<String, Object> map) {
		List<FirmInfo> firmList= companyInfoMapper.getAllEntry(CommonUtils.toBean(map, FirmInfo.class));
		return firmList;
	}
	
	
	@Override
	public FirmInfo getEntryById(Serializable id){
		
		FirmInfo firm = companyInfoMapper.getEntryById(id);
		spiltImgs(firm);
		return firm;
	}

	public void spiltImgs(FirmInfo firm) {
		String imagesAddr = firm.getImagesAddr();
		String[] imgAddrs = null;
		if(imagesAddr.contains(",")){
			String[] strs = imagesAddr.split(",");
			imgAddrs = new String[strs.length];
			for(int i = 0;i < strs.length;i++){
				imgAddrs[i] = SAVEDIRECTORY + strs[i];
			}
		}else if(null == imagesAddr || imagesAddr.trim().isEmpty()){
			imgAddrs = null;
		}else{
			imgAddrs = new String[]{SAVEDIRECTORY + imagesAddr};
		}
		firm.setImgAddrs(imgAddrs);
	}


	@Override
	public FirmInfo getEntryByCoulmn(FirmInfo frimInfo) {
		FirmInfo firmInfo = companyInfoMapper.getEntryByCoulmn(frimInfo);
		spiltImgs(firmInfo);
		return firmInfo;
	}

	@Override
	public Integer modEntry(Map<String, Object> map) {
		
		String flag = (String)map.get("flag");
		String firmId = (String) map.get("firmId");
		String state = (String)map.get("state");
		FirmInfo oldFirmInfo = companyInfoMapper.getEntryById(firmId);
		
		Integer backNum = null;
		try {
			
				if("1".equals(flag)){
					String fileName = SaveFileUtil.saveFile(map,SAVEDIRECTORY);
					map.put("imagesAddr", fileName + "," + oldFirmInfo.getImagesAddr());
					map.remove(flag);
				}
				
				if("1".equals(state)){
					companyInfoMapper.updateEntry(new FirmInfo(false));
				}
				
			   FirmInfo newFirmInfo = CommonUtils.toBean(map,FirmInfo.class);
			   backNum = companyInfoMapper.updateEntry(newFirmInfo);
				
			} catch (Exception e) {
				if(e instanceof ConversionException){
					throw new RuntimeException("不能转换的日期格式");
				}else{
					throw new RuntimeException(e);
				}
			}
		
		return backNum;
	}


	@Override
	public Integer deleteEntry(Map<String,Object> map) {
		String basePath = (String)map.get("basePath");
		String firmId = (String)map.get("firmId");
		String[] firmIds = splitImgAddrStr(firmId);
		
		List<String> imgAddrs = companyInfoMapper.selectImgList(firmIds);
		for(String oldImgAddr : imgAddrs){
			if(null != oldImgAddr && oldImgAddr.contains(",")){
				String[] strs = oldImgAddr.split(",");
				for(String str : strs){
					delFile(basePath, str);
				}
				
			}else{
				delFile(basePath, oldImgAddr);
			}
		}
		
		return companyInfoMapper.deleteEntry(firmIds);
	}

	public String[] splitImgAddrStr(String firmId) {
		String[] firmIds = null;
		if(firmId.contains(",")){
			firmIds = firmId.split(",");
		}else{
			firmIds = new String[]{firmId};
		}
		return firmIds;
	}

	
	
	@Override
	public Integer delFrimImg(Map<String, Object> map) {
		//删除文件
		String[] imgAddrs = (String[]) map.get("imgAddrs");
		String firmId = (String)map.get("firmId");
		
		FirmInfo firmInfo = companyInfoMapper.getEntryById(firmId);
		String oldImagesAddr = firmInfo.getImagesAddr();
		
		String basePath = (String)map.get("basePath");
		String[] strs = splitImgAddrStr(oldImagesAddr);
		
		StringBuffer temp = new StringBuffer();
		
		for(int i = 0;i< strs.length;i++){
			for(int j = 0;j < imgAddrs.length;j++){
				if(strs[i].equals(imgAddrs[j])){
					break;
				}
				if(j == imgAddrs.length -1){
					temp.append(strs[i] + ",");
				}
			}
		}
		
		if(temp.length() > 0){
			temp.deleteCharAt(temp.length() - 1);
		}
		for(String str : imgAddrs){
			delFile(basePath, str);
		}
		//修改数据的图片项的数据
		firmInfo = new FirmInfo();
		firmInfo.setFirmId(firmId);
		firmInfo.setImagesAddr(temp.toString());
		
		return companyInfoMapper.updateEntry(firmInfo);
	}

	private void delFile(String basePath, String str) {
		File file;
		file = new File(basePath + SAVEDIRECTORY + str);
		if(file.exists()&&file.isFile()){
			file.delete();
		}
	}
}