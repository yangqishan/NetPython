package com.example.myframe.util;

/**
 * @Description: java类作用描述
 * @author:wm
 * @date: 2019/5/23 16:37
 */
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES加密工具类
 */
public class EncryptUtils {


    public static String encrypt(String key,String text) throws  Exception {
                 try {
                         byte[] src = text.getBytes("utf-8");
                         //DESedeKeySpec会帮你生成24位秘钥，key可以是任意长度
                         DESedeKeySpec spec = new DESedeKeySpec(key.getBytes("utf-8"));
                         SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
                         SecretKey secretKey = factory.generateSecret(spec);
                         Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                         cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                         byte[] res = cipher.doFinal(src);
                         //encodeBase64会对字符串3位一组自动补全，因而最后可能会出现 == 或者 =
                         return new String(Base64.encodeBase64(res), "utf-8");

                     } catch (Exception e) {
                         System.out.println("error");
                     }
                 return null;
             }
    /**
     * 3DES加密
     * @param key 密钥，24位
     * @param srcStr 将加密的字符串
     * @return
     *
     * lee on 2017-08-09 10:51:44
     */
    public static String  encode3Des(String key,String srcStr){
        byte[] keybyte = hex(key);
        byte[] src = srcStr.getBytes();
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
            //加密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.ENCRYPT_MODE, deskey);

            String pwd = Base64.encodeBase64String(c1.doFinal(src));
//           return c1.doFinal(src);//在单一方面的加密或解密
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        }catch(javax.crypto.NoSuchPaddingException e2){
            e2.printStackTrace();
        }catch(Exception e3){
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 转换成十六进制字符串
     * @param username
     * @return
     *
     * lee on 2017-08-09 10:54:19
     */
    public static byte[] hex(String key){
        String f = DigestUtils.md5Hex(key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i=0;i<24;i++){
            enk[i] = bkeys[i];
        }
        return enk;
    }





}