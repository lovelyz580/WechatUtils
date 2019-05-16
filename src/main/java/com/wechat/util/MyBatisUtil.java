package com.wechat.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private MyBatisUtil() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			File file = Resources.getResourceAsFile(resource);
			String _split = file.getParent() + "\\";
			_split = _split.replace("\\", "/");
			File file1 = new File(file.getParent());
			List<String> list = FileUtil.getFile(file1);
			for (int i = 0; i < list.size(); i++) {
				String _str = list.get(i);
				_str = _str.replace("\\", "/");
				_str = _str.replace(_split, "");
				_str = String.format("<mapper resource='%s' />", _str);
				list.set(i, _str);
			}
			List<String> cList = FileUtil.getFileContext(file.getPath());
			List<String> nList = new ArrayList<String>();
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).contains("<mappers>")) {
					nList.add("<mappers>");
					nList.addAll(list);
					nList.add("</mappers>");
					nList.add("</configuration>");
					break;
				} else {
					nList.add(cList.get(i));
				}
			}
			FileUtil.setFileContext(file.getPath(), nList);
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MyBatisUtil getInstance() {
		return NestedClass.instance;
	}

	private static class NestedClass {
		private static MyBatisUtil instance = new MyBatisUtil();
	}

	private SqlSessionFactory sqlSessionFactory = null;

	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}

	public SqlSession getSqlSession(boolean isAutoCommit) {
		return sqlSessionFactory.openSession(isAutoCommit);
	}
	
}
