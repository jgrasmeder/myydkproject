/**
 * 
 */
package com.ydk.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.utility.YdkRandomString;
import com.ydk.book.persistence.entity.Author;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Publisher;
import com.ydk.book.persistence.entity.Translator;
import com.ydk.epub.EpubBook;
import com.ydk.epub.Image;

/**
 * @author y140zhan
 *
 */
public class FileManager {
	
	private final static Logger logger = Logger.getLogger(FileManager.class.getName());
	public static final String FILE_KEY_INTERVIEW="interview";
	public static final String FILE_KEY_BOOKREVIEW="bookReview";
	public static final String FILE_KEY_BOOKNEWS="bookNews";
	public static final String FILE_KEY_EVENT="event";
	public static final String FILE_KEY_BOOK="book";
	public static final String FILE_KEY_YDKACCOUNT="ydkAccount";
	public static final String FILE_KEY_READER="reader";
//	public static final String FILE_KEY_INTERVIEW="interview";
//	public static final String FILE_KEY_INTERVIEW="interview";
//	public static final String FILE_KEY_INTERVIEW="interview";
//	public static final String FILE_KEY_INTERVIEW="interview";
//	public static final String FILE_KEY_INTERVIEW="interview";
//	public static final String FILE_KEY_INTERVIEW="interview";
	
	protected static String basePath;
	protected static Map<String, String>paths = null;
	
	
	/**
	 * @return the basePath
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * @param basePath the basePath to set
	 */
	public  void setBasePath(String basePath) {
		FileManager.basePath = basePath;
	}

	public void setPaths(Map<String, String> paths)
	{
		this.paths = paths;
	}
	
	public static String getPath(String key)
	{
		if (paths != null)
		{
			return paths.get(key);
		}
		return null;
	}
	
	public static String generateFileName(String key)
	{
		if (paths != null)
		{
			return paths.get(key) 
				+ new Long((new Date()).getTime()) + "_" 
				+ YdkRandomString.getRandomString(4) +"_";
		}
		return null;
	}
	
	public static String checkFileName(String fileName)
	{
		if (paths == null) 
		{
			logger.error("Could not get file path");
			return "Could not get file path";
		}
		if (paths != null)
		{
			
			File test = new File(basePath + fileName);
			if (!test.isFile())
			{
				logger.error("It is Not a file specified by: " + fileName);
				return "It is Not a file specified by : " + fileName;
			}
			if (!test.exists())
			{
				logger.error("Could not get file :" + fileName);
				return "Could not get file: " + fileName;
			}
		}
		return null;
	}
	
