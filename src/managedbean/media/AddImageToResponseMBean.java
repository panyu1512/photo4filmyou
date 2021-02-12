package managedbean.media;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.Part;

import auth.UserLoggableInterface;
import ejb.media.MediaFacadeRemote;

@Named("addImageToResponseBean")
@SessionScoped
public class AddImageToResponseMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private MediaFacadeRemote<UserLoggableInterface> addImageToResponse;
	private Part uploadedFile;
	private int responseId;

	public void addImageToResponse(int responseId) {
		try {
			InputStream is = uploadedFile.getInputStream();
			BufferedImage bImage = ImageIO.read(is);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", bos);
			byte[] data = bos.toByteArray();
			addImageToResponse.addImageToResponse(responseId, data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}
