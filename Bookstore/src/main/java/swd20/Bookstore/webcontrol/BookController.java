package swd20.Bookstore.webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody // Laitoin responsebodyn, koska en vielä tässä vaiheessa hae kirjan attribuutteja
public class BookController {
	
	@RequestMapping("/index")
	public String bookControl() {
		
		return "Welcome";
	}

}
