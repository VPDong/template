package com.temp.server.model.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class RespUtil {
	public static boolean isJsonRequest(HttpServletRequest request) {
		if (request == null) return false;
		return (request.getContentType().toLowerCase().contains("application/json"));
	}

	public static boolean saveMultiFile(MultipartFile file, File dest) {
		if (file == null || file.isEmpty() || dest == null) return false;
		if (dest.exists()) return true;
		try {
			if (!dest.getParentFile().exists()) dest.getParentFile().mkdirs();
			file.transferTo(dest); //保存文件
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void setRedirect(HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setError(HttpServletResponse response, int code, String msg) {
		try {
			response.sendError(code, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setString(HttpServletResponse response, String str) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try (PrintWriter out = response.getWriter()) {
			out.append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setFile(HttpServletResponse response, File file) {
		if (file == null || !file.exists() || file.isDirectory()) {
			setError(response, 404, "the file can not found");
			return;
		}
		try (OutputStream os = response.getOutputStream();
		     BufferedInputStream is = new BufferedInputStream(new FileInputStream(file))) {
			response.setHeader("Content-Disposition", "attachment;filename=" +
					new String(file.getName().getBytes(), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			String fileName = file.getName().toLowerCase();
			if (fileName.endsWith(".jpg")) {
				response.setContentType("image/jpeg");
				return;
			} else if (fileName.endsWith(".png")) {
				response.setContentType("image/png");
				return;
			} else if (fileName.endsWith(".svg")) {
				response.setContentType("text/xml");
				return;
			}

			byte[] buff = new byte[1024];
			int i = is.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = is.read(buff);
			}
		} catch (Exception e) {
			setError(response, 404, "the file can not found");
		}
	}
}
