package com.springnews.workarea.controler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springnews.workarea.config.ApplicationConstants;
import com.springnews.workarea.model.BaseModel;
import com.springnews.workarea.model.InputParam;
import com.springnews.workarea.model.News;


@Controller
@RequestMapping("/first")
public class NewsController {
	private static final Logger logger = Logger.getGlobal();
	private final BaseModel basemodal;
	
	@Autowired
	public NewsController(BaseModel basemodal) {
		this.basemodal=basemodal;
	}
	
	@PostConstruct
    private void postConstruct() {
		System.out.println("Hello World application works!");
    }
	
	@SuppressWarnings("static-access")
	@GetMapping("/newscontent")
	public String newsPage(Model model, HttpServletRequest request) {
		int count;
		if(request.getParameter("count")==null) count = 10;
		else count = Integer.parseInt(request.getParameter("count"));
		model.addAttribute("news", basemodal.getNews(count));
		HttpSession session = request.getSession();
		if(session.getAttribute("Login")!=null) {
			model.addAttribute("state", "EDIT");
		}
		return "/newscontent";
	}
	
	@GetMapping("/addnews")
	public String addNews(Model model) {
		model.addAttribute("news", new News());
		return "/addnews";
	}
	
	@GetMapping("/{id}")
    public String editNews(@PathVariable("id") int id, Model model) throws UnsupportedEncodingException {		
		model.addAttribute("news", BaseModel.getIdNews(id));
        return "/editNews";
    }
	
	@GetMapping("/delete{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        BaseModel.deleteNews(id);
        return "redirect:/first/newscontent";
    }
	
	@RequestMapping(value = "/loadingForm", method = RequestMethod.POST)
	public String create(@ModelAttribute("news") @Valid News news,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model) {	
		if(bindingResult.hasErrors()) {
			return "/addnews";
		}
		else {
			String name = "";
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					name = file.getOriginalFilename();
					File dir = new File(ApplicationConstants.fileDirectory + File.separator + "images");
	 
					if (!dir.exists()) {
						dir.mkdirs();
					}
	 
					File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
	 
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
					stream.write(bytes);
					stream.flush();
					stream.close();
	 
					logger.info("uploaded: " + uploadedFile.getAbsolutePath()); //an example of creating a log to output the status to the console
	 
					System.out.println("You successfully uploaded file файл=" + name);
	 
				} catch (Exception e) {
					System.out.println("You failed to upload файл " + name + " => " + e.getMessage());
				}
			} else {
				System.out.println("You failed to upload файл " + name + " because the file was empty.");
			}
			news.setPath(name);
			news.setDate_add(new Date());
			BaseModel.addNews(news);
			return "redirect:/first/newscontent";
		}
	}
	
	@RequestMapping(value = "/loadingEditForm", method = RequestMethod.POST)
	public String apdate(@ModelAttribute("news") News news, Model model) throws IOException {
		BaseModel.updateNews(news.getHeading(), news.getNews());
		return "redirect:/first/newscontent";
	}
		
	@GetMapping("/exit")
	public String exit( HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("input", new InputParam());
		return "/registration";
	}
	
	@RequestMapping(value = "/authorization", method = RequestMethod.POST)
	public String authorization(@ModelAttribute("input") @Valid InputParam input,
            BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws IOException {	
		response.setContentType("text/html;charset=UTF-8");
		if(bindingResult.hasErrors()) {
			return "/registration";
		}
		else if(BaseModel.authorization(input)) {
			response.getWriter().print("Login or password does not match, please re-enter.");
			return "/registration";
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("Login", input.getLogin());
			return "redirect:/";
		}
	}
	
}
