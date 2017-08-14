package webpage.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webpage.views.SongsView;

@Controller
public class SongsStreamingController {

	@Autowired
	private SongsView songsView;
	@Autowired
	private NotificationService notifyService;

	@RequestMapping(value = "/song/stream/{cod}", method = RequestMethod.GET)
	public ResponseEntity<?> steaming(@PathVariable("cod") long cod, Model model) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("ContentType", "audio/mpeg");

		try {
			InputStream is = new FileInputStream("C:\\Users\\mamup\\Music\\Skeleton\\(02) [brennan heart] memento.mp3");

			return new ResponseEntity<>(is, headers, HttpStatus.OK);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/song/stream2/{cod}", method = RequestMethod.GET)
	public void doPost(@PathVariable("cod") long cod, HttpServletResponse response) {
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {
			stream = response.getOutputStream();

			Authentication user = SecurityContextHolder.getContext().getAuthentication();
			String username = null;
			if (user != null)
				username = user.getName();
			File mp3 = songsView.findFile(cod, username);
			// set response headers
			response.setContentType("audio/mpeg");
			response.addHeader("Content-Disposition", "attachment; filename="+mp3.getName());
			response.setContentLength((int) mp3.length());

			FileInputStream input = new FileInputStream(mp3);
			buf = new BufferedInputStream(input);
			int readBytes = 0;

			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			if (stream != null)
				stream.close();
			if (buf != null)
				buf.close();
		} catch (FileNotFoundException e) {
			notifyService.addErrorMessage("Cannot find song #" + cod + " locally");
		} catch (IOException e) {
//			notifyService.addErrorMessage("Internal Error while processing the request");
		}
	}
}
