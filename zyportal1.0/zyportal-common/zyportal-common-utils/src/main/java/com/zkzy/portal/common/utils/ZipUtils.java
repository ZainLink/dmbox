package com.zkzy.portal.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZipUtils {
	private ZipUtils() {
	};

	/**
	 * APDPlat中的重要打包机制 将jar文件中的某个文件夹里面的内容复制到某个文件夹
	 * 
	 * @param jar
	 *            包含静态资源的jar包
	 * @param subDir
	 *            jar中包含待复制静态资源的文件夹名称
	 * @param loc
	 *            静态资源复制到的目标文件夹
	 * @param force
	 *            目标静态资源存在的时候是否强制覆盖
	 */
	public static void unZip(String jar, String subDir, String loc,
			boolean force) {
		try {
			File base = new File(loc);
			if (!base.exists()) {
				base.mkdirs();
			}

			ZipFile zip = new ZipFile(new File(jar));
			Enumeration<? extends ZipEntry> entrys = zip.entries();
			while (entrys.hasMoreElements()) {
				ZipEntry entry = entrys.nextElement();
				String name = entry.getName();
				if (!name.startsWith(subDir)) {
					continue;
				}
				// 去掉subDir
				name = name.replace(subDir, "").trim();
				if (name.length() < 2) {
					System.out.println(name + " 长度 < 2");
					continue;
				}
				if (entry.isDirectory()) {
					File dir = new File(base, name);
					if (!dir.exists()) {
						dir.mkdirs();
						System.out.println("创建目录");
					} else {
						System.out.println("目录已经存在");
					}
					System.out.println(name + " 是目录");
				} else {
					File file = new File(base, name);
					if (file.exists() && force) {
						file.delete();
					}
					if (!file.exists()) {
						InputStream in = zip.getInputStream(entry);
						copyFile(in, file);
						System.out.println("创建文件");
					} else {
						System.out.println("文件已经存在");
					}
					System.out.println(name + " 不是目录");
				}
			}
		} catch (ZipException ex) {
			System.out.println("文件解压失败");
		} catch (IOException ex) {
			System.out.println("文件操作失败");
		}
	}

	/**
	 * 创建ZIP文件
	 * 
	 * @param sourcePath
	 *            文件或文件夹路径
	 * @param zipPath
	 *            生成的zip文件存在路径（包括文件名）
	 */
	public static void createZip(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到，创建ZIP文件失败");
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				System.out.println("创建ZIP文件失败");
			}

		}
	}

	private static void writeZip(File file, String parentPath,
			ZipOutputStream zos) {
		if (file.exists()) {
			if (file.isDirectory()) {// 处理文件夹
				parentPath += file.getName() + File.separator;
				File[] files = file.listFiles();
				for (File f : files) {
					writeZip(f, parentPath, zos);
				}
			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}

				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				} finally {
					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						System.out.println("创建ZIP文件失败");
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		ZipUtils.createZip("D:\\SysDevDaily.xls", "D:\\backup1.zip");
		// ZipUtils.createZip("D:\\workspaces\\netbeans\\APDPlat\\APDPlat_Web\\target\\APDPlat_Web-2.2\\platform\\index.jsp",
		// "D:\\workspaces\\netbeans\\APDPlat\\APDPlat_Web\\target\\APDPlat_Web-2.2\\platform\\index.zip");

	}

	public static void copyFile(InputStream in, File outFile) {
		OutputStream out = null;
		try {
			byte[] data = readAll(in);
			out = new FileOutputStream(outFile);
			out.write(data, 0, data.length);
			out.close();
		} catch (Exception ex) {
			System.out.println("文件操作失败");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("文件操作失败");
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				System.out.println("文件操作失败");
			}
		}
	}

	public static byte[] readAll(File file) {
		try {
			return readAll(new FileInputStream(file));
		} catch (Exception ex) {
			System.out.println("读取文件失败");
		}
		return null;
	}

	public static byte[] readAll(InputStream in) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			for (int n; (n = in.read(buffer)) > 0;) {
				out.write(buffer, 0, n);
			}
		} catch (IOException ex) {
			System.out.println("读取文件失败");
		}
		return out.toByteArray();
	}

	/**
	 * 将文件写入到zip文件中
	 * 
	 * @param inputFile
	 * @param outputstream
	 * @throws Exception
	 */
	public static void zipFileWrite(File inputFile, ZipOutputStream outputstream)
			throws IOException, ServletException {
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					FileInputStream inStream = new FileInputStream(inputFile);
					BufferedInputStream bInStream = new BufferedInputStream(
							inStream);
					ZipEntry entry = new ZipEntry(inputFile.getName());
					outputstream.putNextEntry(entry);

					final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
					long streamTotal = 0; // 接受流的容量
					int streamNum = 0; // 流需要分开的数量
					int leaveByte = 0; // 文件剩下的字符数
					byte[] inOutbyte; // byte数组接受文件的数据

					streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
					streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
					leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量

					if (streamNum > 0) {
						for (int j = 0; j < streamNum; ++j) {
							inOutbyte = new byte[MAX_BYTE];
							// 读入流,保存在byte数组
							bInStream.read(inOutbyte, 0, MAX_BYTE);
							outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
						}
					}
					// 写出剩下的流数据
					inOutbyte = new byte[leaveByte];
					bInStream.read(inOutbyte, 0, leaveByte);
					outputstream.write(inOutbyte);
					outputstream.closeEntry(); // Closes the current ZIP entry
												// and positions the stream for
												// writing the next entry
					bInStream.close(); // 关闭
					inStream.close();
				}
			} else {
				throw new ServletException("文件不存在！");
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 压缩文件列表中的文件
	 * 
	 * @param files
	 * @param outputStream
	 * @throws IOException
	 */
	public static void zipFileList(List files, ZipOutputStream outputStream)
			throws IOException, ServletException {
		try {
			int size = files.size();
			// 压缩列表中的文件
			for (int i = 0; i < size; i++) {
				File file = (File) files.get(i);
				zipFileWrite(file, outputStream);
			}
		} catch (IOException e) {
			throw e;
		}
	}
	

	/**
	 * 下载打包的文件
	 * 
	 * @param file
	 * @param response
	 */
	public static void downloadZip(File file, HttpServletRequest request,HttpServletResponse response) {
		ServletOutputStream out = null;
		FileInputStream in = null;
		PrintWriter pw = null;
		try {
			if (!file.exists()) {// 文件不存在
				response.setContentType("text/html;charset=utf-8");
				pw = response.getWriter();
				pw.write("<script type=\"text/javascript\">alert('文件不存在!');window.close()</script>");
			} else {
				response.setContentType("application/x-msdownload;charset=utf-8");
				response.setHeader("Content-Disposition","attachment;filename=" + file.getName());
				out = response.getOutputStream();
				in = new FileInputStream(file);
				if (in != null) {
					// 读取文件
					int fileLen = 0;
					byte[] bt = new byte[1024];
					while ((fileLen = in.read(bt, 0, 1024)) != -1) {
						// 写入文件
						out.write(bt, 0, fileLen);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (pw != null) {
					pw.close();
				}
				if(file!=null&&file.exists()){//下载完成后删除压缩文件
					file.delete();
					System.out.println("成功下载,删除文件:"+file.getName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
