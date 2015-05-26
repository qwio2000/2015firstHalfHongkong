package com.jeiglobal.hk.repository;

@AnotherRepositoryAnnoInterface
public interface MssqlRepository {
	public String selectEncryptPassWord(String password);
}
