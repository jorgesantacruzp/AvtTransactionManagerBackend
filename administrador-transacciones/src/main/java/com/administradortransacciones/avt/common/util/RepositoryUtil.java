package com.administradortransacciones.avt.common.util;

import com.administradortransacciones.avt.common.RepositoryEnum;

public class RepositoryUtil {

	// MySQL is the default database
	private static String chosenRepository = "MYSQL";

	public static String getChosenRepository() {
		return chosenRepository;
	}

	public static void setChosenRepository(String chosenRepository) {
		RepositoryUtil.chosenRepository = chosenRepository;
	}

	public static boolean isMySql() {
		return RepositoryEnum.MYSQL.name().equals(chosenRepository);
	}

	public static boolean isMongoDb() {
		return RepositoryEnum.MONGODB.name().equals(chosenRepository);
	}
}