	public static void saveAsFile(String name, byte[] content)
	{
		if (name != null)
		{
			//ToDo move this to background task;
			File tempFile = new File(basePath + name);
			try {
				FileCopyUtils.copy(content, tempFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Save file " + name + "Error!");
				e.printStackTrace();
			}
			
		}
	}
	
	public static byte[] loadFile(String name)
	{
		if (name != null)
		{
			//ToDo move this to background task;
			File tempFile = new File(basePath +name);
			try {
				return FileCopyUtils.copyToByteArray(tempFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Load file " + name + "Error!");
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public static File getFileByName(String fileName)
	{
		if (fileName != null)
		{
			//ToDo move this to background task;
			File tempFile = new File(basePath +fileName);
			if (!tempFile.isFile())
			{
				logger.error("It is Not a file specified by: " + fileName);
				return null;
			}
			if (!tempFile.exists())
			{
				logger.error("Could not get file :" + fileName);
				return null;
			}
			return tempFile;
			
		}
		return null;
	}
	
	public static UploadedFile loadUploadedFile(String name)
	{
		if (name != null)
		{
			//ToDo move this to background task;
			File tempFile = new File(basePath +name);
			UploadedFile result = new UploadedFile();
			result.setFileName(tempFile.getName());
			result.setFileSize(tempFile.length());
			
			try {
				result.setFileContent(FileCopyUtils.copyToByteArray(tempFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Load file " + name + "Error!");
				e.printStackTrace();
			}
//			result.setFileType(tempFile.)
			return result;
		}
		return null;
	}
	
	
	public static Book parseEpubBook(String fileName) throws Exception
	{
		if (fileName != null)
		{
			//ToDo move this to background task;
			EpubBook epubBook = new EpubBook(basePath +fileName);
			epubBook.parseMetadata();
			
			Book book = new Book();
			book.setBookContent(fileName);
			if (epubBook.getAuthors() != null)
			{
				if (epubBook.getAuthors().size() > 4)
				{
					for (int i =4; i < epubBook.getAuthors().size(); i ++)
					{
						book.getAuthorOthers().add(epubBook.getAuthors().get(i));
					}
				}
				if (epubBook.getAuthors().size() > 3)
				{
					book.setAuthor3(new Author(epubBook.getAuthors().get(3)));
				}
				if (epubBook.getAuthors().size() > 2)
				{
					book.setAuthor2(new Author(epubBook.getAuthors().get(2)));
				}
				if (epubBook.getAuthors().size() > 1)
				{
					book.setAuthor1(new Author(epubBook.getAuthors().get(1)));
				}
				if (epubBook.getAuthors().size() > 0)
				{	
					book.setAuthor(new Author(epubBook.getAuthors().get(0)));
				}
				
			}
			
			if (epubBook.getTranslators() != null)
			{
				
				if (epubBook.getTranslators().size() > 4)
				{
					for (int i =4; i < epubBook.getTranslators().size(); i ++)
					{
						book.getTranslatorOthers().add(epubBook.getTranslators().get(i));
					}
				}
				if (epubBook.getTranslators().size() > 3)
				{
					book.setTranslator3(new Translator(epubBook.getTranslators().get(3)));
				}
				if (epubBook.getTranslators().size() > 2)
				{
					book.setTranslator2(new Translator(epubBook.getTranslators().get(2)));
				}
				if (epubBook.getTranslators().size() > 1)
				{
					book.setTranslator1(new Translator(epubBook.getTranslators().get(1)));
				}
				if (epubBook.getTranslators().size() > 0)
				{	
					book.setTranslator(new Translator(epubBook.getTranslators().get(0)));
				}
			}
			
			book.setTitle(epubBook.getTitle());
			book.setYdkBookId(epubBook.getBookUUID());
			//epubBook.getFormat();
			book.setSummary(epubBook.getIntroduction());
			
			//ISBN is Integer.
			book.setIsbn((epubBook.getIsbn()));
//			epubBook.getLayout();
			book.setPages(new Integer(epubBook.getPages()));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss-SSS aa");
			dateFormat.setLenient(true);
			try
			{
				book.setPublishDate(dateFormat.parse(epubBook.getPublishDate()));
			}
			catch(Exception e)
			{
				book.setPublishDate(null);
			}
			book.setPublisher(new Publisher(epubBook.getPublisher()));
			book.setPaperPrice(Double.parseDouble(epubBook.getPaperCopyPrice()));
			
			//Images
			Image img = epubBook.getThumbnailSmall();
			if (img != null)
			{
				saveAsFile(fileName + "is", img.data);
				book.setImageSmall(fileName + "is");
			}
			
			img = epubBook.getThumbnailBig();
			if (img != null)
			{
				saveAsFile(fileName + "ib", img.data);
				book.setImageLarge(fileName + "ib");
			}
			
			img = epubBook.getThumbnail3DSmall();
			if (img != null)
			{
				saveAsFile(fileName + "3ds", img.data);
				book.setImage3DSmall(fileName + "3ds");
			}
			
			img = epubBook.getThumbnail3DBig();
			if (img != null)
			{
				saveAsFile(fileName + "3db", img.data);
				book.setImage3DSmall(fileName + "3ds");
			}
			
//			result.setFileType(tempFile.)
			return book;
		}
		return null;
	}


	

}
