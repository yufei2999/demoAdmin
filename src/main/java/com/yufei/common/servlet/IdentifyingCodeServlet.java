package com.yufei.common.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by pc on 2016-09-29.
 */
public class IdentifyingCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 7894119208526255121L;

    public static final String LOGINRANDOMCODE = "LOGINRANDOMCODE";// session定义
    public static final String LOGINRANDOMCODE2 = "LOGINRANDOMCODE2";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        // 取当前验证码序号 针对前台登录矾有两个取验证码
        String checkCodeNum = request.getParameter("num");

        int width = 80, height = 36;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();
        Graphics g = img.getGraphics();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设定字体
        g.setFont(new Font("Verdana", Font.ITALIC + Font.PLAIN, 20));
        // 设定随机字体
        // 设置背景颜色
        // g.setColor(new
        // Color(random.nextInt(250),random.nextInt(250),random.nextInt(250)));
        // 画边框
        g.setColor(new Color(33, 66, 99));
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生50条干扰线，使图象中的认证码不易被其它程序探测到
        // g.setColor(getRandColor(100, 150));
        g.setColor(new Color(200, 200, 200));
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String[] randStr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        // 取随机产生的认证码(6位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(randStr[random.nextInt(randStr.length - 1)]);
            sRand += rand;

            // 将认证码显示到图象中
            // 设置字体颜色
            // g.setColor(this.getRandColor(40, 60));
            g.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(100)));
            g.drawString(rand, 13 * i + 12, 26);
        }
        // 将认证码存入SESSION
        HttpSession session = request.getSession();
        session.setAttribute(IdentifyingCodeServlet.LOGINRANDOMCODE + checkCodeNum, sRand);
        // 释放图形上下文
        g.dispose();
        // 输出图象到页面
        ImageIO.write(img, "JPEG", response.getOutputStream());
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
