package cn.edu.bjtu.weibo.service.Impl;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import cn.edu.bjtu.weibo.dao.WeiboDAO;
import cn.edu.bjtu.weibo.model.Picture;
import cn.edu.bjtu.weibo.model.Weibo;
import cn.edu.bjtu.weibo.service.WeiboPictureServie;

public class WeiboPictureServieImpl implements WeiboPictureServie{
	
	@Autowired
	private WeiboDAO weiboDao;
	
	@Override
	public List<Picture> getWeiboPictureList(String weiboId) {
		// TODO Auto-generated method stub
		//WeiboDAO weiboDao = new WeiboDAO();
		List<Picture> pic = new LinkedList<Picture>();
		for(int i = 0;i < weiboDao.getWeiboPicurlOr(weiboId).size();i++) {
			pic.get(i).setPicurl(weiboDao.getWeiboPicurlOr(weiboId).get(i));
		}
		return pic;
	}

	@Override
	public boolean uploadWeiboPicture(String weiboId, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		boolean fig;//判断是否上传成功
	    //WeiboDAO weiboDao = new WeiboDAO();
		// 创建保存的文件的指定路径
		String path = "C:\\Users\\liu\\Desktop\\map_12_21_1\\img";
		// 获取该文件的文件名
		String fileName = multipartFile.getOriginalFilename();
		File targetFile = new File(path, fileName);
		String url=path+"\\"+fileName;
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			multipartFile.transferTo(targetFile);
			//存储url
			fig = weiboDao.insertWeiboPicture(weiboId,url); 
			return fig;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}