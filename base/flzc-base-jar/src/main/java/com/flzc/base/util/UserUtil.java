package com.flzc.base.util;


/**
 * 
 * 获取用户id
 * @author chenqi
 *
 */
public class UserUtil {

	/**
	 * 获取用户id
	 * @param tokenId
	 * @return
	 * @throws Exception
	 */
	public static int getUserIdByTokenId(String tokenId) throws Exception{
		try {
			int userId = Integer.parseInt(LinkEncrypt.getUserIdByToken(tokenId));
			return userId;
		} catch (Exception e) {
			throw new RuntimeException("解密失败");
		}
	}
	
	public static void main(String[] args) {
		try {
			
			String tokenId = LinkEncrypt.generateToken("2");
			System.out.println(tokenId);
			System.out.println(UserUtil.getUserIdByTokenId(tokenId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
