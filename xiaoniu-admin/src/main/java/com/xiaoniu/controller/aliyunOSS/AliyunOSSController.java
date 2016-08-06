package com.xiaoniu.controller.aliyunOSS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

@Controller
@RequestMapping("/secure/aliyunOss")
public class AliyunOSSController {
	
	private Logger log = Logger.getLogger(AliyunOSSController.class);
	
	/**
     * 阿里云ACCESS_ID
     */
    private  String ACCESS_ID="hSiKp8YurMRk3LeY";
    /**
     * 阿里云ACCESS_KEY
     */
    private  String ACCESS_KEY="FRP5rKt4TPKQmC8mQOv7NUzSAAX9oG";
    /**
     * 阿里云OSS_ENDPOINT  
     */
    private  String OSS_ENDPOINT="http://oss-cn-shenzhen.aliyuncs.com";
     
    /**
     * 阿里云BUCKET_NAME  OSS
     */
    private String BUCKET_NAME="xiaoniu-66";
	
	/**
     * 阿里云OSS 绑定的域名
     */
	private String OSS_DOMAIN="http://xiaoniu-66.oss-cn-shenzhen.aliyuncs.com";
	
   private void uploadFile(OSSClient client, String bucketName, String filename,String contentType,
		   long fileLength,InputStream input) throws OSSException, ClientException, FileNotFoundException{
	   
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(fileLength);
        objectMeta.setContentType(contentType);
         
        PutObjectResult result = client.putObject(bucketName, filename, input, objectMeta);
        result.getETag();
    }
   
   	@RequestMapping("upload_json")
	@ResponseBody
	public void uploadJson(HttpServletRequest request,HttpServletResponse response){
		
		String saveUrl = "";
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		OSSClient client = null;
		try {
			client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
			writer = response.getWriter();
			if(!ServletFileUpload.isMultipartContent(request)){
				writer.write(getError("请选择文件。"));
				return;
			}

			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			
			if(!extMap.containsKey(dirName)){
				writer.write(getError("目录名不正确。"));
				return;
			}
			//创建文件夹
			saveUrl += dirName + "/";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			saveUrl += ymd + "/";
		
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
            List<MultipartFile> fileList = multipartRequest.getFiles("imgFile");  
            Iterator<MultipartFile> itr = fileList.iterator();
			while (itr.hasNext()) {
				MultipartFile item = itr.next();item.getContentType();
				String fileName = item.getOriginalFilename();
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					writer.write(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return ;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				saveUrl += df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				
				uploadFile(client,BUCKET_NAME,saveUrl,item.getContentType(),item.getSize(),item.getInputStream());
					
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", OSS_DOMAIN + "/" +saveUrl);
				writer.write(obj.toJSONString());
				writer.flush();
			}
		} catch (Exception e1) {
			log.error(e1);
			return ;
		}finally {
			if(client != null){
				client.shutdown();
			}
			if(writer != null){
				writer.close();
			}
		}
		return ;
	}
   	
   	
   	@RequestMapping("file_manager_json")
	@ResponseBody
	public Map<String,Object> fileManagerJson(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
		
		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
				log.error("Invalid Directory name");
				resultMap.put("msg", "Invalid Directory name");
				return resultMap;
			}
		}
		
		String path = request.getParameter("path");
		if(path == null || path.equals("/") || path.equals("")){
			path = dirName;
		}
		while(path.lastIndexOf("/") == path.length() - 1){
			path = path.substring(0,path.length() - 1);
		}
		
		OSSClient client = null;
		try{
			client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
			ListObjectsRequest listObjectRequest = new ListObjectsRequest(BUCKET_NAME);
			listObjectRequest.withDelimiter("/");
			listObjectRequest.withMaxKeys(100);
			listObjectRequest.withPrefix(path+"/");
			ObjectListing objectListing = client.listObjects(listObjectRequest);
			List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
			for (OSSObjectSummary s : sums) {
				Map<String,Object> map = new HashMap<String,Object>();
				String fileName = s.getKey();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				map.put("is_dir", false);
				map.put("has_file", false);
				map.put("filesize", s.getSize());
				map.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
				map.put("filetype", fileExt);
				map.put("filename", OSS_DOMAIN + "/" +fileName);
				map.put("datetime", df.format(s.getLastModified()));
				fileList.add(map);
			}
			for (String commonPrefix : objectListing.getCommonPrefixes()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("is_dir", true);
				map.put("has_file", true);
				map.put("filesize", 0L);
				map.put("is_photo", false);
				map.put("filetype", "");
				map.put("filename", commonPrefix.substring(path.length()));
				map.put("datetime", df.format(new Date()));
				fileList.add(map);
			}
			int lastPos = path.lastIndexOf('/');
			String moveUpDirPath = path;
			if(lastPos > 0){
				moveUpDirPath = path.substring(0,lastPos);
			}
			resultMap.put("moveup_dir_path", moveUpDirPath);
			resultMap.put("current_dir_path", path);
			resultMap.put("current_url", "");
			resultMap.put("total_count", fileList.size());
			resultMap.put("file_list", fileList);
		}catch(Exception e){
			log.error(e);
			resultMap.put("msg", e.getMessage());
		}finally{
			if(client != null){
				client.shutdown();
			}
		}
		return resultMap;
	}
	
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
}
