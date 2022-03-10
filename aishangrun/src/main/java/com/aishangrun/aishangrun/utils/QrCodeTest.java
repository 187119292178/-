package com.aishangrun.aishangrun.utils;

public class QrCodeTest {
    public static void main(String[] args) throws Exception {

        for (int i = 100; i < 101; i++) {

            // 存放在二维码中的内容
            String text = "https://www.szyxd199.com/weixin/miniprogram?qrcode=A080000" + i;
            String destPath = "C:\\Users\\yky99\\Desktop\\摩摩按\\二维码\\A080000" + i + ".jpg";
            QRCodeUtil.encode(text, null, destPath, true);


        }
    }
}