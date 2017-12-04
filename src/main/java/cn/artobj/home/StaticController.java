package cn.artobj.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StaticController {
	@Value("${web.upload-path}")
	private String rootPath;
	
	private final ResourceLoader resourceLoader;  
	  
    @Autowired  
    public StaticController(ResourceLoader resourceLoader) {  
        this.resourceLoader = resourceLoader;  
    }  
    
	@RequestMapping(value="/static/upload/{dir}/{filename:.+}")
	@ResponseBody
	public Object upload(@PathVariable String dir,@PathVariable String filename){
		Resource file=resourceLoader.getResource("file:"+rootPath+"/"+dir+"/"+filename);
        HttpHeaders headers = new HttpHeaders();  
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));  
        headers.add("Pragma", "no-cache");  
        headers.add("Expires", "0");  
		try {  
            return ResponseEntity  
                    .ok()  
                    .headers(headers)  
                    .contentLength(file.contentLength())  
                    .contentType(MediaType.parseMediaType("application/octet-stream"))  
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (Exception e) {  
            return ResponseEntity.notFound().build();  
        }  
	}
	
	@RequestMapping(value="/static/upload/{dir1}/{dir2}/{filename:.+}")
	@ResponseBody
	public Object uploadfile(@PathVariable String dir1,@PathVariable String dir2,@PathVariable String filename){
		try {  
			Resource file=resourceLoader.getResource("file:"+rootPath+"/"+dir1+"/"+dir2+"/"+filename);
	        HttpHeaders headers = new HttpHeaders();  
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
	        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));  
	        headers.add("Pragma", "no-cache");  
	        headers.add("Expires", "0"); 
            return ResponseEntity  
                    .ok()  
                    .headers(headers)  
                    .contentLength(file.contentLength())  
                    .contentType(MediaType.parseMediaType("application/octet-stream"))  
                    .body(new InputStreamResource(file.getInputStream())); 
        } catch (Exception e) {  
            return ResponseEntity.notFound().build();  
        }  
	}
}
