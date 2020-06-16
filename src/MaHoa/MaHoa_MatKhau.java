/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaHoa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MaHoa_MatKhau {
  public static String encrypt(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
      String enrStr;
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] strByte =str.getBytes("UTF-8");
      byte[] enrStrByte =md.digest(strByte);
      
      BigInteger bigInt= new BigInteger(1,enrStrByte);
      enrStr=bigInt.toString(16);
      
      return enrStr;
  }  
}
